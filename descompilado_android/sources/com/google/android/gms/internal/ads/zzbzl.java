package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbzl implements zzdvi<zzbqc<zzbms>> {
    private final zzdvv<zzbzk> zzfcs;
    private final zzbzm zzfql;

    private zzbzl(zzbzm zzbzmVar, zzdvv<zzbzk> zzdvvVar) {
        this.zzfql = zzbzmVar;
        this.zzfcs = zzdvvVar;
    }

    public static zzbzl zza(zzbzm zzbzmVar, zzdvv<zzbzk> zzdvvVar) {
        return new zzbzl(zzbzmVar, zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzfcs.get(), zzawx.zzdwa), "Cannot return null from a non-@Nullable @Provides method");
    }
}
