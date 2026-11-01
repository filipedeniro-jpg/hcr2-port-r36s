package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.MuteThisAdListener;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzwc extends zzwa {
    private final MuteThisAdListener zzcdq;

    public zzwc(MuteThisAdListener muteThisAdListener) {
        this.zzcdq = muteThisAdListener;
    }

    @Override // com.google.android.gms.internal.ads.zzvx
    public final void onAdMuted() {
        this.zzcdq.onAdMuted();
    }
}
