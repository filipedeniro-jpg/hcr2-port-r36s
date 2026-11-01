package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzakc implements MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> {
    private final /* synthetic */ zzajj zzddm;
    private final /* synthetic */ Adapter zzddn;
    private final /* synthetic */ zzajz zzddo;

    zzakc(zzajz zzajzVar, zzajj zzajjVar, Adapter adapter) {
        this.zzddo = zzajzVar;
        this.zzddm = zzajjVar;
        this.zzddn = adapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final MediationRewardedAdCallback onSuccess(MediationRewardedAd mediationRewardedAd) {
        try {
            this.zzddo.zzddi = mediationRewardedAd;
            this.zzddm.onAdLoaded();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
        return new zzard(this.zzddm);
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final void onFailure(String str) {
        try {
            String canonicalName = this.zzddn.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 30 + String.valueOf(str).length());
            sb.append(canonicalName);
            sb.append("failed to loaded medation ad: ");
            sb.append(str);
            zzawo.zzdv(sb.toString());
            this.zzddm.onAdFailedToLoad(0);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }
}
