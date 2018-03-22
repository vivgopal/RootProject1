package com.example.vivekgopal.project1.activities;

import android.os.Bundle;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;


import com.example.vivekgopal.project1.adapters.RecyclerViewLadderAdapter;

public class DisplayCareerLadderActivity extends GenericDbActivity {

    List<String> careerLadderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // calls setupView() and initRecyclerView()

        openDatabase();
        careerLadderList = mDbAdapter.getLadder(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle));
        closeDatabase();

        // Set adapter to recyclerview
        recyclerView.setAdapter(new RecyclerViewLadderAdapter(
                title,
                careerLadderList,
                getApplicationContext()
        ));

    }

    @Override
    public void disableClick(){
    }

    @Override
    public void enableClick(){
    }
}
