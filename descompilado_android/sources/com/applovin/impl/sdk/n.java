package com.applovin.impl.sdk;

import android.util.Log;
import com.ironsource.sdk.constants.Constants;

/* JADX INFO: loaded from: classes.dex */
public class n {
    private final h a;

    n(h hVar) {
        this.a = hVar;
    }

    private boolean a() {
        return this.a.y().d();
    }

    public void a(String str, Boolean bool, String str2) {
        a(str, bool, str2, null);
    }

    public void a(String str, Boolean bool, String str2, Throwable th) {
        if (a()) {
            Log.e("AppLovinSdk", Constants.RequestParameters.LEFT_BRACKETS + str + "] " + str2, th);
        }
        if (bool.booleanValue() && ((Boolean) this.a.a(com.applovin.impl.sdk.b.b.eH)).booleanValue() && this.a.K() != null) {
            this.a.K().a(str2, th);
        }
    }

    public void a(String str, String str2) {
        if (a()) {
            Log.d("AppLovinSdk", Constants.RequestParameters.LEFT_BRACKETS + str + "] " + str2);
        }
    }

    public void a(String str, String str2, Throwable th) {
        if (a()) {
            Log.w("AppLovinSdk", Constants.RequestParameters.LEFT_BRACKETS + str + "] " + str2, th);
        }
    }

    public void b(String str, String str2) {
        if (a()) {
            Log.i("AppLovinSdk", Constants.RequestParameters.LEFT_BRACKETS + str + "] " + str2);
        }
    }

    public void b(String str, String str2, Throwable th) {
        a(str, true, str2, th);
    }

    public void c(String str, String str2) {
        a(str, str2, (Throwable) null);
    }

    public void c(String str, String str2, Throwable th) {
        Log.e("AppLovinSdk", Constants.RequestParameters.LEFT_BRACKETS + str + "] " + str2, th);
    }

    public void d(String str, String str2) {
        b(str, str2, null);
    }

    public void e(String str, String str2) {
        c(str, str2, null);
    }
}
