package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9k, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C03659k extends ViewGroup.LayoutParams {
    public int B;
    public int C;
    public boolean D;
    public boolean E;
    public int F;
    public float G;

    public C03659k() {
        super(-1, -1);
        this.G = 0.0f;
    }

    public C03659k(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.G = 0.0f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, C03739s.GB);
        this.C = typedArrayObtainStyledAttributes.getInteger(0, 48);
        typedArrayObtainStyledAttributes.recycle();
    }
}
