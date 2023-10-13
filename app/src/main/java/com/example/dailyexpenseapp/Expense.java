package com.example.dailyexpenseapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense")
public class Expense {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo(name = "date")
    String date;

    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo(name = "amount")
    String amount;

    Expense(int id, String date, String title, String amount){
        this.id= id;
        this.date = date;
        this.title=title;
        this.amount= amount;
    }

    @Ignore
    Expense(String date, String title, String amount){
        this.date = date;
        this.title=title;
        this.amount= amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
