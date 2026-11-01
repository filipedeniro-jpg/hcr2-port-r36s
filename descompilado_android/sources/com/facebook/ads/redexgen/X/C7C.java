package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7C, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C7C extends RelativeLayout {
    private WeakReference<InterfaceC0627Jo> B;

    @Nullable
    private AnonymousClass67 C;
    private final C5M D;

    public C7C(Context context, C5M videoView) {
        super(context);
        this.D = videoView;
        J6.M((View) this.D);
        addView(this.D.getView(), new RelativeLayout.LayoutParams(-1, -1));
    }

    public final void A(AnonymousClass79 placeholderImagePlugin) {
        addView(placeholderImagePlugin, new RelativeLayout.LayoutParams(-1, -1));
        this.C = (AnonymousClass67) placeholderImagePlugin;
    }

    public final boolean B(int timeMs) {
        return this.D.getCurrentPosition() > timeMs;
    }

    public final void C(AnonymousClass79 placeholderImagePlugin) {
        J6.M(placeholderImagePlugin);
        this.C = null;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean changed, int l, int t, int r, int b) {
        ((View) this.D).layout(0, 0, getWidth(), getHeight());
        if (this.C != null) {
            this.C.layout(0, 0, getWidth(), getHeight());
        }
    }

    @Override // android.widget.RelativeLayout, android.view.View
    public final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mVideoWidth = 0;
        int widthSpecMode = this.D.getVideoWidth();
        int videoHeight = this.D.getVideoHeight();
        int defaultSize = getDefaultSize(widthSpecMode, widthMeasureSpec);
        int widthSpecSize = getDefaultSize(videoHeight, heightMeasureSpec);
        if (widthSpecMode > 0 && videoHeight > 0) {
            mVideoWidth = 1;
            int mode = View.MeasureSpec.getMode(widthMeasureSpec);
            int size = View.MeasureSpec.getSize(widthMeasureSpec);
            int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
            int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
            if (mode == 1073741824 && mode2 == 1073741824) {
                defaultSize = size;
                widthSpecSize = size2;
                if (widthSpecMode * widthSpecSize < defaultSize * videoHeight) {
                    defaultSize = (widthSpecSize * widthSpecMode) / videoHeight;
                } else if (widthSpecMode * widthSpecSize > defaultSize * videoHeight) {
                    widthSpecSize = (defaultSize * videoHeight) / widthSpecMode;
                }
            } else if (mode == 1073741824) {
                defaultSize = size;
                widthSpecSize = (defaultSize * videoHeight) / widthSpecMode;
                if (mode2 == Integer.MIN_VALUE && widthSpecSize > size2) {
                    widthSpecSize = size2;
                }
            } else if (mode2 == 1073741824) {
                widthSpecSize = size2;
                defaultSize = (widthSpecSize * widthSpecMode) / videoHeight;
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize = size;
                }
            } else {
                defaultSize = widthSpecMode;
                widthSpecSize = videoHeight;
                if (mode2 == Integer.MIN_VALUE && widthSpecSize > size2) {
                    widthSpecSize = size2;
                    defaultSize = (widthSpecSize * widthSpecMode) / videoHeight;
                }
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize = size;
                    widthSpecSize = (defaultSize * videoHeight) / widthSpecMode;
                }
            }
        }
        setMeasuredDimension(defaultSize, widthSpecSize);
        if (mVideoWidth != 0 && this.B != null && this.B.get() != null) {
            this.B.get().LF();
        }
    }

    public void setViewImplInflationListener(InterfaceC0627Jo listener) {
        this.B = new WeakReference<>(listener);
    }
}
