package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.NativeAdLayout;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.By, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0431By {
    private static final int B = (int) (J6.B * 200.0f);
    private static final int D = (int) (J6.B * 200.0f);
    private static final int C = (int) (50.0f * J6.B);

    private C0431By() {
    }

    public static AbstractC0432Bz B(Context context, JV adEventManager, String clientToken, InterfaceC0578Hq audienceNetworkView, InterfaceC0525Fo listener) {
        return new C0425Bs(context, adEventManager, clientToken, audienceNetworkView, listener);
    }

    @Nullable
    public static AbstractC0432Bz C(Context context, JV adEventManager, String clientToken, @Nullable NativeAdLayout nativeAdLayout) {
        if (nativeAdLayout == null) {
            return null;
        }
        int h = nativeAdLayout.getWidth();
        int height = nativeAdLayout.getHeight();
        if (h >= B && height >= B) {
            return new C0408Bb(context, adEventManager, clientToken, h, height);
        }
        if (h < D || height < C) {
            return null;
        }
        return new C0418Bl(context, adEventManager, clientToken, h, height);
    }

    public static AnonymousClass15 D(@Nullable NativeAdLayout layout) {
        if (layout == null) {
            return AnonymousClass15.NO_NATIVE_AD_LAYOUT;
        }
        if (E(layout)) {
            return AnonymousClass15.TOO_SMALL;
        }
        return AnonymousClass15.AVAILABLE;
    }

    private static boolean E(NativeAdLayout layout) {
        int w = layout.getWidth();
        int height = layout.getHeight();
        return (w < B || height < B) && (w < D || height < C);
    }
}
