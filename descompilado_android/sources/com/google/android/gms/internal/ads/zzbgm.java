package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgm implements zzdvi<zzbgj> {
    private final zzdvv<zzcxw> zzerp;
    private final zzdvv<zzcvj> zzfba;
    private final zzdvv<zzcvb> zzfbb;

    private zzbgm(zzdvv<zzcvj> zzdvvVar, zzdvv<zzcvb> zzdvvVar2, zzdvv<zzcxw> zzdvvVar3) {
        this.zzfba = zzdvvVar;
        this.zzfbb = zzdvvVar2;
        this.zzerp = zzdvvVar3;
    }

    public static zzbgm zza(zzdvv<zzcvj> zzdvvVar, zzdvv<zzcvb> zzdvvVar2, zzdvv<zzcxw> zzdvvVar3) {
        return new zzbgm(zzdvvVar, zzdvvVar2, zzdvvVar3);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbgj(this.zzfba.get(), this.zzfbb.get(), this.zzerp.get());
    }
}
