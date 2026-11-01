package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcab implements zzdvi<zzcak> {
    private static final zzcab zzfqw = new zzcab();

    public static zzcab zzajm() {
        return zzfqw;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcak) zzdvo.zza(new zzcak(zzsb.zza.zzb.REQUEST_WILL_BUILD_URL, zzsb.zza.zzb.REQUEST_DID_BUILD_URL, zzsb.zza.zzb.REQUEST_FAILED_TO_BUILD_URL), "Cannot return null from a non-@Nullable @Provides method");
    }
}
