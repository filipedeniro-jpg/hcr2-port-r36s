package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzclh extends zzuv {
    private final zzclw zzgbe;

    public zzclh(Context context, zzbds zzbdsVar, zzcvm zzcvmVar, zzbui zzbuiVar, zzur zzurVar) {
        zzcly zzclyVar = new zzcly(zzbuiVar);
        zzclyVar.zzc(zzurVar);
        this.zzgbe = new zzclw(new zzcme(zzbdsVar, context, zzclyVar, zzcvmVar), zzcvmVar.zzamy());
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final synchronized boolean isLoading() throws RemoteException {
        return this.zzgbe.isLoading();
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final void zzb(zztp zztpVar) throws RemoteException {
        this.zzgbe.zzb(zztpVar);
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final synchronized void zza(zztp zztpVar, int i) throws RemoteException {
        this.zzgbe.zza(zztpVar, i);
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final synchronized String getMediationAdapterClassName() {
        return this.zzgbe.getMediationAdapterClassName();
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final synchronized String zzjp() {
        return this.zzgbe.zzjp();
    }
}
