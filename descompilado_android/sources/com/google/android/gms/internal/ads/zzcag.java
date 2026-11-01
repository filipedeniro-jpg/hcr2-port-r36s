package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcag implements zzdvi<zzcak> {
    private static final zzcag zzfrc = new zzcag();

    public static zzcag zzajp() {
        return zzfrc;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcak) zzdvo.zza(new zzcak(zzsb.zza.zzb.REQUEST_WILL_PROCESS_RESPONSE, zzsb.zza.zzb.REQUEST_DID_PROCESS_RESPONSE, zzsb.zza.zzb.REQUEST_FAILED_TO_PROCESS_RESPONSE), "Cannot return null from a non-@Nullable @Provides method");
    }
}
