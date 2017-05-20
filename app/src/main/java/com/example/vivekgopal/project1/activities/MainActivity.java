package com.example.vivekgopal.project1.activities;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.support.v7.app.AppCompatActivity;

import com.example.vivekgopal.project1.preferences.FontsOverride;
import com.example.vivekgopal.project1.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set default fot to PT Sans Narrow
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/pt-sans.narrow.ttf");

        setContentView(R.layout.activity_main);

        // Get Resources
        final Resources res = getResources();

        // Initialize spinners
        final Spinner dynamicSpinnerPresent = (Spinner) findViewById(R.id.dynamic_spinner_present);
        final Spinner dynamicSpinnerFuture = (Spinner) findViewById(R.id.dynamic_spinner_future);
        final Button goButton = (Button) findViewById(R.id.home_go_button);

        // Initialize array lists
        final String[] presentItems = res.getStringArray(R.array.present_items);
        final String[] futureItems = res.getStringArray(R.array.future_items);

        // Create array adapter instances
        ArrayAdapter<String> adapterPresent = new ArrayAdapter<String>(this, R.layout.textview_spinner_font, R.id.textViewSpinner,  presentItems){
            @Override
            public int getCount() {
                return(presentItems.length - 1); // Truncate the list
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setPadding(0, 0, 0, 0);
                return view;
            }
        };
        ArrayAdapter<String> adapterFuture = new ArrayAdapter<String>(this, R.layout.textview_spinner_font, R.id.textViewSpinner,  futureItems){
            @Override
            public int getCount() {
                return(futureItems.length - 1); // Truncate the list
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setPadding(0, 0, 0, 0);
                return view;
            }
        };

        // set adapter to spinners
        dynamicSpinnerPresent.setAdapter(adapterPresent);
        dynamicSpinnerFuture.setAdapter(adapterFuture);

        // Reduce the selection to length-1
        dynamicSpinnerPresent.setSelection(presentItems.length - 1);
        dynamicSpinnerFuture.setSelection(futureItems.length - 1);

        // Listener for Go Button
        goButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String[] items;
                String title = "";
                int dynSpinnerPresentPos = dynamicSpinnerPresent.getSelectedItemPosition();
                int dynSpinnerFuturePos = dynamicSpinnerFuture.getSelectedItemPosition();

                if(dynSpinnerPresentPos == 0 && dynSpinnerFuturePos == 1) {
                    items = res.getStringArray(R.array.eleventh_study_options);
                    title = res.getString(R.string.eleventh_study_options_title);
                    startGenericOptionSelectActivity(items, title);
                }
                else if(dynSpinnerPresentPos == 1 && dynSpinnerFuturePos == 1) {
                    items = res.getStringArray(R.array.eleventh_study_options1);
                    title = res.getString(R.string.eleventh_study_options1_title);
                    startGenericOptionSelectActivity(items, title);
                }
                else if(dynSpinnerPresentPos == 2 && dynSpinnerFuturePos == 1) {
                    items = res.getStringArray(R.array.eleventh_study_options2);
                    title = res.getString(R.string.eleventh_study_options2_title);
                    //startGenericOptionSelectActivity(items, title);
                    Intent intent = new Intent(MainActivity.this, genericSpecializationOptionActivity.class);
                    startActivity(intent);
                }
                else if(dynSpinnerPresentPos == 3 && dynSpinnerFuturePos == 1) {
                    items = res.getStringArray(R.array.eleventh_study_options2);
                    title = res.getString(R.string.eleventh_study_options2_title);
                    //startGenericOptionSelectActivity(items, title);
                    Intent intent = new Intent(MainActivity.this, DisplaySalary.class);
                    startActivity(intent);
                }
            }
        });

    }

    protected void startGenericOptionSelectActivity(String[] items, String title){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(MainActivity.this, genericOptionSelectActivity.class);
        bundle.putStringArray("stringKey", items);
        bundle.putString("titleKey", title);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
