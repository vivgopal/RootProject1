package com.example.vivekgopal.project1.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.DatabaseAdapter;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class DisplayCareerTipActivity extends AppCompatActivity {

    List<String> tips;
    String[] currentTip;
    String title;
    String subtitle;
    public DatabaseAdapter mDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_toolbar_with_tip);

        // Setup toolbars
        Toolbar titleToolbar;
        TextView titleTextView = (TextView) findViewById(R.id.layout_toolbar_with_tip_title_textview);
        titleToolbar = (Toolbar) findViewById(R.id.layout_toolbar_with_tip_title);
        setSupportActionBar(titleToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Setup home button in toolbar
        Drawable drawable= getResources().getDrawable(R.drawable.ic_home_white);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable newdrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 75, 75, true));
        newdrawable.setAlpha(229);
        //newdrawable.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(newdrawable);

        initButtonStrings();
        titleTextView.setText(WordUtils.capitalize(subtitle));

        // Initializa textviews in the tip activity
        TextView tipNumberTextView = (TextView) findViewById(R.id.layout_toolbar_with_tip_number);
        TextView tipContentTextView = (TextView) findViewById(R.id.layout_toolbar_with_tip_content);
        TextView tipNameTextView = (TextView) findViewById(R.id.layout_toolbar_with_tip_name);
        Bundle bundle = this.getIntent().getExtras();
        int tipNum = bundle.getInt("tipIdKey");
        int imageFileId = getResources().getIdentifier("icon_calandar" + Integer.toString((tipNum%5)+1), "drawable", getPackageName());
        int colorId = getResources().getIdentifier("colorTip" + Integer.toString((tipNum%5)+1), "color", getPackageName());

        // Get tips from the database
        openDatabase();
        tips = mDbAdapter.getTips(WordUtils.uncapitalize(title), WordUtils.uncapitalize(subtitle));
        closeDatabase();


        // Set text to the text views
        currentTip = tips.get(tipNum).split("\n", 2); // first line contains the tip name and other lines contain the description
        tipNameTextView.setText(currentTip[0]);

        currentTip = currentTip[1].split("\n", 2); // first line is a new line and reuse variable to remove the new line
        tipContentTextView.setText(currentTip[1]);

        tipNumberTextView.setText("#"+Integer.toString(tipNum+1));
        tipNumberTextView.setBackgroundResource(imageFileId);
        tipNumberTextView.setTextColor(getResources().getColor(colorId));
        //tipNameTextView.setText("Title");
        //tipContentTextView.setText("Content Content Content Content Content Content Content ");
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

    //---------------- Database related methods ----------------
    public void openDatabase() {
        mDbAdapter = new DatabaseAdapter(getApplicationContext());
        mDbAdapter.createDatabase();
        mDbAdapter.open();
    }

    public void closeDatabase() {
        mDbAdapter.close();
    }


}

