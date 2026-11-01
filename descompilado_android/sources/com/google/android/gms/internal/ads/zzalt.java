package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzalt implements MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> {
    private final /* synthetic */ zzajj zzdeh;
    private final /* synthetic */ zzali zzdel;
    private final /* synthetic */ zzals zzdem;

    zzalt(zzals zzalsVar, zzali zzaliVar, zzajj zzajjVar) {
        this.zzdem = zzalsVar;
        this.zzdel = zzaliVar;
        this.zzdeh = zzajjVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final MediationRewardedAdCallback onSuccess(MediationRewardedAd mediationRewardedAd) {
        if (mediationRewardedAd == null) {
            zzawo.zzeu("Adapter incorrectly returned a null ad. The onFailure() callback should be called if an adapter fails to load an ad.");
            try {
                this.zzdel.zzdg("Adapter returned null.");
                return null;
            } catch (RemoteException e) {
                zzawo.zzc("", e);
                return null;
            }
        }
        try {
            this.zzdem.zzddi = mediationRewardedAd;
            this.zzdel.zzsb();
        } catch (RemoteException e2) {
            zzawo.zzc("", e2);
        }
        return new zzaly(this.zzdeh);
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final void onFailure(String str) {
        try {
            this.zzdel.zzdg(str);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }
}
