package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbyb extends VideoController.VideoLifecycleCallbacks {
    private final zzbtx zzfjc;

    public zzbyb(zzbtx zzbtxVar) {
        this.zzfjc = zzbtxVar;
    }

    @Override // com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks
    public final void onVideoStart() {
        zzwl zzwlVarZza = zza(this.zzfjc);
        if (zzwlVarZza == null) {
            return;
        }
        try {
            zzwlVarZza.onVideoStart();
        } catch (RemoteException e) {
            zzatm.zzd("Unable to call onVideoEnd()", e);
        }
    }

    @Override // com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks
    public final void onVideoPause() {
        zzwl zzwlVarZza = zza(this.zzfjc);
        if (zzwlVarZza == null) {
            return;
        }
        try {
            zzwlVarZza.onVideoPause();
        } catch (RemoteException e) {
            zzatm.zzd("Unable to call onVideoEnd()", e);
        }
    }

    @Override // com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks
    public final void onVideoEnd() {
        zzwl zzwlVarZza = zza(this.zzfjc);
        if (zzwlVarZza == null) {
            return;
        }
        try {
            zzwlVarZza.onVideoEnd();
        } catch (RemoteException e) {
            zzatm.zzd("Unable to call onVideoEnd()", e);
        }
    }

    private static zzwl zza(zzbtx zzbtxVar) {
        zzwk videoController = zzbtxVar.getVideoController();
        if (videoController == null) {
            return null;
        }
        try {
            return videoController.zzov();
        } catch (RemoteException unused) {
            return null;
        }
    }
}
