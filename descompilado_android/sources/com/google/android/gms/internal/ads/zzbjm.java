package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbjm implements zzdvi<zzbjj> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzawv> zzfaa;
    private final zzdvv<zzbbc> zzfdi;
    private final zzdvv<zzcvb> zzfem;

    public zzbjm(zzdvv<Context> zzdvvVar, zzdvv<zzbbc> zzdvvVar2, zzdvv<zzcvb> zzdvvVar3, zzdvv<zzawv> zzdvvVar4) {
        this.zzejn = zzdvvVar;
        this.zzfdi = zzdvvVar2;
        this.zzfem = zzdvvVar3;
        this.zzfaa = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbjj(this.zzejn.get(), this.zzfdi.get(), this.zzfem.get(), this.zzfaa.get());
    }
}
