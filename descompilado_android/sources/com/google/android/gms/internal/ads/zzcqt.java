package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqt implements zzdvi<zzcqr> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzarv> zzfad;
    private final zzdvv<zzdcs> zzfbt;

    private zzcqt(zzdvv<zzarv> zzdvvVar, zzdvv<zzdcs> zzdvvVar2, zzdvv<Context> zzdvvVar3) {
        this.zzfad = zzdvvVar;
        this.zzfbt = zzdvvVar2;
        this.zzejn = zzdvvVar3;
    }

    public static zzcqt zzq(zzdvv<zzarv> zzdvvVar, zzdvv<zzdcs> zzdvvVar2, zzdvv<Context> zzdvvVar3) {
        return new zzcqt(zzdvvVar, zzdvvVar2, zzdvvVar3);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcqr(this.zzfad.get(), this.zzfbt.get(), this.zzejn.get());
    }
}
