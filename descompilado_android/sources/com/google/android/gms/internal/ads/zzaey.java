package com.google.android.gms.internal.ads;

import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaey implements BaseGmsClient.BaseOnConnectionFailedListener {
    private final /* synthetic */ zzaxf zzbrn;

    zzaey(zzaer zzaerVar, zzaxf zzaxfVar) {
        this.zzbrn = zzaxfVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zzbrn.setException(new RuntimeException("Connection failed."));
    }
}
