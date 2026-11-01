package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5q, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02675q extends AnonymousClass79 {
    private final Paint B;
    private final C02655o C;

    @Nullable
    private final C0618Jf D;
    private final GK E;
    private final GG F;
    private final GE G;

    public C02675q(Context context, @Nullable C0618Jf funnelLoggingHandler) {
        this(context, false, funnelLoggingHandler);
    }

    public C02675q(Context context, boolean darkBackground, @Nullable C0618Jf funnelLoggingHandler) {
        super(context);
        this.F = new GG() { // from class: com.facebook.ads.redexgen.X.5v
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass72 event) {
                this.B.C.setChecked(true);
            }
        };
        this.G = new GE() { // from class: com.facebook.ads.redexgen.X.5u
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass71 event) {
                this.B.C.setChecked(false);
            }
        };
        this.E = new GK() { // from class: com.facebook.ads.redexgen.X.5t
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass76 event) {
                this.B.C.setChecked(true);
            }
        };
        this.D = funnelLoggingHandler;
        this.C = new C02655o(context, darkBackground);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (((double) displayMetrics.density) * 23.76d), (int) (((double) displayMetrics.density) * 23.76d));
        layoutParams.addRule(13);
        this.C.setLayoutParams(layoutParams);
        this.C.setChecked(true);
        this.C.setClickable(false);
        this.B = new Paint();
        this.B.setStyle(Paint.Style.FILL);
        if (darkBackground) {
            this.B.setColor(-1728053248);
        } else {
            this.B.setColor(-1);
            this.B.setAlpha(204);
        }
        J6.O(this, 0);
        addView(this.C);
        setGravity(17);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((int) (((double) displayMetrics.density) * 72.0d), (int) (((double) displayMetrics.density) * 72.0d));
        layoutParams2.addRule(13);
        setLayoutParams(layoutParams2);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void A() {
        super.A();
        if (getVideoView() != null) {
            getVideoView().getEventBus().B(this.F, this.G, this.E);
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.facebook.ads.redexgen.X.5s
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                if (this.B.getVideoView() != null) {
                    if (this.B.D != null) {
                        this.B.D.A(EnumC0617Je.VIDEO_PLAY_PAUSE_CLICKED, null);
                    }
                    switch (C02685r.B[this.B.getVideoView().getState().ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            this.B.getVideoView().O(C7A.USER_STARTED);
                            break;
                        case 5:
                            this.B.getVideoView().H(true);
                            break;
                    }
                }
            }
        });
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void B() {
        setOnClickListener(null);
        if (getVideoView() != null) {
            getVideoView().getEventBus().D(this.E, this.G, this.F);
        }
        super.B();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = Math.min(width, (getHeight() - getPaddingTop()) - getPaddingBottom());
        int i = height / 2;
        canvas.drawCircle(getPaddingLeft() + i, getPaddingTop() + (height / 2), i, this.B);
        super.onDraw(canvas);
    }
}
