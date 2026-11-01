package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbin implements zzdvi<Set<zzbqc<zzpl>>> {
    private final zzdvv<zzbjl> zzfcs;
    private final zzbib zzfdq;

    public zzbin(zzbib zzbibVar, zzdvv<zzbjl> zzdvvVar) {
        this.zzfdq = zzbibVar;
        this.zzfcs = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(Collections.singleton(new zzbqc(this.zzfcs.get(), zzawx.zzdwb)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
