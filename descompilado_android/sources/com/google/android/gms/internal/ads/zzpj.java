package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzpj extends BroadcastReceiver {
    private final /* synthetic */ zzph zzbof;

    zzpj(zzph zzphVar) {
        this.zzbof = zzphVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.zzbof.zzbm(3);
    }
}
