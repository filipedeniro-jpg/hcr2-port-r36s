package com.google.android.gms.internal.ads;

import com.amazon.device.ads.WebRequest;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzagq implements Runnable {
    private final /* synthetic */ String zzcza;
    private final /* synthetic */ zzagm zzczb;

    zzagq(zzagm zzagmVar, String str) {
        this.zzczb = zzagmVar;
        this.zzcza = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzczb.zzcyx.loadData(this.zzcza, WebRequest.CONTENT_TYPE_HTML, "UTF-8");
    }
}
