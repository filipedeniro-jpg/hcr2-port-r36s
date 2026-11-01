package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbsk implements zzdvi<zzbsh> {
    private final zzdvv<zzbmv> zzfij;

    private zzbsk(zzdvv<zzbmv> zzdvvVar) {
        this.zzfij = zzdvvVar;
    }

    public static zzbsk zzv(zzdvv<zzbmv> zzdvvVar) {
        return new zzbsk(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbsh(this.zzfij.get());
    }
}
