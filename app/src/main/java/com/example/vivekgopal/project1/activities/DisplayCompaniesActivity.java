package com.example.vivekgopal.project1.activities;

import android.os.Bundle;
import com.example.vivekgopal.project1.adapters.RecyclerViewCompanyAdapter;
import com.example.vivekgopal.project1.data.CompanyItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Collections;
import java.util.List;

public class DisplayCompaniesActivity extends GenericDbTempActivity {

    List<String> companyList;
    List<CompanyItem> companyItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // calls setupView() and initRecyclerView()

        openDatabase();
        companyList = mDbAdapter.getCompanies(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle));
        Collections.sort(companyList);
        companyItemList = mDbAdapter.getCompanyItems(companyList);
        closeDatabase();

        // Set adapter to recyclerview
        recyclerView.setAdapter(new RecyclerViewCompanyAdapter(
                title,
                companyItemList,
                getApplicationContext()
        ));

    }
}
