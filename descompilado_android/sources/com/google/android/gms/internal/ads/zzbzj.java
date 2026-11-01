package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbzj implements zzdvi<zzbzk> {
    private final zzdvv<zzbbc> zzfdi;

    private zzbzj(zzdvv<zzbbc> zzdvvVar) {
        this.zzfdi = zzdvvVar;
    }

    public static zzbzj zzy(zzdvv<zzbbc> zzdvvVar) {
        return new zzbzj(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbzk(this.zzfdi.get());
    }
}
