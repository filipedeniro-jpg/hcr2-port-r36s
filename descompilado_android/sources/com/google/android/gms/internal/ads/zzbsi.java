package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbsi implements zzdvi<zzbsf> {
    private final zzdvv<zzbmo> zzfij;
    private final zzdvv<zzcvb> zzfik;

    private zzbsi(zzdvv<zzbmo> zzdvvVar, zzdvv<zzcvb> zzdvvVar2) {
        this.zzfij = zzdvvVar;
        this.zzfik = zzdvvVar2;
    }

    public static zzbsi zzj(zzdvv<zzbmo> zzdvvVar, zzdvv<zzcvb> zzdvvVar2) {
        return new zzbsi(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbsf(this.zzfij.get(), this.zzfik.get());
    }
}
