package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import com.facebook.ads.internal.api.BidderTokenProviderApi;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0536Fz implements BidderTokenProviderApi {

    @VisibleForTesting
    public final MB B = new MB(300000000000L, new RunnableC0535Fy(this));
    private String C;

    @Nullable
    private Context D;

    /* JADX INFO: Access modifiers changed from: private */
    public void C() {
        Context context;
        synchronized (this) {
            context = this.D;
        }
        if (context == null) {
            return;
        }
        String strLC = C0597Ij.B().B(context, true).LC(D(context));
        synchronized (this) {
            this.C = strLC;
        }
    }

    private static InterfaceC0592Ie D(Context context) {
        return JA.q(context) ? C0595Ih.C("ANALOG", "SESSION_ID", "SESSION_TIME", "NETWORK_TYPE") : C0595Ih.B();
    }

    @Override // com.facebook.ads.internal.api.BidderTokenProviderApi
    @WorkerThread
    public final synchronized String getBidderToken(Context context) {
        this.D = context.getApplicationContext();
        C0588Ia.G(this.D);
        if (this.C == null) {
            C();
            this.B.A().C();
        }
        this.B.B();
        return this.C;
    }
}
