package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationBannerAd;
import com.google.android.gms.ads.mediation.MediationBannerAdCallback;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzalr implements MediationAdLoadCallback<MediationBannerAd, MediationBannerAdCallback> {
    private final /* synthetic */ zzakx zzdeg;
    private final /* synthetic */ zzajj zzdeh;

    zzalr(zzals zzalsVar, zzakx zzakxVar, zzajj zzajjVar) {
        this.zzdeg = zzakxVar;
        this.zzdeh = zzajjVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final MediationBannerAdCallback onSuccess(MediationBannerAd mediationBannerAd) {
        if (mediationBannerAd == null) {
            zzawo.zzeu("Adapter incorrectly returned a null ad. The onFailure() callback should be called if an adapter fails to load an ad.");
            try {
                this.zzdeg.zzdg("Adapter returned null.");
                return null;
            } catch (RemoteException e) {
                zzawo.zzc("", e);
                return null;
            }
        }
        try {
            this.zzdeg.zzaa(ObjectWrapper.wrap(mediationBannerAd.getView()));
        } catch (RemoteException e2) {
            zzawo.zzc("", e2);
        }
        return new zzaly(this.zzdeh);
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final void onFailure(String str) {
        try {
            this.zzdeg.zzdg(str);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }
}
