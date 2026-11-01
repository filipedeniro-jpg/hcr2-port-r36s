package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaxv implements Runnable {
    private final /* synthetic */ zzaxr zzdxf;
    private final /* synthetic */ String zzdxg;
    private final /* synthetic */ String zzdxh;

    zzaxv(zzaxr zzaxrVar, String str, String str2) {
        this.zzdxf = zzaxrVar;
        this.zzdxg = str;
        this.zzdxh = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzdxf.zzdxe != null) {
            this.zzdxf.zzdxe.zzn(this.zzdxg, this.zzdxh);
        }
    }
}
