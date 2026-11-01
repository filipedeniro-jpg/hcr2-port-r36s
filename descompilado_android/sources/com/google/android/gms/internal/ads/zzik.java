package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(24)
final class zzik {
    private final MediaCodec.CryptoInfo zzalv;
    private final MediaCodec.CryptoInfo.Pattern zzame;

    private zzik(MediaCodec.CryptoInfo cryptoInfo) {
        this.zzalv = cryptoInfo;
        this.zzame = new MediaCodec.CryptoInfo.Pattern(0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void set(int i, int i2) {
        this.zzame.set(i, i2);
        this.zzalv.setPattern(this.zzame);
    }
}
