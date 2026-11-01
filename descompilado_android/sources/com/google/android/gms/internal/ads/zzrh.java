package com.google.android.gms.internal.ads;

import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzrh implements BaseGmsClient.BaseOnConnectionFailedListener {
    private final /* synthetic */ zzrd zzbqu;

    zzrh(zzrd zzrdVar) {
        this.zzbqu = zzrdVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        synchronized (this.zzbqu.lock) {
            this.zzbqu.zzbqx = null;
            if (this.zzbqu.zzbqw != null) {
                zzrd.zza(this.zzbqu, (zzri) null);
            }
            this.zzbqu.lock.notifyAll();
        }
    }
}
