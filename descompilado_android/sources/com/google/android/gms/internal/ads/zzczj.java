package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.CallSuper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzczj extends Handler {
    private static volatile zzczm zzgnw;

    public zzczj() {
    }

    public zzczj(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public final void dispatchMessage(Message message) {
        zzb(message);
    }

    @CallSuper
    protected void zzb(Message message) {
        super.dispatchMessage(message);
    }
}
