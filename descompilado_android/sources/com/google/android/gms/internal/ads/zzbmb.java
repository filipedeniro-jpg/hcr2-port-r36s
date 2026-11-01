package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbmb implements zzdvi<zzcvk> {
    private final zzblu zzfgi;

    private zzbmb(zzblu zzbluVar) {
        this.zzfgi = zzbluVar;
    }

    public static zzbmb zzk(zzblu zzbluVar) {
        return new zzbmb(zzbluVar);
    }

    public static zzcvk zzl(zzblu zzbluVar) {
        return (zzcvk) zzdvo.zza(zzbluVar.zzafq(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzl(this.zzfgi);
    }
}
