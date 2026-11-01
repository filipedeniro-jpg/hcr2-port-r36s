package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
public final class zzcu extends zzcv {
    private static final char[] zzgp = {'+'};
    private static final char[] zzgq = "0123456789ABCDEF".toCharArray();
    private final boolean zzgr;
    private final boolean[] zzgs;

    public zzcu(String str, boolean z) {
        if (str.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        }
        if (z && str.contains(" ")) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        if (str.contains("%")) {
            throw new IllegalArgumentException("The '%' character cannot be specified as 'safe'");
        }
        this.zzgr = z;
        char[] charArray = str.toCharArray();
        int iMax = 122;
        for (char c : charArray) {
            iMax = Math.max((int) c, iMax);
        }
        boolean[] zArr = new boolean[iMax + 1];
        for (int i = 48; i <= 57; i++) {
            zArr[i] = true;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            zArr[i2] = true;
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            zArr[i3] = true;
        }
        for (char c2 : charArray) {
            zArr[c2] = true;
        }
        this.zzgs = zArr;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzcv
    protected final int zza(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            char cCharAt = charSequence.charAt(i);
            boolean[] zArr = this.zzgs;
            if (cCharAt >= zArr.length || !zArr[cCharAt]) {
                break;
            }
            i++;
        }
        return i;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzcr
    public final String zzag(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            boolean[] zArr = this.zzgs;
            if (cCharAt >= zArr.length || !zArr[cCharAt]) {
                return zza(str, i);
            }
        }
        return str;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzcv
    protected final char[] zzj(int i) {
        boolean[] zArr = this.zzgs;
        if (i < zArr.length && zArr[i]) {
            return null;
        }
        if (i == 32 && this.zzgr) {
            return zzgp;
        }
        if (i <= 127) {
            char[] cArr = zzgq;
            return new char[]{'%', cArr[i >>> 4], cArr[i & 15]};
        }
        if (i <= 2047) {
            char[] cArr2 = zzgq;
            char[] cArr3 = {'%', cArr2[(i >>> 4) | 12], cArr2[i & 15], '%', cArr2[(i & 3) | 8], cArr2[i & 15]};
            int i2 = i >>> 4;
            int i3 = i2 >>> 2;
            return cArr3;
        }
        if (i <= 65535) {
            char[] cArr4 = zzgq;
            char[] cArr5 = {'%', 'E', cArr4[i >>> 2], '%', cArr4[(i & 3) | 8], cArr4[i & 15], '%', cArr4[(i & 3) | 8], cArr4[i & 15]};
            int i4 = i >>> 4;
            int i5 = i4 >>> 2;
            int i6 = i5 >>> 4;
            return cArr5;
        }
        if (i <= 1114111) {
            char[] cArr6 = zzgq;
            char[] cArr7 = {'%', 'F', cArr6[(i >>> 2) & 7], '%', cArr6[(i & 3) | 8], cArr6[i & 15], '%', cArr6[(i & 3) | 8], cArr6[i & 15], '%', cArr6[(i & 3) | 8], cArr6[i & 15]};
            int i7 = i >>> 4;
            int i8 = i7 >>> 2;
            int i9 = i8 >>> 4;
            int i10 = i9 >>> 2;
            int i11 = i10 >>> 4;
            return cArr7;
        }
        StringBuilder sb = new StringBuilder(43);
        sb.append("Invalid unicode character value ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }
}
