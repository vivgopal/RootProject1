package com.example.vivekgopal.project1.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.data.CompanyItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisplaySalariesActivity extends GenericDbActivity {

    List<String> companyList;
    List<CompanyItem> salaryItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_table);
        setupView();
        subtitleTextView.setText(subtitle + " | Top Salaries");

        TableLayout table = (TableLayout)DisplaySalariesActivity.this.findViewById(R.id.salary_table);

        openDatabase();
        companyList = mDbAdapter.getCompanies(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle));
        salaryItemList = new ArrayList<>();
        for (String company : companyList) {
            CompanyItem item = mDbAdapter.getCompanyItem(company);
            if(item.getSalary() != 0) {
                salaryItemList.add(item);
            }
        }

        Collections.sort(salaryItemList);

        for (CompanyItem item : salaryItemList) {
            TableRow row = (TableRow) LayoutInflater.from(DisplaySalariesActivity.this).inflate(R.layout.layout_table_row, null);
            ((TextView) row.findViewById(R.id.row_name)).setText(item.getCompany());
            ((TextView) row.findViewById(R.id.row_value)).setText("₹" + item.getSalary());
            row.setPadding(5, 5, 5, 5);
            table.addView(row);
        }
        closeDatabase();
    }
}
