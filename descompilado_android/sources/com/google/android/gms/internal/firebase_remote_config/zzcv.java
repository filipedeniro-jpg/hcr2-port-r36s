package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzcv extends zzcr {
    protected abstract int zza(CharSequence charSequence, int i, int i2);

    protected abstract char[] zzj(int i);

    protected final String zza(String str, int i) {
        int i2;
        int codePoint;
        int length = str.length();
        char[] cArrZzcd = zzct.zzcd();
        int i3 = 0;
        int length2 = 0;
        while (i < length) {
            if (i >= length) {
                throw new IndexOutOfBoundsException("Index exceeds specified range");
            }
            int i4 = i + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt >= 55296 && cCharAt <= 57343) {
                if (cCharAt > 56319) {
                    codePoint = cCharAt;
                    codePoint = cCharAt;
                    StringBuilder sb = new StringBuilder(82);
                    sb.append("Unexpected low surrogate character '");
                    sb.append(cCharAt);
                    sb.append("' with value ");
                    sb.append((int) cCharAt);
                    sb.append(" at index ");
                    sb.append(i4 - 1);
                    throw new IllegalArgumentException(sb.toString());
                }
                if (i4 == length) {
                    codePoint = -cCharAt;
                } else {
                    char cCharAt2 = str.charAt(i4);
                    if (!Character.isLowSurrogate(cCharAt2)) {
                        StringBuilder sb2 = new StringBuilder(83);
                        sb2.append("Expected low surrogate but got char '");
                        sb2.append(cCharAt2);
                        sb2.append("' with value ");
                        sb2.append((int) cCharAt2);
                        sb2.append(" at index ");
                        sb2.append(i4);
                        throw new IllegalArgumentException(sb2.toString());
                    }
                    codePoint = Character.toCodePoint(cCharAt, cCharAt2);
                }
            }
            if (codePoint < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            char[] cArrZzj = zzj(codePoint);
            int i5 = (Character.isSupplementaryCodePoint(codePoint) ? 2 : 1) + i;
            if (cArrZzj != null) {
                int i6 = i - i3;
                int i7 = length2 + i6;
                int length3 = cArrZzj.length + i7;
                if (cArrZzcd.length < length3) {
                    cArrZzcd = zza(cArrZzcd, length2, ((length3 + length) - i) + 32);
                }
                if (i6 > 0) {
                    str.getChars(i3, i, cArrZzcd, length2);
                    length2 = i7;
                }
                if (cArrZzj.length > 0) {
                    System.arraycopy(cArrZzj, 0, cArrZzcd, length2, cArrZzj.length);
                    length2 += cArrZzj.length;
                }
                i3 = i5;
            }
            i = zza(str, i5, length);
        }
        int i8 = length - i3;
        if (i8 > 0) {
            i2 = i8 + length2;
            if (cArrZzcd.length < i2) {
                cArrZzcd = zza(cArrZzcd, length2, i2);
            }
            str.getChars(i3, length, cArrZzcd, length2);
        } else {
            i2 = length2;
        }
        return new String(cArrZzcd, 0, i2);
    }

    private static char[] zza(char[] cArr, int i, int i2) {
        char[] cArr2 = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, i);
        }
        return cArr2;
    }
}
