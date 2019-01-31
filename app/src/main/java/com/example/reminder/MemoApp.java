package com.example.reminder;

import android.app.Application;
import android.content.res.Resources;

public class MemoApp extends Application {

    private static Resources localResources;

    @Override
    public void onCreate() {
        super.onCreate();

        localResources = this.getResources();
    }

    public static Resources getLocalResources(){
        return localResources;
    }

}
