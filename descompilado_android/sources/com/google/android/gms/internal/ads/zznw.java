package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznw {
    public static boolean zzbc(String str) {
        return "audio".equals(zzbe(str));
    }

    public static boolean zzbd(String str) {
        return "video".equals(zzbe(str));
    }

    private static String zzbe(String str) {
        if (str == null) {
            return null;
        }
        int iIndexOf = str.indexOf(47);
        if (iIndexOf == -1) {
            String strValueOf = String.valueOf(str);
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Invalid mime type: ".concat(strValueOf) : new String("Invalid mime type: "));
        }
        return str.substring(0, iIndexOf);
    }
}
