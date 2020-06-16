package com.drebtchinsky.testeandroidv2.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.drebtchinsky.testeandroidv2.entity.StatementList;
import com.drebtchinsky.testeandroidv2.entity.UserAccount;

import java.util.List;

@Dao
public interface StatementListDAO {
    @Insert
    void create(StatementList statementList);

    @Query("SELECT * FROM statementList")
    List<StatementList> getAll();

    @Delete
    void remove(StatementList statementList);

    @Query("DELETE FROM statementList")
    void removeAll();

    @Update
    void edit(StatementList statementList);
}
