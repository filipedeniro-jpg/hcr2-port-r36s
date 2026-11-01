package com.fingersoft.liveops_sdk.settings;

import android.content.Context;
import android.content.SharedPreferences;
import com.fingersoft.liveops_sdk.utils.CommonUtils;

/* JADX INFO: loaded from: classes.dex */
public class ApplicationSettings {
    static final String SETTINGS_NAME = "APP_SETTINGS";

    public static boolean clearSettings(Context context) {
        CommonUtils.log("Clearing settings");
        try {
            return context.getSharedPreferences(SETTINGS_NAME, 0).edit().clear().commit();
        } catch (Exception e) {
            CommonUtils.log("Could not clear application settings: " + e.getMessage());
            return false;
        }
    }

    public static void setValue(Context context, String str, String str2) {
        CommonUtils.log("Settings setting value: name = " + str + " with values = " + str2);
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SETTINGS_NAME, 0).edit();
            editorEdit.putString(str, str2);
            editorEdit.commit();
        } catch (Exception unused) {
        }
    }

    public static int getValueInt(Context context, String str, int i) {
        try {
            return Integer.parseInt(context.getSharedPreferences(SETTINGS_NAME, 0).getString(str, Integer.toString(i)));
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
