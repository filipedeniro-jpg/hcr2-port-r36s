package com.facebook.ads.redexgen.X;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class MF {
    private MF() {
    }

    public static ME B(Context context) {
        ME meD;
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                meD = C(context);
            } else {
                meD = D();
            }
            return meD;
        } catch (Exception e) {
            return ME.GENERIC_ERROR;
        }
    }

    @RequiresApi(api = 21)
    private static ME C(Context context) {
        if (context == null) {
            return ME.NULL_CONTEXT;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return ME.NO_ACTIVITY_SERVICE;
        }
        List<ActivityManager.AppTask> appTasks = activityManager.getAppTasks();
        if (appTasks.isEmpty()) {
            return ME.NO_RUNNING_TASKS;
        }
        for (ActivityManager.AppTask task : appTasks) {
            ActivityManager.RecentTaskInfo taskInfo = task.getTaskInfo();
            if (taskInfo != null && taskInfo.baseIntent != null && taskInfo.baseIntent.getCategories() != null && taskInfo.baseIntent.getCategories().contains("android.intent.category.LAUNCHER")) {
                return ME.LAUNCHER_FOUND_API21;
            }
        }
        return ME.NO_LAUNCHER_FOUND_API21;
    }

    private static ME D() throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        Class<?> cls = Class.forName("android.app.ActivityThread");
        Object objInvoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
        Field declaredField = cls.getDeclaredField("mActivities");
        declaredField.setAccessible(true);
        Object activityThread = declaredField.get(objInvoke);
        Map map = (Map) activityThread;
        if (map == null) {
            return ME.NULL_ACTIVITIES_REFLECTION;
        }
        for (Object obj : map.values()) {
            Field declaredField2 = obj.getClass().getDeclaredField("activity");
            declaredField2.setAccessible(true);
            Activity activity = (Activity) declaredField2.get(obj);
            if (activity.isTaskRoot() && activity.getIntent().getCategories().contains("android.intent.category.LAUNCHER")) {
                return ME.LAUNCHER_FOUND_REFLECTION;
            }
        }
        return ME.NO_LAUNCHER_FOUND_REFLECTION;
    }
}
