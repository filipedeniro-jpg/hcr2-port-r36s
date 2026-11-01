package com.facebook.ads.redexgen.X;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FW implements View.OnTouchListener {
    public final /* synthetic */ C0513Fc B;

    public FW(C0513Fc this$0) {
        this.B = this$0;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() != 0) {
            return false;
        }
        if (this.B.B) {
            ((KF) this.B.G.getInternalNativeAd()).P();
        } else {
            this.B.K();
        }
        return true;
    }
}
