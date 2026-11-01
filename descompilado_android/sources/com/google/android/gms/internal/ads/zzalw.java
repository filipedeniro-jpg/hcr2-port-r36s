package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationNativeAdCallback;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzalw implements MediationAdLoadCallback<UnifiedNativeAdMapper, MediationNativeAdCallback> {
    private final /* synthetic */ zzajj zzdeh;
    private final /* synthetic */ zzald zzdep;

    zzalw(zzals zzalsVar, zzald zzaldVar, zzajj zzajjVar) {
        this.zzdep = zzaldVar;
        this.zzdeh = zzajjVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final MediationNativeAdCallback onSuccess(UnifiedNativeAdMapper unifiedNativeAdMapper) {
        if (unifiedNativeAdMapper == null) {
            zzawo.zzeu("Adapter incorrectly returned a null ad. The onFailure() callback should be called if an adapter fails to load an ad.");
            try {
                this.zzdep.zzdg("Adapter returned null.");
                return null;
            } catch (RemoteException e) {
                zzawo.zzc("", e);
                return null;
            }
        }
        try {
            this.zzdep.zza(new zzaky(unifiedNativeAdMapper));
        } catch (RemoteException e2) {
            zzawo.zzc("", e2);
        }
        return new zzaly(this.zzdeh);
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final void onFailure(String str) {
        try {
            this.zzdep.zzdg(str);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }
}
