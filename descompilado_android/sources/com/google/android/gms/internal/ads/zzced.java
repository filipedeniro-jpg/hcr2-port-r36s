package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzced implements zzdcg<ParcelFileDescriptor> {
    private final /* synthetic */ zzaoe zzfux;

    zzced(zzcdt zzcdtVar, zzaoe zzaoeVar) {
        this.zzfux = zzaoeVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        try {
            this.zzfux.zza(zzauw.zza(th, zzcce.zzd(th)));
        } catch (RemoteException e) {
            zzatm.zza("Service can't call client", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            this.zzfux.zzb(parcelFileDescriptor);
        } catch (RemoteException e) {
            zzatm.zza("Service can't call client", e);
        }
    }
}
