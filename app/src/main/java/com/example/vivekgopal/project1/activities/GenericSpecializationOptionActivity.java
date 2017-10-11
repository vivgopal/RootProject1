package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;

public class GenericSpecializationOptionActivity extends AppCompatActivity {

    String title;
    String subtitle;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialization_option_select);

        final Button homeButton = (Button) findViewById(R.id.generic_header_home_button);
        final Button skillsButton = (Button) findViewById(R.id.button_specialization_option_select_skills);
        final Button companiesButton = (Button) findViewById(R.id.button_specialization_option_select_companies);
        final Button salariesButton = (Button) findViewById(R.id.button_specialization_option_select_salaries);
        final Button certificationsButton = (Button) findViewById(R.id.button_specialization_option_select_certifications);
        final Button tipsButton = (Button) findViewById(R.id.button_specialization_option_select_tips);
        final Button ladderButton = (Button) findViewById(R.id.button_specialization_option_select_ladder);

        TextView titleTextView = (TextView) findViewById(R.id.generic_header_title);
        TextView subtitleTextView = (TextView) findViewById(R.id.activity_specialization_option_select_round_button);

        initButtonStrings();

        titleTextView.setText(title);
        if(subtitle != "") {
            subtitleTextView.setText(subtitle);
        }

        skillsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(GenericSpecializationOptionActivity.this, DisplaySkillsActivity.class);
                createBundle();
                startActivity(intent);
            }
        });

        companiesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(GenericSpecializationOptionActivity.this, DisplayCompaniesActivity.class);
                createBundle();
                startActivity(intent);
            }
        });

        salariesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(GenericSpecializationOptionActivity.this, DisplaySalariesActivity.class);
                createBundle();
                startActivity(intent);
            }
        });

        certificationsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(GenericSpecializationOptionActivity.this, DisplayCertificationsActivity.class);
                createBundle();
                startActivity(intent);
            }
        });

        tipsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(GenericSpecializationOptionActivity.this, DisplayCareerTipsActivity.class);
                createBundle();
                startActivity(intent);
            }
        });

        ladderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(GenericSpecializationOptionActivity.this, DisplayCareerLadderActivity.class);
                createBundle();
                startActivity(intent);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void initButtonStrings() {
        Bundle bundle = this.getIntent().getExtras();
        this.title = bundle.getString("titleKey");
        this.subtitle = bundle.getString("subtitleKey");
    }

    protected void createBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("titleKey", title);
        bundle.putString("subtitleKey", subtitle);
        intent.putExtras(bundle);
    }
}

