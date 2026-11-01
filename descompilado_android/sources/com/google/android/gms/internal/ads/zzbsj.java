package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import androidx.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbsj implements zzbmg, zzbpu {

    @Nullable
    private final View view;
    private final zzarv zzbmt;
    private final zzars zzfeq;
    private final int zzfif;
    private String zzfil;
    private final Context zzlk;

    public zzbsj(zzars zzarsVar, Context context, zzarv zzarvVar, @Nullable View view, int i) {
        this.zzfeq = zzarsVar;
        this.zzlk = context;
        this.zzbmt = zzarvVar;
        this.view = view;
        this.zzfif = i;
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdLeftApplication() {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onRewardedVideoCompleted() {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onRewardedVideoStarted() {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdOpened() {
        View view = this.view;
        if (view != null && this.zzfil != null) {
            this.zzbmt.zzg(view.getContext(), this.zzfil);
        }
        this.zzfeq.zzaf(true);
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdClosed() {
        this.zzfeq.zzaf(false);
    }

    @Override // com.google.android.gms.internal.ads.zzbpu
    public final void zzagj() {
        this.zzfil = this.zzbmt.zzac(this.zzlk);
        String strValueOf = String.valueOf(this.zzfil);
        String str = this.zzfif == 7 ? "/Rewarded" : "/Interstitial";
        this.zzfil = str.length() != 0 ? strValueOf.concat(str) : new String(strValueOf);
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    @ParametersAreNonnullByDefault
    public final void zzb(zzape zzapeVar, String str, String str2) {
        if (this.zzbmt.zzaa(this.zzlk)) {
            try {
                this.zzbmt.zza(this.zzlk, this.zzbmt.zzaf(this.zzlk), this.zzfeq.getAdUnitId(), zzapeVar.getType(), zzapeVar.getAmount());
            } catch (RemoteException e) {
                zzatm.zzd("Remote Exception to get reward item.", e);
            }
        }
    }
}
