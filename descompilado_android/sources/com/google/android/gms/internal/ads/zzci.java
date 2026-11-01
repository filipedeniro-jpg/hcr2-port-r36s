package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzci {
    private static boolean zznt = false;
    private static MessageDigest zznu;
    private static final Object zznv = new Object();
    private static final Object zznw = new Object();
    static CountDownLatch zznx = new CountDownLatch(1);

    static void zzbx() {
        synchronized (zznw) {
            if (!zznt) {
                zznt = true;
                new Thread(new zzck()).start();
            }
        }
    }

    private static MessageDigest zzby() {
        boolean zAwait;
        MessageDigest messageDigest;
        zzbx();
        try {
            zAwait = zznx.await(2L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            zAwait = false;
        }
        if (zAwait && (messageDigest = zznu) != null) {
            return messageDigest;
        }
        return null;
    }

    static String zzj(zzbo.zza zzaVar, String str) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] bArrZza;
        byte[] byteArray = zzaVar.toByteArray();
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcnb)).booleanValue()) {
            if (zzed.zzyo == null) {
                throw new GeneralSecurityException();
            }
            bArrZza = ((zzbo.zzg) ((zzdqd) zzbo.zzg.zzbj().zzm(zzdot.zzy(zzed.zzyo.zzc(byteArray, str != null ? str.getBytes() : new byte[0]))).zza(zzbv.TINK_HYBRID).zzazm())).toByteArray();
        } else {
            Vector<byte[]> vectorZza = zza(byteArray, 255);
            if (vectorZza == null || vectorZza.size() == 0) {
                bArrZza = zza(zza(zzbo.zza.zzd.PSN_ENCODE_SIZE_FAIL).toByteArray(), str, true);
            } else {
                zzbo.zzg.zza zzaVarZzbj = zzbo.zzg.zzbj();
                Iterator<byte[]> it = vectorZza.iterator();
                while (it.hasNext()) {
                    zzaVarZzbj.zzm(zzdot.zzy(zza(it.next(), str, false)));
                }
                zzaVarZzbj.zzn(zzdot.zzy(zzb(byteArray)));
                bArrZza = ((zzbo.zzg) ((zzdqd) zzaVarZzbj.zzazm())).toByteArray();
            }
        }
        return zzcg.zza(bArrZza, true);
    }

    private static Vector<byte[]> zza(byte[] bArr, int i) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        int length = ((bArr.length + 255) - 1) / 255;
        Vector<byte[]> vector = new Vector<>();
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 255;
            try {
                vector.add(Arrays.copyOfRange(bArr, i3, bArr.length - i3 > 255 ? i3 + 255 : bArr.length));
            } catch (IndexOutOfBoundsException unused) {
                return null;
            }
        }
        return vector;
    }

    private static zzbo.zza zza(zzbo.zza.zzd zzdVar) {
        zzbo.zza.zzb zzbVarZzal = zzbo.zza.zzal();
        zzbVarZzal.zzau(zzdVar.zzab());
        return (zzbo.zza) ((zzdqd) zzbVarZzal.zzazm());
    }

    private static byte[] zza(byte[] bArr, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bArrArray;
        int i = z ? 239 : 255;
        if (bArr.length > i) {
            bArr = zza(zzbo.zza.zzd.PSN_ENCODE_SIZE_FAIL).toByteArray();
        }
        if (bArr.length < i) {
            byte[] bArr2 = new byte[i - bArr.length];
            new SecureRandom().nextBytes(bArr2);
            bArrArray = ByteBuffer.allocate(i + 1).put((byte) bArr.length).put(bArr).put(bArr2).array();
        } else {
            bArrArray = ByteBuffer.allocate(i + 1).put((byte) bArr.length).put(bArr).array();
        }
        if (z) {
            bArrArray = ByteBuffer.allocate(256).put(zzb(bArrArray)).put(bArrArray).array();
        }
        byte[] bArr3 = new byte[256];
        for (zzcl zzclVar : new zzcj().zzvl) {
            zzclVar.zza(bArrArray, bArr3);
        }
        if (str != null && str.length() > 0) {
            if (str.length() > 32) {
                str = str.substring(0, 32);
            }
            new zzdoj(str.getBytes("UTF-8")).zzx(bArr3);
        }
        return bArr3;
    }

    public static byte[] zzb(byte[] bArr) throws NoSuchAlgorithmException {
        byte[] bArrDigest;
        synchronized (zznv) {
            MessageDigest messageDigestZzby = zzby();
            if (messageDigestZzby == null) {
                throw new NoSuchAlgorithmException("Cannot compute hash");
            }
            messageDigestZzby.reset();
            messageDigestZzby.update(bArr);
            bArrDigest = zznu.digest();
        }
        return bArrDigest;
    }

    static String zza(String str, String str2, boolean z) {
        byte[] bArrZzb = zzb(str, str2, true);
        if (bArrZzb != null) {
            return zzcg.zza(bArrZzb, true);
        }
        return Integer.toString(7);
    }

    private static byte[] zzb(String str, String str2, boolean z) {
        byte[] bArrZza;
        zzbo.zzc.zza zzaVarZzav = zzbo.zzc.zzav();
        try {
            if (str.length() < 3) {
                bArrZza = str.getBytes("ISO-8859-1");
            } else {
                bArrZza = zzcg.zza(str, true);
            }
            zzaVarZzav.zzg(zzdot.zzy(bArrZza));
            zzaVarZzav.zzh(zzdot.zzy(str2.length() < 3 ? str2.getBytes("ISO-8859-1") : zzcg.zza(str2, true)));
            return ((zzbo.zzc) ((zzdqd) zzaVarZzav.zzazm())).toByteArray();
        } catch (UnsupportedEncodingException | GeneralSecurityException unused) {
            return null;
        }
    }
}
