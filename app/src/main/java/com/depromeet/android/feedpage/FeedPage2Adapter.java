package com.depromeet.android.feedpage;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.android.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeedPage2Adapter extends RecyclerView.Adapter<FeedPage2Adapter.CustomViewHolder> {

    private ArrayList<Feedpage2Item> mList;

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView date;
        protected TextView category;
        protected ImageView icon;
        protected TextView money;


        public CustomViewHolder(View view) {
            super(view);
            this.date = (TextView) view.findViewById(R.id.feedItemDate);
            this.category = (TextView) view.findViewById(R.id.feedItemCategory);
            this.icon = (ImageView) view.findViewById(R.id.feedItemIcon);
            this.money = (TextView) view.findViewById(R.id.feedItemMoney);

        }
    }


    public FeedPage2Adapter(ArrayList<Feedpage2Item> list) {
        this.mList = list;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.feedpage2_itemlist, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        if (mList.get(position).getMoney() < 0)
            viewholder.money.setTextColor(Color.RED);
        else
            viewholder.money.setTextColor(Color.BLUE);


        viewholder.date.setGravity(Gravity.CENTER);
        viewholder.category.setGravity(Gravity.CENTER);
        viewholder.money.setGravity(Gravity.CENTER);

        viewholder.date.setText(mList.get(position).getDate());
        viewholder.category.setText(mList.get(position).getCategory());
        viewholder.icon.setImageResource(mList.get(position).getIcon()); //아이콘 바꾸기
        int money = mList.get(position).getMoney();

        String moneyStr = getFormatDEC(money) + "원";
        if (money > 0)
            moneyStr = "+" + moneyStr;

        viewholder.money.setText(moneyStr);
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

    public static String getFormatDEC(int num) {
        String number = String.valueOf(num);
        DecimalFormat dec = new DecimalFormat("##,###,###");
        if (!number.trim().equals("")) {
            number = dec.format(Long.valueOf(number));
        }
        return number;
    }
}