package com.facebook.ads.redexgen.X;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class BZ implements View.OnTouchListener {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                J6.O(view, BT.J);
                return false;
            case 1:
                J6.O(view, 0);
                return false;
            default:
                return false;
        }
    }
}
