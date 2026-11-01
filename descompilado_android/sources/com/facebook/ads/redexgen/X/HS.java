package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

/* JADX INFO: loaded from: assets/audience_network.dex */
@VisibleForTesting
public abstract class HS<T> {
    private HY B;

    @Nullable
    /* JADX INFO: renamed from: B */
    public abstract T mo254B();

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.HS != com.facebook.ads.internal.database.AdDatabaseQuery<T> */
    public final HY A() {
        return this.B;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.HS != com.facebook.ads.internal.database.AdDatabaseQuery<T> */
    public final void C(HY mQueryError) {
        this.B = mQueryError;
    }
}
