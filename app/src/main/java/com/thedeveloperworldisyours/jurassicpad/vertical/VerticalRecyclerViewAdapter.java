package com.thedeveloperworldisyours.jurassicpad.vertical;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thedeveloperworldisyours.jurassicpad.R;
import com.thedeveloperworldisyours.jurassicpad.data.SearchItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javierg on 27/04/2017.
 */

public class VerticalRecyclerViewAdapter extends RecyclerView
        .Adapter<VerticalRecyclerViewAdapter
        .DataObjectHolder> {

    private List<SearchItem> mDataset;
    private static VerticalRecyclerViewAdapter.MyClickListener sClickListener;

    static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView mLabel;
        TextView mDateTime;

        DataObjectHolder(View itemView) {
            super(itemView);
            mLabel = (TextView) itemView.findViewById(R.id.vertical_list_item_title);
            mDateTime = (TextView) itemView.findViewById(R.id.vertical_list_item_subtitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            sClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(VerticalRecyclerViewAdapter.MyClickListener myClickListener) {
        this.sClickListener = myClickListener;
    }

    public VerticalRecyclerViewAdapter(List<SearchItem> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public VerticalRecyclerViewAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vertical_list_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(VerticalRecyclerViewAdapter.DataObjectHolder holder, int position) {
        holder.mLabel.setText(mDataset.get(position).getLogin());
        holder.mDateTime.setText(mDataset.get(position).getAvatarUrl());
    }

    void addItem(SearchItem dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    public void refreshResults(List<SearchItem> items) {
        mDataset = items;
        notifyDataSetChanged();
    }

}

