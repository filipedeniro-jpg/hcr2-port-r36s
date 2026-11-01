package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbig implements zzdvi<zzbnl> {
    private final zzbib zzfdq;
    private final zzdvv<Set<zzbqc<zzbnm>>> zzfdr;

    public zzbig(zzbib zzbibVar, zzdvv<Set<zzbqc<zzbnm>>> zzdvvVar) {
        this.zzfdq = zzbibVar;
        this.zzfdr = zzdvvVar;
    }

    public static zzbnl zza(zzbib zzbibVar, Set<zzbqc<zzbnm>> set) {
        return (zzbnl) zzdvo.zza(zzbibVar.zza(set), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zza(this.zzfdq, this.zzfdr.get());
    }
}
