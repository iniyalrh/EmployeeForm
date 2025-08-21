package com.example.employeeform.DataModel;

import java.io.Serializable;

public class EmployeeDetailModel implements Serializable {
    public  String EmpID;
    private String EmpName;
    private String EmailId;
    private String PhoneNumber;
    private String Department;
    private String Designation;
    private String Password;
    private String Salary;

public EmployeeDetailModel(String EmpID, String EmpName, String EmailId,String PhoneNumber,String Department, String Designation,String Password, String Salary ){
    this.EmpID = EmpID;
    this.EmpName = EmpName;
    this.EmailId = EmailId;
    this.PhoneNumber = PhoneNumber;
    this.Department = Department;
    this.Designation = Designation;
    this.Password = Password;
    this.Salary = Salary;
}

public EmployeeDetailModel(){

}

    public  String getEmpID() { return EmpID;}
    public void setEmpID(String empID) {
        this.EmpID = empID;
    }

    public   String getEmpName() {
        return EmpName;
    }
    public void setEmpName(String empName) {
        this.EmpName = empName;
    }

    public   String getEmailId() {
        return EmailId;
    }
    public void setEmailId(String EmailId) {
       this.EmailId = EmailId;
    }

    public   String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }


    public   String getDepartment() {
        return Department;
    }
    public void setDepartment(String department) {
        this.Department = department;
    }

    public   String getDesignation() {return Designation;}
    public void setDesignation(String designation) {
        this.Designation = designation;
    }

    public   String getPassword() {
        return Password;
    }
    public void setPassword(String Password) {
       this.Password = Password;
    }

    public   String getSalary() {
        return Salary;
    }
    public void setSalary(String Salary) {
        this.Salary = Salary;
    }


}
