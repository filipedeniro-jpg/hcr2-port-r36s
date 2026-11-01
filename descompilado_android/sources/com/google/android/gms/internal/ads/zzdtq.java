package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.gms.games.Notifications;
import com.tonyodev.fetch.FetchService;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdtq {
    private final ByteBuffer zzakh;
    private zzdpm zzhqi;
    private int zzhqj;

    private zzdtq(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    public static int zzgm(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    private zzdtq(ByteBuffer byteBuffer) {
        this.zzakh = byteBuffer;
        this.zzakh.order(ByteOrder.LITTLE_ENDIAN);
    }

    public static zzdtq zzae(byte[] bArr) {
        return zzp(bArr, 0, bArr.length);
    }

    public static zzdtq zzp(byte[] bArr, int i, int i2) {
        return new zzdtq(bArr, 0, i2);
    }

    public final void zzaa(int i, int i2) throws IOException {
        zzz(i, 0);
        if (i2 >= 0) {
            zzhf(i2);
        } else {
            zzfm(i2);
        }
    }

    public final void zzg(int i, String str) throws IOException {
        zzz(i, 2);
        try {
            int iZzgm = zzgm(str.length());
            if (iZzgm == zzgm(str.length() * 3)) {
                int iPosition = this.zzakh.position();
                if (this.zzakh.remaining() < iZzgm) {
                    throw new zzdtt(iPosition + iZzgm, this.zzakh.limit());
                }
                this.zzakh.position(iPosition + iZzgm);
                zzd(str, this.zzakh);
                int iPosition2 = this.zzakh.position();
                this.zzakh.position(iPosition);
                zzhf((iPosition2 - iPosition) - iZzgm);
                this.zzakh.position(iPosition2);
                return;
            }
            zzhf(zza(str));
            zzd(str, this.zzakh);
        } catch (BufferOverflowException e) {
            zzdtt zzdttVar = new zzdtt(this.zzakh.position(), this.zzakh.limit());
            zzdttVar.initCause(e);
            throw zzdttVar;
        }
    }

    public final void zza(int i, zzdtz zzdtzVar) throws IOException {
        zzz(i, 2);
        if (zzdtzVar.zzhqt < 0) {
            zzdtzVar.zzazp();
        }
        zzhf(zzdtzVar.zzhqt);
        zzdtzVar.zza(this);
    }

    public final void zze(int i, zzdrn zzdrnVar) throws IOException {
        if (this.zzhqi == null) {
            this.zzhqi = zzdpm.zzm(this.zzakh);
            this.zzhqj = this.zzakh.position();
        } else if (this.zzhqj != this.zzakh.position()) {
            this.zzhqi.write(this.zzakh.array(), this.zzhqj, this.zzakh.position() - this.zzhqj);
            this.zzhqj = this.zzakh.position();
        }
        zzdpm zzdpmVar = this.zzhqi;
        zzdpmVar.zza(i, zzdrnVar);
        zzdpmVar.flush();
        this.zzhqj = this.zzakh.position();
    }

    public final void zza(int i, byte[] bArr) throws IOException {
        zzz(3, 2);
        zzhf(bArr.length);
        int length = bArr.length;
        if (this.zzakh.remaining() >= length) {
            this.zzakh.put(bArr, 0, length);
            return;
        }
        throw new zzdtt(this.zzakh.position(), this.zzakh.limit());
    }

    private static int zza(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (i2 < length) {
            char cCharAt = charSequence.charAt(i2);
            if (cCharAt >= 2048) {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char cCharAt2 = charSequence.charAt(i2);
                    if (cCharAt2 < 2048) {
                        i += (127 - cCharAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= cCharAt2 && cCharAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                StringBuilder sb = new StringBuilder(39);
                                sb.append("Unpaired surrogate at index ");
                                sb.append(i2);
                                throw new IllegalArgumentException(sb.toString());
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i3 += i;
                break;
            }
            i3 += (127 - cCharAt) >>> 31;
            i2++;
        }
        if (i3 >= length) {
            return i3;
        }
        long j = ((long) i3) + 4294967296L;
        StringBuilder sb2 = new StringBuilder(54);
        sb2.append("UTF-8 length does not fit in int: ");
        sb2.append(j);
        throw new IllegalArgumentException(sb2.toString());
    }

    private static void zzd(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int i2;
        char cCharAt;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        int i3 = 0;
        if (byteBuffer.hasArray()) {
            try {
                byte[] bArrArray = byteBuffer.array();
                int iArrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
                int iRemaining = byteBuffer.remaining();
                int length = charSequence.length();
                int i4 = iRemaining + iArrayOffset;
                while (i3 < length) {
                    int i5 = i3 + iArrayOffset;
                    if (i5 >= i4 || (cCharAt = charSequence.charAt(i3)) >= 128) {
                        break;
                    }
                    bArrArray[i5] = (byte) cCharAt;
                    i3++;
                }
                if (i3 == length) {
                    i = iArrayOffset + length;
                } else {
                    i = iArrayOffset + i3;
                    while (i3 < length) {
                        char cCharAt2 = charSequence.charAt(i3);
                        if (cCharAt2 >= 128 || i >= i4) {
                            if (cCharAt2 < 2048 && i <= i4 - 2) {
                                int i6 = i + 1;
                                bArrArray[i] = (byte) ((cCharAt2 >>> 6) | 960);
                                i = i6 + 1;
                                bArrArray[i6] = (byte) ((cCharAt2 & '?') | 128);
                            } else {
                                if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || i > i4 - 3) {
                                    if (i <= i4 - 4) {
                                        int i7 = i3 + 1;
                                        if (i7 != charSequence.length()) {
                                            char cCharAt3 = charSequence.charAt(i7);
                                            if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                                int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                                int i8 = i + 1;
                                                bArrArray[i] = (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK);
                                                int i9 = i8 + 1;
                                                bArrArray[i8] = (byte) (((codePoint >>> 12) & 63) | 128);
                                                int i10 = i9 + 1;
                                                bArrArray[i9] = (byte) (((codePoint >>> 6) & 63) | 128);
                                                i = i10 + 1;
                                                bArrArray[i10] = (byte) ((codePoint & 63) | 128);
                                                i3 = i7;
                                            } else {
                                                i3 = i7;
                                            }
                                        }
                                        StringBuilder sb = new StringBuilder(39);
                                        sb.append("Unpaired surrogate at index ");
                                        sb.append(i3 - 1);
                                        throw new IllegalArgumentException(sb.toString());
                                    }
                                    StringBuilder sb2 = new StringBuilder(37);
                                    sb2.append("Failed writing ");
                                    sb2.append(cCharAt2);
                                    sb2.append(" at index ");
                                    sb2.append(i);
                                    throw new ArrayIndexOutOfBoundsException(sb2.toString());
                                }
                                int i11 = i + 1;
                                bArrArray[i] = (byte) ((cCharAt2 >>> '\f') | FetchService.QUERY_SINGLE);
                                int i12 = i11 + 1;
                                bArrArray[i11] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                                i2 = i12 + 1;
                                bArrArray[i12] = (byte) ((cCharAt2 & '?') | 128);
                            }
                            i3++;
                        } else {
                            i2 = i + 1;
                            bArrArray[i] = (byte) cCharAt2;
                        }
                        i = i2;
                        i3++;
                    }
                }
                byteBuffer.position(i - byteBuffer.arrayOffset());
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        }
        int length2 = charSequence.length();
        while (i3 < length2) {
            char cCharAt4 = charSequence.charAt(i3);
            if (cCharAt4 < 128) {
                byteBuffer.put((byte) cCharAt4);
            } else if (cCharAt4 < 2048) {
                byteBuffer.put((byte) ((cCharAt4 >>> 6) | 960));
                byteBuffer.put((byte) ((cCharAt4 & '?') | 128));
            } else if (cCharAt4 < 55296 || 57343 < cCharAt4) {
                byteBuffer.put((byte) ((cCharAt4 >>> '\f') | FetchService.QUERY_SINGLE));
                byteBuffer.put((byte) (((cCharAt4 >>> 6) & 63) | 128));
                byteBuffer.put((byte) ((cCharAt4 & '?') | 128));
            } else {
                int i13 = i3 + 1;
                if (i13 != charSequence.length()) {
                    char cCharAt5 = charSequence.charAt(i13);
                    if (Character.isSurrogatePair(cCharAt4, cCharAt5)) {
                        int codePoint2 = Character.toCodePoint(cCharAt4, cCharAt5);
                        byteBuffer.put((byte) ((codePoint2 >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                        byteBuffer.put((byte) (((codePoint2 >>> 12) & 63) | 128));
                        byteBuffer.put((byte) (((codePoint2 >>> 6) & 63) | 128));
                        byteBuffer.put((byte) ((codePoint2 & 63) | 128));
                        i3 = i13;
                    } else {
                        i3 = i13;
                    }
                }
                StringBuilder sb3 = new StringBuilder(39);
                sb3.append("Unpaired surrogate at index ");
                sb3.append(i3 - 1);
                throw new IllegalArgumentException(sb3.toString());
            }
            i3++;
        }
    }

    public static int zzae(int i, int i2) {
        return zzge(i) + zzgf(i2);
    }

    public static int zzh(int i, String str) {
        return zzge(i) + zzhj(str);
    }

    public static int zzb(int i, zzdtz zzdtzVar) {
        int iZzge = zzge(i);
        int iZzazp = zzdtzVar.zzazp();
        return iZzge + zzgm(iZzazp) + iZzazp;
    }

    public static int zzgf(int i) {
        if (i >= 0) {
            return zzgm(i);
        }
        return 10;
    }

    public static int zzhj(String str) {
        int iZza = zza(str);
        return zzgm(iZza) + iZza;
    }

    public final void zzayq() {
        if (this.zzakh.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", Integer.valueOf(this.zzakh.remaining())));
        }
    }

    private final void zzhe(int i) throws IOException {
        byte b = (byte) i;
        if (!this.zzakh.hasRemaining()) {
            throw new zzdtt(this.zzakh.position(), this.zzakh.limit());
        }
        this.zzakh.put(b);
    }

    public final void zzz(int i, int i2) throws IOException {
        zzhf((i << 3) | i2);
    }

    public static int zzge(int i) {
        return zzgm(i << 3);
    }

    private final void zzhf(int i) throws IOException {
        while ((i & (-128)) != 0) {
            zzhe((i & Notifications.NOTIFICATION_TYPES_ALL) | 128);
            i >>>= 7;
        }
        zzhe(i);
    }

    public final void zzfm(long j) throws IOException {
        while (((-128) & j) != 0) {
            zzhe((((int) j) & Notifications.NOTIFICATION_TYPES_ALL) | 128);
            j >>>= 7;
        }
        zzhe((int) j);
    }
}
