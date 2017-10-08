package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.DatabaseAdapter;
import com.example.vivekgopal.project1.data.CertificationItem;
import com.example.vivekgopal.project1.data.DataItem;
import com.example.vivekgopal.project1.data.SalaryItem;

import java.util.ArrayList;
import java.util.List;

public class DisplaySalaryActivity extends AppCompatActivity {

    String title;
    String subtitle;
    List<SalaryItem> salaryItemList;
    List<DataItem> dataItemsItemList;

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

        DatabaseAdapter mDbAdapter = new DatabaseAdapter(getApplicationContext());
        mDbAdapter.createDatabase();
        mDbAdapter.open();

        TableLayout table = (TableLayout)DisplaySalaryActivity.this.findViewById(R.id.salary_table);

        salaryItemList = new ArrayList<>();
        salaryItemList = mDbAdapter.getAllSalaries();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Try accessing different types of rows with adapter
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        salaryItemList.add(mDbAdapter.getSalary("SAP"));
//        salaryItemList.add(mDbAdapter.getSalary("Medtronic"));
//        salaryItemList.add(mDbAdapter.getSalary("Infosys"));
//
//        dataItemsItemList = new ArrayList<>();
//        dataItemsItemList = mDbAdapter.getTips("biomedical engineering", "consultant");
//        for (DataItem item : dataItemsItemList) {
//            TableRow row = (TableRow) LayoutInflater.from(DisplaySalaryActivity.this).inflate(R.layout.table_row, null);
//            ((TextView) row.findViewById(R.id.row_name)).setText(item.getTips());
//            ((TextView) row.findViewById(R.id.row_value)).setText(item.getSpecialization());
//            row.setPadding(5,5,5,5);
//            table.addView(row);
//        }
//        List<String> strList = new ArrayList<>();
//        strList = mDbAdapter.getTips("biomedical engineering", "consultant");
//        for (String tip : strList) {
//            TableRow row = (TableRow) LayoutInflater.from(DisplaySalaryActivity.this).inflate(R.layout.table_row, null);
//            ((TextView) row.findViewById(R.id.row_name)).setText(tip);
//            ((TextView) row.findViewById(R.id.row_value)).setText("consultant");
//            row.setPadding(5,5,5,5);
//            table.addView(row);
//        }
//
//        List<CertificationItem> certificationItems = new ArrayList<>();
//        certificationItems = mDbAdapter.getCertifications("biomedical engineering", "consultant");
//        for (CertificationItem item : certificationItems) {
//            TableRow row = (TableRow) LayoutInflater.from(DisplaySalaryActivity.this).inflate(R.layout.table_row, null);
//            ((TextView) row.findViewById(R.id.row_name)).setText(item.getName());
//            ((TextView) row.findViewById(R.id.row_value)).setText(item.getUrl());
//            row.setPadding(5,5,5,5);
//            table.addView(row);
//        }
//
//        //strList = new;
//        strList = mDbAdapter.getLadder("biomedical engineering", "consultant");
//        for (String position : strList) {
//            TableRow row = (TableRow) LayoutInflater.from(DisplaySalaryActivity.this).inflate(R.layout.table_row, null);
//            ((TextView) row.findViewById(R.id.row_name)).setText(position);
//            ((TextView) row.findViewById(R.id.row_value)).setText("consultant");
//            row.setPadding(5,5,5,5);
//            table.addView(row);
//        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        for (SalaryItem item : salaryItemList) {
            TableRow row = (TableRow) LayoutInflater.from(DisplaySalaryActivity.this).inflate(R.layout.table_row, null);
            ((TextView) row.findViewById(R.id.row_name)).setText(item.getCompany());
            ((TextView) row.findViewById(R.id.row_value)).setText("₹" + item.getSalary());
            row.setPadding(5,5,5,5);
            table.addView(row);
        }

        mDbAdapter.close();

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
