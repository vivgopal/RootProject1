package com.example.vivekgopal.project1;
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

        //setContentView(R.layout.linear_layout_screen1);

        //layout_age_19_22 = (LinearLayout) findViewById(R.id.linear_layout_screen_age_19_23);
        //Button button_age_19_22 = (Button) findViewById(R.id.button_age_19_22);
        //button_age_19_22.setOnClickListener(new OnClickListener() {
        //
        //    @Override
        //    public void onClick(View v) {
        //        LayoutInflater inflater = LayoutInflater
        //                .from(getApplicationContext());
        //        View view = inflater.inflate(R.layout.linear_layout_screen_age_19_22, null);
        //        layout_age_19_22.addView(view);
        //
        //    }
        //});

    }

    // Called when the user clicks the Send button
    public void open_activity_19_22(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, LayoutScreenAge19to22Activity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
