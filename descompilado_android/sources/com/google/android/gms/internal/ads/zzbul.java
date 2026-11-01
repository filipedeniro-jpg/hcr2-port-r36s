package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbul implements zzdvi<zzbtx> {
    private final zzbuj zzflt;

    private zzbul(zzbuj zzbujVar) {
        this.zzflt = zzbujVar;
    }

    public static zzbul zza(zzbuj zzbujVar) {
        return new zzbul(zzbujVar);
    }

    public static zzbtx zzb(zzbuj zzbujVar) {
        return (zzbtx) zzdvo.zza(zzbujVar.zzaih(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzb(this.zzflt);
    }
}
