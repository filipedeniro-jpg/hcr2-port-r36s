package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzrd {

    @Nullable
    @GuardedBy("lock")
    private zzri zzbqw;

    @Nullable
    @GuardedBy("lock")
    private zzrm zzbqx;

    @Nullable
    @GuardedBy("lock")
    private Context zzlk;
    private final Runnable zzbqv = new zzrc(this);
    private final Object lock = new Object();

    public final void initialize(Context context) {
        if (context == null) {
            return;
        }
        synchronized (this.lock) {
            if (this.zzlk != null) {
                return;
            }
            this.zzlk = context.getApplicationContext();
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcpg)).booleanValue()) {
                connect();
            } else {
                if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcpf)).booleanValue()) {
                    com.google.android.gms.ads.internal.zzp.zzkb().zza(new zzrf(this));
                }
            }
        }
    }

    public final void zzmf() {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcph)).booleanValue()) {
            synchronized (this.lock) {
                connect();
                com.google.android.gms.ads.internal.zzp.zzjy();
                zzatv.zzdsk.removeCallbacks(this.zzbqv);
                com.google.android.gms.ads.internal.zzp.zzjy();
                zzatv.zzdsk.postDelayed(this.zzbqv, ((Long) zzuo.zzoj().zzd(zzyt.zzcpi)).longValue());
            }
        }
    }

    public final zzrg zza(zzrl zzrlVar) {
        synchronized (this.lock) {
            if (this.zzbqx == null) {
                return new zzrg();
            }
            try {
                return this.zzbqx.zza(zzrlVar);
            } catch (RemoteException e) {
                zzatm.zzc("Unable to call into cache service.", e);
                return new zzrg();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void connect() {
        synchronized (this.lock) {
            if (this.zzlk != null && this.zzbqw == null) {
                this.zzbqw = zza(new zzre(this), new zzrh(this));
                this.zzbqw.checkAvailabilityAndConnect();
            }
        }
    }

    @VisibleForTesting
    private final synchronized zzri zza(BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        return new zzri(this.zzlk, com.google.android.gms.ads.internal.zzp.zzkm().zzvz(), baseConnectionCallbacks, baseOnConnectionFailedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disconnect() {
        synchronized (this.lock) {
            if (this.zzbqw == null) {
                return;
            }
            if (this.zzbqw.isConnected() || this.zzbqw.isConnecting()) {
                this.zzbqw.disconnect();
            }
            this.zzbqw = null;
            this.zzbqx = null;
            Binder.flushPendingCommands();
        }
    }

    static /* synthetic */ zzri zza(zzrd zzrdVar, zzri zzriVar) {
        zzrdVar.zzbqw = null;
        return null;
    }
}
