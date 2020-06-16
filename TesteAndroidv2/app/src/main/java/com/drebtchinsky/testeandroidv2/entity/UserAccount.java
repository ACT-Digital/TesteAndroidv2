package com.drebtchinsky.testeandroidv2.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;
import java.util.Calendar;

@Entity
public class UserAccount {
    @PrimaryKey(autoGenerate = false)
    private int userId = 0;
    private String name;
    private String bankAccount;
    private String agency;
    private double balance;

    public UserAccount() {
    }

    @Ignore
    public UserAccount(int userId, String name, String bankAccount, String agency, double balance) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
