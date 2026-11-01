package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzclp implements zzth {

    @GuardedBy("this")
    private zzuq zzgby;

    public final synchronized void zzb(zzuq zzuqVar) {
        this.zzgby = zzuqVar;
    }

    @Override // com.google.android.gms.internal.ads.zzth
    public final synchronized void onAdClicked() {
        if (this.zzgby != null) {
            try {
                this.zzgby.onAdClicked();
            } catch (RemoteException e) {
                zzatm.zzd("Remote Exception at onAdClicked.", e);
            }
        }
    }
}
