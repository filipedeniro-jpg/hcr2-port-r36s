package com.fingersoft.hcr2;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes.dex */
public class NotificationReceiver extends BroadcastReceiver {
    public static String NOTIFICATION = "notification";
    public static String NOTIFICATION_TAG = "notification-tag";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                ((NotificationManager) context.getSystemService("notification")).notify(intent.getStringExtra(NOTIFICATION_TAG), 0, (Notification) intent.getParcelableExtra(NOTIFICATION));
                try {
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
                    PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 134217728);
                    broadcast.cancel();
                    alarmManager.cancel(broadcast);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
