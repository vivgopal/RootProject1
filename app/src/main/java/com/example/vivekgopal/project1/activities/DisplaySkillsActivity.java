package com.example.vivekgopal.project1.activities;


import android.os.Bundle;
import com.example.vivekgopal.project1.adapters.RecyclerViewSalaryAdapter;
import com.example.vivekgopal.project1.adapters.RecyclerViewSkillAdapter;
import com.example.vivekgopal.project1.data.CompanyItem;
import com.example.vivekgopal.project1.data.SkillItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Collections;
import java.util.List;

public class DisplaySkillsActivity extends GenericDbTempActivity {

    List<String> skillList;
    List<SkillItem> skillItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // calls setupView() and initRecyclerView()

        openDatabase();
        skillList = mDbAdapter.getSkills(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle));
        Collections.sort(skillList);
        skillItemList = mDbAdapter.getSkillItems(skillList);
        closeDatabase();

        // Set adapter to recyclerview
        recyclerView.setAdapter(new RecyclerViewSkillAdapter(
                title,
                skillItemList,
                getApplicationContext()
        ));

    }
}
