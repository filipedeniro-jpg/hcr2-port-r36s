package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbhl implements zzdvi<Set<zzbqc<zzpl>>> {
    private final zzbhj zzfcr;
    private final zzdvv<zzbjl> zzfcs;

    public zzbhl(zzbhj zzbhjVar, zzdvv<zzbjl> zzdvvVar) {
        this.zzfcr = zzbhjVar;
        this.zzfcs = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(Collections.singleton(new zzbqc(this.zzfcs.get(), zzawx.zzdwb)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
