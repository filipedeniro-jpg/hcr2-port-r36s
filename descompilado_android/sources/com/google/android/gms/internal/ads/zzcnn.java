package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcnn implements zzdvi<zzcnl> {
    private final zzdvv<Set<String>> zzgdw;

    private zzcnn(zzdvv<Set<String>> zzdvvVar) {
        this.zzgdw = zzdvvVar;
    }

    public static zzcnn zzai(zzdvv<Set<String>> zzdvvVar) {
        return new zzcnn(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcnl(this.zzgdw.get());
    }
}
