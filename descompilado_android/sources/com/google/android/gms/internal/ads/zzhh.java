package com.google.android.gms.internal.ads;

import android.os.Handler;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzhh {
    private final Handler handler;
    private final zzhi zzaha;

    public zzhh(Handler handler, zzhi zzhiVar) {
        this.handler = zzhiVar != null ? (Handler) zznt.checkNotNull(handler) : null;
        this.zzaha = zzhiVar;
    }

    public final void zza(zzij zzijVar) {
        if (this.zzaha != null) {
            this.handler.post(new zzhk(this, zzijVar));
        }
    }

    public final void zza(String str, long j, long j2) {
        if (this.zzaha != null) {
            this.handler.post(new zzhj(this, str, j, j2));
        }
    }

    public final void zzb(zzgq zzgqVar) {
        if (this.zzaha != null) {
            this.handler.post(new zzhm(this, zzgqVar));
        }
    }

    public final void zza(int i, long j, long j2) {
        if (this.zzaha != null) {
            this.handler.post(new zzhl(this, i, j, j2));
        }
    }

    public final void zzb(zzij zzijVar) {
        if (this.zzaha != null) {
            this.handler.post(new zzho(this, zzijVar));
        }
    }

    public final void zzq(int i) {
        if (this.zzaha != null) {
            this.handler.post(new zzhn(this, i));
        }
    }
}
