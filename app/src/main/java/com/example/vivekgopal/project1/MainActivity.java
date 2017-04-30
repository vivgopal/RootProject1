package com.example.vivekgopal.project1;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set default fot to PT Sans Narrow
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/pt-sans.narrow.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/pt-sans.narrow.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/pt-sans.narrow.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/pt-sans.narrow.ttf");
        FontsOverride.overrideFont(getApplicationContext(), "SERIF", "fonts/pt-sans.narrow.ttf");

        setContentView(R.layout.activity_main);

        // Initialize spinners
        final Spinner dynamicSpinnerPresent = (Spinner) findViewById(R.id.dynamic_spinner_present);
        final Spinner dynamicSpinnerFuture = (Spinner) findViewById(R.id.dynamic_spinner_future);
        final Button goButton = (Button) findViewById(R.id.home_go_button);

        // Spinner array adapter strings
        final List<String> presentItems;
        final List<String> futureItems;

        // Initialize array lists
        presentItems = new ArrayList<>();
        futureItems = new ArrayList<>();

        // Add to array list
        presentItems.add("10th");
        presentItems.add("11th/12th");
        presentItems.add("Bachelors");
        presentItems.add("Masters");
        presentItems.add("Currently I do");
        futureItems.add("Work");
        futureItems.add("Study Further");
        futureItems.add("I want to");

        // Create array adapter instances
        ArrayAdapter<String> adapterPresent = new ArrayAdapter<String>(this, R.layout.textview_spinner_font, R.id.textViewSpinner,  presentItems){
            @Override
            public int getCount() {
                return(presentItems.size() - 1); // Truncate the list
            }
        };
        ArrayAdapter<String> adapterFuture = new ArrayAdapter<String>(this, R.layout.textview_spinner_font, R.id.textViewSpinner,  futureItems){
            @Override
            public int getCount() {
                return(futureItems.size() - 1); // Truncate the list
            }
        };

        // set adapter to spinners
        dynamicSpinnerPresent.setAdapter(adapterPresent);
        dynamicSpinnerFuture.setAdapter(adapterFuture);

        // Reduce the selection to length-1
        dynamicSpinnerPresent.setSelection(presentItems.size() - 1);
        dynamicSpinnerFuture.setSelection(futureItems.size() - 1);

        // Listeners for Spinners
        dynamicSpinnerPresent.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                //presentPos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }

        });

        dynamicSpinnerFuture.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                //futurePos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        // Listener for Go Button
        goButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if(dynamicSpinnerPresent.getSelectedItemPosition() == 1 && dynamicSpinnerFuture.getSelectedItemPosition() == 1) {
                    Intent intent = new Intent(MainActivity.this, eleventhStudyOptionActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

}
