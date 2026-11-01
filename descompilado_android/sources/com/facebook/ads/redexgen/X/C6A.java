package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6A, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C6A extends ImageView implements F0 {
    private static final int F = (int) (4.0f * Resources.getSystem().getDisplayMetrics().density);
    private final Paint B;
    private final C0618Jf C;
    private final GM D;

    @Nullable
    private C0447Co E;

    public C6A(Context context, C0618Jf funnelLoggingHandler) {
        super(context);
        this.D = new GM() { // from class: com.facebook.ads.redexgen.X.6C
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C02966t event) {
                this.B.A();
            }
        };
        this.C = funnelLoggingHandler;
        this.B = new Paint();
        this.B.setColor(-1728053248);
        setColorFilter(-1);
        setPadding(F, F, F, F);
        G();
        setOnClickListener(new View.OnClickListener() { // from class: com.facebook.ads.redexgen.X.6B
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                this.B.C.A(EnumC0617Je.SOUND_BUTTON_PRESSED, null);
                if (this.B.E != null) {
                    if (this.B.E()) {
                        this.B.E.setVolume(1.0f);
                    } else {
                        this.B.E.setVolume(0.0f);
                    }
                    this.B.A();
                }
            }
        });
    }

    public final void A() {
        if (this.E == null) {
            return;
        }
        if (E()) {
            F();
        } else {
            G();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean E() {
        return this.E != null && this.E.getVolume() == 0.0f;
    }

    private void F() {
        setImageBitmap(C0605Ir.E(EnumC0606Is.SOUND_OFF));
    }

    private void G() {
        setImageBitmap(C0605Ir.E(EnumC0606Is.SOUND_ON));
    }

    @Override // com.facebook.ads.redexgen.X.F0
    public final void cC(C0447Co videoView) {
        this.E = videoView;
        if (this.E != null) {
            this.E.getEventBus().C(this.D);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDraw(Canvas canvas) {
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        canvas.drawCircle(width, height, Math.min(width, height), this.B);
        super.onDraw(canvas);
    }

    @Override // com.facebook.ads.redexgen.X.F0
    public final void wF(C0447Co videoView) {
        if (this.E != null) {
            this.E.getEventBus().E(this.D);
        }
        this.E = null;
    }
}
