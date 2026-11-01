package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzig implements zzhg {
    private boolean zzaki;
    private zzid zzalp;
    private long zzalr;
    private long zzals;
    private float zzafy = 1.0f;
    private float zzafz = 1.0f;
    private int zzafl = -1;
    private int zzakd = -1;
    private ByteBuffer zzakh = zzagz;
    private ShortBuffer zzalq = this.zzakh.asShortBuffer();
    private ByteBuffer zzajg = zzagz;

    @Override // com.google.android.gms.internal.ads.zzhg
    public final int zzer() {
        return 2;
    }

    public final float zzb(float f) {
        this.zzafy = zzoh.zza(f, 0.1f, 8.0f);
        return this.zzafy;
    }

    public final float zzc(float f) {
        this.zzafz = zzoh.zza(f, 0.1f, 8.0f);
        return f;
    }

    public final long zzfr() {
        return this.zzalr;
    }

    public final long zzfs() {
        return this.zzals;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final boolean zzb(int i, int i2, int i3) throws zzhf {
        if (i3 != 2) {
            throw new zzhf(i, i2, i3);
        }
        if (this.zzakd == i && this.zzafl == i2) {
            return false;
        }
        this.zzakd = i;
        this.zzafl = i2;
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final boolean isActive() {
        return Math.abs(this.zzafy - 1.0f) >= 0.01f || Math.abs(this.zzafz - 1.0f) >= 0.01f;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final int zzeq() {
        return this.zzafl;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final void zzi(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            ShortBuffer shortBufferAsShortBuffer = byteBuffer.asShortBuffer();
            int iRemaining = byteBuffer.remaining();
            this.zzalr += (long) iRemaining;
            this.zzalp.zza(shortBufferAsShortBuffer);
            byteBuffer.position(byteBuffer.position() + iRemaining);
        }
        int iZzfm = (this.zzalp.zzfm() * this.zzafl) << 1;
        if (iZzfm > 0) {
            if (this.zzakh.capacity() < iZzfm) {
                this.zzakh = ByteBuffer.allocateDirect(iZzfm).order(ByteOrder.nativeOrder());
                this.zzalq = this.zzakh.asShortBuffer();
            } else {
                this.zzakh.clear();
                this.zzalq.clear();
            }
            this.zzalp.zzb(this.zzalq);
            this.zzals += (long) iZzfm;
            this.zzakh.limit(iZzfm);
            this.zzajg = this.zzakh;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final void zzes() {
        this.zzalp.zzes();
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
        if (!this.zzaki) {
            return false;
        }
        zzid zzidVar = this.zzalp;
        return zzidVar == null || zzidVar.zzfm() == 0;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final void flush() {
        this.zzalp = new zzid(this.zzakd, this.zzafl);
        this.zzalp.setSpeed(this.zzafy);
        this.zzalp.zza(this.zzafz);
        this.zzajg = zzagz;
        this.zzalr = 0L;
        this.zzals = 0L;
        this.zzaki = false;
    }

    @Override // com.google.android.gms.internal.ads.zzhg
    public final void reset() {
        this.zzalp = null;
        this.zzakh = zzagz;
        this.zzalq = this.zzakh.asShortBuffer();
        this.zzajg = zzagz;
        this.zzafl = -1;
        this.zzakd = -1;
        this.zzalr = 0L;
        this.zzals = 0L;
        this.zzaki = false;
    }
}
