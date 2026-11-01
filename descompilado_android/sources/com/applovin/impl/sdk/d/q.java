package com.applovin.impl.sdk.d;

import com.ironsource.sdk.constants.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class q {
    private final com.applovin.impl.sdk.h b;
    private final com.applovin.impl.sdk.n c;
    private boolean v;
    private final String a = "TaskManager";
    private final List<c> t = new ArrayList(5);
    private final Object u = new Object();
    private final ScheduledThreadPoolExecutor d = a(Constants.ParametersKeys.MAIN);
    private final ScheduledThreadPoolExecutor e = a("timeout");
    private final ScheduledThreadPoolExecutor f = a("back");
    private final ScheduledThreadPoolExecutor g = a("postbacks");
    private final ScheduledThreadPoolExecutor h = a("caching_interstitial");
    private final ScheduledThreadPoolExecutor i = a("caching_incentivized");
    private final ScheduledThreadPoolExecutor j = a("caching_other");
    private final ScheduledThreadPoolExecutor k = a("reward");
    private final ScheduledThreadPoolExecutor l = a("mediation_main");
    private final ScheduledThreadPoolExecutor m = a("mediation_timeout");
    private final ScheduledThreadPoolExecutor n = a("mediation_background");
    private final ScheduledThreadPoolExecutor o = a("mediation_backup");
    private final ScheduledThreadPoolExecutor p = a("mediation_postbacks");
    private final ScheduledThreadPoolExecutor q = a("mediation_banner");
    private final ScheduledThreadPoolExecutor r = a("mediation_interstitial");
    private final ScheduledThreadPoolExecutor s = a("mediation_incentivized");

    public enum a {
        MAIN,
        TIMEOUT,
        BACKGROUND,
        POSTBACKS,
        CACHING_INTERSTITIAL,
        CACHING_INCENTIVIZED,
        CACHING_OTHER,
        REWARD,
        MEDIATION_MAIN,
        MEDIATION_TIMEOUT,
        MEDIATION_BACKGROUND,
        MEDIATION_BACKUP,
        MEDIATION_POSTBACKS,
        MEDIATION_BANNER,
        MEDIATION_INTERSTITIAL,
        MEDIATION_INCENTIVIZED
    }

    private class b implements ThreadFactory {
        private final String b;

        public b(String str) {
            this.b = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "AppLovinSdk:" + this.b + ":" + com.applovin.impl.sdk.utils.n.a(q.this.b.u()));
            thread.setDaemon(true);
            thread.setPriority(10);
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.applovin.impl.sdk.d.q.b.1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread2, Throwable th) {
                    q.this.c.b("TaskManager", "Caught unhandled exception", th);
                }
            });
            return thread;
        }
    }

    private class c implements Runnable {
        private final String b;
        private final com.applovin.impl.sdk.d.a c;
        private final a d;

        c(com.applovin.impl.sdk.d.a aVar, a aVar2) {
            this.b = aVar.d();
            this.c = aVar;
            this.d = aVar2;
        }

        @Override // java.lang.Runnable
        public void run() {
            long jA;
            com.applovin.impl.sdk.n nVar;
            StringBuilder sb;
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                try {
                    com.applovin.impl.sdk.utils.e.a();
                    if (!q.this.b.c() || this.c.f()) {
                        q.this.c.b(this.b, "Task started execution...");
                        this.c.run();
                        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                        q.this.b.H().a(this.c.a(), jCurrentTimeMillis2);
                        q.this.c.b(this.b, "Task executed successfully in " + jCurrentTimeMillis2 + "ms.");
                    } else {
                        q.this.c.b(this.b, "Task re-scheduled...");
                        q.this.a(this.c, this.d, 2000L);
                    }
                    jA = q.this.a(this.d) - 1;
                    nVar = q.this.c;
                    sb = new StringBuilder();
                } catch (Throwable th) {
                    long jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                    q.this.b.H().a(this.c.a(), true, jCurrentTimeMillis3);
                    q.this.c.b(this.c.d(), "Task failed execution in " + jCurrentTimeMillis3 + "ms.", th);
                    jA = q.this.a(this.d) - 1;
                    nVar = q.this.c;
                    sb = new StringBuilder();
                }
                sb.append(this.d);
                sb.append(" queue finished task ");
                sb.append(this.c.d());
                sb.append(" with queue size ");
                sb.append(jA);
                nVar.b("TaskManager", sb.toString());
            } catch (Throwable th2) {
                long jA2 = q.this.a(this.d) - 1;
                q.this.c.b("TaskManager", this.d + " queue finished task " + this.c.d() + " with queue size " + jA2);
                throw th2;
            }
        }
    }

    public q(com.applovin.impl.sdk.h hVar) {
        this.b = hVar;
        this.c = hVar.w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long a(a aVar) {
        long taskCount;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        if (aVar == a.MAIN) {
            taskCount = this.d.getTaskCount();
            scheduledThreadPoolExecutor = this.d;
        } else if (aVar == a.TIMEOUT) {
            taskCount = this.e.getTaskCount();
            scheduledThreadPoolExecutor = this.e;
        } else if (aVar == a.BACKGROUND) {
            taskCount = this.f.getTaskCount();
            scheduledThreadPoolExecutor = this.f;
        } else if (aVar == a.POSTBACKS) {
            taskCount = this.g.getTaskCount();
            scheduledThreadPoolExecutor = this.g;
        } else if (aVar == a.CACHING_INTERSTITIAL) {
            taskCount = this.h.getTaskCount();
            scheduledThreadPoolExecutor = this.h;
        } else if (aVar == a.CACHING_INCENTIVIZED) {
            taskCount = this.i.getTaskCount();
            scheduledThreadPoolExecutor = this.i;
        } else if (aVar == a.CACHING_OTHER) {
            taskCount = this.j.getTaskCount();
            scheduledThreadPoolExecutor = this.j;
        } else if (aVar == a.REWARD) {
            taskCount = this.k.getTaskCount();
            scheduledThreadPoolExecutor = this.k;
        } else if (aVar == a.MEDIATION_MAIN) {
            taskCount = this.l.getTaskCount();
            scheduledThreadPoolExecutor = this.l;
        } else if (aVar == a.MEDIATION_TIMEOUT) {
            taskCount = this.m.getTaskCount();
            scheduledThreadPoolExecutor = this.m;
        } else if (aVar == a.MEDIATION_BACKGROUND) {
            taskCount = this.n.getTaskCount();
            scheduledThreadPoolExecutor = this.n;
        } else if (aVar == a.MEDIATION_BACKUP) {
            taskCount = this.o.getTaskCount();
            scheduledThreadPoolExecutor = this.o;
        } else if (aVar == a.MEDIATION_POSTBACKS) {
            taskCount = this.p.getTaskCount();
            scheduledThreadPoolExecutor = this.p;
        } else if (aVar == a.MEDIATION_BANNER) {
            taskCount = this.q.getTaskCount();
            scheduledThreadPoolExecutor = this.q;
        } else if (aVar == a.MEDIATION_INTERSTITIAL) {
            taskCount = this.r.getTaskCount();
            scheduledThreadPoolExecutor = this.r;
        } else {
            if (aVar != a.MEDIATION_INCENTIVIZED) {
                return 0L;
            }
            taskCount = this.s.getTaskCount();
            scheduledThreadPoolExecutor = this.s;
        }
        return taskCount - scheduledThreadPoolExecutor.getCompletedTaskCount();
    }

    private ScheduledThreadPoolExecutor a(String str) {
        return new ScheduledThreadPoolExecutor(1, new b(str));
    }

    private static void a(Runnable runnable, long j, ScheduledExecutorService scheduledExecutorService) {
        if (j > 0) {
            scheduledExecutorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
        } else {
            scheduledExecutorService.submit(runnable);
        }
    }

    private boolean a(c cVar) {
        if (cVar.c.f()) {
            return false;
        }
        synchronized (this.u) {
            if (this.v) {
                return false;
            }
            this.t.add(cVar);
            return true;
        }
    }

    public void a() {
        synchronized (this.u) {
            this.v = false;
        }
    }

    public void a(com.applovin.impl.sdk.d.a aVar) {
        if (aVar == null) {
            this.c.d("TaskManager", "Attempted to execute null task immediately");
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            this.c.b("TaskManager", "Executing " + aVar.d() + " immediately...");
            aVar.run();
            this.b.H().a(aVar.a(), System.currentTimeMillis() - jCurrentTimeMillis);
            this.c.b("TaskManager", aVar.d() + " finished executing...");
        } catch (Throwable th) {
            this.c.b(aVar.d(), "Task failed execution", th);
            this.b.H().a(aVar.a(), true, System.currentTimeMillis() - jCurrentTimeMillis);
        }
    }

    public void a(com.applovin.impl.sdk.d.a aVar, a aVar2) {
        a(aVar, aVar2, 0L);
    }

    public void a(com.applovin.impl.sdk.d.a aVar, a aVar2, long j) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        if (aVar == null) {
            throw new IllegalArgumentException("No task specified");
        }
        if (j < 0) {
            throw new IllegalArgumentException("Invalid delay specified: " + j);
        }
        if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.a.H)).booleanValue()) {
            if (aVar2 == a.MEDIATION_MAIN) {
                aVar2 = a.MAIN;
            } else if (aVar2 == a.MEDIATION_BACKGROUND) {
                aVar2 = a.BACKGROUND;
            } else if (aVar2 == a.MEDIATION_POSTBACKS) {
                aVar2 = a.POSTBACKS;
            }
        }
        c cVar = new c(aVar, aVar2);
        if (a(cVar)) {
            this.c.b(aVar.d(), "Task " + aVar.d() + " execution delayed until after init");
            return;
        }
        long jA = a(aVar2) + 1;
        this.c.a("TaskManager", "Scheduling " + aVar.d() + " on " + aVar2 + " queue in " + j + "ms with new queue size " + jA);
        if (aVar2 == a.MAIN) {
            scheduledThreadPoolExecutor = this.d;
        } else if (aVar2 == a.TIMEOUT) {
            scheduledThreadPoolExecutor = this.e;
        } else if (aVar2 == a.BACKGROUND) {
            scheduledThreadPoolExecutor = this.f;
        } else if (aVar2 == a.POSTBACKS) {
            scheduledThreadPoolExecutor = this.g;
        } else if (aVar2 == a.CACHING_INTERSTITIAL) {
            scheduledThreadPoolExecutor = this.h;
        } else if (aVar2 == a.CACHING_INCENTIVIZED) {
            scheduledThreadPoolExecutor = this.i;
        } else if (aVar2 == a.CACHING_OTHER) {
            scheduledThreadPoolExecutor = this.j;
        } else if (aVar2 == a.REWARD) {
            scheduledThreadPoolExecutor = this.k;
        } else if (aVar2 == a.MEDIATION_MAIN) {
            scheduledThreadPoolExecutor = this.l;
        } else if (aVar2 == a.MEDIATION_TIMEOUT) {
            scheduledThreadPoolExecutor = this.m;
        } else if (aVar2 == a.MEDIATION_BACKGROUND) {
            scheduledThreadPoolExecutor = this.n;
        } else if (aVar2 == a.MEDIATION_BACKUP) {
            scheduledThreadPoolExecutor = this.o;
        } else if (aVar2 == a.MEDIATION_POSTBACKS) {
            scheduledThreadPoolExecutor = this.p;
        } else if (aVar2 == a.MEDIATION_BANNER) {
            scheduledThreadPoolExecutor = this.q;
        } else if (aVar2 == a.MEDIATION_INTERSTITIAL) {
            scheduledThreadPoolExecutor = this.r;
        } else if (aVar2 != a.MEDIATION_INCENTIVIZED) {
            return;
        } else {
            scheduledThreadPoolExecutor = this.s;
        }
        a(cVar, j, scheduledThreadPoolExecutor);
    }

    public void b() {
        synchronized (this.u) {
            this.v = true;
            for (c cVar : this.t) {
                a(cVar.c, cVar.d);
            }
            this.t.clear();
        }
    }
}
