package com.google.android.gms.internal.ads;

import android.util.Pair;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzns {
    private static final byte[] zzbfx = {0, 0, 0, 1};
    private static final int[] zzbfy = {96000, 88200, SettingsJsonConstants.SETTINGS_LOG_BUFFER_SIZE_DEFAULT, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};
    private static final int[] zzbfz = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    public static Pair<Integer, Integer> zze(byte[] bArr) {
        zzob zzobVar = new zzob(bArr);
        int iZza = zza(zzobVar);
        int iZzb = zzb(zzobVar);
        int iZzbg = zzobVar.zzbg(4);
        if (iZza == 5 || iZza == 29) {
            iZzb = zzb(zzobVar);
            if (zza(zzobVar) == 22) {
                iZzbg = zzobVar.zzbg(4);
            }
        }
        int i = zzbfz[iZzbg];
        zznt.checkArgument(i != -1);
        return Pair.create(Integer.valueOf(iZzb), Integer.valueOf(i));
    }

    public static byte[] zzc(byte[] bArr, int i, int i2) {
        byte[] bArr2 = zzbfx;
        byte[] bArr3 = new byte[bArr2.length + i2];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, i, bArr3, zzbfx.length, i2);
        return bArr3;
    }

    private static int zza(zzob zzobVar) {
        int iZzbg = zzobVar.zzbg(5);
        return iZzbg == 31 ? zzobVar.zzbg(6) + 32 : iZzbg;
    }

    private static int zzb(zzob zzobVar) {
        int iZzbg = zzobVar.zzbg(4);
        if (iZzbg == 15) {
            return zzobVar.zzbg(24);
        }
        zznt.checkArgument(iZzbg < 13);
        return zzbfy[iZzbg];
    }
}
