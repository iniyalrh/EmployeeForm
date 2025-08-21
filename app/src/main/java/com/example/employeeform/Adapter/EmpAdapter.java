package com.example.employeeform.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeeform.Activity.EmployeeDetails;
import com.example.employeeform.DataModel.EmployeeDetailModel;
import com.example.employeeform.R;
import com.example.employeeform.Sqlite.DBController;
import com.example.employeeform.listener.ItemclickListener;

import java.util.ArrayList;
import java.util.List;

public class EmpAdapter extends RecyclerView.Adapter<EmpAdapter.MyViewHolder>  {

    private ArrayList<EmployeeDetailModel> empList =new ArrayList<>();
    private Context context;
    private ItemclickListener listener;

//    public EmpAdapter(Context context, ArrayList<String> Employee){
//
//    }

    public EmpAdapter(EmployeeDetails context, ArrayList<EmployeeDetailModel> empList,ItemclickListener listener) {
        this.context= context;
        this.empList= empList;
        this.listener= listener;
    }


    @Override
    public  MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empdetails,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         EmployeeDetailModel emp = empList.get(position);

        holder.EmpID.setText("Employee Id : "+emp.getEmpID());
        holder.EmpName.setText("Employee Name : "+emp.getEmpName());
        holder.Department.setText("Employee Department : "+emp.getDepartment());
        holder.Designation.setText("Employee Designation : "+emp.getDesignation());

        holder.btnUpdateEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onButtonClick(emp,holder.getAdapterPosition(),1);
                }
                // Handle button click for the item at 'position'
                // You can get the data for this item using your data list and 'position'
                // Example: MyData item = dataList.get(position);
            }
        });
        holder.btnDeleteEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onButtonClick(emp,holder.getAdapterPosition(),2);
                }
                // Handle button click for the item at 'position'
                // You can get the data for this item using your data list and 'position'
                // Example: MyData item = dataList.get(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return empList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
      TextView EmpID, EmpName, Department, Designation;
        ImageView btnUpdateEmp, btnDeleteEmp;



        public MyViewHolder(View Employee) {
            super(Employee);

            EmpID= Employee.findViewById(R.id.tv_empId);
            EmpName = Employee.findViewById(R.id.tv_empName);
            Department = Employee.findViewById(R.id.tv_dep);
            Designation =  Employee.findViewById(R.id.tv_des);
            btnUpdateEmp = Employee.findViewById(R.id.btnUpdateEmp);
            btnDeleteEmp = Employee.findViewById(R.id.btnDelete);

        }
    }
}
