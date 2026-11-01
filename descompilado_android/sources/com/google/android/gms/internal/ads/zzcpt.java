package com.google.android.gms.internal.ads;

import android.content.pm.PackageInfo;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcpt implements zzdvi<zzcpm> {
    private final zzdvv<zzato> zzeoo;
    private final zzdvv<zzdcs> zzfbt;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<PackageInfo> zzftm;

    public zzcpt(zzdvv<zzdcs> zzdvvVar, zzdvv<zzcvk> zzdvvVar2, zzdvv<PackageInfo> zzdvvVar3, zzdvv<zzato> zzdvvVar4) {
        this.zzfbt = zzdvvVar;
        this.zzfds = zzdvvVar2;
        this.zzftm = zzdvvVar3;
        this.zzeoo = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcpm(this.zzfbt.get(), this.zzfds.get(), this.zzftm.get(), this.zzeoo.get());
    }
}
