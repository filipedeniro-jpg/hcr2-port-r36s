package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzqk extends zzqh {
    private MessageDigest zzbqj;

    @Override // com.google.android.gms.internal.ads.zzqh
    public final byte[] zzbr(String str) {
        byte[] bArrArray;
        String[] strArrSplit = str.split(" ");
        int length = 4;
        if (strArrSplit.length == 1) {
            int iZzbs = zzql.zzbs(strArrSplit[0]);
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferAllocate.putInt(iZzbs);
            bArrArray = byteBufferAllocate.array();
        } else if (strArrSplit.length < 5) {
            bArrArray = new byte[strArrSplit.length << 1];
            for (int i = 0; i < strArrSplit.length; i++) {
                int iZzbs2 = zzql.zzbs(strArrSplit[i]);
                int i2 = (iZzbs2 >> 16) ^ (65535 & iZzbs2);
                byte[] bArr = {(byte) i2, (byte) (i2 >> 8)};
                int i3 = i << 1;
                bArrArray[i3] = bArr[0];
                bArrArray[i3 + 1] = bArr[1];
            }
        } else {
            bArrArray = new byte[strArrSplit.length];
            for (int i4 = 0; i4 < strArrSplit.length; i4++) {
                int iZzbs3 = zzql.zzbs(strArrSplit[i4]);
                bArrArray[i4] = (byte) ((iZzbs3 >> 24) ^ (((iZzbs3 & 255) ^ ((iZzbs3 >> 8) & 255)) ^ ((iZzbs3 >> 16) & 255)));
            }
        }
        this.zzbqj = zzmb();
        synchronized (this.mLock) {
            if (this.zzbqj == null) {
                return new byte[0];
            }
            this.zzbqj.reset();
            this.zzbqj.update(bArrArray);
            byte[] bArrDigest = this.zzbqj.digest();
            if (bArrDigest.length <= 4) {
                length = bArrDigest.length;
            }
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArrDigest, 0, bArr2, 0, bArr2.length);
            return bArr2;
        }
    }
}
