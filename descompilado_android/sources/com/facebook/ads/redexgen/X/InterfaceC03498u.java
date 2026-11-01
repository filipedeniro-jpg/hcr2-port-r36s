package com.facebook.ads.redexgen.X;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8u, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public interface InterfaceC03498u extends InterfaceC03488t {
    void onNestedPreScroll(@NonNull View view, int i, int i2, @Nullable int[] iArr, int i3);

    void onNestedScroll(@NonNull View view, int i, int i2, int i3, int i4, int i5);

    void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i, int i2);

    boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i, int i2);

    void onStopNestedScroll(@NonNull View view, int i);
}
