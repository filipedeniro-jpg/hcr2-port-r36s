package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbpa implements zzdvi<Set<zzbqc<zzbms>>> {
    private final zzbox zzfhi;

    private zzbpa(zzbox zzboxVar) {
        this.zzfhi = zzboxVar;
    }

    public static zzbpa zzi(zzbox zzboxVar) {
        return new zzbpa(zzboxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
