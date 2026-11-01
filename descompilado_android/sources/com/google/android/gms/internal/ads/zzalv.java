package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.rtb.SignalCallbacks;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzalv implements SignalCallbacks {
    private final /* synthetic */ zzalo zzdeo;

    zzalv(zzals zzalsVar, zzalo zzaloVar) {
        this.zzdeo = zzaloVar;
    }

    @Override // com.google.android.gms.ads.mediation.rtb.SignalCallbacks
    public final void onSuccess(String str) {
        try {
            this.zzdeo.zzdi(str);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.rtb.SignalCallbacks
    public final void onFailure(String str) {
        try {
            this.zzdeo.onFailure(str);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }
}
