package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcfq implements zzcfk<zzbhs> {
    private final Executor zzfbm;
    private final zzcvk zzffn;
    private final zzbyr zzfmy;
    private final zzbho zzfwv;
    private final Context zzlk;

    public zzcfq(zzbho zzbhoVar, Context context, Executor executor, zzbyr zzbyrVar, zzcvk zzcvkVar) {
        this.zzlk = context;
        this.zzfwv = zzbhoVar;
        this.zzfbm = executor;
        this.zzfmy = zzbyrVar;
        this.zzffn = zzcvkVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final boolean zza(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        return (zzcvbVar.zzgiw == null || zzcvbVar.zzgiw.zzdhp == null) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final zzdcp<zzbhs> zzb(final zzcvj zzcvjVar, final zzcvb zzcvbVar) {
        return zzdcf.zzb(zzdcf.zzah(null), new zzdbq(this, zzcvjVar, zzcvbVar) { // from class: com.google.android.gms.internal.ads.zzcfp
            private final zzcvb zzfdo;
            private final zzcvj zzfno;
            private final zzcfq zzfwu;

            {
                this.zzfwu = this;
                this.zzfno = zzcvjVar;
                this.zzfdo = zzcvbVar;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfwu.zza(this.zzfno, this.zzfdo, obj);
            }
        }, this.zzfbm);
    }

    final /* synthetic */ zzdcp zza(zzcvj zzcvjVar, zzcvb zzcvbVar, Object obj) throws Exception {
        zztw zztwVarZza = zzcvo.zza(this.zzlk, zzcvbVar.zzgiy);
        final zzbbc zzbbcVarZzc = this.zzfmy.zzc(zztwVarZza);
        final zzbhk zzbhkVarZza = this.zzfwv.zza(new zzbkk(zzcvjVar, zzcvbVar, null), new zzbhj(zzbbcVarZzc.getView(), zzbbcVarZzc, zzcvo.zze(zztwVarZza), zzcvbVar.zzfcq));
        zzbhkVarZza.zzact().zzb(zzbbcVarZzc, false);
        zzbhkVarZza.zzaca().zza(new zzbmt(zzbbcVarZzc) { // from class: com.google.android.gms.internal.ads.zzcfs
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
        zzbhkVarZza.zzact();
        return zzdcf.zzb(zzbyt.zza(zzbbcVarZzc, zzcvbVar.zzgiw.zzdhn, zzcvbVar.zzgiw.zzdhp), new zzczs(zzbhkVarZza) { // from class: com.google.android.gms.internal.ads.zzcfr
            private final zzbhk zzfww;

            {
                this.zzfww = zzbhkVarZza;
            }

            @Override // com.google.android.gms.internal.ads.zzczs
            public final Object apply(Object obj2) {
                return this.zzfww.zzacs();
            }
        }, zzawx.zzdwb);
    }
}
