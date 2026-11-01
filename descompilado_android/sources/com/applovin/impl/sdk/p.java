package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
abstract class p implements k, AppLovinNativeAdLoadListener {
    protected final h a;
    protected final n b;
    private final Object c = new Object();
    private final Map<com.applovin.impl.sdk.ad.d, q> d = new HashMap();
    private final Map<com.applovin.impl.sdk.ad.d, q> e = new HashMap();
    private final Map<com.applovin.impl.sdk.ad.d, Object> f = new HashMap();
    private final Set<com.applovin.impl.sdk.ad.d> g = new HashSet();

    p(h hVar) {
        this.a = hVar;
        this.b = hVar.w();
    }

    private void b(final com.applovin.impl.sdk.ad.d dVar, Object obj) {
        synchronized (this.c) {
            if (this.f.containsKey(dVar)) {
                this.b.c("PreloadManager", "Possibly missing prior registered preload callback.");
            }
            this.f.put(dVar, obj);
        }
        final int iIntValue = ((Integer) this.a.a(com.applovin.impl.sdk.b.b.aT)).intValue();
        if (iIntValue > 0) {
            AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() { // from class: com.applovin.impl.sdk.p.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (p.this.c) {
                        Object obj2 = p.this.f.get(dVar);
                        if (obj2 != null) {
                            p.this.f.remove(dVar);
                            p.this.b.d("PreloadManager", "Load callback for zone " + dVar + " timed out after " + iIntValue + " seconds");
                            p.this.a(obj2, dVar, -102);
                        }
                    }
                }
            }, TimeUnit.SECONDS.toMillis(iIntValue));
        }
    }

    private void c(com.applovin.impl.sdk.ad.j jVar) {
        i(a(jVar));
    }

    private q j(com.applovin.impl.sdk.ad.d dVar) {
        return this.d.get(dVar);
    }

    private q k(com.applovin.impl.sdk.ad.d dVar) {
        return this.e.get(dVar);
    }

    private boolean l(com.applovin.impl.sdk.ad.d dVar) {
        boolean z;
        synchronized (this.c) {
            q qVarJ = j(dVar);
            z = qVarJ != null && qVarJ.c();
        }
        return z;
    }

    private q m(com.applovin.impl.sdk.ad.d dVar) {
        synchronized (this.c) {
            q qVarK = k(dVar);
            if (qVarK != null && qVarK.a() > 0) {
                return qVarK;
            }
            return j(dVar);
        }
    }

    private boolean n(com.applovin.impl.sdk.ad.d dVar) {
        boolean zContains;
        synchronized (this.c) {
            zContains = this.g.contains(dVar);
        }
        return zContains;
    }

    abstract com.applovin.impl.sdk.ad.d a(com.applovin.impl.sdk.ad.j jVar);

    abstract com.applovin.impl.sdk.d.a a(com.applovin.impl.sdk.ad.d dVar);

    abstract void a(Object obj, com.applovin.impl.sdk.ad.d dVar, int i);

    abstract void a(Object obj, com.applovin.impl.sdk.ad.j jVar);

    public void a(LinkedHashSet<com.applovin.impl.sdk.ad.d> linkedHashSet) {
        Map<com.applovin.impl.sdk.ad.d, Object> map = this.f;
        if (map == null || map.isEmpty()) {
            return;
        }
        synchronized (this.c) {
            Iterator<com.applovin.impl.sdk.ad.d> it = this.f.keySet().iterator();
            while (it.hasNext()) {
                com.applovin.impl.sdk.ad.d next = it.next();
                if (!next.j() && !linkedHashSet.contains(next)) {
                    Object obj = this.f.get(next);
                    it.remove();
                    this.b.e("AppLovinAdService", "Failed to load ad for zone (" + next.a() + "). Please check that the zone has been added to your AppLovin account and given at least 30 minutes to fully propagate.");
                    a(obj, next, -7);
                }
            }
        }
    }

    public boolean a(com.applovin.impl.sdk.ad.d dVar, Object obj) {
        boolean z;
        synchronized (this.c) {
            if (n(dVar)) {
                z = false;
            } else {
                b(dVar, obj);
                z = true;
            }
        }
        return z;
    }

    public void b(com.applovin.impl.sdk.ad.d dVar, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            i(dVar);
        }
    }

    void b(com.applovin.impl.sdk.ad.j jVar) {
        Object obj;
        n nVar;
        String str;
        String str2;
        com.applovin.impl.sdk.ad.d dVarA = a(jVar);
        boolean zI = dVarA.i();
        synchronized (this.c) {
            obj = this.f.get(dVarA);
            this.f.remove(dVarA);
            this.g.add(dVarA);
            if (obj == null || zI) {
                j(dVarA).a(jVar);
                nVar = this.b;
                str = "PreloadManager";
                str2 = "Ad enqueued: " + jVar;
            } else {
                nVar = this.b;
                str = "PreloadManager";
                str2 = "Additional callback found or dummy ads are enabled; skipping enqueue...";
            }
            nVar.a(str, str2);
        }
        if (obj != null) {
            this.b.a("PreloadManager", "Called additional callback regarding " + jVar);
            try {
                if (zI) {
                    a(obj, new com.applovin.impl.sdk.ad.h(dVarA, this.a));
                } else {
                    a(obj, jVar);
                    c(jVar);
                }
            } catch (Throwable th) {
                this.a.w().c("PreloadManager", "Encountered throwable while notifying user callback", th);
            }
        }
        this.b.a("PreloadManager", "Pulled ad from network and saved to preload cache: " + jVar);
    }

    public boolean b(com.applovin.impl.sdk.ad.d dVar) {
        return this.f.containsKey(dVar);
    }

    public com.applovin.impl.sdk.ad.j c(com.applovin.impl.sdk.ad.d dVar) {
        com.applovin.impl.sdk.ad.j jVarF;
        synchronized (this.c) {
            q qVarM = m(dVar);
            jVarF = qVarM != null ? qVarM.f() : null;
        }
        return jVarF;
    }

    void c(com.applovin.impl.sdk.ad.d dVar, int i) {
        Object objRemove;
        this.b.a("PreloadManager", "Failed to pre-load an ad of zone " + dVar + ", error code " + i);
        synchronized (this.c) {
            objRemove = this.f.remove(dVar);
            this.g.add(dVar);
        }
        if (objRemove != null) {
            try {
                a(objRemove, dVar, i);
            } catch (Throwable th) {
                this.a.w().c("PreloadManager", "Encountered exception while invoking user callback", th);
            }
        }
    }

    public com.applovin.impl.sdk.ad.j d(com.applovin.impl.sdk.ad.d dVar) {
        com.applovin.impl.sdk.ad.j jVarE;
        synchronized (this.c) {
            q qVarM = m(dVar);
            jVarE = qVarM != null ? qVarM.e() : null;
        }
        return jVarE;
    }

    public com.applovin.impl.sdk.ad.j e(com.applovin.impl.sdk.ad.d dVar) {
        com.applovin.impl.sdk.ad.j jVarE;
        n nVar;
        StringBuilder sb;
        String str;
        com.applovin.impl.sdk.ad.h hVar;
        synchronized (this.c) {
            q qVarJ = j(dVar);
            jVarE = null;
            if (qVarJ != null) {
                if (dVar.i()) {
                    q qVarK = k(dVar);
                    if (qVarK.c()) {
                        hVar = new com.applovin.impl.sdk.ad.h(dVar, this.a);
                    } else if (qVarJ.a() > 0) {
                        qVarK.a(qVarJ.e());
                        hVar = new com.applovin.impl.sdk.ad.h(dVar, this.a);
                    } else if (qVarK.a() > 0 && ((Boolean) this.a.a(com.applovin.impl.sdk.b.b.ci)).booleanValue()) {
                        hVar = new com.applovin.impl.sdk.ad.h(dVar, this.a);
                    }
                    jVarE = hVar;
                } else {
                    jVarE = qVarJ.e();
                }
            }
        }
        if (jVarE != null) {
            nVar = this.b;
            sb = new StringBuilder();
            str = "Retrieved ad of zone ";
        } else {
            nVar = this.b;
            sb = new StringBuilder();
            str = "Unable to retrieve ad of zone ";
        }
        sb.append(str);
        sb.append(dVar);
        sb.append("...");
        nVar.a("PreloadManager", sb.toString());
        return jVarE;
    }

    public void f(com.applovin.impl.sdk.ad.d dVar) {
        int iB;
        if (dVar == null) {
            return;
        }
        synchronized (this.c) {
            q qVarJ = j(dVar);
            iB = qVarJ != null ? qVarJ.b() - qVarJ.a() : 0;
        }
        b(dVar, iB);
    }

    public boolean g(com.applovin.impl.sdk.ad.d dVar) {
        synchronized (this.c) {
            q qVarK = k(dVar);
            boolean z = true;
            if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.cj)).booleanValue() && qVarK != null && qVarK.a() > 0) {
                return true;
            }
            q qVarJ = j(dVar);
            if (qVarJ == null || qVarJ.d()) {
                z = false;
            }
            return z;
        }
    }

    public void h(com.applovin.impl.sdk.ad.d dVar) {
        synchronized (this.c) {
            q qVarJ = j(dVar);
            if (qVarJ != null) {
                qVarJ.a(dVar.e());
            } else {
                this.d.put(dVar, new q(dVar.e()));
            }
            q qVarK = k(dVar);
            if (qVarK != null) {
                qVarK.a(dVar.f());
            } else {
                this.e.put(dVar, new q(dVar.f()));
            }
        }
    }

    public void i(com.applovin.impl.sdk.ad.d dVar) {
        if (!((Boolean) this.a.a(com.applovin.impl.sdk.b.b.aU)).booleanValue() || l(dVar)) {
            return;
        }
        this.b.a("PreloadManager", "Preloading ad for zone " + dVar + "...");
        this.a.F().a(a(dVar), com.applovin.impl.sdk.d.q.a.MAIN, 500L);
    }
}
