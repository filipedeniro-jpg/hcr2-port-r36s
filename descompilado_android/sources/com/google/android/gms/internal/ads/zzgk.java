package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgk extends Handler {
    private final /* synthetic */ zzgh zzaed;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzgk(zzgh zzghVar, Looper looper) {
        super(looper);
        this.zzaed = zzghVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        this.zzaed.zza(message);
    }
}
