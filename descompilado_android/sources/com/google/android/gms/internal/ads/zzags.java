package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzags implements Runnable {
    private final /* synthetic */ zzagm zzczb;
    private final /* synthetic */ String zzczd;

    zzags(zzagm zzagmVar, String str) {
        this.zzczb = zzagmVar;
        this.zzczd = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzczb.zzcyx.loadUrl(this.zzczd);
    }
}
