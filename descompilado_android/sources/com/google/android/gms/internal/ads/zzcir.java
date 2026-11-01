package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcir implements zzcfn<zzbyf, zzcvs, zzcgu> {
    private final Executor zzfbm;
    private final zzbym zzfyz;
    private final Context zzlk;

    public zzcir(Context context, Executor executor, zzbym zzbymVar) {
        this.zzlk = context;
        this.zzfbm = executor;
        this.zzfyz = zzbymVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfn
    public final void zza(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl<zzcvs, zzcgu> zzcflVar) throws zzcvr {
        try {
            zzcflVar.zzdde.zzb(this.zzlk, zzcvjVar.zzgjo.zzffn.zzgjs, zzcvbVar.zzgiz.toString(), (zzajj) zzcflVar.zzfwt);
        } catch (Exception e) {
            String strValueOf = String.valueOf(zzcflVar.zzfev);
            zzatm.zzd(strValueOf.length() != 0 ? "Fail to load ad from adapter ".concat(strValueOf) : new String("Fail to load ad from adapter "), e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzcfn
    public final /* synthetic */ zzbyf zzb(zzcvj zzcvjVar, zzcvb zzcvbVar, final zzcfl zzcflVar) throws zzcvr, zzcin {
        zzbyh zzbyhVarZza = this.zzfyz.zza(new zzbkk(zzcvjVar, zzcvbVar, zzcflVar.zzfev), new zzbyk(new zzbse(zzcflVar) { // from class: com.google.android.gms.internal.ads.zzciu
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
                    ((zzcvs) zzcflVar2.zzdde).zzbz(context);
                } catch (zzcvr e) {
                    zzatm.zzd("Cannot show rewarded .", e);
                }
            }
        }));
        zzbyhVarZza.zzabx().zza(new zzbgh((zzcvs) zzcflVar.zzdde), this.zzfbm);
        ((zzcgu) zzcflVar.zzfwt).zza(zzbyhVarZza.zzadm());
        return zzbyhVarZza.zzadk();
    }
}
