package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcjo implements zzcfn<zzbyf, zzcvs, zzcgt> {
    private final Executor zzfbm;
    private final zzbym zzfyz;
    private final Context zzlk;

    public zzcjo(Context context, Executor executor, zzbym zzbymVar) {
        this.zzlk = context;
        this.zzfbm = executor;
        this.zzfyz = zzbymVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfn
    public final void zza(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl<zzcvs, zzcgt> zzcflVar) throws zzcvr {
        if (!zzcflVar.zzdde.isInitialized()) {
            ((zzcgt) zzcflVar.zzfwt).zza(new zzcjq(this, zzcvjVar, zzcvbVar, zzcflVar));
            zzcflVar.zzdde.zza(this.zzlk, zzcvjVar.zzgjo.zzffn.zzgjs, (String) null, (zzapz) zzcflVar.zzfwt, zzcvbVar.zzgiz.toString());
            return;
        }
        zzc(zzcvjVar, zzcvbVar, zzcflVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzc(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl<zzcvs, zzcgt> zzcflVar) {
        try {
            zzcflVar.zzdde.zza(zzcvjVar.zzgjo.zzffn.zzgjs, zzcvbVar.zzgiz.toString());
        } catch (Exception e) {
            String strValueOf = String.valueOf(zzcflVar.zzfev);
            zzatm.zzd(strValueOf.length() != 0 ? "Fail to load ad from adapter ".concat(strValueOf) : new String("Fail to load ad from adapter "), e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzcfn
    public final /* synthetic */ zzbyf zzb(zzcvj zzcvjVar, zzcvb zzcvbVar, final zzcfl zzcflVar) throws zzcvr, zzcin {
        zzbyh zzbyhVarZza = this.zzfyz.zza(new zzbkk(zzcvjVar, zzcvbVar, zzcflVar.zzfev), new zzbyk(new zzbse(zzcflVar) { // from class: com.google.android.gms.internal.ads.zzcjn
            private final zzcfl zzfxe;

            {
                this.zzfxe = zzcflVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.internal.ads.zzbse
            public final void zza(boolean z, Context context) {
                zzcfl zzcflVar2 = this.zzfxe;
                try {
                    ((zzcvs) zzcflVar2.zzdde).setImmersiveMode(z);
                    ((zzcvs) zzcflVar2.zzdde).showVideo();
                } catch (zzcvr e) {
                    zzatm.zzd("Cannot show rewarded video.", e);
                }
            }
        }));
        zzbyhVarZza.zzabx().zza(new zzbgh((zzcvs) zzcflVar.zzdde), this.zzfbm);
        zzbnb zzbnbVarZzaby = zzbyhVarZza.zzaby();
        zzbmf zzbmfVarZzabz = zzbyhVarZza.zzabz();
        ((zzcgt) zzcflVar.zzfwt).zza(new zzcjs(this, zzbyhVarZza.zzadf(), zzbmfVarZzabz, zzbnbVarZzaby, zzbyhVarZza.zzadl()));
        return zzbyhVarZza.zzadk();
    }
}
