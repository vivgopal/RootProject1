package com.example.vivekgopal.project1.activities;


import android.os.Bundle;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.RecyclerViewSkillAdapter;
import com.example.vivekgopal.project1.data.SkillItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Collections;
import java.util.List;

public class DisplaySkillsActivity extends GenericDbActivity {

    List<String> skillList;
    List<SkillItem> skillItemList;
    RecyclerViewSkillAdapter recyclerViewSkillAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // calls setupView() and initRecyclerView()
        context = DisplaySkillsActivity.this;

        openDatabase();
        skillList = mDbAdapter.getSkills(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle));
        Collections.sort(skillList);
        skillItemList = mDbAdapter.getSkillItems(skillList);
        closeDatabase();

        recyclerViewSkillAdapter = new RecyclerViewSkillAdapter(
                title,
                subtitle,
                skillItemList,
                getApplicationContext(),
                this
        );

        if(isFirstTime(this.getClass().getSimpleName(), getApplicationContext().MODE_PRIVATE)) {
            setupTutorialView(
                    context,
                    getResources().getString(R.string.click_message_skills),
                    getResources().getString(R.string.scroll_message_skills)
            );
        }

        // Set adapter to recyclerview
        recyclerView.setAdapter(recyclerViewSkillAdapter);

    }

    @Override
    public void disableClick(){
        recyclerViewSkillAdapter.disableClick();
    }

    @Override
    public void enableClick(){
        recyclerViewSkillAdapter.enableClick();
    }
}
