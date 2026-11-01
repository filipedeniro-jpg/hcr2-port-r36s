package com.chukong.cocosplay.client;

import android.app.Activity;

/* JADX INFO: loaded from: classes.dex */
public class CocosPlayClient {
    public static String getGameRoot() {
        return "";
    }

    public static native String[] getSearchPaths();

    public static boolean init(Activity activity, boolean z) {
        return false;
    }

    public static boolean isDemo() {
        return false;
    }

    public static boolean isEnabled() {
        return false;
    }

    public static boolean isNotifyFileLoadedEnabled() {
        return false;
    }

    public static void notifyFileLoaded(String str) {
    }

    public static void updateAssets(String str) {
    }
}
