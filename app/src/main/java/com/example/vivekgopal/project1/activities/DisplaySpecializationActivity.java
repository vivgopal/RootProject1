package com.example.vivekgopal.project1.activities;

import android.os.Bundle;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.RecyclerViewSpecializationAdapter;

import org.apache.commons.lang3.text.WordUtils;

public class DisplaySpecializationActivity extends GenericDbActivity {

    RecyclerViewSpecializationAdapter recyclerViewSpecializationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // calls setupView() and initRecyclerView()
        context = DisplaySpecializationActivity.this;

        // Set adapter to recyclerview
        // Get Resources
        String stream = WordUtils.uncapitalize(title);
        int id = getResources().getIdentifier("specialization_" + stream.replaceAll(" ", "_"), "array", getPackageName());

        recyclerViewSpecializationAdapter = new RecyclerViewSpecializationAdapter(
                title,
                getResources().getStringArray(id),
                getApplicationContext()
        );

        if(isFirstTime(this.getClass().getSimpleName(), getApplicationContext().MODE_PRIVATE)) {
            setupTutorialView(
                    context,
                    getResources().getString(R.string.click_message_specializations),
                    getResources().getString(R.string.scroll_message_specializations)
            );
        }

        recyclerView.setAdapter(recyclerViewSpecializationAdapter);
    }

    @Override
    public void disableClick(){
        recyclerViewSpecializationAdapter.disableClick();
    }

    @Override
    public void enableClick(){
        recyclerViewSpecializationAdapter.enableClick();
    }
}
