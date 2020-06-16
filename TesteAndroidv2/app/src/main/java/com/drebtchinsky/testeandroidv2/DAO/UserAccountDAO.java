package com.drebtchinsky.testeandroidv2.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.drebtchinsky.testeandroidv2.entity.UserAccount;

import java.util.List;

@Dao
public interface UserAccountDAO {
    @Insert
    void create(UserAccount userAccount);

    @Query("SELECT * FROM userAccount")
    List<UserAccount> getAll();

    @Delete
    void remove(UserAccount userAccount);

    @Query("DELETE FROM userAccount")
    void removeAll();

    @Update
    void edit(UserAccount userAccount);
}
