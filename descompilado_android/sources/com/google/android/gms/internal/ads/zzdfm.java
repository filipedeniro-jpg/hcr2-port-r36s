package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdfm {
    public static void zza(zzdhw zzdhwVar) throws GeneralSecurityException {
        zzdmv.zza(zza(zzdhwVar.zzasl().zzasw()));
        zza(zzdhwVar.zzasl().zzaqk());
        if (zzdhwVar.zzasn() == zzdhg.UNKNOWN_FORMAT) {
            throw new GeneralSecurityException("unknown EC point format");
        }
        zzdef.zza(zzdhwVar.zzasm().zzasg());
    }

    public static String zza(zzdin zzdinVar) throws NoSuchAlgorithmException {
        int i = zzdfl.zzgsw[zzdinVar.ordinal()];
        if (i == 1) {
            return "HmacSha1";
        }
        if (i == 2) {
            return "HmacSha256";
        }
        if (i == 3) {
            return "HmacSha512";
        }
        String strValueOf = String.valueOf(zzdinVar);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 27);
        sb.append("hash unsupported for HMAC: ");
        sb.append(strValueOf);
        throw new NoSuchAlgorithmException(sb.toString());
    }

    public static zzdmx zza(zzdii zzdiiVar) throws GeneralSecurityException {
        int i = zzdfl.zzgsx[zzdiiVar.ordinal()];
        if (i == 1) {
            return zzdmx.NIST_P256;
        }
        if (i == 2) {
            return zzdmx.NIST_P384;
        }
        if (i == 3) {
            return zzdmx.NIST_P521;
        }
        String strValueOf = String.valueOf(zzdiiVar);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 20);
        sb.append("unknown curve type: ");
        sb.append(strValueOf);
        throw new GeneralSecurityException(sb.toString());
    }

    public static zzdmz zza(zzdhg zzdhgVar) throws GeneralSecurityException {
        int i = zzdfl.zzgsy[zzdhgVar.ordinal()];
        if (i == 1) {
            return zzdmz.UNCOMPRESSED;
        }
        if (i == 2) {
            return zzdmz.DO_NOT_USE_CRUNCHY_UNCOMPRESSED;
        }
        if (i == 3) {
            return zzdmz.COMPRESSED;
        }
        String strValueOf = String.valueOf(zzdhgVar);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 22);
        sb.append("unknown point format: ");
        sb.append(strValueOf);
        throw new GeneralSecurityException(sb.toString());
    }
}
