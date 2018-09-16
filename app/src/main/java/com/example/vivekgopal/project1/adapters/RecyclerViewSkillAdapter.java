package com.example.vivekgopal.project1.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.data.SkillItem;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

import static android.R.attr.bitmap;
import static android.R.attr.buttonStyleInset;

/**
 * Created by sreerakshakr on 2/4/18.
 */


public class RecyclerViewSkillAdapter extends RecyclerView.Adapter<RecyclerViewSkillAdapter.ViewHolder> {

    List<SkillItem> skillItemList;
    String degree;
    String specialization;
    Context context;
    Activity activity;
    int layoutItemId;
    public boolean isClickable = true;

    public RecyclerViewSkillAdapter(String degree, String specialization, List<SkillItem> skillItemList, Context context, Activity activity){
        this.degree = degree;
        this.specialization = specialization;
        this.skillItemList = skillItemList;
        this.context = context;
        this.activity = activity;
        setlayoutItemId(R.layout.layout_recycleview_skill_item);
    }

    public void populate_holder(RecyclerViewSkillAdapter.ViewHolder holder, int position) {
        GradientDrawable gd = new GradientDrawable();
        int colorId = R.color.colorTip1;

        holder.text_view1.setText(WordUtils.capitalize(skillItemList.get(position).getSkill()));
        holder.text_view2.setText(WordUtils.capitalize(skillItemList.get(position).getType()));

        switch (skillItemList.get(position).getType()) {
            case "Analytical":
                colorId = R.color.colorTip3;
                break;
            case "Leadership":
                colorId = R.color.colorTip2;
                break;
            case "Programming":
                colorId = R.color.colorTip1;
                break;
            case "Computer":
                colorId = R.color.colorTip4;
                break;
            case "Communication":
                colorId = R.color.colorTip5;
                break;
            default:
                colorId = R.color.colorBlack;
                break;
        }
        gd.setColor(context.getResources().getColor(colorId));
        gd.setCornerRadius(60);
        holder.text_view2.setBackgroundDrawable(gd);
    }

    public void doOnClick(View view, int position) {
        if(isClickable == true) {

            // Add Analytics
            Bundle params = new Bundle();
            params.putInt(context.getResources().getString(R.string.DEGREE_NAME), context.getResources().getInteger(context.getResources().getIdentifier(degree.toLowerCase().replace(" ", "_").replace("&", "and"), "integer", context.getPackageName())));
            params.putInt(context.getResources().getString(R.string.SPECIALIZATION_NAME), context.getResources().getInteger(context.getResources().getIdentifier(specialization.toLowerCase().replace(" ", "_").replace("&", "and"), "integer", context.getPackageName())));
            params.putInt(context.getResources().getString(R.string.SKILL_TYPE), context.getResources().getInteger(context.getResources().getIdentifier(skillItemList.get(position).getType().toLowerCase().replace(" ", "_").replace("&", "and"), "integer", context.getPackageName())));
            params.putString(context.getResources().getString(R.string.SKILL_NAME), skillItemList.get(position).getSkill().toLowerCase());
            FirebaseAnalytics.getInstance(context.getApplicationContext()).logEvent(
                    context.getResources().getString(R.string.EVENT_SKILL_SELECTED), params);

            //Opens in default browser
            //Uri uri = Uri.parse(skillItemList.get(position).getUrl());
            //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            //view.getContext().startActivity(intent);

            //Opens in custom tab within app
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(activity.getResources().getColor(R.color.colorChromeCustomTab));
            builder.setShowTitle(true);
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(activity, Uri.parse(skillItemList.get(position).getUrl()));
        }
    }

    public void enableClick() {
        isClickable = true;
    }

    public void disableClick() {
        isClickable = false;
    }

    @Override
    public int getItemCount() {
        return skillItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{

        private RecyclerViewSkillAdapter.ItemClickListener clickListener;

        //--------- depends on layout view ---------
        public TextView text_view1;
        public TextView text_view2;

        public void setSubView(View v) {
            text_view1 = (TextView) v.findViewById(R.id.layout_recycleview_skill_item_name);
            text_view2 = (TextView) v.findViewById(R.id.layout_recycleview_skill_item_type);
        }
        //----------------------------

        public ViewHolder(View v) {
            super(v);
            setSubView(v);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(RecyclerViewSkillAdapter.ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }

    //------------------------------------------------------------------------------------------------------------
    // DO NOT CHANGE BELOW
    //------------------------------------------------------------------------------------------------------------
    public void setlayoutItemId(int layoutItemId) {
        this.layoutItemId = layoutItemId;
    }

    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }

    @Override
    public RecyclerViewSkillAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layoutItemId, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewSkillAdapter.ViewHolder holder, int position) {
        populate_holder(holder, position);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    //Toast.makeText(context, "#" + position + " - " + data[position] + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(context, "#" + position + " - " + data[position], Toast.LENGTH_SHORT).show();
                    doOnClick(view, position);
                }
            }
        });
    }

}