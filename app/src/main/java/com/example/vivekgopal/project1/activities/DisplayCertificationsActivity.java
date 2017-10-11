package com.example.vivekgopal.project1.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.data.SalaryItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayCertificationsActivity extends GenericDbActivity {

    LinearLayout container;
    List<LinearLayout> certificationList = new ArrayList<>();
    
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
    String[] items = {""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifications);
        setupView();
        subtitleTextView.setText(subtitle + " | Certifications");

        openDatabase();
        items = mDbAdapter.getCompanies(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle)).toArray(items);
        Arrays.sort(items);
        closeDatabase();

        container = (LinearLayout) findViewById(R.id.activity_certifications_container);
        LayoutInflater inflater =  LayoutInflater.from(this.getApplicationContext());

        params.setMargins(5, 5, 5, 5);

        for(int i=0; i<items.length; i++) {
            View certificationLayout = inflater.inflate(R.layout.layout_certification, null, false);
            certificationLayout.setLayoutParams(params);
            container.addView(certificationLayout);
        }
//        for (String item : items) {
//            View certificationLayout = inflater.inflate(R.layout.layout_certification, null, false);
//            ((View) certificationLayout.findViewById(R.id.certification_title))setText(item.getCompany());
//            ((View) certificationLayout.findViewById(R.id.row_value)).setText("₹" + item.getSalary());
//            row.setPadding(5, 5, 5, 5);
//            table.addView(row);
//        }
    }
}

