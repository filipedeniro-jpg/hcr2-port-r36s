package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.62, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass62 extends AnonymousClass79 implements View.OnTouchListener {
    private final C02655o B;
    private final GK C;
    private final GG D;
    private final GE E;
    private final GC F;

    public AnonymousClass62(Context context) {
        this(context, null);
    }

    private AnonymousClass62(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private AnonymousClass62(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.F = new GC() { // from class: com.facebook.ads.redexgen.X.66
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass70 event) {
                this.B.setVisibility(0);
            }
        };
        this.D = new GG() { // from class: com.facebook.ads.redexgen.X.65
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass72 event) {
                this.B.B.setChecked(true);
            }
        };
        this.E = new GE() { // from class: com.facebook.ads.redexgen.X.64
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass71 event) {
                this.B.B.setChecked(false);
            }
        };
        this.C = new GK() { // from class: com.facebook.ads.redexgen.X.63
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass76 event) {
                this.B.B.setChecked(true);
            }
        };
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.B = new C02655o(context);
        this.B.setChecked(true);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (displayMetrics.density * 25.0f), (int) (displayMetrics.density * 25.0f));
        setVisibility(8);
        addView(this.B, layoutParams);
        setClickable(true);
        setFocusable(true);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void A() {
        super.A();
        this.B.setOnTouchListener(this);
        setOnTouchListener(this);
        if (getVideoView() != null) {
            getVideoView().getEventBus().B(this.F, this.C, this.D, this.E);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void B() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().D(this.E, this.D, this.C, this.F);
        }
        setOnTouchListener(null);
        this.B.setOnTouchListener(null);
        super.B();
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View v, MotionEvent event) {
        C0447Co videoView;
        if (event.getAction() != 1 || (videoView = getVideoView()) == null) {
            return false;
        }
        if (videoView.getState() == C5K.PREPARED || videoView.getState() == C5K.PAUSED || videoView.getState() == C5K.PLAYBACK_COMPLETED) {
            videoView.O(C7A.USER_STARTED);
            return true;
        }
        if (videoView.getState() != C5K.STARTED) {
            return false;
        }
        videoView.H(true);
        return false;
    }
}
