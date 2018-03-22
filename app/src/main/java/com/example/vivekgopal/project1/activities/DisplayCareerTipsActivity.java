package com.example.vivekgopal.project1.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.RecyclerViewTipsAdapter;
import com.example.vivekgopal.project1.preferences.LockableLinearLayoutManager;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

public class DisplayCareerTipsActivity extends GenericDbActivity {

    List<String> tipsList;
    RecyclerViewTipsAdapter recyclerViewTipsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // calls setupView() and initRecyclerView()
        context = DisplayCareerTipsActivity.this;

        openDatabase();
        tipsList = mDbAdapter.getTips(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle));
        closeDatabase();

        recyclerViewTipsAdapter = new RecyclerViewTipsAdapter(
                title,
                subtitle,
                tipsList,
                getApplicationContext());

        if(isFirstTime(this.getClass().getSimpleName(), getApplicationContext().MODE_PRIVATE)) {
            setupTutorialView(
                    context,
                    getResources().getString(R.string.click_message_tips),
                    getResources().getString(R.string.scroll_message_tips)
            );
        }

        // Set adapter to recyclerview
        recyclerView.setAdapter(recyclerViewTipsAdapter);

    }

    @Override
    public void disableClick(){
        recyclerViewTipsAdapter.disableClick();
    }

    @Override
    public void enableClick(){
        recyclerViewTipsAdapter.enableClick();
    }
}