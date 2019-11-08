package com.depromeet.android.feedpage;

public class Feedpage2Item {
    private String date;
    private String category;
    private int money;
    public Feedpage2Item(String date, String category, int money) {
        this.date = date;
        this.category = category;
        this.money = money;
    }

    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
