package com.example.vivekgopal.project1.activities;

import android.os.Bundle;

import com.example.vivekgopal.project1.adapters.RecyclerViewSpecializationAdapter;

import org.apache.commons.lang3.text.WordUtils;

public class DisplaySpecializationActivity extends GenericDbActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // calls setupView() and initRecyclerView()

        // Set adapter to recyclerview
        // Get Resources
        String stream = WordUtils.uncapitalize(title);
        int id = getResources().getIdentifier("specialization_" + stream.replaceAll(" ", "_"), "array", getPackageName());

        recyclerView.setAdapter(new RecyclerViewSpecializationAdapter(
                title,
                getResources().getStringArray(id),
                getApplicationContext()
        ));
    }
}
