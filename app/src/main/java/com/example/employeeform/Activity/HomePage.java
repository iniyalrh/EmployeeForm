package com.example.employeeform.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.employeeform.R;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);
        CardView cv_Admin, cv_Employee;


        cv_Admin = findViewById(R.id.cv_Admin);
        cv_Admin.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, AdminLogin.class);
            startActivity(intent);
        });

        cv_Employee = findViewById(R.id.cv_Employee);
        cv_Employee.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, EmployeeLogin.class);
            startActivity(intent);
        });

    }
}