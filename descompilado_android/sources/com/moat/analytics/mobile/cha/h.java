package com.moat.analytics.mobile.cha;

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
final class h {

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static final h f332 = new h();

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private ScheduledFuture<?> f333;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private ScheduledFuture<?> f335;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private final ScheduledExecutorService f336 = Executors.newScheduledThreadPool(1);

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private final Map<j, String> f334 = new WeakHashMap();

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private final Map<d, String> f337 = new WeakHashMap();

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static h m323() {
        return f332;
    }

    private h() {
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final void m329(final Context context, j jVar) {
        if (jVar != null) {
            this.f334.put(jVar, "");
            ScheduledFuture<?> scheduledFuture = this.f333;
            if (scheduledFuture == null || scheduledFuture.isDone()) {
                a.m273(3, "JSUpdateLooper", this, "Starting metadata reporting loop");
                this.f333 = this.f336.scheduleWithFixedDelay(new Runnable() { // from class: com.moat.analytics.mobile.cha.h.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_METADATA"));
                            if (h.this.f334.isEmpty()) {
                                h.this.f333.cancel(true);
                            }
                        } catch (Exception e) {
                            o.m389(e);
                        }
                    }
                }, 0L, 50L, TimeUnit.MILLISECONDS);
            }
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final void m328(j jVar) {
        if (jVar != null) {
            a.m273(3, "JSUpdateLooper", this, "removeSetupNeededBridge" + jVar.hashCode());
            this.f334.remove(jVar);
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m330(final Context context, d dVar) {
        if (dVar != null) {
            a.m273(3, "JSUpdateLooper", this, "addActiveTracker" + dVar.hashCode());
            if (this.f337.containsKey(dVar)) {
                return;
            }
            this.f337.put(dVar, "");
            ScheduledFuture<?> scheduledFuture = this.f335;
            if (scheduledFuture == null || scheduledFuture.isDone()) {
                a.m273(3, "JSUpdateLooper", this, "Starting view update loop");
                this.f335 = this.f336.scheduleWithFixedDelay(new Runnable() { // from class: com.moat.analytics.mobile.cha.h.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_VIEW_INFO"));
                            if (h.this.f337.isEmpty()) {
                                a.m273(3, "JSUpdateLooper", h.this, "No more active trackers");
                                h.this.f335.cancel(true);
                            }
                        } catch (Exception e) {
                            o.m389(e);
                        }
                    }
                }, 0L, t.m423().f437, TimeUnit.MILLISECONDS);
            }
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m331(d dVar) {
        if (dVar != null) {
            a.m273(3, "JSUpdateLooper", this, "removeActiveTracker" + dVar.hashCode());
            this.f337.remove(dVar);
        }
    }
}
