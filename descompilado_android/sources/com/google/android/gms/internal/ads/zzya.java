package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.formats.ShouldDelayBannerRenderingListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzya extends zzacj {
    private final ShouldDelayBannerRenderingListener zzcfi;

    public zzya(ShouldDelayBannerRenderingListener shouldDelayBannerRenderingListener) {
        this.zzcfi = shouldDelayBannerRenderingListener;
    }

    @Override // com.google.android.gms.internal.ads.zzack
    public final boolean zzq(IObjectWrapper iObjectWrapper) throws RemoteException {
        return this.zzcfi.zzb((Runnable) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
