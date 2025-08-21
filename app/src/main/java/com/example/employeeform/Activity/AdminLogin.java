package com.example.employeeform.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.employeeform.R;

public class AdminLogin extends AppCompatActivity {

EditText et_userName,et_Password;
Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_userName=findViewById(R.id.et_userName);
        et_Password=findViewById(R.id.et_Password);
        btnLogin=findViewById(R.id.btnLogin);

        et_userName.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source != null && !source.toString().matches("[a-zA-Z]*")) {
                    return ""; // Reject non-letter input
                }
                return null; // Accept input
            }
        }});

        et_Password.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source != null && !source.toString().matches("[a-zA-Z]*")) {
                    return ""; // Reject non-letter input
                }
                return null; // Accept input
            }
        }});

    findViewById(R.id.btnLogin).setOnClickListener(v -> {
        String UserName = et_userName.getText().toString().trim();
        String Password = et_Password.getText().toString().trim();

        if(UserName.equals("Admin") && Password.equals("Admin")){

            Toast.makeText(this,"Login Successfully",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AdminLogin.this, EmployeeDetails.class);
            startActivity(intent);

        }
        else{
            Toast.makeText(this,"Invalid Login",Toast.LENGTH_SHORT).show();
        }

});

    }
}