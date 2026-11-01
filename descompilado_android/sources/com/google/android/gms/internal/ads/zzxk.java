package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzxk implements Runnable {
    private final /* synthetic */ zzxh zzcfa;

    zzxk(zzxh zzxhVar) {
        this.zzcfa = zzxhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzcfa.zzcez.zzblm != null) {
            try {
                this.zzcfa.zzcez.zzblm.onAdFailedToLoad(1);
            } catch (RemoteException e) {
                zzawo.zzd("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
