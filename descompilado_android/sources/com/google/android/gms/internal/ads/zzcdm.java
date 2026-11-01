package com.google.android.gms.internal.ads;

import android.os.Binder;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.io.InputStream;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzcdm implements BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    protected zzaok zzfuo;

    @GuardedBy("mLock")
    @VisibleForTesting(otherwise = 3)
    protected zzans zzfup;
    protected final zzaxf<InputStream> zzdaz = new zzaxf<>();
    protected final Object mLock = new Object();
    protected boolean zzfum = false;
    protected boolean zzfun = false;

    protected final void zzakf() {
        synchronized (this.mLock) {
            this.zzfun = true;
            if (this.zzfup.isConnected() || this.zzfup.isConnecting()) {
                this.zzfup.disconnect();
            }
            Binder.flushPendingCommands();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public void onConnectionSuspended(int i) {
        zzatm.zzdv("Cannot connect to remote service, fallback to local instance.");
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        zzatm.zzdv("Disconnected from remote ad request service.");
        this.zzdaz.setException(new zzcdr(0));
    }
}
