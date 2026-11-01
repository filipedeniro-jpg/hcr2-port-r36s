package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbpc implements zzdvi<Set<zzbqc<com.google.android.gms.ads.internal.overlay.zzp>>> {
    private final zzbox zzfhi;

    private zzbpc(zzbox zzboxVar) {
        this.zzfhi = zzboxVar;
    }

    public static zzbpc zzk(zzbox zzboxVar) {
        return new zzbpc(zzboxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
