package com.fingersoft.ads_sdk.advertising;

import android.content.Context;
import android.content.SharedPreferences;
import com.fingersoft.ads_sdk.advertising.utils.AdUtils;

/* JADX INFO: loaded from: classes.dex */
public class AdApplicationSettings {
    static final String SETTINGS_NAME = "APP_SETTINGS";

    public static boolean clearSettings(Context context) {
        AdUtils.log("Clearing settings");
        try {
            return context.getSharedPreferences(SETTINGS_NAME, 0).edit().clear().commit();
        } catch (Exception e) {
            AdUtils.log("Could not clear application settings: " + e.getMessage());
            return false;
        }
    }

    public static void setValue(Context context, String str, String str2) {
        AdUtils.log("Settings setting value: name = " + str + " with values = " + str2);
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SETTINGS_NAME, 0).edit();
            editorEdit.putString(str, str2);
            editorEdit.commit();
        } catch (Exception unused) {
        }
    }

    public static int getValueInt(Context context, String str, int i) {
        try {
            return Integer.parseInt(context.getSharedPreferences(SETTINGS_NAME, 0).getString(str, ""));
        } catch (Exception unused) {
            return i;
        }
    }

    public static String getValueString(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(SETTINGS_NAME, 0).getString(str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }

    public static boolean hasValue(Context context, String str) {
        try {
            return context.getSharedPreferences(SETTINGS_NAME, 0).contains(str);
        } catch (Exception unused) {
            return false;
        }
    }
}
