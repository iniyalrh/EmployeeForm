package com.example.employeeform.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Patterns;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.employeeform.DataModel.EmployeeDetailModel;
import com.example.employeeform.R;
import com.example.employeeform.Sqlite.DBController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kotlin.collections.IndexedValue;

public class MainActivity extends AppCompatActivity {
    EditText et_emp_id, et_emp_name, et_email_id, et_phone_num, et_Password, et_Salary;
    Button btnSave;
    int flag=0;
    EmployeeDetailModel model;
    Spinner emp_dep_spinner, emp_des_spinner;
    String department = "", desig = "";
    String[] departmentArray = {"Select Department","CSE", "ECE"};
    String[] designationArray = {"Select Designation","Principal", "Dean"};
    private DBController MyDBName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_emp_id = findViewById(R.id.et_emp_id);
        et_emp_name = findViewById(R.id.et_emp_name);
        et_email_id = findViewById(R.id.et_email_id);
        et_phone_num = findViewById(R.id.et_phone_num);
        emp_dep_spinner = findViewById(R.id.emp_dep_spinner);
        emp_des_spinner = findViewById(R.id.emp_des_spinner);
        et_Password = findViewById(R.id.et_Password);
        et_Salary = findViewById(R.id.et_Salary);
        btnSave = findViewById(R.id.btnSave);
         Intent intent = getIntent();
        if(intent.hasExtra("flag"))
            flag = intent.getIntExtra("flag",0);
        if(intent.hasExtra("model"))
            model = (EmployeeDetailModel)intent.getSerializableExtra("model");
        MyDBName = new DBController(this);

      //  DBController DBController_ = new DBController(this);
//
       // Bundle extras = getIntent().getExtras();


             /*   Cursor rs = MyDBName.getData(Value);
                int id_To_Update = Value;
                rs.moveToFirst();

                String EmpID = rs.getString(rs.getColumnIndexOrThrow(DBController.Employee_Column_EmpID));
                String EmpName = rs.getString(rs.getColumnIndexOrThrow(DBController.Employee_Column_EmpName));
                String EmailId = rs.getString(rs.getColumnIndexOrThrow(DBController.Employee_Column_EmailId));
                String PhoneNumber = rs.getString(rs.getColumnIndexOrThrow(DBController.Employee_Column_PhoneNumber));
                String Department = rs.getString(rs.getColumnIndexOrThrow(DBController.Employee_Column_Department));
                String Designation = rs.getString(rs.getColumnIndexOrThrow(DBController.Employee_Column_Designation));
                String Password = rs.getString(rs.getColumnIndexOrThrow(DBController.Employee_Column_Password));
                String Salary = rs.getString(rs.getColumnIndexOrThrow(DBController.Employee_Column_Salary));*/

                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departmentArray);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, designationArray);

                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
                emp_dep_spinner.setAdapter(adapter1);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                emp_des_spinner.setAdapter(adapter);

               /* et_emp_id.setFilters(new InputFilter[]{new InputFilter() {
                   @Override
                   public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        // Allow only letters and spaces
                        if (source != null && !source.toString().matches("^[a-zA-Z0-9._%+-]")) {
                            return ""; // Reject the input
                        }
                        return null; // Accept the input
                    }
                }});/*/

                et_emp_name.setFilters(new InputFilter[]{new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        // Allow only letters and spaces
                        if (source != null && !source.toString().matches("[a-zA-Z]*")) {
                            return ""; // Reject the input
                        }
                        return null; // Accept the input
                    }
                }});

                /*et_email_id.setFilters(new InputFilter[]{new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        // Allow only letters and spaces
                        if (source != null && !source.toString().matches("[a-zA-Z0-9@._-]")) {
                            return ""; // Reject the input
                        }
                        return null; // Accept the input
                    }
                }});*/

               /*/ et_phone_num.setFilters(new InputFilter[]{new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        if (source != null && !source.toString().matches("^[a-zA-Z0-9._%+-]")) {
                            return ""; // Reject non-letter input
                        }
                        return null; // Accept input
                    }
                }});*/

                et_Password.setFilters(new InputFilter[]{new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        if (source != null && !source.toString().matches("[a-zA-Z]*")) {
                            return ""; // Reject non-letter input
                        }
                        return null; // Accept input
                    }
                }});

               /* et_Salary.setFilters(new InputFilter[]{new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        if (source != null && !source.toString().matches("^[a-zA-Z0-9._%+-]")) {
                            return ""; // Reject non-letter input
                        }
                        return null; // Accept input
                    }
                }});*/

                 // Example String array
                emp_dep_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                        if (parent.getId() == R.id.emp_dep_spinner) {

                            department = parent.getItemAtPosition(position).toString();
                            // Toast.makeText(MainActivity.this, "Selected: " + selected, Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }


                });

                emp_des_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                        if (parent.getId() == R.id.emp_des_spinner) {

                            desig = parent.getItemAtPosition(position).toString();
                            //Toast.makeText(MainActivity.this, "Selected: " + selected, Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }


                });


                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        validateInputs();
                    }
                });
