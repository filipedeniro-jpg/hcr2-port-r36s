package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5y, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02755y extends AnonymousClass79 {
    private final AbstractC01210a<C03026z> B;
    private final int C;
    private final String D;
    private final String E;
    private final AtomicBoolean F;
    private final C02765z G;

    /* JADX WARN: Type inference failed for: r0v2, types: [com.facebook.ads.redexgen.X.5z] */
    public C02755y(final Context context, int secondsToSkip, String skipAdInText, String skipAdText) {
        super(context);
        this.B = new AbstractC01210a<C03026z>() { // from class: com.facebook.ads.redexgen.X.61
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<C03026z> A() {
                return C03026z.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C03026z event) {
                if (!this.B.F.get() && this.B.getVideoView() != null) {
                    int currentPositionInMillis = this.B.C - (this.B.getVideoView().getCurrentPositionInMillis() / 1000);
                    if (currentPositionInMillis > 0) {
                        setText(this.B.D + ' ' + currentPositionInMillis);
                    } else {
                        setText(this.B.E);
                        this.B.F.set(true);
                    }
                }
            }
        };
        this.C = secondsToSkip;
        this.D = skipAdInText;
        this.E = skipAdText;
        this.F = new AtomicBoolean(false);
        this.G = new TextView(context) { // from class: com.facebook.ads.redexgen.X.5z
            private final Paint B;
            private final Paint C;
            private final RectF D;

            {
                super(context);
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                J6.O(this, 0);
                setTextColor(-3355444);
                setPadding((int) (displayMetrics.density * 9.0f), (int) (displayMetrics.density * 5.0f), (int) (displayMetrics.density * 9.0f), (int) (displayMetrics.density * 5.0f));
                setTextSize(18.0f);
                this.C = new Paint();
                this.C.setStyle(Paint.Style.STROKE);
                this.C.setColor(-10066330);
                this.C.setStrokeWidth(1.0f);
                this.C.setAntiAlias(true);
                this.B = new Paint();
                this.B.setStyle(Paint.Style.FILL);
                this.B.setColor(-1895825408);
                this.D = new RectF();
            }

            @Override // android.widget.TextView, android.view.View
            public final void onDraw(Canvas canvas) {
                if (getText().length() == 0) {
                    return;
                }
                int width = getWidth();
                int height = getHeight();
                this.D.set(0, 0, width, height);
                canvas.drawRoundRect(this.D, 6.0f, 6.0f, this.B);
                this.D.set(2, 2, width - 2, height - 2);
                canvas.drawRoundRect(this.D, 6.0f, 6.0f, this.C);
                super.onDraw(canvas);
            }
        };
        setText(this.D + ' ' + secondsToSkip);
        addView(this.G, new RelativeLayout.LayoutParams(-2, -2));
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void A() {
        super.A();
        if (getVideoView() != null) {
            getVideoView().getEventBus().C(this.B);
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.facebook.ads.redexgen.X.60
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                if (this.B.F.get()) {
                    if (this.B.getVideoView() != null) {
                        this.B.getVideoView().m243N();
                        return;
                    }
                    return;
                }
                Log.i("SkipPlugin", "User clicked skip before the ads is allowed to skip.");
            }
        });
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void B() {
        if (getVideoView() != null) {
            setOnClickListener(null);
            getVideoView().getEventBus().E(this.B);
        }
        super.B();
    }
}
