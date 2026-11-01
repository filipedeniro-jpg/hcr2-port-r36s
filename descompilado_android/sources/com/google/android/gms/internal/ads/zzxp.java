package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzxp extends zzaqj {
    @Override // com.google.android.gms.internal.ads.zzaqg
    public final String getMediationAdapterClassName() throws RemoteException {
        return "";
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final boolean isLoaded() throws RemoteException {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final void zza(IObjectWrapper iObjectWrapper, boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final void zza(zzaql zzaqlVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final void zza(zzaqt zzaqtVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final void zza(zzarb zzarbVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final void zza(zzwf zzwfVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final void zzl(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    @Nullable
    public final zzaqf zzpg() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final void zza(zztp zztpVar, final zzaqo zzaqoVar) throws RemoteException {
        zzawo.zzes("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzawe.zzzb.post(new Runnable(zzaqoVar) { // from class: com.google.android.gms.internal.ads.zzxs
            private final zzaqo zzcff;

            {
                this.zzcff = zzaqoVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzaqo zzaqoVar2 = this.zzcff;
                if (zzaqoVar2 != null) {
                    try {
                        zzaqoVar2.onRewardedAdFailedToLoad(1);
                    } catch (RemoteException e) {
                        zzawo.zze("#007 Could not call remote method.", e);
                    }
                }
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzaqg
    public final Bundle getAdMetadata() throws RemoteException {
        return new Bundle();
    }
}
