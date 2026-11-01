package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzks<T, B> {
    zzks() {
    }

    abstract B zza();

    abstract T zza(B b);

    abstract void zza(B b, int i, int i2);

    abstract void zza(B b, int i, long j);

    abstract void zza(B b, int i, zzgm zzgmVar);

    abstract void zza(B b, int i, T t);

    abstract void zza(T t, zzll zzllVar) throws IOException;

    abstract void zza(Object obj, T t);

    abstract boolean zza(zzjx zzjxVar);

    abstract T zzb(Object obj);

    abstract void zzb(B b, int i, long j);

    abstract void zzb(T t, zzll zzllVar) throws IOException;

    abstract void zzb(Object obj, B b);

    abstract B zzc(Object obj);

    abstract T zzc(T t, T t2);

    abstract void zzd(Object obj);

    abstract int zze(T t);

    abstract int zzf(T t);

    final boolean zza(B b, zzjx zzjxVar) throws IOException {
        int iZzb = zzjxVar.zzb();
        int i = iZzb >>> 3;
        int i2 = iZzb & 7;
        if (i2 == 0) {
            zza(b, i, zzjxVar.zzg());
            return true;
        }
        if (i2 == 1) {
            zzb(b, i, zzjxVar.zzi());
            return true;
        }
        if (i2 == 2) {
            zza((Object) b, i, zzjxVar.zzn());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 != 5) {
                throw zzig.zzf();
            }
            zza((Object) b, i, zzjxVar.zzj());
            return true;
        }
        B bZza = zza();
        int i3 = 4 | (i << 3);
        while (zzjxVar.zza() != Integer.MAX_VALUE && zza((Object) bZza, zzjxVar)) {
        }
        if (i3 != zzjxVar.zzb()) {
            throw zzig.zze();
        }
        zza(b, i, zza(bZza));
        return true;
    }
}
