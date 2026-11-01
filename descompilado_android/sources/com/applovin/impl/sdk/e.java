package com.applovin.impl.sdk;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class e {
    public static Boolean a(Context context) {
        return a(com.applovin.impl.sdk.b.d.e, context);
    }

    private static Boolean a(com.applovin.impl.sdk.b.d<Boolean> dVar, Context context) {
        return (Boolean) com.applovin.impl.sdk.b.e.b(dVar, (Object) null, context);
    }

    private static boolean a(com.applovin.impl.sdk.b.d<Boolean> dVar, Boolean bool, Context context) {
        Boolean boolA = a(dVar, context);
        com.applovin.impl.sdk.b.e.a(dVar, bool, context);
        return boolA == null || boolA != bool;
    }

    public static boolean a(boolean z, Context context) {
        return a(com.applovin.impl.sdk.b.d.e, Boolean.valueOf(z), context);
    }

    public static Boolean b(Context context) {
        return a(com.applovin.impl.sdk.b.d.f, context);
    }

    public static boolean b(boolean z, Context context) {
        return a(com.applovin.impl.sdk.b.d.f, Boolean.valueOf(z), context);
    }
}
