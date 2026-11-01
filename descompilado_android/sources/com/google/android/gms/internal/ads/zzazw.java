package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzazw implements zzduu {
    private final ByteBuffer zzakh;

    zzazw(ByteBuffer byteBuffer) {
        this.zzakh = byteBuffer.duplicate();
    }

    @Override // com.google.android.gms.internal.ads.zzduu, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
    }

    @Override // com.google.android.gms.internal.ads.zzduu
    public final int read(ByteBuffer byteBuffer) throws IOException {
        if (this.zzakh.remaining() == 0 && byteBuffer.remaining() > 0) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), this.zzakh.remaining());
        byte[] bArr = new byte[iMin];
        this.zzakh.get(bArr);
        byteBuffer.put(bArr);
        return iMin;
    }

    @Override // com.google.android.gms.internal.ads.zzduu
    public final long size() throws IOException {
        return this.zzakh.limit();
    }

    @Override // com.google.android.gms.internal.ads.zzduu
    public final long position() throws IOException {
        return this.zzakh.position();
    }

    @Override // com.google.android.gms.internal.ads.zzduu
    public final void zzew(long j) throws IOException {
        this.zzakh.position((int) j);
    }

    @Override // com.google.android.gms.internal.ads.zzduu
    public final ByteBuffer zzh(long j, long j2) throws IOException {
        int iPosition = this.zzakh.position();
        this.zzakh.position((int) j);
        ByteBuffer byteBufferSlice = this.zzakh.slice();
        byteBufferSlice.limit((int) j2);
        this.zzakh.position(iPosition);
        return byteBufferSlice;
    }
}
