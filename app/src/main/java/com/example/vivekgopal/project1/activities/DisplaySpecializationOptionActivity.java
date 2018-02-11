package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;

import org.apache.commons.lang3.text.WordUtils;

public class DisplaySpecializationOptionActivity extends AppCompatActivity {

    String title;
    String subtitle;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Toolbar titleToolbar;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_toolbar_with_specialization);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        final ImageButton skillsButton = (ImageButton) findViewById(R.id.layout_toolbar_with_specialization_skills);
        final ImageButton companiesButton = (ImageButton) findViewById(R.id.layout_toolbar_with_specialization_companies);
        final ImageButton salariesButton = (ImageButton) findViewById(R.id.layout_toolbar_with_specialization_salaries);
        final ImageButton certificationsButton = (ImageButton) findViewById(R.id.layout_toolbar_with_specialization_certifications);
        final ImageButton tipsButton = (ImageButton) findViewById(R.id.layout_toolbar_with_specialization_career_tips);
        final ImageButton ladderButton = (ImageButton) findViewById(R.id.layout_toolbar_with_specialization_career_ladder);

        TextView titleTextView = (TextView) findViewById(R.id.layout_toolbar_with_specialization_title_textview);
        // Setup toolbars
        titleToolbar = (Toolbar) findViewById(R.id.layout_toolbar_with_specialization_title);
        setSupportActionBar(titleToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initButtonStrings();

        titleTextView.setText(WordUtils.capitalize(subtitle));

        skillsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(DisplaySpecializationOptionActivity.this, DisplaySkillsActivity.class);
                createBundle();
                startActivity(intent);
            }
        });

        companiesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(DisplaySpecializationOptionActivity.this, DisplayCompaniesActivity.class);
                createBundle();
                startActivity(intent);
            }
        });

        salariesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(DisplaySpecializationOptionActivity.this, DisplaySalariesActivity.class);
                createBundle();
                startActivity(intent);
            }
        });

        certificationsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(DisplaySpecializationOptionActivity.this, DisplayCertificationsActivity.class);
                createBundle();
                startActivity(intent);
            }
        });

        tipsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(DisplaySpecializationOptionActivity.this, DisplayCareerTipsActivity.class);
                createBundle();
                startActivity(intent);
            }
        });

        ladderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(DisplaySpecializationOptionActivity.this, DisplayCareerLadderActivity.class);
                createBundle();
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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

