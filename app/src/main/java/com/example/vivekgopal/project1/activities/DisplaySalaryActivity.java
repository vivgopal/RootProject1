package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.DatabaseAdapter;

public class DisplaySalaryActivity extends AppCompatActivity {

    String title;
    String subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_table);

        final Button homeButton = (Button) findViewById(R.id.generic_header_home_button);
        TextView titleTextView = (TextView) findViewById(R.id.generic_header_title);
        TextView subtitleTextView = (TextView) findViewById(R.id.generic_header_subtitle);

        initButtonStrings();

        titleTextView.setText(title);
        if(subtitle != "") {
            subtitleTextView.setText(subtitle);
        }

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

        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void initButtonStrings() {
        Bundle bundle = this.getIntent().getExtras();
        this.title = bundle.getString("titleKey");
        this.subtitle = bundle.getString("subtitleKey");
    }
}
