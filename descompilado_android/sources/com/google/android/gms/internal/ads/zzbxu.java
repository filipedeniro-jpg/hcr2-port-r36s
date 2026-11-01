package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbxu implements zzdcg<zzbbc> {
    private final /* synthetic */ String zzfpg;
    private final /* synthetic */ zzadx zzfph;

    zzbxu(zzbxn zzbxnVar, String str, zzadx zzadxVar) {
        this.zzfpg = str;
        this.zzfph = zzadxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(zzbbc zzbbcVar) {
        zzbbcVar.zzb(this.zzfpg, this.zzfph);
    }
}
