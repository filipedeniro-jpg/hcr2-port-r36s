package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.AdMetadataListener;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcur extends AdMetadataListener {
    private final /* synthetic */ zzcup zzgia;
    private final /* synthetic */ zzwf zzgih;

    zzcur(zzcup zzcupVar, zzwf zzwfVar) {
        this.zzgia = zzcupVar;
        this.zzgih = zzwfVar;
    }

    @Override // com.google.android.gms.ads.reward.AdMetadataListener
    public final void onAdMetadataChanged() {
        if (this.zzgia.zzgid != null) {
            try {
                this.zzgih.onAdMetadataChanged();
            } catch (RemoteException e) {
                zzatm.zze("#007 Could not call remote method.", e);
            }
        }
    }
}
