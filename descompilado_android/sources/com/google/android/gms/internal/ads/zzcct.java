package com.google.android.gms.internal.ads;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcct implements zzdvi<List<String>> {
    private static final zzcct zzftx = new zzcct();

    public static zzcct zzakc() {
        return zzftx;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (List) zzdvo.zza(zzyt.zzpn(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
