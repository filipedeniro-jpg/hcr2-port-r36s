package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcpx implements zzdvi<zzcpv> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzdcs> zzfbt;
    private final zzdvv<Set<String>> zzgdw;

    private zzcpx(zzdvv<zzdcs> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<Set<String>> zzdvvVar3) {
        this.zzfbt = zzdvvVar;
        this.zzejn = zzdvvVar2;
        this.zzgdw = zzdvvVar3;
    }

    public static zzcpx zzp(zzdvv<zzdcs> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<Set<String>> zzdvvVar3) {
        return new zzcpx(zzdvvVar, zzdvvVar2, zzdvvVar3);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcpv(this.zzfbt.get(), this.zzejn.get(), this.zzgdw.get());
    }
}
