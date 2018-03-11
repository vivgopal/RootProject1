package com.example.vivekgopal.project1.activities;

import android.os.Bundle;
import com.example.vivekgopal.project1.adapters.RecyclerViewCertificationAdapter;
import com.example.vivekgopal.project1.data.CertificationItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Collections;
import java.util.List;

public class DisplayCertificationsActivity extends GenericDbActivity {

    List<String> certificationList;
    List<CertificationItem> certificationItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // calls setupView() and initRecyclerView()

        openDatabase();
        certificationList = mDbAdapter.getCertifications(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle));
        Collections.sort(certificationList);
        certificationItemList = mDbAdapter.getCertificationItems(certificationList);
        closeDatabase();

        // Set adapter to recyclerview
        recyclerView.setAdapter(new RecyclerViewCertificationAdapter(
                title,
                certificationItemList,
                getApplicationContext()
        ));

    }
}
