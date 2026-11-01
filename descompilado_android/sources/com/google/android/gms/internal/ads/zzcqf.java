package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqf implements zzdvi<zzcqd> {
    private final zzdvv<zzdcs> zzfbt;
    private final zzdvv<Bundle> zzgfc;

    private zzcqf(zzdvv<zzdcs> zzdvvVar, zzdvv<Bundle> zzdvvVar2) {
        this.zzfbt = zzdvvVar;
        this.zzgfc = zzdvvVar2;
    }

    public static zzcqf zzar(zzdvv<zzdcs> zzdvvVar, zzdvv<Bundle> zzdvvVar2) {
        return new zzcqf(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcqd(this.zzfbt.get(), this.zzgfc.get());
    }
}
