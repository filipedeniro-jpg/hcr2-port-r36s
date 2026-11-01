package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcki implements zzdvi<zzckc> {
    private final zzdvv<zzdcs> zzfdl;
    private final zzdvv<zzcxn> zzfdw;
    private final zzdvv<zzbit> zzfwz;
    private final zzdvv<Context> zzgag;
    private final zzdvv<zzzn> zzgah;

    public zzcki(zzdvv<Context> zzdvvVar, zzdvv<zzbit> zzdvvVar2, zzdvv<zzcxn> zzdvvVar3, zzdvv<zzdcs> zzdvvVar4, zzdvv<zzzn> zzdvvVar5) {
        this.zzgag = zzdvvVar;
        this.zzfwz = zzdvvVar2;
        this.zzfdw = zzdvvVar3;
        this.zzfdl = zzdvvVar4;
        this.zzgah = zzdvvVar5;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzckc(this.zzgag.get(), this.zzfwz.get(), this.zzfdw.get(), this.zzfdl.get(), this.zzgah.get());
    }
}
