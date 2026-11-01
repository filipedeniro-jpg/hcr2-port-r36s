package com.applovin.impl.mediation;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.n;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxErrorCodes;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.adapter.MaxAdViewAdapter;
import com.applovin.mediation.adapter.MaxAdapter;
import com.applovin.mediation.adapter.MaxAdapterError;
import com.applovin.mediation.adapter.MaxInterstitialAdapter;
import com.applovin.mediation.adapter.MaxRewardedAdapter;
import com.applovin.mediation.adapter.MaxSignalProvider;
import com.applovin.mediation.adapter.listeners.MaxAdViewAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxInterstitialAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxRewardedAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxSignalCollectionListener;
import com.applovin.mediation.adapter.parameters.MaxAdapterInitializationParameters;
import com.applovin.mediation.adapter.parameters.MaxAdapterResponseParameters;
import com.applovin.mediation.adapter.parameters.MaxAdapterSignalCollectionParameters;
import com.ironsource.sdk.constants.Constants;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class h {
    private final com.applovin.impl.sdk.h b;
    private final n c;
    private final String d;
    private final com.applovin.impl.mediation.b.e e;
    private final String f;
    private MaxAdapter g;
    private String h;
    private com.applovin.impl.mediation.b.a i;
    private View j;
    private MaxAdapterResponseParameters l;
    private final Handler a = new Handler(Looper.getMainLooper());
    private final a k = new a(this, null);
    private final AtomicBoolean m = new AtomicBoolean(true);
    private final AtomicBoolean n = new AtomicBoolean(false);
    private final AtomicBoolean o = new AtomicBoolean(false);
    private final AtomicBoolean p = new AtomicBoolean(false);

    /* JADX INFO: renamed from: com.applovin.impl.mediation.h$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ MaxAdapterInitializationParameters a;
        final /* synthetic */ Activity b;

        AnonymousClass1(MaxAdapterInitializationParameters maxAdapterInitializationParameters, Activity activity) {
            this.a = maxAdapterInitializationParameters;
            this.b = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.g.initialize(this.a, this.b, new MaxAdapter.OnCompletionListener() { // from class: com.applovin.impl.mediation.h.1.1
                @Override // com.applovin.mediation.adapter.MaxAdapter.OnCompletionListener
                public void onCompletion() {
                    long jD = h.this.e.D();
                    if (jD > 0) {
                        h.this.a.postDelayed(new Runnable() { // from class: com.applovin.impl.mediation.h.1.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                h.this.b.a(AnonymousClass1.this.b).a(h.this.e.u());
                            }
                        }, jD);
                    } else {
                        h.this.b.a(AnonymousClass1.this.b).a(h.this.e.u());
                    }
                }
            });
        }
    }

    private class a implements MaxAdViewAdapterListener, MaxInterstitialAdapterListener, MaxRewardedAdapterListener {
        private com.applovin.impl.mediation.d b;

        private a() {
        }

        /* synthetic */ a(h hVar, AnonymousClass1 anonymousClass1) {
            this();
        }

        private int a(MaxAdapterError maxAdapterError) {
            return maxAdapterError != null ? maxAdapterError.getErrorCode() : MaxAdapterError.ERROR_CODE_UNSPECIFIED;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(com.applovin.impl.mediation.d dVar) {
            if (dVar == null) {
                throw new IllegalArgumentException("No listener specified");
            }
            this.b = dVar;
        }

        private void a(String str) {
            h.this.o.set(true);
            a(str, this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.1
                @Override // java.lang.Runnable
                public void run() {
                    if (h.this.n.compareAndSet(false, true)) {
                        a.this.b.onAdLoaded(h.this.i);
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str, int i) {
            a(str, i, "");
        }

        private void a(String str, final int i, final String str2) {
            a(str, this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.9
                @Override // java.lang.Runnable
                public void run() {
                    if (h.this.n.compareAndSet(false, true)) {
                        a.this.b.a(h.this.h, i, str2);
                        if (h.this.e.F()) {
                            h.this.g();
                        }
                    }
                }
            });
        }

        private void a(final String str, final MaxAdListener maxAdListener, final Runnable runnable) {
            h.this.a.post(new Runnable() { // from class: com.applovin.impl.mediation.h.a.8
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        runnable.run();
                    } catch (Exception e) {
                        MaxAdListener maxAdListener2 = maxAdListener;
                        String name = maxAdListener2 != null ? maxAdListener2.getClass().getName() : null;
                        h.this.c.b("MediationAdapterWrapper", "Failed to forward call (" + str + ") to " + name, e);
                    }
                }
            });
        }

        private void a(String str, MaxAdapterError maxAdapterError) {
            a(str, a(maxAdapterError), b(maxAdapterError));
        }

        private String b(MaxAdapterError maxAdapterError) {
            return maxAdapterError != null ? maxAdapterError.getAdapterError() : "";
        }

        private void b(String str) {
            if (h.this.i.g().compareAndSet(false, true)) {
                a(str, this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.10
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.b.onAdDisplayed(h.this.i);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(String str, int i) {
            b(str, i, "");
        }

        private void b(String str, final int i, final String str2) {
            a(str, this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.11
                @Override // java.lang.Runnable
                public void run() {
                    a.this.b.a(h.this.i, i, str2);
                }
            });
        }

        private void b(String str, MaxAdapterError maxAdapterError) {
            b(str, a(maxAdapterError), b(maxAdapterError));
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxAdViewAdapterListener
        public void onAdViewAdClicked() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": adview ad clicked");
            a("onAdViewAdClicked", this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.4
                @Override // java.lang.Runnable
                public void run() {
                    a.this.b.onAdClicked(h.this.i);
                }
            });
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxAdViewAdapterListener
        public void onAdViewAdCollapsed() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": adview ad collapsed");
            a("onAdViewAdCollapsed", this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.7
                @Override // java.lang.Runnable
                public void run() {
                    if (a.this.b instanceof MaxAdViewAdListener) {
                        ((MaxAdViewAdListener) a.this.b).onAdCollapsed(h.this.i);
                    }
                }
            });
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxAdViewAdapterListener
        public void onAdViewAdDisplayFailed(MaxAdapterError maxAdapterError) {
            h.this.c.c("MediationAdapterWrapper", h.this.f + ": adview ad failed to display with code: " + maxAdapterError);
            b("onAdViewAdDisplayFailed", maxAdapterError);
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxAdViewAdapterListener
        public void onAdViewAdDisplayed() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": adview ad displayed");
            b("onAdViewAdDisplayed");
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxAdViewAdapterListener
        public void onAdViewAdExpanded() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": adview ad expanded");
            a("onAdViewAdExpanded", this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.6
                @Override // java.lang.Runnable
                public void run() {
                    if (a.this.b instanceof MaxAdViewAdListener) {
                        ((MaxAdViewAdListener) a.this.b).onAdExpanded(h.this.i);
                    }
                }
            });
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxAdViewAdapterListener
        public void onAdViewAdHidden() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": adview ad hidden");
            a("onAdViewAdHidden", this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.5
                @Override // java.lang.Runnable
                public void run() {
                    a.this.b.onAdHidden(h.this.i);
                }
            });
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxAdViewAdapterListener
        public void onAdViewAdLoadFailed(MaxAdapterError maxAdapterError) {
            h.this.c.c("MediationAdapterWrapper", h.this.f + ": adview ad ad failed to load with code: " + maxAdapterError);
            a("onAdViewAdLoadFailed", maxAdapterError);
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxAdViewAdapterListener
        public void onAdViewAdLoaded(View view) {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": adview ad loaded");
            h.this.j = view;
            a("onAdViewAdLoaded");
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxInterstitialAdapterListener
        public void onInterstitialAdClicked() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": interstitial ad clicked");
            a(Constants.JSMethods.ON_INTERSTITIAL_AD_CLICKED, this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.12
                @Override // java.lang.Runnable
                public void run() {
                    a.this.b.onAdClicked(h.this.i);
                }
            });
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxInterstitialAdapterListener
        public void onInterstitialAdDisplayFailed(MaxAdapterError maxAdapterError) {
            h.this.c.c("MediationAdapterWrapper", h.this.f + ": interstitial ad failed to display with code " + maxAdapterError);
            b("onInterstitialAdDisplayFailed", maxAdapterError);
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxInterstitialAdapterListener
        public void onInterstitialAdDisplayed() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": interstitial ad displayed");
            b("onInterstitialAdDisplayed");
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxInterstitialAdapterListener
        public void onInterstitialAdHidden() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": interstitial ad hidden");
            a("onInterstitialAdHidden", this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.13
                @Override // java.lang.Runnable
                public void run() {
                    a.this.b.onAdHidden(h.this.i);
                }
            });
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxInterstitialAdapterListener
        public void onInterstitialAdLoadFailed(MaxAdapterError maxAdapterError) {
            h.this.c.c("MediationAdapterWrapper", h.this.f + ": interstitial ad failed to load with error " + maxAdapterError);
            a("onInterstitialAdLoadFailed", maxAdapterError);
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxInterstitialAdapterListener
        public void onInterstitialAdLoaded() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": interstitial ad loaded");
            a("onInterstitialAdLoaded");
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxRewardedAdapterListener
        public void onRewardedAdClicked() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": rewarded ad clicked");
            a("onRewardedAdClicked", this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.14
                @Override // java.lang.Runnable
                public void run() {
                    a.this.b.onAdClicked(h.this.i);
                }
            });
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxRewardedAdapterListener
        public void onRewardedAdDisplayFailed(MaxAdapterError maxAdapterError) {
            h.this.c.c("MediationAdapterWrapper", h.this.f + ": rewarded ad display failed with error: " + maxAdapterError);
            b("onRewardedAdDisplayFailed", maxAdapterError);
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxRewardedAdapterListener
        public void onRewardedAdDisplayed() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": rewarded ad displayed");
            b("onRewardedAdDisplayed");
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxRewardedAdapterListener
        public void onRewardedAdHidden() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": rewarded ad hidden");
            a("onRewardedAdHidden", this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.15
                @Override // java.lang.Runnable
                public void run() {
                    a.this.b.onAdHidden(h.this.i);
                }
            });
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxRewardedAdapterListener
        public void onRewardedAdLoadFailed(MaxAdapterError maxAdapterError) {
            h.this.c.c("MediationAdapterWrapper", h.this.f + ": rewarded ad failed to load with code: " + maxAdapterError);
            a("onRewardedAdLoadFailed", maxAdapterError);
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxRewardedAdapterListener
        public void onRewardedAdLoaded() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": rewarded ad loaded");
            a("onRewardedAdLoaded");
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxRewardedAdapterListener
        public void onRewardedAdVideoCompleted() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": rewarded video completed");
            a("onRewardedAdVideoCompleted", this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.3
                @Override // java.lang.Runnable
                public void run() {
                    if (a.this.b instanceof MaxRewardedAdListener) {
                        ((MaxRewardedAdListener) a.this.b).onRewardedVideoCompleted(h.this.i);
                    }
                }
            });
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxRewardedAdapterListener
        public void onRewardedAdVideoStarted() {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": rewarded video started");
            a("onRewardedAdVideoStarted", this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.2
                @Override // java.lang.Runnable
                public void run() {
                    if (a.this.b instanceof MaxRewardedAdListener) {
                        ((MaxRewardedAdListener) a.this.b).onRewardedVideoStarted(h.this.i);
                    }
                }
            });
        }

        @Override // com.applovin.mediation.adapter.listeners.MaxRewardedAdapterListener
        public void onUserRewarded(final MaxReward maxReward) {
            h.this.c.b("MediationAdapterWrapper", h.this.f + ": user was rewarded: " + maxReward);
            a("onUserRewarded", this.b, new Runnable() { // from class: com.applovin.impl.mediation.h.a.16
                @Override // java.lang.Runnable
                public void run() {
                    if (a.this.b instanceof MaxRewardedAdListener) {
                        ((MaxRewardedAdListener) a.this.b).onUserRewarded(h.this.i, maxReward);
                    }
                }
            });
        }
    }

    private static class b {
        private final com.applovin.impl.mediation.b.g a;
        private final MaxSignalCollectionListener b;
        private final AtomicBoolean c = new AtomicBoolean();

        b(com.applovin.impl.mediation.b.g gVar, MaxSignalCollectionListener maxSignalCollectionListener) {
            this.a = gVar;
            this.b = maxSignalCollectionListener;
        }
    }

    private class c extends com.applovin.impl.sdk.d.a {
        private c() {
            super("TaskTimeoutMediatedAd", h.this.b);
        }

        /* synthetic */ c(h hVar, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.applovin.impl.sdk.d.a
        public i a() {
            return i.F;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (h.this.o.get()) {
                return;
            }
            h.this.c.c(d(), h.this.f + " is timing out " + h.this.i + "...");
            h.this.k.a(d(), MaxErrorCodes.MEDIATION_ADAPTER_TIMEOUT);
        }
    }

    private class d extends com.applovin.impl.sdk.d.a {
        private final b c;

        private d(b bVar) {
            super("TaskTimeoutSignalCollection", h.this.b);
            this.c = bVar;
        }

        /* synthetic */ d(h hVar, b bVar, AnonymousClass1 anonymousClass1) {
            this(bVar);
        }

        @Override // com.applovin.impl.sdk.d.a
        public i a() {
            return i.G;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.c.c.get()) {
                return;
            }
            c(h.this.f + " is timing out " + this.c.a + "...");
            h.this.b("The adapter (" + h.this.f + ") timed out", this.c);
        }
    }

    h(com.applovin.impl.mediation.b.e eVar, MaxAdapter maxAdapter, com.applovin.impl.sdk.h hVar) {
        if (eVar == null) {
            throw new IllegalArgumentException("No adapter name specified");
        }
        if (maxAdapter == null) {
            throw new IllegalArgumentException("No adapter specified");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.d = eVar.v();
        this.g = maxAdapter;
        this.b = hVar;
        this.c = hVar.w();
        this.e = eVar;
        this.f = maxAdapter.getClass().getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.c.b("MediationAdapterWrapper", "Marking " + this.f + " as disabled due to: " + str);
        this.m.set(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, b bVar) {
        if (!bVar.c.compareAndSet(false, true) || bVar.b == null) {
            return;
        }
        bVar.b.onSignalCollected(str);
    }

    private void a(final String str, final Runnable runnable) {
        Runnable runnable2 = new Runnable() { // from class: com.applovin.impl.mediation.h.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    h.this.c.a("MediationAdapterWrapper", h.this.f + ": running " + str + "...");
                    runnable.run();
                    h.this.c.a("MediationAdapterWrapper", h.this.f + ": finished " + str + "");
                } catch (Throwable th) {
                    h.this.c.b("MediationAdapterWrapper", "Unable to run adapter operation " + str + ", marking " + h.this.f + " as disabled", th);
                    h hVar = h.this;
                    StringBuilder sb = new StringBuilder();
                    sb.append("fail_");
                    sb.append(str);
                    hVar.a(sb.toString());
                }
            }
        };
        if (this.e.x()) {
            this.a.post(runnable2);
        } else {
            runnable2.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, b bVar) {
        if (!bVar.c.compareAndSet(false, true) || bVar.b == null) {
            return;
        }
        bVar.b.onSignalCollectionFailed(str);
    }

    public View a() {
        return this.j;
    }

    void a(final com.applovin.impl.mediation.b.a aVar, final Activity activity) {
        final Runnable runnable;
        if (aVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        }
        if (aVar.b() != this) {
            throw new IllegalArgumentException("Mediated ad belongs to a different adapter");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        if (!this.m.get()) {
            this.c.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' is disabled. Showing ads with this adapter is disabled.");
            this.k.b("ad_show", MaxErrorCodes.MEDIATION_ADAPTER_DISABLED);
            return;
        }
        if (!d()) {
            this.c.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' does not have an ad loaded. Please load an ad first");
            this.k.b("ad_show", MaxErrorCodes.MEDIATION_ADAPTER_AD_NOT_READY);
            return;
        }
        if (aVar.getFormat() == MaxAdFormat.INTERSTITIAL) {
            if (!(this.g instanceof MaxInterstitialAdapter)) {
                this.c.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' is not an interstitial adapter.");
                this.k.b("showFullscreenAd", MaxErrorCodes.MEDIATION_ADAPTER_WRONG_TYPE);
                return;
            }
            runnable = new Runnable() { // from class: com.applovin.impl.mediation.h.8
                @Override // java.lang.Runnable
                public void run() {
                    ((MaxInterstitialAdapter) h.this.g).showInterstitialAd(h.this.l, activity, h.this.k);
                }
            };
        } else {
            if (aVar.getFormat() != MaxAdFormat.REWARDED) {
                this.c.e("MediationAdapterWrapper", "Failed to show " + aVar + ": " + aVar.getFormat() + " is not a supported ad format");
                this.k.b("showFullscreenAd", MaxErrorCodes.MEDIATION_ADAPTER_WRONG_TYPE);
                return;
            }
            if (!(this.g instanceof MaxRewardedAdapter)) {
                this.c.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' is not an incentivized adapter.");
                this.k.b("showFullscreenAd", MaxErrorCodes.MEDIATION_ADAPTER_WRONG_TYPE);
                return;
            }
            runnable = new Runnable() { // from class: com.applovin.impl.mediation.h.9
                @Override // java.lang.Runnable
                public void run() {
                    ((MaxRewardedAdapter) h.this.g).showRewardedAd(h.this.l, activity, h.this.k);
                }
            };
        }
        a("ad_render", new Runnable() { // from class: com.applovin.impl.mediation.h.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    h.this.c.b("MediationAdapterWrapper", "Failed to start displaying ad" + aVar, th);
                    h.this.k.b("ad_render", MaxAdapterError.ERROR_CODE_UNSPECIFIED);
                }
            }
        });
    }

    void a(MaxAdapterInitializationParameters maxAdapterInitializationParameters, Activity activity) {
        a("initialize", new AnonymousClass1(maxAdapterInitializationParameters, activity));
    }

    void a(final MaxAdapterSignalCollectionParameters maxAdapterSignalCollectionParameters, final com.applovin.impl.mediation.b.g gVar, final Activity activity, MaxSignalCollectionListener maxSignalCollectionListener) {
        if (maxSignalCollectionListener == null) {
            throw new IllegalArgumentException("No callback specified");
        }
        if (this.m.get()) {
            final b bVar = new b(gVar, maxSignalCollectionListener);
            MaxAdapter maxAdapter = this.g;
            if (maxAdapter instanceof MaxSignalProvider) {
                final MaxSignalProvider maxSignalProvider = (MaxSignalProvider) maxAdapter;
                a("collect_signal", new Runnable() { // from class: com.applovin.impl.mediation.h.11
                    @Override // java.lang.Runnable
                    public void run() {
                        maxSignalProvider.collectSignal(maxAdapterSignalCollectionParameters, activity, new MaxSignalCollectionListener() { // from class: com.applovin.impl.mediation.h.11.1
                            @Override // com.applovin.mediation.adapter.listeners.MaxSignalCollectionListener
                            public void onSignalCollected(String str) {
                                h.this.a(str, bVar);
                            }

                            @Override // com.applovin.mediation.adapter.listeners.MaxSignalCollectionListener
                            public void onSignalCollectionFailed(String str) {
                                h.this.b(str, bVar);
                            }
                        });
                        if (bVar.c.get()) {
                            return;
                        }
                        if (gVar.z() == 0) {
                            h.this.c.a("MediationAdapterWrapper", "Failing signal collection " + gVar + " since it has 0 timeout");
                            h.this.b("The adapter (" + h.this.f + ") has 0 timeout", bVar);
                            return;
                        }
                        if (gVar.z() <= 0) {
                            h.this.c.a("MediationAdapterWrapper", "Negative timeout set for " + gVar + ", not scheduling a timeout");
                            return;
                        }
                        h.this.c.a("MediationAdapterWrapper", "Setting timeout " + gVar.z() + "ms. for " + gVar);
                        h.this.b.F().a(new d(h.this, bVar, null), q.a.MEDIATION_TIMEOUT, gVar.z());
                    }
                });
                return;
            }
            b("The adapter (" + this.f + ") does not support signal collection", bVar);
            return;
        }
        this.c.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' is disabled. Signal collection ads with this adapter is disabled.");
        maxSignalCollectionListener.onSignalCollectionFailed("The adapter (" + this.f + ") is disabled");
    }

    void a(String str, com.applovin.impl.mediation.b.a aVar) {
        this.h = str;
        this.i = aVar;
    }

    void a(String str, final MaxAdapterResponseParameters maxAdapterResponseParameters, final com.applovin.impl.mediation.b.a aVar, final Activity activity, com.applovin.impl.mediation.d dVar) {
        final Runnable runnable;
        if (aVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        }
        if (!this.m.get()) {
            this.c.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' was disabled due to earlier failures. Loading ads with this adapter is disabled.");
            dVar.onAdLoadFailed(str, MaxErrorCodes.MEDIATION_ADAPTER_DISABLED);
            return;
        }
        this.l = maxAdapterResponseParameters;
        this.k.a(dVar);
        if (aVar.getFormat() == MaxAdFormat.INTERSTITIAL) {
            if (!(this.g instanceof MaxInterstitialAdapter)) {
                this.c.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' is not an interstitial adapter.");
                this.k.a("loadAd", MaxErrorCodes.MEDIATION_ADAPTER_WRONG_TYPE);
                return;
            }
            runnable = new Runnable() { // from class: com.applovin.impl.mediation.h.4
                @Override // java.lang.Runnable
                public void run() {
                    ((MaxInterstitialAdapter) h.this.g).loadInterstitialAd(maxAdapterResponseParameters, activity, h.this.k);
                }
            };
        } else if (aVar.getFormat() == MaxAdFormat.REWARDED) {
            if (!(this.g instanceof MaxRewardedAdapter)) {
                this.c.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' is not an incentivized adapter.");
                this.k.a("loadAd", MaxErrorCodes.MEDIATION_ADAPTER_WRONG_TYPE);
                return;
            }
            runnable = new Runnable() { // from class: com.applovin.impl.mediation.h.5
                @Override // java.lang.Runnable
                public void run() {
                    ((MaxRewardedAdapter) h.this.g).loadRewardedAd(maxAdapterResponseParameters, activity, h.this.k);
                }
            };
        } else {
            if (aVar.getFormat() != MaxAdFormat.BANNER && aVar.getFormat() != MaxAdFormat.LEADER && aVar.getFormat() != MaxAdFormat.MREC) {
                this.c.e("MediationAdapterWrapper", "Failed to load " + aVar + ": " + aVar.getFormat() + " is not a supported ad format");
                this.k.a("loadAd", MaxErrorCodes.FORMAT_TYPE_NOT_SUPPORTED);
                return;
            }
            if (!(this.g instanceof MaxAdViewAdapter)) {
                this.c.e("MediationAdapterWrapper", "Mediation adapter '" + this.f + "' is not an adview-based adapter.");
                this.k.a("loadAd", MaxErrorCodes.MEDIATION_ADAPTER_WRONG_TYPE);
                return;
            }
            runnable = new Runnable() { // from class: com.applovin.impl.mediation.h.6
                @Override // java.lang.Runnable
                public void run() {
                    ((MaxAdViewAdapter) h.this.g).loadAdViewAd(maxAdapterResponseParameters, aVar.getFormat(), activity, h.this.k);
                }
            };
        }
        a("ad_load", new Runnable() { // from class: com.applovin.impl.mediation.h.7
            @Override // java.lang.Runnable
            public void run() {
                com.applovin.impl.sdk.c.h hVarG;
                com.applovin.impl.sdk.c.g gVar;
                try {
                    if (h.this.p.compareAndSet(false, true)) {
                        if (h.this.i.getFormat() == MaxAdFormat.INTERSTITIAL) {
                            hVarG = h.this.b.G();
                            gVar = com.applovin.impl.sdk.c.g.v;
                        } else if (h.this.i.getFormat() == MaxAdFormat.REWARDED) {
                            hVarG = h.this.b.G();
                            gVar = com.applovin.impl.sdk.c.g.w;
                        } else {
                            hVarG = h.this.b.G();
                            gVar = com.applovin.impl.sdk.c.g.x;
                        }
                    } else if (h.this.i.getFormat() == MaxAdFormat.INTERSTITIAL) {
                        hVarG = h.this.b.G();
                        gVar = com.applovin.impl.sdk.c.g.y;
                    } else if (h.this.i.getFormat() == MaxAdFormat.REWARDED) {
                        hVarG = h.this.b.G();
                        gVar = com.applovin.impl.sdk.c.g.z;
                    } else {
                        hVarG = h.this.b.G();
                        gVar = com.applovin.impl.sdk.c.g.A;
                    }
                    hVarG.a(gVar);
                    runnable.run();
                } catch (Throwable th) {
                    h.this.c.b("MediationAdapterWrapper", "Failed start loading " + aVar, th);
                    h.this.k.a("loadAd", -1);
                }
                if (h.this.n.get()) {
                    return;
                }
                if (h.this.e.z() == 0) {
                    h.this.c.a("MediationAdapterWrapper", "Failing ad " + aVar + " since it has 0 timeout");
                    h.this.k.a("loadAd", MaxErrorCodes.MEDIATION_ADAPTER_IMMEDIATE_TIMEOUT);
                    return;
                }
                if (h.this.e.z() <= 0) {
                    h.this.c.a("MediationAdapterWrapper", "Negative timeout set for " + aVar + ", not scheduling a timeout");
                    return;
                }
                h.this.c.a("MediationAdapterWrapper", "Setting timeout " + h.this.e.z() + "ms. for " + aVar);
                h.this.b.F().a(new c(h.this, null), q.a.MEDIATION_TIMEOUT, h.this.e.z());
            }
        });
    }

    public String b() {
        return this.d;
    }

    public boolean c() {
        return this.m.get();
    }

    public boolean d() {
        return this.n.get() && this.o.get();
    }

    public String e() {
        MaxAdapter maxAdapter = this.g;
        if (maxAdapter == null) {
            return null;
        }
        try {
            return maxAdapter.getSdkVersion();
        } catch (Throwable th) {
            this.c.b("MediationAdapterWrapper", "Unable to get adapter's SDK version, marking " + this + " as disabled", th);
            a("fail_version");
            return null;
        }
    }

    public String f() {
        MaxAdapter maxAdapter = this.g;
        if (maxAdapter == null) {
            return null;
        }
        try {
            return maxAdapter.getAdapterVersion();
        } catch (Throwable th) {
            this.c.b("MediationAdapterWrapper", "Unable to get adapter version, marking " + this + " as disabled", th);
            a("fail_version");
            return null;
        }
    }

    void g() {
        a("destroy", new Runnable() { // from class: com.applovin.impl.mediation.h.2
            @Override // java.lang.Runnable
            public void run() {
                h.this.a("destroy");
                h.this.g.onDestroy();
                h.this.g = null;
            }
        });
    }

    public String toString() {
        return "MediationAdapterWrapper{adapterTag='" + this.f + "'}";
    }
}
