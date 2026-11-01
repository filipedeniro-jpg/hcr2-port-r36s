package com.google.android.gms.internal.ads;

import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeoutException;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcio<T> implements zzdcg<T> {
    private final /* synthetic */ String zzfys;
    private final /* synthetic */ long zzfyt;
    private final /* synthetic */ zzcil zzfyu;

    zzcio(zzcil zzcilVar, String str, long j) {
        this.zzfyu = zzcilVar;
        this.zzfys = str;
        this.zzfyt = j;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void onSuccess(T t) {
        this.zzfyu.zza(this.zzfys, 0, this.zzfyu.zzbmd.elapsedRealtime() - this.zzfyt);
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        long jElapsedRealtime = this.zzfyu.zzbmd.elapsedRealtime();
        int i = 3;
        if (th instanceof TimeoutException) {
            i = 2;
        } else if (!(th instanceof zzcib)) {
            if (th instanceof CancellationException) {
                i = 4;
            } else {
                i = ((th instanceof zzcce) && ((zzcce) th).getErrorCode() == 3) ? 1 : 6;
            }
        }
        this.zzfyu.zza(this.zzfys, i, jElapsedRealtime - this.zzfyt);
    }
}
