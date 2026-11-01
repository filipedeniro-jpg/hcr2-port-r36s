package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcwk implements zzdvi<zzdf> {
    private final zzcwg zzgkn;
    private final zzdvv<zzcwe> zzgkp;

    private zzcwk(zzcwg zzcwgVar, zzdvv<zzcwe> zzdvvVar) {
        this.zzgkn = zzcwgVar;
        this.zzgkp = zzdvvVar;
    }

    public static zzcwk zzb(zzcwg zzcwgVar, zzdvv<zzcwe> zzdvvVar) {
        return new zzcwk(zzcwgVar, zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzdf) zzdvo.zza(this.zzgkp.get().zzgkm, "Cannot return null from a non-@Nullable @Provides method");
    }
}
