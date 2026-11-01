package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzciw implements zzcfn<zzbyf, zzalj, zzcgu> {
    private final zzbym zzfyz;
    private final Context zzlk;

    public zzciw(Context context, zzbym zzbymVar) {
        this.zzlk = context;
        this.zzfyz = zzbymVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfn
    public final void zza(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl<zzalj, zzcgu> zzcflVar) throws zzcvr {
        try {
            zzcflVar.zzdde.zzdh(zzcvbVar.zzdek);
            zzcflVar.zzdde.zza(zzcvbVar.zzehw, zzcvbVar.zzgiz.toString(), zzcvjVar.zzgjo.zzffn.zzgjs, ObjectWrapper.wrap(this.zzlk), new zzcix(this, zzcflVar), (zzajj) zzcflVar.zzfwt);
        } catch (RemoteException e) {
            zzdof.zzj(e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcfn
    public final /* synthetic */ zzbyf zzb(zzcvj zzcvjVar, zzcvb zzcvbVar, final zzcfl zzcflVar) throws zzcvr, zzcin {
        final zzcfm zzcfmVar = new zzcfm(zzcvbVar);
        zzbyh zzbyhVarZza = this.zzfyz.zza(new zzbkk(zzcvjVar, zzcvbVar, zzcflVar.zzfev), new zzbyk(new zzbse(zzcflVar, zzcfmVar) { // from class: com.google.android.gms.internal.ads.zzciv
            private final zzcfl zzfxe;
            private final zzcfm zzfyf;

            {
                this.zzfxe = zzcflVar;
                this.zzfyf = zzcfmVar;
            }

            @Override // com.google.android.gms.internal.ads.zzbse
            public final void zza(boolean z, Context context) {
                zzcfl zzcflVar2 = this.zzfxe;
                zzcfm zzcfmVar2 = this.zzfyf;
                try {
                    if (((zzalj) zzcflVar2.zzdde).zzad(ObjectWrapper.wrap(context))) {
                        zzcfmVar2.zzako();
                    } else {
                        zzatm.zzeu("Can't show rewarded video.");
                    }
                } catch (RemoteException e) {
                    zzatm.zzd("Can't show rewarded video.", e);
                }
            }
        }));
        zzcfmVar.zza(zzbyhVarZza.zzaca());
        ((zzcgu) zzcflVar.zzfwt).zza(zzbyhVarZza.zzadm());
        return zzbyhVarZza.zzadk();
    }
}
