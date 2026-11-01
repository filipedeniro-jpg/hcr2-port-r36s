package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbsp implements zzdvi<zzbsn> {
    private final zzbsn zzfis;

    private zzbsp(zzbsn zzbsnVar) {
        this.zzfis = zzbsnVar;
    }

    public static zzbsp zzc(zzbsn zzbsnVar) {
        return new zzbsp(zzbsnVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        zzbsn zzbsnVar = this.zzfis;
        if (zzbsnVar != null) {
            return (zzbsn) zzdvo.zza(zzbsnVar, "Cannot return null from a non-@Nullable @Provides method");
        }
        throw null;
    }
}
