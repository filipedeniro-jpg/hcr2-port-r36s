package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbfw implements zzdvi<zzait> {
    private static final zzbfw zzfak = new zzbfw();

    public static zzbfw zzadz() {
        return zzfak;
    }

    public static zzait zzaea() {
        return (zzait) zzdvo.zza(new zzait(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzaea();
    }
}
