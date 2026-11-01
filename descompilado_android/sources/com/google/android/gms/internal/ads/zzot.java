package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.os.Handler;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(23)
final class zzot implements MediaCodec.OnFrameRenderedListener {
    private final /* synthetic */ zzoo zzbix;

    private zzot(zzoo zzooVar, MediaCodec mediaCodec) {
        this.zzbix = zzooVar;
        mediaCodec.setOnFrameRenderedListener(this, new Handler());
    }

    @Override // android.media.MediaCodec.OnFrameRenderedListener
    public final void onFrameRendered(@NonNull MediaCodec mediaCodec, long j, long j2) {
        if (this != this.zzbix.zzbii) {
            return;
        }
        this.zzbix.zzis();
    }
}
