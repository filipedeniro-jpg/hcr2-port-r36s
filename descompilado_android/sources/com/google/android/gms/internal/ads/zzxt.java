package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.rewarded.OnAdMetadataChangedListener;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzxt extends zzwi {
    private final OnAdMetadataChangedListener zzcfg;

    public zzxt(OnAdMetadataChangedListener onAdMetadataChangedListener) {
        this.zzcfg = onAdMetadataChangedListener;
    }

    @Override // com.google.android.gms.internal.ads.zzwf
    public final void onAdMetadataChanged() throws RemoteException {
        OnAdMetadataChangedListener onAdMetadataChangedListener = this.zzcfg;
        if (onAdMetadataChangedListener != null) {
            onAdMetadataChangedListener.onAdMetadataChanged();
        }
    }
}
