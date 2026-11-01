package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class B0 extends B1 {

    @Nullable
    private final JV B;

    @Nullable
    private final InterfaceC0525Fo C;

    @Nullable
    private B2 D;
    private final String E;
    private final C0621Ji F;
    private final C5I G;

    public B0(Context context, boolean roundCornersEnabled, boolean ctaOverMedia, String clickEvent, C1Y colors, JV adEventManager, InterfaceC0525Fo listener, C5I viewabilityChecker, C0621Ji touchDataRecorder) {
        super(context, roundCornersEnabled, ctaOverMedia, colors);
        this.B = adEventManager;
        this.C = listener;
        this.E = clickEvent;
        this.G = viewabilityChecker;
        this.F = touchDataRecorder;
    }

    public final boolean A() {
        if (this.D != null) {
            return this.D.A();
        }
        return false;
    }

    public final void B(C1Z data, String clientToken, Map<String, String> map) {
        Uri uri = Uri.parse(data.C());
        this.G.A(map);
        map.put("touch", MJ.C(this.F.m258B()));
        AnonymousClass09 anonymousClass09H = H(uri, clientToken, map, false);
        if (anonymousClass09H != null) {
            anonymousClass09H.B();
        }
    }

    @Nullable
    private AnonymousClass09 H(Uri uri, String clientToken, Map<String, String> extraData, boolean actionEnabled) {
        return I(uri, clientToken, extraData, actionEnabled, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public AnonymousClass09 I(Uri uri, String clientToken, Map<String, String> extraData, boolean actionEnabled, boolean isInAppBrowser) {
        return C0A.C(getContext(), this.B, clientToken, uri, extraData, actionEnabled, isInAppBrowser);
    }

    public void setActionEnabled(boolean actionEnabled) {
        if (this.D != null) {
            this.D.B(actionEnabled);
        }
    }

    public void setCta(C1Z data, String clientToken, Map<String, String> extraData) {
        setCta(data.B(), data.C(), clientToken, extraData, null);
    }

    public void setCta(C1Z data, String clientToken, Map<String, String> extraData, @Nullable CP onCtaClickedListener) {
        setCta(data.B(), data.C(), clientToken, extraData, onCtaClickedListener);
    }

    private void setCta(String text, String url, String clientToken, Map<String, String> map, @Nullable CP onCtaClickedListener) {
        if (TextUtils.isEmpty(text) || TextUtils.isEmpty(url) || this.B == null) {
            setVisibility(8);
            return;
        }
        this.D = new B2(url, clientToken, map, this, new C0618Jf(clientToken, this.B));
        this.D.D(onCtaClickedListener);
        setText(text);
        setOnClickListener(this.D);
    }

    public void setIsInAppBrowser(boolean isInAppBrowser) {
        if (this.D != null) {
            this.D.C(isInAppBrowser);
        }
    }
}
