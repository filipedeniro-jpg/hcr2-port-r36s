package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcad implements zzdvi<zzcak> {
    private static final zzcad zzfqy = new zzcad();

    public static zzcad zzajn() {
        return zzfqy;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcak) zzdvo.zza(new zzcak(zzsb.zza.zzb.REQUEST_WILL_MAKE_NETWORK_REQUEST, zzsb.zza.zzb.REQUEST_DID_RECEIVE_NETWORK_RESPONSE, zzsb.zza.zzb.REQUEST_FAILED_TO_MAKE_NETWORK_REQUEST), "Cannot return null from a non-@Nullable @Provides method");
    }
}
