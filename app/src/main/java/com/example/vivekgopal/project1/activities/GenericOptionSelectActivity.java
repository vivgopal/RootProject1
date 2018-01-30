package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;

public class GenericOptionSelectActivity extends AppCompatActivity {

    LinearLayout container;
    List<Button> btnList = new ArrayList<>();
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
    String[] items = {""};
    String title;
    String subtitle;

    private View decorView;
    private int display_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        decorView = this.getWindow().getDecorView();
        display_mode = getResources().getConfiguration().orientation;

        if(display_mode == Configuration.ORIENTATION_LANDSCAPE) {
            decorView.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    //| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    //| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

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
            final int idx = i;
            btnList.add(new Button(this));
            btnList.get(i).setText(WordUtils.capitalize(items[i]));
            btnList.get(i).setTextColor(Color.argb(buttonIntAlpha, 255, 255, 255));
            btnList.get(i).setTransformationMethod(null);
            btnList.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.colorButtonLight));
            btnList.get(i).setLayoutParams(params);
            btnList.get(i).setElevation(40);
            btnList.get(i).setAlpha(buttonAlpha);
            container.addView(btnList.get(btnList.size() - 1));

            btnList.get(i).setOnClickListener(new View.OnClickListener() {
                  public void onClick(View v) {
                      Bundle bundle = new Bundle();
                      Intent intent = new Intent(GenericOptionSelectActivity.this, GenericSpecializationOptionActivity.class);
                      bundle.putString("titleKey", title);
                      bundle.putString("subtitleKey", WordUtils.capitalize(items[idx]));
                      intent.putExtras(bundle);
                      startActivity(intent);
                  }
              }
            );
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

