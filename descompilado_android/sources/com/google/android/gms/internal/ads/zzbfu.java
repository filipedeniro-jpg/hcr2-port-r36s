package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbfu implements zzdvi<zzro> {
    private static final zzbfu zzfai = new zzbfu();

    public static zzbfu zzadv() {
        return zzfai;
    }

    public static zzro zzadw() {
        return (zzro) zzdvo.zza(new zzro(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzadw();
    }
}
