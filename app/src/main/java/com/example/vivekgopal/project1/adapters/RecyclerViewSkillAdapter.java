package com.example.vivekgopal.project1.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.data.CompanyItem;
import com.example.vivekgopal.project1.data.SkillItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

/**
 * Created by sreerakshakr on 2/4/18.
 */


public class RecyclerViewSkillAdapter extends RecyclerView.Adapter<RecyclerViewSkillAdapter.ViewHolder> {

    List<SkillItem> skillItemList;
    String title;
    Context context;
    int layoutItemId;
    public boolean isClickable = true;

    public RecyclerViewSkillAdapter(String title, List<SkillItem> skillItemList, Context context){
        this.title = title;
        this.skillItemList = skillItemList;
        this.context = context;
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
            Uri uri = Uri.parse(skillItemList.get(position).getUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            view.getContext().startActivity(intent);
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