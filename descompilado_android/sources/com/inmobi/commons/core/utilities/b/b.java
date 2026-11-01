package com.inmobi.commons.core.utilities.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import androidx.annotation.NonNull;
import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: DeviceInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {
    @SuppressLint({"MissingPermission", "NewApi"})
    private static String b() {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null || !com.inmobi.commons.core.utilities.e.a(contextB, "root", "android.permission.ACCESS_NETWORK_STATE") || (connectivityManager = (ConnectivityManager) contextB.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return "";
        }
        if (Build.VERSION.SDK_INT < 28) {
            int type = activeNetworkInfo.getType();
            int subtype = activeNetworkInfo.getSubtype();
            if (type == 0) {
                return type + "|" + subtype;
            }
            if (type != 1) {
                return Integer.toString(type);
            }
        } else {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities.hasTransport(0)) {
                return "0|" + activeNetworkInfo.getSubtype();
            }
            if (!networkCapabilities.hasTransport(1)) {
                if (networkCapabilities.hasTransport(2)) {
                    return "7";
                }
                if (networkCapabilities.hasTransport(3)) {
                    return "9";
                }
                if (networkCapabilities.hasTransport(4)) {
                    return "17";
                }
                if (networkCapabilities.hasTransport(5)) {
                    return "10";
                }
                return networkCapabilities.hasTransport(6) ? "11" : "8";
            }
        }
        return "1";
    }

    public static Map<String, String> a(boolean z) {
        int streamVolume;
        HashMap map = new HashMap();
        try {
            map.put("d-brand-name", Build.BRAND);
            map.put("d-manufacturer-name", Build.MANUFACTURER);
            map.put("d-model-name", Build.MODEL);
            map.put("d-nettype-raw", b());
            map.put("d-localization", Locale.getDefault().toString());
            Context contextB = com.inmobi.commons.a.a.b();
            if (contextB == null || z) {
                streamVolume = 0;
            } else {
                AudioManager audioManager = (AudioManager) contextB.getSystemService("audio");
                streamVolume = (audioManager.getStreamVolume(3) * 100) / audioManager.getStreamMaxVolume(3);
            }
            map.put("d-media-volume", String.valueOf(streamVolume));
        } catch (Exception e) {
            b.class.getSimpleName();
            new StringBuilder("SDK encountered unexpected error in getting device info; ").append(e.getMessage());
        }
        return map;
    }

    public static int a() {
        String strB = b();
        if (strB.startsWith(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            return 0;
        }
        return strB.startsWith("1") ? 1 : 2;
    }

    public static int a(@NonNull Context context) {
        return ((AudioManager) context.getSystemService("audio")).getStreamVolume(3);
    }
}
