package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhx implements zzhg {
    private int[] zzake;
    private boolean zzakf;
    private int[] zzakg;
    private boolean zzaki;
    private ByteBuffer zzakh = zzagz;
    private ByteBuffer zzajg = zzagz;
    private int zzafl = -1;
    private int zzakd = -1;

    @Override // com.google.android.gms.internal.ads.zzhg
    public final int zzer() {
        return 2;
    }

    public final void zzb(int[] iArr) {
        this.zzake = iArr;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final boolean zzb(int i, int i2, int i3) throws zzhf {
        boolean z = !Arrays.equals(this.zzake, this.zzakg);
        this.zzakg = this.zzake;
        if (this.zzakg == null) {
            this.zzakf = false;
            return z;
        }
        if (i3 != 2) {
            throw new zzhf(i, i2, i3);
        }
        if (!z && this.zzakd == i && this.zzafl == i2) {
            return false;
        }
        this.zzakd = i;
        this.zzafl = i2;
        this.zzakf = i2 != this.zzakg.length;
        int i4 = 0;
        while (true) {
            int[] iArr = this.zzakg;
            if (i4 >= iArr.length) {
                return true;
            }
            int i5 = iArr[i4];
            if (i5 >= i2) {
                throw new zzhf(i, i2, i3);
            }
            this.zzakf = (i5 != i4) | this.zzakf;
            i4++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final boolean isActive() {
        return this.zzakf;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final int zzeq() {
        int[] iArr = this.zzakg;
        return iArr == null ? this.zzafl : iArr.length;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final void zzi(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        int length = (((iLimit - iPosition) / (this.zzafl * 2)) * this.zzakg.length) << 1;
        if (this.zzakh.capacity() < length) {
            this.zzakh = ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
        } else {
            this.zzakh.clear();
        }
        while (iPosition < iLimit) {
            for (int i : this.zzakg) {
                this.zzakh.putShort(byteBuffer.getShort((i * 2) + iPosition));
            }
            iPosition += this.zzafl << 1;
        }
        byteBuffer.position(iLimit);
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
        this.zzafl = -1;
        this.zzakd = -1;
        this.zzakg = null;
        this.zzakf = false;
    }
}
