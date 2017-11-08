package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayCareerTipsActivity extends GenericDbActivity {

    LinearLayout container;
    List<Button> btnList = new ArrayList<>();
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_option_select);
        setupView();

        openDatabase();
        items = mDbAdapter.getTips(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle)).toArray(items);
        closeDatabase();

        subtitleTextView.setText(subtitle + " | Career Tips");
        float buttonAlpha = (float) 0.90;
        int buttonIntAlpha = (int) (buttonAlpha * 255);

        container = (LinearLayout) findViewById(R.id.activity_generic_option_select_button_container);
        params.setMargins(25, 40, 25, 0);

        for(int i=0; i<items.length; i++) {
            int tip_num = i+1;
            items[i] = "Tip#" + tip_num + "\n" + items[i];
            final String[] currentTip = {items[i]};
            final int idx = i;
            btnList.add(new Button(this));
            btnList.get(i).setText(items[i]);
            btnList.get(i).setTextColor(Color.argb(buttonIntAlpha, 255, 255, 255));
            btnList.get(i).setTransformationMethod(null);
            btnList.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.colorButtonDark));
            btnList.get(i).setLines(2);
            btnList.get(i).setPadding(5,5,5,5);
            btnList.get(i).setEllipsize(TextUtils.TruncateAt.END);
            btnList.get(i).setLayoutParams(params);
            btnList.get(i).setElevation(40);
            btnList.get(i).setAlpha(buttonAlpha);
            container.addView(btnList.get(btnList.size() - 1));

            btnList.get(i).setOnClickListener(new View.OnClickListener() {
                  public void onClick(View v) {
                      Bundle bundle = new Bundle();
                      Intent intent = new Intent(DisplayCareerTipsActivity.this, DisplayCareerTipActivity.class);
                      bundle.putString("titleKey", title);
                      bundle.putString("subtitleKey", WordUtils.capitalize(subtitle));
                      bundle.putInt("tipIdKey", idx);
                      bundle.putStringArray("stringKey", currentTip);
                      intent.putExtras(bundle);
                      startActivity(intent);
                  }
              }
            );
        }
    }
}

