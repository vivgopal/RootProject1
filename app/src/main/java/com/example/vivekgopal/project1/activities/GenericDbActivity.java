package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.DatabaseAdapter;
import com.example.vivekgopal.project1.data.DataItem;
import com.example.vivekgopal.project1.data.SalaryItem;

import java.util.ArrayList;
import java.util.List;

public class GenericDbActivity extends AppCompatActivity {

    public String title;
    public String subtitle;
    public Button homeButton;
    public TextView titleTextView;
    public TextView subtitleTextView;
    String[] items = {""};
    public DatabaseAdapter mDbAdapter;

    public void setupView() {
        homeButton = (Button) findViewById(R.id.generic_header_home_button);
        titleTextView = (TextView) findViewById(R.id.generic_header_title);
        subtitleTextView = (TextView) findViewById(R.id.generic_header_subtitle);

        initTitleStrings();

        titleTextView.setText(title);
        if(subtitle != "") {
            subtitleTextView.setText(subtitle);
        }

        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void openDatabase() {
        mDbAdapter = new DatabaseAdapter(getApplicationContext());
        mDbAdapter.createDatabase();
        mDbAdapter.open();
    }

    public void closeDatabase() {
        mDbAdapter.close();
    }

    public void initTitleStrings() {
        Bundle bundle = this.getIntent().getExtras();
        this.title = bundle.getString("titleKey");
        this.subtitle = bundle.getString("subtitleKey");
    }

    public void initItems() {
        Bundle bundle = this.getIntent().getExtras();
        this.items = bundle.getStringArray("stringKey");
    }
}
