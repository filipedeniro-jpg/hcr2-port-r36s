package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzard implements MediationRewardedAdCallback {
    private final zzajj zzddq;

    public zzard(zzajj zzajjVar) {
        this.zzddq = zzajjVar;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdCallback
    public final void onAdOpened() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdOpened.");
        try {
            this.zzddq.onAdOpened();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdCallback
    public final void onAdClosed() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdClosed.");
        try {
            this.zzddq.onAdClosed();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationRewardedAdCallback
    public final void onUserEarnedReward(RewardItem rewardItem) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onUserEarnedReward.");
        try {
            this.zzddq.zza(new zzarc(rewardItem));
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdCallback
    public final void reportAdClicked() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called reportAdClicked.");
        try {
            this.zzddq.onAdClicked();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdCallback
    public final void reportAdImpression() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called reportAdImpression.");
        try {
            this.zzddq.onAdImpression();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationRewardedAdCallback
    public final void onAdFailedToShow(String str) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdFailedToShow.");
        String strValueOf = String.valueOf(str);
        zzawo.zzeu(strValueOf.length() != 0 ? "Mediation ad failed to show: ".concat(strValueOf) : new String("Mediation ad failed to show: "));
        try {
            this.zzddq.zzcm(0);
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationRewardedAdCallback
    public final void onVideoStart() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onVideoStart.");
        try {
            this.zzddq.zzrs();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationRewardedAdCallback
    public final void onVideoComplete() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onVideoComplete.");
        try {
            this.zzddq.zzrt();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }
}
