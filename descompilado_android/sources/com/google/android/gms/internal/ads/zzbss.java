package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbss implements zzdvi<zzbui> {
    private final zzbsn zzfis;

    private zzbss(zzbsn zzbsnVar) {
        this.zzfis = zzbsnVar;
    }

    public static zzbss zzd(zzbsn zzbsnVar) {
        return new zzbss(zzbsnVar);
    }

    public static zzbui zze(zzbsn zzbsnVar) {
        return (zzbui) zzdvo.zza(zzbsnVar.zzagu(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zze(this.zzfis);
    }
}
