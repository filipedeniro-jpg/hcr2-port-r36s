package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.AdMetadataListener;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcux extends AdMetadataListener {
    private final /* synthetic */ zzcuv zzgii;
    private final /* synthetic */ zzvh zzgij;

    zzcux(zzcuv zzcuvVar, zzvh zzvhVar) {
        this.zzgii = zzcuvVar;
        this.zzgij = zzvhVar;
    }

    @Override // com.google.android.gms.ads.reward.AdMetadataListener
    public final void onAdMetadataChanged() {
        if (this.zzgii.zzgid != null) {
            try {
                this.zzgij.onAdMetadataChanged();
            } catch (RemoteException e) {
                zzatm.zze("#007 Could not call remote method.", e);
            }
        }
    }
}
