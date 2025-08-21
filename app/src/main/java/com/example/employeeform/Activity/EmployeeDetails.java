package com.example.employeeform.Activity;



import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeeform.Adapter.EmpAdapter;
import com.example.employeeform.DataModel.EmployeeDetailModel;
import com.example.employeeform.R;
import com.example.employeeform.Sqlite.DBController;
import com.example.employeeform.listener.ItemclickListener;


import java.util.ArrayList;
import java.util.List;

public class EmployeeDetails extends AppCompatActivity {
    Button btnAddEmp;
    RecyclerView rv_emp_list;
    DBController MyDBName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MyDBName = new DBController(this);
       // ArrayList<String> array_list = MyDBName.getMainActivity();
       btnAddEmp = findViewById(R.id.btnAddEmp);
        rv_emp_list = findViewById(R.id.rv_emp_list);

        ArrayList<EmployeeDetailModel> empList = new ArrayList<>();
        Intent intent = getIntent();
        if(intent.hasExtra("list")){
           empList = (ArrayList<EmployeeDetailModel>) getIntent().getSerializableExtra("list");

        }

        RecyclerView recyclerView= findViewById(R.id.rv_emp_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.e("list",""+MyDBName.getData().toString());
        empList.addAll(MyDBName.getData());
        //activity

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        EmpAdapter adapter = new EmpAdapter(this, empList, (model, position, type) -> {
            if(type == 1){
                Intent intent1 = new Intent(EmployeeDetails.this, MainActivity.class);
                intent1.putExtra("flag",type);
                intent1.putExtra("model",model);
                startActivity(intent1);
            }else{
                int RowDeleted = MyDBName.deleteEmployee(Integer.valueOf(model.EmpID));

                if (RowDeleted>0) {
                    Toast.makeText(this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, " Failed", Toast.LENGTH_SHORT).show();
                }
            }

        });
        recyclerView.setAdapter(adapter);

        btnAddEmp.setOnClickListener(view -> {


            Toast.makeText(this,"Added Successfully",Toast.LENGTH_SHORT).show();

            Intent intent1 = new Intent(EmployeeDetails.this, MainActivity.class);
            startActivity(intent1);

        });



    }
}