package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ji, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0621Ji {
    private static final String T = C0621Ji.class.getSimpleName();
    private boolean G;

    @Nullable
    private View I;

    @Nullable
    private View Q;
    private int C = -1;
    private int D = -1;
    private int E = -1;
    private int B = -1;
    private long F = -1;
    private int L = -1;
    private long P = -1;
    private long H = -1;
    private int R = -1;
    private int S = -1;
    private int J = -1;
    private int K = -1;
    private float M = -1.0f;
    private float O = -1.0f;
    private float N = -1.0f;

    public final long A() {
        if (D()) {
            return System.currentTimeMillis() - this.F;
        }
        return -1L;
    }

    private MZ B() {
        if (this.Q == null || this.I == null) {
            return MZ.INTERNAL_NULL_VIEW;
        }
        if (this.Q != this.I) {
            return MZ.INTERNAL_NO_CLICK;
        }
        if (Build.VERSION.SDK_INT < 4) {
            return MZ.INTERNAL_API_TOO_LOW;
        }
        Object tag = this.Q.getTag(MZ.E);
        if (tag == null) {
            return MZ.INTERNAL_NO_TAG;
        }
        if (!(tag instanceof MZ)) {
            return MZ.INTERNAL_WRONG_TAG_CLASS;
        }
        return (MZ) tag;
    }

    /* JADX INFO: renamed from: B, reason: collision with other method in class */
    public final Map<String, String> m258B() {
        if (!this.G) {
            return null;
        }
        String strValueOf = String.valueOf((this.O * this.N) / 2.0f);
        long j = (this.F <= 0 || this.H <= this.F) ? -1L : this.H - this.F;
        HashMap map = new HashMap();
        map.put("adPositionX", String.valueOf(this.C));
        map.put("adPositionY", String.valueOf(this.D));
        map.put("width", String.valueOf(this.E));
        map.put("height", String.valueOf(this.B));
        map.put("clickDelayTime", String.valueOf(j));
        map.put("startTime", String.valueOf(this.P));
        map.put("endTime", String.valueOf(this.H));
        map.put("startX", String.valueOf(this.R));
        map.put("startY", String.valueOf(this.S));
        map.put("clickX", String.valueOf(this.J));
        map.put("clickY", String.valueOf(this.K));
        map.put("endX", String.valueOf(this.J));
        map.put("endY", String.valueOf(this.K));
        map.put("force", String.valueOf(this.M));
        map.put("radiusX", strValueOf);
        map.put("radiusY", strValueOf);
        map.put("clickedViewTag", String.valueOf(B().A()));
        return map;
    }

    public final boolean C(Context context) {
        int iV = JA.V(context);
        return iV >= 0 && A() < ((long) iV);
    }

    public final boolean D() {
        return this.F != -1;
    }

    public final boolean E() {
        return this.G;
    }

    public final void F(MotionEvent event, View adView, View clickedView) {
        if (!this.G) {
            this.G = true;
            InputDevice device = event.getDevice();
            if (device != null) {
                InputDevice.MotionRange yr = device.getMotionRange(0);
                InputDevice.MotionRange xr = device.getMotionRange(1);
                if (yr != null && xr != null) {
                    this.N = Math.min(yr.getRange(), xr.getRange());
                }
            }
            if (this.N <= 0.0f) {
                this.N = Math.min(adView.getMeasuredWidth(), adView.getMeasuredHeight());
            }
        }
        int[] iArr = new int[2];
        adView.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        clickedView.getLocationInWindow(iArr2);
        switch (event.getAction()) {
            case 0:
                this.C = (int) (iArr[0] / J6.B);
                this.D = (int) (iArr[1] / J6.B);
                this.E = (int) (adView.getWidth() / J6.B);
                this.B = (int) (adView.getHeight() / J6.B);
                this.L = 1;
                this.P = System.currentTimeMillis();
                int touchStartXInPx = (int) (((((int) (event.getX() + 0.5f)) + iArr2[0]) - iArr[0]) / J6.B);
                this.R = touchStartXInPx;
                int y = (int) (event.getY() + 0.5f);
                int touchStartXInPx2 = iArr2[1];
                int i = y + touchStartXInPx2;
                int touchStartXInPx3 = iArr[1];
                this.S = (int) ((i - touchStartXInPx3) / J6.B);
                this.M = event.getPressure();
                this.O = event.getSize();
                this.Q = clickedView;
                break;
            case 1:
            case 3:
                this.H = System.currentTimeMillis();
                this.J = (int) (((((int) (event.getX() + 0.5f)) + iArr2[0]) - iArr[0]) / J6.B);
                int touchEndYInPx = (int) (((((int) (event.getY() + 0.5f)) + iArr2[1]) - iArr[1]) / J6.B);
                this.K = touchEndYInPx;
                this.I = clickedView;
                break;
            case 2:
                this.M -= this.M / this.L;
                this.M += event.getPressure() / this.L;
                this.O -= this.O / this.L;
                this.O += event.getSize() / this.L;
                this.L++;
                break;
        }
    }

    public final void G() {
        this.F = System.currentTimeMillis();
    }
}
