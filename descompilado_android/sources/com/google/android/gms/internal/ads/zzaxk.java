package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaxk<T> implements zzdcg<T> {
    private final /* synthetic */ zzaxj zzdwj;
    private final /* synthetic */ zzaxh zzdwk;

    zzaxk(zzaxi zzaxiVar, zzaxj zzaxjVar, zzaxh zzaxhVar) {
        this.zzdwj = zzaxjVar;
        this.zzdwk = zzaxhVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void onSuccess(@Nullable T t) {
        this.zzdwj.zzh(t);
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        this.zzdwk.run();
    }
}
