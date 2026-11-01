package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzciz extends zzcjz {
    private zzbqm zzfza;
    private zzbmk zzfzb;

    public zzciz(zzbmf zzbmfVar, zzbmo zzbmoVar, zzbnb zzbnbVar, zzbnl zzbnlVar, zzbmk zzbmkVar, zzbop zzbopVar, zzbqr zzbqrVar, zzbnu zzbnuVar, zzbqm zzbqmVar) {
        super(zzbmfVar, zzbmoVar, zzbnbVar, zzbnlVar, zzbopVar, zzbnuVar, zzbqrVar);
        this.zzfza = zzbqmVar;
        this.zzfzb = zzbmkVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcjz, com.google.android.gms.internal.ads.zzajj
    public final void zzb(Bundle bundle) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzcjz, com.google.android.gms.internal.ads.zzajj
    public final void zzrs() {
        this.zzfza.zzqv();
    }

    @Override // com.google.android.gms.internal.ads.zzcjz, com.google.android.gms.internal.ads.zzajj
    public final void zzcm(int i) throws RemoteException {
        this.zzfzb.zzcm(i);
    }

    @Override // com.google.android.gms.internal.ads.zzcjz, com.google.android.gms.internal.ads.zzajj
    public final void zzrt() throws RemoteException {
        this.zzfza.zzqw();
    }

    @Override // com.google.android.gms.internal.ads.zzcjz, com.google.android.gms.internal.ads.zzajj
    public final void zza(zzaqf zzaqfVar) throws RemoteException {
        this.zzfza.zza(new zzaqd(zzaqfVar.getType(), zzaqfVar.getAmount()));
    }

    @Override // com.google.android.gms.internal.ads.zzcjz, com.google.android.gms.internal.ads.zzajj
    public final void zzb(zzaqd zzaqdVar) {
        this.zzfza.zza(zzaqdVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcjz, com.google.android.gms.internal.ads.zzajj
    public final void onVideoEnd() {
        this.zzfza.zzqw();
    }
}
