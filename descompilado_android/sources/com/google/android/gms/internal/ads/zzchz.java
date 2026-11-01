package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzchz extends zzalg {
    private zzcfl<zzalj, zzcgu> zzfxf;
    private final /* synthetic */ zzchx zzfym;

    private zzchz(zzchx zzchxVar, zzcfl<zzalj, zzcgu> zzcflVar) {
        this.zzfym = zzchxVar;
        this.zzfxf = zzcflVar;
    }

    @Override // com.google.android.gms.internal.ads.zzald
    public final void zza(zzajw zzajwVar) throws RemoteException {
        this.zzfym.zzfyl = zzajwVar;
        ((zzcgu) this.zzfxf.zzfwt).onAdLoaded();
    }

    @Override // com.google.android.gms.internal.ads.zzald
    public final void zzdg(String str) throws RemoteException {
        ((zzcgu) this.zzfxf.zzfwt).onAdFailedToLoad(0);
    }
}
