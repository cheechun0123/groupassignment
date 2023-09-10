package com.example.anew;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.CardView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class userPage extends AppCompatActivity {

    CardView announcement, visitorProfile, createNotification, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        getSupportActionBar().setTitle("Home");


        announcement=findViewById(R.id.announcement);
        // Retrieve the visitor's email from the intent
        String email = getIntent().getStringExtra("email");

        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(userPage.this,Announcement.class);
                //startActivity(intent);
            }
        });
        visitorProfile=findViewById(R.id.visitorProfile);
        visitorProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(userPage.this,VisitorDetail.class);
                //Intent passEmail = new Intent(userPage.this,VisitorDetail.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        createNotification=findViewById(R.id.scanQR);
        createNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(userPage.this,ScanQR.class);
                startActivity(intent);
            }
        });

        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(userPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}