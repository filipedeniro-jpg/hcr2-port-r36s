package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController;
import java.util.Set;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbqr extends zzbos<VideoController.VideoLifecycleCallbacks> {

    @GuardedBy("this")
    private boolean zzegz;

    protected zzbqr(Set<zzbqc<VideoController.VideoLifecycleCallbacks>> set) {
        super(set);
    }

    public final void onVideoPause() {
        zza(zzbqu.zzfgm);
    }

    public final void onVideoEnd() {
        zza(zzbqt.zzfgm);
    }

    public final synchronized void onVideoStart() {
        zza(zzbqw.zzfgm);
        this.zzegz = true;
    }

    public final synchronized void onVideoPlay() {
        if (!this.zzegz) {
            zza(zzbqv.zzfgm);
            this.zzegz = true;
        }
        zza(zzbqy.zzfgm);
    }
}
