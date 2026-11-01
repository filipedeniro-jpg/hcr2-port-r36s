package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzarj implements zzdcg<Void> {
    private final /* synthetic */ zzdcp zzdoj;

    zzarj(zzarf zzarfVar, zzdcp zzdcpVar) {
        this.zzdoj = zzdcpVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        zzarf.zzdnv.remove(this.zzdoj);
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(Void r2) {
        zzarf.zzdnv.remove(this.zzdoj);
    }
}
