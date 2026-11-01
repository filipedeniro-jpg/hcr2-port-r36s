package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzchh implements zzcfn<zzbrc, zzalj, zzcgu> {
    private final zzbry zzfxh;
    private final Context zzlk;

    public zzchh(Context context, zzbry zzbryVar) {
        this.zzlk = context;
        this.zzfxh = zzbryVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfn
    public final void zza(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl<zzalj, zzcgu> zzcflVar) throws zzcvr {
        try {
            zzcflVar.zzdde.zzdh(zzcvbVar.zzdek);
            zzcflVar.zzdde.zza(zzcvbVar.zzehw, zzcvbVar.zzgiz.toString(), zzcvjVar.zzgjo.zzffn.zzgjs, ObjectWrapper.wrap(this.zzlk), new zzchm(this, zzcflVar), (zzajj) zzcflVar.zzfwt);
        } catch (RemoteException e) {
            throw new zzcvr(e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcfn
    public final /* synthetic */ zzbrc zzb(zzcvj zzcvjVar, zzcvb zzcvbVar, final zzcfl zzcflVar) throws zzcvr, zzcin {
        final zzcfm zzcfmVar = new zzcfm(zzcvbVar);
        zzbre zzbreVarZza = this.zzfxh.zza(new zzbkk(zzcvjVar, zzcvbVar, zzcflVar.zzfev), new zzbrd(new zzbse(zzcflVar, zzcfmVar) { // from class: com.google.android.gms.internal.ads.zzchk
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
                    if (((zzalj) zzcflVar2.zzdde).zzac(ObjectWrapper.wrap(context))) {
                        zzcfmVar2.zzako();
                    } else {
                        zzatm.zzeu("Cannot show interstitial.");
                    }
                } catch (RemoteException e) {
                    zzatm.zzd("Cannot show interstitial.", e);
                }
            }
        }));
        zzcfmVar.zza(zzbreVarZza.zzaca());
        ((zzcgu) zzcflVar.zzfwt).zza(zzbreVarZza.zzacc());
        return zzbreVarZza.zzade();
    }
}
