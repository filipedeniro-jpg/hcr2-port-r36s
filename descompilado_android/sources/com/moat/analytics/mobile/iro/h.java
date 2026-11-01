package com.moat.analytics.mobile.iro;

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

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static final h f576 = new h();

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private ScheduledFuture<?> f577;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private ScheduledFuture<?> f580;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private final ScheduledExecutorService f578 = Executors.newScheduledThreadPool(1);

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private final Map<f, String> f579 = new WeakHashMap();

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private final Map<c, String> f581 = new WeakHashMap();

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static h m523() {
        return f576;
    }

    private h() {
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final void m528(final Context context, f fVar) {
        if (fVar != null) {
            this.f579.put(fVar, "");
            ScheduledFuture<?> scheduledFuture = this.f577;
            if (scheduledFuture == null || scheduledFuture.isDone()) {
                b.m461(3, "JSUpdateLooper", this, "Starting metadata reporting loop");
                this.f577 = this.f578.scheduleWithFixedDelay(new Runnable() { // from class: com.moat.analytics.mobile.iro.h.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_METADATA"));
                            if (h.this.f579.isEmpty()) {
                                h.this.f577.cancel(true);
                            }
                        } catch (Exception e) {
                            o.m569(e);
                        }
                    }
                }, 0L, 50L, TimeUnit.MILLISECONDS);
            }
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final void m529(f fVar) {
        if (fVar != null) {
            b.m461(3, "JSUpdateLooper", this, "removeSetupNeededBridge" + fVar.hashCode());
            this.f579.remove(fVar);
        }
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final void m527(final Context context, c cVar) {
        if (cVar != null) {
            b.m461(3, "JSUpdateLooper", this, "addActiveTracker" + cVar.hashCode());
            if (this.f581.containsKey(cVar)) {
                return;
            }
            this.f581.put(cVar, "");
            ScheduledFuture<?> scheduledFuture = this.f580;
            if (scheduledFuture == null || scheduledFuture.isDone()) {
                b.m461(3, "JSUpdateLooper", this, "Starting view update loop");
                this.f580 = this.f578.scheduleWithFixedDelay(new Runnable() { // from class: com.moat.analytics.mobile.iro.h.4
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_VIEW_INFO"));
                            if (h.this.f581.isEmpty()) {
                                b.m461(3, "JSUpdateLooper", h.this, "No more active trackers");
                                h.this.f580.cancel(true);
                            }
                        } catch (Exception e) {
                            o.m569(e);
                        }
                    }
                }, 0L, t.m598().f665, TimeUnit.MILLISECONDS);
            }
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m530(c cVar) {
        if (cVar != null) {
            b.m461(3, "JSUpdateLooper", this, "removeActiveTracker" + cVar.hashCode());
            this.f581.remove(cVar);
        }
    }
}
