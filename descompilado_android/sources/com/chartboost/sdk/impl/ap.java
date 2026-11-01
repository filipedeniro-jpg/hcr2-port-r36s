package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ap {
    final com.chartboost.sdk.Libraries.d a;
    final ai b;
    final AtomicReference<com.chartboost.sdk.Model.e> c;
    final SharedPreferences d;
    final com.chartboost.sdk.Libraries.i e;
    final String f;
    final String g;
    final String h;
    final String i;
    String j;
    String k;
    final String l;
    final Integer m;
    final Integer n;
    final Integer o;
    final Integer p;
    final String q;
    final Float r;
    final String s;
    final String t;
    final String u;
    final JSONObject v;
    final boolean w;
    final String x;
    final Integer y;

    public ap(Context context, String str, com.chartboost.sdk.Libraries.d dVar, ai aiVar, AtomicReference<com.chartboost.sdk.Model.e> atomicReference, SharedPreferences sharedPreferences, com.chartboost.sdk.Libraries.i iVar) {
        JSONObject jSONObject;
        int iWidth;
        WindowManager windowManager;
        String simOperator;
        String strSubstring;
        this.a = dVar;
        this.b = aiVar;
        this.c = atomicReference;
        this.d = sharedPreferences;
        this.e = iVar;
        this.s = str;
        if ("sdk".equals(Build.PRODUCT) || CommonUtils.GOOGLE_SDK.equals(Build.PRODUCT) || (Build.MANUFACTURER != null && Build.MANUFACTURER.contains("Genymotion"))) {
            this.f = "Android Simulator";
        } else {
            this.f = Build.MODEL;
        }
        this.t = Build.MANUFACTURER + " " + Build.MODEL;
        this.u = ar.a(context);
        this.g = "Android " + Build.VERSION.RELEASE;
        this.h = Locale.getDefault().getCountry();
        this.i = Locale.getDefault().getLanguage();
        this.l = "7.3.1";
        this.r = Float.valueOf(context.getResources().getDisplayMetrics().density);
        try {
            String packageName = context.getPackageName();
            this.j = context.getPackageManager().getPackageInfo(packageName, 128).versionName;
            this.k = packageName;
        } catch (Exception e) {
            CBLogging.a("RequestBody", "Exception raised getting package mager object", e);
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        int iHeight = 0;
        if (telephonyManager != null && telephonyManager.getPhoneType() != 0 && telephonyManager.getSimState() == 5) {
            String strSubstring2 = null;
            try {
                simOperator = telephonyManager.getSimOperator();
            } catch (Exception e2) {
                com.chartboost.sdk.Tracking.a.a(Chartboost.class, "Unable to retrieve sim operator information", e2);
                simOperator = null;
            }
            if (simOperator == null || TextUtils.isEmpty(simOperator)) {
                strSubstring = null;
            } else {
                strSubstring2 = simOperator.substring(0, 3);
                strSubstring = simOperator.substring(3);
            }
            jSONObject = com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("carrier-name", telephonyManager.getNetworkOperatorName()), com.chartboost.sdk.Libraries.e.a("mobile-country-code", strSubstring2), com.chartboost.sdk.Libraries.e.a("mobile-network-code", strSubstring), com.chartboost.sdk.Libraries.e.a("iso-country-code", telephonyManager.getNetworkCountryIso()), com.chartboost.sdk.Libraries.e.a("phone-type", Integer.valueOf(telephonyManager.getPhoneType())));
        } else {
            jSONObject = new JSONObject();
        }
        this.v = jSONObject;
        this.w = CBUtility.c();
        this.x = CBUtility.d();
        this.y = ai.d(context);
        try {
            if (context instanceof Activity) {
                Rect rect = new Rect();
                ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                iWidth = rect.width();
                try {
                    iHeight = rect.height();
                } catch (Exception e3) {
                    e = e3;
                    CBLogging.b("RequestBody", "Exception getting activity size", e);
                }
            } else {
                iWidth = 0;
            }
        } catch (Exception e4) {
            e = e4;
            iWidth = 0;
        }
        DisplayMetrics displayMetrics = (DisplayMetrics) com.chartboost.sdk.g.a().a(new DisplayMetrics());
        displayMetrics.setTo(context.getResources().getDisplayMetrics());
        if (Build.VERSION.SDK_INT >= 17 && (windowManager = (WindowManager) context.getSystemService("window")) != null) {
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        }
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        this.o = Integer.valueOf(i);
        this.p = Integer.valueOf(i2);
        this.q = "" + displayMetrics.densityDpi;
        if (iWidth > 0 && iWidth <= i) {
            i = iWidth;
        }
        iHeight = (iHeight <= 0 || iHeight > i2) ? i2 : iHeight;
        this.m = Integer.valueOf(i);
        this.n = Integer.valueOf(iHeight);
    }
}
