package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbzy implements zzdvi<String> {
    private static final zzbzy zzfqs = new zzbzy();

    public static zzbzy zzajl() {
        return zzfqs;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        com.google.android.gms.ads.internal.zzp.zzjy();
        return (String) zzdvo.zza(zzatv.zzvi(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
