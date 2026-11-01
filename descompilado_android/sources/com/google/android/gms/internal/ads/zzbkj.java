package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbkj implements zzdvi<zzcvb> {
    private final zzbkk zzffh;

    private zzbkj(zzbkk zzbkkVar) {
        this.zzffh = zzbkkVar;
    }

    public static zzbkj zza(zzbkk zzbkkVar) {
        return new zzbkj(zzbkkVar);
    }

    public static zzcvb zzb(zzbkk zzbkkVar) {
        return (zzcvb) zzdvo.zza(zzbkkVar.zzafm(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzb(this.zzffh);
    }
}
