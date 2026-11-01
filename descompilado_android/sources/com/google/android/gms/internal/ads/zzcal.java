package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcal implements zzdvi<zzcam> {
    private final zzdvv<zzcay> zzekd;
    private final zzdvv<zzcar> zzeor;

    private zzcal(zzdvv<zzcar> zzdvvVar, zzdvv<zzcay> zzdvvVar2) {
        this.zzeor = zzdvvVar;
        this.zzekd = zzdvvVar2;
    }

    public static zzcal zzu(zzdvv<zzcar> zzdvvVar, zzdvv<zzcay> zzdvvVar2) {
        return new zzcal(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcam(this.zzeor.get(), this.zzekd.get());
    }
}
