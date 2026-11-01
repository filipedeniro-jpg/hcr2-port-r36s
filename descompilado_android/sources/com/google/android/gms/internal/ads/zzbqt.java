package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzbqt implements zzbou {
    static final zzbou zzfgm = new zzbqt();

    private zzbqt() {
    }

    @Override // com.google.android.gms.internal.ads.zzbou
    public final void zzp(Object obj) {
        ((VideoController.VideoLifecycleCallbacks) obj).onVideoEnd();
    }
}
