package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationInterstitialAd;
import com.google.android.gms.ads.mediation.MediationInterstitialAdCallback;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzalu implements MediationAdLoadCallback<MediationInterstitialAd, MediationInterstitialAdCallback> {
    private final /* synthetic */ zzajj zzdeh;
    private final /* synthetic */ zzals zzdem;
    private final /* synthetic */ zzalc zzden;

    zzalu(zzals zzalsVar, zzalc zzalcVar, zzajj zzajjVar) {
        this.zzdem = zzalsVar;
        this.zzden = zzalcVar;
        this.zzdeh = zzajjVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final MediationInterstitialAdCallback onSuccess(MediationInterstitialAd mediationInterstitialAd) {
        if (mediationInterstitialAd == null) {
            zzawo.zzeu("Adapter incorrectly returned a null ad. The onFailure() callback should be called if an adapter fails to load an ad.");
            try {
                this.zzden.zzdg("Adapter returned null.");
                return null;
            } catch (RemoteException e) {
                zzawo.zzc("", e);
                return null;
            }
        }
        try {
            this.zzdem.zzdej = mediationInterstitialAd;
            this.zzden.zzsb();
        } catch (RemoteException e2) {
            zzawo.zzc("", e2);
        }
        return new zzaly(this.zzdeh);
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final void onFailure(String str) {
        try {
            this.zzden.zzdg(str);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }
}
