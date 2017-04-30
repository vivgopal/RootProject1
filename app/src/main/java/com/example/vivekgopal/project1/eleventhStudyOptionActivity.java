package com.example.vivekgopal.project1;

import android.graphics.Color;
import android.graphics.Outline;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class eleventhStudyOptionActivity extends AppCompatActivity {

    LinearLayout container;
    List<Button> btnList = new ArrayList<>();
    List<String> items = new ArrayList<>();
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleventh_study_option);

        container = (LinearLayout) findViewById(R.id.activity_eleventh_study_option_container);
        params.setMargins(25, 40, 25, 0);

        items.add("Diploma");
        items.add("BSc");
        items.add("BTech");

        for(int i=0; i<12; i++) {
            btnList.add(new Button(this));
            btnList.get(i).setText(items.get(0));
            btnList.get(i).setTransformationMethod(null);
            btnList.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.colorButtonLight));
            btnList.get(i).setLayoutParams(params);
            btnList.get(i).setElevation(40);
            container.addView(btnList.get(btnList.size() - 1));
        }
    }
}
