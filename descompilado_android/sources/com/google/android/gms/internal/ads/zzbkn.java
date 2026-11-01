package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbkn implements zzdvi<zzbko> {
    private final zzdvv<zzbnb> zzelz;

    private zzbkn(zzdvv<zzbnb> zzdvvVar) {
        this.zzelz = zzdvvVar;
    }

    public static zzbkn zze(zzdvv<zzbnb> zzdvvVar) {
        return new zzbkn(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbko(this.zzelz.get());
    }
}
