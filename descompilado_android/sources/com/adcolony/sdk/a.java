package com.adcolony.sdk;

import android.app.Activity;
import com.vungle.warren.model.Cookie;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class a {
    static boolean a;
    static boolean b;
    private static WeakReference<Activity> c;
    private static l d;

    a() {
    }

    static void a(final Activity activity, AdColonyAppOptions adColonyAppOptions, boolean z) {
        a(activity);
        b = true;
        l lVar = d;
        if (lVar == null) {
            d = new l();
            d.a(adColonyAppOptions, z);
        } else {
            lVar.a(adColonyAppOptions);
        }
        aw.b.execute(new Runnable() { // from class: com.adcolony.sdk.a.1
            @Override // java.lang.Runnable
            public void run() {
                a.d.a(activity, (af) null);
            }
        });
        new aa.a().a("Configuring AdColony").a(aa.c);
        d.b(false);
        d.l().d(true);
        d.l().e(true);
        d.l().f(false);
        l lVar2 = d;
        lVar2.f = true;
        lVar2.l().a(false);
    }

    static l a() {
        if (!b()) {
            Activity activityC = c();
            if (activityC != null) {
                d = new l();
                JSONObject jSONObjectC = y.c(activityC.getFilesDir().getAbsolutePath() + "/adc3/AppInfo");
                d.a(new AdColonyAppOptions().a(y.b(jSONObjectC, Cookie.APP_ID)).a(y.a(y.g(jSONObjectC, "zoneIds"))), false);
            } else {
                return new l();
            }
        }
        return d;
    }

    static boolean b() {
        return d != null;
    }

    static void a(Activity activity) {
        if (activity == null) {
            c.clear();
        } else {
            c = new WeakReference<>(activity);
        }
    }

    static Activity c() {
        WeakReference<Activity> weakReference = c;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    static boolean d() {
        WeakReference<Activity> weakReference = c;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    static boolean e() {
        return a;
    }

    static void a(String str, ah ahVar) {
        a().q().a(str, ahVar);
    }

    static ah a(String str, ah ahVar, boolean z) {
        a().q().a(str, ahVar);
        return ahVar;
    }

    static void b(String str, ah ahVar) {
        a().q().b(str, ahVar);
    }

    static void f() {
        a().q().b();
    }

    static void a(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = y.a();
        }
        y.a(jSONObject, "m_type", str);
        a().q().a(jSONObject);
    }

    static void a(String str) {
        try {
            af afVar = new af("CustomMessage.send", 0);
            afVar.c().put("message", str);
            afVar.b();
        } catch (JSONException e) {
            new aa.a().a("JSON error from ADC.java's send_custom_message(): ").a(e.toString()).a(aa.h);
        }
    }
}
