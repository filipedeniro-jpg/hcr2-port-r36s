package com.facebook.ads.redexgen.X;

import android.support.annotation.NonNull;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8t, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public interface InterfaceC03488t {
    boolean onNestedFling(@NonNull View view, float f, float f2, boolean z);

    boolean onNestedPreFling(@NonNull View view, float f, float f2);

    void onNestedPreScroll(@NonNull View view, int i, int i2, @NonNull int[] iArr);

    void onNestedScroll(@NonNull View view, int i, int i2, int i3, int i4);

    void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i);

    boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i);

    void onStopNestedScroll(@NonNull View view);
}
