package com.google.android.gms.internal.ads;

import com.ironsource.sdk.ISNAdView.ISNAdViewConstants;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzayj implements Runnable {
    private final /* synthetic */ zzayc zzdyg;
    private final /* synthetic */ boolean zzdyj;

    zzayj(zzayc zzaycVar, boolean z) {
        this.zzdyg = zzaycVar;
        this.zzdyj = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzdyg.zzd("windowVisibilityChanged", ISNAdViewConstants.IS_VISIBLE_KEY, String.valueOf(this.zzdyj));
    }
}
