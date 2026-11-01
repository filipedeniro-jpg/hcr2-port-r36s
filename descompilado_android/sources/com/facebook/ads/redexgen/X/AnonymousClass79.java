package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.79, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AnonymousClass79 extends RelativeLayout implements F0 {

    @Nullable
    private C0447Co B;

    public AnonymousClass79(Context context) {
        super(context);
    }

    public AnonymousClass79(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    }

    public void A() {
    }

    public void B() {
    }

    @Override // com.facebook.ads.redexgen.X.F0
    public final void cC(C0447Co videoView) {
        this.B = videoView;
        A();
    }

    @Nullable
    public C0447Co getVideoView() {
        return this.B;
    }

    @Override // com.facebook.ads.redexgen.X.F0
    public final void wF(C0447Co videoView) {
        B();
        this.B = null;
    }
}
