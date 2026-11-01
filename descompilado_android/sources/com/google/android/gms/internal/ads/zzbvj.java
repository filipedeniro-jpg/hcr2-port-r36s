package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbvj implements zzdvi<zzbvk> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzbmf> zzemc;
    private final zzdvv<zzbmo> zzemj;
    private final zzdvv<zzajw> zzeuo;
    private final zzdvv<zzawv> zzfaa;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<zzcvb> zzfem;
    private final zzdvv<zzajq> zzfnd;
    private final zzdvv<zzajr> zzfne;

    public zzbvj(zzdvv<zzajq> zzdvvVar, zzdvv<zzajr> zzdvvVar2, zzdvv<zzajw> zzdvvVar3, zzdvv<zzbmo> zzdvvVar4, zzdvv<zzbmf> zzdvvVar5, zzdvv<Context> zzdvvVar6, zzdvv<zzcvb> zzdvvVar7, zzdvv<zzawv> zzdvvVar8, zzdvv<zzcvk> zzdvvVar9) {
        this.zzfnd = zzdvvVar;
        this.zzfne = zzdvvVar2;
        this.zzeuo = zzdvvVar3;
        this.zzemj = zzdvvVar4;
        this.zzemc = zzdvvVar5;
        this.zzejn = zzdvvVar6;
        this.zzfem = zzdvvVar7;
        this.zzfaa = zzdvvVar8;
        this.zzfds = zzdvvVar9;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbvk(this.zzfnd.get(), this.zzfne.get(), this.zzeuo.get(), this.zzemj.get(), this.zzemc.get(), this.zzejn.get(), this.zzfem.get(), this.zzfaa.get(), this.zzfds.get());
    }
}
