package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbof implements Runnable {
    private final WeakReference<zzboa> zzfgs;

    private zzbof(zzboa zzboaVar) {
        this.zzfgs = new WeakReference<>(zzboaVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzboa zzboaVar = this.zzfgs.get();
        if (zzboaVar != null) {
            zzboaVar.zzafx();
        }
    }
}
