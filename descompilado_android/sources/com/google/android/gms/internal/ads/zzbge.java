package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbge implements zzdvi<zzbgb> {
    private final zzdvv<zzato> zzeoo;

    private zzbge(zzdvv<zzato> zzdvvVar) {
        this.zzeoo = zzdvvVar;
    }

    public static zzbge zza(zzdvv<zzato> zzdvvVar) {
        return new zzbge(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbgb(this.zzeoo.get());
    }
}