if(flag == 1){
    updateUI(model);
}
        }
        private  void updateUI(EmployeeDetailModel model){

            et_emp_id.setText(String.valueOf(model.EmpID));
            et_emp_name.setText(model.getEmpName());
            et_email_id.setText(model.getEmailId());
            et_phone_num.setText(model.getPhoneNumber());
            //String Department = department;
            emp_dep_spinner.setSelection(getSelectedSpinnerPosition(model.getDepartment(),1));
            emp_des_spinner.setSelection(getSelectedSpinnerPosition(model.getDesignation(),0));

            //String Designation = desig;
            et_Password.setText(model.getPassword());
            et_Salary.setText(model.getSalary());




            /*String EmpName = et_emp_name.getText().toString().trim();
            String EmailId = et_email_id.getText().toString().trim();
            String PhoneNumber = et_phone_num.getText().toString().trim();
            String Department = department;
            String Designation = desig;
            String Password = et_Password.getText().toString().trim();
            String Salary = et_Salary.getText().toString().trim();*/
        }

        private int getSelectedSpinnerPosition(String selectedName,int type){
        if(type == 1){
            for(int i = 0;i<=departmentArray.length;i++)
                if(selectedName.contains(departmentArray[i])){
                    return i;
                }
        }else{
            for(int i = 0;i<=designationArray.length;i++)
                if(selectedName.contains(designationArray[i])){
                    return i;
                }
        }
        return 0;
        }


    private void validateInputs() {
        int EmpId = 0;
        EmpId = Integer.parseInt(et_emp_id.getText().toString().trim());
        String EmpName = et_emp_name.getText().toString().trim();
        String EmailId = et_email_id.getText().toString().trim();
        String PhoneNumber = et_phone_num.getText().toString().trim();
        String Department = department;
        String Designation = desig;
        String Password = et_Password.getText().toString().trim();
        String Salary = et_Salary.getText().toString().trim();


        if (EmpId == 0) {
            et_emp_id.setError("EmpId is required");
            et_emp_id.requestFocus();
            return;
        }
        if (EmpName.isEmpty()) {
            et_emp_name.setError("EmpName is required");
            et_emp_name.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(EmailId).matches()) {
            et_email_id.setError("EmailId is required");
            et_email_id.requestFocus();
            return;
        }

        if (PhoneNumber.length() < 10) {
            et_phone_num.setError("PhoneNumber is required");
            et_phone_num.requestFocus();
            return;
        }

        //if (emp_dep_spinner.getSelectedItem().toString().trim().equals("Select department")) {
        //   Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        //}

        //if (emp_des_spinner.getSelectedItem().toString().trim().equals("Select Designation")) {
        //  Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        //}

        /*if (PhoneNumber.length() < 10) {
            et_phone_num.setError("PhoneNumber is required");
            et_phone_num.requestFocus();
            return;
        }*/

        if (Password.isEmpty()) {
            et_Password.setError("Password is required");
            et_Password.requestFocus();
            return;
        }
        if (Password.length() < 5) {
            et_Password.setError("Password must be at least 5 characters");
            et_Password.requestFocus();
            return;
        }

        if (Salary.isEmpty()) {
            et_Salary.setError("Salary is required");
            et_Salary.requestFocus();
            return;
        }

        List<EmployeeDetailModel> empList = new ArrayList<>();

        empList.add(new EmployeeDetailModel("EmpID : " + EmpId, "EmpName : " + EmpName, "EmailId : " + EmailId, "PhoneNumber : " + PhoneNumber, "Department : " + Department, "Designation : " + Designation, "Password : " + Password, "Salary : " + Salary));

        SharedPreferences sp = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("EmpID", EmpId);
        editor.putString("EmpName", EmpName);
        editor.putString("EmailId", EmailId);
        editor.putString("PhoneNumber", PhoneNumber);
        editor.putString("Department", Department);
        editor.putString("Designation", Designation);
        editor.putString("Password", Password);
        editor.putString("Salary", Salary);

        Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
 if(flag == 1){
    boolean isUpdated = MyDBName.updateEmployees(EmpId, EmpName, EmailId, PhoneNumber, Department , Designation, Password,Salary);

    if (isUpdated) {
        Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, EmployeeDetails.class);
        startActivity(intent);
        finish();
    } else {
        Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
    }
 } else {
    boolean success = MyDBName.insertEmployee(EmpId, EmpName, EmailId, PhoneNumber, Department , Designation, Password,Salary);

    if (success) {
        Toast.makeText(this, "Employee inserted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, EmployeeDetails.class);
        startActivity(intent);
        finish();
    } else {
        Toast.makeText(this, "Insert failed", Toast.LENGTH_SHORT).show();
    }
}

     //   if (!rs.isClosed()) {
       //     rs.close();

    }

        }




