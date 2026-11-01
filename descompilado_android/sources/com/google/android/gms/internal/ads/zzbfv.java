package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbfv implements zzdvi<zzanb> {
    private static final zzbfv zzfaj = new zzbfv();

    public static zzbfv zzadx() {
        return zzfaj;
    }

    public static zzanb zzady() {
        return (zzanb) zzdvo.zza(new zzanb(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzady();
    }
}
