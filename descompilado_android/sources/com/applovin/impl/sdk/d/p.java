package com.applovin.impl.sdk.d;

import android.app.Activity;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.facebook.internal.AnalyticsEvents;
import com.ironsource.sdk.constants.Constants;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class p extends a {
    private final com.applovin.impl.sdk.h a;

    public p(com.applovin.impl.sdk.h hVar) {
        super("TaskInitializeSdk", hVar);
        this.a = hVar;
    }

    private void a(com.applovin.impl.sdk.b.b<Boolean> bVar) {
        if (((Boolean) this.a.a(bVar)).booleanValue()) {
            this.a.O().f(com.applovin.impl.sdk.ad.d.a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, this.a));
        }
    }

    private boolean b() {
        if (com.applovin.impl.sdk.i.a("android.permission.INTERNET", e())) {
            return true;
        }
        c().w().e(d(), "Unable to enable AppLovin SDK: no android.permission.INTERNET");
        return false;
    }

    private void g() {
        b bVar = new b(this.a);
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.dN)).booleanValue()) {
            this.a.F().a(bVar);
        } else {
            this.a.F().a(bVar, q.a.MAIN);
        }
    }

    private void h() {
        this.a.O().a();
        this.a.P().a();
    }

    private void i() {
        j();
        k();
        l();
    }

    private void j() {
        LinkedHashSet<com.applovin.impl.sdk.ad.d> linkedHashSetB = this.a.R().b();
        if (linkedHashSetB.isEmpty()) {
            return;
        }
        a("Scheduling preload(s) for " + linkedHashSetB.size() + " zone(s)");
        for (com.applovin.impl.sdk.ad.d dVar : linkedHashSetB) {
            if (dVar.d()) {
                this.a.q().preloadAds(dVar);
            } else {
                this.a.p().preloadAds(dVar);
            }
        }
    }

    private void k() {
        com.applovin.impl.sdk.b.b<Boolean> bVar = com.applovin.impl.sdk.b.b.aW;
        String str = (String) this.a.a(com.applovin.impl.sdk.b.b.aV);
        boolean z = false;
        if (str.length() > 0) {
            Iterator<String> it = com.applovin.impl.sdk.utils.d.a(str).iterator();
            while (it.hasNext()) {
                AppLovinAdSize appLovinAdSizeFromString = AppLovinAdSize.fromString(it.next());
                if (appLovinAdSizeFromString != null) {
                    this.a.O().f(com.applovin.impl.sdk.ad.d.a(appLovinAdSizeFromString, AppLovinAdType.REGULAR, this.a));
                    if (AppLovinAdSize.INTERSTITIAL.getLabel().equals(appLovinAdSizeFromString.getLabel())) {
                        a(bVar);
                        z = true;
                    }
                }
            }
        }
        if (z) {
            return;
        }
        a(bVar);
    }

    private void l() {
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.aX)).booleanValue()) {
            this.a.P().f(com.applovin.impl.sdk.ad.d.h(this.a));
        }
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.a;
    }

    @Override // java.lang.Runnable
    public void run() {
        StringBuilder sb;
        String str = AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED;
        long jCurrentTimeMillis = System.currentTimeMillis();
        a("Initializing AppLovin SDK 9.4.2...");
        try {
            if (b()) {
                this.a.G().d();
                this.a.G().c(com.applovin.impl.sdk.c.g.b);
                this.a.Q().c(e());
                this.a.Q().b(e());
                h();
                i();
                if (this.a.B() != null) {
                    Activity activityB = this.a.B();
                    this.a.a(activityB).maybeInitialize(activityB);
                }
                this.a.S().a();
                this.a.i();
                g();
                this.a.J().e();
                this.a.a(true);
                this.a.I().a();
                this.a.r().trackEvent("landing");
                if (this.a.x().a()) {
                    this.a.x().b();
                }
            } else {
                this.a.a(false);
                c().w().e(d(), "Couldn't initialize the AppLovin SDK due to missing INTERNET permission");
            }
            this.a.e();
            if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.ar)).booleanValue()) {
                this.a.a(((Long) this.a.a(com.applovin.impl.sdk.b.b.as)).longValue());
            }
            sb = new StringBuilder();
            sb.append("AppLovin SDK 9.4.2 initialization ");
            if (!this.a.d()) {
                str = Constants.ParametersKeys.FAILED;
            }
        } catch (Throwable th) {
            a("Unable to initialize SDK.", th);
            this.a.a(false);
            this.a.H().a(a());
            this.a.e();
            if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.ar)).booleanValue()) {
                this.a.a(((Long) this.a.a(com.applovin.impl.sdk.b.b.as)).longValue());
            }
            sb = new StringBuilder();
            sb.append("AppLovin SDK 9.4.2 initialization ");
            if (!this.a.d()) {
            }
        } finally {
            this.a.e();
            if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.ar)).booleanValue()) {
                this.a.a(((Long) this.a.a(com.applovin.impl.sdk.b.b.as)).longValue());
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("AppLovin SDK 9.4.2 initialization ");
            if (!this.a.d()) {
                str = Constants.ParametersKeys.FAILED;
            }
            sb2.append(str);
            sb2.append(" in ");
            sb2.append(System.currentTimeMillis() - jCurrentTimeMillis);
            sb2.append("ms");
            a(sb2.toString());
        }
        sb.append(str);
        sb.append(" in ");
        sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
        sb.append("ms");
        String string = sb.toString();
    }
}
