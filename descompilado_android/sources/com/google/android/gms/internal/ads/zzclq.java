package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzclq implements zzbml {

    @GuardedBy("this")
    private zzqu zzgbz;

    public final synchronized void zzb(zzqu zzquVar) {
        this.zzgbz = zzquVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbml
    public final synchronized void onAdFailedToLoad(int i) {
        if (this.zzgbz != null) {
            try {
                this.zzgbz.zzbp(i);
            } catch (RemoteException e) {
                zzatm.zzd("Remote Exception at onAdFailedToLoad.", e);
            }
        }
    }
}
