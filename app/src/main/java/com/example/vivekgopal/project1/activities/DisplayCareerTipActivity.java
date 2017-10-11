package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.vivekgopal.project1.R;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class DisplayCareerTipActivity extends GenericDbActivity {

    LinearLayout container;
    List<Button> btnList = new ArrayList<>();
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_option_select);
        setupView();

        openDatabase();
        items = mDbAdapter.getTips(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle)).toArray(items);
        closeDatabase();

        subtitleTextView.setText(subtitle + " | Career Tips");
        float buttonAlpha = (float) 0.90;
        int buttonIntAlpha = (int) (buttonAlpha * 255);

        container = (LinearLayout) findViewById(R.id.activity_generic_option_select_button_container);
        params.setMargins(25, 40, 25, 0);

        Bundle bundle = this.getIntent().getExtras();
        int i = bundle.getInt("tipIdKey");
        items[i] = "Tip#" + i + "\n\n" + items[i];
        btnList.add(new Button(this));
        btnList.get(0).setText(items[i]);
        btnList.get(0).setTextColor(Color.argb(buttonIntAlpha, 255, 255, 255));
        btnList.get(0).setTransformationMethod(null);
        btnList.get(0).setBackgroundColor(ContextCompat.getColor(this, R.color.colorButtonDark));
        btnList.get(0).setPadding(15,15,15,15);
        btnList.get(0).setLayoutParams(params);
        btnList.get(0).setElevation(40);
        btnList.get(0).setAlpha(buttonAlpha);
        container.addView(btnList.get(btnList.size() - 1));
    }
}

