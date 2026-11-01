package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgz implements zzdvi<zzaim> {
    private final zzdvv<zzaid> zzfce;

    private zzbgz(zzdvv<zzaid> zzdvvVar) {
        this.zzfce = zzdvvVar;
    }

    public static zzbgz zzb(zzdvv<zzaid> zzdvvVar) {
        return new zzbgz(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzaim) zzdvo.zza(this.zzfce.get().zzrh(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
