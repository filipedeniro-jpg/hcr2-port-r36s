package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.rewarded.OnAdMetadataChangedListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.rewarded.ServerSideVerificationOptions;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzaqv {
    private final zzaqg zzdnr;
    private final Context zzzc;

    public zzaqv(Context context, String str) {
        this.zzzc = context.getApplicationContext();
        this.zzdnr = zzuo.zzog().zzc(context, str, new zzaje());
    }

    public final void zza(zzws zzwsVar, RewardedAdLoadCallback rewardedAdLoadCallback) {
        try {
            this.zzdnr.zza(zztu.zza(this.zzzc, zzwsVar), new zzaqy(rewardedAdLoadCallback));
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    public final String getMediationAdapterClassName() {
        try {
            return this.zzdnr.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            return "";
        }
    }

    public final void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions) {
        try {
            this.zzdnr.zza(new zzarb(serverSideVerificationOptions));
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    public final void setOnAdMetadataChangedListener(OnAdMetadataChangedListener onAdMetadataChangedListener) {
        try {
            this.zzdnr.zza(new zzxt(onAdMetadataChangedListener));
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    public final Bundle getAdMetadata() {
        try {
            return this.zzdnr.getAdMetadata();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            return new Bundle();
        }
    }

    public final boolean isLoaded() {
        try {
            return this.zzdnr.isLoaded();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            return false;
        }
    }

    public final void show(Activity activity, RewardedAdCallback rewardedAdCallback) {
        try {
            this.zzdnr.zza(new zzaqx(rewardedAdCallback));
            this.zzdnr.zzl(ObjectWrapper.wrap(activity));
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    public final void show(Activity activity, RewardedAdCallback rewardedAdCallback, boolean z) {
        try {
            this.zzdnr.zza(new zzaqx(rewardedAdCallback));
            this.zzdnr.zza(ObjectWrapper.wrap(activity), z);
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Nullable
    public final RewardItem getRewardItem() {
        try {
            zzaqf zzaqfVarZzpg = this.zzdnr.zzpg();
            if (zzaqfVarZzpg == null) {
                return null;
            }
            return new zzaqu(zzaqfVarZzpg);
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            return null;
        }
    }
}
