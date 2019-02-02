package com.example.reminder;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.res.Resources;

public class MemoApp extends Application {

    private static Resources localResources;
    private MemoDB memoDB;

    @Override
    public void onCreate() {
        super.onCreate();

        localResources = this.getResources();

        this.memoDB = Room.databaseBuilder(this.getApplicationContext(), MemoDB.class, "Memos").
                fallbackToDestructiveMigration().build();
    }

    public static Resources getLocalResources(){
        return localResources;
    }

    public MemoDB getMemoDB(){
        return this.memoDB;
    }
}
