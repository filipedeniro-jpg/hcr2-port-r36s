package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import java.util.HashMap;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6D, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C6D extends AnonymousClass79 {
    private final JV B;
    private final Paint C;
    private final String D;
    private final TextView E;
    private final String F;
    private final RectF G;

    public C6D(Context context, String mLearnMoreUrl, JV mAdEventManager, String mClientToken, String learnMoreText) {
        super(context);
        this.F = mLearnMoreUrl;
        this.B = mAdEventManager;
        this.D = mClientToken;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.E = new TextView(getContext());
        this.E.setTextColor(-3355444);
        this.E.setTextSize(16.0f);
        this.E.setPadding((int) (displayMetrics.density * 6.0f), (int) (displayMetrics.density * 4.0f), (int) (displayMetrics.density * 6.0f), (int) (displayMetrics.density * 4.0f));
        this.C = new Paint();
        this.C.setStyle(Paint.Style.FILL);
        this.C.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.C.setAlpha(178);
        this.G = new RectF();
        J6.O(this, 0);
        this.E.setText(learnMoreText);
        addView(this.E, new RelativeLayout.LayoutParams(-2, -2));
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void A() {
        super.A();
        this.E.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.ads.redexgen.X.6E
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (this.B.getVideoView() != null) {
                    final Uri uri = Uri.parse(this.B.F);
                    this.B.getVideoView().getEventBus().A(new It(uri) { // from class: com.facebook.ads.redexgen.X.77
                        private final Uri B;

                        {
                            this.B = uri;
                        }
                    });
                    AnonymousClass09 anonymousClass09B = C0A.B(this.B.getContext(), this.B.B, this.B.D, uri, new HashMap());
                    if (anonymousClass09B == null) {
                        return;
                    }
                    anonymousClass09B.A();
                }
            }
        });
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void B() {
        this.E.setOnClickListener(null);
        super.B();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        this.G.set(0.0f, 0.0f, getWidth(), getHeight());
        canvas.drawRoundRect(this.G, 0.0f, 0.0f, this.C);
        super.onDraw(canvas);
    }
}
