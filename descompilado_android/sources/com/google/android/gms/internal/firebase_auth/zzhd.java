package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhd extends zzgy {
    private final InputStream zze;
    private final byte[] zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private zzhc zzm;

    private zzhd(InputStream inputStream, int i) {
        super();
        this.zzl = Integer.MAX_VALUE;
        this.zzm = null;
        zzib.zza(inputStream, "input");
        this.zze = inputStream;
        this.zzf = new byte[i];
        this.zzg = 0;
        this.zzi = 0;
        this.zzk = 0;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final int zza() throws IOException {
        if (zzt()) {
            this.zzj = 0;
            return 0;
        }
        this.zzj = zzv();
        int i = this.zzj;
        if ((i >>> 3) != 0) {
            return i;
        }
        throw zzig.zzd();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final void zza(int i) throws zzig {
        if (this.zzj != i) {
            throw zzig.zze();
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final boolean zzb(int i) throws IOException {
        int iZza;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.zzg - this.zzi >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.zzf;
                    int i4 = this.zzi;
                    this.zzi = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzig.zzc();
            }
            while (i3 < 10) {
                if (zzaa() < 0) {
                    i3++;
                }
            }
            throw zzig.zzc();
            return true;
        }
        if (i2 == 1) {
            zzj(8);
            return true;
        }
        if (i2 == 2) {
            zzj(zzv());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 == 5) {
                zzj(4);
                return true;
            }
            throw zzig.zzf();
        }
        do {
            iZza = zza();
            if (iZza == 0) {
                break;
            }
        } while (zzb(iZza));
        zza(((i >>> 3) << 3) | 4);
        return true;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final double zzb() throws IOException {
        return Double.longBitsToDouble(zzy());
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final float zzc() throws IOException {
        return Float.intBitsToFloat(zzx());
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final long zzd() throws IOException {
        return zzw();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final long zze() throws IOException {
        return zzw();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final int zzf() throws IOException {
        return zzv();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final long zzg() throws IOException {
        return zzy();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final int zzh() throws IOException {
        return zzx();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final boolean zzi() throws IOException {
        return zzw() != 0;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final String zzj() throws IOException {
        int iZzv = zzv();
        if (iZzv > 0) {
            int i = this.zzg;
            int i2 = this.zzi;
            if (iZzv <= i - i2) {
                String str = new String(this.zzf, i2, iZzv, zzib.zza);
                this.zzi += iZzv;
                return str;
            }
        }
        if (iZzv == 0) {
            return "";
        }
        if (iZzv <= this.zzg) {
            zzf(iZzv);
            String str2 = new String(this.zzf, this.zzi, iZzv, zzib.zza);
            this.zzi += iZzv;
            return str2;
        }
        return new String(zza(iZzv, false), zzib.zza);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final String zzk() throws IOException {
        byte[] bArrZza;
        int iZzv = zzv();
        int i = this.zzi;
        int i2 = 0;
        if (iZzv <= this.zzg - i && iZzv > 0) {
            bArrZza = this.zzf;
            this.zzi = i + iZzv;
            i2 = i;
        } else {
            if (iZzv == 0) {
                return "";
            }
            if (iZzv <= this.zzg) {
                zzf(iZzv);
                bArrZza = this.zzf;
                this.zzi = iZzv;
            } else {
                bArrZza = zza(iZzv, false);
            }
        }
        return zzla.zzb(bArrZza, i2, iZzv);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final zzgm zzl() throws IOException {
        int iZzv = zzv();
        int i = this.zzg;
        int i2 = this.zzi;
        if (iZzv <= i - i2 && iZzv > 0) {
            zzgm zzgmVarZza = zzgm.zza(this.zzf, i2, iZzv);
            this.zzi += iZzv;
            return zzgmVarZza;
        }
        if (iZzv == 0) {
            return zzgm.zza;
        }
        byte[] bArrZzh = zzh(iZzv);
        if (bArrZzh != null) {
            return zzgm.zza(bArrZzh);
        }
        int i3 = this.zzi;
        int i4 = this.zzg;
        int length = i4 - i3;
        this.zzk += i4;
        this.zzi = 0;
        this.zzg = 0;
        List<byte[]> listZzi = zzi(iZzv - length);
        byte[] bArr = new byte[iZzv];
        System.arraycopy(this.zzf, i3, bArr, 0, length);
        for (byte[] bArr2 : listZzi) {
            System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
            length += bArr2.length;
        }
        return zzgm.zzb(bArr);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final int zzm() throws IOException {
        return zzv();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final int zzn() throws IOException {
        return zzv();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final int zzo() throws IOException {
        return zzx();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final long zzp() throws IOException {
        return zzy();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final int zzq() throws IOException {
        return zze(zzv());
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final long zzr() throws IOException {
        return zza(zzw());
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0066, code lost:
    
        if (r2[r3] >= 0) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzv() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzi
            int r1 = r5.zzg
            if (r1 == r0) goto L6b
            byte[] r2 = r5.zzf
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L11
            r5.zzi = r3
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
            r5.zzi = r1
            return r0
        L6b:
            long r0 = r5.zzs()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzhd.zzv():int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b0, code lost:
    
        if (r2[r0] >= 0) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final long zzw() throws java.io.IOException {
        /*
            r11 = this;
            int r0 = r11.zzi
            int r1 = r11.zzg
            if (r1 == r0) goto Lb5
            byte[] r2 = r11.zzf
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L12
            r11.zzi = r3
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
            r11.zzi = r1
            return r3
        Lb5:
            long r0 = r11.zzs()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzhd.zzw():long");
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    final long zzs() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte bZzaa = zzaa();
            j |= ((long) (bZzaa & 127)) << i;
            if ((bZzaa & 128) == 0) {
                return j;
            }
        }
        throw zzig.zzc();
    }

    private final int zzx() throws IOException {
        int i = this.zzi;
        if (this.zzg - i < 4) {
            zzf(4);
            i = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private final long zzy() throws IOException {
        int i = this.zzi;
        if (this.zzg - i < 8) {
            zzf(8);
            i = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final int zzc(int i) throws zzig {
        if (i < 0) {
            throw zzig.zzb();
        }
        int i2 = i + this.zzk + this.zzi;
        int i3 = this.zzl;
        if (i2 > i3) {
            throw zzig.zza();
        }
        this.zzl = i2;
        zzz();
        return i3;
    }

    private final void zzz() {
        this.zzg += this.zzh;
        int i = this.zzk;
        int i2 = this.zzg;
        int i3 = i + i2;
        int i4 = this.zzl;
        if (i3 > i4) {
            this.zzh = i3 - i4;
            this.zzg = i2 - this.zzh;
        } else {
            this.zzh = 0;
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final void zzd(int i) {
        this.zzl = i;
        zzz();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final boolean zzt() throws IOException {
        return this.zzi == this.zzg && !zzg(1);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgy
    public final int zzu() {
        return this.zzk + this.zzi;
    }

    private final void zzf(int i) throws IOException {
        if (zzg(i)) {
            return;
        }
        if (i > (this.zzc - this.zzk) - this.zzi) {
            throw zzig.zzg();
        }
        throw zzig.zza();
    }

    private final boolean zzg(int i) throws IOException {
        while (this.zzi + i > this.zzg) {
            int i2 = this.zzc;
            int i3 = this.zzk;
            int i4 = this.zzi;
            if (i > (i2 - i3) - i4 || i3 + i4 + i > this.zzl) {
                return false;
            }
            if (i4 > 0) {
                int i5 = this.zzg;
                if (i5 > i4) {
                    byte[] bArr = this.zzf;
                    System.arraycopy(bArr, i4, bArr, 0, i5 - i4);
                }
                this.zzk += i4;
                this.zzg -= i4;
                this.zzi = 0;
            }
            InputStream inputStream = this.zze;
            byte[] bArr2 = this.zzf;
            int i6 = this.zzg;
            int i7 = inputStream.read(bArr2, i6, Math.min(bArr2.length - i6, (this.zzc - this.zzk) - this.zzg));
            if (i7 == 0 || i7 < -1 || i7 > this.zzf.length) {
                String strValueOf = String.valueOf(this.zze.getClass());
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 91);
                sb.append(strValueOf);
                sb.append("#read(byte[]) returned invalid result: ");
                sb.append(i7);
                sb.append("\nThe InputStream implementation is buggy.");
                throw new IllegalStateException(sb.toString());
            }
            if (i7 <= 0) {
                return false;
            }
            this.zzg += i7;
            zzz();
            if (this.zzg >= i) {
                return true;
            }
        }
        StringBuilder sb2 = new StringBuilder(77);
        sb2.append("refillBuffer() called when ");
        sb2.append(i);
        sb2.append(" bytes were already available in buffer");
        throw new IllegalStateException(sb2.toString());
    }

    private final byte zzaa() throws IOException {
        if (this.zzi == this.zzg) {
            zzf(1);
        }
        byte[] bArr = this.zzf;
        int i = this.zzi;
        this.zzi = i + 1;
        return bArr[i];
    }

    private final byte[] zza(int i, boolean z) throws IOException {
        byte[] bArrZzh = zzh(i);
        if (bArrZzh != null) {
            return bArrZzh;
        }
        int i2 = this.zzi;
        int i3 = this.zzg;
        int length = i3 - i2;
        this.zzk += i3;
        this.zzi = 0;
        this.zzg = 0;
        List<byte[]> listZzi = zzi(i - length);
        byte[] bArr = new byte[i];
        System.arraycopy(this.zzf, i2, bArr, 0, length);
        for (byte[] bArr2 : listZzi) {
            System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
            length += bArr2.length;
        }
        return bArr;
    }

    private final byte[] zzh(int i) throws IOException {
        if (i == 0) {
            return zzib.zzb;
        }
        if (i < 0) {
            throw zzig.zzb();
        }
        int i2 = this.zzk + this.zzi + i;
        if (i2 - this.zzc > 0) {
            throw zzig.zzg();
        }
        int i3 = this.zzl;
        if (i2 > i3) {
            zzj((i3 - this.zzk) - this.zzi);
            throw zzig.zza();
        }
        int i4 = this.zzg - this.zzi;
        int i5 = i - i4;
        if (i5 >= 4096 && i5 > this.zze.available()) {
            return null;
        }
        byte[] bArr = new byte[i];
        System.arraycopy(this.zzf, this.zzi, bArr, 0, i4);
        this.zzk += this.zzg;
        this.zzi = 0;
        this.zzg = 0;
        while (i4 < bArr.length) {
            int i6 = this.zze.read(bArr, i4, i - i4);
            if (i6 == -1) {
                throw zzig.zza();
            }
            this.zzk += i6;
            i4 += i6;
        }
        return bArr;
    }

    private final List<byte[]> zzi(int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (i > 0) {
            byte[] bArr = new byte[Math.min(i, 4096)];
            int i2 = 0;
            while (i2 < bArr.length) {
                int i3 = this.zze.read(bArr, i2, bArr.length - i2);
                if (i3 == -1) {
                    throw zzig.zza();
                }
                this.zzk += i3;
                i2 += i3;
            }
            i -= bArr.length;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    private final void zzj(int i) throws IOException {
        int i2 = this.zzg;
        int i3 = this.zzi;
        if (i <= i2 - i3 && i >= 0) {
            this.zzi = i3 + i;
            return;
        }
        if (i < 0) {
            throw zzig.zzb();
        }
        int i4 = this.zzk;
        int i5 = this.zzi;
        int i6 = i4 + i5 + i;
        int i7 = this.zzl;
        if (i6 > i7) {
            zzj((i7 - i4) - i5);
            throw zzig.zza();
        }
        this.zzk = i4 + i5;
        int i8 = this.zzg - i5;
        this.zzg = 0;
        this.zzi = 0;
        while (i8 < i) {
            try {
                long j = i - i8;
                long jSkip = this.zze.skip(j);
                if (jSkip >= 0 && jSkip <= j) {
                    if (jSkip == 0) {
                        break;
                    } else {
                        i8 += (int) jSkip;
                    }
                } else {
                    String strValueOf = String.valueOf(this.zze.getClass());
                    StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 92);
                    sb.append(strValueOf);
                    sb.append("#skip returned invalid result: ");
                    sb.append(jSkip);
                    sb.append("\nThe InputStream implementation is buggy.");
                    throw new IllegalStateException(sb.toString());
                }
            } catch (Throwable th) {
                this.zzk += i8;
                zzz();
                throw th;
            }
        }
        this.zzk += i8;
        zzz();
        if (i8 >= i) {
            return;
        }
        int i9 = this.zzg;
        int i10 = i9 - this.zzi;
        this.zzi = i9;
        zzf(1);
        while (true) {
            int i11 = i - i10;
            int i12 = this.zzg;
            if (i11 > i12) {
                i10 += i12;
                this.zzi = i12;
                zzf(1);
            } else {
                this.zzi = i11;
                return;
            }
        }
    }
}
