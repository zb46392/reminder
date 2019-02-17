package com.example.reminder;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Calendar;
import java.util.List;

@Dao
public interface MemoDao {

    @Query("SELECT * FROM Memos")
    LiveData<List<Memo>> getAll();

    @Query("SELECT * FROM Memos WHERE id=:id")
    Memo getById(Integer id);

    @Query("SELECT reminder_date FROM Memos WHERE id = :id")
    Calendar getReminderDateById(Integer id);

    @Insert
    Long insert(Memo memo);

    @Update
    void update(Memo memo);

    @Delete
    void delete(Memo memo);
}
