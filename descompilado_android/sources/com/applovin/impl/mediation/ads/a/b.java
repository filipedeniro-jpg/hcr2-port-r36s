package com.applovin.impl.mediation.ads.a;

import android.app.Activity;
import com.applovin.impl.mediation.ads.MaxInterstitialImpl;
import com.applovin.impl.mediation.d.c;
import com.applovin.impl.mediation.e;
import com.applovin.impl.sdk.n;
import com.applovin.impl.sdk.utils.h;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends com.applovin.impl.mediation.ads.a.a implements com.applovin.impl.sdk.b.a {
    private final com.applovin.impl.sdk.b a;
    private final com.applovin.impl.mediation.b b;
    private final Object c;
    private MaxAd d;
    private EnumC0017b e;
    private final AtomicBoolean f;
    protected final a listenerWrapper;

    /* JADX INFO: Access modifiers changed from: protected */
    public class a implements MaxAdListener, MaxRewardedAdListener {
        protected a() {
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdClicked(MaxAd maxAd) {
            h.d(b.this.adListener, maxAd, b.this.sdk);
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdDisplayFailed(final MaxAd maxAd, final int i) {
            b.this.transitionToState(EnumC0017b.IDLE, new Runnable() { // from class: com.applovin.impl.mediation.ads.a.b.a.4
                @Override // java.lang.Runnable
                public void run() {
                    b.this.a.a();
                    b.this.b();
                    h.a(b.this.adListener, maxAd, i, b.this.sdk);
                }
            });
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdDisplayed(MaxAd maxAd) {
            b.this.a.a();
            h.b(b.this.adListener, maxAd, b.this.sdk);
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdHidden(final MaxAd maxAd) {
            if (c.a(b.this.d) != maxAd) {
                b.this.logger.d(b.this.tag, "AD HIDDEN callback received for previous ad");
            } else {
                b.this.b.a(maxAd);
                b.this.transitionToState(EnumC0017b.IDLE, new Runnable() { // from class: com.applovin.impl.mediation.ads.a.b.a.3
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.b();
                        h.c(b.this.adListener, maxAd, b.this.sdk);
                    }
                });
            }
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdLoadFailed(final String str, final int i) {
            b.this.transitionToState(EnumC0017b.IDLE, new Runnable() { // from class: com.applovin.impl.mediation.ads.a.b.a.2
                @Override // java.lang.Runnable
                public void run() {
                    b.this.b();
                    if (b.this.f.compareAndSet(true, false)) {
                        b.this.loadRequestBuilder.a("expired_ad_ad_unit_id");
                    }
                    h.a(b.this.adListener, str, i, b.this.sdk);
                }
            });
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdLoaded(final MaxAd maxAd) {
            if (!b.this.f.compareAndSet(true, false)) {
                b.this.transitionToState(EnumC0017b.READY, new Runnable() { // from class: com.applovin.impl.mediation.ads.a.b.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.b(maxAd);
                        h.a(b.this.adListener, maxAd, b.this.sdk);
                    }
                });
            } else {
                b.this.loadRequestBuilder.a("expired_ad_ad_unit_id");
                b.this.b(maxAd);
            }
        }

        @Override // com.applovin.mediation.MaxRewardedAdListener
        public void onRewardedVideoCompleted(MaxAd maxAd) {
            h.f(b.this.adListener, maxAd, b.this.sdk);
        }

        @Override // com.applovin.mediation.MaxRewardedAdListener
        public void onRewardedVideoStarted(MaxAd maxAd) {
            h.e(b.this.adListener, maxAd, b.this.sdk);
        }

        @Override // com.applovin.mediation.MaxRewardedAdListener
        public void onUserRewarded(MaxAd maxAd, MaxReward maxReward) {
            h.a(b.this.adListener, maxAd, maxReward, b.this.sdk);
        }
    }

    /* JADX INFO: renamed from: com.applovin.impl.mediation.ads.a.b$b, reason: collision with other inner class name */
    public enum EnumC0017b {
        IDLE,
        LOADING,
        READY,
        SHOWING,
        DESTROYED
    }

    protected b(String str, String str2, com.applovin.impl.sdk.h hVar) {
        super(str, str2, hVar);
        this.c = new Object();
        this.d = null;
        this.e = EnumC0017b.IDLE;
        this.f = new AtomicBoolean();
        this.listenerWrapper = new a();
        this.a = new com.applovin.impl.sdk.b(hVar, this);
        this.b = new com.applovin.impl.mediation.b(hVar, this.listenerWrapper);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MaxAd a() {
        MaxAd maxAd;
        synchronized (this.c) {
            maxAd = this.d;
            this.d = null;
        }
        return maxAd;
    }

    private void a(MaxAd maxAd) {
        synchronized (this.c) {
            this.d = maxAd;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.sdk.a(getActivity()).destroyAd(a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MaxAd maxAd) {
        a(maxAd);
        c(maxAd);
    }

    private void c(MaxAd maxAd) {
        long jD;
        if (maxAd instanceof com.applovin.impl.mediation.b.c) {
            jD = ((com.applovin.impl.mediation.b.c) maxAd).i();
        } else {
            jD = maxAd instanceof e ? ((e) maxAd).d() : -1L;
        }
        if (jD >= 0) {
            this.logger.a(this.tag, "Scheduling ad expiration " + TimeUnit.MILLISECONDS.toMinutes(jD) + " minutes from now for " + getAdUnitId() + " ...");
            this.a.a(jD);
        }
    }

    public void destroy() {
        transitionToState(EnumC0017b.DESTROYED, new Runnable() { // from class: com.applovin.impl.mediation.ads.a.b.1
            @Override // java.lang.Runnable
            public void run() {
                MaxAd maxAdA = b.this.a();
                b.this.logger.a(b.this.tag, "Destroying ad for '" + b.this.adUnitId + "'; current ad: " + maxAdA + "...");
                b.this.sdk.a(b.this.getActivity()).destroyAd(maxAdA);
            }
        });
    }

    protected abstract Activity getActivity();

    protected MaxAd getLoadedAd() {
        MaxAd maxAd;
        synchronized (this.c) {
            maxAd = this.d;
        }
        return maxAd;
    }

    public boolean isReady() {
        boolean z;
        synchronized (this.c) {
            z = this.d != null && this.d.isReady() && this.e == EnumC0017b.READY;
        }
        return z;
    }

    @Override // com.applovin.impl.sdk.b.a
    public void onAdExpired() {
        this.logger.a(this.tag, "Ad expired " + getAdUnitId());
        this.f.set(true);
        this.loadRequestBuilder.a("expired_ad_ad_unit_id", getAdUnitId());
        this.sdk.a(getActivity()).loadAd(this.adUnitId, this instanceof MaxInterstitialImpl ? MaxAdFormat.INTERSTITIAL : MaxAdFormat.REWARDED, this.loadRequestBuilder.a(), getActivity(), this.listenerWrapper);
    }

    protected void onTransitionedToState(EnumC0017b enumC0017b, EnumC0017b enumC0017b2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showFullscreenAd(String str, Activity activity) {
        MaxAd loadedAd = getLoadedAd();
        if (loadedAd instanceof e) {
            loadedAd = ((e) loadedAd).a(activity);
        }
        com.applovin.impl.mediation.b.c cVar = (com.applovin.impl.mediation.b.c) loadedAd;
        this.b.b(cVar);
        this.logger.a(this.tag, "Showing ad for '" + this.adUnitId + "'; loaded ad: " + cVar + "...");
        this.sdk.a(activity).showFullscreenAd(cVar, str, activity);
    }

    protected void transitionToState(EnumC0017b enumC0017b, Runnable runnable) {
        boolean z;
        n nVar;
        String str;
        String str2;
        n nVar2;
        String str3;
        String str4;
        EnumC0017b enumC0017b2 = this.e;
        synchronized (this.c) {
            this.logger.a(this.tag, "Attempting state transition from " + enumC0017b2 + " to " + enumC0017b);
            z = false;
            if (enumC0017b2 == EnumC0017b.IDLE) {
                if (enumC0017b == EnumC0017b.LOADING || enumC0017b == EnumC0017b.DESTROYED) {
                    z = true;
                } else if (enumC0017b == EnumC0017b.SHOWING) {
                    nVar2 = this.logger;
                    str3 = this.tag;
                    str4 = "No ad is loading or loaded";
                    nVar2.e(str3, str4);
                } else {
                    nVar = this.logger;
                    str = this.tag;
                    str2 = "Unable to transition to: " + enumC0017b;
                    nVar.d(str, str2);
                }
            } else if (enumC0017b2 == EnumC0017b.LOADING) {
                if (enumC0017b != EnumC0017b.IDLE) {
                    if (enumC0017b == EnumC0017b.LOADING) {
                        nVar2 = this.logger;
                        str3 = this.tag;
                        str4 = "An ad is already loading";
                    } else if (enumC0017b != EnumC0017b.READY) {
                        if (enumC0017b == EnumC0017b.SHOWING) {
                            nVar2 = this.logger;
                            str3 = this.tag;
                            str4 = "An ad is not ready to be shown yet";
                        } else if (enumC0017b != EnumC0017b.DESTROYED) {
                            nVar = this.logger;
                            str = this.tag;
                            str2 = "Unable to transition to: " + enumC0017b;
                            nVar.d(str, str2);
                        }
                    }
                    nVar2.e(str3, str4);
                }
                z = true;
            } else if (enumC0017b2 == EnumC0017b.READY) {
                if (enumC0017b != EnumC0017b.IDLE) {
                    if (enumC0017b == EnumC0017b.LOADING) {
                        nVar2 = this.logger;
                        str3 = this.tag;
                        str4 = "An ad is already loaded";
                        nVar2.e(str3, str4);
                    } else {
                        if (enumC0017b == EnumC0017b.READY) {
                            nVar = this.logger;
                            str = this.tag;
                            str2 = "An ad is already marked as ready";
                        } else if (enumC0017b != EnumC0017b.SHOWING && enumC0017b != EnumC0017b.DESTROYED) {
                            nVar = this.logger;
                            str = this.tag;
                            str2 = "Unable to transition to: " + enumC0017b;
                        }
                        nVar.d(str, str2);
                    }
                }
                z = true;
            } else if (enumC0017b2 == EnumC0017b.SHOWING) {
                if (enumC0017b != EnumC0017b.IDLE) {
                    if (enumC0017b == EnumC0017b.LOADING) {
                        nVar2 = this.logger;
                        str3 = this.tag;
                        str4 = "Can not load another interstitial while the ad is showing";
                    } else {
                        if (enumC0017b == EnumC0017b.READY) {
                            nVar = this.logger;
                            str = this.tag;
                            str2 = "An ad is already showing, ignoring";
                        } else if (enumC0017b == EnumC0017b.SHOWING) {
                            nVar2 = this.logger;
                            str3 = this.tag;
                            str4 = "The ad is already showing, not showing another one";
                        } else if (enumC0017b != EnumC0017b.DESTROYED) {
                            nVar = this.logger;
                            str = this.tag;
                            str2 = "Unable to transition to: " + enumC0017b;
                        }
                        nVar.d(str, str2);
                    }
                    nVar2.e(str3, str4);
                }
                z = true;
            } else if (enumC0017b2 == EnumC0017b.DESTROYED) {
                nVar2 = this.logger;
                str3 = this.tag;
                str4 = "No operations are allowed on a destroyed instance";
                nVar2.e(str3, str4);
            } else {
                nVar = this.logger;
                str = this.tag;
                str2 = "Unknown state: " + this.e;
                nVar.d(str, str2);
            }
            if (z) {
                this.logger.a(this.tag, "Transitioning from " + this.e + " to " + enumC0017b + "...");
                this.e = enumC0017b;
            } else {
                this.logger.c(this.tag, "Not allowed transition from " + this.e + " to " + enumC0017b);
            }
        }
        if (z) {
            if (runnable != null) {
                runnable.run();
            }
            onTransitionedToState(enumC0017b2, enumC0017b);
        }
    }
}
