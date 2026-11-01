package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbpl implements zzdvi<zzbox> {
    private final zzbox zzfhi;

    private zzbpl(zzbox zzboxVar) {
        this.zzfhi = zzboxVar;
    }

    public static zzbpl zzt(zzbox zzboxVar) {
        return new zzbpl(zzboxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        zzbox zzboxVar = this.zzfhi;
        if (zzboxVar != null) {
            return (zzbox) zzdvo.zza(zzboxVar, "Cannot return null from a non-@Nullable @Provides method");
        }
        throw null;
    }
}
