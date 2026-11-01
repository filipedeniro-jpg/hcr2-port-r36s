package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.ExtraHints;
import com.facebook.ads.internal.api.AdViewApi;
import com.facebook.ads.internal.api.AdViewParentApi;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.Locale;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public final class C0520Fj implements AdViewApi {

    @Nullable
    private AdListener B;
    private final KV C;
    private final AdView D;

    @Nullable
    private AnonymousClass29 E;
    private B8 F;

    @Nullable
    private String G;

    @Nullable
    private String H;
    private final DisplayMetrics I;
    private final AdViewParentApi J;
    private final String K;

    @Nullable
    private View L;

    @SuppressLint({"ConstructorMayLeakThis"})
    public C0520Fj(Context context, String placementId, AdSize adSize, AdViewParentApi parentApi, AdView adView) {
        if (adSize == null || adSize == AdSize.INTERSTITIAL) {
            throw new IllegalArgumentException("adSize");
        }
        this.I = adView.getContext().getResources().getDisplayMetrics();
        this.C = KV.B(adSize);
        this.K = placementId;
        this.J = parentApi;
        this.D = adView;
        AnonymousClass25 anonymousClass25 = new AnonymousClass25(placementId, KY.D(this.C), AdPlacementType.BANNER, KV.B(adSize), 1);
        anonymousClass25.D(this.G);
        anonymousClass25.G(this.H);
        this.E = new AnonymousClass29(context, anonymousClass25);
        this.E.L(new C0519Fi(this, adView, placementId));
    }

    @SuppressLint({"ConstructorMayLeakThis"})
    public C0520Fj(Context context, String placementId, String bidPayload, AdViewParentApi parentApi, AdView adView) throws KR {
        this(context, placementId, L(bidPayload), parentApi, adView);
    }

    private static AdSize L(String bidPayload) throws KR {
        KW kwB = C0640Kb.B(bidPayload);
        if (kwB == null) {
            throw new KR(AdErrorType.BID_PAYLOAD_ERROR, String.format(Locale.US, "Cannot find a template that load bid '%s'", bidPayload));
        }
        C0640Kb.C(kwB);
        return KY.B(kwB);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M(RelativeLayout adView, View visibleAdView) {
        C8N c8nC;
        if (this.H != null && (c8nC = C8O.C(adView.getContext(), this.H)) != null) {
            ViewGroup.LayoutParams layoutParams = visibleAdView.getLayoutParams();
            adView.addView(c8nC, new RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height));
        }
    }

    private void N(@Nullable String bidPayload) {
        if (this.E != null) {
            this.E.E(bidPayload);
        }
    }

    @Override // com.facebook.ads.Ad
    public final void destroy() {
        C0642Kd.B("destroy", "Banner ad destroyed");
        if (this.E != null) {
            this.E.P(true);
            this.E = null;
        }
        if (Build.VERSION.SDK_INT >= 18 && this.F != null && JA.s(this.D.getContext())) {
            this.F.B();
            if (this.L != null) {
                this.L.getOverlay().remove(this.F);
            }
        }
        this.D.removeAllViews();
        this.L = null;
        this.B = null;
    }

    @Override // com.facebook.ads.Ad
    public final String getPlacementId() {
        return this.K;
    }

    @Override // com.facebook.ads.Ad
    public final boolean isAdInvalidated() {
        return this.E == null || this.E.F();
    }

    @Override // com.facebook.ads.Ad
    public final void loadAd() {
        C0642Kd.B("loadAd", "Banner ad load requested");
        N(null);
    }

    @Override // com.facebook.ads.Ad
    public final void loadAdFromBid(String str) {
        C0642Kd.B("loadAdFromBid", "Banner ad load requested");
        N(str);
    }

    @Override // com.facebook.ads.internal.api.AdViewApi, com.facebook.ads.internal.api.AdViewParentApi
    public final void onConfigurationChanged(Configuration newConfig) {
        this.J.onConfigurationChanged(newConfig);
        if (this.L != null) {
            KY.E(this.I, this.L, this.C);
        }
    }

    @Override // com.facebook.ads.internal.api.AdViewApi
    public final void setAdListener(@Nullable AdListener adListener) {
        this.B = adListener;
    }

    @Override // com.facebook.ads.internal.api.AdViewApi, com.facebook.ads.Ad
    public final void setExtraHints(ExtraHints extraHints) {
        this.G = extraHints.getHints();
        this.H = extraHints.getMediationData();
    }
}
