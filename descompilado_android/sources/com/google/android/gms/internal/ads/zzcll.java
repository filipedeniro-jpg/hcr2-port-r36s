package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.widget.RelativeLayout;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcll implements zzdcg<zzbhs> {
    private final /* synthetic */ zzbho zzgbp;
    private final /* synthetic */ zzclk zzgbq;

    zzcll(zzclk zzclkVar, zzbho zzbhoVar) {
        this.zzgbq = zzclkVar;
        this.zzgbp = zzbhoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        synchronized (this.zzgbq) {
            zzclk.zza(this.zzgbq, (zzdcp) null);
            this.zzgbp.zzacj().onAdFailedToLoad(zzcce.zzd(th));
            zzcvt.zzc(th, "AppOpenAdManagerShim.onFailure");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(zzbhs zzbhsVar) {
        zzbhs zzbhsVar2 = zzbhsVar;
        synchronized (this.zzgbq) {
            zzclk.zza(this.zzgbq, (zzdcp) null);
            if (this.zzgbq.zzgbn != null) {
                this.zzgbq.zzgbn.destroy();
            }
            this.zzgbq.zzgbn = zzbhsVar2;
            this.zzgbq.zzfcw.removeAllViews();
            this.zzgbq.zzfcw.addView(zzbhsVar2.zzaer(), com.google.android.gms.ads.internal.zzp.zzka().zzvn());
            com.google.android.gms.ads.internal.overlay.zzo zzoVarZza = this.zzgbq.zza(zzbhsVar2);
            zzclk zzclkVar = this.zzgbq;
            RelativeLayout.LayoutParams layoutParamsZzb = zzclk.zzb(zzbhsVar2);
            zzoVarZza.zzae(zzbhsVar2.zzaes());
            this.zzgbq.zzfcw.addView(zzoVarZza, layoutParamsZzb);
            this.zzgbq.zzc(zzbhsVar2);
            this.zzgbq.zzfcw.setMinimumHeight(this.zzgbq.zzala().heightPixels);
            this.zzgbq.zzfcw.setMinimumWidth(this.zzgbq.zzala().widthPixels);
            try {
                this.zzgbq.zzgbk.zza(new zzbhu(zzbhsVar2, this.zzgbq));
            } catch (RemoteException e) {
                zzatm.zzc("RemoteException when onAppOpenAdLoaded is called", e);
            }
            zzbhsVar2.zzaew();
        }
    }
}
