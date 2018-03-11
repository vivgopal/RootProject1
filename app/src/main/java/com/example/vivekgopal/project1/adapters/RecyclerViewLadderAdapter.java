package com.example.vivekgopal.project1.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vivekgopal.project1.R;
import com.example.vivekgopal.project1.data.CompanyItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

/**
 * Created by sreerakshakr on 2/4/18.
 */


public class RecyclerViewLadderAdapter extends RecyclerView.Adapter<RecyclerViewLadderAdapter.ViewHolder> {

    List<String> careerLadderItemList;
    String title;
    Context context;
    int layoutItemId;

    public RecyclerViewLadderAdapter(String title, List<String> careerLadderItemList, Context context){
        this.title = title;
        this.careerLadderItemList = careerLadderItemList;
        this.context = context;
        setlayoutItemId(R.layout.layout_recycleview_ladder_item);
    }

    public void populate_holder(RecyclerViewLadderAdapter.ViewHolder holder, int position) {
        int imageFileId;
        holder.text_view.setText(WordUtils.capitalize(careerLadderItemList.get(position)));
        imageFileId = context.getResources().getIdentifier("icon_ladder", "drawable", context.getPackageName());
        holder.image_view.setImageResource(imageFileId);
        //holder.image_view.setAlpha((position+1)/careerLadderItemList.size());
        holder.image_view.setAlpha((float) (position+1)/careerLadderItemList.size());
    }

    public void doOnClick(View view, int position) {
        // Do nothing
    }

    @Override
    public int getItemCount() {
        return careerLadderItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{

        private RecyclerViewLadderAdapter.ItemClickListener clickListener;

        //--------- depends on layout view ---------
        public TextView text_view;
        public ImageView image_view;

        public void setSubView(View v) {
            text_view = (TextView) v.findViewById(R.id.layout_recycleview_ladder_item_name);
            image_view = (ImageView) v.findViewById(R.id.layout_recycleview_ladder_item_image);
        }
        //----------------------------

        public ViewHolder(View v) {
            super(v);
            setSubView(v);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(RecyclerViewLadderAdapter.ItemClickListener itemClickListener) {
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
    public RecyclerViewLadderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layoutItemId, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewLadderAdapter.ViewHolder holder, int position) {
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