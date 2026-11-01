package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcje implements zzcfk<zzbyf> {
    private final zzawv zzbkw;
    private final Executor zzfbm;
    private final zzcvk zzffn;
    private final zzbyr zzfmy;
    private final zzbym zzfyz;
    private final Context zzlk;

    public zzcje(Context context, zzawv zzawvVar, zzcvk zzcvkVar, Executor executor, zzbym zzbymVar, zzbyr zzbyrVar) {
        this.zzlk = context;
        this.zzffn = zzcvkVar;
        this.zzfyz = zzbymVar;
        this.zzfbm = executor;
        this.zzbkw = zzawvVar;
        this.zzfmy = zzbyrVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final boolean zza(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        return (zzcvbVar.zzgiw == null || zzcvbVar.zzgiw.zzdhp == null) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final zzdcp<zzbyf> zzb(final zzcvj zzcvjVar, final zzcvb zzcvbVar) {
        final zzbzh zzbzhVar = new zzbzh();
        zzdcp<zzbyf> zzdcpVarZzb = zzdcf.zzb(zzdcf.zzah(null), new zzdbq(this, zzcvbVar, zzbzhVar, zzcvjVar) { // from class: com.google.android.gms.internal.ads.zzcjd
            private final zzcvb zzfxy;
            private final zzbzh zzfxz;
            private final zzcvj zzfya;
            private final zzcje zzfze;

            {
                this.zzfze = this;
                this.zzfxy = zzcvbVar;
                this.zzfxz = zzbzhVar;
                this.zzfya = zzcvjVar;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfze.zzb(this.zzfxy, this.zzfxz, this.zzfya, obj);
            }
        }, this.zzfbm);
        zzbzhVar.getClass();
        zzdcpVarZzb.addListener(zzcjg.zza(zzbzhVar), this.zzfbm);
        return zzdcpVarZzb;
    }

    final /* synthetic */ zzdcp zzb(final zzcvb zzcvbVar, zzbzh zzbzhVar, zzcvj zzcvjVar, Object obj) throws Exception {
        zzdcp<?> zzdcpVarZza;
        final zzbbc zzbbcVarZza = this.zzfmy.zza(this.zzffn.zzbli, zzcvbVar.zzefr);
        zzbbcVarZza.zzat(zzcvbVar.zzdlf);
        zzbzhVar.zza(this.zzlk, zzbbcVarZza.getView());
        zzaxf zzaxfVar = new zzaxf();
        final zzbyh zzbyhVarZza = this.zzfyz.zza(new zzbkk(zzcvjVar, zzcvbVar, null), new zzbyk(new zzcjk(this.zzlk, this.zzfmy, this.zzffn, this.zzbkw, zzcvbVar, zzaxfVar, zzbbcVarZza), zzbbcVarZza));
        zzaxfVar.set(zzbyhVarZza);
        zzaei.zza(zzbbcVarZza, zzbyhVarZza.zzadl());
        zzbyhVarZza.zzaca().zza(new zzbmt(zzbbcVarZza) { // from class: com.google.android.gms.internal.ads.zzcjf
            private final zzbbc zzehf;

            {
                this.zzehf = zzbbcVarZza;
            }

            @Override // com.google.android.gms.internal.ads.zzbmt
            public final void onAdImpression() {
                zzbbc zzbbcVar = this.zzehf;
                if (zzbbcVar.zzyy() != null) {
                    zzbbcVar.zzyy().zzzw();
                }
            }
        }, zzawx.zzdwb);
        zzbyhVarZza.zzact().zzb(zzbbcVarZza, true);
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcsy)).booleanValue() && zzcvbVar.zzefr) {
            zzdcpVarZza = zzdcf.zzah(null);
        } else {
            zzbyhVarZza.zzact();
            zzdcpVarZza = zzbyt.zza(zzbbcVarZza, zzcvbVar.zzgiw.zzdhn, zzcvbVar.zzgiw.zzdhp);
        }
        return zzdcf.zzb(zzdcpVarZza, new zzczs(this, zzbbcVarZza, zzcvbVar, zzbyhVarZza) { // from class: com.google.android.gms.internal.ads.zzcji
            private final zzcvb zzfdo;
            private final zzbbc zzfoq;
            private final zzcje zzfze;
            private final zzbyh zzfzf;

            {
                this.zzfze = this;
                this.zzfoq = zzbbcVarZza;
                this.zzfdo = zzcvbVar;
                this.zzfzf = zzbyhVarZza;
            }

            @Override // com.google.android.gms.internal.ads.zzczs
            public final Object apply(Object obj2) {
                zzbbc zzbbcVar = this.zzfoq;
                zzcvb zzcvbVar2 = this.zzfdo;
                zzbyh zzbyhVar = this.zzfzf;
                if (zzcvbVar2.zzdlx) {
                    zzbbcVar.zzzl();
                }
                zzbbcVar.zzys();
                if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjv)).booleanValue()) {
                    com.google.android.gms.ads.internal.zzp.zzka();
                    zzaub.zza(zzbbcVar);
                }
                return zzbyhVar.zzadk();
            }
        }, this.zzfbm);
    }
}
