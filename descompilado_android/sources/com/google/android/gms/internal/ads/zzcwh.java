package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcwh implements zzdvi<zzcwc> {
    private final zzdvv<zzawv> zzfcg;
    private final zzdvv<Context> zzfgb;
    private final zzdvv<zzatb> zzgdr;

    public zzcwh(zzdvv<Context> zzdvvVar, zzdvv<zzawv> zzdvvVar2, zzdvv<zzatb> zzdvvVar3) {
        this.zzfgb = zzdvvVar;
        this.zzfcg = zzdvvVar2;
        this.zzgdr = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcwc(this.zzfgb.get(), this.zzfcg.get(), this.zzgdr.get());
    }
}
