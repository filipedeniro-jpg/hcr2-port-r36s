package com.applovin.sdk;

import android.content.Context;
import com.applovin.impl.sdk.e;

/* JADX INFO: loaded from: classes.dex */
public class AppLovinPrivacySettings {
    public static boolean hasUserConsent(Context context) {
        Boolean boolA = e.a(context);
        if (boolA != null) {
            return boolA.booleanValue();
        }
        return false;
    }

    public static boolean isAgeRestrictedUser(Context context) {
        Boolean boolB = e.b(context);
        if (boolB != null) {
            return boolB.booleanValue();
        }
        return false;
    }

    public static void setHasUserConsent(boolean z, Context context) {
        if (e.a(z, context)) {
            AppLovinSdk.reinitializeAll(Boolean.valueOf(z));
        }
    }

    public static void setIsAgeRestrictedUser(boolean z, Context context) {
        if (e.b(z, context)) {
            AppLovinSdk.reinitializeAll();
        }
    }
}
