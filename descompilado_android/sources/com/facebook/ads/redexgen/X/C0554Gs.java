package com.facebook.ads.redexgen.X;

import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gs, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0554Gs {
    private C0554Gs() {
    }

    public static Method B(Class<?> cls, String methodName, Class<?>... clsArr) {
        try {
            return cls.getMethod(methodName, clsArr);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method C(String className, String methodName, Class<?>... clsArr) {
        try {
            Class<?> clazz = Class.forName(className);
            return B(clazz, methodName, clsArr);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Object D(Object receiver, Method method, Object... args) {
        try {
            return method.invoke(receiver, args);
        } catch (Exception e) {
            return null;
        }
    }
}
