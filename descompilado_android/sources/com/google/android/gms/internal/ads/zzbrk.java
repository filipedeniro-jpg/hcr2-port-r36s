package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbrk implements zzdvi<Set<zzbqc<zzbmg>>> {
    private final zzdvv<zzbsf> zzfcs;
    private final zzbrd zzfia;

    private zzbrk(zzbrd zzbrdVar, zzdvv<zzbsf> zzdvvVar) {
        this.zzfia = zzbrdVar;
        this.zzfcs = zzdvvVar;
    }

    public static zzbrk zza(zzbrd zzbrdVar, zzdvv<zzbsf> zzdvvVar) {
        return new zzbrk(zzbrdVar, zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(this.zzfia.zza(this.zzfcs.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
