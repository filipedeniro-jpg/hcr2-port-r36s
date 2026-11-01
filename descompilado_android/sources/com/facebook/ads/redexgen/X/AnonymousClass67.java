package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.67, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass67 extends AnonymousClass79 {
    private final ImageView B;
    private final AbstractC01210a<AnonymousClass76> C;
    private final AbstractC01210a<AnonymousClass71> D;

    public AnonymousClass67(Context context) {
        super(context);
        this.D = new AbstractC01210a<AnonymousClass71>() { // from class: com.facebook.ads.redexgen.X.69
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<AnonymousClass71> A() {
                return AnonymousClass71.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass71 event) {
                this.B.setVisibility(8);
            }
        };
        this.C = new AbstractC01210a<AnonymousClass76>() { // from class: com.facebook.ads.redexgen.X.68
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<AnonymousClass76> A() {
                return AnonymousClass76.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass76 event) {
                this.B.setVisibility(0);
            }
        };
        this.B = new ImageView(context);
        this.B.setScaleType(ImageView.ScaleType.FIT_CENTER);
        J6.O(this.B, ViewCompat.MEASURED_STATE_MASK);
        this.B.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        addView(this.B);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void A() {
        super.A();
        if (getVideoView() != null) {
            getVideoView().getEventBus().B(this.D, this.C);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void B() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().D(this.C, this.D);
        }
        super.B();
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean changed, int l, int t, int r, int b) {
        this.B.layout(0, 0, r - l, b - t);
    }

    public void setImage(@Nullable String url) {
        setImage(url, null);
    }

    public void setImage(@Nullable String url, @Nullable C1D listener) {
        if (url == null) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        B7 b7B = new B7(this.B).B();
        if (listener != null) {
            b7B.C(listener);
        }
        b7B.E(url);
    }
}
