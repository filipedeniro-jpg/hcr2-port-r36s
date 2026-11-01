package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbxl implements zzdvi<zzbxm> {
    private final zzdvv<String> zzffx;
    private final zzdvv<zzbtx> zzfju;
    private final zzdvv<zzbtp> zzfox;

    private zzbxl(zzdvv<String> zzdvvVar, zzdvv<zzbtp> zzdvvVar2, zzdvv<zzbtx> zzdvvVar3) {
        this.zzffx = zzdvvVar;
        this.zzfox = zzdvvVar2;
        this.zzfju = zzdvvVar3;
    }

    public static zzbxl zzl(zzdvv<String> zzdvvVar, zzdvv<zzbtp> zzdvvVar2, zzdvv<zzbtx> zzdvvVar3) {
        return new zzbxl(zzdvvVar, zzdvvVar2, zzdvvVar3);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbxm(this.zzffx.get(), this.zzfox.get(), this.zzfju.get());
    }
}
