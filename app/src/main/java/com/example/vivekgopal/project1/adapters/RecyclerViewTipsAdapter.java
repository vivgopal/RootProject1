package com.example.vivekgopal.project1.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.activities.DisplayCareerTipActivity;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

/**
 * Created by sreerakshakr on 2/4/18.
 */


public class RecyclerViewTipsAdapter extends RecyclerView.Adapter<RecyclerViewTipsAdapter.ViewHolder> {

    List<String> tipsList;
    String title;
    String subtitle;
    Context context;
    int layoutItemId;
    public boolean isClickable = true;

    public RecyclerViewTipsAdapter(String title, String subtitle, List<String> tipsList, Context context){
        this.title = title;
        this.subtitle = subtitle;
        this.tipsList = tipsList;
        this.context = context;
        setlayoutItemId(R.layout.layout_recycleview_tips_item);
    }

    public void populate_holder(RecyclerViewTipsAdapter.ViewHolder holder, int position) {
        int imageFileId;
        int colorId;
        imageFileId = context.getResources().getIdentifier("icon_calandar" + Integer.toString((position%5)+1), "drawable", context.getPackageName());
        colorId = context.getResources().getIdentifier("colorTip" + Integer.toString((position%5)+1), "color", context.getPackageName());
        holder.text_view1.setText("#" + Integer.toString(position + 1));
        holder.text_view1.setBackgroundResource(imageFileId);
        holder.text_view1.setTextColor(context.getResources().getColor(colorId));
        holder.text_view2.setText(tipsList.get(position));
        //holder.text_view1.setText("1");
        //holder.text_view2.setText("sldflsdjkflsdj");
    }

    public void doOnClick(View view, int position) {
        if(isClickable == true) {
            Bundle bundle = new Bundle();
            Intent intent = new Intent(view.getContext(), DisplayCareerTipActivity.class);
            bundle.putString("titleKey", title);
            bundle.putString("subtitleKey", WordUtils.capitalize(subtitle));
            bundle.putInt("tipIdKey", position);
            intent.putExtras(bundle);
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
        return tipsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{

        private RecyclerViewTipsAdapter.ItemClickListener clickListener;

        //--------- depends on layout view ---------
        public TextView text_view1;
        public TextView text_view2;

        public void setSubView(View v) {
            text_view1 = (TextView) v.findViewById(R.id.layout_recycleview_tips_item_number);
            text_view2 = (TextView) v.findViewById(R.id.layout_recycleview_tips_item_name);
        }
        //----------------------------

        public ViewHolder(View v) {
            super(v);
            setSubView(v);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(RecyclerViewTipsAdapter.ItemClickListener itemClickListener) {
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
    public RecyclerViewTipsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layoutItemId, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewTipsAdapter.ViewHolder holder, int position) {
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