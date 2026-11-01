package net.hockeyapp.android.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes2.dex */
public class VersionCache {
    private static String VERSION_INFO_KEY = "versionInfo";

    public static void setVersionInfo(Context context, String str) {
        if (context != null) {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("HockeyApp", 0).edit();
            editorEdit.putString(VERSION_INFO_KEY, str);
            PrefsUtil.applyChanges(editorEdit);
        }
    }

    public static String getVersionInfo(Context context) {
        return context != null ? context.getSharedPreferences("HockeyApp", 0).getString(VERSION_INFO_KEY, "[]") : "[]";
    }
}
