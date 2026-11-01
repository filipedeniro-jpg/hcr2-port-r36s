package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcaj implements zzdvi<zzcai> {
    private final zzdvv<zzrz> zzfqx;
    private final zzdvv<Map<zzcxk, zzcak>> zzfre;

    private zzcaj(zzdvv<zzrz> zzdvvVar, zzdvv<Map<zzcxk, zzcak>> zzdvvVar2) {
        this.zzfqx = zzdvvVar;
        this.zzfre = zzdvvVar2;
    }

    public static zzcaj zzt(zzdvv<zzrz> zzdvvVar, zzdvv<Map<zzcxk, zzcak>> zzdvvVar2) {
        return new zzcaj(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcai(this.zzfqx.get(), this.zzfre.get());
    }
}
