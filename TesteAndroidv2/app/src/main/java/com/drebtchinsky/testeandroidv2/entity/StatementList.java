package com.drebtchinsky.testeandroidv2.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class StatementList {
    @PrimaryKey(autoGenerate = true)
    private int statementId = 0;
    private String title;
    private String desc;
    private String date;
    private double value;

    public StatementList() {
    }

    @Ignore
    public StatementList(String title, String desc, String date, double value) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.value = value;
    }

    public int getStatementId() {
        return statementId;
    }

    public void setStatementId(int statementId) {
        this.statementId = statementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
