package com.applovin.impl.sdk.d;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class h extends c {
    private final com.applovin.impl.a.a c;

    public h(com.applovin.impl.a.a aVar, com.applovin.impl.sdk.h hVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        super("TaskCacheVastAd", aVar, hVar, appLovinAdLoadListener);
        this.c = aVar;
    }

    private void h() {
        String str;
        com.applovin.impl.a.a aVar;
        String str2;
        if (this.c.l()) {
            com.applovin.impl.a.b bVarD = this.c.d();
            if (bVarD != null) {
                com.applovin.impl.a.e eVarB = bVarD.b();
                if (eVarB == null) {
                    d("Failed to retrieve non-video resources from companion ad. Skipping...");
                    return;
                }
                try {
                    Uri uriB = eVarB.b();
                    String string = uriB != null ? uriB.toString() : "";
                    String strC = eVarB.c();
                    if (!URLUtil.isValidUrl(string) && !com.applovin.impl.sdk.utils.k.b(strC)) {
                        c("Companion ad does not have any resources attached. Skipping...");
                        return;
                    }
                    if (eVarB.a() == com.applovin.impl.a.e.a.STATIC) {
                        a("Caching static companion ad at " + string + "...");
                        List<String> listF = this.c.f();
                        Uri uriB2 = b(string, listF, (listF == null || listF.isEmpty()) ? false : true);
                        if (uriB2 == null) {
                            str2 = "Failed to cache static companion ad";
                            d(str2);
                            return;
                        } else {
                            eVarB.a(uriB2);
                            aVar = this.c;
                            aVar.a(true);
                            return;
                        }
                    }
                    if (eVarB.a() != com.applovin.impl.a.e.a.HTML) {
                        if (eVarB.a() == com.applovin.impl.a.e.a.IFRAME) {
                            a("Skip caching of iFrame resource...");
                            return;
                        }
                        return;
                    }
                    if (com.applovin.impl.sdk.utils.k.b(string)) {
                        a("Begin caching HTML companion ad. Fetching from " + string + "...");
                        String strG = g(string);
                        if (!com.applovin.impl.sdk.utils.k.b(strG)) {
                            str2 = "Unable to load companion ad resources from " + string;
                            d(str2);
                            return;
                        }
                        a("HTML fetched. Caching HTML now...");
                        eVarB.a(a(strG, this.c.f(), this.c));
                        aVar = this.c;
                    } else {
                        a("Caching provided HTML for companion ad. No fetch required. HTML: " + strC);
                        eVarB.a(a(strC, this.c.f(), this.c));
                        aVar = this.c;
                    }
                    aVar.a(true);
                    return;
                } catch (Throwable th) {
                    a("Failed to cache companion ad", th);
                    return;
                }
            }
            str = "No companion ad provided. Skipping...";
        } else {
            str = "Companion ad caching disabled. Skipping...";
        }
        a(str);
    }

    private void i() {
        com.applovin.impl.a.k kVarC;
        Uri uriB;
        if (!this.c.aA()) {
            a("Video caching disabled. Skipping...");
            return;
        }
        if (this.c.a() == null || (kVarC = this.c.c()) == null || (uriB = kVarC.b()) == null) {
            return;
        }
        List<String> listF = this.c.f();
        Uri uriA = a(uriB.toString(), listF, (listF == null || listF.isEmpty()) ? false : true);
        if (uriA == null) {
            d("Failed to cache video file: " + kVarC);
            return;
        }
        a("Video file successfully cached into: " + uriA);
        kVarC.a(uriA);
    }

    private void j() {
        String strJ;
        String str;
        if (this.c.k() != null) {
            a("Begin caching HTML template. Fetching from " + this.c.k() + "...");
            strJ = a(this.c.k().toString(), this.c.H());
        } else {
            strJ = this.c.j();
        }
        if (com.applovin.impl.sdk.utils.k.b(strJ)) {
            com.applovin.impl.a.a aVar = this.c;
            aVar.a(a(strJ, aVar.H(), this.c));
            str = "Finish caching HTML template " + this.c.j() + " for ad #" + this.c.getAdIdNumber();
        } else {
            str = "Unable to load HTML template";
        }
        a(str);
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.l;
    }

    @Override // java.lang.Runnable
    public void run() {
        a("Begin caching for VAST ad #" + this.c.getAdIdNumber() + "...");
        b();
        h();
        i();
        j();
        g();
        a("Finished caching VAST ad #" + this.c.getAdIdNumber());
        long jCurrentTimeMillis = System.currentTimeMillis() - this.c.getCreatedAtMillis();
        com.applovin.impl.sdk.c.d.a(this.c, this.b);
        com.applovin.impl.sdk.c.d.a(jCurrentTimeMillis, this.c, this.b);
        a(this.c);
    }
}
