package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcni implements zzdvi<zzcng> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzcon<zzcrh>> zzeko;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<zzatb> zzgdr;

    private zzcni(zzdvv<zzcon<zzcrh>> zzdvvVar, zzdvv<zzcvk> zzdvvVar2, zzdvv<Context> zzdvvVar3, zzdvv<zzatb> zzdvvVar4) {
        this.zzeko = zzdvvVar;
        this.zzfds = zzdvvVar2;
        this.zzejn = zzdvvVar3;
        this.zzgdr = zzdvvVar4;
    }

    public static zzcni zze(zzdvv<zzcon<zzcrh>> zzdvvVar, zzdvv<zzcvk> zzdvvVar2, zzdvv<Context> zzdvvVar3, zzdvv<zzatb> zzdvvVar4) {
        return new zzcni(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcng(this.zzeko.get(), this.zzfds.get(), this.zzejn.get(), this.zzgdr.get());
    }
}
