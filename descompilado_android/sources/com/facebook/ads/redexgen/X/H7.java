package com.facebook.ads.redexgen.X;

import android.view.MotionEvent;
import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class H7 implements View.OnClickListener, View.OnTouchListener {
    public final WeakReference<C03217s> B;
    public final WeakReference<FV> C;

    public H7(FV chooseYourOwnAdFullscreenView, C03217s adListItemView) {
        this.C = new WeakReference<>(chooseYourOwnAdFullscreenView);
        this.B = new WeakReference<>(adListItemView);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        if (this.C.get() != null && this.B.get() != null && !this.B.get().C()) {
            this.C.get().L(this.B.get().getAdDataBundle());
        }
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View v, MotionEvent event) {
        if (this.C.get() != null) {
            this.C.get().getTouchDataRecorder().F(event, this.C.get(), v);
            return false;
        }
        return false;
    }
}
