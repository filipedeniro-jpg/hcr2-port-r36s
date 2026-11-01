package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbvh implements zzdvi<zzbvi> {
    private final zzdvv<zzbub> zzelm;
    private final zzdvv<zzbtx> zzfju;

    public zzbvh(zzdvv<zzbtx> zzdvvVar, zzdvv<zzbub> zzdvvVar2) {
        this.zzfju = zzdvvVar;
        this.zzelm = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbvi(this.zzfju.get(), this.zzelm.get());
    }
}
