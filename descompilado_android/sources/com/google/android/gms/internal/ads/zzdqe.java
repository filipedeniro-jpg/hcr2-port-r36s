package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdqe {
    public static final byte[] zzhkv;
    private static final ByteBuffer zzhkw;
    private static final zzdpf zzhkx;
    static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

    public static int zzbj(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int zzfk(long j) {
        return (int) (j ^ (j >>> 32));
    }

    static boolean zzn(zzdrn zzdrnVar) {
        return false;
    }

    static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean zzac(byte[] bArr) {
        return zzdtd.zzac(bArr);
    }

    public static String zzad(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int iZza = zza(length, bArr, 0, length);
        if (iZza == 0) {
            return 1;
        }
        return iZza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + bArr[i5];
        }
        return i4;
    }

    static Object zzd(Object obj, Object obj2) {
        return ((zzdrn) obj).zzazs().zzi((zzdrn) obj2).zzazl();
    }

    static {
        byte[] bArr = new byte[0];
        zzhkv = bArr;
        zzhkw = ByteBuffer.wrap(bArr);
        byte[] bArr2 = zzhkv;
        zzhkx = zzdpf.zzc(bArr2, 0, bArr2.length, false);
    }
}
