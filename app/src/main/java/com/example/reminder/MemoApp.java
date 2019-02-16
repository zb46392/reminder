package com.example.reminder;

import android.app.Application;
import android.content.res.Resources;

public class MemoApp extends Application {

    private static Resources localResources;
    public final static String memoExtra = "memo";
    public final static String memoMilisExtra = "memoMilis";

    public static final Integer setReminderRequestCode = 100;

    @Override
    public void onCreate() {
        super.onCreate();
        localResources = this.getResources();
    }

    public static Resources getLocalResources(){
        return localResources;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        MemoDB.getInstance(this).close();
    }
}
