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
import android.widget.Toast;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.activities.DisplaySpecializationOptionActivity;

import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by sreerakshakr on 2/4/18.
 */


public class RecyclerViewTypeOneAdapter extends RecyclerView.Adapter<RecyclerViewTypeOneAdapter.ViewHolder> {

    String data[];
    String title;
    Context context;
    String imageFileName = "";
    int imageFileId;


    public RecyclerViewTypeOneAdapter(String title, String data[], Context context){
        this.title = title;
        this.data = data;
        this.context = context;
    }

    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }

    @Override
    public RecyclerViewTypeOneAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recycleview_item_type_one, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewTypeOneAdapter.ViewHolder holder, int position) {
        holder.text_view.setText(WordUtils.capitalize(data[position]));
        imageFileName = new String(data[position]);
        imageFileName.replace(" ", "_");
        imageFileName.replace("&", "and");
        imageFileId = context.getResources().getIdentifier("role_" + data[position].replace(" ", "_").replace("&", "and"), "drawable", context.getPackageName());
        holder.image_view.setImageResource(imageFileId);

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    //Toast.makeText(context, "#" + position + " - " + data[position] + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(view.getContext(), DisplaySpecializationOptionActivity.class);
                    bundle.putString("titleKey", title);
                    bundle.putString("subtitleKey", data[position]);
                    intent.putExtras(bundle);
                    view.getContext().startActivity(intent);
                    //Toast.makeText(context, "#" + position + " - " + data[position], Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{

        private RecyclerViewTypeOneAdapter.ItemClickListener clickListener;
        public TextView text_view;
        public ImageView image_view;
        public ViewHolder(View v) {
            super(v);
            text_view = (TextView) v.findViewById(R.id.layout_recycleview_item_type_one_name);
            image_view = (ImageView) v.findViewById(R.id.layout_recycleview_item_type_one_image);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(RecyclerViewTypeOneAdapter.ItemClickListener itemClickListener) {
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

}