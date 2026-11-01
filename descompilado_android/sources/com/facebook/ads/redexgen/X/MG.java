package com.facebook.ads.redexgen.X;

import android.app.Activity;
import android.support.annotation.Nullable;
import com.ironsource.sdk.constants.Constants;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class MG {
    private MG() {
    }

    @Nullable
    public static Activity B() {
        try {
            return C();
        } catch (Exception e) {
            return null;
        }
    }

    private static Activity C() throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        Class<?> cls = Class.forName("android.app.ActivityThread");
        Object activityThread = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
        Field declaredField = cls.getDeclaredField("mActivities");
        declaredField.setAccessible(true);
        Map map = (Map) declaredField.get(activityThread);
        if (map == null) {
            return null;
        }
        for (Object obj : map.values()) {
            Class<?> cls2 = obj.getClass();
            Field declaredField2 = cls2.getDeclaredField(Constants.ParametersKeys.VIDEO_STATUS_PAUSED);
            declaredField2.setAccessible(true);
            if (!declaredField2.getBoolean(obj)) {
                Field activitiesField = cls2.getDeclaredField("activity");
                activitiesField.setAccessible(true);
                return (Activity) activitiesField.get(obj);
            }
        }
        return null;
    }
}
