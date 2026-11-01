package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcgu extends zzajm implements zzbnj {

    @GuardedBy("this")
    private zzajj zzder;

    @GuardedBy("this")
    private zzbni zzfxt;

    public final synchronized void zza(zzajj zzajjVar) {
        this.zzder = zzajjVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbnj
    public final synchronized void zza(zzbni zzbniVar) {
        this.zzfxt = zzbniVar;
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void onAdClicked() throws RemoteException {
        if (this.zzder != null) {
            this.zzder.onAdClicked();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void onAdClosed() throws RemoteException {
        if (this.zzder != null) {
            this.zzder.onAdClosed();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void onAdFailedToLoad(int i) throws RemoteException {
        if (this.zzder != null) {
            this.zzder.onAdFailedToLoad(i);
        }
        if (this.zzfxt != null) {
            this.zzfxt.onAdFailedToLoad(i);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void onAdLeftApplication() throws RemoteException {
        if (this.zzder != null) {
            this.zzder.onAdLeftApplication();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void onAdOpened() throws RemoteException {
        if (this.zzder != null) {
            this.zzder.onAdOpened();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void onAdLoaded() throws RemoteException {
        if (this.zzder != null) {
            this.zzder.onAdLoaded();
        }
        if (this.zzfxt != null) {
            this.zzfxt.onAdLoaded();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void zza(zzajo zzajoVar) throws RemoteException {
        if (this.zzder != null) {
            this.zzder.zza(zzajoVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void onAdImpression() throws RemoteException {
        if (this.zzder != null) {
            this.zzder.onAdImpression();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void onAppEvent(String str, String str2) throws RemoteException {
        if (this.zzder != null) {
            this.zzder.onAppEvent(str, str2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void zza(zzabo zzaboVar, String str) throws RemoteException {
        if (this.zzder != null) {
            this.zzder.zza(zzaboVar, str);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void onVideoEnd() throws RemoteException {
        if (this.zzder != null) {
            this.zzder.onVideoEnd();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void zzde(String str) throws RemoteException {
        if (this.zzder != null) {
            this.zzder.zzde(str);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void zzrs() throws RemoteException {
        if (this.zzder != null) {
            this.zzder.zzrs();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void onVideoPlay() throws RemoteException {
        if (this.zzder != null) {
            this.zzder.onVideoPlay();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void zzb(zzaqd zzaqdVar) throws RemoteException {
        if (this.zzder != null) {
            this.zzder.zzb(zzaqdVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void onVideoPause() throws RemoteException {
        if (this.zzder != null) {
            this.zzder.onVideoPause();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void zzb(Bundle bundle) throws RemoteException {
        if (this.zzder != null) {
            this.zzder.zzb(bundle);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void zzrt() throws RemoteException {
        if (this.zzder != null) {
            this.zzder.zzrt();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void zzcm(int i) throws RemoteException {
        if (this.zzder != null) {
            this.zzder.zzcm(i);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final synchronized void zza(zzaqf zzaqfVar) throws RemoteException {
        if (this.zzder != null) {
            this.zzder.zza(zzaqfVar);
        }
    }
}
