package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.view.Surface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzox {
    private final Handler handler;
    private final zzou zzbje;

    public zzox(Handler handler, zzou zzouVar) {
        this.handler = zzouVar != null ? (Handler) zznt.checkNotNull(handler) : null;
        this.zzbje = zzouVar;
    }

    public final void zza(zzij zzijVar) {
        if (this.zzbje != null) {
            this.handler.post(new zzow(this, zzijVar));
        }
    }

    public final void zza(String str, long j, long j2) {
        if (this.zzbje != null) {
            this.handler.post(new zzoz(this, str, j, j2));
        }
    }

    public final void zzb(zzgq zzgqVar) {
        if (this.zzbje != null) {
            this.handler.post(new zzoy(this, zzgqVar));
        }
    }

    public final void zzf(int i, long j) {
        if (this.zzbje != null) {
            this.handler.post(new zzpb(this, i, j));
        }
    }

    public final void zzb(int i, int i2, int i3, float f) {
        if (this.zzbje != null) {
            this.handler.post(new zzpa(this, i, i2, i3, f));
        }
    }

    public final void zzb(Surface surface) {
        if (this.zzbje != null) {
            this.handler.post(new zzpd(this, surface));
        }
    }

    public final void zzb(zzij zzijVar) {
        if (this.zzbje != null) {
            this.handler.post(new zzpc(this, zzijVar));
        }
    }
}
