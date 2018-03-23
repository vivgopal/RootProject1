package com.example.vivekgopal.project1.activities;

import android.os.Bundle;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.RecyclerViewCertificationAdapter;
import com.example.vivekgopal.project1.data.CertificationItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Collections;
import java.util.List;

public class DisplayCertificationsActivity extends GenericDbActivity {

    List<String> certificationList;
    List<CertificationItem> certificationItemList;
    RecyclerViewCertificationAdapter recyclerViewCertificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // calls setupView() and initRecyclerView()
        context = DisplayCertificationsActivity.this;

        openDatabase();
        certificationList = mDbAdapter.getCertifications(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle));
        Collections.sort(certificationList);
        certificationItemList = mDbAdapter.getCertificationItems(certificationList);
        closeDatabase();

        recyclerViewCertificationAdapter = new RecyclerViewCertificationAdapter(
                title,
                subtitle,
                certificationItemList,
                getApplicationContext()
        );

        if(isFirstTime(this.getClass().getSimpleName(), getApplicationContext().MODE_PRIVATE)) {
            setupTutorialView(
                    context,
                    getResources().getString(R.string.click_message_certifications),
                    getResources().getString(R.string.scroll_message_certifications)
            );
        }

        // Set adapter to recyclerview
        recyclerView.setAdapter(recyclerViewCertificationAdapter);

    }

    @Override
    public void disableClick(){
        recyclerViewCertificationAdapter.disableClick();
    }

    @Override
    public void enableClick(){
        recyclerViewCertificationAdapter.enableClick();
    }
}
