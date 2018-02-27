package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.DatabaseAdapter;

import org.apache.commons.lang3.text.WordUtils;

public class GenericDbTempActivity extends AppCompatActivity {

    public String title;
    public String subtitle;
    public DatabaseAdapter mDbAdapter;
    Toolbar titleToolbar;
    Toolbar subtitleToolbar;
    RecyclerView recyclerView;

    // This onCreate medhod has to ba always called from the extended class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_toolbar_with_recycleview);
        setupView();
        initRecyclerView();
    }

    //---------------- Toolbar related methods ----------------
    public void setupView() {
        initTitleStrings();

        // Setup toolbars
        titleToolbar = (Toolbar) findViewById(R.id.layout_toolbar_with_recycleview_title);
        subtitleToolbar = (Toolbar) findViewById(R.id.layout_toolbar_with_recycleview_subtitle);
        setSupportActionBar(titleToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Set text to title and subtitle toolbars
        TextView titleTextView = (TextView) findViewById(R.id.layout_toolbar_with_recycleview_title_textview);
        TextView subTitleTextView = (TextView) findViewById(R.id.layout_toolbar_with_recycleview_subtitle_textview);
        titleTextView.setText(title);
        subTitleTextView.setText(subtitle);
    }

    public void initTitleStrings() {
        Bundle bundle = this.getIntent().getExtras();
        this.title = WordUtils.capitalize(bundle.getString("titleKey"));
        this.subtitle = WordUtils.capitalize(bundle.getString("subtitleKey"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //---------------- Database related methods ----------------
    public void openDatabase() {
        mDbAdapter = new DatabaseAdapter(getApplicationContext());
        mDbAdapter.createDatabase();
        mDbAdapter.open();
    }

    public void closeDatabase() {
        mDbAdapter.close();
    }


    //---------------- RecyclerView related methods ----------------
    public void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.layout_toolbar_with_recycleview_container);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
