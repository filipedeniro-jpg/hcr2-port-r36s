package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.ExtraHints;
import com.facebook.ads.InstreamVideoAdListener;
import com.facebook.ads.InstreamVideoAdView;
import com.facebook.ads.internal.api.InstreamVideoAdViewApi;
import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.EnumSet;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class G3 implements InstreamVideoAdViewApi {

    @Nullable
    private B8 B;

    @Nullable
    private InstreamVideoAdListener C;
    private boolean D;
    private final AdSize E;

    @Nullable
    private C01320l F;
    private final Context G;

    @Nullable
    private C2E H;

    @Nullable
    private String I;
    private final InstreamVideoAdView J;

    @Nullable
    private String K;
    private final String L;

    @Nullable
    private Bundle M;

    @Nullable
    private View N;

    public G3(InstreamVideoAdView instreamVideoAdView, Context context, Bundle previousState) {
        this(instreamVideoAdView, context, previousState.getString("placementID"), (AdSize) previousState.get("adSize"));
        this.M = previousState;
    }

    public G3(InstreamVideoAdView instreamVideoAdView, Context context, String placementID, AdSize adSize) {
        this.D = false;
        this.J = instreamVideoAdView;
        this.G = context;
        this.L = placementID;
        this.E = adSize;
        this.H = O();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M(InstreamVideoAdView instreamVideoAdView) {
        C8N c8nC = C8O.C(this.G, this.K);
        if (c8nC != null) {
            instreamVideoAdView.addView(c8nC, new RelativeLayout.LayoutParams(-1, -1));
        }
    }

    private void N(@Nullable String bidPayload) {
        if (this.H == null) {
            return;
        }
        if (this.M != null) {
            C01320l c01320l = (C01320l) new C0T().A(AdPlacementType.INSTREAM);
            this.F = c01320l;
            c01320l.B(this.J.getContext(), new G2(this), this.H.C, this.M.getBundle("adapter"), EnumSet.of(CacheFlag.NONE));
            return;
        }
        this.H.E(bidPayload);
    }

    private C2E O() {
        AnonymousClass25 anonymousClass25 = new AnonymousClass25(this.L, KW.INSTREAM_VIDEO, AdPlacementType.INSTREAM, KV.B(this.E), 1);
        anonymousClass25.D(this.I);
        anonymousClass25.D(this.K);
        this.H = new C2E(this.J.getContext(), anonymousClass25);
        this.H.L(new G1(this));
        return this.H;
    }

    private void P() {
        if (this.H != null) {
            this.H.P(true);
            this.H = null;
            this.H = O();
            this.F = null;
            this.D = false;
            this.J.removeAllViews();
        }
    }

    @Override // com.facebook.ads.internal.api.InstreamVideoAdViewApi, com.facebook.ads.Ad
    public final void destroy() {
        if (this.B != null && Build.VERSION.SDK_INT >= 18 && JA.s(this.G)) {
            this.B.B();
            if (this.N != null) {
                this.N.getOverlay().remove(this.B);
            }
        }
        P();
    }

    @Override // com.facebook.ads.internal.api.InstreamVideoAdViewApi, com.facebook.ads.Ad
    public final String getPlacementId() {
        return this.L;
    }

    @Override // com.facebook.ads.internal.api.InstreamVideoAdViewApi
    @Nullable
    public final Bundle getSaveInstanceState() {
        Bundle saveInstanceState;
        if (this.H == null) {
            return null;
        }
        InterfaceC01300j interfaceC01300j = this.F != null ? this.F : (AbstractC01310k) this.H.I;
        if (interfaceC01300j == null || (saveInstanceState = interfaceC01300j.getSaveInstanceState()) == null) {
            return null;
        }
        Bundle adapterState = new Bundle();
        adapterState.putBundle("adapter", saveInstanceState);
        adapterState.putString("placementID", this.L);
        adapterState.putSerializable("adSize", this.E);
        return adapterState;
    }

    @Override // com.facebook.ads.internal.api.InstreamVideoAdViewApi, com.facebook.ads.Ad
    public final boolean isAdInvalidated() {
        return this.H == null || this.H.F();
    }

    @Override // com.facebook.ads.internal.api.InstreamVideoAdViewApi
    public final boolean isAdLoaded() {
        return this.D;
    }

    @Override // com.facebook.ads.internal.api.InstreamVideoAdViewApi, com.facebook.ads.Ad
    public final void loadAd() {
        N(null);
    }

    @Override // com.facebook.ads.internal.api.InstreamVideoAdViewApi, com.facebook.ads.Ad
    public final void loadAdFromBid(String bidPayload) {
        N(bidPayload);
    }

    @Override // com.facebook.ads.internal.api.InstreamVideoAdViewApi
    public final void setAdListener(@Nullable InstreamVideoAdListener adListener) {
        this.C = adListener;
    }

    @Override // com.facebook.ads.internal.api.InstreamVideoAdViewApi, com.facebook.ads.Ad
    public final void setExtraHints(ExtraHints extraHints) {
        this.I = extraHints.getHints();
        this.K = extraHints.getMediationData();
    }

    @Override // com.facebook.ads.internal.api.InstreamVideoAdViewApi
    public final boolean show() {
        if (!this.D || (this.H == null && this.F == null)) {
            if (this.C == null) {
                return false;
            }
            this.C.onError(this.J, AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            return false;
        }
        if (this.F != null) {
            this.F.A();
        } else {
            this.H.N();
        }
        this.D = false;
        return true;
    }
}
