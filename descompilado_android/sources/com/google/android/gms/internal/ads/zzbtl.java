package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbtl implements zzdvi<zzbqc<zzbmt>> {
    private final zzdvv<zzbvi> zzfcs;
    private final zzbtd zzfjx;

    public zzbtl(zzbtd zzbtdVar, zzdvv<zzbvi> zzdvvVar) {
        this.zzfjx = zzbtdVar;
        this.zzfcs = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzfcs.get(), zzawx.zzdwb), "Cannot return null from a non-@Nullable @Provides method");
    }
}
