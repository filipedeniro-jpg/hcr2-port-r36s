package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcew implements zzdvi<zzcet> {
    private final zzdvv<zzcez> zzfvx;

    private zzcew(zzdvv<zzcez> zzdvvVar) {
        this.zzfvx = zzdvvVar;
    }

    public static zzcew zzad(zzdvv<zzcez> zzdvvVar) {
        return new zzcew(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcet(this.zzfvx.get());
    }
}
