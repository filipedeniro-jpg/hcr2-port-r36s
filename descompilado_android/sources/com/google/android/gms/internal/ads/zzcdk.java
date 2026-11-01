package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import java.io.InputStream;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcdk extends zzcdm {
    public zzcdk(Context context) {
        this.zzfup = new zzans(context, com.google.android.gms.ads.internal.zzp.zzkm().zzvz(), this, this);
    }

    public final zzdcp<InputStream> zzf(zzaok zzaokVar) {
        synchronized (this.mLock) {
            if (this.zzfum) {
                return this.zzdaz;
            }
            this.zzfum = true;
            this.zzfuo = zzaokVar;
            this.zzfup.checkAvailabilityAndConnect();
            this.zzdaz.addListener(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzcdj
                private final zzcdk zzfuk;

                {
                    this.zzfuk = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzfuk.zzakf();
                }
            }, zzawx.zzdwb);
            return this.zzdaz;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcdm, com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        zzatm.zzdv("Cannot connect to remote service, fallback to local instance.");
        this.zzdaz.setException(new zzcdr(0));
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.zzfun) {
                this.zzfun = true;
                try {
                    this.zzfup.zzsw().zza(this.zzfuo, new zzcdl(this));
                } catch (RemoteException | IllegalArgumentException unused) {
                    this.zzdaz.setException(new zzcdr(0));
                } catch (Throwable th) {
                    com.google.android.gms.ads.internal.zzp.zzkc().zza(th, "RemoteAdRequestClientTask.onConnected");
                    this.zzdaz.setException(new zzcdr(0));
                }
            }
        }
    }
}
