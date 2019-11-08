package com.depromeet.android.data;

import com.depromeet.android.R;

import java.util.ArrayList;

public enum  CategorySetIcon {
    Parenting(0, R.drawable.parenting_category_icon),
    Transport(1, R.drawable.transport_category_icon),
    Food(2, R.drawable.food_category_icon),
    Event(3, R.drawable.event_category_icon),
    Culture(4, R.drawable.culture_category_icon),
    Utility(5, R.drawable.utility_fee_category_icon),
    Shopping(6, R.drawable.shopping_category_icon),
    Extra(7, R.drawable.shopping_category_icon);

    private int value;
    private int image;

    CategorySetIcon(int value, int drawableId) {
        this.value = value;
        this.image = drawableId;
    }

    public int getImage(){
        return image;
    }
    private ArrayList<Integer> list;

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;

        list.add(R.drawable.parenting_category_icon);
        list.add(R.drawable.transport_category_icon);
        list.add(R.drawable.food_category_icon);
        list.add(R.drawable.event_category_icon);
        list.add(R.drawable.culture_category_icon);
        list.add(R.drawable.utility_fee_category_icon);
        list.add(R.drawable.shopping_category_icon);
        list.add(R.drawable.extra_category_icon);
    }

}
