package com.example.vivekgopal.project1.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;

/**
 * Created by sreerakshakr on 10/10/17.
 */

public class CertificationListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] certificationTitle;
    private final Integer[] certificationLogoId;

    // Constructor
    public CertificationListAdapter(Activity context, String[] certificationTitle, Integer[] certificationLogoId) {
        super(context, R.layout.layout_certification, certificationTitle);
        this.context = context;
        this.certificationTitle = certificationTitle;
        this.certificationLogoId = certificationLogoId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.layout_certification, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.certification_title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.certification_logo);

        txtTitle.setText(certificationTitle[position]);
        imageView.setImageResource(certificationLogoId[position]);
        return rowView;
    }
}
