package com.example.reminder;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

public class MemoApp extends Application {

    private final static String TAG = MemoApp.class.getSimpleName();
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

    public void setReminderAlarm(Memo memo){
        Log.d(TAG, "Setting Alarm...");
        AlarmManager alarmMng = (AlarmManager)getSystemService(getApplicationContext().ALARM_SERVICE);
        Intent intent = new Intent("com.example.reminder.REMINDER");

        intent.putExtra(memoExtra, memo);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, memo.getId(), intent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        alarmMng.setExact(AlarmManager.RTC_WAKEUP, memo.getReminderDate().getTimeInMillis(), pIntent);
        Log.d(TAG, "Alarm set...");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        MemoDB.getInstance(this).close();
    }
}
