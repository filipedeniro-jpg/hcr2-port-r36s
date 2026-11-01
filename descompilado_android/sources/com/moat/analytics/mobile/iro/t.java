package com.moat.analytics.mobile.iro;

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
    private static final Queue<b> f655 = new ConcurrentLinkedQueue();

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private static t f656;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private Handler f668;

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private long f659 = 1800000;

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private long f657 = ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    volatile int f660 = c.f676;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    volatile boolean f662 = false;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    volatile boolean f663 = false;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    volatile int f665 = 200;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    volatile int f658 = 10;

    /* JADX INFO: renamed from: ॱˊ, reason: contains not printable characters */
    private final AtomicBoolean f666 = new AtomicBoolean(false);

    /* JADX INFO: renamed from: ॱˋ, reason: contains not printable characters */
    private volatile long f667 = 0;

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    private final AtomicInteger f661 = new AtomicInteger(0);

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    private final AtomicBoolean f664 = new AtomicBoolean(false);

    interface a {
        /* JADX INFO: renamed from: ॱ */
        void mo544() throws o;
    }

    interface e {
        /* JADX INFO: renamed from: ˋ */
        void mo609(i iVar) throws o;
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    static final class c {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        public static final int f676 = 1;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        public static final int f677 = 2;

        static {
            int[] iArr = {1, 2};
        }
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static synchronized t m598() {
        if (f656 == null) {
            f656 = new t();
        }
        return f656;
    }

    private t() {
        try {
            this.f668 = new Handler(Looper.getMainLooper());
        } catch (Exception e2) {
            o.m569(e2);
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final void m607() {
        if (System.currentTimeMillis() - this.f667 > this.f659) {
            m600(0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    public void m600(final long j) {
        if (this.f664.compareAndSet(false, true)) {
            com.moat.analytics.mobile.iro.b.m461(3, "OnOff", this, "Performing status check.");
            new Thread() { // from class: com.moat.analytics.mobile.iro.t.5
                /* JADX WARN: Type inference failed for: r4v0, types: [com.moat.analytics.mobile.iro.t$5$2] */
                @Override // java.lang.Thread, java.lang.Runnable
                public final void run() {
                    Looper.prepare();
                    Handler handler = new Handler();
                    handler.postDelayed(new d("IRO", handler, new e() { // from class: com.moat.analytics.mobile.iro.t.5.2
                        @Override // com.moat.analytics.mobile.iro.t.e
                        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
                        public final void mo609(i iVar) throws o {
                            synchronized (t.f655) {
                                boolean z = ((j) MoatAnalytics.getInstance()).f601;
                                if (t.this.f660 != iVar.m539() || (t.this.f660 == c.f676 && z)) {
                                    t.this.f660 = iVar.m539();
                                    if (t.this.f660 == c.f676 && z) {
                                        t.this.f660 = c.f677;
                                    }
                                    if (t.this.f660 == c.f677) {
                                        com.moat.analytics.mobile.iro.b.m461(3, "OnOff", this, "Moat enabled - Version 2.4.0");
                                    }
                                    for (b bVar : t.f655) {
                                        if (t.this.f660 == c.f677) {
                                            bVar.f673.mo544();
                                        }
                                    }
                                }
                                while (!t.f655.isEmpty()) {
                                    t.f655.remove();
                                }
                            }
                        }
                    }), j);
                    Looper.loop();
                }
            }.start();
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m608(a aVar) throws o {
        if (this.f660 == c.f677) {
            aVar.mo544();
            return;
        }
        m596();
        f655.add(new b(Long.valueOf(System.currentTimeMillis()), aVar));
        if (this.f666.compareAndSet(false, true)) {
            this.f668.postDelayed(new Runnable() { // from class: com.moat.analytics.mobile.iro.t.2
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        if (t.f655.size() > 0) {
                            t.m596();
                            t.this.f668.postDelayed(this, ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
                        } else {
                            t.this.f666.compareAndSet(true, false);
                            t.this.f668.removeCallbacks(this);
                        }
                    } catch (Exception e2) {
                        o.m569(e2);
                    }
                }
            }, ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    public static void m596() {
        synchronized (f655) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            Iterator<b> it = f655.iterator();
            while (it.hasNext()) {
                if (jCurrentTimeMillis - it.next().f675.longValue() >= ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS) {
                    it.remove();
                }
            }
            if (f655.size() >= 15) {
                for (int i = 0; i < 5; i++) {
                    f655.remove();
                }
            }
        }
    }

    class d implements Runnable {

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        private final Handler f678;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private final AnonymousClass5.AnonymousClass2 f679;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        private final String f680;

        private d(String str, Handler handler, AnonymousClass5.AnonymousClass2 anonymousClass2) {
            this.f679 = anonymousClass2;
            this.f678 = handler;
            this.f680 = "https://z.moatads.com/" + str + "/android/" + "2bc3418b93f01686fcbd1ebebcc04694651821b2".substring(0, 7) + "/status.json";
        }

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        private String m611() {
            try {
                return l.m565(this.f680 + "?ts=" + System.currentTimeMillis() + "&v=2.4.0").get();
            } catch (Exception unused) {
                return null;
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                String strM611 = m611();
                final i iVar = new i(strM611);
                t.this.f662 = iVar.m536();
                t.this.f663 = iVar.m538();
                t.this.f665 = iVar.m537();
                t.this.f658 = iVar.m540();
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.moat.analytics.mobile.iro.t.d.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            mo609(iVar);
                        } catch (Exception e) {
                            o.m569(e);
                        }
                    }
                });
                t.this.f667 = System.currentTimeMillis();
                t.this.f664.compareAndSet(true, false);
                if (strM611 == null) {
                    if (t.this.f661.incrementAndGet() < 10) {
                        t.this.m600(t.this.f657);
                    }
                } else {
                    t.this.f661.set(0);
                }
            } catch (Exception e) {
                o.m569(e);
            }
            this.f678.removeCallbacks(this);
            Looper looperMyLooper = Looper.myLooper();
            if (looperMyLooper != null) {
                looperMyLooper.quit();
            }
        }
    }

    class b {

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        final a f673;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        final Long f675;

        b(Long l, a aVar) {
            this.f675 = l;
            this.f673 = aVar;
        }
    }
}
