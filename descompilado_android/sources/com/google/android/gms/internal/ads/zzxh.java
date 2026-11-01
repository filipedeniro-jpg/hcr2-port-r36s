package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzxh extends zzuv {
    final /* synthetic */ zzxf zzcez;

    private zzxh(zzxf zzxfVar) {
        this.zzcez = zzxfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final boolean isLoading() throws RemoteException {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final String zzjp() throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final void zzb(zztp zztpVar) throws RemoteException {
        zza(zztpVar, 1);
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final void zza(zztp zztpVar, int i) throws RemoteException {
        zzawo.zzes("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzawe.zzzb.post(new zzxk(this));
    }
}
