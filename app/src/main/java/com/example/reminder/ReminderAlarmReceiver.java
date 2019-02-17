package com.example.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class ReminderAlarmReceiver extends BroadcastReceiver {
    private static final String TAG = ReminderAlarmReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Reminder broadcast received...");
        Memo memo = (Memo) intent.getSerializableExtra(MemoApp.memoExtra);

        if (memo != null) {
            this.setReminderNotification(memo, context);

            Log.d(TAG, "Reminder notification set...");
        } else {
            Log.d(TAG, "MEMO IS NULL...");
        }
    }

    private void setReminderNotification(Memo memo, Context context) {
        Log.d(TAG, "Setting notification...");
        NotificationManager notificationManager;
        Notification notification;
        Intent intent = new Intent(context, UpdateMemoActivity.class);

        intent.putExtra(MemoApp.memoExtra, memo);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, memo.getId(),
                intent, PendingIntent.FLAG_CANCEL_CURRENT);

        CharSequence notificationTitle = memo.getTitle();
        CharSequence notificationSummary = memo.getBody();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        notification = builder.setAutoCancel(true).setWhen(System.currentTimeMillis()).
                setContentTitle(notificationTitle).setContentText(notificationSummary).
                setSmallIcon(android.R.mipmap.sym_def_app_icon).setContentIntent(pendingIntent).
                setOngoing(false).build();
        notificationManager = (NotificationManager) context.getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(memo.getId(), notification);

        Log.d(TAG, "Notification set...");
    }
}
