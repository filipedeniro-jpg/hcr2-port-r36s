package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcjs extends zzapy {
    private final /* synthetic */ zzbnu zzfzn;
    private final /* synthetic */ zzbmf zzfzo;
    private final /* synthetic */ zzbnb zzfzp;
    private final /* synthetic */ zzbqm zzfzq;

    zzcjs(zzcjo zzcjoVar, zzbnu zzbnuVar, zzbmf zzbmfVar, zzbnb zzbnbVar, zzbqm zzbqmVar) {
        this.zzfzn = zzbnuVar;
        this.zzfzo = zzbmfVar;
        this.zzfzp = zzbnbVar;
        this.zzfzq = zzbqmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final void zzai(IObjectWrapper iObjectWrapper) {
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final void zzaj(IObjectWrapper iObjectWrapper) {
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final void zzb(Bundle bundle) {
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final void zzd(IObjectWrapper iObjectWrapper, int i) {
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final void zze(IObjectWrapper iObjectWrapper, int i) {
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final void zzak(IObjectWrapper iObjectWrapper) {
        this.zzfzn.zzsf();
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final void zzam(IObjectWrapper iObjectWrapper) {
        this.zzfzn.zzse();
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final void zzan(IObjectWrapper iObjectWrapper) {
        this.zzfzo.onAdClicked();
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final void zzao(IObjectWrapper iObjectWrapper) {
        this.zzfzp.onAdLeftApplication();
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final void zza(IObjectWrapper iObjectWrapper, zzaqd zzaqdVar) {
        this.zzfzq.zza(zzaqdVar);
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final void zzal(IObjectWrapper iObjectWrapper) {
        this.zzfzq.zzqv();
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final void zzap(IObjectWrapper iObjectWrapper) throws RemoteException {
        this.zzfzp.onRewardedVideoCompleted();
    }
}
