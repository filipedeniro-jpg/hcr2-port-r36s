package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzddd<V> extends zzdcc<V> implements RunnableFuture<V> {
    private volatile zzdco<?> zzgrr;

    static <V> zzddd<V> zze(Callable<V> callable) {
        return new zzddd<>(callable);
    }

    static <V> zzddd<V> zza(Runnable runnable, @NullableDecl V v) {
        return new zzddd<>(Executors.callable(runnable, v));
    }

    private zzddd(Callable<V> callable) {
        this.zzgrr = new zzddg(this, callable);
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public final void run() {
        zzdco<?> zzdcoVar = this.zzgrr;
        if (zzdcoVar != null) {
            zzdcoVar.run();
        }
        this.zzgrr = null;
    }

    @Override // com.google.android.gms.internal.ads.zzdbf
    protected final void afterDone() {
        zzdco<?> zzdcoVar;
        super.afterDone();
        if (wasInterrupted() && (zzdcoVar = this.zzgrr) != null) {
            zzdcoVar.interruptTask();
        }
        this.zzgrr = null;
    }

    @Override // com.google.android.gms.internal.ads.zzdbf
    protected final String pendingToString() {
        zzdco<?> zzdcoVar = this.zzgrr;
        if (zzdcoVar != null) {
            String strValueOf = String.valueOf(zzdcoVar);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 7);
            sb.append("task=[");
            sb.append(strValueOf);
            sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
            return sb.toString();
        }
        return super.pendingToString();
    }
}
