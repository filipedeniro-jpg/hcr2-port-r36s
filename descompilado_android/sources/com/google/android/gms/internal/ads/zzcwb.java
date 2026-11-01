package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcwb implements zzdvi<zzbqc<zzbml>> {
    private final zzdvv<zzcwa> zzffu;
    private final zzcvy zzgkj;

    private zzcwb(zzcvy zzcvyVar, zzdvv<zzcwa> zzdvvVar) {
        this.zzgkj = zzcvyVar;
        this.zzffu = zzdvvVar;
    }

    public static zzcwb zza(zzcvy zzcvyVar, zzdvv<zzcwa> zzdvvVar) {
        return new zzcwb(zzcvyVar, zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzffu.get(), zzawx.zzdwb), "Cannot return null from a non-@Nullable @Provides method");
    }
}
