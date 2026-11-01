package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzboz implements zzdvi<Set<zzbqc<zzbnm>>> {
    private final zzbox zzfhi;

    private zzboz(zzbox zzboxVar) {
        this.zzfhi = zzboxVar;
    }

    public static zzboz zzg(zzbox zzboxVar) {
        return new zzboz(zzboxVar);
    }

    public static Set<zzbqc<zzbnm>> zzh(zzbox zzboxVar) {
        return (Set) zzdvo.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzh(this.zzfhi);
    }
}
