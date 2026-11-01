package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzahq implements zzaxh {
    private final /* synthetic */ zzahk zzdaa;

    zzahq(zzaho zzahoVar, zzahk zzahkVar) {
        this.zzdaa = zzahkVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaxh
    public final void run() {
        zzatm.zzdy("Rejecting reference for JS Engine.");
        this.zzdaa.reject();
    }
}
