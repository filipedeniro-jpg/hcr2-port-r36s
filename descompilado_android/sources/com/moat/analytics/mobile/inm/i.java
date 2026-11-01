package com.moat.analytics.mobile.inm;

import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
class i {
    private static final i a = new i();
    private ScheduledFuture<?> e;
    private ScheduledFuture<?> f;
    private final ScheduledExecutorService d = Executors.newScheduledThreadPool(1);
    private final Map<j, String> b = new WeakHashMap();
    private final Map<b, String> c = new WeakHashMap();

    private i() {
    }

    static i a() {
        return a;
    }

    private void a(final Context context) {
        ScheduledFuture<?> scheduledFuture = this.f;
        if (scheduledFuture == null || scheduledFuture.isDone()) {
            p.a(3, "JSUpdateLooper", this, "Starting metadata reporting loop");
            this.f = this.d.scheduleWithFixedDelay(new Runnable() { // from class: com.moat.analytics.mobile.inm.i.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_METADATA"));
                        if (i.this.b.isEmpty()) {
                            i.this.f.cancel(true);
                        }
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            }, 0L, 50L, TimeUnit.MILLISECONDS);
        }
    }

    private void b(final Context context) {
        ScheduledFuture<?> scheduledFuture = this.e;
        if (scheduledFuture == null || scheduledFuture.isDone()) {
            p.a(3, "JSUpdateLooper", this, "Starting view update loop");
            this.e = this.d.scheduleWithFixedDelay(new Runnable() { // from class: com.moat.analytics.mobile.inm.i.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_VIEW_INFO"));
                        if (i.this.c.isEmpty()) {
                            p.a(3, "JSUpdateLooper", i.this, "No more active trackers");
                            i.this.e.cancel(true);
                        }
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            }, 0L, w.a().d, TimeUnit.MILLISECONDS);
        }
    }

    void a(Context context, b bVar) {
        if (bVar != null) {
            p.a(3, "JSUpdateLooper", this, "addActiveTracker" + bVar.hashCode());
            Map<b, String> map = this.c;
            if (map == null || map.containsKey(bVar)) {
                return;
            }
            this.c.put(bVar, "");
            b(context);
        }
    }

    void a(Context context, j jVar) {
        Map<j, String> map = this.b;
        if (map == null || jVar == null) {
            return;
        }
        map.put(jVar, "");
        a(context);
    }

    void a(b bVar) {
        if (bVar != null) {
            p.a(3, "JSUpdateLooper", this, "removeActiveTracker" + bVar.hashCode());
            Map<b, String> map = this.c;
            if (map != null) {
                map.remove(bVar);
            }
        }
    }

    void a(j jVar) {
        if (jVar != null) {
            p.a(3, "JSUpdateLooper", this, "removeSetupNeededBridge" + jVar.hashCode());
            Map<j, String> map = this.b;
            if (map != null) {
                map.remove(jVar);
            }
        }
    }
}
