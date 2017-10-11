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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.CertificationListAdapter;
import com.example.vivekgopal.project1.data.CertificationItem;
import com.example.vivekgopal.project1.data.SalaryItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayCertificationsActivity extends GenericDbActivity {

    ListView list;
    String[] certificationTitle;
    Integer[] certificationLogoId;
    List<CertificationItem> certificationItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifications);
        setupView();
        subtitleTextView.setText(subtitle + " | Certifications");

        openDatabase();
        certificationItemList = mDbAdapter.getCertifications(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle));
        closeDatabase();

        certificationTitle = new String[certificationItemList.size()];
        certificationLogoId = new Integer[certificationItemList.size()];
        int i = 0;
        for(CertificationItem item : certificationItemList){
            certificationTitle[i] = item.getName();
            certificationLogoId[i] = getResources().getIdentifier("logo_" + WordUtils.uncapitalize(item.getSource()), "drawable", getPackageName());
            i++;
        }
        CertificationListAdapter adapter = new CertificationListAdapter(DisplayCertificationsActivity.this, certificationTitle, certificationLogoId);
        list=(ListView)findViewById(R.id.activity_certifications_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DisplayCertificationsActivity.this, "You Clicked at " +certificationTitle[+ position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}

