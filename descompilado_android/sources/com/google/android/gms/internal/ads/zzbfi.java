package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbfi implements zzdvi<com.google.android.gms.ads.internal.zzb> {
    private final zzbfg zzezt;

    public zzbfi(zzbfg zzbfgVar) {
        this.zzezt = zzbfgVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (com.google.android.gms.ads.internal.zzb) zzdvo.zza(this.zzezt.zzadp(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
