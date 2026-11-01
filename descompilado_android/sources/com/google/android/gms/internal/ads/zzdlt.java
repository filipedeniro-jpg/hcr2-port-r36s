package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdlt {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static byte[] decode(String str) {
        byte[] bytes = str.getBytes(UTF_8);
        int length = bytes.length;
        zzdlv zzdlvVar = new zzdlv(2, new byte[(length * 3) / 4]);
        if (!zzdlvVar.zzb(bytes, 0, length, true)) {
            throw new IllegalArgumentException("bad base-64");
        }
        if (zzdlvVar.zzhaw == zzdlvVar.zzhav.length) {
            return zzdlvVar.zzhav;
        }
        byte[] bArr = new byte[zzdlvVar.zzhaw];
        System.arraycopy(zzdlvVar.zzhav, 0, bArr, 0, zzdlvVar.zzhaw);
        return bArr;
    }
}
