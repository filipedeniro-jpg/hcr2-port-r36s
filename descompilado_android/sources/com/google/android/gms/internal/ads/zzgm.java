package com.google.android.gms.internal.ads;

import android.util.Log;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgm {
    public final int index;
    private final zzgv[] zzacm;
    private final zzmw zzacn;
    private final zzgy[] zzadj;
    private final zzgu zzadk;
    private final zzls zzadq;
    public int zzaee;
    public long zzaef;
    public final zzlq zzaei;
    public final Object zzaej;
    public final zzmf[] zzaek;
    private final boolean[] zzael;
    public final long zzaem;
    public boolean zzaen;
    public boolean zzaeo;
    public boolean zzaep;
    public zzgm zzaeq;
    public zzmy zzaer;
    private zzmy zzaes;

    public zzgm(zzgv[] zzgvVarArr, zzgy[] zzgyVarArr, long j, zzmw zzmwVar, zzgu zzguVar, zzls zzlsVar, Object obj, int i, int i2, boolean z, long j2) {
        this.zzacm = zzgvVarArr;
        this.zzadj = zzgyVarArr;
        this.zzaem = j;
        this.zzacn = zzmwVar;
        this.zzadk = zzguVar;
        this.zzadq = zzlsVar;
        this.zzaej = zznt.checkNotNull(obj);
        this.index = i;
        this.zzaee = i2;
        this.zzaen = z;
        this.zzaef = j2;
        this.zzaek = new zzmf[zzgvVarArr.length];
        this.zzael = new boolean[zzgvVarArr.length];
        this.zzaei = zzlsVar.zza(i2, zzguVar.zzek());
    }

    public final long zzed() {
        return this.zzaem - this.zzaef;
    }

    public final void zzc(int i, boolean z) {
        this.zzaee = i;
        this.zzaen = z;
    }

    public final boolean zzee() {
        if (this.zzaeo) {
            return !this.zzaep || this.zzaei.zzha() == Long.MIN_VALUE;
        }
        return false;
    }

    public final boolean zzef() throws zzgb {
        boolean z;
        zzmy zzmyVarZza = this.zzacn.zza(this.zzadj, this.zzaei.zzgx());
        zzmy zzmyVar = this.zzaes;
        if (zzmyVar != null) {
            int i = 0;
            while (true) {
                if (i >= zzmyVarZza.zzbed.length) {
                    z = true;
                    break;
                }
                if (!zzmyVarZza.zza(zzmyVar, i)) {
                    z = false;
                    break;
                }
                i++;
            }
        } else {
            z = false;
            break;
        }
        if (z) {
            return false;
        }
        this.zzaer = zzmyVarZza;
        return true;
    }

    public final long zzb(long j, boolean z) {
        return zza(j, false, new boolean[this.zzacm.length]);
    }

    public final long zza(long j, boolean z, boolean[] zArr) {
        zzmx zzmxVar = this.zzaer.zzbed;
        int i = 0;
        while (true) {
            boolean z2 = true;
            if (i >= zzmxVar.length) {
                break;
            }
            boolean[] zArr2 = this.zzael;
            if (z || !this.zzaer.zza(this.zzaes, i)) {
                z2 = false;
            }
            zArr2[i] = z2;
            i++;
        }
        long jZza = this.zzaei.zza(zzmxVar.zzhv(), this.zzael, this.zzaek, zArr, j);
        this.zzaes = this.zzaer;
        this.zzaep = false;
        int i2 = 0;
        while (true) {
            zzmf[] zzmfVarArr = this.zzaek;
            if (i2 < zzmfVarArr.length) {
                if (zzmfVarArr[i2] != null) {
                    zznt.checkState(zzmxVar.zzax(i2) != null);
                    this.zzaep = true;
                } else {
                    zznt.checkState(zzmxVar.zzax(i2) == null);
                }
                i2++;
            } else {
                this.zzadk.zza(this.zzacm, this.zzaer.zzbec, zzmxVar);
                return jZza;
            }
        }
    }

    public final void release() {
        try {
            this.zzadq.zzb(this.zzaei);
        } catch (RuntimeException e) {
            Log.e("ExoPlayerImplInternal", "Period release failed.", e);
        }
    }
}
