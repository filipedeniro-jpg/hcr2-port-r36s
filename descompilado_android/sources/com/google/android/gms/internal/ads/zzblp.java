package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzblp implements zzdvi<zzboa> {
    private final zzdvv<zzboa> zzfge;

    private zzblp(zzdvv<zzboa> zzdvvVar) {
        this.zzfge = zzdvvVar;
    }

    public static zzblp zzf(zzdvv<zzboa> zzdvvVar) {
        return new zzblp(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzboa) zzdvo.zza(this.zzfge.get(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
