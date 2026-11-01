package com.fingersoft.hcr2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import com.my.target.aa;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class NotificationChannels {
    private static Map<String, NotificationChannelInfo> notificationChannelInfos;

    public static class NotificationChannelInfo {
        String description;
        int importance = 3;
        String name;

        public NotificationChannelInfo(String str, String str2) {
            this.name = str;
            this.description = str2;
        }

        public String getName() {
            return this.name;
        }

        public String getDescription() {
            return this.description;
        }

        public int getImportance() {
            return this.importance;
        }
    }

    private static void popuplateNotificationChannels() {
        notificationChannelInfos = new HashMap();
        notificationChannelInfos.put("timers", new NotificationChannelInfo("Timers", "Notifications for finished timers"));
        notificationChannelInfos.put("reminders", new NotificationChannelInfo("Reminders", "Notifications for upcoming and ending events"));
    }

    public static NotificationChannelInfo getNotificationChannelInfo(String str) {
        if (notificationChannelInfos == null) {
            popuplateNotificationChannels();
        }
        return notificationChannelInfos.get(str);
    }

    public static void createChannel(String str) {
        NotificationChannelInfo notificationChannelInfo;
        if (Build.VERSION.SDK_INT < 26 || (notificationChannelInfo = getNotificationChannelInfo(str)) == null) {
            return;
        }
        String name = notificationChannelInfo.getName();
        String description = notificationChannelInfo.getDescription();
        NotificationChannel notificationChannel = new NotificationChannel(str, name, notificationChannelInfo.getImportance());
        notificationChannel.setDescription(description);
        ((NotificationManager) AppActivity.getContext().getSystemService("notification")).createNotificationChannel(notificationChannel);
    }

    public static void deleteUnusedChannels() {
        deleteChannel(aa.f.bq);
    }

    private static void deleteChannel(String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) AppActivity.getContext().getSystemService("notification")).deleteNotificationChannel(str);
        }
    }
}
