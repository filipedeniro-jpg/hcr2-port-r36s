package com.fingersoft.hcr2;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.media.session.PlaybackStateCompat;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    private static final String TAG = "hcr2 - Utils";

    public void startMemTracking(final Context context) {
        new Thread() { // from class: com.fingersoft.hcr2.Utils.1
            /* JADX INFO: Infinite loop detected, blocks: 8, insns: 0 */
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
                        android.util.Log.d(Utils.TAG, "Available memory: " + (memoryInfo.availMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) + " (Used: " + Utils.getUsedMemorySize() + ")");
                        Thread.sleep(500L);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static long getUsedMemorySize() {
        try {
            Runtime runtime = Runtime.getRuntime();
            return runtime.totalMemory() - runtime.freeMemory();
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static boolean isFirstVersionStartup(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("utils", 0);
        int i = sharedPreferences.getInt("startupVersion", 0);
        android.util.Log.d(TAG, "Config startup version: " + i);
        android.util.Log.d(TAG, "Build version: 445");
        if (445 <= i) {
            return false;
        }
        sharedPreferences.edit().putInt("startupVersion", BuildConfig.VERSION_CODE).apply();
        return true;
    }
}
