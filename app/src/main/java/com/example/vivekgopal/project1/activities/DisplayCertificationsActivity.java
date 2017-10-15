package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.CertificationListAdapter;
import com.example.vivekgopal.project1.data.CertificationItem;
import com.example.vivekgopal.project1.data.SkillItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DisplayCertificationsActivity extends GenericDbActivity {

    ListView list;
    String[] certificationTitle;
    Integer[] certificationLogoId;
    String[] certificationUrl;
    List<String> certificationList;
    List<CertificationItem> certificationItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifications);
        setupView();
        subtitleTextView.setText(subtitle + " | Certifications");

        openDatabase();
        certificationList = mDbAdapter.getCertifications(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle));
        Collections.sort(certificationList);
        certificationItemList = mDbAdapter.getCertificationItems(certificationList);
        closeDatabase();

        certificationTitle = new String[certificationItemList.size()];
        certificationLogoId = new Integer[certificationItemList.size()];
        certificationUrl = new String[certificationItemList.size()];

        int i = 0;
        for(CertificationItem item : certificationItemList){
            certificationTitle[i] = item.getName();
            certificationUrl[i] = item.getUrl();
            certificationLogoId[i] = getResources().getIdentifier("logo_" + item.getSource().toLowerCase(), "drawable", getPackageName());
            i++;
        }
        CertificationListAdapter adapter = new CertificationListAdapter(DisplayCertificationsActivity.this, certificationTitle, certificationLogoId);
        list=(ListView)findViewById(R.id.activity_certifications_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
                Uri uri = Uri.parse(certificationUrl[position]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}

