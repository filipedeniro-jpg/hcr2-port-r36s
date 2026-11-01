package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbxt implements zzdcg<zzbbc> {
    private final /* synthetic */ String zzfpi;
    private final /* synthetic */ Map zzfpj;

    zzbxt(zzbxn zzbxnVar, String str, Map map) {
        this.zzfpi = str;
        this.zzfpj = map;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(zzbbc zzbbcVar) {
        zzbbcVar.zza(this.zzfpi, this.zzfpj);
    }
}
