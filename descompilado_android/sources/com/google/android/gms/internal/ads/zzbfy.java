package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbfy implements zzdvi<zzasx> {
    private static final zzbfy zzfam = new zzbfy();

    public static zzbfy zzaeb() {
        return zzfam;
    }

    public static zzasx zzaec() {
        return (zzasx) zzdvo.zza(new zzasv(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzaec();
    }
}
