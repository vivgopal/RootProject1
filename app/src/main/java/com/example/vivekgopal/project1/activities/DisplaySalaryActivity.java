package com.example.vivekgopal.project1.activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.DatabaseAdapter;

public class DisplaySalaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_table);
        DatabaseAdapter mDbHelper = new DatabaseAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        TableLayout table = (TableLayout)DisplaySalaryActivity.this.findViewById(R.id.salary_table);
        Cursor testdata = mDbHelper.getTestData();

        while (testdata.moveToNext()) {
            TableRow row = (TableRow) LayoutInflater.from(DisplaySalaryActivity.this).inflate(R.layout.table_row, null);
            ((TextView) row.findViewById(R.id.row_name)).setText(testdata.getString(1));
            ((TextView) row.findViewById(R.id.row_value)).setText(testdata.getString(2));
            table.addView(row);
        }

        mDbHelper.close();
    }
}
