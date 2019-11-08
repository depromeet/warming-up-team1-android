package com.depromeet.android.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponaseAccount {
    @SerializedName("balance")
    @Expose
    private int balance;

    @SerializedName("budget")
    @Expose
    private int budget;

    @SerializedName("id")
    @Expose
    private int id;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @SerializedName("month")
    @Expose
    private int month;
}
