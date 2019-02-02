package com.example.reminder;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {Memo.class}, version = 1)
@TypeConverters({MemoTypeConverters.class})
public abstract class MemoDB extends RoomDatabase {
    public abstract MemoDao memoDao();
}
