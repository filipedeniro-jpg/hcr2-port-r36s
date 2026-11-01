package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcup extends zzaqj {
    private final String zzccm;
    private final zzcvi zzfgh;
    private final zzcul zzgib;
    private final zzcto zzgic;

    @Nullable
    @GuardedBy("this")
    private zzbyf zzgid;

    public zzcup(@Nullable String str, zzcul zzculVar, zzcto zzctoVar, zzcvi zzcviVar) {
        this.zzccm = str;
        this.zzgib = zzculVar;
        this.zzgic = zzctoVar;
        this.zzfgh = zzcviVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final synchronized void zza(zztp zztpVar, zzaqo zzaqoVar) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        this.zzgic.zza(zzaqoVar);
        if (this.zzgid != null) {
            return;
        }
        this.zzgib.zza(zztpVar, this.zzccm, new zzcui(null), new zzcuo(this));
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final synchronized void zzl(IObjectWrapper iObjectWrapper) throws RemoteException {
        zza(iObjectWrapper, false);
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final synchronized void zza(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (this.zzgid == null) {
            zzatm.zzeu("Rewarded can not be shown before loaded");
            this.zzgic.zzcm(2);
        } else {
            this.zzgid.zzb(z, (Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final synchronized String getMediationAdapterClassName() throws RemoteException {
        if (this.zzgid == null) {
            return null;
        }
        return this.zzgid.getMediationAdapterClassName();
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final boolean isLoaded() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbyf zzbyfVar = this.zzgid;
        return (zzbyfVar == null || zzbyfVar.zzajd()) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final void zza(zzaql zzaqlVar) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        this.zzgic.zzb(zzaqlVar);
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final void zza(zzaqt zzaqtVar) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        this.zzgic.zzb(zzaqtVar);
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final void zza(zzwf zzwfVar) {
        if (zzwfVar == null) {
            this.zzgic.zza((AdMetadataListener) null);
        } else {
            this.zzgic.zza(new zzcur(this, zzwfVar));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final Bundle getAdMetadata() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbyf zzbyfVar = this.zzgid;
        return zzbyfVar != null ? zzbyfVar.getAdMetadata() : new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    @Nullable
    public final zzaqf zzpg() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbyf zzbyfVar = this.zzgid;
        if (zzbyfVar != null) {
            return zzbyfVar.zzpg();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final synchronized void zza(zzarb zzarbVar) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzcvi zzcviVar = this.zzfgh;
        zzcviVar.zzdnn = zzarbVar.zzdnn;
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzckg)).booleanValue()) {
            zzcviVar.zzdno = zzarbVar.zzdno;
        }
    }
}
