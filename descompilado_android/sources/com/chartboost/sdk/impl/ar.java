package com.chartboost.sdk.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import com.vungle.warren.network.VungleApiClient;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class ar {
    public static String a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        int i = context.getResources().getConfiguration().uiMode & 15;
        int i2 = context.getResources().getConfiguration().screenLayout & 15;
        if (packageManager.hasSystemFeature("org.chromium.arc.device_management")) {
            return "chromebook";
        }
        if (Build.BRAND != null && Build.BRAND.equals("chromium") && Build.MANUFACTURER.equals("chromium")) {
            return "chromebook";
        }
        if (Build.DEVICE != null && Build.DEVICE.matches(".+_cheets")) {
            return "chromebook";
        }
        if (packageManager.hasSystemFeature("android.hardware.type.watch") || i == 6) {
            return "watch";
        }
        if (packageManager.hasSystemFeature("android.hardware.type.television") || i == 4) {
            return "tv";
        }
        return ((Build.MANUFACTURER == null || !Build.MANUFACTURER.equalsIgnoreCase(VungleApiClient.MANUFACTURER_AMAZON)) && i2 != 4) ? "phone" : "tablet";
    }

    public static String b(Context context) {
        try {
            Object obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get("cb.limit.aid");
            if ((obj instanceof Integer) && ((Integer) obj).intValue() == 1) {
                return null;
            }
        } catch (Exception unused) {
        }
        String string = Settings.Secure.getString(context.getContentResolver(), com.my.target.i.ANDROID_ID);
        String str = "9774d56d682e549c".equals(string) ? null : string;
        return str == null ? c(context) : str;
    }

    private static String c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("cbPrefs", 0);
        if (sharedPreferences == null) {
            return UUID.randomUUID().toString();
        }
        String string = sharedPreferences.getString("cbUUID", null);
        if (string != null) {
            return string;
        }
        String string2 = UUID.randomUUID().toString();
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString("cbUUID", string2);
        editorEdit.apply();
        return string2;
    }
}
