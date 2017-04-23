package com.example.vivekgopal.project1;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.Intent;
import android.view.Menu;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout_age_19_22;
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Typeface pt_sans_narrow = Typeface.createFromAsset(getAssets(),"fonts/pt-sans.narrow.ttf");
        //TextView textView_button1 = (TextView) findViewById(R.id.text_button1);
        //textView_button1.setTypeface(pt_sans_narrow);

        // Set default fot to PT Sans Narrow
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/pt-sans.narrow.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/pt-sans.narrow.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/pt-sans.narrow.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/pt-sans.narrow.ttf");

        setContentView(R.layout.activity_main);

        // Initialize spinners
        final Spinner dynamicSpinnerAge = (Spinner) findViewById(R.id.dynamic_spinner_age);
        Spinner dynamicSpinnerPresent = (Spinner) findViewById(R.id.dynamic_spinner_present);
        Spinner dynamicSpinnerFuture = (Spinner) findViewById(R.id.dynamic_spinner_future);

        // Spinner array adapter strings
        String[] ageItems = new String[] { "14 - 15", "16 - 18", "19 - 22", "23+", "Select Age" };
        String[] presentItems = new String[] { "10th", "12th", "Diploma", "1st Year", "Currently I do" };
        String[] futureItems = new String[] { "Work", "Study", "I want to" };

        // Reduce the size to display all but the last item, which is the title
        final int ageItemsSize = ageItems.length - 1;
        final int presentItemsSize = presentItems.length - 1;
        final int futureItemsSize = futureItems.length - 1;


        // Array adapter initializations
        ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(this, R.layout.textview_with_font_change, R.id.textView1, ageItems){
        //ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ageItems){
            @Override
            public int getCount() {
                return(ageItemsSize); // Truncate the list
            }
        };


        ArrayAdapter<String> adapterPresent = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, presentItems){
            @Override
            public int getCount() {
                return(presentItemsSize); // Truncate the list
            }
        };

        ArrayAdapter<String> adapterFuture = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, futureItems){
            @Override
            public int getCount() {
                return(futureItemsSize); // Truncate the list
            }
        };


        // set adapter
        dynamicSpinnerAge.setAdapter(adapterAge);
        dynamicSpinnerPresent.setAdapter(adapterPresent);
        dynamicSpinnerFuture.setAdapter(adapterFuture);

        // Reduce the selection to length-1
        dynamicSpinnerAge.setSelection(ageItemsSize);
        dynamicSpinnerPresent.setSelection(presentItemsSize);
        dynamicSpinnerFuture.setSelection(futureItemsSize);

        // Listeners for spinners
        dynamicSpinnerAge.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        dynamicSpinnerPresent.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }

    // Called when the user clicks the Send button
//    public void open_activity_19_22(View view) {
//        // Do something in response to button
//        Intent intent = new Intent(this, LayoutScreenAge19to22Activity.class);
//        //EditText editText = (EditText) findViewById(R.id.edit_message);
//        //String message = editText.getText().toString();
//        //intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }
}
