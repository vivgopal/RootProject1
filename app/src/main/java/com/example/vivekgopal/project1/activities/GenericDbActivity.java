package com.example.vivekgopal.project1.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.DatabaseAdapter;
import com.example.vivekgopal.project1.adapters.RecyclerViewCompanyAdapter;
import com.example.vivekgopal.project1.preferences.LockableLinearLayoutManager;

import org.apache.commons.lang3.text.WordUtils;

public abstract class GenericDbActivity extends AppCompatActivity {

    public String title;
    public String subtitle;
    public DatabaseAdapter mDbAdapter;
    Toolbar titleToolbar;
    Toolbar subtitleToolbar;
    RecyclerView recyclerView;

    // User tutorial related variables
    Context context;
    Boolean firstTime = null;
    LinearLayout tutorialContainer;
    LinearLayout tutorialLayout;
    TextView clickMessageTextView;
    TextView scrollMessageTextView;
    LockableLinearLayoutManager lockableLinearLayoutManager;

    // This onCreate medhod has to ba always called from the extended class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_toolbar_with_recycleview);
        setupView();
        initRecyclerView();
    }

    //---------------------------------------------------------
    //---------------- Toolbar related methods ----------------
    //---------------------------------------------------------

    public void setupView() {
        initTitleStrings();
        // Setup toolbars
        titleToolbar = (Toolbar) findViewById(R.id.layout_toolbar_with_recycleview_title);
        //subtitleToolbar = (Toolbar) findViewById(R.id.layout_toolbar_with_recycleview_subtitle);
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

        // Set text to title and subtitle toolbars
        TextView titleTextView = (TextView) findViewById(R.id.layout_toolbar_with_recycleview_title_textview);
        //TextView subTitleTextView = (TextView) findViewById(R.id.layout_toolbar_with_recycleview_subtitle_textview);
        titleTextView.setText(subtitle);
        //subTitleTextView.setText(subtitle);
    }

    public void initTitleStrings() {
        Bundle bundle = this.getIntent().getExtras();
        this.title = WordUtils.capitalize(bundle.getString("titleKey"));
        this.subtitle = WordUtils.capitalize(bundle.getString("subtitleKey"));
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

    //-----------------------------------------------------------
    //---------------- Database related methods ----------------
    //-----------------------------------------------------------

    public void openDatabase() {
        mDbAdapter = new DatabaseAdapter(getApplicationContext());
        mDbAdapter.createDatabase();
        mDbAdapter.open();
    }

    public void closeDatabase() {
        mDbAdapter.close();
    }


    //--------------------------------------------------------------
    //---------------- RecyclerView related methods ----------------
    //--------------------------------------------------------------

    public void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.layout_toolbar_with_recycleview_container);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lockableLinearLayoutManager = new LockableLinearLayoutManager(this);
        recyclerView.setLayoutManager(lockableLinearLayoutManager);
    }

    public void setupTutorialView (Context context, String clickMessage, String scrollMessage) {
        // lock screen orientation, click and scroll when its the first time the user enters
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        lockableLinearLayoutManager.setScrollEnabled(false);
        disableClick();

        // inflate tutorial layout and and add to the container defined in parent layout
        tutorialContainer = (LinearLayout) findViewById(R.id.layout_toolbar_with_recycleview_tutorials_container);
        tutorialLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_toolbar_with_recycleview_tutorial, tutorialContainer, false);

        clickMessageTextView = (TextView) tutorialLayout.findViewById(R.id.clickMessageTextView);
        scrollMessageTextView = (TextView) tutorialLayout.findViewById(R.id.scrollMessageTextView);
        clickMessageTextView.setText(clickMessage);
        scrollMessageTextView.setText(scrollMessage);
        tutorialLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        tutorialContainer.addView(tutorialLayout);
    }

    // Execute this when the tutorial is seen already
    public void tutorialSeen(View v) {
        tutorialContainer.removeAllViews();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        lockableLinearLayoutManager.setScrollEnabled(true);
        enableClick();
    }

    protected boolean isFirstTime(String name, int mode) {
        if (firstTime == null) {
            SharedPreferences mPreferences = this.getSharedPreferences(name, mode);
            firstTime = mPreferences.getBoolean("firstTime", true);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.apply();
            }
        }
        return firstTime;
    }

    public abstract void disableClick();

    public abstract void enableClick();

}
