package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcft implements zzcfk<zzbhy> {
    private final Executor zzfbm;
    private final zzcvk zzffn;
    private final zzbyr zzfmy;
    private final zzbit zzfwx;
    private final zzczs<zzcvb, zzaun> zzfwy;
    private final Context zzlk;

    public zzcft(zzbit zzbitVar, Context context, Executor executor, zzbyr zzbyrVar, zzcvk zzcvkVar, zzczs<zzcvb, zzaun> zzczsVar) {
        this.zzlk = context;
        this.zzfwx = zzbitVar;
        this.zzfbm = executor;
        this.zzfmy = zzbyrVar;
        this.zzffn = zzcvkVar;
        this.zzfwy = zzczsVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final boolean zza(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        return (zzcvbVar.zzgiw == null || zzcvbVar.zzgiw.zzdhp == null) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final zzdcp<zzbhy> zzb(final zzcvj zzcvjVar, final zzcvb zzcvbVar) {
        return zzdcf.zzb(zzdcf.zzah(null), new zzdbq(this, zzcvjVar, zzcvbVar) { // from class: com.google.android.gms.internal.ads.zzcfw
            private final zzcvb zzfdo;
            private final zzcvj zzfno;
            private final zzcft zzfxa;

            {
                this.zzfxa = this;
                this.zzfno = zzcvjVar;
                this.zzfdo = zzcvbVar;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfxa.zzb(this.zzfno, this.zzfdo, obj);
            }
        }, this.zzfbm);
    }

    final /* synthetic */ void zzo(zzbbc zzbbcVar) {
        zzbbcVar.zzys();
        zzbbu zzbbuVarZzxj = zzbbcVar.zzxj();
        if (this.zzffn.zzgjr == null || zzbbuVarZzxj == null) {
            return;
        }
        zzbbuVarZzxj.zzb(this.zzffn.zzgjr);
    }

    final /* synthetic */ zzdcp zzb(zzcvj zzcvjVar, zzcvb zzcvbVar, Object obj) throws Exception {
        zztw zztwVarZza = zzcvo.zza(this.zzlk, zzcvbVar.zzgiy);
        final zzbbc zzbbcVarZzc = this.zzfmy.zzc(zztwVarZza);
        zzbbcVarZzc.zzat(zzcvbVar.zzdlf);
        zzbit zzbitVar = this.zzfwx;
        zzbkk zzbkkVar = new zzbkk(zzcvjVar, zzcvbVar, null);
        zzbzi zzbziVar = new zzbzi(this.zzlk, zzbbcVarZzc.getView(), this.zzfwy.apply(zzcvbVar));
        zzbbcVarZzc.getClass();
        final zzbhx zzbhxVarZza = zzbitVar.zza(zzbkkVar, new zzbib(zzbziVar, zzbbcVarZzc, zzcfv.zzp(zzbbcVarZzc), zzcvo.zze(zztwVarZza)));
        zzbhxVarZza.zzact().zzb(zzbbcVarZzc, false);
        zzbhxVarZza.zzaca().zza(new zzbmt(zzbbcVarZzc) { // from class: com.google.android.gms.internal.ads.zzcfy
            private final zzbbc zzehf;

            {
                this.zzehf = zzbbcVarZzc;
            }

            @Override // com.google.android.gms.internal.ads.zzbmt
            public final void onAdImpression() {
                zzbbc zzbbcVar = this.zzehf;
                if (zzbbcVar.zzyy() != null) {
                    zzbbcVar.zzyy().zzzw();
                }
            }
        }, zzawx.zzdwb);
        zzbhxVarZza.zzact();
        zzdcp<?> zzdcpVarZza = zzbyt.zza(zzbbcVarZzc, zzcvbVar.zzgiw.zzdhn, zzcvbVar.zzgiw.zzdhp);
        if (zzcvbVar.zzdlx) {
            zzbbcVarZzc.getClass();
            zzdcpVarZza.addListener(zzcfx.zzh(zzbbcVarZzc), this.zzfbm);
        }
        zzdcpVarZza.addListener(new Runnable(this, zzbbcVarZzc) { // from class: com.google.android.gms.internal.ads.zzcga
            private final zzbbc zzfoq;
            private final zzcft zzfxa;

            {
                this.zzfxa = this;
                this.zzfoq = zzbbcVarZzc;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzfxa.zzo(this.zzfoq);
            }
        }, this.zzfbm);
        return zzdcf.zzb(zzdcpVarZza, new zzczs(zzbhxVarZza) { // from class: com.google.android.gms.internal.ads.zzcfz
            private final zzbhx zzfxb;

            {
                this.zzfxb = zzbhxVarZza;
            }

            @Override // com.google.android.gms.internal.ads.zzczs
            public final Object apply(Object obj2) {
                return this.zzfxb.zzacv();
            }
        }, zzawx.zzdwb);
    }
}
