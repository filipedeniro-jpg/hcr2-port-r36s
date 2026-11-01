package com.moat.analytics.mobile.inm;

import android.util.Log;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
class p {
    p() {
    }

    static String a(View view) {
        if (view == null) {
            return "null";
        }
        return view.getClass().getSimpleName() + "@" + view.hashCode();
    }

    private static String a(String str) {
        return "Moat" + str;
    }

    static void a(int i, String str, Object obj, String str2) {
        if (w.a().b) {
            if (obj == null) {
                Log.println(i, a(str), String.format("message = %s", str2));
            } else {
                Log.println(i, a(str), String.format("id = %s, message = %s", Integer.valueOf(obj.hashCode()), str2));
            }
        }
    }

    static void a(String str, int i, String str2, Object obj, String str3) {
        a(i, str2, obj, str3);
        a(str, str3);
    }

    static void a(String str, Object obj, String str2, Throwable th) {
        if (w.a().b) {
            Log.e(a(str), String.format("id = %s, message = %s", Integer.valueOf(obj.hashCode()), str2), th);
        }
    }

    static void a(String str, String str2) {
        if (w.a().b || !((k) MoatAnalytics.getInstance()).a) {
            return;
        }
        Log.println(str.equals("[ERROR] ") ? 6 : 2, "MoatAnalytics", str + str2);
    }

    static void b(int i, String str, Object obj, String str2) {
        if (w.a().c) {
            String strA = a(str);
            Object[] objArr = new Object[2];
            objArr[0] = obj == null ? "null" : Integer.valueOf(obj.hashCode());
            objArr[1] = str2;
            Log.println(i, strA, String.format("id = %s, message = %s", objArr));
        }
    }
}
