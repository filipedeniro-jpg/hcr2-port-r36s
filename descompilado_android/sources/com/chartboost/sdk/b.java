package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.impl.s;
import com.facebook.internal.AnalyticsEvents;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    static void a(String str) {
        if (i.d == null) {
            CBLogging.b("CBConfig", "Set a valid CBFramework first");
        } else if (TextUtils.isEmpty(str)) {
            CBLogging.b("CBConfig", "Invalid Version String");
        } else {
            i.b = str;
        }
    }

    public static boolean a(AtomicReference<com.chartboost.sdk.Model.e> atomicReference, JSONObject jSONObject, SharedPreferences sharedPreferences) {
        try {
            atomicReference.set(new com.chartboost.sdk.Model.e(jSONObject));
            return true;
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(b.class, "updateConfig", e);
            return false;
        }
    }

    public static boolean a() {
        return b() && c();
    }

    private static boolean c() {
        h hVarA = h.a();
        if (hVarA == null) {
            return false;
        }
        if (hVarA.q.d != null) {
            return true;
        }
        try {
            throw new Exception("Chartboost Weak Activity reference is null");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean a(Activity activity) {
        if (activity != null) {
            return true;
        }
        try {
            throw new Exception("Invalid activity context: Host Activity object is null, Please send a valid activity object");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean b() {
        try {
            if (h.a() == null) {
                throw new Exception("SDK Initialization error. SDK seems to be not initialized properly, check for any integration issues");
            }
            if (i.m == null) {
                throw new Exception("SDK Initialization error. Activity context seems to be not initialized properly, host activity or application context is being sent as null");
            }
            if (TextUtils.isEmpty(i.k)) {
                throw new Exception("SDK Initialization error. AppId is missing");
            }
            if (TextUtils.isEmpty(i.l)) {
                throw new Exception("SDK Initialization error. AppSignature is missing");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean a(Context context) {
        int iCheckCallingOrSelfPermission;
        int iCheckCallingOrSelfPermission2;
        int iCheckCallingOrSelfPermission3;
        int iCheckCallingOrSelfPermission4;
        int iCheckCallingOrSelfPermission5;
        try {
            if (context == null) {
                throw new RuntimeException("Invalid activity context passed during intitalization");
            }
            if (s.a().a(23)) {
                iCheckCallingOrSelfPermission = context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
                iCheckCallingOrSelfPermission2 = context.checkSelfPermission("android.permission.ACCESS_NETWORK_STATE");
                iCheckCallingOrSelfPermission3 = context.checkSelfPermission("android.permission.INTERNET");
                iCheckCallingOrSelfPermission4 = context.checkSelfPermission("android.permission.READ_PHONE_STATE");
                iCheckCallingOrSelfPermission5 = context.checkSelfPermission("android.permission.ACCESS_WIFI_STATE");
            } else {
                iCheckCallingOrSelfPermission = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
                iCheckCallingOrSelfPermission2 = context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
                iCheckCallingOrSelfPermission3 = context.checkCallingOrSelfPermission("android.permission.INTERNET");
                iCheckCallingOrSelfPermission4 = context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE");
                iCheckCallingOrSelfPermission5 = context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE");
            }
            i.n = iCheckCallingOrSelfPermission != 0;
            i.o = iCheckCallingOrSelfPermission3 != 0;
            i.p = iCheckCallingOrSelfPermission2 != 0;
            i.q = iCheckCallingOrSelfPermission4 != 0;
            i.r = iCheckCallingOrSelfPermission5 != 0;
            if (i.o) {
                throw new RuntimeException("Please add the permission : android.permission.INTERNET in your android manifest.xml");
            }
            if (i.p) {
                throw new RuntimeException("Please add the permission : android.permission.ACCESS_NETWORK_STATE in your android manifest.xml");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean b(Context context) {
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent(context, (Class<?>) CBImpressionActivity.class), 0);
        if (listQueryIntentActivities.isEmpty()) {
            return false;
        }
        ActivityInfo activityInfo = listQueryIntentActivities.get(0).activityInfo;
        return ((activityInfo.flags & 512) == 0 || (activityInfo.flags & 32) == 0 || (activityInfo.configChanges & 128) == 0 || (activityInfo.configChanges & 32) == 0 || (activityInfo.configChanges & 1024) == 0) ? false : true;
    }

    public static String a(com.chartboost.sdk.Model.e eVar) {
        return !eVar.y ? AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE : "web";
    }
}
