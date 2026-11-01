package com.facebook.ads.redexgen.X;

import android.text.TextUtils;
import com.facebook.ads.NativeAdBase;
import com.facebook.internal.AnalyticsEvents;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class GZ implements AnonymousClass03 {
    public final /* synthetic */ C0537Ga B;
    public final /* synthetic */ NativeAdBase.MediaCacheFlag C;

    public GZ(C0537Ga this$0, NativeAdBase.MediaCacheFlag mediaCacheFlag) {
        this.B = this$0;
        this.C = mediaCacheFlag;
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass03
    public final void gE(List<C01360p> list) {
        H2 h2 = new H2(this.B.B);
        String strM = "unknown";
        for (C01360p nativeAdapter : list) {
            if ("unknown".equals(strM)) {
                strM = nativeAdapter.M();
            }
            if (this.C.equals(NativeAdBase.MediaCacheFlag.ALL)) {
                if (nativeAdapter.G() != null) {
                    h2.B(new C0560Gy(nativeAdapter.G().getUrl(), nativeAdapter.G().getHeight(), nativeAdapter.G().getWidth(), nativeAdapter.M(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE));
                }
                if (nativeAdapter.F() != null) {
                    h2.B(new C0560Gy(nativeAdapter.F().getUrl(), nativeAdapter.F().getHeight(), nativeAdapter.F().getWidth(), nativeAdapter.M(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE));
                }
                if (!TextUtils.isEmpty(nativeAdapter.S())) {
                    h2.E(new H0(nativeAdapter.S(), nativeAdapter.M(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE));
                }
            }
        }
        h2.H(new GY(this, list), new C0558Gw(strM, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE));
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass03
    public final void yD(KQ error) {
        if (this.B.G == null) {
            return;
        }
        this.B.G.onAdError(KQ.E(error));
    }
}
