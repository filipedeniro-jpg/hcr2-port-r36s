package com.adcolony.sdk;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AdColonyInterstitial {
    public static final int ADCOLONY_IAP_ENGAGEMENT_END_CARD = 0;
    public static final int ADCOLONY_IAP_ENGAGEMENT_OVERLAY = 1;
    private AdColonyInterstitialListener a;
    private c b;
    private AdColonyAdOptions c;
    private g d;
    private int e;
    private String f;
    private String g;
    private String h;
    private int i;
    private String j;
    private boolean k;
    private boolean l;
    private boolean m;

    AdColonyInterstitial(String str, AdColonyInterstitialListener adColonyInterstitialListener, String str2) {
        this.a = adColonyInterstitialListener;
        this.j = str2;
        this.f = str;
    }

    public boolean show() {
        if (!a.b()) {
            return false;
        }
        l lVarA = a.a();
        if (this.l) {
            new aa.a().a("This ad object has already been shown. Please request a new ad ").a("via AdColony.requestInterstitial.").a(aa.e);
            return false;
        }
        if (this.k) {
            new aa.a().a("This ad object has expired. Please request a new ad via AdColony").a(".requestInterstitial.").a(aa.e);
            return false;
        }
        if (lVarA.w()) {
            new aa.a().a("Can not show ad while an interstitial is already active.").a(aa.e);
            return false;
        }
        if (a(lVarA.f().get(this.j))) {
            new aa.a().a("Skipping show()").a(aa.d);
            return false;
        }
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "zone_id", this.j);
        y.b(jSONObjectA, "type", 0);
        y.a(jSONObjectA, "id", this.f);
        AdColonyAdOptions adColonyAdOptions = this.c;
        if (adColonyAdOptions != null) {
            y.a(jSONObjectA, "pre_popup", adColonyAdOptions.a);
            y.a(jSONObjectA, "post_popup", this.c.b);
        }
        AdColonyZone adColonyZone = lVarA.f().get(this.j);
        if (adColonyZone != null && adColonyZone.isRewarded() && lVarA.i() == null) {
            new aa.a().a("Rewarded ad: show() called with no reward listener set.").a(aa.e);
        }
        new af("AdSession.launch_ad_unit", 1, jSONObjectA).b();
        return true;
    }

    boolean a(AdColonyZone adColonyZone) {
        if (adColonyZone != null) {
            if (adColonyZone.getPlayFrequency() <= 1) {
                return false;
            }
            if (adColonyZone.b() == 0) {
                adColonyZone.a(adColonyZone.getPlayFrequency() - 1);
                return false;
            }
            adColonyZone.a(adColonyZone.b() - 1);
        }
        return true;
    }

    public boolean cancel() {
        if (this.b == null) {
            return false;
        }
        Activity activityC = a.c();
        if (activityC != null && !(activityC instanceof AdColonyInterstitialActivity)) {
            return false;
        }
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "id", this.b.b());
        new af("AdSession.on_request_close", this.b.c(), jSONObjectA).b();
        return true;
    }

    public AdColonyInterstitialListener getListener() {
        return this.a;
    }

    public void setListener(@NonNull AdColonyInterstitialListener adColonyInterstitialListener) {
        this.a = adColonyInterstitialListener;
    }

    public String getZoneID() {
        return this.j;
    }

    public boolean isExpired() {
        return this.k || this.l;
    }

    public boolean destroy() {
        a.a().m().c().remove(this.f);
        return true;
    }

    boolean a() {
        Activity activityC = a.c();
        if (activityC == null || !a.b()) {
            return false;
        }
        a.a().c(true);
        a.a().a(this.b);
        a.a().a(this);
        new aa.a().a("Launching fullscreen Activity via AdColonyInterstitial's launch ").a("method.").a(aa.b);
        activityC.startActivity(new Intent(activityC, (Class<?>) AdColonyInterstitialActivity.class));
        this.l = true;
        return true;
    }

    void a(JSONObject jSONObject) {
        if (jSONObject.length() > 0) {
            this.d = new g(jSONObject);
        }
    }

    void a(c cVar) {
        this.b = cVar;
    }

    void a(boolean z) {
        this.k = z;
    }

    void a(AdColonyAdOptions adColonyAdOptions) {
        this.c = adColonyAdOptions;
    }

    void a(int i) {
        this.i = i;
    }

    void b(int i) {
        this.e = i;
    }

    String b() {
        String str = this.g;
        return str == null ? "" : str;
    }

    void a(String str) {
        this.g = str;
    }

    String c() {
        String str = this.h;
        return str == null ? "" : str;
    }

    void b(String str) {
        this.h = str;
    }

    c d() {
        return this.b;
    }

    int e() {
        return this.e;
    }

    String f() {
        return this.f;
    }

    void b(boolean z) {
        this.m = z;
    }

    boolean g() {
        return this.d != null;
    }

    g h() {
        return this.d;
    }

    boolean i() {
        return this.m;
    }
}
