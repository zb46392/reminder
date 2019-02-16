package com.example.reminder;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {Memo.class}, version = 1)
@TypeConverters({MemoTypeConverters.class})
public abstract class MemoDB extends RoomDatabase {
    private static MemoDB instance;

    public abstract MemoDao memoDao();

    public static synchronized MemoDB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MemoDB.class, "Memos")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return  instance;
    }
}
