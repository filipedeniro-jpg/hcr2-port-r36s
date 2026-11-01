package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcre<T> implements zzdvi<zzcrd<T>> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<Set<zzcra<? extends zzcrb<T>>>> zzgfx;

    private zzcre(zzdvv<Executor> zzdvvVar, zzdvv<Set<zzcra<? extends zzcrb<T>>>> zzdvvVar2) {
        this.zzfbt = zzdvvVar;
        this.zzgfx = zzdvvVar2;
    }

    public static <T> zzcre<T> zzat(zzdvv<Executor> zzdvvVar, zzdvv<Set<zzcra<? extends zzcrb<T>>>> zzdvvVar2) {
        return new zzcre<>(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcrd(this.zzfbt.get(), this.zzgfx.get());
    }
}
