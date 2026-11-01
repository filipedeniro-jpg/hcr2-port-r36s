package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaxl<T> implements zzdcg<T> {
    private final /* synthetic */ zzaxi zzdwl;

    zzaxl(zzaxi zzaxiVar) {
        this.zzdwl = zzaxiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void onSuccess(@Nullable T t) {
        this.zzdwl.zzdwi.set(1);
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        this.zzdwl.zzdwi.set(-1);
    }
}
