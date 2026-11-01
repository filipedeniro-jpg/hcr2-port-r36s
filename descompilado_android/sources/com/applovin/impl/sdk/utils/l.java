package com.applovin.impl.sdk.utils;

import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class l {
    private final com.applovin.impl.sdk.h a;
    private Timer b;
    private long c;
    private long d;
    private final Runnable e;
    private long f;
    private final Object g = new Object();

    private l(com.applovin.impl.sdk.h hVar, Runnable runnable) {
        this.a = hVar;
        this.e = runnable;
    }

    public static l a(long j, com.applovin.impl.sdk.h hVar, Runnable runnable) {
        if (j < 0) {
            throw new IllegalArgumentException("Cannot create a scheduled timer. Invalid fire time passed in: " + j + ".");
        }
        if (runnable == null) {
            throw new IllegalArgumentException("Cannot create a scheduled timer. Runnable is null.");
        }
        l lVar = new l(hVar, runnable);
        lVar.c = System.currentTimeMillis();
        lVar.d = j;
        lVar.b = new Timer();
        lVar.b.schedule(lVar.e(), j);
        return lVar;
    }

    private TimerTask e() {
        return new TimerTask() { // from class: com.applovin.impl.sdk.utils.l.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                try {
                    try {
                        l.this.e.run();
                        synchronized (l.this.g) {
                            l.this.b = null;
                        }
                    } catch (Throwable th) {
                        if (l.this.a != null) {
                            l.this.a.w().b("Timer", "Encountered error while executing timed task", th);
                        }
                        synchronized (l.this.g) {
                            l.this.b = null;
                        }
                    }
                } catch (Throwable th2) {
                    synchronized (l.this.g) {
                        l.this.b = null;
                        throw th2;
                    }
                }
            }
        };
    }

    public long a() {
        if (this.b == null) {
            return this.d - this.f;
        }
        return this.d - (System.currentTimeMillis() - this.c);
    }

    public void b() {
        synchronized (this.g) {
            if (this.b != null) {
                try {
                    try {
                        this.b.cancel();
                        this.f = System.currentTimeMillis() - this.c;
                    } catch (Throwable th) {
                        if (this.a != null) {
                            this.a.w().b("Timer", "Encountered error while pausing timer", th);
                        }
                    }
                    this.b = null;
                } catch (Throwable th2) {
                    this.b = null;
                    throw th2;
                }
            }
        }
    }

    public void c() {
        synchronized (this.g) {
            try {
                if (this.f > 0) {
                    try {
                        this.d -= this.f;
                        if (this.d < 0) {
                            this.d = 0L;
                        }
                        this.b = new Timer();
                        this.b.schedule(e(), this.d);
                        this.c = System.currentTimeMillis();
                    } catch (Throwable th) {
                        if (this.a != null) {
                            this.a.w().b("Timer", "Encountered error while resuming timer", th);
                        }
                    }
                    this.f = 0L;
                }
            } catch (Throwable th2) {
                this.f = 0L;
                throw th2;
            }
        }
    }

    public void d() {
        synchronized (this.g) {
            if (this.b != null) {
                try {
                    try {
                        this.b.cancel();
                        this.b = null;
                    } catch (Throwable th) {
                        this.b = null;
                        this.f = 0L;
                        throw th;
                    }
                } catch (Throwable th2) {
                    if (this.a != null) {
                        this.a.w().b("Timer", "Encountered error while cancelling timer", th2);
                    }
                    this.b = null;
                }
                this.f = 0L;
            }
        }
    }
}
