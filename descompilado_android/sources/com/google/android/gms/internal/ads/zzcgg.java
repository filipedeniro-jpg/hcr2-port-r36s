package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcgg implements zzcfn<zzbhy, zzalj, zzcgu> {
    private View view;
    private final zzbit zzfwx;
    private final Context zzlk;

    public zzcgg(Context context, zzbit zzbitVar) {
        this.zzlk = context;
        this.zzfwx = zzbitVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfn
    public final void zza(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl<zzalj, zzcgu> zzcflVar) throws zzcvr {
        try {
            zzcflVar.zzdde.zzdh(zzcvbVar.zzdek);
            zzcflVar.zzdde.zza(zzcvbVar.zzehw, zzcvbVar.zzgiz.toString(), zzcvjVar.zzgjo.zzffn.zzgjs, ObjectWrapper.wrap(this.zzlk), new zzcgh(this, zzcflVar), (zzajj) zzcflVar.zzfwt, zzcvjVar.zzgjo.zzffn.zzbli);
        } catch (RemoteException e) {
            throw new zzcvr(e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcfn
    public final /* synthetic */ zzbhy zzb(zzcvj zzcvjVar, zzcvb zzcvbVar, final zzcfl zzcflVar) throws zzcvr, zzcin {
        zzbhx zzbhxVarZza = this.zzfwx.zza(new zzbkk(zzcvjVar, zzcvbVar, zzcflVar.zzfev), new zzbib(this.view, null, new zzbjr(zzcflVar) { // from class: com.google.android.gms.internal.ads.zzcgf
            private final zzcfl zzfxe;

            {
                this.zzfxe = zzcflVar;
            }

            @Override // com.google.android.gms.internal.ads.zzbjr
            public final zzwk getVideoController() {
                return zzcgg.zza(this.zzfxe);
            }
        }, zzcvbVar.zzgiy.get(0)));
        zzbhxVarZza.zzacw().zzq(this.view);
        ((zzcgu) zzcflVar.zzfwt).zza(zzbhxVarZza.zzacc());
        return zzbhxVarZza.zzacv();
    }

    static final /* synthetic */ zzwk zza(zzcfl zzcflVar) throws zzcvr {
        try {
            return ((zzalj) zzcflVar.zzdde).getVideoController();
        } catch (RemoteException e) {
            throw new zzcvr(e);
        }
    }
}
