package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcwi implements zzdvi<zzcwe> {
    private final zzdvv<zzcwc> zzfbr;
    private final zzcwg zzgkn;
    private final zzdvv<String> zzgko;

    private zzcwi(zzcwg zzcwgVar, zzdvv<zzcwc> zzdvvVar, zzdvv<String> zzdvvVar2) {
        this.zzgkn = zzcwgVar;
        this.zzfbr = zzdvvVar;
        this.zzgko = zzdvvVar2;
    }

    public static zzcwi zza(zzcwg zzcwgVar, zzdvv<zzcwc> zzdvvVar, zzdvv<String> zzdvvVar2) {
        return new zzcwi(zzcwgVar, zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcwe) zzdvo.zza(this.zzfbr.get().zzgg(this.zzgko.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
