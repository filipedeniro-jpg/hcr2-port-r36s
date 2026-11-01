package com.google.android.gms.internal.firebase_remote_config;

import com.google.android.gms.games.Notifications;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
final class zzft {
    static int zza(byte[] bArr, int i, zzfw zzfwVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zza(b, bArr, i2, zzfwVar);
        }
        zzfwVar.zzor = b;
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, zzfw zzfwVar) {
        int i3 = i & Notifications.NOTIFICATION_TYPES_ALL;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzfwVar.zzor = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & 127) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzfwVar.zzor = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & 127) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzfwVar.zzor = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & 127) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzfwVar.zzor = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & 127) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzfwVar.zzor = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzb(byte[] bArr, int i, zzfw zzfwVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzfwVar.zzos = j;
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
        zzfwVar.zzos = j2;
        return i3;
    }

    static int zza(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    static long zzb(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static double zzc(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzb(bArr, i));
    }

    static float zzd(byte[] bArr, int i) {
        return Float.intBitsToFloat(zza(bArr, i));
    }

    static int zzc(byte[] bArr, int i, zzfw zzfwVar) throws zzhq {
        int iZza = zza(bArr, i, zzfwVar);
        int i2 = zzfwVar.zzor;
        if (i2 < 0) {
            throw zzhq.zzhf();
        }
        if (i2 == 0) {
            zzfwVar.zzot = "";
            return iZza;
        }
        zzfwVar.zzot = new String(bArr, iZza, i2, zzhm.UTF_8);
        return iZza + i2;
    }

    static int zzd(byte[] bArr, int i, zzfw zzfwVar) throws zzhq {
        int iZza = zza(bArr, i, zzfwVar);
        int i2 = zzfwVar.zzor;
        if (i2 < 0) {
            throw zzhq.zzhf();
        }
        if (i2 == 0) {
            zzfwVar.zzot = "";
            return iZza;
        }
        zzfwVar.zzot = zzkg.zzg(bArr, iZza, i2);
        return iZza + i2;
    }

    static int zze(byte[] bArr, int i, zzfw zzfwVar) throws zzhq {
        int iZza = zza(bArr, i, zzfwVar);
        int i2 = zzfwVar.zzor;
        if (i2 < 0) {
            throw zzhq.zzhf();
        }
        if (i2 > bArr.length - iZza) {
            throw zzhq.zzhe();
        }
        if (i2 == 0) {
            zzfwVar.zzot = zzfx.zzov;
            return iZza;
        }
        zzfwVar.zzot = zzfx.zza(bArr, iZza, i2);
        return iZza + i2;
    }

    static int zza(zzjf zzjfVar, byte[] bArr, int i, int i2, zzfw zzfwVar) throws IOException {
        int iZza = i + 1;
        int i3 = bArr[i];
        if (i3 < 0) {
            iZza = zza(i3, bArr, iZza, zzfwVar);
            i3 = zzfwVar.zzor;
        }
        int i4 = iZza;
        if (i3 < 0 || i3 > i2 - i4) {
            throw zzhq.zzhe();
        }
        Object objNewInstance = zzjfVar.newInstance();
        int i5 = i3 + i4;
        zzjfVar.zza(objNewInstance, bArr, i4, i5, zzfwVar);
        zzjfVar.zzm(objNewInstance);
        zzfwVar.zzot = objNewInstance;
        return i5;
    }

    static int zza(zzjf zzjfVar, byte[] bArr, int i, int i2, int i3, zzfw zzfwVar) throws IOException {
        zziu zziuVar = (zziu) zzjfVar;
        Object objNewInstance = zziuVar.newInstance();
        int iZza = zziuVar.zza(objNewInstance, bArr, i, i2, i3, zzfwVar);
        zziuVar.zzm(objNewInstance);
        zzfwVar.zzot = objNewInstance;
        return iZza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzhn<?> zzhnVar, zzfw zzfwVar) {
        zzhk zzhkVar = (zzhk) zzhnVar;
        int iZza = zza(bArr, i2, zzfwVar);
        zzhkVar.zzbb(zzfwVar.zzor);
        while (iZza < i3) {
            int iZza2 = zza(bArr, iZza, zzfwVar);
            if (i != zzfwVar.zzor) {
                break;
            }
            iZza = zza(bArr, iZza2, zzfwVar);
            zzhkVar.zzbb(zzfwVar.zzor);
        }
        return iZza;
    }

    static int zza(byte[] bArr, int i, zzhn<?> zzhnVar, zzfw zzfwVar) throws IOException {
        zzhk zzhkVar = (zzhk) zzhnVar;
        int iZza = zza(bArr, i, zzfwVar);
        int i2 = zzfwVar.zzor + iZza;
        while (iZza < i2) {
            iZza = zza(bArr, iZza, zzfwVar);
            zzhkVar.zzbb(zzfwVar.zzor);
        }
        if (iZza == i2) {
            return iZza;
        }
        throw zzhq.zzhe();
    }

    static int zza(zzjf<?> zzjfVar, int i, byte[] bArr, int i2, int i3, zzhn<?> zzhnVar, zzfw zzfwVar) throws IOException {
        int iZza = zza(zzjfVar, bArr, i2, i3, zzfwVar);
        zzhnVar.add(zzfwVar.zzot);
        while (iZza < i3) {
            int iZza2 = zza(bArr, iZza, zzfwVar);
            if (i != zzfwVar.zzor) {
                break;
            }
            iZza = zza(zzjfVar, bArr, iZza2, i3, zzfwVar);
            zzhnVar.add(zzfwVar.zzot);
        }
        return iZza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzka zzkaVar, zzfw zzfwVar) throws zzhq {
        if ((i >>> 3) == 0) {
            throw zzhq.zzhh();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            int iZzb = zzb(bArr, i2, zzfwVar);
            zzkaVar.zzb(i, Long.valueOf(zzfwVar.zzos));
            return iZzb;
        }
        if (i4 == 1) {
            zzkaVar.zzb(i, Long.valueOf(zzb(bArr, i2)));
            return i2 + 8;
        }
        if (i4 == 2) {
            int iZza = zza(bArr, i2, zzfwVar);
            int i5 = zzfwVar.zzor;
            if (i5 < 0) {
                throw zzhq.zzhf();
            }
            if (i5 > bArr.length - iZza) {
                throw zzhq.zzhe();
            }
            if (i5 == 0) {
                zzkaVar.zzb(i, zzfx.zzov);
            } else {
                zzkaVar.zzb(i, zzfx.zza(bArr, iZza, i5));
            }
            return iZza + i5;
        }
        if (i4 != 3) {
            if (i4 == 5) {
                zzkaVar.zzb(i, Integer.valueOf(zza(bArr, i2)));
                return i2 + 4;
            }
            throw zzhq.zzhh();
        }
        zzka zzkaVarZzjb = zzka.zzjb();
        int i6 = (i & (-8)) | 4;
        int i7 = 0;
        while (i2 < i3) {
            int iZza2 = zza(bArr, i2, zzfwVar);
            int i8 = zzfwVar.zzor;
            if (i8 == i6) {
                i7 = i8;
                i2 = iZza2;
                break;
            }
            i7 = i8;
            i2 = zza(i8, bArr, iZza2, i3, zzkaVarZzjb, zzfwVar);
        }
        if (i2 > i3 || i7 != i6) {
            throw zzhq.zzhl();
        }
        zzkaVar.zzb(i, zzkaVarZzjb);
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzfw zzfwVar) throws zzhq {
        if ((i >>> 3) == 0) {
            throw zzhq.zzhh();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            return zzb(bArr, i2, zzfwVar);
        }
        if (i4 == 1) {
            return i2 + 8;
        }
        if (i4 == 2) {
            return zza(bArr, i2, zzfwVar) + zzfwVar.zzor;
        }
        if (i4 != 3) {
            if (i4 == 5) {
                return i2 + 4;
            }
            throw zzhq.zzhh();
        }
        int i5 = (i & (-8)) | 4;
        int i6 = 0;
        while (i2 < i3) {
            i2 = zza(bArr, i2, zzfwVar);
            i6 = zzfwVar.zzor;
            if (i6 == i5) {
                break;
            }
            i2 = zza(i6, bArr, i2, i3, zzfwVar);
        }
        if (i2 > i3 || i6 != i5) {
            throw zzhq.zzhl();
        }
        return i2;
    }
}
