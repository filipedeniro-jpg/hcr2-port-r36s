package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbti implements zzdvi<zzbub> {
    private final zzdvv<zzbue> zzell;
    private final zzbtd zzfjx;

    public zzbti(zzbtd zzbtdVar, zzdvv<zzbue> zzdvvVar) {
        this.zzfjx = zzbtdVar;
        this.zzell = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbub) zzdvo.zza(this.zzell.get(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
