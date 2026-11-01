package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzqo extends zzqh {
    private MessageDigest zzbqj;
    private final int zzbqk;
    private final int zzbql;

    public zzqo(int i) {
        int i2 = i / 8;
        this.zzbqk = i % 8 > 0 ? i2 + 1 : i2;
        this.zzbql = i;
    }

    @Override // com.google.android.gms.internal.ads.zzqh
    public final byte[] zzbr(String str) {
        synchronized (this.mLock) {
            this.zzbqj = zzmb();
            if (this.zzbqj == null) {
                return new byte[0];
            }
            this.zzbqj.reset();
            this.zzbqj.update(str.getBytes(Charset.forName("UTF-8")));
            byte[] bArrDigest = this.zzbqj.digest();
            byte[] bArr = new byte[bArrDigest.length > this.zzbqk ? this.zzbqk : bArrDigest.length];
            System.arraycopy(bArrDigest, 0, bArr, 0, bArr.length);
            if (this.zzbql % 8 > 0) {
                long j = 0;
                for (int i = 0; i < bArr.length; i++) {
                    if (i > 0) {
                        j <<= 8;
                    }
                    j += (long) (bArr[i] & 255);
                }
                long j2 = j >>> (8 - (this.zzbql % 8));
                for (int i2 = this.zzbqk - 1; i2 >= 0; i2--) {
                    bArr[i2] = (byte) (255 & j2);
                    j2 >>>= 8;
                }
            }
            return bArr;
        }
    }
}
