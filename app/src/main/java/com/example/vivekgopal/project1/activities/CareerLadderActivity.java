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

import java.util.ArrayList;
import java.util.List;

public class CareerLadderActivity extends AppCompatActivity {

    LinearLayout container;
    List<TextView> positionList = new ArrayList<>();
    List<TextView> arrowList = new ArrayList<>();
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
    String[] items = {""};
    String title;
    String subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_ladder);

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
        container = (LinearLayout) findViewById(R.id.activity_career_ladder_button_container);
        params.setMargins(25, 40, 25, 0);

        for(int i=0; i<items.length; i++) {
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
    }


}

