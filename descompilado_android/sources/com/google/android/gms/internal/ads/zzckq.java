package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzckq implements zzdvi<zzcko> {
    private final zzcko zzgan;

    private zzckq(zzcko zzckoVar) {
        this.zzgan = zzckoVar;
    }

    public static zzckq zzd(zzcko zzckoVar) {
        return new zzckq(zzckoVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        zzcko zzckoVar = this.zzgan;
        if (zzckoVar != null) {
            return (zzcko) zzdvo.zza(zzckoVar, "Cannot return null from a non-@Nullable @Provides method");
        }
        throw null;
    }
}
