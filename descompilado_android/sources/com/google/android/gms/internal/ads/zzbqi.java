package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbqi implements zzdvi<zzbqg> {
    private final zzdvv<Set<zzbqc<zzbqh>>> zzfdr;

    private zzbqi(zzdvv<Set<zzbqc<zzbqh>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzbqi zzs(zzdvv<Set<zzbqc<zzbqh>>> zzdvvVar) {
        return new zzbqi(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbqg(this.zzfdr.get());
    }
}
