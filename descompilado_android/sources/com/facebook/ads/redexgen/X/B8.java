package com.facebook.ads.redexgen.X;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import com.facebook.ads.internal.settings.AdSdkVersion;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.gms.games.Notifications;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class B8 extends Drawable {
    private String E;
    private int F;
    private int G;
    private String H;
    private int I;
    private boolean K;

    @Nullable
    private String M;
    private long N;

    @Nullable
    private WeakReference<C5I> Q;
    private final Paint B = new Paint();
    private final Paint C = new Paint();
    private final Path D = new Path();
    private final TextPaint J = new TextPaint();
    private final Paint L = new Paint();
    private final Handler O = new Handler();
    private final Runnable P = new B9(this);

    public B8() {
        this.B.setColor(Color.argb(Notifications.NOTIFICATION_TYPES_ALL, 36, 36, 36));
        this.B.setStyle(Paint.Style.FILL_AND_STROKE);
        this.C.setAntiAlias(true);
        this.C.setColor(Color.argb(191, 0, 255, 0));
        this.C.setStrokeWidth(20.0f);
        this.C.setStyle(Paint.Style.STROKE);
        this.J.setAntiAlias(true);
        this.J.setColor(-1);
        this.J.setStyle(Paint.Style.FILL_AND_STROKE);
        this.J.setTextSize(30.0f);
        this.L.setColor(Color.argb(212, 0, 0, 0));
        this.L.setStyle(Paint.Style.FILL_AND_STROKE);
        B();
    }

    public final boolean A() {
        return this.K;
    }

    public final void B() {
        this.F = 0;
        this.G = -1;
        this.H = "Initializing...";
        this.I = 100;
        this.M = null;
        this.N = -1L;
        this.Q = null;
        C(false);
    }

    public final void C(boolean active) {
        this.K = active;
        if (this.K) {
            this.O.post(this.P);
        } else {
            this.O.removeCallbacks(this.P);
        }
        invalidateSelf();
    }

    public final void D(String name) {
        this.E = name;
        F();
    }

    public final void E(int count, int index) {
        this.F = count;
        this.G = index;
        F();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        StringBuilder sb = new StringBuilder();
        if (this.F <= 0) {
            if (!TextUtils.isEmpty(this.M)) {
                sb.append(this.M);
                sb.append("\n");
            }
            if (!TextUtils.isEmpty(this.E)) {
                sb.append(this.E);
                sb.append("\n");
            }
            sb.append("Sdk ");
            sb.append(AdSdkVersion.BUILD);
            sb.append(", Loaded ");
            if (this.N > 0) {
                long jMax = Math.max(0L, System.currentTimeMillis() - this.N);
                int i = (int) (jMax / 3600000);
                long j = jMax % 3600000;
                int i2 = (int) (j / ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
                int i3 = (int) ((j % ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS) / 1000);
                if (i > 0) {
                    sb.append(i);
                    sb.append("h ");
                }
                if (i > 0 || i2 > 0) {
                    sb.append(i2);
                    sb.append("m ");
                }
                sb.append(i3);
                sb.append("s ago");
            } else {
                sb.append(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
            }
        } else {
            sb.append("Card ");
            sb.append(this.G + 1);
            sb.append(" of ");
            sb.append(this.F);
        }
        sb.append("\nView: ");
        if (this.Q == null || this.Q.get() == null) {
            sb.append("Viewability Checker not set");
        } else {
            C5I vc = this.Q.get();
            String line = vc.B();
            sb.append(line);
        }
        this.H = sb.toString();
        float fMax = -2.1474836E9f;
        for (String str : this.H.split("\n")) {
            fMax = Math.max(fMax, this.J.measureText(str, 0, str.length()));
        }
        this.I = (int) (0.5f + fMax);
        invalidateSelf();
    }

    public final void F(String id) {
        this.M = id;
        F();
    }

    public final void G(long ms) {
        this.N = ms;
        F();
    }

    public final void H(C5I checker) {
        this.Q = new WeakReference<>(checker);
        F();
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        if (!this.K) {
            return;
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        canvas.drawRect(0.0f, 0.0f, width, height, this.B);
        String str = this.H;
        TextPaint textPaint = this.J;
        int canvasHeight = this.I;
        StaticLayout staticLayout = new StaticLayout(str, textPaint, canvasHeight, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        float f = width / 2.0f;
        float f2 = height / 2.0f;
        float width2 = staticLayout.getWidth() / 2.0f;
        float height2 = staticLayout.getHeight() / 2.0f;
        float textMiddleY = f - width2;
        float canvasMiddleX = 40.0f + f + width2;
        canvas.drawRect(textMiddleY - 40.0f, (f2 - height2) - 40.0f, canvasMiddleX, 40.0f + f2 + height2, this.L);
        canvas.save();
        canvas.translate(f - width2, f2 - height2);
        staticLayout.draw(canvas);
        canvas.restore();
        this.D.reset();
        this.D.moveTo(0.0f, 0.0f);
        this.D.lineTo(width, 0.0f);
        this.D.lineTo(width, height);
        this.D.lineTo(0.0f, height);
        this.D.lineTo(0.0f, 0.0f);
        canvas.drawPath(this.D, this.C);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(@Nullable ColorFilter colorFilter) {
    }
}
