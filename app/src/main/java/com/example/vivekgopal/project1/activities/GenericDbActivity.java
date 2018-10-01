package com.example.vivekgopal.project1.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.adapters.DatabaseAdapter;
import com.example.vivekgopal.project1.preferences.LockableLinearLayoutManager;

import org.apache.commons.lang3.text.WordUtils;

import java.lang.reflect.Field;

public abstract class GenericDbActivity extends AppCompatActivity {

    public String title;
    public String subtitle;
    public DatabaseAdapter mDbAdapter;
    Toolbar titleToolbar;
    Toolbar subtitleToolbar;
    RecyclerView recyclerView;
    Toolbar searchToolbar;
    Menu search_menu;
    MenuItem item_search;

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
        setContentView(R.layout.layout_recycleview_with_toolbar);
        setupView();
        initRecyclerView();
        setSearchToolbar();
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
        Drawable drawable= getResources().getDrawable(R.drawable.ic_home);
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
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            case R.id.action_search:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    circleReveal(R.id.searchtoolbar,1,true,true);
                else
                    searchToolbar.setVisibility(View.VISIBLE);

                item_search.expandActionView();
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }


    public void setSearchToolbar()
    {
        searchToolbar = (Toolbar) findViewById(R.id.searchtoolbar);
        if (searchToolbar != null) {
            searchToolbar.inflateMenu(R.menu.menu_search);
            search_menu=searchToolbar.getMenu();

            searchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        circleReveal(R.id.searchtoolbar,1,true,false);
                    else
                        searchToolbar.setVisibility(View.GONE);
                }
            });

            item_search = search_menu.findItem(R.id.action_filter_search);

            MenuItemCompat.setOnActionExpandListener(item_search, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    // Do something when collapsed
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleReveal(R.id.searchtoolbar,1,true,false);
                    }
                    else
                        searchToolbar.setVisibility(View.GONE);
                    return true;
                }

                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    // Do something when expanded
                    return true;
                }
            });

            initSearchView();


        } else
            Log.d("toolbar", "setSearchToolbar: NULL");
    }

    public void initSearchView()
    {
        final SearchView searchView =
                (SearchView) search_menu.findItem(R.id.action_filter_search).getActionView();

        // Enable/Disable Submit button in the keyboard

        searchView.setSubmitButtonEnabled(false);

        // Change search close button image
        ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);
        closeButton.setImageResource(R.drawable.ic_close);


        // set hint and the text colors
        EditText txtSearch = ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        txtSearch.setHint("Search App..");
        txtSearch.setHintTextColor(getResources().getColor(R.color.colorTransparentWhite));
        txtSearch.setTextColor(getResources().getColor(R.color.colorWhite));


        // set the cursor
        AutoCompleteTextView searchTextView = (AutoCompleteTextView) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            mCursorDrawableRes.set(searchTextView, R.drawable.search_cursor); //This sets the cursor resource ID to 0 or @null which will make it visible on white background
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                callSearch(newText);
                return true;
            }

            public void callSearch(String query) {
                //Do searching
                Log.i("query", "" + query);

            }

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void circleReveal(int viewID, int posFromRight, boolean containsOverflow, final boolean isShow)
    {
        final View myView = findViewById(viewID);

        int width=myView.getWidth();

        if(posFromRight>0)
            width-=(posFromRight*getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material))-(getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)/ 2);
        if(containsOverflow)
            width-=getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material);

        int cx=width;
        int cy=myView.getHeight()/2;

        Animator anim;
        if(isShow)
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0,(float)width);
        else
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, (float)width, 0);

        anim.setDuration((long)220);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if(!isShow)
                {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            }
        });

        // make the view visible and start the animation
        if(isShow)
            myView.setVisibility(View.VISIBLE);

        // start the animation
        anim.start();


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
        tutorialLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_recycleview_with_toolbar_tutorial, tutorialContainer, false);

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
