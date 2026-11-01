package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcoy implements zzdvi<zzcow> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzdcs> zzfbt;

    private zzcoy(zzdvv<Context> zzdvvVar, zzdvv<zzdcs> zzdvvVar2) {
        this.zzejn = zzdvvVar;
        this.zzfbt = zzdvvVar2;
    }

    public static zzcoy zzao(zzdvv<Context> zzdvvVar, zzdvv<zzdcs> zzdvvVar2) {
        return new zzcoy(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcow(this.zzejn.get(), this.zzfbt.get());
    }
}
