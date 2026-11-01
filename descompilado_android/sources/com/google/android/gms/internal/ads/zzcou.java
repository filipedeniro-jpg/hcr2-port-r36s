package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcou implements zzdvi<zzcoq> {
    private final zzdvv<zzdcs> zzfbt;
    private final zzdvv<Context> zzfgb;

    private zzcou(zzdvv<Context> zzdvvVar, zzdvv<zzdcs> zzdvvVar2) {
        this.zzfgb = zzdvvVar;
        this.zzfbt = zzdvvVar2;
    }

    public static zzcou zzan(zzdvv<Context> zzdvvVar, zzdvv<zzdcs> zzdvvVar2) {
        return new zzcou(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcoq(this.zzfgb.get(), this.zzfbt.get());
    }
}
