package com.example.vivekgopal.project1.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.RecyclerViewCompanyAdapter;
import com.example.vivekgopal.project1.data.CompanyItem;
import com.example.vivekgopal.project1.preferences.LockableLinearLayoutManager;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Collections;
import java.util.List;

public class DisplayCompaniesActivity extends GenericDbActivity {

    List<String> companyList;
    List<CompanyItem> companyItemList;
    RecyclerViewCompanyAdapter recyclerViewCompanyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // calls setupView() and initRecyclerView()
        context = DisplayCompaniesActivity.this;

        openDatabase();
        companyList = mDbAdapter.getCompanies(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle));
        Collections.sort(companyList);
        companyItemList = mDbAdapter.getCompanyItems(companyList);
        closeDatabase();

        recyclerViewCompanyAdapter = new RecyclerViewCompanyAdapter(
                title,
                companyItemList,
                getApplicationContext()
        );

        if(isFirstTime(this.getClass().getSimpleName(), getApplicationContext().MODE_PRIVATE)) {
            setupTutorialView(
                    context,
                    getResources().getString(R.string.click_message_companies),
                    getResources().getString(R.string.scroll_message_companies)
            );
        }

        // Set adapter to recyclerview
        recyclerView.setAdapter(recyclerViewCompanyAdapter);
    }

    @Override
    public void disableClick(){
        recyclerViewCompanyAdapter.disableClick();
    }

    @Override
    public void enableClick(){
        recyclerViewCompanyAdapter.enableClick();
    }
}
