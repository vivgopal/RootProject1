package com.example.vivekgopal.project1.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.RecyclerViewTypeOneAdapter;

import org.apache.commons.lang3.text.WordUtils;

public class DisplaySpecializationActivity extends AppCompatActivity {

    public String title;
    public String subtitle;

    Toolbar titleToolbar;
    Toolbar subtitleToolbar;
    RecyclerView specialization_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_generic_header_with_recycleview);

        // Setup toolbars
        titleToolbar = (Toolbar) findViewById(R.id.layout_salary_table_row_toolbar_title);
        subtitleToolbar = (Toolbar) findViewById(R.id.layout_salary_table_row_toolbar_subtitle);
        setSupportActionBar(titleToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initTitleStrings();

        // Set text to title and subtitle toolbars
        TextView titleTextView = (TextView) findViewById(R.id.layout_salary_table_row_toolbar_title_textview);
        TextView subTitleTextView = (TextView) findViewById(R.id.layout_salary_table_row_toolbar_subtitle_textview);
        titleTextView.setText(title);
        subTitleTextView.setText(subtitle);

        // Set adapter to recyclerview
        // Get Resources
        String stream = WordUtils.uncapitalize(title);
        int id = getResources().getIdentifier("specialization_" + stream.replaceAll(" ", "_"), "array", getPackageName());
        specialization_list = (RecyclerView) findViewById(R.id.layout_salary_table_row_recycler_view);
        specialization_list.setLayoutManager(new LinearLayoutManager(this));
        specialization_list.setAdapter(new RecyclerViewTypeOneAdapter(
                getResources().getStringArray(id),
                getApplicationContext()
        ));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void initTitleStrings() {
        Bundle bundle = this.getIntent().getExtras();
        this.title = bundle.getString("titleKey");
        this.subtitle = bundle.getString("subtitleKey");
    }

}
