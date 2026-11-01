package com.facebook.ads.redexgen.X;

import android.view.View;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.internal.api.NativeComponentTagApi;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0544Gh implements NativeComponentTagApi {
    @Override // com.facebook.ads.internal.api.NativeComponentTagApi
    public final void tagView(View view, NativeAdBase.NativeComponentTag tag) {
        if (view != null && tag != null) {
            MZ.B(view, tag);
        }
    }
}
