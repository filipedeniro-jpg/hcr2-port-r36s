package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbty implements zzdvi<zzbtv> {
    private final zzdvv<zzbtn> zzflh;

    private zzbty(zzdvv<zzbtn> zzdvvVar) {
        this.zzflh = zzdvvVar;
    }

    public static zzbty zzx(zzdvv<zzbtn> zzdvvVar) {
        return new zzbty(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbtv(this.zzflh.get());
    }
}
