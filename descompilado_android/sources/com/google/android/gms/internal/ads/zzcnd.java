package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcnd implements zzcra<Object> {
    private final Executor executor;
    private final zzdcp<String> zzgdm;

    public zzcnd(zzdcp<String> zzdcpVar, Executor executor) {
        this.zzgdm = zzdcpVar;
        this.executor = executor;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<Object> zzalr() {
        return zzdcf.zzb(this.zzgdm, zzcnc.zzbks, this.executor);
    }
}
