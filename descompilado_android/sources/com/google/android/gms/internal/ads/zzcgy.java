package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcgy implements zzcfk<zzbrc> {
    private final zzawv zzbkw;
    private final Executor zzfbm;
    private final zzcvk zzffn;
    private final zzbyr zzfmy;
    private final zzbry zzfxh;
    private final Context zzlk;

    public zzcgy(Context context, zzawv zzawvVar, zzcvk zzcvkVar, Executor executor, zzbry zzbryVar, zzbyr zzbyrVar) {
        this.zzlk = context;
        this.zzffn = zzcvkVar;
        this.zzfxh = zzbryVar;
        this.zzfbm = executor;
        this.zzbkw = zzawvVar;
        this.zzfmy = zzbyrVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final boolean zza(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        return (zzcvbVar.zzgiw == null || zzcvbVar.zzgiw.zzdhp == null) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final zzdcp<zzbrc> zzb(final zzcvj zzcvjVar, final zzcvb zzcvbVar) {
        final zzbzh zzbzhVar = new zzbzh();
        zzdcp<zzbrc> zzdcpVarZzb = zzdcf.zzb(zzdcf.zzah(null), new zzdbq(this, zzcvbVar, zzbzhVar, zzcvjVar) { // from class: com.google.android.gms.internal.ads.zzcgx
            private final zzcgy zzfxx;
            private final zzcvb zzfxy;
            private final zzbzh zzfxz;
            private final zzcvj zzfya;

            {
                this.zzfxx = this;
                this.zzfxy = zzcvbVar;
                this.zzfxz = zzbzhVar;
                this.zzfya = zzcvjVar;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfxx.zza(this.zzfxy, this.zzfxz, this.zzfya, obj);
            }
        }, this.zzfbm);
        zzbzhVar.getClass();
        zzdcpVarZzb.addListener(zzcha.zza(zzbzhVar), this.zzfbm);
        return zzdcpVarZzb;
    }

    final /* synthetic */ zzdcp zza(final zzcvb zzcvbVar, zzbzh zzbzhVar, zzcvj zzcvjVar, Object obj) throws Exception {
        zzdcp<?> zzdcpVarZza;
        final zzbbc zzbbcVarZza = this.zzfmy.zza(this.zzffn.zzbli, zzcvbVar.zzefr);
        zzbbcVarZza.zzat(zzcvbVar.zzdlf);
        zzbzhVar.zza(this.zzlk, zzbbcVarZza.getView());
        zzaxf zzaxfVar = new zzaxf();
        final zzbre zzbreVarZza = this.zzfxh.zza(new zzbkk(zzcvjVar, zzcvbVar, null), new zzbrd(new zzche(this.zzlk, this.zzbkw, zzaxfVar, zzcvbVar, zzbbcVarZza), zzbbcVarZza));
        zzaxfVar.set(zzbreVarZza);
        zzbreVarZza.zzaca().zza(new zzbmt(zzbbcVarZza) { // from class: com.google.android.gms.internal.ads.zzcgz
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
        zzbreVarZza.zzact().zzb(zzbbcVarZza, true);
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcsy)).booleanValue() && zzcvbVar.zzefr) {
            zzdcpVarZza = zzdcf.zzah(null);
        } else {
            zzbreVarZza.zzact();
            zzdcpVarZza = zzbyt.zza(zzbbcVarZza, zzcvbVar.zzgiw.zzdhn, zzcvbVar.zzgiw.zzdhp);
        }
        return zzdcf.zzb(zzdcpVarZza, new zzczs(this, zzbbcVarZza, zzcvbVar, zzbreVarZza) { // from class: com.google.android.gms.internal.ads.zzchc
            private final zzcvb zzfdo;
            private final zzbbc zzfoq;
            private final zzcgy zzfxx;
            private final zzbre zzfyc;

            {
                this.zzfxx = this;
                this.zzfoq = zzbbcVarZza;
                this.zzfdo = zzcvbVar;
                this.zzfyc = zzbreVarZza;
            }

            @Override // com.google.android.gms.internal.ads.zzczs
            public final Object apply(Object obj2) {
                zzbbc zzbbcVar = this.zzfoq;
                zzcvb zzcvbVar2 = this.zzfdo;
                zzbre zzbreVar = this.zzfyc;
                if (zzcvbVar2.zzdlx) {
                    zzbbcVar.zzzl();
                }
                zzbbcVar.zzys();
                if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjv)).booleanValue()) {
                    com.google.android.gms.ads.internal.zzp.zzka();
                    zzaub.zza(zzbbcVar);
                }
                return zzbreVar.zzade();
            }
        }, this.zzfbm);
    }
}
