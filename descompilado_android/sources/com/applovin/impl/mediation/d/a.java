package com.applovin.impl.mediation.d;

import com.applovin.impl.sdk.h;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;

/* JADX INFO: loaded from: classes.dex */
public class a implements MaxAdListener, MaxAdViewAdListener, MaxRewardedAdListener {
    private final h a;
    private final MaxAdListener b;

    public a(MaxAdListener maxAdListener, h hVar) {
        this.a = hVar;
        this.b = maxAdListener;
    }

    @Override // com.applovin.mediation.MaxAdListener
    public void onAdClicked(MaxAd maxAd) {
        com.applovin.impl.sdk.utils.h.d(this.b, maxAd, this.a);
    }

    @Override // com.applovin.mediation.MaxAdViewAdListener
    public void onAdCollapsed(MaxAd maxAd) {
        com.applovin.impl.sdk.utils.h.h(this.b, maxAd, this.a);
    }

    @Override // com.applovin.mediation.MaxAdListener
    public void onAdDisplayFailed(MaxAd maxAd, int i) {
        com.applovin.impl.sdk.utils.h.a(this.b, maxAd, i, this.a);
    }

    @Override // com.applovin.mediation.MaxAdListener
    public void onAdDisplayed(MaxAd maxAd) {
        com.applovin.impl.sdk.utils.h.b(this.b, maxAd, this.a);
    }

    @Override // com.applovin.mediation.MaxAdViewAdListener
    public void onAdExpanded(MaxAd maxAd) {
        com.applovin.impl.sdk.utils.h.g(this.b, maxAd, this.a);
    }

    @Override // com.applovin.mediation.MaxAdListener
    public void onAdHidden(MaxAd maxAd) {
        com.applovin.impl.sdk.utils.h.c(this.b, maxAd, this.a);
    }

    @Override // com.applovin.mediation.MaxAdListener
    public void onAdLoadFailed(String str, int i) {
        com.applovin.impl.sdk.utils.h.a(this.b, str, i, this.a);
    }

    @Override // com.applovin.mediation.MaxAdListener
    public void onAdLoaded(MaxAd maxAd) {
        com.applovin.impl.sdk.utils.h.a(this.b, maxAd, this.a);
    }

    @Override // com.applovin.mediation.MaxRewardedAdListener
    public void onRewardedVideoCompleted(MaxAd maxAd) {
        com.applovin.impl.sdk.utils.h.f(this.b, maxAd, this.a);
    }

    @Override // com.applovin.mediation.MaxRewardedAdListener
    public void onRewardedVideoStarted(MaxAd maxAd) {
        com.applovin.impl.sdk.utils.h.e(this.b, maxAd, this.a);
    }

    @Override // com.applovin.mediation.MaxRewardedAdListener
    public void onUserRewarded(MaxAd maxAd, MaxReward maxReward) {
        com.applovin.impl.sdk.utils.h.a(this.b, maxAd, maxReward, this.a);
    }
}
