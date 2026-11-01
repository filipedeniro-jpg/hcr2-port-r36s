package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.WorkerThread;
import com.facebook.ads.AudienceNetworkAds;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class IV implements Runnable {
    public final /* synthetic */ Context B;
    public final /* synthetic */ AudienceNetworkAds.InitListener C;

    public IV(Context context, AudienceNetworkAds.InitListener initListener) {
        this.B = context;
        this.C = initListener;
    }

    @Override // java.lang.Runnable
    @WorkerThread
    public final void run() {
        try {
            C0588Ia.K(this.B);
        } catch (Throwable th) {
            C0585Hx.C(th);
        }
        if (this.C == null) {
            return;
        }
        C0588Ia.M(this.C, new IZ(true, "Sdk successfully initialized!"));
    }
}
