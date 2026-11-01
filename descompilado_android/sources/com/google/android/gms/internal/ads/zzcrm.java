package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcrm implements zzdvi<zzcrk> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzdcs> zzfbt;

    private zzcrm(zzdvv<zzdcs> zzdvvVar, zzdvv<Context> zzdvvVar2) {
        this.zzfbt = zzdvvVar;
        this.zzejn = zzdvvVar2;
    }

    public static zzcrm zzau(zzdvv<zzdcs> zzdvvVar, zzdvv<Context> zzdvvVar2) {
        return new zzcrm(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcrk(this.zzfbt.get(), this.zzejn.get());
    }
}
