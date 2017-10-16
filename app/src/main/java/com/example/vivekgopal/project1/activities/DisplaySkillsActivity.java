package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.data.SkillItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DisplaySkillsActivity extends GenericDbActivity {

    LinearLayout container;
    List<Button> btnList = new ArrayList<>();
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
    List<SkillItem> skillItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_option_select);
        setupView();

        openDatabase();
        skillItemList = mDbAdapter.getSkillItems(mDbAdapter.getSkills(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle)));
        closeDatabase();

        // Sort skills alphabetically
        if (skillItemList.size() > 0) {
            Collections.sort(skillItemList, new Comparator<SkillItem>() {
                @Override
                public int compare(final SkillItem skill1, final SkillItem skill2) {
                    return skill1.getSkill().compareTo(skill2.getSkill());
                }
            });
        }

        float buttonAlpha = (float) 0.90;
        int buttonIntAlpha = (int) (buttonAlpha * 255);

        titleTextView.setText(title);
        if(subtitle != "") {
            subtitleTextView.setText(subtitle + " | Skills");
        }
        container = (LinearLayout) findViewById(R.id.activity_generic_option_select_button_container);
        params.setMargins(25, 40, 25, 0);

        int i = 0;
        for(final SkillItem item:skillItemList) {
            btnList.add(new Button(this));
            btnList.get(i).setText(WordUtils.capitalize(item.getSkill()));
            btnList.get(i).setTextColor(Color.argb(buttonIntAlpha, 255, 255, 255));
            btnList.get(i).setTransformationMethod(null);
            btnList.get(i).setLayoutParams(params);
            btnList.get(i).setElevation(40);
            btnList.get(i).setAlpha(buttonAlpha);
            btnList.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.colorButtonLight));
            container.addView(btnList.get(btnList.size() - 1));

            btnList.get(i).setOnClickListener(new View.OnClickListener() {
                  public void onClick(View v) {
                          Uri uri = Uri.parse(item.getUrl());
                          Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                          startActivity(intent);
                      }
                  }
            );
            i++;
        }

        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}

