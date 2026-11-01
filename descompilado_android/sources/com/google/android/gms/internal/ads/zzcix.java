package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcix extends zzalh {
    private zzcfl<zzalj, zzcgu> zzfxf;

    private zzcix(zzciw zzciwVar, zzcfl<zzalj, zzcgu> zzcflVar) {
        this.zzfxf = zzcflVar;
    }

    @Override // com.google.android.gms.internal.ads.zzali
    public final void zzsb() throws RemoteException {
        ((zzcgu) this.zzfxf.zzfwt).onAdLoaded();
    }

    @Override // com.google.android.gms.internal.ads.zzali
    public final void zzdg(String str) throws RemoteException {
        ((zzcgu) this.zzfxf.zzfwt).onAdFailedToLoad(0);
    }
}
