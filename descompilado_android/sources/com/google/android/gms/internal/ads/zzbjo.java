package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbjo implements zzdvi<zzbjl> {
    private final zzdvv<zzcvb> zzfbb;
    private final zzdvv<zzbmo> zzfeo;
    private final zzdvv<zzbnq> zzfep;

    private zzbjo(zzdvv<zzcvb> zzdvvVar, zzdvv<zzbmo> zzdvvVar2, zzdvv<zzbnq> zzdvvVar3) {
        this.zzfbb = zzdvvVar;
        this.zzfeo = zzdvvVar2;
        this.zzfep = zzdvvVar3;
    }

    public static zzbjo zzg(zzdvv<zzcvb> zzdvvVar, zzdvv<zzbmo> zzdvvVar2, zzdvv<zzbnq> zzdvvVar3) {
        return new zzbjo(zzdvvVar, zzdvvVar2, zzdvvVar3);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbjl(this.zzfbb.get(), this.zzfeo.get(), this.zzfep.get());
    }
}
