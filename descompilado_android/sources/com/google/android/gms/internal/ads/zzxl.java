package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzxl extends zzvt {
    private zzafe zzcfb;

    @Override // com.google.android.gms.internal.ads.zzvu
    public final String getVersionString() {
        return "";
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void setAppMuted(boolean z) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void setAppVolume(float f) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void zza(zzajd zzajdVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void zza(zzxw zzxwVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void zzb(String str, IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void zzby(String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void zzbz(String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void zzc(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final float zzoo() throws RemoteException {
        return 1.0f;
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final boolean zzop() throws RemoteException {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void initialize() throws RemoteException {
        zzawo.zzes("The initialization is not processed because MobileAdsSettingsManager is not created successfully.");
        zzawe.zzzb.post(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzxo
            private final zzxl zzcfd;

            {
                this.zzcfd = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzcfd.zzpf();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void zza(zzafe zzafeVar) throws RemoteException {
        this.zzcfb = zzafeVar;
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final List<zzaex> zzoq() throws RemoteException {
        return Collections.EMPTY_LIST;
    }

    final /* synthetic */ void zzpf() {
        zzafe zzafeVar = this.zzcfb;
        if (zzafeVar != null) {
            try {
                zzafeVar.zzc(Collections.EMPTY_LIST);
            } catch (RemoteException e) {
                zzawo.zzd("Could not notify onComplete event.", e);
            }
        }
    }
}
