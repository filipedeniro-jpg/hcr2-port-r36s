package com.facebook.ads.redexgen.X;

import android.support.annotation.RequiresApi;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9V, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@RequiresApi(23)
public class C9V extends C9U {
    @Override // com.facebook.ads.redexgen.X.C9U, com.facebook.ads.redexgen.X.C9N
    public final void K(View view, int offset) {
        view.offsetTopAndBottom(offset);
    }

    @Override // com.facebook.ads.redexgen.X.C9U, com.facebook.ads.redexgen.X.C9N
    public final void L(View view, int offset) {
        view.offsetLeftAndRight(offset);
    }
}
