package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcmh extends zzaso {
    private zzbds zzgcx;

    public zzcmh(zzbds zzbdsVar) {
        this.zzgcx = zzbdsVar;
    }

    @Override // com.google.android.gms.internal.ads.zzasp
    public final void zza(IObjectWrapper iObjectWrapper, zzasr zzasrVar, zzasn zzasnVar) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        String str = zzasrVar.zzccm;
        String str2 = zzasrVar.zzbne;
        zzdcf.zza(this.zzgcx.zzabo().zzf(new zzblu.zza().zzbx(context).zza(new zzcvm().zzgf(str).zzg(new zzts().zznx()).zzd(zzasrVar.zzdpn).zzamz()).zzafu()).zza(new zzcmm(new zzcmm.zza().zzge(str2))).zzf(new zzbox.zza().zzagi()).zzado().zzadn(), new zzcmk(this, zzasnVar), this.zzgcx.zzabb());
    }
}
