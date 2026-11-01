package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.facebook.share.internal.MessengerShareContentUtility;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.File;
import java.lang.reflect.Field;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ib, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0589Ib {
    private static final String B = C0589Ib.class.getSimpleName();
    private static final AtomicBoolean C = new AtomicBoolean();

    private C0589Ib() {
    }

    public static String B(Context context) {
        JSONObject jSONObject = new JSONObject();
        C(jSONObject, "is_emu", String.valueOf(I()));
        C(jSONObject, "apk_size", String.valueOf(D(context)));
        C(jSONObject, "timezone_offset", String.valueOf(G()));
        C(jSONObject, "app_started_reason", String.valueOf(MF.B(context)));
        C(jSONObject, "is_debuggable", String.valueOf(H(context)));
        C(jSONObject, "debug_value", F(context));
        C(jSONObject, "build_type", E(context));
        return jSONObject.toString();
    }

    private static void C(JSONObject obj, String key, String val) {
        try {
            obj.put(key, val);
        } catch (JSONException e) {
            if (BuildConfigApi.isDebug()) {
                String key2 = B;
                Log.e(key2, "Could not add parameter:", e);
            }
        }
    }

    private static long D(Context context) {
        try {
            return new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).publicSourceDir).length();
        } catch (Exception e) {
            if (C.compareAndSet(false, true)) {
                C0585Hx.D(context, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.AB, new C0583Hv(e));
            }
            return -1L;
        }
    }

    private static String E(Context context) {
        try {
            Field buildType = Class.forName(context.getPackageName() + ".BuildConfig").getDeclaredField("BUILD_TYPE");
            return (String) buildType.get(null);
        } catch (Exception e) {
            return "N/A";
        }
    }

    private static String F(Context context) {
        try {
            Field buildType = Class.forName(context.getPackageName() + ".BuildConfig").getDeclaredField("DEBUG");
            return String.valueOf(buildType.getBoolean(null));
        } catch (Exception e) {
            return "N/A";
        }
    }

    private static int G() {
        return TimeZone.getDefault().getRawOffset();
    }

    private static boolean H(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    private static boolean I() {
        return Build.FINGERPRINT.contains(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains(CommonUtils.GOOGLE_SDK) || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) && Build.DEVICE.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE)) || CommonUtils.GOOGLE_SDK.equals(Build.PRODUCT);
    }
}
