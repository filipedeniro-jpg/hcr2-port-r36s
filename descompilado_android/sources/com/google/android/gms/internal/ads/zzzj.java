package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzzj implements CustomRenderedAd {
    private final zzzm zzcui;

    public zzzj(zzzm zzzmVar) {
        this.zzcui = zzzmVar;
    }

    @Override // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final String getBaseUrl() {
        try {
            return this.zzcui.zzpz();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final String getContent() {
        try {
            return this.zzcui.getContent();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final void onAdRendered(View view) {
        try {
            this.zzcui.zzr(view != null ? ObjectWrapper.wrap(view) : null);
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final void recordClick() {
        try {
            this.zzcui.recordClick();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.doubleclick.CustomRenderedAd
    public final void recordImpression() {
        try {
            this.zzcui.recordImpression();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }
}
