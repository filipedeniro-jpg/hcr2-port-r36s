package com.facebook.ads.redexgen.X;

import android.widget.ImageView;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8L, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C8L implements C1D {
    public final WeakReference<ImageView> B;

    private C8L(ImageView iconImageView) {
        this.B = new WeakReference<>(iconImageView);
    }

    @Override // com.facebook.ads.redexgen.X.C1D
    public final void FE(boolean success) {
        if (!success && this.B.get() != null) {
            this.B.get().setVisibility(8);
        }
    }
}
