package com.example.asgm2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTenant extends AppCompatActivity {

    private EditText nameET, phoneET, icET, emailET;
    private Button addTenantBtn;
    private TenantDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tenant);

        nameET = findViewById(R.id.nameET);
        phoneET = findViewById(R.id.phoneET);
        icET = findViewById(R.id.icET);
        emailET = findViewById(R.id.emailET);
        addTenantBtn = findViewById(R.id.addTenantBtn);

        //create new tenantdbhelper
        dbHelper = new TenantDBHelper(AddTenant.this);

        addTenantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String email = emailET.getText().toString();
                String icNo = icET.getText().toString();
                String phoneNo = phoneET.getText().toString();

                if (name.isEmpty() || email.isEmpty() || icNo.isEmpty() || phoneNo.isEmpty()){
                    Toast.makeText(AddTenant.this, "Please enter all information.", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHelper.addNewTenant(icNo, name, phoneNo, email);

                Toast.makeText(AddTenant.this, "Tenant " + name + "has been added.", Toast.LENGTH_SHORT).show();
                icET.setText("");
                nameET.setText("");
                phoneET.setText("");
                emailET.setText("");
            }
        });
    }
}