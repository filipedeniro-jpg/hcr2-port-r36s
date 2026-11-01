package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzclw extends zzuv {

    @GuardedBy("this")
    private boolean zzacz = false;
    private final String zzccm;
    private final zzcma<zzbju> zzgci;

    @Nullable
    @GuardedBy("this")
    private String zzgcj;

    @Nullable
    @GuardedBy("this")
    private String zzgck;

    public zzclw(zzcma<zzbju> zzcmaVar, String str) {
        this.zzgci = zzcmaVar;
        this.zzccm = str;
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final synchronized boolean isLoading() throws RemoteException {
        return this.zzgci.isLoading();
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final void zzb(zztp zztpVar) throws RemoteException {
        zza(zztpVar, 1);
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final synchronized void zza(zztp zztpVar, int i) throws RemoteException {
        this.zzgcj = null;
        this.zzgck = null;
        this.zzacz = this.zzgci.zza(zztpVar, this.zzccm, new zzcmb(i), new zzclv(this));
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final synchronized String getMediationAdapterClassName() {
        return this.zzgcj;
    }

    @Override // com.google.android.gms.internal.ads.zzuw
    public final synchronized String zzjp() {
        return this.zzgck;
    }

    static /* synthetic */ boolean zza(zzclw zzclwVar, boolean z) {
        zzclwVar.zzacz = false;
        return false;
    }
}
