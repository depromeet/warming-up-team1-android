package com.depromeet.android.category;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.depromeet.android.R;

public class CategoryGridAdapter extends BaseAdapter
{
    private final Context context;
    public static Integer[] icons={R.drawable.addcategory283,R.drawable.addcategory282,R.drawable.addcategory281,R.drawable.addcategory280,
            R.drawable.addcategory385,R.drawable.addcategory386,R.drawable.addcategory387,R.drawable.addcategory388,
            R.drawable.addcategory389,R.drawable.addcategory390,R.drawable.addcategory391,R.drawable.addcategory392,
            R.drawable.addcategory411,R.drawable.addcategory412,R.drawable.addcategory413,R.drawable.addcategory414};

    public CategoryGridAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return icons.length;
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
        ImageView imageView;
        if(view == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT));
            imageView.setPadding(0,24,0,24);

            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }else
            imageView=(ImageView)view;
        imageView.setImageResource(icons[position]);
        return imageView;
    }
}