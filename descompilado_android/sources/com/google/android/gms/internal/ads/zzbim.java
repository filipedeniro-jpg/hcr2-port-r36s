package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbim implements zzdvi<Set<zzbqc<zzbnm>>> {
    private final zzdvv<zzbjl> zzfcs;
    private final zzbib zzfdq;

    public zzbim(zzbib zzbibVar, zzdvv<zzbjl> zzdvvVar) {
        this.zzfdq = zzbibVar;
        this.zzfcs = zzdvvVar;
    }

    public static Set<zzbqc<zzbnm>> zza(zzbib zzbibVar, zzbjl zzbjlVar) {
        return (Set) zzdvo.zza(Collections.singleton(new zzbqc(zzbjlVar, zzawx.zzdwb)), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zza(this.zzfdq, this.zzfcs.get());
    }
}
