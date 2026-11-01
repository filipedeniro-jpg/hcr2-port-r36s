package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbtk implements zzdvi<zzbxn> {
    private final zzbtd zzfjx;

    public zzbtk(zzbtd zzbtdVar) {
        this.zzfjx = zzbtdVar;
    }

    public static zzbxn zza(zzbtd zzbtdVar) {
        return (zzbxn) zzdvo.zza(zzbtdVar.zzahe(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zza(this.zzfjx);
    }
}
