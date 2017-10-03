package com.example.vivekgopal.project1.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vivekgopal.project1.data.DataItem;
import com.example.vivekgopal.project1.data.DatabaseHelper;
import com.example.vivekgopal.project1.data.SalaryItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sreerakshakr on 5/20/17.
 */

public class DatabaseAdapter {

    protected static final String TAG = "DataAdapter";

    // Table names
    private static final String TABLE_SALARY = "SALARY_TABLE";
    private static final String TABLE_DATA = "DATA_TABLE";


    // SALARY_TABLE Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_COMPANY = "company";
    private static final String KEY_SALARY = "salary";

    // DATA_TABLE Columns names
    private static final String KEY_STREAM = "stream";
    private static final String KEY_SPECIALIZATION = "specialization";
    private static final String KEY_SKILL = "skill";
    private static final String KEY_CERTIFICATION = "certification";
    private static final String KEY_TIPS = "tips";
    private static final String KEY_LADDER = "ladder";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;

    public DatabaseAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DatabaseHelper(mContext);
    }

    public DatabaseAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DatabaseAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public Cursor getTestData()
    {
        try
        {
            Cursor dbCursor = mDb.query("SALARY_TABLE", null, null, null, null, null, null);
            //Cursor dbCursor = mDb.query("salary_table", null, null, null, null, null, null);
            //Cursor dbCursor = mDb.rawQuery("SELECT company_name, salary FROM salary_table", null);
            /*
            String sql ="SELECT * FROM salary_table";

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                mCur.moveToNext();
            }
            //Log.d(TAG, "getTestData >>"+ mCur.toString());
            return mCur;
            */
            return dbCursor;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    // All CRUD Operation routines

    // Getting All Salaries
    public List<SalaryItem> getAllSalaries() {
        List<SalaryItem> salaryList = new ArrayList<SalaryItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SALARY;

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SalaryItem item = new SalaryItem();
                item.set_id(cursor.getInt(0));
                item.setCompany(cursor.getString(1));
                item.setSalary(cursor.getInt(2));
                // Adding contact to list
                salaryList.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list
        return salaryList;
    }

    // Getting single Salary
    public SalaryItem getSalary(String company) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        Cursor cursor = db.query(TABLE_SALARY, new String[] { KEY_ID,
                        KEY_COMPANY, KEY_SALARY }, KEY_COMPANY + "=?",
                new String[] { String.valueOf(company) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        SalaryItem item = new SalaryItem();
        item.set_id(cursor.getInt(0));
        item.setCompany(cursor.getString(1));
        item.setSalary(cursor.getInt(2));

        return item;
    }

    // Getting All Data
    public List<DataItem> getAllData() {
        List<DataItem> dataList = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataItem item = new DataItem();
                item.set_id(cursor.getString(0));
                item.setStream(cursor.getString(1));
                item.setSpecialization(cursor.getString(2));
                item.setSkill(cursor.getString(3));
                item.setCertification(cursor.getString(4));
                item.setTips(cursor.getString(5));
                item.setLadder(cursor.getString(6));
                // Adding item to list
                dataList.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }
}
