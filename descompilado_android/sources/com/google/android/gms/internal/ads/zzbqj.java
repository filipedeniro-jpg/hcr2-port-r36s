package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbqj implements zzdvi<zzbqk> {
    private final zzdvv<zzcxw> zzerp;
    private final zzdvv<zzcvb> zzfbb;

    private zzbqj(zzdvv<zzcvb> zzdvvVar, zzdvv<zzcxw> zzdvvVar2) {
        this.zzfbb = zzdvvVar;
        this.zzerp = zzdvvVar2;
    }

    public static zzbqj zzh(zzdvv<zzcvb> zzdvvVar, zzdvv<zzcxw> zzdvvVar2) {
        return new zzbqj(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbqk(this.zzfbb.get(), this.zzerp.get());
    }
}
