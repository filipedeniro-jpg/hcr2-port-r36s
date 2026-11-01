package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzcuf implements zzcug {
    static final zzcug zzghn = new zzcuf();

    private zzcuf() {
    }

    @Override // com.google.android.gms.internal.ads.zzcug
    public final void zzt(Object obj) throws RemoteException {
        ((zzapo) obj).onRewardedVideoAdClosed();
    }
}
