package com.example.vivekgopal.project1.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.vivekgopal.project1.data.CertificationItem;
import com.example.vivekgopal.project1.data.DataItem;
import com.example.vivekgopal.project1.data.DatabaseHelper;
import com.example.vivekgopal.project1.data.CompanyItem;
import com.example.vivekgopal.project1.data.SkillItem;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.vivekgopal.project1.R.string.certifications;

/**
 * Created by sreerakshakr on 5/20/17.
 */

public class DatabaseAdapter {

    protected static final String TAG = "DataAdapter";

    // Table names
    private static final String TABLE_COMPANY = "COMPANY_TABLE";
    private static final String TABLE_DATA = "DATA_TABLE";
    private static final String TABLE_CERTIFICATION = "CERTIFICATION_TABLE";
    private static final String TABLE_SKILL = "SKILL_TABLE";

    // COMPANY_TABLE Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_COMPANY = "company";
    private static final String KEY_SALARY = "salary";
    private static final String KEY_URL = "url";

    // DATA_TABLE Columns names
    //private static final String KEY_ID = "_id";
    private static final String KEY_STREAM = "stream";
    private static final String KEY_SPECIALIZATION = "specialization";
    private static final String KEY_SKILL = "skill";
    private static final String KEY_CERTIFICATION = "certification";
    private static final String KEY_TIPS = "tips";
    private static final String KEY_LADDER = "ladder";
    //private static final String KEY_COMPANY = "company";

    // SKILL_TABLE Columns names
    //private static final String KEY_ID = "_id";
    //private static final String KEY_SKILL = "skill";
    //private static final String KEY_URL = "url";

    // CERTIFICATION_TABLE Columns names
    //private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SOURCE = "source";
    //private static final String KEY_URL = "url";

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
            //mDbHelper.close();
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

    public boolean isOpen() {
        return mDbHelper.isOpen();
    }

    // All CRUD Operation routines

    // Getting All CompanyItems
    public List<CompanyItem> getAllCompanyItems() {
        List<CompanyItem> companyItemList = new ArrayList<CompanyItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_COMPANY;

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CompanyItem item = new CompanyItem();
                item.setCompany(cursor.getString(1));
                item.setSalary(cursor.getInt(2));
                item.setUrl(cursor.getString(3));
                // Adding contact to list
                companyItemList.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list
        return companyItemList;
    }

    // Getting single CompanyItem
    public CompanyItem getCompanyItem(String company) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_COMPANY, new String[] { KEY_ID,
                        KEY_COMPANY, KEY_SALARY, KEY_URL}, KEY_COMPANY + "=?",
                new String[] { company }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        CompanyItem item = new CompanyItem();
        item.setCompany(cursor.getString(1));
        item.setSalary(cursor.getInt(2));
        item.setUrl(cursor.getString(3));

