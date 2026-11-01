package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbyg implements zzdvi<zzbyd> {
    private final zzdvv<zzbnb> zzelz;
    private final zzdvv<zzcvb> zzfbb;

    public zzbyg(zzdvv<zzbnb> zzdvvVar, zzdvv<zzcvb> zzdvvVar2) {
        this.zzelz = zzdvvVar;
        this.zzfbb = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbyd(this.zzelz.get(), this.zzfbb.get());
    }
}
