package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbkm implements zzdvi<String> {
    private final zzbkk zzffh;

    private zzbkm(zzbkk zzbkkVar) {
        this.zzffh = zzbkkVar;
    }

    public static zzbkm zze(zzbkk zzbkkVar) {
        return new zzbkm(zzbkkVar);
    }

    public static String zzf(zzbkk zzbkkVar) {
        return (String) zzdvo.zza(zzbkkVar.zzafn(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzf(this.zzffh);
    }
}
