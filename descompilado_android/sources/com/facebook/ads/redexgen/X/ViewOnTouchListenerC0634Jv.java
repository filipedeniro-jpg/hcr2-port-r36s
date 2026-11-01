package com.facebook.ads.redexgen.X;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Jv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class ViewOnTouchListenerC0634Jv implements View.OnTouchListener {
    public final /* synthetic */ C0638Jz B;

    public ViewOnTouchListenerC0634Jv(C0638Jz this$0) {
        this.B = this$0;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View v, MotionEvent event) {
        if (this.B.I != null && event.getAction() == 1) {
            this.B.I.R();
        }
        return true;
    }
}
