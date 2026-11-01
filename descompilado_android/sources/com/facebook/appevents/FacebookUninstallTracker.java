package com.facebook.appevents;

/* JADX INFO: loaded from: classes.dex */
public class FacebookUninstallTracker {
    @Deprecated
    public static void updateDeviceToken(String str) {
        AppEventsLogger.setPushNotificationsRegistrationId(str);
    }
}