        return item;
    }

    // Getting single Company URL
    public String getCompanyUrl(String company) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_COMPANY, new String[] { KEY_ID,
                        KEY_COMPANY, KEY_SALARY, KEY_URL}, KEY_COMPANY + "=?",
                new String[] { company }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getString(3);
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

    // Getting All SkillItems
    public List<SkillItem> getAllSkillItems() {
        List<SkillItem> skillItemList = new ArrayList<SkillItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SKILL;

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SkillItem item = new SkillItem();
                item.setSkill(cursor.getString(1));
                item.setUrl(cursor.getString(2));
                // Adding contact to list
                skillItemList.add(item);
            } while (cursor.moveToNext());
        }

        // return skill list
        return skillItemList;
    }

    // Getting skills
    public List<String> getSkills(String stream, String specialization) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        List<String> skillsList = new ArrayList();

        // Get all skills for given stream and specialization
        Cursor cursor = db.query(TABLE_DATA, new String[]{KEY_ID,
                        KEY_STREAM, KEY_SPECIALIZATION, KEY_SKILL,
                        KEY_CERTIFICATION, KEY_TIPS, KEY_LADDER, KEY_COMPANY},
                KEY_STREAM + "=? AND " + KEY_SPECIALIZATION + "=? AND " + KEY_SKILL + " IS NOT NULL",
                new String[]{stream, specialization}, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                skillsList = Arrays.asList(cursor.getString(3).split(","));
            } while (cursor.moveToNext());
        }

        return skillsList;
    }

    public List<SkillItem> getSkillItems(List<String> skillsList) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        List<SkillItem> skillItemList = new ArrayList();

        for (String skill:skillsList) {
                // Adding item to list
                SkillItem item = new SkillItem();
                item.setSkill(skill);
                skillItemList.add(item);
        }

        // Get Wikipedia URL for each skill
        int i = 0;
        for(SkillItem item:skillItemList) {
            Cursor cursor = db.query(TABLE_SKILL, new String[]{KEY_ID,
                                KEY_SKILL, KEY_URL},
                        KEY_SKILL + "=?",
                        new String[]{item.getSkill()}, null, null, null, null);

                if (cursor != null) {
                        cursor.moveToFirst();
                        skillItemList.get(i).setUrl(cursor.getString(2));
                        i++;

                }
        }

        return skillItemList;
    }

    // Getting All SkillItems
    public List<CertificationItem> getAllCertificationItems() {
        List<CertificationItem> certificationItemList = new ArrayList<CertificationItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CERTIFICATION;

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CertificationItem item = new CertificationItem();
                item.setName(cursor.getString(1));
                item.setSource(cursor.getString(2));
                item.setUrl(cursor.getString(3));
                // Adding contact to list
                certificationItemList.add(item);
            } while (cursor.moveToNext());
        }

        // return skill list
        return certificationItemList;
    }

    // Getting Certification
    public List<String> getCertifications(String stream, String specialization) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        List<String> certifications = new ArrayList();

        Cursor cursor = db.query(TABLE_DATA, new String[] { KEY_ID,
                        KEY_STREAM, KEY_SPECIALIZATION, KEY_SKILL,
                        KEY_CERTIFICATION, KEY_TIPS, KEY_LADDER, KEY_COMPANY},
                KEY_STREAM + "=? AND " + KEY_SPECIALIZATION + "=? AND " + KEY_CERTIFICATION + " IS NOT NULL",
                new String[] {stream , specialization}, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                // Adding item to list
                certifications = Arrays.asList(cursor.getString(4).split(","));
            } while (cursor.moveToNext());
        }
        return certifications;
    }

    // Getting Certification
    public List<CertificationItem> getCertificationItems(List<String> certificationList) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        List<CertificationItem> certificationItemList = new ArrayList();

        for(String certification : certificationList) {
            Cursor cursor = db.query(TABLE_CERTIFICATION, new String[] { KEY_ID,
                            KEY_NAME, KEY_SOURCE, KEY_URL},
                    KEY_NAME + "=?",
                    new String[] {certification}, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    CertificationItem item = new CertificationItem();
                    item.setName(cursor.getString(1));
                    item.setSource(cursor.getString(2));
                    item.setUrl(cursor.getString(3));
                    // Adding item to list
                    certificationItemList.add(item);
                } while (cursor.moveToNext());
            }
        }

        return certificationItemList;
    }

    // Getting Tips
    public List<String> getTips(String stream, String specialization) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        List<String> tips = new ArrayList();

        Cursor cursor = db.query(TABLE_DATA, new String[] { KEY_ID,
                        KEY_STREAM, KEY_SPECIALIZATION, KEY_SKILL,
                        KEY_CERTIFICATION, KEY_TIPS, KEY_LADDER, KEY_COMPANY},
                KEY_STREAM + "=? AND " + KEY_SPECIALIZATION + "=? AND " + KEY_TIPS + " IS NOT NULL",
                new String[] {stream , specialization}, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                tips.add(cursor.getString(5));
            } while (cursor.moveToNext());
        }
        return tips;
    }

    // Getting Ladder
    public List<String> getLadder(String stream, String specialization) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        List<String> titles = new ArrayList();

        Cursor cursor = db.query(TABLE_DATA, new String[] { KEY_ID,
                        KEY_STREAM, KEY_SPECIALIZATION, KEY_SKILL,
                        KEY_CERTIFICATION, KEY_TIPS, KEY_LADDER, KEY_COMPANY},
                KEY_STREAM + "=? AND " + KEY_SPECIALIZATION + "=? AND " + KEY_LADDER + " IS NOT NULL",
                new String[] {stream , specialization}, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                titles = Arrays.asList(cursor.getString(6).split(","));
            } while (cursor.moveToNext());
        }

        return titles;
    }

    // Getting Companies
    public List<String> getCompanies(String stream, String specialization) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        List<String> companies = new ArrayList();

        Cursor cursor = db.query(TABLE_DATA, new String[] { KEY_ID,
                        KEY_STREAM, KEY_SPECIALIZATION, KEY_SKILL,
                        KEY_CERTIFICATION, KEY_TIPS, KEY_LADDER, KEY_COMPANY},
                KEY_STREAM + "=? AND " + KEY_SPECIALIZATION + "=? AND " + KEY_COMPANY + " IS NOT NULL",
                new String[] {stream , specialization}, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                companies = Arrays.asList(cursor.getString(7).split(","));
            } while (cursor.moveToNext());
        }

        return companies;
    }

    public boolean checkDataInDB(String TableName, String dbfield, String fieldValue) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String Query = "SELECT * FROM  " + TableName + " WHERE " + dbfield + " LIKE \"" + fieldValue + "\"";
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
