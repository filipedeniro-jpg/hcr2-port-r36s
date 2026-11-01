package com.facebook.ads.redexgen.X;

import android.R;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5j, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02605j extends RelativeLayout implements F0 {
    private static final int J = (int) (6.0f * J6.B);
    private ObjectAnimator B;
    private AtomicInteger C;
    private AbstractC01210a D;
    private AbstractC01210a E;
    private AbstractC01210a F;
    private AbstractC01210a G;
    private ProgressBar H;

    @Nullable
    private C0447Co I;

    public C02605j(Context context) {
        this(context, J, -12549889, 0);
    }

    private C02605j(Context context, int progressBarHeight, int progressColor, int backgroundColor) {
        super(context);
        this.G = new FO() { // from class: com.facebook.ads.redexgen.X.5n
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C03026z event) {
                if (this.B.I != null) {
                    this.B.H(this.B.I.getDuration(), this.B.I.getCurrentPositionInMillis());
                }
            }
        };
        this.E = new GG() { // from class: com.facebook.ads.redexgen.X.5m
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass72 event) {
                this.B.G();
            }
        };
        this.F = new GE() { // from class: com.facebook.ads.redexgen.X.5l
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass71 event) {
                if (this.B.I != null) {
                    this.B.H(this.B.I.getDuration(), this.B.I.getCurrentPositionInMillis());
                }
            }
        };
        this.D = new GK() { // from class: com.facebook.ads.redexgen.X.5k
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass76 event) {
                if (this.B.I != null) {
                    this.B.F();
                }
            }
        };
        this.C = new AtomicInteger(-1);
        this.H = new ProgressBar(context, null, R.attr.progressBarStyleHorizontal);
        this.H.setLayoutParams(new RelativeLayout.LayoutParams(-1, progressBarHeight));
        B(progressColor, backgroundColor);
        this.H.setMax(10000);
        addView(this.H);
    }

    public final void A() {
        G();
        this.H = null;
        this.I = null;
    }

    public final void B(int progressColor, int backgroundColor) {
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{new ColorDrawable(backgroundColor), new ColorDrawable(backgroundColor), new ScaleDrawable(new ColorDrawable(progressColor), GravityCompat.START, 1.0f, -1.0f)});
        layerDrawable.setId(0, R.id.background);
        layerDrawable.setId(1, R.id.secondaryProgress);
        layerDrawable.setId(2, R.id.progress);
        this.H.setProgressDrawable(layerDrawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        G();
        this.B = ObjectAnimator.ofInt(this.H, "progress", 0, 0);
        this.B.setDuration(0L);
        this.B.setInterpolator(new LinearInterpolator());
        this.B.start();
        this.C.set(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        if (this.B != null) {
            this.B.cancel();
            this.B.setTarget(null);
            this.B = null;
            this.H.clearAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H(int duration, int position) {
        G();
        if (this.C.get() >= position || duration <= position) {
            return;
        }
        this.B = ObjectAnimator.ofInt(this.H, "progress", (position * 10000) / duration, (Math.min(position + 250, duration) * 10000) / duration);
        int progress = duration - position;
        this.B.setDuration(Math.min(250, progress));
        this.B.setInterpolator(new LinearInterpolator());
        this.B.start();
        this.C.set(position);
    }

    @Override // com.facebook.ads.redexgen.X.F0
    public final void cC(C0447Co videoView) {
        this.I = videoView;
        videoView.getEventBus().B(this.E, this.F, this.G, this.D);
    }

    @Override // com.facebook.ads.redexgen.X.F0
    public final void wF(C0447Co videoView) {
        videoView.getEventBus().D(this.G, this.F, this.E, this.D);
        this.I = null;
    }
}
