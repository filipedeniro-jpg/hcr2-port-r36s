package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbxe implements zzdvi<zzbxc> {
    private final zzdvv<zzbtx> zzfju;
    private final zzdvv<zzbtp> zzfox;

    private zzbxe(zzdvv<zzbtp> zzdvvVar, zzdvv<zzbtx> zzdvvVar2) {
        this.zzfox = zzdvvVar;
        this.zzfju = zzdvvVar2;
    }

    public static zzbxe zzk(zzdvv<zzbtp> zzdvvVar, zzdvv<zzbtx> zzdvvVar2) {
        return new zzbxe(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbxc(this.zzfox.get(), this.zzfju.get());
    }
}
