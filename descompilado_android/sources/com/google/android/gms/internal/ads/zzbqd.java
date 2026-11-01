package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbqd implements zzdvi<zzbqb> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<Set<zzbqc<zzpl>>> zzfdr;
    private final zzdvv<zzcvb> zzfem;

    private zzbqd(zzdvv<Context> zzdvvVar, zzdvv<Set<zzbqc<zzpl>>> zzdvvVar2, zzdvv<zzcvb> zzdvvVar3) {
        this.zzejn = zzdvvVar;
        this.zzfdr = zzdvvVar2;
        this.zzfem = zzdvvVar3;
    }

    public static zzbqd zzi(zzdvv<Context> zzdvvVar, zzdvv<Set<zzbqc<zzpl>>> zzdvvVar2, zzdvv<zzcvb> zzdvvVar3) {
        return new zzbqd(zzdvvVar, zzdvvVar2, zzdvvVar3);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbqb(this.zzejn.get(), this.zzfdr.get(), this.zzfem.get());
    }
}
