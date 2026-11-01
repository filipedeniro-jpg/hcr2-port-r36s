package com.google.android.gms.ads;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzth;
import com.google.android.gms.internal.ads.zzww;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class InterstitialAd {
    private final zzww zzabg;

    public InterstitialAd(Context context) {
        this.zzabg = new zzww(context);
        Preconditions.checkNotNull(context, "Context cannot be null");
    }

    public final AdListener getAdListener() {
        return this.zzabg.getAdListener();
    }

    public final String getAdUnitId() {
        return this.zzabg.getAdUnitId();
    }

    public final boolean isLoaded() {
        return this.zzabg.isLoaded();
    }

    public final boolean isLoading() {
        return this.zzabg.isLoading();
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(AdRequest adRequest) {
        this.zzabg.zza(adRequest.zzda());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setAdListener(AdListener adListener) {
        this.zzabg.setAdListener(adListener);
        if (adListener != 0 && (adListener instanceof zzth)) {
            this.zzabg.zza((zzth) adListener);
        } else if (adListener == 0) {
            this.zzabg.zza((zzth) null);
        }
    }

    public final void setAdUnitId(String str) {
        this.zzabg.setAdUnitId(str);
    }

    public final String getMediationAdapterClassName() {
        return this.zzabg.getMediationAdapterClassName();
    }

    public final void show() {
        this.zzabg.show();
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        this.zzabg.setRewardedVideoAdListener(rewardedVideoAdListener);
    }

    public final void setAdMetadataListener(AdMetadataListener adMetadataListener) {
        this.zzabg.setAdMetadataListener(adMetadataListener);
    }

    public final Bundle getAdMetadata() {
        return this.zzabg.getAdMetadata();
    }

    public final void zzc(boolean z) {
        this.zzabg.zzc(true);
    }

    public final void setImmersiveMode(boolean z) {
        this.zzabg.setImmersiveMode(z);
    }
}
