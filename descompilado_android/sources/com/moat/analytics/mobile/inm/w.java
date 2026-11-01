package com.moat.analytics.mobile.inm;

import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
class w {
    private static w h;
    private static final Queue<c> i = new ConcurrentLinkedQueue();
    private Handler j;
    private long f = 1800000;
    private long g = ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS;
    volatile d a = d.OFF;
    volatile boolean b = false;
    volatile boolean c = false;
    volatile int d = 200;
    volatile int e = 10;
    private final AtomicBoolean k = new AtomicBoolean(false);
    private volatile long l = 0;
    private final AtomicInteger m = new AtomicInteger(0);
    private final AtomicBoolean n = new AtomicBoolean(false);

    private class a implements Runnable {
        private final Handler b;
        private final String c;
        private final e d;

        private a(String str, Handler handler, e eVar) {
            this.d = eVar;
            this.b = handler;
            this.c = "https://z.moatads.com/" + str + "/android/" + "c334ae83accfebb8da23104450c896463c9cfab7".substring(0, 7) + "/status.json";
        }

        private void a() {
            String strB = b();
            final l lVar = new l(strB);
            w.this.b = lVar.a();
            w.this.c = lVar.b();
            w.this.d = lVar.c();
            w.this.e = lVar.d();
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.moat.analytics.mobile.inm.w.a.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        a.this.d.a(lVar);
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            });
            w.this.l = System.currentTimeMillis();
            w.this.n.compareAndSet(true, false);
            if (strB != null) {
                w.this.m.set(0);
            } else if (w.this.m.incrementAndGet() < 10) {
                w wVar = w.this;
                wVar.a(wVar.g);
            }
        }

        private String b() {
            try {
                return q.a(this.c + "?ts=" + System.currentTimeMillis() + "&v=2.5.0").b();
            } catch (Exception unused) {
                return null;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a();
            } catch (Exception e) {
                m.a(e);
            }
            this.b.removeCallbacks(this);
            Looper looperMyLooper = Looper.myLooper();
            if (looperMyLooper != null) {
                looperMyLooper.quit();
            }
        }
    }

    interface b {
        void c();

        void d();
    }

    private class c {
        final Long a;
        final b b;

        c(Long l, b bVar) {
            this.a = l;
            this.b = bVar;
        }
    }

    enum d {
        OFF,
        ON
    }

    interface e {
        void a(l lVar);
    }

    private w() {
        try {
            this.j = new Handler(Looper.getMainLooper());
        } catch (Exception e2) {
            m.a(e2);
        }
    }

    static synchronized w a() {
        if (h == null) {
            h = new w();
        }
        return h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final long j) {
        if (this.n.compareAndSet(false, true)) {
            p.a(3, "OnOff", this, "Performing status check.");
            new Thread() { // from class: com.moat.analytics.mobile.inm.w.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Looper.prepare();
                    Handler handler = new Handler();
                    handler.postDelayed(new a("INM", handler, new e() { // from class: com.moat.analytics.mobile.inm.w.1.1
                        @Override // com.moat.analytics.mobile.inm.w.e
                        public void a(l lVar) {
                            synchronized (w.i) {
                                boolean z = ((k) MoatAnalytics.getInstance()).a;
                                if (w.this.a != lVar.e() || (w.this.a == d.OFF && z)) {
                                    w.this.a = lVar.e();
                                    if (w.this.a == d.OFF && z) {
                                        w.this.a = d.ON;
                                    }
                                    if (w.this.a == d.ON) {
                                        p.a(3, "OnOff", this, "Moat enabled - Version 2.5.0");
                                    }
                                    for (c cVar : w.i) {
                                        if (w.this.a == d.ON) {
                                            cVar.b.c();
                                        } else {
                                            cVar.b.d();
                                        }
                                    }
                                }
                                while (!w.i.isEmpty()) {
                                    w.i.remove();
                                }
                            }
                        }
                    }), j);
                    Looper.loop();
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        synchronized (i) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            Iterator<c> it = i.iterator();
            while (it.hasNext()) {
                if (jCurrentTimeMillis - it.next().a.longValue() >= ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS) {
                    it.remove();
                }
            }
            if (i.size() >= 15) {
                for (int i2 = 0; i2 < 5; i2++) {
                    i.remove();
                }
            }
        }
    }

    private void e() {
        if (this.k.compareAndSet(false, true)) {
            this.j.postDelayed(new Runnable() { // from class: com.moat.analytics.mobile.inm.w.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (w.i.size() > 0) {
                            w.this.d();
                            w.this.j.postDelayed(this, ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
                        } else {
                            w.this.k.compareAndSet(true, false);
                            w.this.j.removeCallbacks(this);
                        }
                    } catch (Exception e2) {
                        m.a(e2);
                    }
                }
            }, ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
        }
    }

    void a(b bVar) {
        if (this.a == d.ON) {
            bVar.c();
            return;
        }
        d();
        i.add(new c(Long.valueOf(System.currentTimeMillis()), bVar));
        e();
    }

    void b() {
        if (System.currentTimeMillis() - this.l > this.f) {
            a(0L);
        }
    }
}
