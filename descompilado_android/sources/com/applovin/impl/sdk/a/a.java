package com.applovin.impl.sdk.a;

import android.app.Activity;
import android.content.Context;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.impl.sdk.ad.g;
import com.applovin.impl.sdk.d.ab;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.d.x;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.utils.k;
import com.applovin.impl.sdk.utils.n;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.ref.SoftReference;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class a {
    protected final h a;
    protected final AppLovinAdServiceImpl b;
    private AppLovinAd c;
    private String d;
    private SoftReference<AppLovinAdLoadListener> e;
    private volatile String g;
    private ab h;
    private SoftReference<AppLovinInterstitialAdDialog> j;
    private final Object f = new Object();
    private volatile boolean i = false;

    /* JADX INFO: renamed from: com.applovin.impl.sdk.a.a$a, reason: collision with other inner class name */
    private class C0021a implements AppLovinAdLoadListener {
        private final AppLovinAdLoadListener b;

        C0021a(AppLovinAdLoadListener appLovinAdLoadListener) {
            this.b = appLovinAdLoadListener;
        }

        @Override // com.applovin.sdk.AppLovinAdLoadListener
        public void adReceived(final AppLovinAd appLovinAd) {
            a.this.c = appLovinAd;
            if (this.b != null) {
                AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.a.a.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            C0021a.this.b.adReceived(appLovinAd);
                        } catch (Throwable th) {
                            a.this.a.w().c("AppLovinIncentivizedInterstitial", "Unable to notify ad listener about a newly loaded ad", th);
                        }
                    }
                });
            }
        }

        @Override // com.applovin.sdk.AppLovinAdLoadListener
        public void failedToReceiveAd(final int i) {
            if (this.b != null) {
                AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.a.a.a.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            C0021a.this.b.failedToReceiveAd(i);
                        } catch (Throwable th) {
                            a.this.a.w().c("AppLovinIncentivizedInterstitial", "Unable to notify listener about ad load failure", th);
                        }
                    }
                });
            }
        }
    }

    private class b implements AppLovinAdClickListener, AppLovinAdDisplayListener, AppLovinAdRewardListener, AppLovinAdVideoPlaybackListener {
        private final Context b;
        private final AppLovinAdDisplayListener c;
        private final AppLovinAdClickListener d;
        private final AppLovinAdVideoPlaybackListener e;
        private final AppLovinAdRewardListener f;

        private b(Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
            this.c = appLovinAdDisplayListener;
            this.d = appLovinAdClickListener;
            this.e = appLovinAdVideoPlaybackListener;
            this.f = appLovinAdRewardListener;
            this.b = context;
        }

        private void a(g gVar) {
            int i;
            String str;
            String strE = a.this.e();
            if (k.b(strE) && a.this.i) {
                a.this.a(strE, this.b);
            } else {
                a.this.h.a(true);
                if (a.this.i) {
                    i = AppLovinErrorCodes.INCENTIVIZED_SERVER_TIMEOUT;
                    str = "network_timeout";
                } else {
                    i = AppLovinErrorCodes.INCENTIVIZED_USER_CLOSED_VIDEO;
                    str = "user_closed_video";
                }
                e.a().a(gVar, str);
                if (a.this.i) {
                    a.this.a(strE, this.b);
                }
                com.applovin.impl.sdk.utils.h.a(this.f, gVar, i, a.this.a);
            }
            a.this.a(gVar);
            com.applovin.impl.sdk.utils.h.b(this.c, gVar, a.this.a);
            if (gVar.ag().getAndSet(true)) {
                return;
            }
            a.this.a.F().a(new x(gVar, a.this.a), q.a.REWARD);
        }

        @Override // com.applovin.sdk.AppLovinAdClickListener
        public void adClicked(AppLovinAd appLovinAd) {
            com.applovin.impl.sdk.utils.h.a(this.d, appLovinAd, a.this.a);
        }

        @Override // com.applovin.sdk.AppLovinAdDisplayListener
        public void adDisplayed(AppLovinAd appLovinAd) {
            com.applovin.impl.sdk.utils.h.a(this.c, appLovinAd, a.this.a);
        }

        @Override // com.applovin.sdk.AppLovinAdDisplayListener
        public void adHidden(AppLovinAd appLovinAd) {
            if (appLovinAd instanceof com.applovin.impl.sdk.ad.h) {
                appLovinAd = ((com.applovin.impl.sdk.ad.h) appLovinAd).a();
            }
            if (appLovinAd instanceof g) {
                a((g) appLovinAd);
                return;
            }
            a.this.a.w().d("IncentivizedAdController", "Something is terribly wrong. Received `adHidden` callback for invalid ad of type: " + appLovinAd);
        }

        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
        }

        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void userOverQuota(AppLovinAd appLovinAd, Map<String, String> map) {
            a.this.a("quota_exceeded");
            com.applovin.impl.sdk.utils.h.b(this.f, appLovinAd, map, a.this.a);
        }

        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void userRewardRejected(AppLovinAd appLovinAd, Map<String, String> map) {
            a.this.a("rejected");
            com.applovin.impl.sdk.utils.h.c(this.f, appLovinAd, map, a.this.a);
        }

        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void userRewardVerified(AppLovinAd appLovinAd, Map<String, String> map) {
            a.this.a("accepted");
            com.applovin.impl.sdk.utils.h.a(this.f, appLovinAd, map, a.this.a);
        }

        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
            a.this.a("network_timeout");
            com.applovin.impl.sdk.utils.h.a(this.f, appLovinAd, i, a.this.a);
        }

        @Override // com.applovin.sdk.AppLovinAdVideoPlaybackListener
        public void videoPlaybackBegan(AppLovinAd appLovinAd) {
            com.applovin.impl.sdk.utils.h.a(this.e, appLovinAd, a.this.a);
        }

        @Override // com.applovin.sdk.AppLovinAdVideoPlaybackListener
        public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
            com.applovin.impl.sdk.utils.h.a(this.e, appLovinAd, d, z, a.this.a);
            a.this.i = z;
        }
    }

    public a(String str, AppLovinSdk appLovinSdk) {
        this.a = n.a(appLovinSdk);
        this.b = (AppLovinAdServiceImpl) appLovinSdk.getAdService();
        this.d = str;
    }

    private void a(final AppLovinAdBase appLovinAdBase, final Context context, final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, final AppLovinAdDisplayListener appLovinAdDisplayListener, final AppLovinAdClickListener appLovinAdClickListener) {
        com.applovin.impl.sdk.n nVarW;
        String str;
        if (appLovinAdBase.getType().equals(AppLovinAdType.INCENTIVIZED)) {
            if (!n.a((AppLovinAd) appLovinAdBase, this.a)) {
                a(appLovinAdBase, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
                return;
            }
            g gVar = appLovinAdBase instanceof com.applovin.impl.sdk.ad.h ? (g) this.a.O().c(appLovinAdBase.getAdZone()) : (g) appLovinAdBase;
            if (!n.a(gVar, context, this.a)) {
                this.a.G().a(com.applovin.impl.sdk.c.g.o);
                if (gVar instanceof com.applovin.impl.sdk.ad.a) {
                    com.applovin.impl.sdk.ad.a aVar = (com.applovin.impl.sdk.ad.a) gVar;
                    if (aVar.aj() && aVar.d()) {
                        this.a.w().d("IncentivizedAdController", "Cached video removed from local filesystem for ad server ad. Restored video uri to video stream url: " + aVar.e());
                    } else {
                        this.a.w().d("IncentivizedAdController", "Cached video removed from local filesystem for ad server ad: " + aVar.getAdIdNumber() + " and could not restore video stream url. Failing ad show.");
                    }
                } else {
                    nVarW = this.a.w();
                    str = "Failed to render an ad: video cache has been removed.";
                }
                a(appLovinAdBase, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
            }
            Runnable runnable = new Runnable() { // from class: com.applovin.impl.sdk.a.a.1
                @Override // java.lang.Runnable
                public void run() {
                    AppLovinAd appLovinAdB = n.b(appLovinAdBase, a.this.a);
                    if (appLovinAdB == null) {
                        a.this.a(appLovinAdBase, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
                        return;
                    }
                    AppLovinInterstitialAdDialog appLovinInterstitialAdDialogCreate = AppLovinInterstitialAd.create(a.this.a.N(), context);
                    b bVar = new b(context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
                    appLovinInterstitialAdDialogCreate.setAdDisplayListener(bVar);
                    appLovinInterstitialAdDialogCreate.setAdVideoPlaybackListener(bVar);
                    appLovinInterstitialAdDialogCreate.setAdClickListener(bVar);
                    appLovinInterstitialAdDialogCreate.showAndRender(appLovinAdB);
                    a.this.j = new SoftReference(appLovinInterstitialAdDialogCreate);
                    if (appLovinAdB instanceof g) {
                        a.this.a((g) appLovinAdB, (AppLovinAdRewardListener) bVar);
                    }
                }
            };
            boolean zBooleanValue = ((Boolean) this.a.a(com.applovin.impl.sdk.b.b.cd)).booleanValue();
            if (zBooleanValue && (context instanceof Activity)) {
                Activity activity = (Activity) context;
                if (!activity.isFinishing()) {
                    c.a().a(this.a).a(activity).a(this).a(appLovinAdRewardListener).a(runnable).a().a(appLovinAdBase);
                    return;
                }
            }
            if (zBooleanValue) {
                this.a.w().e("IncentivizedAdController", "Unable to show Incentivized Ad prompt. Must pass in an active Activity context.");
            }
            this.a.G().a(com.applovin.impl.sdk.c.g.j);
            runnable.run();
            return;
        }
        nVarW = this.a.w();
        str = "Failed to render an ad of type " + appLovinAdBase.getType() + " in an Incentivized Ad interstitial.";
        nVarW.d("IncentivizedAdController", str);
        a(appLovinAdBase, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(g gVar, AppLovinAdRewardListener appLovinAdRewardListener) {
        this.h = new ab(gVar, appLovinAdRewardListener, this.a);
        this.a.F().a(this.h, q.a.REWARD);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppLovinAd appLovinAd) {
        AppLovinAd appLovinAd2 = this.c;
        if (appLovinAd2 != null) {
            if (appLovinAd2 instanceof com.applovin.impl.sdk.ad.h) {
                if (appLovinAd != ((com.applovin.impl.sdk.ad.h) appLovinAd2).a()) {
                    return;
                }
            } else if (appLovinAd != appLovinAd2) {
                return;
            }
            this.c = null;
        }
    }

    private void a(AppLovinAd appLovinAd, Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        if (appLovinAd == null) {
            appLovinAd = this.c;
        }
        AppLovinAdBase appLovinAdBase = (AppLovinAdBase) appLovinAd;
        if (appLovinAdBase != null) {
            a(appLovinAdBase, context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
        } else {
            this.a.w().e("IncentivizedAdController", "Skipping incentivized video playback: user attempted to play an incentivized video before one was preloaded.");
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppLovinAd appLovinAd, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.a.G().a(com.applovin.impl.sdk.c.g.m);
        com.applovin.impl.sdk.utils.h.a(appLovinAdVideoPlaybackListener, appLovinAd, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, false, this.a);
        com.applovin.impl.sdk.utils.h.b(appLovinAdDisplayListener, appLovinAd, this.a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        synchronized (this.f) {
            this.g = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Context context) {
        if (str == null || !((Boolean) this.a.a(com.applovin.impl.sdk.b.b.ce)).booleanValue()) {
            return;
        }
        new com.applovin.impl.sdk.a.b(this.a, context, str).a();
    }

    private void b(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.b.loadNextIncentivizedAd(this.d, appLovinAdLoadListener);
    }

    private void d() {
        AppLovinAdLoadListener appLovinAdLoadListener;
        SoftReference<AppLovinAdLoadListener> softReference = this.e;
        if (softReference == null || (appLovinAdLoadListener = softReference.get()) == null) {
            return;
        }
        appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.INCENTIVIZED_NO_AD_PRELOADED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e() {
        String str;
        synchronized (this.f) {
            str = this.g;
        }
        return str;
    }

    private AppLovinAdRewardListener f() {
        return new AppLovinAdRewardListener() { // from class: com.applovin.impl.sdk.a.a.2
            @Override // com.applovin.sdk.AppLovinAdRewardListener
            public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
                a.this.a.w().a("IncentivizedAdController", "User declined to view");
            }

            @Override // com.applovin.sdk.AppLovinAdRewardListener
            public void userOverQuota(AppLovinAd appLovinAd, Map<String, String> map) {
                a.this.a.w().a("IncentivizedAdController", "User over quota: " + map);
            }

            @Override // com.applovin.sdk.AppLovinAdRewardListener
            public void userRewardRejected(AppLovinAd appLovinAd, Map<String, String> map) {
                a.this.a.w().a("IncentivizedAdController", "Reward rejected: " + map);
            }

            @Override // com.applovin.sdk.AppLovinAdRewardListener
            public void userRewardVerified(AppLovinAd appLovinAd, Map<String, String> map) {
                a.this.a.w().a("IncentivizedAdController", "Reward validated: " + map);
            }

            @Override // com.applovin.sdk.AppLovinAdRewardListener
            public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
                a.this.a.w().a("IncentivizedAdController", "Reward validation failed: " + i);
            }
        };
    }

    public void a(AppLovinAd appLovinAd, Context context, String str, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        if (appLovinAdRewardListener == null) {
            appLovinAdRewardListener = f();
        }
        a(appLovinAd, context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
    }

    void a(AppLovinAd appLovinAd, AppLovinAdRewardListener appLovinAdRewardListener) {
        com.applovin.impl.sdk.utils.h.a(appLovinAdRewardListener, appLovinAd, this.a);
    }

    public void a(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a.w().a("IncentivizedAdController", "User requested preload of incentivized ad...");
        this.e = new SoftReference<>(appLovinAdLoadListener);
        if (!a()) {
            b(new C0021a(appLovinAdLoadListener));
            return;
        }
        this.a.w().e("IncentivizedAdController", "Attempted to call preloadAndNotify: while an ad was already loaded or currently being played. Do not call preloadAndNotify: again until the last ad has been closed (adHidden).");
        if (appLovinAdLoadListener != null) {
            appLovinAdLoadListener.adReceived(this.c);
        }
    }

    public boolean a() {
        return this.c != null;
    }

    public String b() {
        return this.d;
    }

    public void c() {
        AppLovinInterstitialAdDialog appLovinInterstitialAdDialog;
        SoftReference<AppLovinInterstitialAdDialog> softReference = this.j;
        if (softReference == null || (appLovinInterstitialAdDialog = softReference.get()) == null) {
            return;
        }
        appLovinInterstitialAdDialog.dismiss();
    }
}
