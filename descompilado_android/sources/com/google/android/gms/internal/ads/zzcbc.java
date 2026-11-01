package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcbc implements zzdvi<zzcaz> {
    private final zzdvv<String> zzfsa;
    private final zzdvv<zzcay> zzfsb;

    public zzcbc(zzdvv<String> zzdvvVar, zzdvv<zzcay> zzdvvVar2) {
        this.zzfsa = zzdvvVar;
        this.zzfsb = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcaz(this.zzfsa.get(), this.zzfsb.get());
    }
}
