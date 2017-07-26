package com.example.vivekgopal.project1.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;

import java.util.ArrayList;
import java.util.List;

public class CertificationsActivity extends AppCompatActivity {

    LinearLayout container;
    List<LinearLayout> certificationList = new ArrayList<>();
    
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
    String[] items = {""};
    String title;
    String subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifications);
        
        // Initialize button parameters
        final Button homeButton = (Button) findViewById(R.id.generic_header_home_button);
        TextView titleTextView = (TextView) findViewById(R.id.generic_header_title);
        TextView subtitleTextView = (TextView) findViewById(R.id.generic_header_subtitle);

        initButtonStrings();

        titleTextView.setText(title);
        if(subtitle != "") {
            subtitleTextView.setText(subtitle);
        }

        container = (LinearLayout) findViewById(R.id.activity_certifications_container);
        LayoutInflater inflater =  LayoutInflater.from(this.getApplicationContext());

        params.setMargins(5, 5, 5, 5);

        for(int i=0; i<items.length; i++) {
            //certificationList.add(new LinearLayout(this));
            //certificationList.get(i).findViewById(R.id.activity_certifications);
            View certificationLayout = inflater.inflate(R.layout.layout_certification, null, false);
            certificationLayout.setLayoutParams(params);
            container.addView(certificationLayout);
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

