package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzii {
    public byte[] iv;
    private byte[] key;
    private int mode;
    public int[] numBytesOfClearData;
    public int[] numBytesOfEncryptedData;
    private int numSubSamples;
    private int zzalt;
    private int zzalu;
    private final MediaCodec.CryptoInfo zzalv;
    private final zzik zzalw;

    public zzii() {
        this.zzalv = zzoh.SDK_INT >= 16 ? new MediaCodec.CryptoInfo() : null;
        this.zzalw = zzoh.SDK_INT >= 24 ? new zzik(this.zzalv) : null;
    }

    public final void set(int i, int[] iArr, int[] iArr2, byte[] bArr, byte[] bArr2, int i2) {
        this.numSubSamples = i;
        this.numBytesOfClearData = iArr;
        this.numBytesOfEncryptedData = iArr2;
        this.key = bArr;
        this.iv = bArr2;
        this.mode = i2;
        this.zzalt = 0;
        this.zzalu = 0;
        if (zzoh.SDK_INT >= 16) {
            MediaCodec.CryptoInfo cryptoInfo = this.zzalv;
            cryptoInfo.numSubSamples = this.numSubSamples;
            cryptoInfo.numBytesOfClearData = this.numBytesOfClearData;
            cryptoInfo.numBytesOfEncryptedData = this.numBytesOfEncryptedData;
            cryptoInfo.key = this.key;
            cryptoInfo.iv = this.iv;
            cryptoInfo.mode = this.mode;
            if (zzoh.SDK_INT >= 24) {
                this.zzalw.set(0, 0);
            }
        }
    }

    @TargetApi(16)
    public final MediaCodec.CryptoInfo zzft() {
        return this.zzalv;
    }
}
