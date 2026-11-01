package com.applovin.impl.adview;

import android.os.Handler;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    private final com.applovin.impl.sdk.n a;
    private final Handler b;
    private final Set<b> c = new HashSet();
    private final AtomicInteger d = new AtomicInteger();

    interface a {
        void a();

        boolean b();
    }

    private static class b {
        private final String a;
        private final a b;
        private final long c;

        private b(String str, long j, a aVar) {
            this.a = str;
            this.c = j;
            this.b = aVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String a() {
            return this.a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long b() {
            return this.c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public a c() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            String str = this.a;
            if (str != null) {
                return str.equalsIgnoreCase(bVar.a);
            }
            return bVar.a == null;
        }

        public int hashCode() {
            String str = this.a;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "CountdownProxy{identifier='" + this.a + "', countdownStepMillis=" + this.c + '}';
        }
    }

    public g(Handler handler, com.applovin.impl.sdk.h hVar) {
        if (handler == null) {
            throw new IllegalArgumentException("No handler specified.");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        this.b = handler;
        this.a = hVar.w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final b bVar, final int i) {
        this.b.postDelayed(new Runnable() { // from class: com.applovin.impl.adview.g.1
            @Override // java.lang.Runnable
            public void run() {
                a aVarC = bVar.c();
                if (!aVarC.b()) {
                    g.this.a.a("CountdownManager", "Ending countdown for " + bVar.a());
                    return;
                }
                if (g.this.d.get() != i) {
                    g.this.a.c("CountdownManager", "Killing duplicate countdown from previous generation: " + bVar.a());
                    return;
                }
                try {
                    aVarC.a();
                } catch (Throwable th) {
                    g.this.a.b("CountdownManager", "Encountered error on countdown step for: " + bVar.a(), th);
                }
                g.this.a(bVar, i);
            }
        }, bVar.b());
    }

    public void a() {
        HashSet<b> hashSet = new HashSet(this.c);
        this.a.a("CountdownManager", "Starting " + hashSet.size() + " countdowns...");
        int iIncrementAndGet = this.d.incrementAndGet();
        for (b bVar : hashSet) {
            this.a.a("CountdownManager", "Starting countdown: " + bVar.a() + " for generation " + iIncrementAndGet + "...");
            a(bVar, iIncrementAndGet);
        }
    }

    public void a(String str, long j, a aVar) {
        if (j <= 0) {
            throw new IllegalArgumentException("Invalid step specified.");
        }
        if (this.b == null) {
            throw new IllegalArgumentException("No handler specified.");
        }
        this.a.a("CountdownManager", "Adding countdown: " + str);
        this.c.add(new b(str, j, aVar));
    }

    public void b() {
        this.a.a("CountdownManager", "Removing all countdowns...");
        c();
        this.c.clear();
    }

    public void c() {
        this.a.a("CountdownManager", "Stopping countdowns...");
        this.d.incrementAndGet();
        this.b.removeCallbacksAndMessages(null);
    }
}
