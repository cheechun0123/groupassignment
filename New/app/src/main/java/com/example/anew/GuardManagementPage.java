package com.example.anew;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.CardView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class GuardManagementPage extends AppCompatActivity {

    CardView addTenant, visitor, trackLocation, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_management_page);
        getSupportActionBar().setTitle("Home");


        addTenant=findViewById(R.id.addTenant);
        addTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(GuardManagementPage.this,AddTenant.class);
                //startActivity(intent);
            }
        });

        visitor=findViewById(R.id.visitorProfiles);
        visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(GuardManagementPage.this,SearchUser.class);
                //startActivity(intent);
            }
        });

        trackLocation=findViewById(R.id.trackLocation);
        trackLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(GuardManagementPage.this,TrackLocation.class);
                //startActivity(intent);
            }
        });

        logout=findViewById(R.id.guard_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GuardManagementPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}