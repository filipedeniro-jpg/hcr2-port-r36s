package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzcuc implements zzcug {
    static final zzcug zzghn = new zzcuc();

    private zzcuc() {
    }

    @Override // com.google.android.gms.internal.ads.zzcug
    public final void zzt(Object obj) throws RemoteException {
        ((zzaql) obj).onRewardedAdClosed();
    }
}
