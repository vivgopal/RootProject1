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
        String[] columnNames = testdata.getColumnNames();
        tv.setText(columnNames[0]);
        mDbHelper.close();

    }
}
