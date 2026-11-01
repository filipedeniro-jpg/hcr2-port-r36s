package com.google.android.gms.internal.firebase_auth;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.tonyodev.fetch.FetchService;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzld extends zzlc {
    zzld() {
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzlc
    final int zza(int i, byte[] bArr, int i2, int i3) {
        int i4;
        long j;
        long j2;
        if ((i2 | i3 | (bArr.length - i3)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        long j3 = i2;
        int i5 = (int) (((long) i3) - j3);
        if (i5 >= 16) {
            long j4 = j3;
            i4 = 0;
            while (true) {
                if (i4 >= i5) {
                    i4 = i5;
                    break;
                }
                long j5 = j4 + 1;
                if (zzky.zza(bArr, j4) < 0) {
                    break;
                }
                i4++;
                j4 = j5;
            }
        } else {
            i4 = 0;
        }
        int i6 = i5 - i4;
        long j6 = j3 + ((long) i4);
        while (true) {
            byte bZza = 0;
            while (true) {
                if (i6 <= 0) {
                    j = j6;
                    break;
                }
                j = j6 + 1;
                bZza = zzky.zza(bArr, j6);
                if (bZza < 0) {
                    break;
                }
                i6--;
                j6 = j;
            }
            if (i6 == 0) {
                return 0;
            }
            int i7 = i6 - 1;
            if (bZza < -32) {
                if (i7 == 0) {
                    return bZza;
                }
                i6 = i7 - 1;
                if (bZza >= -62) {
                    j2 = j + 1;
                    if (zzky.zza(bArr, j) > -65) {
                    }
                }
                return -1;
            }
            if (bZza >= -16) {
                if (i7 < 3) {
                    return zza(bArr, bZza, j, i7);
                }
                i6 = i7 - 3;
                long j7 = j + 1;
                byte bZza2 = zzky.zza(bArr, j);
                if (bZza2 <= -65 && (((bZza << 28) + (bZza2 + 112)) >> 30) == 0) {
                    long j8 = j7 + 1;
                    if (zzky.zza(bArr, j7) <= -65) {
                        j2 = j8 + 1;
                        if (zzky.zza(bArr, j8) > -65) {
                        }
                    }
                }
                return -1;
            }
            if (i7 < 2) {
                return zza(bArr, bZza, j, i7);
            }
            i6 = i7 - 2;
            long j9 = j + 1;
            byte bZza3 = zzky.zza(bArr, j);
            if (bZza3 <= -65 && ((bZza != -32 || bZza3 >= -96) && (bZza != -19 || bZza3 < -96))) {
                long j10 = j9 + 1;
                if (zzky.zza(bArr, j9) <= -65) {
                    j6 = j10;
                }
            }
            return -1;
            j6 = j2;
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzlc
    final String zza(byte[] bArr, int i, int i2) throws zzig {
        if ((i | i2 | ((bArr.length - i) - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        int i3 = i + i2;
        char[] cArr = new char[i2];
        int i4 = 0;
        while (i < i3) {
            byte bZza = zzky.zza(bArr, i);
            if (!zzkz.zzd(bZza)) {
                break;
            }
            i++;
            zzkz.zzb(bZza, cArr, i4);
            i4++;
        }
        int i5 = i4;
        while (i < i3) {
            int i6 = i + 1;
            byte bZza2 = zzky.zza(bArr, i);
            if (zzkz.zzd(bZza2)) {
                int i7 = i5 + 1;
                zzkz.zzb(bZza2, cArr, i5);
                while (i6 < i3) {
                    byte bZza3 = zzky.zza(bArr, i6);
                    if (!zzkz.zzd(bZza3)) {
                        break;
                    }
                    i6++;
                    zzkz.zzb(bZza3, cArr, i7);
                    i7++;
                }
                i = i6;
                i5 = i7;
            } else if (zzkz.zze(bZza2)) {
                if (i6 < i3) {
                    zzkz.zzb(bZza2, zzky.zza(bArr, i6), cArr, i5);
                    i = i6 + 1;
                    i5++;
                } else {
                    throw zzig.zzi();
                }
            } else if (zzkz.zzf(bZza2)) {
                if (i6 < i3 - 1) {
                    int i8 = i6 + 1;
                    zzkz.zzb(bZza2, zzky.zza(bArr, i6), zzky.zza(bArr, i8), cArr, i5);
                    i = i8 + 1;
                    i5++;
                } else {
                    throw zzig.zzi();
                }
            } else {
                if (i6 >= i3 - 2) {
                    throw zzig.zzi();
                }
                int i9 = i6 + 1;
                byte bZza4 = zzky.zza(bArr, i6);
                int i10 = i9 + 1;
                zzkz.zzb(bZza2, bZza4, zzky.zza(bArr, i9), zzky.zza(bArr, i10), cArr, i5);
                i = i10 + 1;
                i5 = i5 + 1 + 1;
            }
        }
        return new String(cArr, 0, i5);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzlc
    final int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        char c;
        long j;
        long j2;
        long j3;
        int i3;
        char cCharAt;
        long j4 = i;
        long j5 = ((long) i2) + j4;
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            char cCharAt2 = charSequence.charAt(length - 1);
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(cCharAt2);
            sb.append(" at index ");
            sb.append(i + i2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i4 = 0;
        while (true) {
            c = 128;
            j = 1;
            if (i4 >= length || (cCharAt = charSequence.charAt(i4)) >= 128) {
                break;
            }
            zzky.zza(bArr, j4, (byte) cCharAt);
            i4++;
            j4 = 1 + j4;
        }
        if (i4 == length) {
            return (int) j4;
        }
        while (i4 < length) {
            char cCharAt3 = charSequence.charAt(i4);
            if (cCharAt3 >= c || j4 >= j5) {
                if (cCharAt3 < 2048 && j4 <= j5 - 2) {
                    long j6 = j4 + j;
                    zzky.zza(bArr, j4, (byte) ((cCharAt3 >>> 6) | 960));
                    zzky.zza(bArr, j6, (byte) ((cCharAt3 & '?') | 128));
                    j2 = j6 + j;
                    j3 = j;
                } else {
                    if ((cCharAt3 >= 55296 && 57343 >= cCharAt3) || j4 > j5 - 3) {
                        if (j4 <= j5 - 4) {
                            int i5 = i4 + 1;
                            if (i5 != length) {
                                char cCharAt4 = charSequence.charAt(i5);
                                if (Character.isSurrogatePair(cCharAt3, cCharAt4)) {
                                    int codePoint = Character.toCodePoint(cCharAt3, cCharAt4);
                                    long j7 = j4 + 1;
                                    zzky.zza(bArr, j4, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                    long j8 = j7 + 1;
                                    zzky.zza(bArr, j7, (byte) (((codePoint >>> 12) & 63) | 128));
                                    long j9 = j8 + 1;
                                    zzky.zza(bArr, j8, (byte) (((codePoint >>> 6) & 63) | 128));
                                    j3 = 1;
                                    j2 = j9 + 1;
                                    zzky.zza(bArr, j9, (byte) ((codePoint & 63) | 128));
                                    i4 = i5;
                                } else {
                                    i4 = i5;
                                }
                            }
                            throw new zzle(i4 - 1, length);
                        }
                        if (55296 <= cCharAt3 && cCharAt3 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(cCharAt3, charSequence.charAt(i3)))) {
                            throw new zzle(i4, length);
                        }
                        StringBuilder sb2 = new StringBuilder(46);
                        sb2.append("Failed writing ");
                        sb2.append(cCharAt3);
                        sb2.append(" at index ");
                        sb2.append(j4);
                        throw new ArrayIndexOutOfBoundsException(sb2.toString());
                    }
                    long j10 = j4 + j;
                    zzky.zza(bArr, j4, (byte) ((cCharAt3 >>> '\f') | FetchService.QUERY_SINGLE));
                    long j11 = j10 + j;
                    zzky.zza(bArr, j10, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                    zzky.zza(bArr, j11, (byte) ((cCharAt3 & '?') | 128));
                    j2 = j11 + 1;
                    j3 = 1;
                }
                i4++;
                c = 128;
                long j12 = j3;
                j4 = j2;
                j = j12;
            } else {
                long j13 = j4 + j;
                zzky.zza(bArr, j4, (byte) cCharAt3);
                j3 = j;
                j2 = j13;
            }
            i4++;
            c = 128;
            long j14 = j3;
            j4 = j2;
            j = j14;
        }
        return (int) j4;
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        if (i2 == 0) {
            return zzla.zzb(i);
        }
        if (i2 == 1) {
            return zzla.zzb(i, zzky.zza(bArr, j));
        }
        if (i2 == 2) {
            return zzla.zzb(i, zzky.zza(bArr, j), zzky.zza(bArr, j + 1));
        }
        throw new AssertionError();
    }
}
