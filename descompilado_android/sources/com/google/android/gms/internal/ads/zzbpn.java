package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbpn implements zzdvi<Set<zzbqc<zzbqh>>> {
    private final zzbox zzfhi;

    private zzbpn(zzbox zzboxVar) {
        this.zzfhi = zzboxVar;
    }

    public static zzbpn zzv(zzbox zzboxVar) {
        return new zzbpn(zzboxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
