package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.vivekgopal.project1.R;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DisplayCompaniesActivity extends GenericDbActivity {

    LinearLayout container;
    List<Button> btnList = new ArrayList<>();
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_option_select);
        setupView();

        openDatabase();
        items = mDbAdapter.getCompanies(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle)).toArray(items);
        Arrays.sort(items);
        closeDatabase();

        float buttonAlpha = (float) 0.90;
        int buttonIntAlpha = (int) (buttonAlpha * 255);

        subtitleTextView.setText(subtitle + " | Companies");

        container = (LinearLayout) findViewById(R.id.activity_generic_option_select_button_container);
        params.setMargins(25, 40, 25, 0);

        for(int i=0; i<items.length; i++) {
            btnList.add(new Button(this));
            btnList.get(i).setText(WordUtils.capitalize(items[i]));
            btnList.get(i).setTextColor(Color.argb(buttonIntAlpha, 255, 255, 255));
            btnList.get(i).setTransformationMethod(null);
            btnList.get(i).setLayoutParams(params);
            btnList.get(i).setElevation(40);
            btnList.get(i).setAlpha(buttonAlpha);
            btnList.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.colorButtonLight));
            container.addView(btnList.get(btnList.size() - 1));
        }
    }

}

