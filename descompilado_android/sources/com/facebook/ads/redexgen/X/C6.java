package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C6 extends View {
    private K6 B;

    public C6(Context context, K6 mListener) {
        super(context);
        this.B = mListener;
        setLayoutParams(new ViewGroup.LayoutParams(0, 0));
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int visibility) {
        if (this.B != null) {
            this.B.PF(visibility);
        }
    }
}
