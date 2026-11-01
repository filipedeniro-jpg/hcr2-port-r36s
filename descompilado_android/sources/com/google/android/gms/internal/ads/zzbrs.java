package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbrs implements zzdvi<Set<zzbqc<zzbpv>>> {
    private final zzdvv<zzbsh> zzfcs;

    private zzbrs(zzdvv<zzbsh> zzdvvVar) {
        this.zzfcs = zzdvvVar;
    }

    public static zzbrs zzu(zzdvv<zzbsh> zzdvvVar) {
        return new zzbrs(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(Collections.singleton(zzbqc.zzb(this.zzfcs.get(), zzawx.zzdwb)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
