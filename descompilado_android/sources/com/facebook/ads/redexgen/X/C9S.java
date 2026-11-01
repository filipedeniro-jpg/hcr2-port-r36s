package com.facebook.ads.redexgen.X;

import android.support.annotation.RequiresApi;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9S, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@RequiresApi(19)
public class C9S extends C9R {
    @Override // com.facebook.ads.redexgen.X.C9P, com.facebook.ads.redexgen.X.C9N
    public final void C(View view, int mode) {
        view.setImportantForAccessibility(mode);
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public final boolean M(View view) {
        return view.isAttachedToWindow();
    }
}
