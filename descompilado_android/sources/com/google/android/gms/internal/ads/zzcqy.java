package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqy implements zzdvi<zzcqw> {
    private final zzdvv<zzctp> zzfik;

    private zzcqy(zzdvv<zzctp> zzdvvVar) {
        this.zzfik = zzdvvVar;
    }

    public static zzcqy zzal(zzdvv<zzctp> zzdvvVar) {
        return new zzcqy(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcqw(this.zzfik.get());
    }
}
