package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzakd extends zzajm {
    private final zzapz zzddg;
    private final Adapter zzddp;

    zzakd(Adapter adapter, zzapz zzapzVar) {
        this.zzddp = adapter;
        this.zzddg = zzapzVar;
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAdImpression() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAdLeftApplication() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAppEvent(String str, String str2) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onVideoEnd() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onVideoPause() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onVideoPlay() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void zza(zzabo zzaboVar, String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void zza(zzajo zzajoVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void zzb(Bundle bundle) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void zzb(zzaqd zzaqdVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void zzcm(int i) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void zzde(String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAdLoaded() throws RemoteException {
        zzapz zzapzVar = this.zzddg;
        if (zzapzVar != null) {
            zzapzVar.zzaj(ObjectWrapper.wrap(this.zzddp));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAdOpened() throws RemoteException {
        zzapz zzapzVar = this.zzddg;
        if (zzapzVar != null) {
            zzapzVar.zzak(ObjectWrapper.wrap(this.zzddp));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAdClosed() throws RemoteException {
        zzapz zzapzVar = this.zzddg;
        if (zzapzVar != null) {
            zzapzVar.zzam(ObjectWrapper.wrap(this.zzddp));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void zza(zzaqf zzaqfVar) throws RemoteException {
        zzapz zzapzVar = this.zzddg;
        if (zzapzVar != null) {
            zzapzVar.zza(ObjectWrapper.wrap(this.zzddp), new zzaqd(zzaqfVar.getType(), zzaqfVar.getAmount()));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void zzrs() throws RemoteException {
        zzapz zzapzVar = this.zzddg;
        if (zzapzVar != null) {
            zzapzVar.zzal(ObjectWrapper.wrap(this.zzddp));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void zzrt() throws RemoteException {
        zzapz zzapzVar = this.zzddg;
        if (zzapzVar != null) {
            zzapzVar.zzap(ObjectWrapper.wrap(this.zzddp));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAdClicked() throws RemoteException {
        zzapz zzapzVar = this.zzddg;
        if (zzapzVar != null) {
            zzapzVar.zzan(ObjectWrapper.wrap(this.zzddp));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAdFailedToLoad(int i) throws RemoteException {
        zzapz zzapzVar = this.zzddg;
        if (zzapzVar != null) {
            zzapzVar.zze(ObjectWrapper.wrap(this.zzddp), i);
        }
    }
}
