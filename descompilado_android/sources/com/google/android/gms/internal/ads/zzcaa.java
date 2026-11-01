package com.google.android.gms.internal.ads;

import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcaa implements zzbml, zzbmt, zzbnm, zzboi, zzth {
    private final zzrz zzfqt;

    @GuardedBy("this")
    private boolean zzfqu = false;

    @GuardedBy("this")
    private boolean zzfqv = false;

    public zzcaa(zzrz zzrzVar, @Nullable zzctp zzctpVar) {
        this.zzfqt = zzrzVar;
        zzrzVar.zza(zzsb.zza.zzb.AD_REQUEST);
        if (zzctpVar == null || !zzctpVar.zzgfs) {
            return;
        }
        zzrzVar.zza(zzsb.zza.zzb.REQUEST_IS_PREFETCH);
    }

    @Override // com.google.android.gms.internal.ads.zzboi
    public final void zzb(zzaok zzaokVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzboi
    public final void zza(final zzcvj zzcvjVar) {
        this.zzfqt.zza(new zzry(zzcvjVar) { // from class: com.google.android.gms.internal.ads.zzbzz
            private final zzcvj zzfgu;

            {
                this.zzfgu = zzcvjVar;
            }

            @Override // com.google.android.gms.internal.ads.zzry
            public final void zza(zztd zztdVar) {
                zzcvj zzcvjVar2 = this.zzfgu;
                zztdVar.zzcal.zzbzh.zzbzx = zzcvjVar2.zzgjp.zzgjm.zzbzx;
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzbnm
    public final void onAdLoaded() {
        this.zzfqt.zza(zzsb.zza.zzb.AD_LOADED);
    }

    @Override // com.google.android.gms.internal.ads.zzbml
    public final void onAdFailedToLoad(int i) {
        switch (i) {
            case 1:
                this.zzfqt.zza(zzsb.zza.zzb.AD_FAILED_TO_LOAD_INVALID_REQUEST);
                break;
            case 2:
                this.zzfqt.zza(zzsb.zza.zzb.AD_FAILED_TO_LOAD_NETWORK_ERROR);
                break;
            case 3:
                this.zzfqt.zza(zzsb.zza.zzb.AD_FAILED_TO_LOAD_NO_FILL);
                break;
            case 4:
                this.zzfqt.zza(zzsb.zza.zzb.AD_FAILED_TO_LOAD_TIMEOUT);
                break;
            case 5:
                this.zzfqt.zza(zzsb.zza.zzb.AD_FAILED_TO_LOAD_CANCELLED);
                break;
            case 6:
                this.zzfqt.zza(zzsb.zza.zzb.AD_FAILED_TO_LOAD_NO_ERROR);
                break;
            case 7:
                this.zzfqt.zza(zzsb.zza.zzb.AD_FAILED_TO_LOAD_NOT_FOUND);
                break;
            default:
                this.zzfqt.zza(zzsb.zza.zzb.AD_FAILED_TO_LOAD);
                break;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbmt
    public final synchronized void onAdImpression() {
        this.zzfqt.zza(zzsb.zza.zzb.AD_IMPRESSION);
    }

    @Override // com.google.android.gms.internal.ads.zzth
    public final synchronized void onAdClicked() {
        if (!this.zzfqv) {
            this.zzfqt.zza(zzsb.zza.zzb.AD_FIRST_CLICK);
            this.zzfqv = true;
        } else {
            this.zzfqt.zza(zzsb.zza.zzb.AD_SUBSEQUENT_CLICK);
        }
    }
}
