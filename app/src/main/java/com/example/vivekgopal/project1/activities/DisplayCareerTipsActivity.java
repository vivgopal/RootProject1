package com.example.vivekgopal.project1.activities;

import android.os.Bundle;

import com.example.vivekgopal.project1.adapters.RecyclerViewSkillAdapter;
import com.example.vivekgopal.project1.adapters.RecyclerViewTipsAdapter;
import com.example.vivekgopal.project1.data.SkillItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Collections;
import java.util.List;

public class DisplayCareerTipsActivity extends GenericDbTempActivity {

    List<String> tipsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // calls setupView() and initRecyclerView()

        openDatabase();
        tipsList = mDbAdapter.getTips(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle));
        closeDatabase();

        // Set adapter to recyclerview
        recyclerView.setAdapter(new RecyclerViewTipsAdapter(
                title,
                subtitle,
                tipsList,
                getApplicationContext()
        ));

    }
}