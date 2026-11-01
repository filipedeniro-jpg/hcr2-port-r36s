package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbtq implements zzdvi<zzbtn> {
    private final zzdvv<zzbtx> zzfju;

    private zzbtq(zzdvv<zzbtx> zzdvvVar) {
        this.zzfju = zzdvvVar;
    }

    public static zzbtq zzw(zzdvv<zzbtx> zzdvvVar) {
        return new zzbtq(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbtn(this.zzfju.get());
    }
}
