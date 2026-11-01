package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzccf implements zzdbq {
    static final zzdbq zzbks = new zzccf();

    private zzccf() {
    }

    @Override // com.google.android.gms.internal.ads.zzdbq
    public final zzdcp zzf(Object obj) {
        return zzdcf.zzi(((ExecutionException) obj).getCause());
    }
}
