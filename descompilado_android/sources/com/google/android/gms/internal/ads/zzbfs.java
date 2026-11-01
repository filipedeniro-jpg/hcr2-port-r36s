package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbfs implements zzdvi<zzbbl> {
    private static final zzbfs zzfag = new zzbfs();

    public static zzbfs zzads() {
        return zzfag;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbbl) zzdvo.zza(new zzbbl(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
