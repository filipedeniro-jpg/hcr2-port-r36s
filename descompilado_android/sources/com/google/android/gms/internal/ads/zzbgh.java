package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgh implements zzbms {
    private final zzcvs zzfap;

    public zzbgh(zzcvs zzcvsVar) {
        this.zzfap = zzcvsVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbms
    public final void zzbt(@Nullable Context context) {
        try {
            this.zzfap.pause();
        } catch (zzcvr e) {
            zzatm.zzd("Cannot invoke onPause for the mediation adapter.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbms
    public final void zzbu(@Nullable Context context) {
        try {
            this.zzfap.resume();
            if (context != null) {
                this.zzfap.onContextChanged(context);
            }
        } catch (zzcvr e) {
            zzatm.zzd("Cannot invoke onResume for the mediation adapter.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbms
    public final void zzbv(@Nullable Context context) {
        try {
            this.zzfap.destroy();
        } catch (zzcvr e) {
            zzatm.zzd("Cannot invoke onDestroy for the mediation adapter.", e);
        }
    }
}
