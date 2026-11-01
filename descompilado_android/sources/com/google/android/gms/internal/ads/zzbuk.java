package com.google.android.gms.internal.ads;

import androidx.collection.SimpleArrayMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbuk {
    zzabt zzfll;
    zzabs zzflm;
    zzacf zzfln;
    zzace zzflo;
    zzafp zzflp;
    final SimpleArrayMap<String, zzabz> zzflq = new SimpleArrayMap<>();
    final SimpleArrayMap<String, zzaby> zzflr = new SimpleArrayMap<>();

    public final zzbuk zzb(zzabt zzabtVar) {
        this.zzfll = zzabtVar;
        return this;
    }

    public final zzbuk zzb(zzabs zzabsVar) {
        this.zzflm = zzabsVar;
        return this;
    }

    public final zzbuk zzb(zzacf zzacfVar) {
        this.zzfln = zzacfVar;
        return this;
    }

    public final zzbuk zza(zzace zzaceVar) {
        this.zzflo = zzaceVar;
        return this;
    }

    public final zzbuk zzb(zzafp zzafpVar) {
        this.zzflp = zzafpVar;
        return this;
    }

    public final zzbuk zzb(String str, zzabz zzabzVar, zzaby zzabyVar) {
        this.zzflq.put(str, zzabzVar);
        this.zzflr.put(str, zzabyVar);
        return this;
    }

    public final zzbui zzaii() {
        return new zzbui(this);
    }
}
