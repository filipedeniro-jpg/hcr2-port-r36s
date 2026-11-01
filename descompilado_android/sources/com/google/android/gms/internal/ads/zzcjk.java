package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcjk implements zzbse {
    private final zzawv zzbkw;
    private final zzcvb zzfef;
    private final zzcvk zzffn;
    private final zzbyr zzfmy;
    private final zzdcp<zzbyh> zzfyd;
    private final zzbbc zzfzg;
    private final Context zzlk;

    private zzcjk(Context context, zzbyr zzbyrVar, zzcvk zzcvkVar, zzawv zzawvVar, zzcvb zzcvbVar, zzdcp<zzbyh> zzdcpVar, zzbbc zzbbcVar) {
        this.zzlk = context;
        this.zzfmy = zzbyrVar;
        this.zzffn = zzcvkVar;
        this.zzbkw = zzawvVar;
        this.zzfef = zzcvbVar;
        this.zzfyd = zzdcpVar;
        this.zzfzg = zzbbcVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbse
    public final void zza(boolean z, Context context) {
        zzbbc zzbbcVar;
        zzbbc zzbbcVar2;
        zzbyh zzbyhVar = (zzbyh) zzdcf.zzc(this.zzfyd);
        try {
            zzcvb zzcvbVar = this.zzfef;
            if (!this.zzfzg.zzzn()) {
                zzbbcVar2 = this.zzfzg;
            } else {
                if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcjz)).booleanValue()) {
                    zzbbcVar2 = this.zzfzg;
                } else {
                    final zzbbc zzbbcVarZzc = this.zzfmy.zzc(this.zzffn.zzbli);
                    zzaei.zza(zzbbcVarZzc, zzbyhVar.zzadl());
                    final zzbzh zzbzhVar = new zzbzh();
                    zzbzhVar.zza(this.zzlk, zzbbcVarZzc.getView());
                    zzbyhVar.zzact().zzb(zzbbcVarZzc, true);
                    zzbbcVarZzc.zzyy().zza(new zzbcp(zzbzhVar, zzbbcVarZzc) { // from class: com.google.android.gms.internal.ads.zzcjj
                        private final zzbbc zzfoq;
                        private final zzbzh zzfyb;

                        {
                            this.zzfyb = zzbzhVar;
                            this.zzfoq = zzbbcVarZzc;
                        }

                        @Override // com.google.android.gms.internal.ads.zzbcp
                        public final void zzab(boolean z2) {
                            zzbzh zzbzhVar2 = this.zzfyb;
                            zzbbc zzbbcVar3 = this.zzfoq;
                            zzbzhVar2.zzaji();
                            zzbbcVar3.zzys();
                            zzbbcVar3.zzyy().zzzw();
                        }
                    });
                    zzbcm zzbcmVarZzyy = zzbbcVarZzc.zzyy();
                    zzbbcVarZzc.getClass();
                    zzbcmVarZzyy.zza(zzcjm.zzq(zzbbcVarZzc));
                    zzbbcVarZzc.zzb(zzcvbVar.zzgiw.zzdhn, zzcvbVar.zzgiw.zzdhp, null);
                    zzbbcVar = zzbbcVarZzc;
                }
                zzbbcVar.zzaq(true);
                com.google.android.gms.ads.internal.zzp.zzjy();
                com.google.android.gms.ads.internal.zzi zziVar = new com.google.android.gms.ads.internal.zzi(false, zzatv.zzba(this.zzlk), false, 0.0f, -1, z, this.zzfef.zzgjd, this.zzfef.zzblg);
                com.google.android.gms.ads.internal.zzp.zzjx();
                com.google.android.gms.ads.internal.overlay.zzl.zza(context, new AdOverlayInfoParcel((zzth) null, zzbyhVar.zzadg(), (com.google.android.gms.ads.internal.overlay.zzv) null, zzbbcVar, this.zzfef.zzgje, this.zzbkw, this.zzfef.zzdkj, zziVar, this.zzfef.zzgiw.zzdhn, this.zzfef.zzgiw.zzdhp), true);
            }
            zzbbcVar = zzbbcVar2;
            zzbbcVar.zzaq(true);
            com.google.android.gms.ads.internal.zzp.zzjy();
            com.google.android.gms.ads.internal.zzi zziVar2 = new com.google.android.gms.ads.internal.zzi(false, zzatv.zzba(this.zzlk), false, 0.0f, -1, z, this.zzfef.zzgjd, this.zzfef.zzblg);
            com.google.android.gms.ads.internal.zzp.zzjx();
            com.google.android.gms.ads.internal.overlay.zzl.zza(context, new AdOverlayInfoParcel((zzth) null, zzbyhVar.zzadg(), (com.google.android.gms.ads.internal.overlay.zzv) null, zzbbcVar, this.zzfef.zzgje, this.zzbkw, this.zzfef.zzdkj, zziVar2, this.zzfef.zzgiw.zzdhn, this.zzfef.zzgiw.zzdhp), true);
        } catch (zzbbp e) {
            zzawo.zzc("", e);
        }
    }
}
