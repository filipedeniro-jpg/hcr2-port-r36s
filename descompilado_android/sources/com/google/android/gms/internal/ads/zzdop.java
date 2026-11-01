package com.google.android.gms.internal.ads;

import com.google.android.gms.games.Notifications;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdop {
    static int zza(byte[] bArr, int i, zzdos zzdosVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zza(b, bArr, i2, zzdosVar);
        }
        zzdosVar.zzhfj = b;
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, zzdos zzdosVar) {
        int i3 = i & Notifications.NOTIFICATION_TYPES_ALL;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzdosVar.zzhfj = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & 127) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzdosVar.zzhfj = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & 127) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzdosVar.zzhfj = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & 127) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzdosVar.zzhfj = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & 127) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzdosVar.zzhfj = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzb(byte[] bArr, int i, zzdos zzdosVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzdosVar.zzhfk = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & 127)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & 127)) << i4;
            b = b2;
            i3 = i5;
        }
        zzdosVar.zzhfk = j2;
        return i3;
    }

    static int zzf(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    static long zzg(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static double zzh(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzg(bArr, i));
    }

    static float zzi(byte[] bArr, int i) {
        return Float.intBitsToFloat(zzf(bArr, i));
    }

    static int zzc(byte[] bArr, int i, zzdos zzdosVar) throws zzdqn {
        int iZza = zza(bArr, i, zzdosVar);
        int i2 = zzdosVar.zzhfj;
        if (i2 < 0) {
            throw zzdqn.zzazy();
        }
        if (i2 == 0) {
            zzdosVar.zzhfl = "";
            return iZza;
        }
        zzdosVar.zzhfl = new String(bArr, iZza, i2, zzdqe.UTF_8);
        return iZza + i2;
    }

    static int zzd(byte[] bArr, int i, zzdos zzdosVar) throws zzdqn {
        int iZza = zza(bArr, i, zzdosVar);
        int i2 = zzdosVar.zzhfj;
        if (i2 < 0) {
            throw zzdqn.zzazy();
        }
        if (i2 == 0) {
            zzdosVar.zzhfl = "";
            return iZza;
        }
        zzdosVar.zzhfl = zzdtd.zzn(bArr, iZza, i2);
        return iZza + i2;
    }

    static int zze(byte[] bArr, int i, zzdos zzdosVar) throws zzdqn {
        int iZza = zza(bArr, i, zzdosVar);
        int i2 = zzdosVar.zzhfj;
        if (i2 < 0) {
            throw zzdqn.zzazy();
        }
        if (i2 > bArr.length - iZza) {
            throw zzdqn.zzazx();
        }
        if (i2 == 0) {
            zzdosVar.zzhfl = zzdot.zzhfn;
            return iZza;
        }
        zzdosVar.zzhfl = zzdot.zzh(bArr, iZza, i2);
        return iZza + i2;
    }

    static int zza(zzdsc zzdscVar, byte[] bArr, int i, int i2, zzdos zzdosVar) throws IOException {
        int iZza = i + 1;
        int i3 = bArr[i];
        if (i3 < 0) {
            iZza = zza(i3, bArr, iZza, zzdosVar);
            i3 = zzdosVar.zzhfj;
        }
        int i4 = iZza;
        if (i3 < 0 || i3 > i2 - i4) {
            throw zzdqn.zzazx();
        }
        Object objNewInstance = zzdscVar.newInstance();
        int i5 = i3 + i4;
        zzdscVar.zza(objNewInstance, bArr, i4, i5, zzdosVar);
        zzdscVar.zzak(objNewInstance);
        zzdosVar.zzhfl = objNewInstance;
        return i5;
    }

    static int zza(zzdsc zzdscVar, byte[] bArr, int i, int i2, int i3, zzdos zzdosVar) throws IOException {
        zzdrr zzdrrVar = (zzdrr) zzdscVar;
        Object objNewInstance = zzdrrVar.newInstance();
        int iZza = zzdrrVar.zza(objNewInstance, bArr, i, i2, i3, zzdosVar);
        zzdrrVar.zzak(objNewInstance);
        zzdosVar.zzhfl = objNewInstance;
        return iZza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzdqk<?> zzdqkVar, zzdos zzdosVar) {
        zzdqf zzdqfVar = (zzdqf) zzdqkVar;
        int iZza = zza(bArr, i2, zzdosVar);
        zzdqfVar.zzgq(zzdosVar.zzhfj);
        while (iZza < i3) {
            int iZza2 = zza(bArr, iZza, zzdosVar);
            if (i != zzdosVar.zzhfj) {
                break;
            }
            iZza = zza(bArr, iZza2, zzdosVar);
            zzdqfVar.zzgq(zzdosVar.zzhfj);
        }
        return iZza;
    }

    static int zza(byte[] bArr, int i, zzdqk<?> zzdqkVar, zzdos zzdosVar) throws IOException {
        zzdqf zzdqfVar = (zzdqf) zzdqkVar;
        int iZza = zza(bArr, i, zzdosVar);
        int i2 = zzdosVar.zzhfj + iZza;
        while (iZza < i2) {
            iZza = zza(bArr, iZza, zzdosVar);
            zzdqfVar.zzgq(zzdosVar.zzhfj);
        }
        if (iZza == i2) {
            return iZza;
        }
        throw zzdqn.zzazx();
    }

    static int zza(zzdsc<?> zzdscVar, int i, byte[] bArr, int i2, int i3, zzdqk<?> zzdqkVar, zzdos zzdosVar) throws IOException {
        int iZza = zza(zzdscVar, bArr, i2, i3, zzdosVar);
        zzdqkVar.add(zzdosVar.zzhfl);
        while (iZza < i3) {
            int iZza2 = zza(bArr, iZza, zzdosVar);
            if (i != zzdosVar.zzhfj) {
                break;
            }
            iZza = zza(zzdscVar, bArr, iZza2, i3, zzdosVar);
            zzdqkVar.add(zzdosVar.zzhfl);
        }
        return iZza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzdsx zzdsxVar, zzdos zzdosVar) throws zzdqn {
        if ((i >>> 3) == 0) {
            throw zzdqn.zzbaa();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            int iZzb = zzb(bArr, i2, zzdosVar);
            zzdsxVar.zzc(i, Long.valueOf(zzdosVar.zzhfk));
            return iZzb;
        }
        if (i4 == 1) {
            zzdsxVar.zzc(i, Long.valueOf(zzg(bArr, i2)));
            return i2 + 8;
        }
        if (i4 == 2) {
            int iZza = zza(bArr, i2, zzdosVar);
            int i5 = zzdosVar.zzhfj;
            if (i5 < 0) {
                throw zzdqn.zzazy();
            }
            if (i5 > bArr.length - iZza) {
                throw zzdqn.zzazx();
            }
            if (i5 == 0) {
                zzdsxVar.zzc(i, zzdot.zzhfn);
            } else {
                zzdsxVar.zzc(i, zzdot.zzh(bArr, iZza, i5));
            }
            return iZza + i5;
        }
        if (i4 != 3) {
            if (i4 == 5) {
                zzdsxVar.zzc(i, Integer.valueOf(zzf(bArr, i2)));
                return i2 + 4;
            }
            throw zzdqn.zzbaa();
        }
        zzdsx zzdsxVarZzbbt = zzdsx.zzbbt();
        int i6 = (i & (-8)) | 4;
        int i7 = 0;
        while (i2 < i3) {
            int iZza2 = zza(bArr, i2, zzdosVar);
            int i8 = zzdosVar.zzhfj;
            if (i8 == i6) {
                i7 = i8;
                i2 = iZza2;
                break;
            }
            i7 = i8;
            i2 = zza(i8, bArr, iZza2, i3, zzdsxVarZzbbt, zzdosVar);
        }
        if (i2 > i3 || i7 != i6) {
            throw zzdqn.zzbad();
        }
        zzdsxVar.zzc(i, zzdsxVarZzbbt);
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzdos zzdosVar) throws zzdqn {
        if ((i >>> 3) == 0) {
            throw zzdqn.zzbaa();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            return zzb(bArr, i2, zzdosVar);
        }
        if (i4 == 1) {
            return i2 + 8;
        }
        if (i4 == 2) {
            return zza(bArr, i2, zzdosVar) + zzdosVar.zzhfj;
        }
        if (i4 != 3) {
            if (i4 == 5) {
                return i2 + 4;
            }
            throw zzdqn.zzbaa();
        }
        int i5 = (i & (-8)) | 4;
        int i6 = 0;
        while (i2 < i3) {
            i2 = zza(bArr, i2, zzdosVar);
            i6 = zzdosVar.zzhfj;
            if (i6 == i5) {
                break;
            }
            i2 = zza(i6, bArr, i2, i3, zzdosVar);
        }
        if (i2 > i3 || i6 != i5) {
            throw zzdqn.zzbad();
        }
        return i2;
    }
}
