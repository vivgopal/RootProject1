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

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import android.os.Bundle;
import com.example.vivekgopal.project1.adapters.RecyclerViewCompanyAdapter;
import com.example.vivekgopal.project1.adapters.RecyclerViewLadderAdapter;
import com.example.vivekgopal.project1.data.CompanyItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Collections;
import java.util.List;

public class DisplayCareerLadderActivity extends GenericDbTempActivity {

    List<String> careerLadderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // calls setupView() and initRecyclerView()

        openDatabase();
        careerLadderList = mDbAdapter.getLadder(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle));
        closeDatabase();

        // Set adapter to recyclerview
        recyclerView.setAdapter(new RecyclerViewLadderAdapter(
                title,
                careerLadderList,
                getApplicationContext()
        ));

    }
}
