package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcyl implements BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    private final Object lock = new Object();
    private boolean zzfum = false;
    private boolean zzfun = false;
    private final zzcyx zzgmx;
    private final zzcys zzgmy;

    zzcyl(@NonNull Context context, @NonNull Looper looper, @NonNull zzcys zzcysVar) {
        this.zzgmy = zzcysVar;
        this.zzgmx = new zzcyx(context, looper, this, this);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int i) {
    }

    final void zzanr() {
        synchronized (this.lock) {
            if (!this.zzfum) {
                this.zzfum = true;
                this.zzgmx.checkAvailabilityAndConnect();
            }
        }
    }

    private final void zzakf() {
        synchronized (this.lock) {
            if (this.zzgmx.isConnected() || this.zzgmx.isConnecting()) {
                this.zzgmx.disconnect();
            }
            Binder.flushPendingCommands();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        synchronized (this.lock) {
            if (this.zzfun) {
                return;
            }
            this.zzfun = true;
            try {
                this.zzgmx.zzanw().zza(new zzcyv(this.zzgmy.toByteArray()));
            } catch (Exception unused) {
            } finally {
                zzakf();
            }
        }
    }
}
