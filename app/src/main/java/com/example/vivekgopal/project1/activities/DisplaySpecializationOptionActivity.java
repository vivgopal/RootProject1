package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.apache.commons.lang3.text.WordUtils;

public class DisplaySpecializationOptionActivity extends AppCompatActivity {

    String title;
    String subtitle;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Toolbar titleToolbar;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_specialization_with_toolbar);

        final ImageButton skillsButton = (ImageButton) findViewById(R.id.layout_toolbar_with_specialization_skills);
        final ImageButton companiesButton = (ImageButton) findViewById(R.id.layout_toolbar_with_specialization_companies);
        final ImageButton salariesButton = (ImageButton) findViewById(R.id.layout_toolbar_with_specialization_salaries);
        final ImageButton certificationsButton = (ImageButton) findViewById(R.id.layout_toolbar_with_specialization_certifications);
        final ImageButton tipsButton = (ImageButton) findViewById(R.id.layout_toolbar_with_specialization_career_tips);
        final ImageButton ladderButton = (ImageButton) findViewById(R.id.layout_toolbar_with_specialization_career_ladder);

        // Setup toolbars
        TextView titleTextView = (TextView) findViewById(R.id.layout_toolbar_with_specialization_title_textview);
        titleToolbar = (Toolbar) findViewById(R.id.layout_toolbar_with_specialization_title);
        setSupportActionBar(titleToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Setup home button in toolbar
        Drawable drawable= getResources().getDrawable(R.drawable.ic_home);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable newdrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 75, 75, true));
        newdrawable.setAlpha(229);
        //newdrawable.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(newdrawable);

        initButtonStrings();

        titleTextView.setText(WordUtils.capitalize(subtitle));

        skillsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(DisplaySpecializationOptionActivity.this, DisplaySkillsActivity.class);
                createBundle();
                addAnalytics(getResources().getString(R.string.SKILLS));
                startActivity(intent);
            }
        });

        companiesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(DisplaySpecializationOptionActivity.this, DisplayCompaniesActivity.class);
                createBundle();
                addAnalytics(getResources().getString(R.string.COMPANIES));
                startActivity(intent);
            }
        });

        salariesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(DisplaySpecializationOptionActivity.this, DisplaySalariesActivity.class);
                createBundle();
                addAnalytics(getResources().getString(R.string.SALARIES));
                startActivity(intent);
            }
        });

        certificationsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(DisplaySpecializationOptionActivity.this, DisplayCertificationsActivity.class);
                createBundle();
                addAnalytics(getResources().getString(R.string.CERTIFICATIONS));
                startActivity(intent);
            }
        });

        tipsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(DisplaySpecializationOptionActivity.this, DisplayCareerTipsActivity.class);
                createBundle();
                addAnalytics(getResources().getString(R.string.CAREER_TIPS));
                startActivity(intent);
            }
        });

        ladderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(DisplaySpecializationOptionActivity.this, DisplayCareerLadderActivity.class);
                createBundle();
                addAnalytics(getResources().getString(R.string.CAREER_LADDER));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }
        return (super.onOptionsItemSelected(menuItem));
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

    protected void addAnalytics(String specializationOption) {
        // Add Analytics
        Bundle params = new Bundle();
        params.putInt(getResources().getString(R.string.DEGREE_NAME), getResources().getInteger(getResources().getIdentifier(title.toLowerCase().replace(" ", "_").replace("&", "and"), "integer", getPackageName())));
        params.putInt(getResources().getString(R.string.SPECIALIZATION_NAME), getResources().getInteger(getResources().getIdentifier(subtitle.toLowerCase().replace(" ", "_").replace("&", "and"), "integer", getPackageName())));
        params.putInt(getResources().getString(R.string.SPECIALIZATION_OPTION_NAME), getResources().getInteger(getResources().getIdentifier(specializationOption.toLowerCase().replace(" ", "_").replace("&", "and"), "integer", getPackageName())));
        FirebaseAnalytics.getInstance(getApplicationContext()).logEvent(
                getResources().getString(R.string.EVENT_SPECIALIZATION_OPTION_SELECTED), params);

    }
}

