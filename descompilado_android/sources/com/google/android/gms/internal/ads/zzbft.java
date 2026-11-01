package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbft implements zzdvi<zzass> {
    private static final zzbft zzfah = new zzbft();

    public static zzbft zzadt() {
        return zzfah;
    }

    public static zzass zzadu() {
        return (zzass) zzdvo.zza(new zzass(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzadu();
    }
}
