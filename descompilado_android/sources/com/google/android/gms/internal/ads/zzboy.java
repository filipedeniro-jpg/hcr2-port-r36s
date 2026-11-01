package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzboy implements zzdvi<zzbmj> {
    private final zzbox zzfhi;
    private final zzdvv<Set<zzbqc<zzbml>>> zzfhj;

    private zzboy(zzbox zzboxVar, zzdvv<Set<zzbqc<zzbml>>> zzdvvVar) {
        this.zzfhi = zzboxVar;
        this.zzfhj = zzdvvVar;
    }

    public static zzboy zza(zzbox zzboxVar, zzdvv<Set<zzbqc<zzbml>>> zzdvvVar) {
        return new zzboy(zzboxVar, zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbmj) zzdvo.zza(this.zzfhi.zzc(this.zzfhj.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
