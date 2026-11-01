package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcau implements zzdvi<zzcar> {
    private final zzdvv<zzcay> zzekd;

    private zzcau(zzdvv<zzcay> zzdvvVar) {
        this.zzekd = zzdvvVar;
    }

    public static zzcau zzz(zzdvv<zzcay> zzdvvVar) {
        return new zzcau(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcar(this.zzekd.get());
    }
}
