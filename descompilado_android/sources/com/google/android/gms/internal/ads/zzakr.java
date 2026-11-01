package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzakr implements Runnable {
    private final /* synthetic */ zzakk zzdeb;

    zzakr(zzakk zzakkVar) {
        this.zzdeb = zzakkVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzdeb.zzddq.onAdClosed();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }
}
