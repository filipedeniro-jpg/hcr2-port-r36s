package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcna implements zzdvi<zzcnb> {
    private final zzdvv<zzcvk> zzfds;

    private zzcna(zzdvv<zzcvk> zzdvvVar) {
        this.zzfds = zzdvvVar;
    }

    public static zzcna zzah(zzdvv<zzcvk> zzdvvVar) {
        return new zzcna(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcnb(this.zzfds.get());
    }
}
