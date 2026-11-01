package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcwj implements zzdvi<zzato> {
    private final zzcwg zzgkn;
    private final zzdvv<zzcwe> zzgkp;

    private zzcwj(zzcwg zzcwgVar, zzdvv<zzcwe> zzdvvVar) {
        this.zzgkn = zzcwgVar;
        this.zzgkp = zzdvvVar;
    }

    public static zzcwj zza(zzcwg zzcwgVar, zzdvv<zzcwe> zzdvvVar) {
        return new zzcwj(zzcwgVar, zzdvvVar);
    }

    public static zzato zza(zzcwg zzcwgVar, zzcwe zzcweVar) {
        return (zzato) zzdvo.zza(zzcweVar.zzdqz, "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zza(this.zzgkn, this.zzgkp.get());
    }
}
