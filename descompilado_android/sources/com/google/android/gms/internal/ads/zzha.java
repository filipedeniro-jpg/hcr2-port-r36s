package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzha {
    public static final zzha zzagd = new zzgz();

    public abstract zzhb zza(int i, zzhb zzhbVar, boolean z, long j);

    public abstract zzhc zza(int i, zzhc zzhcVar, boolean z);

    public abstract int zzc(Object obj);

    public abstract int zzem();

    public abstract int zzen();

    public final boolean isEmpty() {
        return zzem() == 0;
    }

    public final zzhb zza(int i, zzhb zzhbVar, boolean z) {
        return zza(i, zzhbVar, false, 0L);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0020  */
    public final int zza(int i, zzhc zzhcVar, zzhb zzhbVar, int i2) {
        zza(i, zzhcVar, false);
        zza(0, zzhbVar, false);
        int i3 = 1;
        if (i != 0) {
            return i + 1;
        }
        if (i2 != 0) {
            if (i2 == 1) {
                i3 = 0;
            } else if (i2 == 2) {
                if (zzem() - 1 == 0) {
                    i3 = 0;
                }
            } else {
                throw new IllegalStateException();
            }
        } else if (zzem() - 1 == 0) {
            i3 = -1;
        }
        if (i3 == -1) {
            return -1;
        }
        zza(i3, zzhbVar, false);
        return 0;
    }
}
