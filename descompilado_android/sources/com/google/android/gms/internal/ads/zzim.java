package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzim extends zzif {
    public long zzami;
    public ByteBuffer zzcq;
    public final zzii zzamh = new zzii();
    private final int zzamj = 0;

    public zzim(int i) {
    }

    public final void zzy(int i) throws IllegalStateException {
        ByteBuffer byteBuffer = this.zzcq;
        if (byteBuffer == null) {
            this.zzcq = zzz(i);
            return;
        }
        int iCapacity = byteBuffer.capacity();
        int iPosition = this.zzcq.position();
        int i2 = i + iPosition;
        if (iCapacity >= i2) {
            return;
        }
        ByteBuffer byteBufferZzz = zzz(i2);
        if (iPosition > 0) {
            this.zzcq.position(0);
            this.zzcq.limit(iPosition);
            byteBufferZzz.put(this.zzcq);
        }
        this.zzcq = byteBufferZzz;
    }

    public final boolean zzfv() {
        return zzw(1073741824);
    }

    @Override // com.google.android.gms.internal.ads.zzif
    public final void clear() {
        super.clear();
        ByteBuffer byteBuffer = this.zzcq;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
    }

    private final ByteBuffer zzz(int i) {
        ByteBuffer byteBuffer = this.zzcq;
        int iCapacity = byteBuffer == null ? 0 : byteBuffer.capacity();
        StringBuilder sb = new StringBuilder(44);
        sb.append("Buffer too small (");
        sb.append(iCapacity);
        sb.append(" < ");
        sb.append(i);
        sb.append(")");
        throw new IllegalStateException(sb.toString());
    }
}
