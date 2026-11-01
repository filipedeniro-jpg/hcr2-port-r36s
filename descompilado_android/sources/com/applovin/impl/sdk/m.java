package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class m {
    private final h a;
    private long d;
    private long g;
    private final AtomicBoolean b = new AtomicBoolean();
    private final List<a> c = Collections.synchronizedList(new ArrayList());
    private final Object e = new Object();
    private final AtomicBoolean f = new AtomicBoolean();

    interface a {
        void h();

        void i();
    }

    m(h hVar) {
        this.a = hVar;
    }

    public void a(a aVar) {
        this.c.add(aVar);
    }

    public void a(boolean z) {
        synchronized (this.e) {
            this.f.set(z);
            if (z) {
                this.g = System.currentTimeMillis();
                this.a.w().a("FullScreenAdTracker", "Setting fullscreen ad pending display: " + this.g);
                final long jLongValue = ((Long) this.a.a(com.applovin.impl.sdk.b.b.cH)).longValue();
                if (jLongValue >= 0) {
                    AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() { // from class: com.applovin.impl.sdk.m.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (m.this.a() && System.currentTimeMillis() - m.this.g >= jLongValue) {
                                m.this.a.w().a("FullScreenAdTracker", "Resetting \"pending display\" state...");
                                m.this.f.set(false);
                            }
                        }
                    }, jLongValue);
                }
            } else {
                this.g = 0L;
                this.a.w().a("FullScreenAdTracker", "Setting fullscreen ad not pending display: " + System.currentTimeMillis());
            }
        }
    }

    public boolean a() {
        return this.f.get();
    }

    public void b(a aVar) {
        this.c.remove(aVar);
    }

    public boolean b() {
        return this.b.get();
    }

    public void c() {
        if (this.b.compareAndSet(false, true)) {
            this.d = System.currentTimeMillis();
            this.a.w().a("FullScreenAdTracker", "Setting fullscreen ad displayed: " + this.d);
            Iterator it = new ArrayList(this.c).iterator();
            while (it.hasNext()) {
                ((a) it.next()).h();
            }
            final long jLongValue = ((Long) this.a.a(com.applovin.impl.sdk.b.b.cI)).longValue();
            if (jLongValue >= 0) {
                AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() { // from class: com.applovin.impl.sdk.m.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (m.this.b.get() && System.currentTimeMillis() - m.this.d >= jLongValue) {
                            m.this.a.w().a("FullScreenAdTracker", "Resetting \"display\" state...");
                            m.this.d();
                        }
                    }
                }, jLongValue);
            }
        }
    }

    public void d() {
        if (this.b.compareAndSet(true, false)) {
            this.a.w().a("FullScreenAdTracker", "Setting fullscreen ad hidden: " + System.currentTimeMillis());
            Iterator it = new ArrayList(this.c).iterator();
            while (it.hasNext()) {
                ((a) it.next()).i();
            }
        }
    }
}
