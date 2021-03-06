package com.example.vivekgopal.project1.activities;

import android.os.Bundle;
import com.example.vivekgopal.project1.adapters.RecyclerViewSalaryAdapter;
import com.example.vivekgopal.project1.data.CompanyItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Collections;
import java.util.List;

public class DisplaySalariesActivity extends GenericDbActivity {

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

        for(int i=companyItemList.size()-1; i>=0; i--) {
            if(companyItemList.get(i).getSalary() == 0) {
                companyItemList.remove(i);
            }
        }

        Collections.sort(companyItemList);

        // Set adapter to recyclerview
        recyclerView.setAdapter(new RecyclerViewSalaryAdapter(
                title,
                subtitle,
                companyItemList,
                getApplicationContext(),
                this
        ));

    }

    @Override
    public void disableClick(){
    }

    @Override
    public void enableClick(){
    }
}
