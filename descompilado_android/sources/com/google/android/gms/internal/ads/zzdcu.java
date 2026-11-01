package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdcu implements Executor {
    boolean zzgrh = true;
    private final /* synthetic */ Executor zzgri;
    private final /* synthetic */ zzdbf zzgrj;

    zzdcu(Executor executor, zzdbf zzdbfVar) {
        this.zzgri = executor;
        this.zzgrj = zzdbfVar;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        try {
            this.zzgri.execute(new zzdct(this, runnable));
        } catch (RejectedExecutionException e) {
            if (this.zzgrh) {
                this.zzgrj.setException(e);
            }
        }
    }
}
