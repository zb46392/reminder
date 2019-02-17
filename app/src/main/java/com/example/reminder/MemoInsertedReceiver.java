package com.example.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class MemoInsertedReceiver extends BroadcastReceiver {
    public static final String INTENT_NAME = "com.example.reminder.MEMO_INSERTED";
    private static final String TAG = MemoInsertedReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Broadcast Receved");
        Memo memo = (Memo)intent.getExtras().getSerializable(MemoApp.memoExtra);

        if(memo != null && memo.getReminderDate() != null
                && memo.getReminderDate().after(Calendar.getInstance())){
            ((MemoApp)context.getApplicationContext()).setReminderAlarm(memo);
            Log.d(TAG, "Alarm set...");
        }
    }
}
