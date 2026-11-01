package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaty extends BroadcastReceiver {
    private final /* synthetic */ zzatv zzdsp;

    private zzaty(zzatv zzatvVar) {
        this.zzdsp = zzatvVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
            this.zzdsp.zzyf = true;
        } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            this.zzdsp.zzyf = false;
        }
    }

    /* synthetic */ zzaty(zzatv zzatvVar, zzatu zzatuVar) {
        this(zzatvVar);
    }
}
