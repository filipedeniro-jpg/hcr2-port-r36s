package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcgh extends zzala {
    private zzcfl<zzalj, zzcgu> zzfxf;
    private final /* synthetic */ zzcgg zzfxg;

    private zzcgh(zzcgg zzcggVar, zzcfl<zzalj, zzcgu> zzcflVar) {
        this.zzfxg = zzcggVar;
        this.zzfxf = zzcflVar;
    }

    @Override // com.google.android.gms.internal.ads.zzakx
    public final void zzaa(IObjectWrapper iObjectWrapper) throws RemoteException {
        this.zzfxg.view = (View) ObjectWrapper.unwrap(iObjectWrapper);
        ((zzcgu) this.zzfxf.zzfwt).onAdLoaded();
    }

    @Override // com.google.android.gms.internal.ads.zzakx
    public final void zzdg(String str) throws RemoteException {
        ((zzcgu) this.zzfxf.zzfwt).onAdFailedToLoad(0);
    }
}
