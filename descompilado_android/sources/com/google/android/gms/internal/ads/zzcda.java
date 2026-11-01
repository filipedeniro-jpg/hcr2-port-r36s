package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcda implements zzdcg<zzcvj> {
    private final /* synthetic */ zzccx zzfud;

    zzcda(zzccx zzccxVar) {
        this.zzfud = zzccxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(zzcvj zzcvjVar) {
        this.zzfud.zzftz.zza(zzcvjVar);
    }
}
