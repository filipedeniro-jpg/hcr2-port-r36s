package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzxm implements Runnable {
    private final /* synthetic */ zzxj zzcfc;

    zzxm(zzxj zzxjVar) {
        this.zzcfc = zzxjVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzcfc.zzblm != null) {
            try {
                this.zzcfc.zzblm.onAdFailedToLoad(1);
            } catch (RemoteException e) {
                zzawo.zzd("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
