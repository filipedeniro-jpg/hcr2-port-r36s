package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbyo implements zzdvi<zzcfk<zzbyf>> {
    private final zzdvv<zzcjw<zzbyf, zzcvs, zzcgt>> zzfbs;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<zzcjw<zzbyf, zzcvs, zzcgu>> zzfpw;

    public zzbyo(zzdvv<zzcjw<zzbyf, zzcvs, zzcgt>> zzdvvVar, zzdvv<zzcjw<zzbyf, zzcvs, zzcgu>> zzdvvVar2, zzdvv<zzcvk> zzdvvVar3) {
        this.zzfbs = zzdvvVar;
        this.zzfpw = zzdvvVar2;
        this.zzfds = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        zzcjw<zzbyf, zzcvs, zzcgt> zzcjwVar;
        zzdvv<zzcjw<zzbyf, zzcvs, zzcgt>> zzdvvVar = this.zzfbs;
        zzdvv<zzcjw<zzbyf, zzcvs, zzcgu>> zzdvvVar2 = this.zzfpw;
        if (this.zzfds.get().zzgjz.contains("new_rewarded")) {
            zzcjwVar = zzdvvVar2.get();
        } else {
            zzcjwVar = zzdvvVar.get();
        }
        return (zzcfk) zzdvo.zza(zzcjwVar, "Cannot return null from a non-@Nullable @Provides method");
    }
}
