package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzei extends BroadcastReceiver {
    private final /* synthetic */ zzeg zzzq;

    zzei(zzeg zzegVar) {
        this.zzzq = zzegVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.zzzq.zzcs();
    }
}
