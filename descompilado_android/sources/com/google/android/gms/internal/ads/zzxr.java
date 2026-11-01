package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzxr extends zzapk {
    private zzapo zzcfe;

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void destroy() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final boolean isLoaded() throws RemoteException {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void pause() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void resume() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void setAppPackageName(String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void setCustomData(String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void setImmersiveMode(boolean z) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void setUserId(String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void show() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void zza(zzapj zzapjVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void zza(zzvh zzvhVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void zzm(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void zzn(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void zzo(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void zzp(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final boolean zzph() {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void zza(zzapu zzapuVar) throws RemoteException {
        zzawo.zzes("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzawe.zzzb.post(new zzxu(this));
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final void zza(zzapo zzapoVar) throws RemoteException {
        this.zzcfe = zzapoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzapl
    public final Bundle getAdMetadata() throws RemoteException {
        return new Bundle();
    }
}
