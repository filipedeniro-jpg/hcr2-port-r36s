package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbso implements zzdvi<zzbsl> {
    private final zzdvv<zzbui> zzfdk;
    private final zzdvv<Map<String, zzcfk<zzbka>>> zzfew;
    private final zzdvv<zzbjv<zzbhy>> zzfio;
    private final zzdvv<Map<String, zzcfk<zzbtp>>> zzfiq;
    private final zzdvv<Map<String, zzchn<zzbtp>>> zzfir;

    public zzbso(zzdvv<Map<String, zzcfk<zzbka>>> zzdvvVar, zzdvv<Map<String, zzcfk<zzbtp>>> zzdvvVar2, zzdvv<Map<String, zzchn<zzbtp>>> zzdvvVar3, zzdvv<zzbjv<zzbhy>> zzdvvVar4, zzdvv<zzbui> zzdvvVar5) {
        this.zzfew = zzdvvVar;
        this.zzfiq = zzdvvVar2;
        this.zzfir = zzdvvVar3;
        this.zzfio = zzdvvVar4;
        this.zzfdk = zzdvvVar5;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbsl(this.zzfew.get(), this.zzfiq.get(), this.zzfir.get(), this.zzfio, this.zzfdk.get());
    }
}
