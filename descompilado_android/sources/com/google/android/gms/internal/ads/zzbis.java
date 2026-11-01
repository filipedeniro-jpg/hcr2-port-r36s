package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbis implements zzdvi<zzbqc<zzbom>> {
    private final zzbib zzfdq;
    private final zzdvv<zzboa> zzfdt;

    public zzbis(zzbib zzbibVar, zzdvv<zzboa> zzdvvVar) {
        this.zzfdq = zzbibVar;
        this.zzfdt = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        final zzboa zzboaVar = this.zzfdt.get();
        return (zzbqc) zzdvo.zza(new zzbqc(new zzbom(zzboaVar) { // from class: com.google.android.gms.internal.ads.zzbid
            private final zzboa zzfdm;

            {
                this.zzfdm = zzboaVar;
            }

            @Override // com.google.android.gms.internal.ads.zzbom
            public final void zzafa() {
                this.zzfdm.zzafw();
            }
        }, zzawx.zzdwb), "Cannot return null from a non-@Nullable @Provides method");
    }
}
