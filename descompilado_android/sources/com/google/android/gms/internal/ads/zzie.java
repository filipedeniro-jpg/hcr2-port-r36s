package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzie implements zzhg {
    private boolean zzaki;
    private int zzakd = -1;
    private int zzafl = -1;
    private int zzaib = 0;
    private ByteBuffer zzakh = zzagz;
    private ByteBuffer zzajg = zzagz;

    @Override // com.google.android.gms.internal.ads.zzhg
    public final int zzer() {
        return 2;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final boolean zzb(int i, int i2, int i3) throws zzhf {
        if (i3 != 3 && i3 != 2 && i3 != Integer.MIN_VALUE && i3 != 1073741824) {
            throw new zzhf(i, i2, i3);
        }
        if (this.zzakd == i && this.zzafl == i2 && this.zzaib == i3) {
            return false;
        }
        this.zzakd = i;
        this.zzafl = i2;
        this.zzaib = i3;
        if (i3 != 2) {
            return true;
        }
        this.zzakh = zzagz;
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final boolean isActive() {
        int i = this.zzaib;
        return (i == 0 || i == 2) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final int zzeq() {
        return this.zzafl;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x002b  */
    /* JADX WARN: Code duplicated, block: B:14:0x003a  */
    /* JADX WARN: Code duplicated, block: B:17:0x0043 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:18:0x0045 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:19:0x0047 A[ADDED_TO_REGION, LOOP:0: B:19:0x0047->B:20:0x0049, LOOP_START, PHI: r0
  0x0047: PHI (r0v6 int) = (r0v0 int), (r0v7 int) binds: [B:18:0x0045, B:20:0x0049] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:20:0x0049 A[LOOP:0: B:19:0x0047->B:20:0x0049, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:21:0x0062  */
    /* JADX WARN: Code duplicated, block: B:23:0x0068 A[ADDED_TO_REGION, LOOP:1: B:23:0x0068->B:24:0x006a, LOOP_START, PHI: r0
  0x0068: PHI (r0v4 int) = (r0v0 int), (r0v5 int) binds: [B:17:0x0043, B:24:0x006a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:24:0x006a A[LOOP:1: B:23:0x0068->B:24:0x006a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:25:0x0081 A[ADDED_TO_REGION, LOOP:2: B:25:0x0081->B:26:0x0083, LOOP_START, PHI: r0
  0x0081: PHI (r0v1 int) = (r0v0 int), (r0v2 int) binds: [B:16:0x0041, B:26:0x0083] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:26:0x0083 A[LOOP:2: B:25:0x0081->B:26:0x0083, LOOP_END] */
    @Override // com.google.android.gms.internal.ads.zzhg
    public final void zzi(ByteBuffer byteBuffer) {
        int i;
        int i2;
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        int i3 = iLimit - iPosition;
        int i4 = this.zzaib;
        if (i4 == Integer.MIN_VALUE) {
            i3 /= 3;
        } else {
            if (i4 != 3) {
                if (i4 == 1073741824) {
                    i = i3 / 2;
                } else {
                    throw new IllegalStateException();
                }
            }
            if (this.zzakh.capacity() < i) {
                this.zzakh = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
            } else {
                this.zzakh.clear();
            }
            i2 = this.zzaib;
            if (i2 != Integer.MIN_VALUE) {
                while (iPosition < iLimit) {
                    this.zzakh.put(byteBuffer.get(iPosition + 1));
                    this.zzakh.put(byteBuffer.get(iPosition + 2));
                    iPosition += 3;
                }
            } else if (i2 != 3) {
                while (iPosition < iLimit) {
                    this.zzakh.put((byte) 0);
                    this.zzakh.put((byte) ((byteBuffer.get(iPosition) & 255) - 128));
                    iPosition++;
                }
            } else {
                if (i2 == 1073741824) {
                    throw new IllegalStateException();
                }
                while (iPosition < iLimit) {
                    this.zzakh.put(byteBuffer.get(iPosition + 2));
                    this.zzakh.put(byteBuffer.get(iPosition + 3));
                    iPosition += 4;
                }
            }
            byteBuffer.position(byteBuffer.limit());
            this.zzakh.flip();
            this.zzajg = this.zzakh;
        }
        i = i3 << 1;
        if (this.zzakh.capacity() < i) {
            this.zzakh = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
        } else {
            this.zzakh.clear();
        }
        i2 = this.zzaib;
        if (i2 != Integer.MIN_VALUE) {
            while (iPosition < iLimit) {
                this.zzakh.put(byteBuffer.get(iPosition + 1));
                this.zzakh.put(byteBuffer.get(iPosition + 2));
                iPosition += 3;
            }
        } else if (i2 != 3) {
            while (iPosition < iLimit) {
                this.zzakh.put((byte) 0);
                this.zzakh.put((byte) ((byteBuffer.get(iPosition) & 255) - 128));
                iPosition++;
            }
        } else {
            if (i2 == 1073741824) {
                throw new IllegalStateException();
            }
            while (iPosition < iLimit) {
                this.zzakh.put(byteBuffer.get(iPosition + 2));
                this.zzakh.put(byteBuffer.get(iPosition + 3));
                iPosition += 4;
            }
        }
        byteBuffer.position(byteBuffer.limit());
        this.zzakh.flip();
        this.zzajg = this.zzakh;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final void zzes() {
        this.zzaki = true;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final ByteBuffer zzet() {
        ByteBuffer byteBuffer = this.zzajg;
        this.zzajg = zzagz;
        return byteBuffer;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final boolean zzel() {
        return this.zzaki && this.zzajg == zzagz;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final void flush() {
        this.zzajg = zzagz;
        this.zzaki = false;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final void reset() {
        flush();
        this.zzakh = zzagz;
        this.zzakd = -1;
        this.zzafl = -1;
        this.zzaib = 0;
    }
}
