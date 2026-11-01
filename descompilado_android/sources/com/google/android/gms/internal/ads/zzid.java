package com.google.android.gms.internal.ads;

import com.google.android.gms.games.GamesStatusCodes;
import java.nio.ShortBuffer;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzid {
    private final int zzafm;
    private float zzafy;
    private float zzafz;
    private final int zzaku;
    private final int zzakv;
    private final int zzakw;
    private final int zzakx;
    private final short[] zzaky;
    private int zzakz;
    private short[] zzala;
    private int zzalb;
    private short[] zzalc;
    private int zzald;
    private short[] zzale;
    private int zzalf;
    private int zzalg;
    private int zzalh;
    private int zzali;
    private int zzalj;
    private int zzalk;
    private int zzall;
    private int zzalm;
    private int zzaln;
    private int zzalo;

    public zzid(int i, int i2) {
        this.zzafm = i;
        this.zzaku = i2;
        this.zzakv = i / 400;
        this.zzakw = i / 65;
        this.zzakx = this.zzakw * 2;
        int i3 = this.zzakx;
        this.zzaky = new short[i3];
        this.zzakz = i3;
        this.zzala = new short[i3 * i2];
        this.zzalb = i3;
        this.zzalc = new short[i3 * i2];
        this.zzald = i3;
        this.zzale = new short[i3 * i2];
        this.zzalf = 0;
        this.zzalg = 0;
        this.zzall = 0;
        this.zzafy = 1.0f;
        this.zzafz = 1.0f;
    }

    public final void setSpeed(float f) {
        this.zzafy = f;
    }

    public final void zza(float f) {
        this.zzafz = f;
    }

    public final void zza(ShortBuffer shortBuffer) {
        int iRemaining = shortBuffer.remaining();
        int i = this.zzaku;
        int i2 = iRemaining / i;
        zzu(i2);
        shortBuffer.get(this.zzala, this.zzalh * this.zzaku, ((i * i2) << 1) / 2);
        this.zzalh += i2;
        zzfn();
    }

    public final void zzb(ShortBuffer shortBuffer) {
        int iMin = Math.min(shortBuffer.remaining() / this.zzaku, this.zzali);
        shortBuffer.put(this.zzalc, 0, this.zzaku * iMin);
        this.zzali -= iMin;
        short[] sArr = this.zzalc;
        int i = this.zzaku;
        System.arraycopy(sArr, iMin * i, sArr, 0, this.zzali * i);
    }

    public final void zzes() {
        int i;
        int i2 = this.zzalh;
        float f = this.zzafy;
        float f2 = this.zzafz;
        int i3 = this.zzali + ((int) ((((i2 / (f / f2)) + this.zzalj) / f2) + 0.5f));
        zzu((this.zzakx * 2) + i2);
        int i4 = 0;
        while (true) {
            i = this.zzakx;
            int i5 = this.zzaku;
            if (i4 >= i * 2 * i5) {
                break;
            }
            this.zzala[(i5 * i2) + i4] = 0;
            i4++;
        }
        this.zzalh += i * 2;
        zzfn();
        if (this.zzali > i3) {
            this.zzali = i3;
        }
        this.zzalh = 0;
        this.zzalk = 0;
        this.zzalj = 0;
    }

    public final int zzfm() {
        return this.zzali;
    }

    private final void zzt(int i) {
        int i2 = this.zzali + i;
        int i3 = this.zzalb;
        if (i2 > i3) {
            this.zzalb = i3 + (i3 / 2) + i;
            this.zzalc = Arrays.copyOf(this.zzalc, this.zzalb * this.zzaku);
        }
    }

    private final void zzu(int i) {
        int i2 = this.zzalh + i;
        int i3 = this.zzakz;
        if (i2 > i3) {
            this.zzakz = i3 + (i3 / 2) + i;
            this.zzala = Arrays.copyOf(this.zzala, this.zzakz * this.zzaku);
        }
    }

    private final void zza(short[] sArr, int i, int i2) {
        zzt(i2);
        int i3 = this.zzaku;
        System.arraycopy(sArr, i * i3, this.zzalc, this.zzali * i3, i3 * i2);
        this.zzali += i2;
    }

    private final void zzb(short[] sArr, int i, int i2) {
        int i3 = this.zzakx / i2;
        int i4 = this.zzaku;
        int i5 = i2 * i4;
        int i6 = i * i4;
        for (int i7 = 0; i7 < i3; i7++) {
            int i8 = 0;
            for (int i9 = 0; i9 < i5; i9++) {
                i8 += sArr[(i7 * i5) + i6 + i9];
            }
            this.zzaky[i7] = (short) (i8 / i5);
        }
    }

    private final int zza(short[] sArr, int i, int i2, int i3) {
        int i4 = i * this.zzaku;
        int i5 = 1;
        int i6 = 0;
        int i7 = 0;
        int i8 = 255;
        while (i2 <= i3) {
            int i9 = 0;
            for (int i10 = 0; i10 < i2; i10++) {
                short s = sArr[i4 + i10];
                short s2 = sArr[i4 + i2 + i10];
                i9 += s >= s2 ? s - s2 : s2 - s;
            }
            if (i9 * i6 < i5 * i2) {
                i6 = i2;
                i5 = i9;
            }
            if (i9 * i8 > i7 * i2) {
                i8 = i2;
                i7 = i9;
            }
            i2++;
        }
        this.zzaln = i5 / i6;
        this.zzalo = i7 / i8;
        return i6;
    }

    private final void zzfn() {
        int iZza;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = this.zzali;
        float f = this.zzafy / this.zzafz;
        double d = f;
        int i7 = 1;
        if (d > 1.00001d || d < 0.99999d) {
            int i8 = this.zzalh;
            if (i8 >= this.zzakx) {
                int i9 = 0;
                while (true) {
                    int i10 = this.zzalk;
                    if (i10 > 0) {
                        int iMin = Math.min(this.zzakx, i10);
                        zza(this.zzala, i9, iMin);
                        this.zzalk -= iMin;
                        i9 += iMin;
                    } else {
                        short[] sArr = this.zzala;
                        int i11 = this.zzafm;
                        int i12 = i11 > 4000 ? i11 / GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND : 1;
                        if (this.zzaku == i7 && i12 == i7) {
                            iZza = zza(sArr, i9, this.zzakv, this.zzakw);
                        } else {
                            zzb(sArr, i9, i12);
                            int iZza2 = zza(this.zzaky, 0, this.zzakv / i12, this.zzakw / i12);
                            if (i12 != i7) {
                                int i13 = iZza2 * i12;
                                int i14 = i12 << 2;
                                int i15 = i13 - i14;
                                int i16 = i13 + i14;
                                int i17 = this.zzakv;
                                if (i15 >= i17) {
                                    i17 = i15;
                                }
                                int i18 = this.zzakw;
                                if (i16 > i18) {
                                    i16 = i18;
                                }
                                if (this.zzaku == i7) {
                                    iZza = zza(sArr, i9, i17, i16);
                                } else {
                                    zzb(sArr, i9, i7);
                                    iZza = zza(this.zzaky, 0, i17, i16);
                                }
                            } else {
                                iZza = iZza2;
                            }
                        }
                        int i19 = this.zzaln;
                        int i20 = i19 != 0 && this.zzall != 0 && this.zzalo <= i19 * 3 && (i19 << 1) > this.zzalm * 3 ? this.zzall : iZza;
                        this.zzalm = this.zzaln;
                        this.zzall = iZza;
                        if (d > 1.0d) {
                            short[] sArr2 = this.zzala;
                            if (f >= 2.0f) {
                                i2 = (int) (i20 / (f - 1.0f));
                            } else {
                                this.zzalk = (int) ((i20 * (2.0f - f)) / (f - 1.0f));
                                i2 = i20;
                            }
                            zzt(i2);
                            int i21 = i2;
                            zza(i2, this.zzaku, this.zzalc, this.zzali, sArr2, i9, sArr2, i9 + i20);
                            this.zzali += i21;
                            i9 += i20 + i21;
                        } else {
                            int i22 = i20;
                            short[] sArr3 = this.zzala;
                            if (f < 0.5f) {
                                i = (int) ((i22 * f) / (1.0f - f));
                            } else {
                                this.zzalk = (int) ((i22 * ((2.0f * f) - 1.0f)) / (1.0f - f));
                                i = i22;
                            }
                            int i23 = i22 + i;
                            zzt(i23);
                            int i24 = this.zzaku;
                            System.arraycopy(sArr3, i9 * i24, this.zzalc, this.zzali * i24, i24 * i22);
                            zza(i, this.zzaku, this.zzalc, this.zzali + i22, sArr3, i22 + i9, sArr3, i9);
                            this.zzali += i23;
                            i9 += i;
                        }
                    }
                    if (this.zzakx + i9 > i8) {
                        break;
                    } else {
                        i7 = 1;
                    }
                }
                int i25 = this.zzalh - i9;
                short[] sArr4 = this.zzala;
                int i26 = this.zzaku;
                System.arraycopy(sArr4, i9 * i26, sArr4, 0, i26 * i25);
                this.zzalh = i25;
            }
        } else {
            zza(this.zzala, 0, this.zzalh);
            this.zzalh = 0;
        }
        float f2 = this.zzafz;
        if (f2 == 1.0f || this.zzali == i6) {
            return;
        }
        int i27 = this.zzafm;
        int i28 = (int) (i27 / f2);
        while (true) {
            if (i28 <= 16384 && i27 <= 16384) {
                break;
            }
            i28 /= 2;
            i27 /= 2;
        }
        int i29 = this.zzali - i6;
        int i30 = this.zzalj + i29;
        int i31 = this.zzald;
        if (i30 > i31) {
            this.zzald = i31 + (i31 / 2) + i29;
            this.zzale = Arrays.copyOf(this.zzale, this.zzald * this.zzaku);
        }
        short[] sArr5 = this.zzalc;
        int i32 = this.zzaku;
        System.arraycopy(sArr5, i6 * i32, this.zzale, this.zzalj * i32, i32 * i29);
        this.zzali = i6;
        this.zzalj += i29;
        int i33 = 0;
        while (true) {
            i3 = this.zzalj;
            if (i33 >= i3 - 1) {
                break;
            }
            while (true) {
                i4 = this.zzalf;
                int i34 = (i4 + 1) * i28;
                i5 = this.zzalg;
                if (i34 <= i5 * i27) {
                    break;
                }
                zzt(1);
                int i35 = 0;
                while (true) {
                    int i36 = this.zzaku;
                    if (i35 < i36) {
                        short[] sArr6 = this.zzalc;
                        int i37 = (this.zzali * i36) + i35;
                        short[] sArr7 = this.zzale;
                        int i38 = (i33 * i36) + i35;
                        short s = sArr7[i38];
                        short s2 = sArr7[i38 + i36];
                        int i39 = this.zzalg * i27;
                        int i40 = this.zzalf;
                        int i41 = i40 * i28;
                        int i42 = (i40 + 1) * i28;
                        int i43 = i42 - i39;
                        int i44 = i42 - i41;
                        sArr6[i37] = (short) (((s * i43) + ((i44 - i43) * s2)) / i44);
                        i35++;
                    }
                }
                this.zzalg++;
                this.zzali++;
            }
            this.zzalf = i4 + 1;
            if (this.zzalf == i27) {
                this.zzalf = 0;
                zznt.checkState(i5 == i28);
                this.zzalg = 0;
            }
            i33++;
        }
        int i45 = i3 - 1;
        if (i45 != 0) {
            short[] sArr8 = this.zzale;
            int i46 = this.zzaku;
            System.arraycopy(sArr8, i45 * i46, sArr8, 0, (i3 - i45) * i46);
            this.zzalj -= i45;
        }
    }

    private static void zza(int i, int i2, short[] sArr, int i3, short[] sArr2, int i4, short[] sArr3, int i5) {
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = (i4 * i2) + i6;
            int i8 = (i5 * i2) + i6;
            int i9 = (i3 * i2) + i6;
            for (int i10 = 0; i10 < i; i10++) {
                sArr[i9] = (short) (((sArr2[i7] * (i - i10)) + (sArr3[i8] * i10)) / i);
                i9 += i2;
                i7 += i2;
                i8 += i2;
            }
        }
    }
}
