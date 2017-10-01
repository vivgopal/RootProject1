package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;

import java.util.ArrayList;
import java.util.List;

public class GenericOptionSelectActivity extends AppCompatActivity {

    LinearLayout container;
    List<Button> btnList = new ArrayList<>();
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
    String[] items = {""};
    String title;
    String subtitle;
    String activityType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_option_select);


        // Initialize button parameters
        final Button homeButton = (Button) findViewById(R.id.generic_header_home_button);
        TextView titleTextView = (TextView) findViewById(R.id.generic_header_title);
        TextView subtitleTextView = (TextView) findViewById(R.id.generic_header_subtitle);

        float buttonAlpha = (float) 0.90;
        int buttonIntAlpha = (int) (buttonAlpha * 255);
        initButtonStrings();

        titleTextView.setText(title);
        if(subtitle != "") {
            subtitleTextView.setText(subtitle);
        }
        container = (LinearLayout) findViewById(R.id.activity_generic_option_select_button_container);
        params.setMargins(25, 40, 25, 0);

        for(int i=0; i<items.length; i++) {
            btnList.add(new Button(this));
            btnList.get(i).setText(items[i]);
            btnList.get(i).setTextColor(Color.argb(buttonIntAlpha, 255, 255, 255));
            btnList.get(i).setTransformationMethod(null);
            if(activityType.equals("tips")) {
                btnList.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.colorButtonDark));
                btnList.get(i).setLines(2);
                btnList.get(i).setPadding(5,5,5,5);
                btnList.get(i).setEllipsize(TextUtils.TruncateAt.END);
            } else {
                btnList.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.colorButtonLight));
            }
            btnList.get(i).setLayoutParams(params);
            btnList.get(i).setElevation(40);
            btnList.get(i).setAlpha(buttonAlpha);
            container.addView(btnList.get(btnList.size() - 1));
        }

        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    protected void initButtonStrings() {
        Bundle bundle = this.getIntent().getExtras();
        this.items = bundle.getStringArray("stringKey");
        this.title = bundle.getString("titleKey");
        this.subtitle = bundle.getString("subtitleKey");
        this.activityType = bundle.getString("activityTypeKey");

        if(activityType.equals("tips")) {
            for (int i = 0; i < items.length; i++) {
                items[i] = "Tip#" + i + "\n" + items[i];
            }
        }
    }


}
