package com.moat.analytics.mobile.cha;

import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
final class t {

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private static t f434;

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private static final Queue<e> f435 = new ConcurrentLinkedQueue();

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private Handler f447;

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private long f438 = 1800000;

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private long f436 = ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    volatile int f441 = a.f453;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    volatile boolean f442 = false;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    volatile boolean f439 = false;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    volatile int f437 = 200;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    volatile int f444 = 10;

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    private final AtomicBoolean f440 = new AtomicBoolean(false);

    /* JADX INFO: renamed from: ॱˋ, reason: contains not printable characters */
    private volatile long f446 = 0;

    /* JADX INFO: renamed from: ॱˊ, reason: contains not printable characters */
    private final AtomicInteger f445 = new AtomicInteger(0);

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    private final AtomicBoolean f443 = new AtomicBoolean(false);

    interface b {
        /* JADX INFO: renamed from: ˎ */
        void mo312() throws o;
    }

    interface c {
        /* JADX INFO: renamed from: ˏ */
        void mo430(g gVar) throws o;
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    static final class a {

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        public static final int f452 = 2;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        public static final int f453 = 1;

        static {
            int[] iArr = {1, 2};
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static synchronized t m423() {
        if (f434 == null) {
            f434 = new t();
        }
        return f434;
    }

    private t() {
        try {
            this.f447 = new Handler(Looper.getMainLooper());
        } catch (Exception e2) {
            o.m389(e2);
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final void m429() {
        if (System.currentTimeMillis() - this.f446 > this.f438) {
            m424(0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    public void m424(final long j) {
        if (this.f443.compareAndSet(false, true)) {
            com.moat.analytics.mobile.cha.a.m273(3, "OnOff", this, "Performing status check.");
            new Thread() { // from class: com.moat.analytics.mobile.cha.t.2
                /* JADX WARN: Type inference failed for: r4v0, types: [com.moat.analytics.mobile.cha.t$2$2] */
                @Override // java.lang.Thread, java.lang.Runnable
                public final void run() {
                    Looper.prepare();
                    Handler handler = new Handler();
                    handler.postDelayed(new d(BuildConfig.NAMESPACE, handler, new c() { // from class: com.moat.analytics.mobile.cha.t.2.2
                        @Override // com.moat.analytics.mobile.cha.t.c
                        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
                        public final void mo430(g gVar) throws o {
                            synchronized (t.f435) {
                                boolean z = ((f) MoatAnalytics.getInstance()).f318;
                                if (t.this.f441 != gVar.m319() || (t.this.f441 == a.f453 && z)) {
                                    t.this.f441 = gVar.m319();
                                    if (t.this.f441 == a.f453 && z) {
                                        t.this.f441 = a.f452;
                                    }
                                    if (t.this.f441 == a.f452) {
                                        com.moat.analytics.mobile.cha.a.m273(3, "OnOff", this, "Moat enabled - Version 2.4.1");
                                    }
                                    for (e eVar : t.f435) {
                                        if (t.this.f441 == a.f452) {
                                            eVar.f462.mo312();
                                        }
                                    }
                                }
                                while (!t.f435.isEmpty()) {
                                    t.f435.remove();
                                }
                            }
                        }
                    }), j);
                    Looper.loop();
                }
            }.start();
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final void m428(b bVar) throws o {
        if (this.f441 == a.f452) {
            bVar.mo312();
            return;
        }
        m417();
        f435.add(new e(Long.valueOf(System.currentTimeMillis()), bVar));
        if (this.f440.compareAndSet(false, true)) {
            this.f447.postDelayed(new Runnable() { // from class: com.moat.analytics.mobile.cha.t.4
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        if (t.f435.size() > 0) {
                            t.m417();
                            t.this.f447.postDelayed(this, ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
                        } else {
                            t.this.f440.compareAndSet(true, false);
                            t.this.f447.removeCallbacks(this);
                        }
                    } catch (Exception e2) {
                        o.m389(e2);
                    }
                }
            }, ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    public static void m417() {
        synchronized (f435) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            Iterator<e> it = f435.iterator();
            while (it.hasNext()) {
                if (jCurrentTimeMillis - it.next().f460.longValue() >= ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS) {
                    it.remove();
                }
            }
            if (f435.size() >= 15) {
                for (int i = 0; i < 5; i++) {
                    f435.remove();
                }
            }
        }
    }

    class d implements Runnable {

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private final String f455;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        private final AnonymousClass2.C00842 f456;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        private final Handler f457;

        private d(String str, Handler handler, AnonymousClass2.C00842 c00842) {
            this.f456 = c00842;
            this.f457 = handler;
            this.f455 = "https://z.moatads.com/" + str + "/android/" + BuildConfig.REVISION.substring(0, 7) + "/status.json";
        }

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private String m431() {
            try {
                return m.m367(this.f455 + "?ts=" + System.currentTimeMillis() + "&v=2.4.1").get();
            } catch (Exception unused) {
                return null;
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                String strM431 = m431();
                final g gVar = new g(strM431);
                t.this.f442 = gVar.m318();
                t.this.f439 = gVar.m322();
                t.this.f437 = gVar.m321();
                t.this.f444 = gVar.m320();
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.moat.analytics.mobile.cha.t.d.5
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            mo430(gVar);
                        } catch (Exception e) {
                            o.m389(e);
                        }
                    }
                });
                t.this.f446 = System.currentTimeMillis();
                t.this.f443.compareAndSet(true, false);
                if (strM431 == null) {
                    if (t.this.f445.incrementAndGet() < 10) {
                        t.this.m424(t.this.f436);
                    }
                } else {
                    t.this.f445.set(0);
                }
            } catch (Exception e) {
                o.m389(e);
            }
            this.f457.removeCallbacks(this);
            Looper looperMyLooper = Looper.myLooper();
            if (looperMyLooper != null) {
                looperMyLooper.quit();
            }
        }
    }

    class e {

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        final Long f460;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        final b f462;

        e(Long l, b bVar) {
            this.f460 = l;
            this.f462 = bVar;
        }
    }
}
