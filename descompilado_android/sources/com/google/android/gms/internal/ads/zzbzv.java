package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbzv implements zzdvi<zzcak> {
    private static final zzbzv zzfqq = new zzbzv();

    public static zzbzv zzajj() {
        return zzfqq;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcak) zzdvo.zza(new zzcak(zzsb.zza.zzb.REQUEST_WILL_RENDER, zzsb.zza.zzb.REQUEST_DID_RENDER, zzsb.zza.zzb.REQUEST_FAILED_TO_RENDER), "Cannot return null from a non-@Nullable @Provides method");
    }
}
