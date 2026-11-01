package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbdw implements zzdvi<zzcfo<zzcvs, zzcgu>> {
    private final zzbdr zzeji;
    private final zzdvv<zzcgw> zzejj;

    public zzbdw(zzbdr zzbdrVar, zzdvv<zzcgw> zzdvvVar) {
        this.zzeji = zzbdrVar;
        this.zzejj = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcfo) zzdvo.zza(new zzcho(this.zzejj.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
