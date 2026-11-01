package com.facebook.ads.redexgen.X;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.annotation.AnyThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: assets/audience_network.dex */
@AnyThread
public final class I6 {
    public static boolean B;

    @Nullable
    private static String C;
    public static boolean D = false;

    private I6() {
    }

    @Nullable
    public static String B(Context context) {
        if (C != null) {
            return C;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            return D();
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof Application) {
            C = E((Application) applicationContext);
            return C;
        }
        return null;
    }

    public static String C(String originalName, Context context) {
        String packageName = context.getPackageName();
        String strB = B(context);
        if (!TextUtils.isEmpty(strB) && !packageName.equals(strB)) {
            if (strB.contains(":")) {
                strB = strB.split(":")[1];
            }
            String packageName2 = originalName + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + strB;
            return packageName2;
        }
        return originalName;
    }

    @Nullable
    private static String D() {
        try {
            Method getProcessName = Application.class.getMethod("getProcessName", (Class[]) null);
            return (String) getProcessName.invoke(null, (Object[]) null);
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    private static String E(Application app) {
        try {
            Field field = app.getClass().getField("mLoadedApk");
            field.setAccessible(true);
            Object obj = field.get(app);
            Field declaredField = obj.getClass().getDeclaredField("mActivityThread");
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            return (String) obj2.getClass().getDeclaredMethod("getProcessName", (Class[]) null).invoke(obj2, (Object[]) null);
        } catch (Exception e) {
            return null;
        }
    }
}
