package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqx implements zzdvi<zzcqv> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzawv> zzfaa;
    private final zzdvv<zzdcs> zzfbt;

    private zzcqx(zzdvv<zzdcs> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<zzawv> zzdvvVar3) {
        this.zzfbt = zzdvvVar;
        this.zzejn = zzdvvVar2;
        this.zzfaa = zzdvvVar3;
    }

    public static zzcqx zzr(zzdvv<zzdcs> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<zzawv> zzdvvVar3) {
        return new zzcqx(zzdvvVar, zzdvvVar2, zzdvvVar3);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcqv(this.zzfbt.get(), this.zzejn.get(), this.zzfaa.get());
    }
}
