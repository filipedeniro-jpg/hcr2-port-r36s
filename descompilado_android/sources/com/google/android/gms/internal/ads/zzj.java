package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzj implements zzaa {
    private final Executor zzv;

    public zzj(Handler handler) {
        this.zzv = new zzi(this, handler);
    }

    @Override // com.google.android.gms.internal.ads.zzaa
    public final void zzb(zzq<?> zzqVar, zzz<?> zzzVar) {
        zza(zzqVar, zzzVar, null);
    }

    @Override // com.google.android.gms.internal.ads.zzaa
    public final void zza(zzq<?> zzqVar, zzz<?> zzzVar, Runnable runnable) {
        zzqVar.zzj();
        zzqVar.zzb("post-response");
        this.zzv.execute(new zzl(zzqVar, zzzVar, runnable));
    }

    @Override // com.google.android.gms.internal.ads.zzaa
    public final void zza(zzq<?> zzqVar, zzae zzaeVar) {
        zzqVar.zzb("post-error");
        this.zzv.execute(new zzl(zzqVar, zzz.zzd(zzaeVar), null));
    }
}
