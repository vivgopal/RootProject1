package com.example.vivekgopal.project1.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.activities.DisplaySpecializationOptionActivity;

import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by sreerakshakr on 2/4/18.
 */


public class RecyclerViewSpecializationAdapter extends RecyclerView.Adapter<RecyclerViewSpecializationAdapter.ViewHolder> {

    String data[];
    String title;
    Context context;
    int layoutItemId;

    public RecyclerViewSpecializationAdapter(String title, String data[], Context context){
        this.title = title;
        this.data = data;
        this.context = context;
        setlayoutItemId(R.layout.layout_recycleview_specialization_item);
    }

    public void populate_holder(RecyclerViewSpecializationAdapter.ViewHolder holder, int position) {
        int imageFileId;
        holder.text_view.setText(WordUtils.capitalize(data[position]));
        imageFileId = context.getResources().getIdentifier("role_" + data[position].replace(" ", "_").replace("&", "and"), "drawable", context.getPackageName());
        holder.image_view.setImageResource(imageFileId);
    }

    public void doOnClick(View view, int position) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(view.getContext(), DisplaySpecializationOptionActivity.class);
        bundle.putString("titleKey", title);
        bundle.putString("subtitleKey", data[position]);
        intent.putExtras(bundle);
        view.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{

        private RecyclerViewSpecializationAdapter.ItemClickListener clickListener;

        //--------- depends on layout view ---------
        public TextView text_view;
        public ImageView image_view;

        public void setSubView(View v) {
            text_view = (TextView) v.findViewById(R.id.layout_recycleview_specialization_item_name);
            image_view = (ImageView) v.findViewById(R.id.layout_recycleview_specialization_item_image);
        }
        //----------------------------

        public ViewHolder(View v) {
            super(v);
            setSubView(v);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(RecyclerViewSpecializationAdapter.ItemClickListener itemClickListener) {
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
    public RecyclerViewSpecializationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layoutItemId, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewSpecializationAdapter.ViewHolder holder, int position) {
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