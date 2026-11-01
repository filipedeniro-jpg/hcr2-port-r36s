package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzajx extends zzwj {
    private final Object lock = new Object();
    private volatile zzwl zzddd;

    @Override // com.google.android.gms.internal.ads.zzwk
    public final void play() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final void pause() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final void stop() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final void mute(boolean z) throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final boolean isMuted() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final int getPlaybackState() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final float zzot() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final float zzou() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final void zza(zzwl zzwlVar) throws RemoteException {
        synchronized (this.lock) {
            this.zzddd = zzwlVar;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final zzwl zzov() throws RemoteException {
        zzwl zzwlVar;
        synchronized (this.lock) {
            zzwlVar = this.zzddd;
        }
        return zzwlVar;
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final float getAspectRatio() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final boolean isCustomControlsEnabled() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final boolean isClickToExpandEnabled() throws RemoteException {
        throw new RemoteException();
    }
}
