package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbiy implements zzdvi<zzcfk<zzbhy>> {
    private final zzdvv<zzcjw<zzbhy, zzcvs, zzcgu>> zzerx;
    private final zzdvv<Boolean> zzewq;
    private final zzdvv<zzcif> zzews;

    public zzbiy(zzdvv<Boolean> zzdvvVar, zzdvv<zzcif> zzdvvVar2, zzdvv<zzcjw<zzbhy, zzcvs, zzcgu>> zzdvvVar3) {
        this.zzewq = zzdvvVar;
        this.zzews = zzdvvVar2;
        this.zzerx = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        boolean zBooleanValue = this.zzewq.get().booleanValue();
        zzcif zzcifVar = this.zzews.get();
        zzcjw<zzbhy, zzcvs, zzcgu> zzcjwVar = this.zzerx.get();
        if (!zBooleanValue) {
            zzcifVar = zzcjwVar;
        }
        return (zzcfk) zzdvo.zza(zzcifVar, "Cannot return null from a non-@Nullable @Provides method");
    }
}
