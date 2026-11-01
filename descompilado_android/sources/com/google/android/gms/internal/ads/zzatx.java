package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzatx implements Runnable {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzatv zzdsp;

    zzatx(zzatv zzatvVar, Context context) {
        this.zzdsp = zzatvVar;
        this.val$context = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzdsp.zzdsl) {
            this.zzdsp.zzbey = zzatv.zzap(this.val$context);
            this.zzdsp.zzdsl.notifyAll();
        }
    }
}
