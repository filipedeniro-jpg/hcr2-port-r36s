package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcgt extends zzapy implements zzbnj {

    @GuardedBy("this")
    private zzapz zzfxs;

    @GuardedBy("this")
    private zzbni zzfxt;

    @GuardedBy("this")
    private zzbqp zzfxu;

    public final synchronized void zza(zzapz zzapzVar) {
        this.zzfxs = zzapzVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbnj
    public final synchronized void zza(zzbni zzbniVar) {
        this.zzfxt = zzbniVar;
    }

    public final synchronized void zza(zzbqp zzbqpVar) {
        this.zzfxu = zzbqpVar;
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final synchronized void zzai(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (this.zzfxs != null) {
            this.zzfxs.zzai(iObjectWrapper);
        }
        if (this.zzfxu != null) {
            this.zzfxu.onInitializationSucceeded();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final synchronized void zzd(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        if (this.zzfxs != null) {
            this.zzfxs.zzd(iObjectWrapper, i);
        }
        if (this.zzfxu != null) {
            this.zzfxu.zzdf(i);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final synchronized void zzal(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (this.zzfxs != null) {
            this.zzfxs.zzal(iObjectWrapper);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final synchronized void zza(IObjectWrapper iObjectWrapper, zzaqd zzaqdVar) throws RemoteException {
        if (this.zzfxs != null) {
            this.zzfxs.zza(iObjectWrapper, zzaqdVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final synchronized void zzan(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (this.zzfxs != null) {
            this.zzfxs.zzan(iObjectWrapper);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final synchronized void zzam(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (this.zzfxs != null) {
            this.zzfxs.zzam(iObjectWrapper);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final synchronized void zze(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        if (this.zzfxs != null) {
            this.zzfxs.zze(iObjectWrapper, i);
        }
        if (this.zzfxt != null) {
            this.zzfxt.onAdFailedToLoad(i);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final synchronized void zzao(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (this.zzfxs != null) {
            this.zzfxs.zzao(iObjectWrapper);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final synchronized void zzap(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (this.zzfxs != null) {
            this.zzfxs.zzap(iObjectWrapper);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final synchronized void zzak(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (this.zzfxs != null) {
            this.zzfxs.zzak(iObjectWrapper);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final synchronized void zzaj(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (this.zzfxs != null) {
            this.zzfxs.zzaj(iObjectWrapper);
        }
        if (this.zzfxt != null) {
            this.zzfxt.onAdLoaded();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapz
    public final synchronized void zzb(Bundle bundle) throws RemoteException {
        if (this.zzfxs != null) {
            this.zzfxs.zzb(bundle);
        }
    }
}
