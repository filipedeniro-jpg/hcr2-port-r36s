package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
abstract class zzjx<T, B> {
    zzjx() {
    }

    abstract void zza(B b, int i, long j);

    abstract void zza(B b, int i, zzfx zzfxVar);

    abstract void zza(B b, int i, T t);

    abstract void zza(T t, zzku zzkuVar) throws IOException;

    abstract boolean zza(zzjg zzjgVar);

    abstract T zzaa(Object obj);

    abstract B zzab(Object obj);

    abstract int zzac(T t);

    abstract void zzb(B b, int i, long j);

    abstract void zzc(B b, int i, int i2);

    abstract void zzc(T t, zzku zzkuVar) throws IOException;

    abstract void zzg(Object obj, T t);

    abstract void zzh(Object obj, B b);

    abstract T zzi(T t, T t2);

    abstract B zziz();

    abstract void zzm(Object obj);

    abstract T zzs(B b);

    abstract int zzw(T t);

    final boolean zza(B b, zzjg zzjgVar) throws IOException {
        int tag = zzjgVar.getTag();
        int i = tag >>> 3;
        int i2 = tag & 7;
        if (i2 == 0) {
            zza(b, i, zzjgVar.zzfd());
            return true;
        }
        if (i2 == 1) {
            zzb(b, i, zzjgVar.zzff());
            return true;
        }
        if (i2 == 2) {
            zza((Object) b, i, zzjgVar.zzfj());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 != 5) {
                throw zzhq.zzhj();
            }
            zzc(b, i, zzjgVar.zzfg());
            return true;
        }
        B bZziz = zziz();
        int i3 = 4 | (i << 3);
        while (zzjgVar.zzfz() != Integer.MAX_VALUE && zza(bZziz, zzjgVar)) {
        }
        if (i3 != zzjgVar.getTag()) {
            throw zzhq.zzhi();
        }
        zza(b, i, zzs(bZziz));
        return true;
    }
}
