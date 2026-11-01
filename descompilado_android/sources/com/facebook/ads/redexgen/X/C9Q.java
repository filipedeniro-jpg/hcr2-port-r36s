package com.facebook.ads.redexgen.X;

import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9Q, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@RequiresApi(17)
public class C9Q extends C9P {
    @Override // com.facebook.ads.redexgen.X.C9N
    public final Display P(View view) {
        return view.getDisplay();
    }

    @Override // com.facebook.ads.redexgen.X.C9N
    public final int U(View view) {
        return view.getLayoutDirection();
    }
}
