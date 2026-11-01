package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbzf implements zzdvi<zzbyr> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzawv> zzfaa;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<zzboa> zzfdt;
    private final zzdvv<zzdf> zzfjv;
    private final zzdvv<zzrz> zzfol;
    private final zzdvv<zzbbl> zzfpr;
    private final zzdvv<com.google.android.gms.ads.internal.zzb> zzfqg;
    private final zzdvv<zzbra> zzfqh;

    private zzbzf(zzdvv<zzbbl> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<zzcvk> zzdvvVar3, zzdvv<zzdf> zzdvvVar4, zzdvv<zzawv> zzdvvVar5, zzdvv<com.google.android.gms.ads.internal.zzb> zzdvvVar6, zzdvv<zzrz> zzdvvVar7, zzdvv<zzboa> zzdvvVar8, zzdvv<zzbra> zzdvvVar9) {
        this.zzfpr = zzdvvVar;
        this.zzejn = zzdvvVar2;
        this.zzfds = zzdvvVar3;
        this.zzfjv = zzdvvVar4;
        this.zzfaa = zzdvvVar5;
        this.zzfqg = zzdvvVar6;
        this.zzfol = zzdvvVar7;
        this.zzfdt = zzdvvVar8;
        this.zzfqh = zzdvvVar9;
    }

    public static zzbzf zzb(zzdvv<zzbbl> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<zzcvk> zzdvvVar3, zzdvv<zzdf> zzdvvVar4, zzdvv<zzawv> zzdvvVar5, zzdvv<com.google.android.gms.ads.internal.zzb> zzdvvVar6, zzdvv<zzrz> zzdvvVar7, zzdvv<zzboa> zzdvvVar8, zzdvv<zzbra> zzdvvVar9) {
        return new zzbzf(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4, zzdvvVar5, zzdvvVar6, zzdvvVar7, zzdvvVar8, zzdvvVar9);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbyr(this.zzfpr.get(), this.zzejn.get(), this.zzfds.get(), this.zzfjv.get(), this.zzfaa.get(), this.zzfqg.get(), this.zzfol.get(), this.zzfdt.get(), this.zzfqh.get());
    }
}
