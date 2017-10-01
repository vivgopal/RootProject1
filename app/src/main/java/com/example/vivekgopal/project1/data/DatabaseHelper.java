package com.example.vivekgopal.project1.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sreerakshakr on 5/20/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;
    private final Context context;

    protected static final String TAG = "DatabaseHelper";
    private static String DB_PATH = "";
    private static String DB_NAME = "pathXploreDB.db";



    // Constructor
    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        if(android.os.Build.VERSION.SDK_INT >= 17){
            //DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";

        }
        else
        {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.context = context;
    }


    // Create a new empty database and copy over existing database
    public void createDataBase() throws IOException{
        boolean dbExists = checkDataBase();

        //if(dbExists){
        //    //database already exists
        //}else{
            this.getReadableDatabase(); //Empty database will be created

            try {
                copyDataBase();
                Log.d(TAG, "Vivek - Database copied");
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        //}

    }


    // Check if database already exists
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String path = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            Log.d(TAG, "Vivek - Database is " + path);
            Log.d(TAG, "Vivek - checkDB value is" + checkDB);

        }catch(SQLiteException e){
            //database does't exist yet.
            Log.e(TAG, "Database does not exist");
        }

        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }


    // Copy database from local assets file to a system file
    private void copyDataBase() throws IOException{

        //Open your database as the input stream
        InputStream myInput = context.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }


    // Method to open database
    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Log.d(TAG, "Vivek - Opening Database ... " + myPath);

    }


    @Override
    public synchronized void close() {
        if(database != null)
            database.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
