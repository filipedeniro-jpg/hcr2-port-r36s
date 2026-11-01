package com.applovin.impl.sdk.d;

import android.net.Uri;
import com.applovin.sdk.AppLovinAdLoadListener;

/* JADX INFO: loaded from: classes.dex */
public class d extends c {
    private final com.applovin.impl.sdk.ad.a c;
    private boolean d;
    private boolean e;

    public d(com.applovin.impl.sdk.ad.a aVar, com.applovin.impl.sdk.h hVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        super("TaskCacheAppLovinAd", aVar, hVar, appLovinAdLoadListener);
        this.c = aVar;
    }

    private void h() {
        a("Caching HTML resources...");
        this.c.a(a(this.c.a(), this.c.H(), this.c));
        this.c.a(true);
        a("Finish caching non-video resources for ad #" + this.c.getAdIdNumber());
        a("Ad updated with cachedHTML = " + this.c.a());
    }

    private void i() {
        Uri uriE = e(this.c.f());
        if (uriE != null) {
            this.c.c();
            this.c.a(uriE);
        }
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.i;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void b(boolean z) {
        this.e = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean zB = this.c.b();
        boolean z = this.e;
        if (zB || z) {
            a("Begin caching for streaming ad #" + this.c.getAdIdNumber() + "...");
            b();
            if (zB) {
                if (this.d) {
                    g();
                }
                h();
                if (!this.d) {
                    g();
                }
                i();
            } else {
                g();
                h();
            }
        } else {
            a("Begin processing for non-streaming ad #" + this.c.getAdIdNumber() + "...");
            b();
            h();
            i();
            g();
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - this.c.getCreatedAtMillis();
        com.applovin.impl.sdk.c.d.a(this.c, this.b);
        com.applovin.impl.sdk.c.d.a(jCurrentTimeMillis, this.c, this.b);
        a(this.c);
    }
}
