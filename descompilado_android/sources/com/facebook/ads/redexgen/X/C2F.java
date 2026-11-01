package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.facebook.ads.AdSettings;
import com.facebook.ads.internal.settings.AdInternalSettings;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2F, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C2F {
    private C2F() {
    }

    public static AdSettings.IntegrationErrorMode B(Context appContext) {
        AdSettings.IntegrationErrorMode integrationErrorMode = (AdSettings.IntegrationErrorMode) AdInternalSettings.sSettingsBundle.getSerializable(AdInternalSettings.SRL_INTEGRATION_ERROR_MODE_KEY);
        if (integrationErrorMode == null) {
            integrationErrorMode = AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE;
        }
        if (integrationErrorMode == AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE && !C(appContext)) {
            return AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CALLBACK_MODE;
        }
        return integrationErrorMode;
    }

    public static boolean C(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    @WorkerThread
    @Nullable
    public static KQ D(Context mAppContext, Integer... depsList) {
        return null;
    }
}
