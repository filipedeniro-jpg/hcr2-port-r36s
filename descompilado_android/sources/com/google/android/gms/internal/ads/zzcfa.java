package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcfa implements zzdvi<zzcex> {
    private final zzdvv<zzcev> zzfwf;

    private zzcfa(zzdvv<zzcev> zzdvvVar) {
        this.zzfwf = zzdvvVar;
    }

    public static zzcfa zzae(zzdvv<zzcev> zzdvvVar) {
        return new zzcfa(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcex(this.zzfwf.get());
    }
}
