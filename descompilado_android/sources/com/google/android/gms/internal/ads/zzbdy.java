package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbdy implements zzdvi<zzbga> {
    private final zzbdr zzeji;
    private final zzdvv<zzbds> zzejk;

    public zzbdy(zzbdr zzbdrVar, zzdvv<zzbds> zzdvvVar) {
        this.zzeji = zzbdrVar;
        this.zzejk = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbga) zzdvo.zza(this.zzejk.get(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
