package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
final class zzgl extends zzgj {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzpi;
    private int zzpj;
    private int zzpk;
    private int zzpl;
    private int zzpm;

    private zzgl(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzpm = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzpk = this.pos;
        this.zzpi = z;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final int zzfb() throws IOException {
        if (zzfr()) {
            this.zzpl = 0;
            return 0;
        }
        this.zzpl = zzft();
        int i = this.zzpl;
        if ((i >>> 3) != 0) {
            return i;
        }
        throw zzhq.zzhh();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final void zzy(int i) throws zzhq {
        if (this.zzpl != i) {
            throw zzhq.zzhi();
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final boolean zzz(int i) throws IOException {
        int iZzfb;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.limit - this.pos >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.buffer;
                    int i4 = this.pos;
                    this.pos = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzhq.zzhg();
            }
            while (i3 < 10) {
                if (zzfy() < 0) {
                    i3++;
                }
            }
            throw zzhq.zzhg();
            return true;
        }
        if (i2 == 1) {
            zzad(8);
            return true;
        }
        if (i2 == 2) {
            zzad(zzft());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 == 5) {
                zzad(4);
                return true;
            }
            throw zzhq.zzhj();
        }
        do {
            iZzfb = zzfb();
            if (iZzfb == 0) {
                break;
            }
        } while (zzz(iZzfb));
        zzy(((i >>> 3) << 3) | 4);
        return true;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzfw());
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzfv());
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final long zzfc() throws IOException {
        return zzfu();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final long zzfd() throws IOException {
        return zzfu();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final int zzfe() throws IOException {
        return zzft();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final long zzff() throws IOException {
        return zzfw();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final int zzfg() throws IOException {
        return zzfv();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final boolean zzfh() throws IOException {
        return zzfu() != 0;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final String readString() throws IOException {
        int iZzft = zzft();
        if (iZzft > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (iZzft <= i - i2) {
                String str = new String(this.buffer, i2, iZzft, zzhm.UTF_8);
                this.pos += iZzft;
                return str;
            }
        }
        if (iZzft == 0) {
            return "";
        }
        if (iZzft < 0) {
            throw zzhq.zzhf();
        }
        throw zzhq.zzhe();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final String zzfi() throws IOException {
        int iZzft = zzft();
        if (iZzft > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (iZzft <= i - i2) {
                String strZzg = zzkg.zzg(this.buffer, i2, iZzft);
                this.pos += iZzft;
                return strZzg;
            }
        }
        if (iZzft == 0) {
            return "";
        }
        if (iZzft <= 0) {
            throw zzhq.zzhf();
        }
        throw zzhq.zzhe();
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0033 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:16:0x0035 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:17:0x0037  */
    /* JADX WARN: Code duplicated, block: B:20:0x003e  */
    /* JADX WARN: Code duplicated, block: B:22:0x0043  */
    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final zzfx zzfj() throws IOException {
        byte[] bArrCopyOfRange;
        int iZzft = zzft();
        if (iZzft > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (iZzft <= i - i2) {
                zzfx zzfxVarZza = zzfx.zza(this.buffer, i2, iZzft);
                this.pos += iZzft;
                return zzfxVarZza;
            }
        }
        if (iZzft == 0) {
            return zzfx.zzov;
        }
        if (iZzft > 0) {
            int i3 = this.limit;
            int i4 = this.pos;
            if (iZzft <= i3 - i4) {
                this.pos = iZzft + i4;
                bArrCopyOfRange = Arrays.copyOfRange(this.buffer, i4, this.pos);
            } else {
                if (iZzft <= 0) {
                    throw zzhq.zzhe();
                }
                if (iZzft == 0) {
                    bArrCopyOfRange = zzhm.zzua;
                } else {
                    throw zzhq.zzhf();
                }
            }
        } else {
            if (iZzft <= 0) {
                throw zzhq.zzhe();
            }
            if (iZzft == 0) {
                bArrCopyOfRange = zzhm.zzua;
            } else {
                throw zzhq.zzhf();
            }
        }
        return zzfx.zzb(bArrCopyOfRange);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final int zzfk() throws IOException {
        return zzft();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final int zzfl() throws IOException {
        return zzft();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final int zzfm() throws IOException {
        return zzfv();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final long zzfn() throws IOException {
        return zzfw();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final int zzfo() throws IOException {
        return zzac(zzft());
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final long zzfp() throws IOException {
        return zze(zzfu());
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0066, code lost:
    
        if (r2[r3] >= 0) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzft() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.pos
            int r1 = r5.limit
            if (r1 == r0) goto L6b
            byte[] r2 = r5.buffer
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L11
            r5.pos = r3
            return r0
        L11:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L6b
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L22
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L68
        L22:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L2f
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L2d:
            r1 = r3
            goto L68
        L2f:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L3d
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L68
        L3d:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L68
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L68
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r2 = r2[r3]
            if (r2 < 0) goto L6b
        L68:
            r5.pos = r1
            return r0
        L6b:
            long r0 = r5.zzfq()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_remote_config.zzgl.zzft():int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b0, code lost:
    
        if (r2[r0] >= 0) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final long zzfu() throws java.io.IOException {
        /*
            r11 = this;
            int r0 = r11.pos
            int r1 = r11.limit
            if (r1 == r0) goto Lb5
            byte[] r2 = r11.buffer
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L12
            r11.pos = r3
            long r0 = (long) r0
            return r0
        L12:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto Lb5
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L26
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
        L22:
            long r2 = (long) r0
            r3 = r2
            goto Lb2
        L26:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L37
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            long r0 = (long) r0
            r9 = r0
            r1 = r3
            r3 = r9
            goto Lb2
        L37:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L45
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L22
        L45:
            long r3 = (long) r0
            int r0 = r1 + 1
            r1 = r2[r1]
            long r5 = (long) r1
            r1 = 28
            long r5 = r5 << r1
            long r3 = r3 ^ r5
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L5c
            r1 = 266354560(0xfe03f80, double:1.315966377E-315)
        L58:
            long r1 = r1 ^ r3
            r3 = r1
        L5a:
            r1 = r0
            goto Lb2
        L5c:
            int r1 = r0 + 1
            r0 = r2[r0]
            long r7 = (long) r0
            r0 = 35
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L70
            r5 = -34093383808(0xfffffff80fe03f80, double:NaN)
        L6e:
            long r3 = r3 ^ r5
            goto Lb2
        L70:
            int r0 = r1 + 1
            r1 = r2[r1]
            long r7 = (long) r1
            r1 = 42
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L83
            r1 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            goto L58
        L83:
            int r1 = r0 + 1
            r0 = r2[r0]
            long r7 = (long) r0
            r0 = 49
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L96
            r5 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            goto L6e
        L96:
            int r0 = r1 + 1
            r1 = r2[r1]
            long r7 = (long) r1
            r1 = 56
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            r7 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 >= 0) goto L5a
            int r1 = r0 + 1
            r0 = r2[r0]
            long r7 = (long) r0
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 < 0) goto Lb5
        Lb2:
            r11.pos = r1
            return r3
        Lb5:
            long r0 = r11.zzfq()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_remote_config.zzgl.zzfu():long");
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    final long zzfq() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte bZzfy = zzfy();
            j |= ((long) (bZzfy & 127)) << i;
            if ((bZzfy & 128) == 0) {
                return j;
            }
        }
        throw zzhq.zzhg();
    }

    private final int zzfv() throws IOException {
        int i = this.pos;
        if (this.limit - i < 4) {
            throw zzhq.zzhe();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private final long zzfw() throws IOException {
        int i = this.pos;
        if (this.limit - i < 8) {
            throw zzhq.zzhe();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final int zzaa(int i) throws zzhq {
        if (i < 0) {
            throw zzhq.zzhf();
        }
        int iZzfs = i + zzfs();
        int i2 = this.zzpm;
        if (iZzfs > i2) {
            throw zzhq.zzhe();
        }
        this.zzpm = iZzfs;
        zzfx();
        return i2;
    }

    private final void zzfx() {
        this.limit += this.zzpj;
        int i = this.limit;
        int i2 = i - this.zzpk;
        int i3 = this.zzpm;
        if (i2 > i3) {
            this.zzpj = i2 - i3;
            this.limit = i - this.zzpj;
        } else {
            this.zzpj = 0;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final void zzab(int i) {
        this.zzpm = i;
        zzfx();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final boolean zzfr() throws IOException {
        return this.pos == this.limit;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgj
    public final int zzfs() {
        return this.pos - this.zzpk;
    }

    private final byte zzfy() throws IOException {
        int i = this.pos;
        if (i == this.limit) {
            throw zzhq.zzhe();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 1;
        return bArr[i];
    }

    private final void zzad(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.limit;
            int i3 = this.pos;
            if (i <= i2 - i3) {
                this.pos = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzhq.zzhf();
        }
        throw zzhq.zzhe();
    }
}
