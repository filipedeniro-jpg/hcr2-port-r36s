package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjy implements zzjx {
    private final zzoa zzauh;
    private final int zzavb;
    private final int zzavc;
    private int zzavd;
    private int zzave;

    public zzjy(zzjs zzjsVar) {
        this.zzauh = zzjsVar.zzauh;
        this.zzauh.zzbd(12);
        this.zzavc = this.zzauh.zzij() & 255;
        this.zzavb = this.zzauh.zzij();
    }

    @Override // com.google.android.gms.internal.ads.zzjx
    public final boolean zzgj() {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzjx
    public final int zzgh() {
        return this.zzavb;
    }

    @Override // com.google.android.gms.internal.ads.zzjx
    public final int zzgi() {
        int i = this.zzavc;
        if (i == 8) {
            return this.zzauh.readUnsignedByte();
        }
        if (i == 16) {
            return this.zzauh.readUnsignedShort();
        }
        int i2 = this.zzavd;
        this.zzavd = i2 + 1;
        if (i2 % 2 == 0) {
            this.zzave = this.zzauh.readUnsignedByte();
            return (this.zzave & PsExtractor.VIDEO_STREAM_MASK) >> 4;
        }
        return this.zzave & 15;
    }
}
