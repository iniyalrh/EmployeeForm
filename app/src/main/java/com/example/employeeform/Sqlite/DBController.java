package com.example.employeeform.Sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.employeeform.DataModel.EmployeeDetailModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DBController extends SQLiteOpenHelper {
    private static final String TAG = DBController.class.getSimpleName();
    private static final int VERSION_CODE = 17; //versioncode of the database
    public static ArrayList<EmployeeDetailModel> getData;
    private boolean Upgraded=false;

private static final String DATABASE_NAME = "MyDBName.db";
private static final String Employee_TABLE_NAME ="Employee";
public static final String Employee_Column_EmpID = "EmpID";
public static final String Employee_Column_EmpName = "EmpName";
public static final String Employee_Column_EmailId = "EmailId";
public static final String Employee_Column_PhoneNumber = "PhoneNumber";
public static final String Employee_Column_Department = "Department";
public static final String Employee_Column_Designation = "Designation";
public static final String Employee_Column_Password = "Password";
public static final String Employee_Column_Salary = "Salary";





Context context;
    public DBController(Context context) {
        super(context, DATABASE_NAME,null, VERSION_CODE);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query;
        query = "CREATE TABLE IF NOT EXISTS " + Employee_TABLE_NAME + "(" + Employee_Column_EmpID + " integer primary key, "
                + Employee_Column_EmpName + " text, " + Employee_Column_EmailId + " text , "+ Employee_Column_PhoneNumber + " text, "
                + Employee_Column_Department + " text , " + Employee_Column_Designation + " text , "+ Employee_Column_Password + " text, "
                + Employee_Column_Salary + " text )";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query;
        query = "DROP TABLE IF EXISTS " + Employee_TABLE_NAME;
        sqLiteDatabase.execSQL(query);
    }

    public List<EmployeeDetailModel> getData() {
    ArrayList<HashMap<String, String>> empList = new ArrayList<>();
    SQLiteDatabase database = this.getWritableDatabase();
    Cursor cursor = database.rawQuery("SELECT * FROM " + Employee_TABLE_NAME, null);
        List<EmployeeDetailModel> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
         //   Employee_Column_EmpID="alen";
        do {
            EmployeeDetailModel model = new EmployeeDetailModel();


           HashMap<String, String> map = new HashMap<>();
            model.setEmpID(cursor.getString(0));
            model.setEmpName(cursor.getString(1));
            model.setEmailId(cursor.getString(2));
            model.setPhoneNumber(cursor.getString(3));
            model.setDepartment(cursor.getString(4));
            model.setDesignation(cursor.getString(5));
            model.setPassword(cursor.getString(6));
            model.setSalary(cursor.getString(7));
            list.add(model);
        } while (cursor.moveToNext());
    }

        cursor.close();
        database.close();

// return contact list
        return list;
}


    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Employee_TABLE_NAME);
        return numRows;
    }
    public boolean updateEmployee (Integer EmpID, String EmpName, String EmailId, String PhoneNumber,
                                   String Department, String Designation, String Password, String Salary) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues .put(Employee_Column_EmpID, EmpID);
        contentValues.put(Employee_Column_EmpName, EmpName);
        contentValues.put(Employee_Column_EmailId, EmailId);
        contentValues.put(Employee_Column_PhoneNumber, PhoneNumber);
        contentValues.put(Employee_Column_Department, Department);
        contentValues.put(Employee_Column_Designation, Designation);
        contentValues.put(Employee_Column_Password, Password);
        contentValues.put(Employee_Column_Salary, Salary);

        db.update(Employee_TABLE_NAME, contentValues, "EmpID = ? ", new String[] { Integer.toString(EmpID) } );

        return true;
    }

    public Integer deleteEmployee (Integer EmpID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(Employee_TABLE_NAME,"EmpID=?", new String[]{String.valueOf(EmpID)});
        db.close();
        return rowsAffected;
    }

@SuppressLint("Range")
public ArrayList<String> getMainActivity(){
    ArrayList<String> array_list = new ArrayList<>();
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor res = db.rawQuery("Select * from Employee", null);
    res.moveToFirst();

    while(res.isAfterLast() == false){
        array_list.add(res.getString(res.getColumnIndex(Employee_Column_EmpName)));
        res.moveToNext();
    }
    return array_list;
    }


    public Cursor getData(int EmpID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Employee where EmpID="+EmpID+"", null );
        return res;

    }

    public boolean updateEmployees(Integer EmpID, String EmpName, String EmailId, String PhoneNumber,
                                   String Department, String Designation, String Password, String Salary) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Employee_Column_EmpName, EmpName);
        contentValues.put(Employee_Column_EmailId, EmailId);
        contentValues.put(Employee_Column_PhoneNumber, PhoneNumber);
        contentValues.put(Employee_Column_Department, Department);
        contentValues.put(Employee_Column_Designation, Designation);
        contentValues.put(Employee_Column_Password, Password);
        contentValues.put(Employee_Column_Salary, Salary);

        String whereClause = Employee_Column_EmpID + " = ?";
        String[] whereArgs = { String.valueOf(EmpID) };

        int rowsAffected = db.update(Employee_TABLE_NAME, contentValues, whereClause, whereArgs);
        return rowsAffected > 0;
    }


    public boolean insertEmployee (Integer EmpID, String EmpName, String EmailId, String PhoneNumber,
                                   String Department, String Designation, String Password, String Salary) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        contentValues.put(Employee_Column_EmpID, EmpID);
        contentValues.put(Employee_Column_EmpName, EmpName);
        contentValues.put(Employee_Column_EmailId, EmailId);
        contentValues.put(Employee_Column_PhoneNumber, PhoneNumber);
        contentValues.put(Employee_Column_Department, Department);
        contentValues.put(Employee_Column_Designation, Designation);
        contentValues.put(Employee_Column_Password, Password);
        contentValues.put(Employee_Column_Salary, Salary);

        long result = db.insert(Employee_TABLE_NAME, null, contentValues);
        return result != -1;


    }



}
