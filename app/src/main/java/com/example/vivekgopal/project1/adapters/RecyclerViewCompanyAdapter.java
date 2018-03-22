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
import com.example.vivekgopal.project1.data.CertificationItem;
import com.example.vivekgopal.project1.data.CompanyItem;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

/**
 * Created by sreerakshakr on 2/4/18.
 */


public class RecyclerViewCompanyAdapter extends RecyclerView.Adapter<RecyclerViewCompanyAdapter.ViewHolder> {

    List<CompanyItem> companyItemList;
    String title;
    Context context;
    int layoutItemId;
    public boolean isClickable = true;

    public RecyclerViewCompanyAdapter(String title, List<CompanyItem> companyItemList, Context context){
        this.title = title;
        this.companyItemList = companyItemList;
        this.context = context;
        setlayoutItemId(R.layout.layout_recycleview_company_item);
    }

    public void populate_holder(RecyclerViewCompanyAdapter.ViewHolder holder, int position) {
        int imageFileId;
        holder.text_view.setText(WordUtils.capitalize(companyItemList.get(position).getCompany()));
        imageFileId = context.getResources().getIdentifier("company_" + companyItemList.get(position).getCompany().toLowerCase().replace(" ","_").replace("-","").replace(".","").replace("&","and").replace("'",""), "drawable", context.getPackageName());
        holder.image_view.setImageResource(imageFileId);
    }

    public void doOnClick(View view, int position) {
        if(isClickable == true) {
            Uri uri = Uri.parse(companyItemList.get(position).getUrl());
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
        return companyItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{

        private RecyclerViewCompanyAdapter.ItemClickListener clickListener;

        //--------- depends on layout view ---------
        public TextView text_view;
        public ImageView image_view;

        public void setSubView(View v) {
            text_view = (TextView) v.findViewById(R.id.layout_recycleview_company_item_name);
            image_view = (ImageView) v.findViewById(R.id.layout_recycleview_company_item_image);
        }
        //----------------------------

        public ViewHolder(View v) {
            super(v);
            setSubView(v);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(RecyclerViewCompanyAdapter.ItemClickListener itemClickListener) {
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
    public RecyclerViewCompanyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layoutItemId, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewCompanyAdapter.ViewHolder holder, int position) {
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