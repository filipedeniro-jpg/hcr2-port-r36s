package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcxf<O> {
    private final E zzgkx;

    @Nullable
    private final String zzgky;
    private final List<zzdcp<?>> zzglc;
    final /* synthetic */ zzcwz zzgld;
    private final zzdcp<?> zzglg;
    private final zzdcp<O> zzglh;

    private zzcxf(zzcwz zzcwzVar, E e, String str, zzdcp<?> zzdcpVar, List<zzdcp<?>> list, zzdcp<O> zzdcpVar2) {
        this.zzgld = zzcwzVar;
        this.zzgkx = e;
        this.zzgky = str;
        this.zzglg = zzdcpVar;
        this.zzglc = list;
        this.zzglh = zzdcpVar2;
    }

    public final zzcxf<O> zzgi(String str) {
        return new zzcxf<>(this.zzgld, this.zzgkx, str, this.zzglg, this.zzglc, this.zzglh);
    }

    public final <O2> zzcxf<O2> zzb(final zzcwu<O, O2> zzcwuVar) {
        return zza(new zzdbq(zzcwuVar) { // from class: com.google.android.gms.internal.ads.zzcxe
            private final zzcwu zzglf;

            {
                this.zzglf = zzcwuVar;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return zzdcf.zzah(this.zzglf.apply(obj));
            }
        });
    }

    public final <O2> zzcxf<O2> zza(zzdbq<O, O2> zzdbqVar) {
        return zza(zzdbqVar, this.zzgld.zzfnq);
    }

    private final <O2> zzcxf<O2> zza(zzdbq<O, O2> zzdbqVar, Executor executor) {
        return new zzcxf<>(this.zzgld, this.zzgkx, this.zzgky, this.zzglg, this.zzglc, zzdcf.zzb(this.zzglh, zzdbqVar, executor));
    }

    public final <O2> zzcxf<O2> zzb(final zzdcp<O2> zzdcpVar) {
        return zza(new zzdbq(zzdcpVar) { // from class: com.google.android.gms.internal.ads.zzcxh
            private final zzdcp zzfoj;

            {
                this.zzfoj = zzdcpVar;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfoj;
            }
        }, zzawx.zzdwb);
    }

    public final <T extends Throwable> zzcxf<O> zza(Class<T> cls, final zzcwu<T, O> zzcwuVar) {
        return zza(cls, new zzdbq(zzcwuVar) { // from class: com.google.android.gms.internal.ads.zzcxg
            private final zzcwu zzglf;

            {
                this.zzglf = zzcwuVar;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return zzdcf.zzah(this.zzglf.apply((Throwable) obj));
            }
        });
    }

    public final <T extends Throwable> zzcxf<O> zza(Class<T> cls, zzdbq<T, O> zzdbqVar) {
        zzcwz zzcwzVar = this.zzgld;
        return new zzcxf<>(zzcwzVar, this.zzgkx, this.zzgky, this.zzglg, this.zzglc, zzdcf.zzb(this.zzglh, cls, zzdbqVar, zzcwzVar.zzfnq));
    }

    public final zzcxf<O> zza(long j, TimeUnit timeUnit) {
        zzcwz zzcwzVar = this.zzgld;
        return new zzcxf<>(zzcwzVar, this.zzgkx, this.zzgky, this.zzglg, this.zzglc, zzdcf.zza(this.zzglh, j, timeUnit, zzcwzVar.zzfck));
    }

    public final zzcww<E, O> zzano() {
        E e = this.zzgkx;
        String strZzv = this.zzgky;
        if (strZzv == null) {
            strZzv = this.zzgld.zzv(e);
        }
        final zzcww<E, O> zzcwwVar = new zzcww<>(e, strZzv, this.zzglh);
        this.zzgld.zzglb.zza(zzcwwVar);
        this.zzglg.addListener(new Runnable(this, zzcwwVar) { // from class: com.google.android.gms.internal.ads.zzcxj
            private final zzcxf zzglk;
            private final zzcww zzgll;

            {
                this.zzglk = this;
                this.zzgll = zzcwwVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzcxf zzcxfVar = this.zzglk;
                zzcxfVar.zzgld.zzglb.zzb(this.zzgll);
            }
        }, zzawx.zzdwb);
        zzdcf.zza(zzcwwVar, new zzcxi(this, zzcwwVar), zzawx.zzdwb);
        return zzcwwVar;
    }

    public final zzcxf<O> zzw(E e) {
        return this.zzgld.zza(e, zzano());
    }
}
