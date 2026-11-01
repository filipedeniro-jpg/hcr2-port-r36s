package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcls implements zzbmg, zzbml, zzbmt, zzbnm, zzth {

    @GuardedBy("this")
    private zzur zzgcb;

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onRewardedVideoCompleted() {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onRewardedVideoStarted() {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void zzb(zzape zzapeVar, String str, String str2) {
    }

    public final synchronized void zzc(zzur zzurVar) {
        this.zzgcb = zzurVar;
    }

    public final synchronized zzur zzale() {
        return this.zzgcb;
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final synchronized void onAdClosed() {
        if (this.zzgcb != null) {
            try {
                this.zzgcb.onAdClosed();
            } catch (RemoteException e) {
                zzatm.zzd("Remote Exception at onAdClosed.", e);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbml
    public final synchronized void onAdFailedToLoad(int i) {
        if (this.zzgcb != null) {
            try {
                this.zzgcb.onAdFailedToLoad(i);
            } catch (RemoteException e) {
                zzatm.zzd("Remote Exception at onAdFailedToLoad.", e);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final synchronized void onAdLeftApplication() {
        if (this.zzgcb != null) {
            try {
                this.zzgcb.onAdLeftApplication();
            } catch (RemoteException e) {
                zzatm.zzd("Remote Exception at onAdLeftApplication.", e);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbnm
    public final synchronized void onAdLoaded() {
        if (this.zzgcb != null) {
            try {
                this.zzgcb.onAdLoaded();
            } catch (RemoteException e) {
                zzatm.zzd("Remote Exception at onAdLoaded.", e);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final synchronized void onAdOpened() {
        if (this.zzgcb != null) {
            try {
                this.zzgcb.onAdOpened();
            } catch (RemoteException e) {
                zzatm.zzd("Remote Exception at onAdOpened.", e);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzth
    public final synchronized void onAdClicked() {
        if (this.zzgcb != null) {
            try {
                this.zzgcb.onAdClicked();
            } catch (RemoteException e) {
                zzatm.zzd("Remote Exception at onAdClicked.", e);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbmt
    public final synchronized void onAdImpression() {
        if (this.zzgcb != null) {
            try {
                this.zzgcb.onAdImpression();
            } catch (RemoteException e) {
                zzatm.zzd("Remote Exception at onAdImpression.", e);
            }
        }
    }
}
