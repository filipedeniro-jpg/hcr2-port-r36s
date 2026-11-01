package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcky implements zzdvi<zzckv> {
    private final zzdvv<zzcgw> zzfzr;

    private zzcky(zzdvv<zzcgw> zzdvvVar) {
        this.zzfzr = zzdvvVar;
    }

    public static zzcky zzag(zzdvv<zzcgw> zzdvvVar) {
        return new zzcky(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzckv(this.zzfzr.get());
    }
}
