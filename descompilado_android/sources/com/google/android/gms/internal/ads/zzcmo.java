package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcmo implements zzdvi<Set<String>> {
    private final zzcmm zzgdb;

    public zzcmo(zzcmm zzcmmVar) {
        this.zzgdb = zzcmmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(this.zzgdb.zzalo(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
