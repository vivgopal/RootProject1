package com.example.vivekgopal.project1.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.activities.DisplaySpecializationActivity;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.apache.commons.lang3.text.WordUtils;

public class MainActivitySearchFragment extends Fragment {

    Boolean firstTime = null;
    LinearLayout tutorialContainer;
    LinearLayout tutorialLayout;
    Context context;
    boolean isClickable = true;
    Spinner dynamicSpinnerStream;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_activity_search, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        context = this.getActivity();

        super.onActivityCreated(savedInstanceState);

        // Get Resources
        final Resources res = getResources();

        // Initialize spinners
        dynamicSpinnerStream = (Spinner) getView().findViewById(R.id.search_fragment_dynamic_spinner_specialization);
        final Button goButton = (Button) getView().findViewById(R.id.search_fragment_home_go_button);

        // Initialize array lists
        final String[] StreamItems = res.getStringArray(R.array.streams);
        for (int i=0; i<StreamItems.length - 1; i++) { // Make text to have small caps
            StreamItems[i] = WordUtils.capitalize(StreamItems[i]);
        }

        // Create array adapter instances
        ArrayAdapter<String> adapterStream = new ArrayAdapter<String>(getActivity(), R.layout.layout_spinner_font, R.id.textViewSpinner,  StreamItems){
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

                if(isClickable == true) {
                    int dynSpinnerStreamPos = dynamicSpinnerStream.getSelectedItemPosition();
                    if (dynSpinnerStreamPos < StreamItems.length - 1) {
                        String stream = res.getStringArray(R.array.streams)[dynSpinnerStreamPos];
                        int id = res.getIdentifier("specialization_" + stream.replaceAll(" ", "_"), "array", getActivity().getPackageName());
                        items = res.getStringArray(id);
                        title = WordUtils.capitalize(stream);
                        subtitle = res.getString(R.string.specializations);
                        startGenericOptionSelectActivity(items, title, subtitle);
                    } else if (dynSpinnerStreamPos == StreamItems.length - 1) {
                        Context context = getActivity();
                        CharSequence text = "Select a specialization";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }
        });

        /*
        if(isFirstTime(this.getClass().getSimpleName(), getActivity().MODE_PRIVATE)) {
            // Lock spinner selection and button press
            isClickable = false;
            dynamicSpinnerStream.setEnabled(false);

            // inflate tutorial layout and and add to the container defined in parent layout
            tutorialContainer = (LinearLayout) getView().findViewById(R.id.activity_main_tutorial_container);
            tutorialLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.activity_main_tutorial, tutorialContainer, false);
            tutorialLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            tutorialContainer.addView(tutorialLayout);
        }
        */
    }

    protected void startGenericOptionSelectActivity(String[] items, String title, String subtitle){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getActivity(), DisplaySpecializationActivity.class);
        bundle.putStringArray("stringKey", items);
        bundle.putString("titleKey", title);
        bundle.putString("subtitleKey", subtitle);
        intent.putExtras(bundle);

        // Add Analytics
        Bundle params = new Bundle();
        params.putInt(getResources().getString(R.string.DEGREE_NAME), getResources().getInteger(getResources().getIdentifier(title.toLowerCase().replace(" ", "_").replace("&", "and"), "integer", getActivity().getPackageName())));
        FirebaseAnalytics.getInstance(getActivity()).logEvent(
                getResources().getString(R.string.EVENT_DEGREE_SELECTED), params);

        startActivity(intent);
    }

    protected boolean isFirstTime(String name, int mode) {
        if (firstTime == null) {
            SharedPreferences mPreferences = getActivity().getSharedPreferences(name, mode);
            firstTime = mPreferences.getBoolean("firstTime", true);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.apply();
            }
        }
        return firstTime;
    }

    // Execute this when the tutorial is seen already
    public void tutorialSeen(View v) {
        tutorialContainer.removeAllViews();
        isClickable = true;
        dynamicSpinnerStream.setEnabled(true);
    }

}
