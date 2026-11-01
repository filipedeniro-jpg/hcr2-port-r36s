package com.google.android.gms.internal.ads;

import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzrx implements BaseGmsClient.BaseOnConnectionFailedListener {
    private final /* synthetic */ zzrr zzbrg;
    private final /* synthetic */ zzaxf zzbrn;

    zzrx(zzrr zzrrVar, zzaxf zzaxfVar) {
        this.zzbrg = zzrrVar;
        this.zzbrn = zzaxfVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        synchronized (this.zzbrg.lock) {
            this.zzbrn.setException(new RuntimeException("Connection failed."));
        }
    }
}
