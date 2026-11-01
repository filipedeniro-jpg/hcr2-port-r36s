package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbkl implements zzdvi<zzcvj> {
    private final zzbkk zzffh;

    private zzbkl(zzbkk zzbkkVar) {
        this.zzffh = zzbkkVar;
    }

    public static zzbkl zzc(zzbkk zzbkkVar) {
        return new zzbkl(zzbkkVar);
    }

    public static zzcvj zzd(zzbkk zzbkkVar) {
        return (zzcvj) zzdvo.zza(zzbkkVar.zzafl(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzd(this.zzffh);
    }
}
