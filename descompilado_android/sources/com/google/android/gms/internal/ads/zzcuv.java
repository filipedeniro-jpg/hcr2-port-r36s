package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
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
public final class zzcuv extends zzapk {
    private final zzcvi zzfgh;

    @GuardedBy("this")
    private boolean zzgcg = false;
    private final zzcul zzgib;
    private final zzcto zzgic;

    @Nullable
    @GuardedBy("this")
    private zzbyf zzgid;

    public zzcuv(zzcul zzculVar, zzcto zzctoVar, zzcvi zzcviVar) {
        this.zzgib = zzculVar;
        this.zzgic = zzctoVar;
        this.zzfgh = zzcviVar;
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void setAppPackageName(String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final synchronized void zza(zzapu zzapuVar) throws RemoteException {
        Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
        if (zzyv.zzck(zzapuVar.zzccm)) {
            return;
        }
        if (zzalf()) {
            if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcrc)).booleanValue()) {
                return;
            }
        }
        zzcui zzcuiVar = new zzcui(null);
        this.zzgid = null;
        this.zzgib.zza(zzapuVar.zzdii, zzapuVar.zzccm, zzcuiVar, new zzcuu(this));
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void destroy() throws RemoteException {
        zzp(null);
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final synchronized void zzp(IObjectWrapper iObjectWrapper) {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        Context context = null;
        this.zzgic.zza((AdMetadataListener) null);
        if (this.zzgid != null) {
            if (iObjectWrapper != null) {
                context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
            }
            this.zzgid.zzafi().zzbv(context);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void pause() {
        zzn(null);
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final synchronized void zzn(IObjectWrapper iObjectWrapper) {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        if (this.zzgid != null) {
            this.zzgid.zzafi().zzbt(iObjectWrapper == null ? null : (Context) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void resume() {
        zzo(null);
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final synchronized void zzo(IObjectWrapper iObjectWrapper) {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        if (this.zzgid != null) {
            this.zzgid.zzafi().zzbu(iObjectWrapper == null ? null : (Context) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final synchronized void show() throws RemoteException {
        zzm(null);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0019  */
    @Override // com.google.android.gms.internal.ads.zzapl
    public final synchronized void zzm(@Nullable IObjectWrapper iObjectWrapper) throws RemoteException {
        Activity activity;
        Preconditions.checkMainThread("showAd must be called on the main UI thread.");
        if (this.zzgid == null) {
            return;
        }
        if (iObjectWrapper != null) {
            Object objUnwrap = ObjectWrapper.unwrap(iObjectWrapper);
            if (objUnwrap instanceof Activity) {
                activity = (Activity) objUnwrap;
            } else {
                activity = null;
            }
        } else {
            activity = null;
        }
        this.zzgid.zzb(this.zzgcg, activity);
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final boolean zzph() {
        zzbyf zzbyfVar = this.zzgid;
        return zzbyfVar != null && zzbyfVar.zzph();
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final synchronized String getMediationAdapterClassName() throws RemoteException {
        if (this.zzgid == null) {
            return null;
        }
        return this.zzgid.getMediationAdapterClassName();
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final boolean isLoaded() throws RemoteException {
        Preconditions.checkMainThread("isLoaded must be called on the main UI thread.");
        return zzalf();
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void zza(zzapo zzapoVar) throws RemoteException {
        Preconditions.checkMainThread("setRewardedVideoAdListener can only be called from the UI thread.");
        this.zzgic.zzb(zzapoVar);
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void zza(zzapj zzapjVar) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedAdSkuListener");
        this.zzgic.zzb(zzapjVar);
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void zza(zzvh zzvhVar) {
        Preconditions.checkMainThread("setAdMetadataListener can only be called from the UI thread.");
        if (zzvhVar == null) {
            this.zzgic.zza((AdMetadataListener) null);
        } else {
            this.zzgic.zza(new zzcux(this, zzvhVar));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final Bundle getAdMetadata() {
        Preconditions.checkMainThread("getAdMetadata can only be called from the UI thread.");
        zzbyf zzbyfVar = this.zzgid;
        return zzbyfVar != null ? zzbyfVar.getAdMetadata() : new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final synchronized void setUserId(String str) throws RemoteException {
        Preconditions.checkMainThread("setUserId must be called on the main UI thread.");
        this.zzfgh.zzdnn = str;
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final synchronized void setCustomData(String str) throws RemoteException {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzckg)).booleanValue()) {
            Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setCustomData");
            this.zzfgh.zzdno = str;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final synchronized void setImmersiveMode(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzgcg = z;
    }

    private final synchronized boolean zzalf() {
        return (this.zzgid == null || this.zzgid.isClosed()) ? false : true;
    }
}
