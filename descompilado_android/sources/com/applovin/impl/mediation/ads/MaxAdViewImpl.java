package com.applovin.impl.mediation.ads;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.applovin.impl.mediation.e;
import com.applovin.impl.sdk.d;
import com.applovin.impl.sdk.d.aa;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.n;
import com.applovin.impl.sdk.t;
import com.applovin.impl.sdk.u;
import com.applovin.impl.sdk.utils.h;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class MaxAdViewImpl extends com.applovin.impl.mediation.ads.a.a implements d.a, u.a {
    private final Activity a;
    private final MaxAdView b;
    private MaxAd c;
    private String d;
    private final a e;
    private final c f;
    private final d g;
    private final t h;
    private final u i;
    private final Object j;
    private com.applovin.impl.mediation.b.b k;
    private boolean l;
    private boolean m;
    private boolean n;

    /* JADX INFO: renamed from: com.applovin.impl.mediation.ads.MaxAdViewImpl$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ com.applovin.impl.mediation.b.b a;

        AnonymousClass2(com.applovin.impl.mediation.b.b bVar) {
            this.a = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            n nVar;
            String str;
            String str2;
            if (this.a.k() != null) {
                final MaxAdView maxAdView = MaxAdViewImpl.this.b;
                if (maxAdView != null) {
                    MaxAdViewImpl.this.a(new AnimatorListenerAdapter() { // from class: com.applovin.impl.mediation.ads.MaxAdViewImpl.2.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            super.onAnimationEnd(animator);
                            MaxAdViewImpl.this.a();
                            if (AnonymousClass2.this.a.q()) {
                                MaxAdViewImpl.this.i.a(MaxAdViewImpl.this.a, AnonymousClass2.this.a);
                            }
                            MaxAdViewImpl.this.a(AnonymousClass2.this.a, maxAdView);
                            synchronized (MaxAdViewImpl.this.j) {
                                MaxAdViewImpl.this.k = AnonymousClass2.this.a;
                            }
                            MaxAdViewImpl.this.logger.a(MaxAdViewImpl.this.tag, "Scheduling impression for ad manually...");
                            MaxAdViewImpl.this.sdk.a(MaxAdViewImpl.this.a).maybeScheduleRawAdImpressionPostback(AnonymousClass2.this.a);
                            AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() { // from class: com.applovin.impl.mediation.ads.MaxAdViewImpl.2.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    long jA = MaxAdViewImpl.this.h.a(AnonymousClass2.this.a);
                                    if (!AnonymousClass2.this.a.q()) {
                                        MaxAdViewImpl.this.a(AnonymousClass2.this.a, jA);
                                    }
                                    MaxAdViewImpl.this.a(jA);
                                }
                            }, AnonymousClass2.this.a.l());
                        }
                    });
                    return;
                } else {
                    nVar = MaxAdViewImpl.this.logger;
                    str = MaxAdViewImpl.this.tag;
                    str2 = "Max ad view does not have a parent View";
                }
            } else {
                nVar = MaxAdViewImpl.this.logger;
                str = MaxAdViewImpl.this.tag;
                str2 = "Max ad does not have a loaded ad view";
            }
            nVar.d(str, str2);
            MaxAdViewImpl.this.e.onAdDisplayFailed(this.a, -5201);
        }
    }

    private class a extends b {
        private a() {
            super();
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdLoadFailed(String str, int i) {
            h.a(MaxAdViewImpl.this.adListener, str, i, MaxAdViewImpl.this.sdk);
            MaxAdViewImpl.this.a(i);
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdLoaded(MaxAd maxAd) {
            com.applovin.impl.mediation.b.a aVarA;
            if (maxAd instanceof e) {
                aVarA = ((e) maxAd).a(MaxAdViewImpl.this.a);
            } else {
                if (!(maxAd instanceof com.applovin.impl.mediation.b.a)) {
                    throw new IllegalArgumentException("Unknown type of loaded ad: " + maxAd.getClass().getName());
                }
                aVarA = (com.applovin.impl.mediation.b.a) maxAd;
            }
            if (!(aVarA instanceof com.applovin.impl.mediation.b.b)) {
                MaxAdViewImpl.this.logger.d(MaxAdViewImpl.this.tag, "Not a MediatedAdViewAd received: " + maxAd);
                onAdLoadFailed(MaxAdViewImpl.this.adUnitId, -5201);
                return;
            }
            com.applovin.impl.mediation.b.b bVar = (com.applovin.impl.mediation.b.b) aVarA;
            bVar.d(MaxAdViewImpl.this.d);
            MaxAdViewImpl.this.a(bVar);
            if (bVar.A()) {
                long jB = bVar.B();
                MaxAdViewImpl.this.sdk.w().a(MaxAdViewImpl.this.tag, "Scheduling banner ad refresh " + jB + " milliseconds from now for '" + MaxAdViewImpl.this.adUnitId + "'...");
                MaxAdViewImpl.this.g.a(jB);
            }
            h.a(MaxAdViewImpl.this.adListener, maxAd, MaxAdViewImpl.this.sdk);
        }
    }

    private abstract class b implements MaxAdListener, MaxAdViewAdListener {
        private b() {
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdClicked(MaxAd maxAd) {
            if (maxAd.equals(MaxAdViewImpl.this.k)) {
                h.d(MaxAdViewImpl.this.adListener, maxAd, MaxAdViewImpl.this.sdk);
            }
        }

        @Override // com.applovin.mediation.MaxAdViewAdListener
        public void onAdCollapsed(MaxAd maxAd) {
            if (maxAd.equals(MaxAdViewImpl.this.k)) {
                h.h(MaxAdViewImpl.this.adListener, maxAd, MaxAdViewImpl.this.sdk);
            }
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdDisplayFailed(MaxAd maxAd, int i) {
            if (maxAd.equals(MaxAdViewImpl.this.k)) {
                h.a(MaxAdViewImpl.this.adListener, maxAd, i, MaxAdViewImpl.this.sdk);
            }
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdDisplayed(MaxAd maxAd) {
            if (maxAd.equals(MaxAdViewImpl.this.k)) {
                h.b(MaxAdViewImpl.this.adListener, maxAd, MaxAdViewImpl.this.sdk);
            }
        }

        @Override // com.applovin.mediation.MaxAdViewAdListener
        public void onAdExpanded(MaxAd maxAd) {
            if (maxAd.equals(MaxAdViewImpl.this.k)) {
                h.g(MaxAdViewImpl.this.adListener, maxAd, MaxAdViewImpl.this.sdk);
            }
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdHidden(MaxAd maxAd) {
            if (maxAd.equals(MaxAdViewImpl.this.k)) {
                h.c(MaxAdViewImpl.this.adListener, maxAd, MaxAdViewImpl.this.sdk);
            }
        }
    }

    private class c extends b {
        private c() {
            super();
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdLoadFailed(String str, int i) {
            MaxAdViewImpl.this.logger.a(MaxAdViewImpl.this.tag, "Failed to pre-cache ad for refresh with error code " + i);
            MaxAdViewImpl.this.a(i);
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdLoaded(MaxAd maxAd) {
            MaxAdViewImpl.this.logger.a(MaxAdViewImpl.this.tag, "Successfully pre-cached ad for refresh");
            MaxAdViewImpl.this.a(maxAd);
        }
    }

    public MaxAdViewImpl(String str, MaxAdView maxAdView, com.applovin.impl.sdk.h hVar, Activity activity) {
        super(str, "MaxAdView", hVar);
        this.j = new Object();
        this.k = null;
        this.n = false;
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        this.a = activity;
        this.b = maxAdView;
        this.e = new a();
        this.f = new c();
        this.g = new d(hVar, this);
        this.h = new t(maxAdView, hVar);
        this.i = new u(maxAdView, hVar, this);
        this.logger.a(this.tag, "Created new MaxAdView (" + this + ")");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        com.applovin.impl.mediation.b.b bVar;
        MaxAdView maxAdView = this.b;
        if (maxAdView != null) {
            maxAdView.removeAllViews();
        }
        this.i.a();
        synchronized (this.j) {
            bVar = this.k;
        }
        if (bVar != null) {
            this.sdk.a(this.a).destroyAd(bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (this.sdk.b(com.applovin.impl.sdk.b.a.y).contains(String.valueOf(i))) {
            this.sdk.w().a(this.tag, "Ignoring banner ad refresh for error code '" + i + "'...");
            return;
        }
        this.l = true;
        long jLongValue = ((Long) this.sdk.a(com.applovin.impl.sdk.b.a.x)).longValue();
        if (jLongValue >= 0) {
            this.sdk.w().a(this.tag, "Scheduling failed banner ad refresh " + jLongValue + " milliseconds from now for '" + this.adUnitId + "'...");
            this.g.a(jLongValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        if (!com.applovin.impl.sdk.utils.n.a(j, ((Long) this.sdk.a(com.applovin.impl.sdk.b.a.I)).longValue())) {
            this.logger.a(this.tag, "No undesired viewability flags matched - scheduling viewability");
            this.l = false;
            b();
            return;
        }
        this.logger.a(this.tag, "Undesired flags matched - current: " + Long.toBinaryString(j) + ", undesired: " + Long.toBinaryString(j));
        this.logger.a(this.tag, "Waiting for refresh timer to manually fire request");
        this.l = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AnimatorListenerAdapter animatorListenerAdapter) {
        com.applovin.impl.mediation.b.b bVar = this.k;
        if (bVar == null) {
            animatorListenerAdapter.onAnimationEnd(null);
            return;
        }
        View viewK = bVar.k();
        viewK.animate().alpha(0.0f).setDuration(((Long) this.sdk.a(com.applovin.impl.sdk.b.a.D)).longValue()).setListener(animatorListenerAdapter).start();
    }

    private static void a(View view, com.applovin.impl.mediation.b.b bVar) {
        DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
        int iApplyDimension = bVar.i() == -1 ? -1 : (int) TypedValue.applyDimension(1, bVar.i(), displayMetrics);
        int iApplyDimension2 = bVar.j() != -1 ? (int) TypedValue.applyDimension(1, bVar.j(), displayMetrics) : -1;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        }
        layoutParams.width = iApplyDimension;
        layoutParams.height = iApplyDimension2;
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.addRule(10);
            layoutParams2.addRule(9);
        }
        view.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.applovin.impl.mediation.b.b bVar) {
        AppLovinSdkUtils.runOnUiThread(new AnonymousClass2(bVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.applovin.impl.mediation.b.b bVar, long j) {
        this.logger.a(this.tag, "Scheduling viewability impression for ad...");
        this.sdk.a(this.a).maybeScheduleViewabilityAdImpressionPostback(bVar, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.applovin.impl.mediation.b.b bVar, MaxAdView maxAdView) {
        View viewK = bVar.k();
        viewK.setAlpha(0.0f);
        a(viewK, bVar);
        maxAdView.setBackgroundColor(0);
        maxAdView.addView(viewK);
        viewK.animate().alpha(1.0f).setDuration(((Long) this.sdk.a(com.applovin.impl.sdk.b.a.C)).longValue()).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MaxAd maxAd) {
        if (!this.m) {
            this.c = maxAd;
            return;
        }
        this.m = false;
        this.logger.a(this.tag, "Rendering precache request ad: " + maxAd.getAdUnitId() + "...");
        this.e.onAdLoaded(maxAd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final MaxAdListener maxAdListener) {
        if (!d()) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.mediation.ads.MaxAdViewImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    if (MaxAdViewImpl.this.k != null) {
                        MaxAdViewImpl.this.loadRequestBuilder.a("visible_ad_ad_unit_id", MaxAdViewImpl.this.k.getAdUnitId()).a("viewability_flags", String.valueOf(MaxAdViewImpl.this.h.a(MaxAdViewImpl.this.k)));
                    } else {
                        MaxAdViewImpl.this.loadRequestBuilder.a("visible_ad_ad_unit_id").a("viewability_flags");
                    }
                    MaxAdViewImpl.this.logger.a(MaxAdViewImpl.this.tag, "Loading banner ad for '" + MaxAdViewImpl.this.adUnitId + "' and notifying " + maxAdListener + "...");
                    MaxAdViewImpl.this.sdk.a(MaxAdViewImpl.this.a).loadAd(MaxAdViewImpl.this.adUnitId, MaxAdFormat.BANNER, MaxAdViewImpl.this.loadRequestBuilder.a(), MaxAdViewImpl.this.a, maxAdListener);
                }
            });
        } else {
            this.logger.e(this.tag, "Unable to load new ad; ad is already destroyed");
            h.a(this.adListener, this.adUnitId, -1, this.sdk);
        }
    }

    private void b() {
        if (c()) {
            long jLongValue = ((Long) this.sdk.a(com.applovin.impl.sdk.b.a.J)).longValue();
            this.logger.a(this.tag, "Scheduling refresh precache request in " + TimeUnit.MICROSECONDS.toSeconds(jLongValue) + " seconds...");
            this.sdk.F().a(new aa(this.sdk, new Runnable() { // from class: com.applovin.impl.mediation.ads.MaxAdViewImpl.3
                @Override // java.lang.Runnable
                public void run() {
                    MaxAdViewImpl maxAdViewImpl = MaxAdViewImpl.this;
                    maxAdViewImpl.a(maxAdViewImpl.f);
                }
            }), com.applovin.impl.mediation.d.c.a(MaxAdFormat.BANNER, q.a.MEDIATION_MAIN, this.sdk), jLongValue);
        }
    }

    private boolean c() {
        return ((Long) this.sdk.a(com.applovin.impl.sdk.b.a.J)).longValue() > 0;
    }

    private boolean d() {
        boolean z;
        synchronized (this.j) {
            z = this.n;
        }
        return z;
    }

    public void destroy() {
        a();
        synchronized (this.j) {
            this.n = true;
        }
        this.g.e();
    }

    public String getPlacement() {
        return this.d;
    }

    public void loadAd() {
        this.logger.a(this.tag, "" + this + " Loading ad for " + this.adUnitId + "...");
        if (d()) {
            this.logger.e(this.tag, "Unable to load new ad; ad is already destroyed");
            h.a(this.adListener, this.adUnitId, -1, this.sdk);
            return;
        }
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.a.K)).booleanValue() || !this.g.a()) {
            a(this.e);
            return;
        }
        this.logger.e(this.tag, "Unable to load a new ad. An ad refresh has already been scheduled in " + TimeUnit.MILLISECONDS.toSeconds(this.g.d()) + " seconds.");
    }

    @Override // com.applovin.impl.sdk.d.a
    public void onAdRefresh() {
        n nVar;
        String str;
        String str2;
        this.m = false;
        if (this.c != null) {
            this.logger.a(this.tag, "Refreshing for cached ad: " + this.c.getAdUnitId() + "...");
            this.e.onAdLoaded(this.c);
            this.c = null;
            return;
        }
        if (!c()) {
            nVar = this.logger;
            str = this.tag;
            str2 = "Refreshing ad from network...";
        } else if (!this.l) {
            this.logger.d(this.tag, "Ignoring attempt to refresh ad - either still waiting for precache or did not attempt request due to visibility requirement not met");
            this.m = true;
            return;
        } else {
            nVar = this.logger;
            str = this.tag;
            str2 = "Refreshing ad from network due to viewability requirements not met for refresh request...";
        }
        nVar.a(str, str2);
        loadAd();
    }

    @Override // com.applovin.impl.sdk.u.a
    public void onLogVisibilityImpression() {
        a(this.k, this.h.a(this.k));
    }

    public void setPlacement(String str) {
        this.d = str;
    }

    public void startAutoRefresh() {
        this.g.g();
        this.logger.a(this.tag, "Resumed autorefresh with remaining time: " + this.g.d());
    }

    public void stopAutoRefresh() {
        this.logger.a(this.tag, "Pausing autorefresh with remaining time: " + this.g.d());
        this.g.f();
    }

    public String toString() {
        return "MaxAdView{adUnitId='" + this.adUnitId + "', adListener=" + this.adListener + ", isDestroyed=" + d() + '}';
    }
}
