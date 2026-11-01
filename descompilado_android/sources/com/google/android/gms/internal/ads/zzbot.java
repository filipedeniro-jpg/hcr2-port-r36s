package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbot implements zzdvi<zzboq> {
    private final zzdvv<Set<zzbqc<Object>>> zzfdr;

    private zzbot(zzdvv<Set<zzbqc<Object>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzbot zzq(zzdvv<Set<zzbqc<Object>>> zzdvvVar) {
        return new zzbot(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzboq(this.zzfdr.get());
    }
}
