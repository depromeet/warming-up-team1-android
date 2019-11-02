package com.depromeet.android.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.depromeet.android.R;
import com.depromeet.android.data.Category;

import java.util.ArrayList;

public class MainGridAdapter extends BaseAdapter
        implements MainAdapterContract.View, MainAdapterContract.Model{

    private ArrayList<Category> items;
    private final Context context;
    private OnItemClickListener itemClickListener;

    public MainGridAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public int getCount() {
        if(items == null) return 0;
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        MainGridViewHolder holder;

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_main, null);
            holder = new MainGridViewHolder(view, context, itemClickListener);
            view.setTag(holder);
        } else {
            holder = (MainGridViewHolder) view.getTag();
        }
        holder.onBind(items.get(position), position);

        return view;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.itemClickListener = onClickListener;
    }

    @Override
    public void addCategory(ArrayList items) {
        this.items.addAll(items);
        notifyAdapter();
    }

    @Override
    public void clearCategory() {
        items.clear();
        notifyAdapter();
    }
}
