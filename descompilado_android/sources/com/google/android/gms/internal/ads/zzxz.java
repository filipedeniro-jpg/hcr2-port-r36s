package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzxz extends zzwo {
    private final VideoController.VideoLifecycleCallbacks zzabo;

    public zzxz(VideoController.VideoLifecycleCallbacks videoLifecycleCallbacks) {
        this.zzabo = videoLifecycleCallbacks;
    }

    @Override // com.google.android.gms.internal.ads.zzwl
    public final void onVideoStart() {
        this.zzabo.onVideoStart();
    }

    @Override // com.google.android.gms.internal.ads.zzwl
    public final void onVideoPlay() {
        this.zzabo.onVideoPlay();
    }

    @Override // com.google.android.gms.internal.ads.zzwl
    public final void onVideoPause() {
        this.zzabo.onVideoPause();
    }

    @Override // com.google.android.gms.internal.ads.zzwl
    public final void onVideoEnd() {
        this.zzabo.onVideoEnd();
    }

    @Override // com.google.android.gms.internal.ads.zzwl
    public final void onVideoMute(boolean z) {
        this.zzabo.onVideoMute(z);
    }
}
