package com.drebtchinsky.testeandroidv2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.drebtchinsky.testeandroidv2.DAO.StatementListDAO;
import com.drebtchinsky.testeandroidv2.DAO.UserAccountDAO;
import com.drebtchinsky.testeandroidv2.database.converter.ConverterCalendar;
import com.drebtchinsky.testeandroidv2.entity.StatementList;
import com.drebtchinsky.testeandroidv2.entity.UserAccount;

@Database(entities = {UserAccount.class, StatementList.class}, version = 1, exportSchema = false)
@TypeConverters({ConverterCalendar.class})
public abstract class BankDatabase extends RoomDatabase {
    private static final String BANK_DB = "bank.db";
    public abstract UserAccountDAO getUserAccountDAO();
    public abstract StatementListDAO getStatementListDAO();

    public static BankDatabase getInstance(Context context) {
        return Room
                .databaseBuilder(context, BankDatabase.class, BANK_DB)
                .allowMainThreadQueries()
                .build();
    }
}
