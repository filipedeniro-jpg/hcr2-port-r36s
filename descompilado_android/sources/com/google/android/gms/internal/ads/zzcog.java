package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcog implements zzdvi<zzcoe> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzdcs> zzfbt;

    private zzcog(zzdvv<zzdcs> zzdvvVar, zzdvv<Context> zzdvvVar2) {
        this.zzfbt = zzdvvVar;
        this.zzejn = zzdvvVar2;
    }

    public static zzcog zzam(zzdvv<zzdcs> zzdvvVar, zzdvv<Context> zzdvvVar2) {
        return new zzcog(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcoe(this.zzfbt.get(), this.zzejn.get());
    }
}
