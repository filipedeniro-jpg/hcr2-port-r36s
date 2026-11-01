package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcnw implements zzdvi<zzcnu> {
    private final zzdvv<zzbkx> zzeog;
    private final zzdvv<zzcwa> zzepq;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<String> zzgea;
    private final zzdvv<String> zzgeb;

    private zzcnw(zzdvv<String> zzdvvVar, zzdvv<String> zzdvvVar2, zzdvv<zzbkx> zzdvvVar3, zzdvv<zzcwa> zzdvvVar4, zzdvv<zzcvk> zzdvvVar5) {
        this.zzgea = zzdvvVar;
        this.zzgeb = zzdvvVar2;
        this.zzeog = zzdvvVar3;
        this.zzepq = zzdvvVar4;
        this.zzfds = zzdvvVar5;
    }

    public static zzcnw zzh(zzdvv<String> zzdvvVar, zzdvv<String> zzdvvVar2, zzdvv<zzbkx> zzdvvVar3, zzdvv<zzcwa> zzdvvVar4, zzdvv<zzcvk> zzdvvVar5) {
        return new zzcnw(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4, zzdvvVar5);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcnu(this.zzgea.get(), this.zzgeb.get(), this.zzeog.get(), this.zzepq.get(), this.zzfds.get());
    }
}
