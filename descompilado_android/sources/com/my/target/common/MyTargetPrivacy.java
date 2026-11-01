package com.my.target.common;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class MyTargetPrivacy {
    private static boolean userAgeRestricted;

    @Nullable
    private static Boolean userConsent;

    public static boolean isUserConsent() {
        Boolean bool = userConsent;
        return bool == null || bool.booleanValue();
    }

    public static boolean isConsentSpecified() {
        return userConsent != null;
    }

    public static void setUserConsent(boolean z) {
        userConsent = Boolean.valueOf(z);
    }

    public static boolean isUserAgeRestricted() {
        return userAgeRestricted;
    }

    public static void setUserAgeRestricted(boolean z) {
        userAgeRestricted = z;
    }
}
