package com.google.android.gms.internal.ads;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzdbv<T> extends zzdco<T> {
    private final Executor zzgqq;
    boolean zzgqr = true;
    private final /* synthetic */ zzdbt zzgqs;

    public zzdbv(zzdbt zzdbtVar, Executor executor) {
        this.zzgqs = zzdbtVar;
        this.zzgqq = (Executor) zzczx.checkNotNull(executor);
    }

    abstract void setValue(T t);

    @Override // com.google.android.gms.internal.ads.zzdco
    final boolean isDone() {
        return this.zzgqs.isDone();
    }

    final void execute() {
        try {
            this.zzgqq.execute(this);
        } catch (RejectedExecutionException e) {
            if (this.zzgqr) {
                this.zzgqs.setException(e);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdco
    final void zzb(T t, Throwable th) {
        if (th != null) {
            if (th instanceof ExecutionException) {
                this.zzgqs.setException(th.getCause());
                return;
            } else if (th instanceof CancellationException) {
                this.zzgqs.cancel(false);
                return;
            } else {
                this.zzgqs.setException(th);
                return;
            }
        }
        setValue(t);
    }
}
