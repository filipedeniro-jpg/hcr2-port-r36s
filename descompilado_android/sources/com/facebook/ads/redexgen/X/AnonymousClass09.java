package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.09, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AnonymousClass09 {
    public final JV B;
    public final String C;
    public final Context D;

    public abstract void A();

    public AnonymousClass09(Context context, JV adEventManager, String clientToken) {
        this.D = context;
        this.B = adEventManager;
        this.C = clientToken;
    }

    @Nullable
    public AnonymousClass08 B() {
        return null;
    }
}
