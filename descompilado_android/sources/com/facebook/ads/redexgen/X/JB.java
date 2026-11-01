package com.facebook.ads.redexgen.X;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class JB implements Runnable {
    public final /* synthetic */ int B;
    public final /* synthetic */ View C;
    public final /* synthetic */ View D;
    public final /* synthetic */ int E;

    public JB(View view, View view2, int i, int i2) {
        this.C = view;
        this.D = view2;
        this.E = i;
        this.B = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Rect rect = new Rect();
        this.C.getHitRect(rect);
        View view = (View) this.C.getParent();
        Rect rect2 = new Rect();
        view.getGlobalVisibleRect(rect2);
        Rect rect3 = new Rect();
        this.D.getGlobalVisibleRect(rect3);
        int i = rect2.left - rect3.left;
        int i2 = rect2.top - rect3.top;
        int i3 = rect.left;
        int deltaY = this.E;
        rect.left = i3 + (i - deltaY);
        int i4 = rect.top;
        int deltaY2 = this.B;
        rect.top = i4 + (i2 - deltaY2);
        int i5 = rect.right;
        int deltaY3 = this.E;
        rect.right = i5 + deltaY3 + i;
        int i6 = rect.bottom;
        int deltaY4 = this.B;
        rect.bottom = i6 + deltaY4 + i2;
        this.D.setTouchDelegate(new TouchDelegate(rect, this.C));
    }
}
