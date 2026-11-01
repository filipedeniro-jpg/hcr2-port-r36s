package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8W, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C8W extends FrameLayout {
    private static final int H = (int) (16.0f * J6.B);
    private final C0618Jf B;
    private C6A C;
    private AnonymousClass67 D;

    @Nullable
    private C7G E;
    private C02675q F;
    private CF G;

    public C8W(Context context, C0618Jf funnelLoggingHandler) {
        super(context);
        this.B = funnelLoggingHandler;
        setUpView(context);
    }

    public final void A(JV adEventManager, String clientToken, Map<String, String> extraParams) {
        E();
        this.E = new C7G(getContext(), adEventManager, this.G, clientToken, extraParams);
    }

    public final boolean B() {
        return this.G.G();
    }

    public final void C() {
        this.G.H(true);
    }

    public final void D(AbstractC01210a subscriber) {
        this.G.getEventBus().C(subscriber);
    }

    public final void E() {
        if (this.E != null) {
            this.E.L();
            this.E = null;
        }
    }

    public final void F(C7A reason) {
        this.G.O(reason);
    }

    @VisibleForTesting
    public C0447Co getSimpleVideoView() {
        return this.G;
    }

    public float getVolume() {
        return this.G.getVolume();
    }

    public void setPlaceholderUrl(String placeholderUrl) {
        this.D.setImage(placeholderUrl);
    }

    private void setUpPlugins(Context context) {
        this.G.K();
        this.D = new AnonymousClass67(context);
        this.G.A(this.D);
        this.C = new C6A(context, this.B);
        this.G.A(new C6T(context));
        this.G.A(this.C);
        this.F = new C02675q(context, true, this.B);
        this.G.A(this.F);
        this.G.A(new C6H(this.F, C6I.FADE_OUT_ON_PLAY, true, true));
        if (!this.G.D()) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        layoutParams.setMargins(H, H, H, H);
        this.C.setLayoutParams(layoutParams);
        this.G.addView(this.C);
    }

    private void setUpVideo(Context context) {
        this.G = new CF(context);
        this.G.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        J6.U(this.G);
        addView(this.G);
        setOnClickListener(new View.OnClickListener() { // from class: com.facebook.ads.redexgen.X.8X
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                this.B.F.performClick();
            }
        });
    }

    private void setUpView(Context context) {
        setUpVideo(context);
        setUpPlugins(context);
    }

    public void setVideoURI(String videoUrl) {
        this.G.setVideoURI(videoUrl);
    }

    public void setVolume(float volume) {
        this.G.setVolume(volume);
        this.C.A();
    }
}
