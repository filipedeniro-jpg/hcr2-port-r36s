package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzxu implements Runnable {
    private final /* synthetic */ zzxr zzcfh;

    zzxu(zzxr zzxrVar) {
        this.zzcfh = zzxrVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzcfh.zzcfe != null) {
            try {
                this.zzcfh.zzcfe.onRewardedVideoAdFailedToLoad(1);
            } catch (RemoteException e) {
                zzawo.zzd("Could not notify onRewardedVideoAdFailedToLoad event.", e);
            }
        }
    }
}
