package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9K, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class C9K extends AbstractC0390Aj {
    private final Paint B;
    private C03308b C;
    private final String D;
    private final Path E;
    private final RectF F;
    private CG G;
    private boolean H;
    private RelativeLayout I;
    private C9D J;
    private final GK K;
    private final GG L;
    private final GE M;
    private final GC N;
    private C8W O;
    private final GM P;
    private boolean Q;
    private boolean R;
    private static final int T = (int) (1.0f * J6.B);
    private static final int U = (int) (4.0f * J6.B);
    private static final int S = (int) (6.0f * J6.B);

    public abstract void P(Context context);

    public C9K(AF params, C1Y colors, boolean textInsideMedia, String clientToken, C03308b carouselCardBehaviorHelper) {
        super(params, colors, textInsideMedia);
        this.E = new Path();
        this.F = new RectF();
        this.P = new GM() { // from class: com.facebook.ads.redexgen.X.9I
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C02966t event) {
                this.B.C.E().setVolume(this.B.getVideoView().getVolume());
            }
        };
        this.K = new GK() { // from class: com.facebook.ads.redexgen.X.9H
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass76 event) {
                this.B.C.C().KF(((Integer) this.B.getTag(-1593835536)).intValue());
            }
        };
        this.M = new GE() { // from class: com.facebook.ads.redexgen.X.9G
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass71 event) {
                this.B.C.D().NF(this.B);
            }
        };
        this.L = new GG() { // from class: com.facebook.ads.redexgen.X.9F
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass72 event) {
                this.B.C.D().MF(this.B);
            }
        };
        this.N = new GC() { // from class: com.facebook.ads.redexgen.X.9E
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass70 event) {
                this.B.R = true;
                this.B.G();
            }
        };
        this.C = carouselCardBehaviorHelper;
        this.D = clientToken;
        setGravity(17);
        setPadding(T, 0, T, T);
        J6.O(this, 0);
        setUpView(getContext());
        this.B = new Paint();
        this.B.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.B.setStyle(Paint.Style.FILL);
        this.B.setAlpha(16);
        this.B.setAntiAlias(true);
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public final boolean B() {
        return false;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public boolean C() {
        return false;
    }

    private void F(View view) {
        view.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        J6.U(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        if (this.J == null) {
            return;
        }
        if ((!S() || !this.R) && (S() || !this.Q)) {
            return;
        }
        this.J.XD();
    }

    public final void Q(Map<String, String> map) {
        this.O.E();
        if (S()) {
            this.O.A(getAdEventManager(), this.D, map);
        }
    }

    public final boolean R() {
        return S() && this.O.B();
    }

    public final boolean S() {
        return this.H;
    }

    public final void T() {
        if (S()) {
            this.O.C();
        }
    }

    public final void U() {
        if (S()) {
            V();
            this.O.F(C7A.AUTO_STARTED);
        }
    }

    public final void V() {
        float volume = this.C.E().getVolume();
        if (S() && volume != this.O.getVolume()) {
            this.O.setVolume(volume);
        }
    }

    public final RelativeLayout getMediaContainer() {
        return this.I;
    }

    public final C8W getVideoView() {
        return this.O;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        this.E.reset();
        this.F.set(0.0f, 0.0f, getWidth(), getHeight());
        this.E.addRoundRect(this.F, S, S, Path.Direction.CW);
        canvas.drawPath(this.E, this.B);
        this.F.set(T, 0.0f, getWidth() - T, getHeight() - T);
        this.E.addRoundRect(this.F, U, U, Path.Direction.CW);
        canvas.clipPath(this.E);
        super.onDraw(canvas);
    }

    public void setAdTitleAndDescription(String title, String description) {
        getTitleDescContainer().A(title, description, null, true, false);
    }

    public void setCTAInfo(C1Z ctaData, Map<String, String> map) {
        getCtaButton().setCta(ctaData, this.D, map);
    }

    public void setImageUrl(String imageUrl) {
        this.G.setVisibility(0);
        this.O.setVisibility(8);
        new B7(this.G).B().C(new C9C(this)).E(imageUrl);
    }

    public void setIsVideo(boolean mIsVideo) {
        this.H = mIsVideo;
    }

    public void setOnAssetsLoadedListener(C9D onAssetsLoadedListener) {
        this.J = onAssetsLoadedListener;
    }

    public void setUpImageView(Context context) {
        this.G = new CG(context);
        F(this.G);
    }

    public void setUpMediaContainer(Context context) {
        this.I = new RelativeLayout(context);
        F(this.I);
    }

    public void setUpVideoView(Context context) {
        this.O = new C8W(context, new C0618Jf(this.D, getAdEventManager()));
        F(this.O);
    }

    private void setUpView(Context context) {
        setUpImageView(context);
        setUpVideoView(context);
        setUpMediaContainer(context);
        this.I.addView(this.G);
        this.I.addView(this.O);
        P(context);
    }

    public void setVideoPlaceholderUrl(String placeholderUrl) {
        this.O.setPlaceholderUrl(placeholderUrl);
    }

    public void setVideoUrl(String videoUrl) {
        this.G.setVisibility(8);
        this.O.setVisibility(0);
        this.O.setVideoURI(videoUrl);
        this.O.D(this.P);
        this.O.D(this.K);
        this.O.D(this.M);
        this.O.D(this.L);
        this.O.D(this.N);
    }
}
