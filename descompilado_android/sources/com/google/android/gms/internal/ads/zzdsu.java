package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzdsu<T, B> {
    zzdsu() {
    }

    abstract void zza(B b, int i, long j);

    abstract void zza(B b, int i, zzdot zzdotVar);

    abstract void zza(B b, int i, T t);

    abstract void zza(T t, zzdtr zzdtrVar) throws IOException;

    abstract boolean zza(zzdsd zzdsdVar);

    abstract void zzak(Object obj);

    abstract T zzaq(B b);

    abstract int zzau(T t);

    abstract T zzay(Object obj);

    abstract B zzaz(Object obj);

    abstract void zzb(B b, int i, long j);

    abstract int zzba(T t);

    abstract B zzbbr();

    abstract void zzc(B b, int i, int i2);

    abstract void zzc(T t, zzdtr zzdtrVar) throws IOException;

    abstract void zzh(Object obj, T t);

    abstract void zzi(Object obj, B b);

    abstract T zzj(T t, T t2);

    final boolean zza(B b, zzdsd zzdsdVar) throws IOException {
        int tag = zzdsdVar.getTag();
        int i = tag >>> 3;
        int i2 = tag & 7;
        if (i2 == 0) {
            zza(b, i, zzdsdVar.zzaxr());
            return true;
        }
        if (i2 == 1) {
            zzb(b, i, zzdsdVar.zzaxt());
            return true;
        }
        if (i2 == 2) {
            zza((Object) b, i, zzdsdVar.zzaxx());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 != 5) {
                throw zzdqn.zzbac();
            }
            zzc(b, i, zzdsdVar.zzaxu());
            return true;
        }
        B bZzbbr = zzbbr();
        int i3 = 4 | (i << 3);
        while (zzdsdVar.zzayn() != Integer.MAX_VALUE && zza(bZzbbr, zzdsdVar)) {
        }
        if (i3 != zzdsdVar.getTag()) {
            throw zzdqn.zzbab();
        }
        zza(b, i, zzaq(bZzbbr));
        return true;
    }
}
