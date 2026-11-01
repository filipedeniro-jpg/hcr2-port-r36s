package net.hockeyapp.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import net.hockeyapp.android.utils.PrefsUtil;

/* JADX INFO: loaded from: classes2.dex */
public class Tracking {
    private static final String START_TIME_KEY = "startTime";
    private static final String USAGE_TIME_KEY = "usageTime";

    public static void startUsage(Activity activity) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (activity == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = getPreferences(activity).edit();
        editorEdit.putLong(START_TIME_KEY + activity.hashCode(), jCurrentTimeMillis);
        PrefsUtil.applyChanges(editorEdit);
    }

    public static void stopUsage(Activity activity) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (activity != null && checkVersion(activity)) {
            SharedPreferences preferences = getPreferences(activity);
            long j = preferences.getLong(START_TIME_KEY + activity.hashCode(), 0L);
            long j2 = preferences.getLong(USAGE_TIME_KEY + Constants.APP_VERSION, 0L);
            if (j > 0) {
                SharedPreferences.Editor editorEdit = preferences.edit();
                editorEdit.putLong(USAGE_TIME_KEY + Constants.APP_VERSION, j2 + (jCurrentTimeMillis - j));
                PrefsUtil.applyChanges(editorEdit);
            }
        }
    }

    public static long getUsageTime(Context context) {
        if (!checkVersion(context)) {
            return 0L;
        }
        return getPreferences(context).getLong(USAGE_TIME_KEY + Constants.APP_VERSION, 0L) / 1000;
    }

    private static boolean checkVersion(Context context) {
        if (Constants.APP_VERSION != null) {
            return true;
        }
        Constants.loadFromContext(context);
        return Constants.APP_VERSION != null;
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences("HockeyApp", 0);
    }
}
