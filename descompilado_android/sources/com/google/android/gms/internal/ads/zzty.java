package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzty extends zzvl {
    private final AppEventListener zzbkg;

    public zzty(AppEventListener appEventListener) {
        this.zzbkg = appEventListener;
    }

    @Override // com.google.android.gms.internal.ads.zzvm
    public final void onAppEvent(String str, String str2) {
        this.zzbkg.onAppEvent(str, str2);
    }

    public final AppEventListener getAppEventListener() {
        return this.zzbkg;
    }
}
