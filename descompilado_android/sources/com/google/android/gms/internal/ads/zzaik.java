package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaik implements zzaxh {
    private final /* synthetic */ zzaxf zzcxs;
    private final /* synthetic */ zzahk zzdas;

    zzaik(zzaii zzaiiVar, zzaxf zzaxfVar, zzahk zzahkVar) {
        this.zzcxs = zzaxfVar;
        this.zzdas = zzahkVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaxh
    public final void run() {
        this.zzcxs.setException(new zzahw("Unable to obtain a JavascriptEngine."));
        this.zzdas.release();
    }
}
