package com.example.vivekgopal.project1.activities;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.fragments.MainActivityExploreFragment;
import com.example.vivekgopal.project1.fragments.MainActivitySearchFragment;
import com.example.vivekgopal.project1.fragments.Fragment1;
import com.example.vivekgopal.project1.preferences.FontsOverride;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        final View tabOne;
        final View tabTwo;

        // Set default fot to PT Sans Narrow
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/pt-sans.narrow.ttf");
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/pt-sans.narrow.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/pt-sans.narrow.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/pt-sans.narrow.ttf");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        createViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //createTabIcons();
        tabOne = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView tabOneText = (TextView) tabOne.findViewById(R.id.tab_text);
        ImageView tabOneImage = (ImageView) tabOne.findViewById(R.id.tab_image);
        //tabOne.findViewById(R.id.tab_image).setAlpha((float)0.2);
        tabOneText.setText("Explore Careers");
        tabOneImage.setImageResource(R.drawable.ic_briefcase);
        //tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_calandar1, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        tabTwo = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView tabTwoText = (TextView) tabTwo.findViewById(R.id.tab_text);
        ImageView tabTwoImage = (ImageView) tabTwo.findViewById(R.id.tab_image);
        tabTwoText.setText("Search");
        tabTwoImage.setImageResource(R.drawable.ic_magnifier);
        //tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_calandar2, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        // Instantiate tab colors
        tabOne.findViewById(R.id.tab_image).setAlpha((float)0.9);
        tabOne.findViewById(R.id.tab_text).setAlpha((float)0.9);
        tabTwo.findViewById(R.id.tab_image).setAlpha((float)0.3);
        tabTwo.findViewById(R.id.tab_text).setAlpha((float)0.3);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Toast.makeText(MainActivity.this, "tabSelected:  " + tab.getPosition(), Toast.LENGTH_SHORT).show();

                switch (tab.getPosition()) {
                    case 0:
                        tabOne.findViewById(R.id.tab_image).setAlpha((float)0.9);
                        tabOne.findViewById(R.id.tab_text).setAlpha((float)0.9);
                        tabTwo.findViewById(R.id.tab_image).setAlpha((float)0.3);
                        tabTwo.findViewById(R.id.tab_text).setAlpha((float)0.3);
                        break;
                    case 1:
                        tabTwo.findViewById(R.id.tab_image).setAlpha((float)0.9);
                        tabTwo.findViewById(R.id.tab_text).setAlpha((float)0.9);
                        tabOne.findViewById(R.id.tab_image).setAlpha((float)0.3);
                        tabOne.findViewById(R.id.tab_text).setAlpha((float)0.3);
                        break;
                }

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void createTabIcons() {

        View tabOne = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView tabOneText = (TextView) tabOne.findViewById(R.id.tab_text);
        //tabOne.findViewById(R.id.tab_image).setAlpha((float)0.2);
        tabOneText.setText("Explore Careers");
        //tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_calandar1, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        View tabTwo = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView tabTwoText = (TextView) tabTwo.findViewById(R.id.tab_text);
        tabTwoText.setText("Search");
        //tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_calandar2, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);
    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        //adapter.addFrag(new MainActivityExploreFragment(), "Tab 1");
        adapter.addFrag(new MainActivitySearchFragment(), "Tab 1");
        adapter.addFrag(new MainActivitySearchFragment(), "Tab 2");
        //adapter.addFrag(new Fragment1(), "Tab 1");
        //adapter.addFrag(new Fragment1(), "Tab 2");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }
}