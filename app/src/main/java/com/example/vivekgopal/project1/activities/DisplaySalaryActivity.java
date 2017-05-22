package com.example.vivekgopal.project1.activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.DatabaseAdapter;

public class DisplaySalaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_table);

        TextView tv = (TextView) findViewById(R.id.sample_tv);
        DatabaseAdapter mDbHelper = new DatabaseAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor testdata = mDbHelper.getTestData();
        //String[] columnNames = testdata.getColumnNames();
        //int arraySize = columnNames.length;
        //for(int i = 0; i < arraySize; i++) {
        //    tv.append(columnNames[i]);
        //    tv.append("\n");
        //}
        while (testdata.moveToNext()) {
            tv.append("" + testdata.getInt(0));
            tv.append("\n");
            tv.append(testdata.getString(1));
            tv.append("\n");
            tv.append(testdata.getString(2));
            tv.append("\n");
        }

        tv.append("count = " + testdata.getCount() + "\n");
        mDbHelper.close();

    }
}
