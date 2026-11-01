package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcjt implements zzdvi<zzcju> {
    private final zzdvv<zzckv> zzfzr;

    private zzcjt(zzdvv<zzckv> zzdvvVar) {
        this.zzfzr = zzdvvVar;
    }

    public static zzcjt zzaf(zzdvv<zzckv> zzdvvVar) {
        return new zzcjt(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcju(this.zzfzr.get());
    }
}
