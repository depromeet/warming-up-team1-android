package com.depromeet.android.data;

import com.depromeet.android.R;

import java.util.ArrayList;

public class CategorySet {
    private ArrayList<Integer> categories;

    public CategorySet() {
        this.categories = new ArrayList<>();
        categories.add(R.drawable.parenting_category_icon);
        categories.add(R.drawable.transport_category_icon);
        categories.add(R.drawable.food_category_icon);
        categories.add(R.drawable.event_category_icon);
        categories.add(R.drawable.culture_category_icon);
        categories.add(R.drawable.utility_fee_category_icon);
        categories.add(R.drawable.shopping_category_icon);
        categories.add(R.drawable.extra_category_icon);
    }

    public int getCategories(int i) {
        return categories.get(i);
    }

    public int size(){
        return categories.size();
    }
}
