package com.facebook.ads.redexgen.X;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ic, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0590Ic {

    @Nullable
    private static AtomicBoolean B;

    private C0590Ic() {
    }

    public static boolean B(Context appContext) {
        if (AdInternalSettings.sMultiprocessSupportMode == AdSettings.MultiprocessSupportMode.MULTIPROCESS_SUPPORT_MODE_OFF) {
            return false;
        }
        if (B != null) {
            return B.get();
        }
        Context applicationContext = appContext.getApplicationContext();
        if (!(applicationContext instanceof Application)) {
            Log.w(AudienceNetworkAds.TAG, "Multi-process support won't work because application Context is not Application instance.");
            C0585Hx.F(appContext, "ipc", C0584Hw.UB, new C0583Hv("ApplicationContext is not Application.", applicationContext.getClass().getName()));
            B = new AtomicBoolean(false);
            return false;
        }
        if (AdInternalSettings.sMultiprocessSupportMode != AdSettings.MultiprocessSupportMode.MULTIPROCESS_SUPPORT_MODE_ON && !I6.B && !((Application) applicationContext).getClass().equals(Application.class)) {
            Log.e(AudienceNetworkAds.TAG, "You are using custom Application class and don't call AudienceNetworkAds.isInAdsProcess(). Multi-process support will be disabled. Please call AudienceNetworkAds.isInAdsProcess() if you want to support multi-process mode.");
            C0585Hx.F(appContext, "ipc", C0584Hw.VB, new C0583Hv("No AudienceNetworkAds.isInAdsProcess() call.", "Custom Application: " + applicationContext.getClass().getName()));
            B = new AtomicBoolean(false);
            return false;
        }
        B = new AtomicBoolean(true);
        return true;
    }

    public static String C(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            String strB = I6.B(context);
            if (strB == null) {
                strB = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
            jSONObject.put("process_name", strB);
            jSONObject.put("is_ads_process", I6.D);
            jSONObject.put("client_supports", B(context.getApplicationContext()));
        } catch (Exception e) {
            C0585Hx.D(context, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.PB, new C0583Hv(e));
        }
        return jSONObject.toString();
    }
}
