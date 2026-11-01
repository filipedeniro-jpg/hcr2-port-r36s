package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import java.io.InputStream;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcdo extends zzcdm {
    public zzcdo(Context context) {
        this.zzfup = new zzans(context, com.google.android.gms.ads.internal.zzp.zzkm().zzvz(), this, this);
    }

    public final zzdcp<InputStream> zzg(zzaok zzaokVar) {
        synchronized (this.mLock) {
            if (this.zzfum) {
                return this.zzdaz;
            }
            this.zzfum = true;
            this.zzfuo = zzaokVar;
            this.zzfup.checkAvailabilityAndConnect();
            this.zzdaz.addListener(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzcdn
                private final zzcdo zzfuq;

                {
                    this.zzfuq = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzfuq.zzakf();
                }
            }, zzawx.zzdwb);
            return this.zzdaz;
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.zzfun) {
                this.zzfun = true;
                try {
                    this.zzfup.zzsw().zzb(this.zzfuo, new zzcdl(this));
                } catch (RemoteException | IllegalArgumentException unused) {
                    this.zzdaz.setException(new zzcdr(0));
                } catch (Throwable th) {
                    com.google.android.gms.ads.internal.zzp.zzkc().zza(th, "RemoteSignalsClientTask.onConnected");
                    this.zzdaz.setException(new zzcdr(0));
                }
            }
        }
    }
}
