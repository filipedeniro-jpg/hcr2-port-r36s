package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbzw implements zzdvi<zzcak> {
    private static final zzbzw zzfqr = new zzbzw();

    public static zzbzw zzajk() {
        return zzfqr;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcak) zzdvo.zza(new zzcak(zzsb.zza.zzb.REQUEST_WILL_UPDATE_SIGNALS, zzsb.zza.zzb.REQUEST_DID_UPDATE_SIGNALS, zzsb.zza.zzb.REQUEST_FAILED_TO_UPDATE_SIGNALS), "Cannot return null from a non-@Nullable @Provides method");
    }
}
