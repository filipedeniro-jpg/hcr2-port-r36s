package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5w, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02735w extends AnonymousClass79 {
    private final AbstractC01210a<AnonymousClass70> B;

    public C02735w(Context context) {
        this(context, null);
    }

    private C02735w(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private C02735w(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.B = new AbstractC01210a<AnonymousClass70>() { // from class: com.facebook.ads.redexgen.X.5x
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<AnonymousClass70> A() {
                return AnonymousClass70.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass70 event) {
                this.B.setVisibility(8);
            }
        };
        int iApplyDimension = (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics());
        ProgressBar progressBar = new ProgressBar(getContext());
        progressBar.setIndeterminate(true);
        progressBar.getIndeterminateDrawable().setColorFilter(-1, PorterDuff.Mode.SRC_IN);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iApplyDimension, iApplyDimension);
        layoutParams.addRule(13);
        addView(progressBar, layoutParams);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void A() {
        super.A();
        setVisibility(0);
        if (getVideoView() != null) {
            getVideoView().getEventBus().C(this.B);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void B() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().E(this.B);
        }
        setVisibility(8);
        super.B();
    }
}
