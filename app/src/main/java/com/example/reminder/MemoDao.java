package com.example.reminder;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MemoDao {

    @Query("SELECT * FROM Memos")
    List<Memo> getAll();

    @Query("SELECT * FROM Memos WHERE id=:id")
    Memo getById(Integer id);

    @Query("SELECT id, create_date, title, reminder_date, period FROM Memos")
    List<Memo> getAllBasic();

    @Insert
    void insert(Memo memo);

    @Update
    void update(Memo memo);

    @Delete
    void delete(Memo memo);
}
