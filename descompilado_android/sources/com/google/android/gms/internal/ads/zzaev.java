package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.DeadObjectException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaev implements BaseGmsClient.BaseConnectionCallbacks {
    private final /* synthetic */ zzaxf zzbrn;
    private final /* synthetic */ zzaer zzcya;

    zzaev(zzaer zzaerVar, zzaxf zzaxfVar) {
        this.zzcya = zzaerVar;
        this.zzbrn = zzaxfVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        try {
            this.zzbrn.set(this.zzcya.zzcxy.zzqx());
        } catch (DeadObjectException e) {
            this.zzbrn.setException(e);
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        zzaxf zzaxfVar = this.zzbrn;
        StringBuilder sb = new StringBuilder(34);
        sb.append("onConnectionSuspended: ");
        sb.append(i);
        zzaxfVar.setException(new RuntimeException(sb.toString()));
    }
}
