package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.DeadObjectException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzre implements BaseGmsClient.BaseConnectionCallbacks {
    private final /* synthetic */ zzrd zzbqu;

    zzre(zzrd zzrdVar) {
        this.zzbqu = zzrdVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        synchronized (this.zzbqu.lock) {
            try {
                if (this.zzbqu.zzbqw != null) {
                    this.zzbqu.zzbqx = this.zzbqu.zzbqw.zzmj();
                }
            } catch (DeadObjectException e) {
                zzatm.zzc("Unable to obtain a cache service instance.", e);
                this.zzbqu.disconnect();
            }
            this.zzbqu.lock.notifyAll();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        synchronized (this.zzbqu.lock) {
            this.zzbqu.zzbqx = null;
            this.zzbqu.lock.notifyAll();
        }
    }
}
