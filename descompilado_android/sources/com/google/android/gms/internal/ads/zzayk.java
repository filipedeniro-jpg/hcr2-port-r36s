package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(14)
public final class zzayk {
    private long zzdyl;
    private final long zzdyk = TimeUnit.MILLISECONDS.toNanos(((Long) zzuo.zzoj().zzd(zzyt.zzchk)).longValue());
    private boolean zzdym = true;

    zzayk() {
    }

    public final void zzws() {
        this.zzdym = true;
    }

    public final void zza(SurfaceTexture surfaceTexture, zzayb zzaybVar) {
        if (zzaybVar == null) {
            return;
        }
        long timestamp = surfaceTexture.getTimestamp();
        if (this.zzdym || Math.abs(timestamp - this.zzdyl) >= this.zzdyk) {
            this.zzdym = false;
            this.zzdyl = timestamp;
            zzatv.zzdsk.post(new zzayn(this, zzaybVar));
        }
    }
}
