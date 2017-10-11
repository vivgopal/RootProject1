package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayCareerLadderActivity extends GenericDbActivity {

    LinearLayout container;
    List<TextView> positionList = new ArrayList<>();
    List<TextView> arrowList = new ArrayList<>();
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_ladder);
        setupView();

        openDatabase();
        items = mDbAdapter.getLadder(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle)).toArray(items);
        closeDatabase();

        float buttonAlpha = (float) 0.90;
        int buttonIntAlpha = (int) (buttonAlpha * 255);

        subtitleTextView.setText(subtitle + " | Career Ladder");

        container = (LinearLayout) findViewById(R.id.activity_career_ladder_button_container);
        params.setMargins(25, 40, 25, 0);

        for(int i=0; i<items.length; i++) {
            items[i] = WordUtils.capitalize(items[i]);
            positionList.add(new TextView(this));
            if(i == 0) {
                positionList.get(i).setText("\n" + items[i]);
            } else {
                positionList.get(i).setText(items[i]);
            }
            positionList.get(i).setTextColor(Color.argb(buttonIntAlpha, 255, 255, 255));
            positionList.get(i).setGravity(Gravity.CENTER);
            container.addView(positionList.get(positionList.size() - 1));

            if(i != items.length - 1) {
                arrowList.add(new TextView(this));
                arrowList.get(i).setText("↓");
                arrowList.get(i).setTextColor(Color.BLACK);
                arrowList.get(i).setGravity(Gravity.CENTER);
                container.addView(arrowList.get(arrowList.size() - 1));
            }
        }
    }
}

