package com.example.vivekgopal.project1.activities;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.vivekgopal.project1.preferences.FontsOverride;
import com.example.vivekgopal.project1.R;
import org.apache.commons.lang3.text.WordUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        super.onCreate(savedInstanceState);

        // Set default fot to PT Sans Narrow
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/pt-sans.narrow.ttf");
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/pt-sans.narrow.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/pt-sans.narrow.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/pt-sans.narrow.ttf");

        setContentView(R.layout.activity_main);

        // Get Resources
        final Resources res = getResources();

        // Initialize spinners
        final Spinner dynamicSpinnerStream = (Spinner) findViewById(R.id.dynamic_spinner_specialization);
        final Button goButton = (Button) findViewById(R.id.home_go_button);

        // Initialize array lists
        final String[] StreamItems = res.getStringArray(R.array.streams);
        for (int i=0; i<StreamItems.length - 1; i++) { // Make text to have small caps
            StreamItems[i] = WordUtils.capitalize(StreamItems[i]);
        }

        // Create array adapter instances
        ArrayAdapter<String> adapterStream = new ArrayAdapter<String>(this, R.layout.layout_spinner_font, R.id.textViewSpinner,  StreamItems){
            @Override
            public int getCount() {
                return(StreamItems.length - 1); // Truncate the list
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setPadding(0, 0, 0, 0);
                return view;
            }
        };

        // set adapter to spinners
        dynamicSpinnerStream.setAdapter(adapterStream);

        // Reduce the selection to length-1
        dynamicSpinnerStream.setSelection(StreamItems.length - 1);

        // Listener for Go Button
        goButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String[] items;
                String title = "";
                String subtitle = "";

                int dynSpinnerStreamPos = dynamicSpinnerStream.getSelectedItemPosition();
                if(dynSpinnerStreamPos < StreamItems.length - 1) {
                    String stream = res.getStringArray(R.array.streams)[dynSpinnerStreamPos];
                    int id = res.getIdentifier("specialization_" + stream.replaceAll(" ", "_"), "array", getPackageName());
                    items = res.getStringArray(id);
                    title = WordUtils.capitalize(stream);
                    subtitle = res.getString(R.string.roles);
                    startGenericOptionSelectActivity(items, title, subtitle);
                } else if(dynSpinnerStreamPos == StreamItems.length - 1) {
                    Context context = getApplicationContext();
                    CharSequence text = "Select a specialization";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }

    protected void startGenericOptionSelectActivity(String[] items, String title, String subtitle){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(MainActivity.this, GenericOptionSelectActivity.class);
        bundle.putStringArray("stringKey", items);
        bundle.putString("titleKey", title);
        bundle.putString("subtitleKey", subtitle);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
