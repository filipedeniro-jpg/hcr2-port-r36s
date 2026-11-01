package com.google.firebase.auth.internal;

import com.google.android.gms.common.api.internal.BackgroundDetector;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzax implements BackgroundDetector.BackgroundStateChangeListener {
    private final /* synthetic */ zzau zza;

    zzax(zzau zzauVar) {
        this.zza = zzauVar;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean z) {
        if (z) {
            this.zza.zzc = true;
            this.zza.zza();
        } else {
            this.zza.zzc = false;
            if (this.zza.zzb()) {
                this.zza.zzb.zza();
            }
        }
    }
}
