package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.AdMetadataListener;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcul implements zzcma<zzbyf> {
    private final Executor zzfbm;
    private final zzcvi zzfgh;

    @GuardedBy("this")
    private final zzcvm zzgbg;
    private final zzbds zzgcx;
    private final Context zzghr;
    private final zzcto zzghs;
    private final zzctn<zzbym, zzbyf> zzght;

    @GuardedBy("this")
    private zzdcp<zzbyf> zzghu;

    public zzcul(Context context, Executor executor, zzbds zzbdsVar, zzctn<zzbym, zzbyf> zzctnVar, zzcto zzctoVar, zzcvm zzcvmVar, zzcvi zzcviVar) {
        this.zzghr = context;
        this.zzfbm = executor;
        this.zzgcx = zzbdsVar;
        this.zzght = zzctnVar;
        this.zzghs = zzctoVar;
        this.zzgbg = zzcvmVar;
        this.zzfgh = zzcviVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcma
    public final boolean isLoading() {
        zzdcp<zzbyf> zzdcpVar = this.zzghu;
        return (zzdcpVar == null || zzdcpVar.isDone()) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzcma
    public final boolean zza(zztp zztpVar, String str, zzclz zzclzVar, zzcmc<? super zzbyf> zzcmcVar) throws RemoteException {
        zzapu zzapuVar = new zzapu(zztpVar, str);
        String str2 = zzclzVar instanceof zzcui ? ((zzcui) zzclzVar).zzgho : null;
        if (zzapuVar.zzccm == null) {
            zzatm.zzes("Ad unit ID should not be null for rewarded video ad.");
            this.zzfbm.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzcuk
                private final zzcul zzghq;

                {
                    this.zzghq = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzghq.zzamr();
                }
            });
            return false;
        }
        zzdcp<zzbyf> zzdcpVar = this.zzghu;
        if (zzdcpVar != null && !zzdcpVar.isDone()) {
            return false;
        }
        zzcvt.zze(this.zzghr, zzapuVar.zzdii.zzcbq);
        zzcvk zzcvkVarZzamz = this.zzgbg.zzgf(zzapuVar.zzccm).zzd(zztw.zzny()).zzg(zzapuVar.zzdii).zzamz();
        zzbox.zza zzaVar = new zzbox.zza();
        zzaVar.zza((zzbmg) this.zzghs, this.zzfbm);
        zzaVar.zza((zzbnm) this.zzghs, this.zzfbm);
        zzaVar.zza((zzbml) this.zzghs, this.zzfbm);
        zzaVar.zza((AdMetadataListener) this.zzghs, this.zzfbm);
        zzaVar.zza((zzbmp) this.zzghs, this.zzfbm);
        this.zzghu = this.zzght.zza(this.zzgcx.zzabm().zze(new zzblu.zza().zzbx(this.zzghr).zza(zzcvkVarZzamz).zzfn(str2).zza(this.zzfgh).zzafu()).zze(zzaVar.zzagi()), this.zzfbm);
        zzdcf.zza(this.zzghu, new zzcun(this, zzcmcVar), this.zzfbm);
        return true;
    }

    final /* synthetic */ void zzamr() {
        this.zzghs.onAdFailedToLoad(1);
    }
}
