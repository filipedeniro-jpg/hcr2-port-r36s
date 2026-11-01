package com.applovin.impl.sdk.utils;

import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class h {
    public static void a(final AppLovinAdViewEventListener appLovinAdViewEventListener, final AppLovinAd appLovinAd, final AppLovinAdView appLovinAdView, final com.applovin.impl.sdk.h hVar) {
        if (appLovinAd == null || appLovinAdViewEventListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.22
            @Override // java.lang.Runnable
            public void run() {
                try {
                    appLovinAdViewEventListener.adOpenedFullscreen(h.b(appLovinAd), appLovinAdView);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about fullscreen opened event", th);
                }
            }
        });
    }

    public static void a(final MaxAdListener maxAdListener, final MaxAd maxAd, final int i, final com.applovin.impl.sdk.h hVar) {
        if (maxAd == null || maxAdListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.13
            @Override // java.lang.Runnable
            public void run() {
                try {
                    maxAdListener.onAdDisplayFailed(maxAd, i);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad failing to display", th);
                }
            }
        });
    }

    public static void a(final MaxAdListener maxAdListener, final MaxAd maxAd, final com.applovin.impl.sdk.h hVar) {
        if (maxAd == null || maxAdListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    maxAdListener.onAdLoaded(maxAd);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being loaded", th);
                }
            }
        });
    }

    public static void a(final MaxAdListener maxAdListener, final MaxAd maxAd, final MaxReward maxReward, final com.applovin.impl.sdk.h hVar) {
        if (maxAd == null || !(maxAdListener instanceof MaxRewardedAdListener)) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.16
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ((MaxRewardedAdListener) maxAdListener).onUserRewarded(maxAd, maxReward);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about user being rewarded", th);
                }
            }
        });
    }

    public static void a(final MaxAdListener maxAdListener, final String str, final int i, final com.applovin.impl.sdk.h hVar) {
        if (str == null || maxAdListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    maxAdListener.onAdLoadFailed(str, i);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad failing to load", th);
                }
            }
        });
    }

    public static void a(final AppLovinAdClickListener appLovinAdClickListener, final AppLovinAd appLovinAd, final com.applovin.impl.sdk.h hVar) {
        if (appLovinAd == null || appLovinAdClickListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.19
            @Override // java.lang.Runnable
            public void run() {
                try {
                    appLovinAdClickListener.adClicked(h.b(appLovinAd));
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being clicked", th);
                }
            }
        });
    }

    public static void a(final AppLovinAdDisplayListener appLovinAdDisplayListener, final AppLovinAd appLovinAd, final com.applovin.impl.sdk.h hVar) {
        if (appLovinAd == null || appLovinAdDisplayListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    appLovinAdDisplayListener.adDisplayed(h.b(appLovinAd));
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being displayed", th);
                }
            }
        });
    }

    public static void a(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final int i, final com.applovin.impl.sdk.h hVar) {
        if (appLovinAd == null || appLovinAdRewardListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    appLovinAdRewardListener.validationRequestFailed(h.b(appLovinAd), i);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad reward listener about reward validation request failing", th);
                }
            }
        });
    }

    public static void a(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final com.applovin.impl.sdk.h hVar) {
        if (appLovinAd == null || appLovinAdRewardListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    appLovinAdRewardListener.userDeclinedToViewAd(h.b(appLovinAd));
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad reward listener about user declining to view ad", th);
                }
            }
        });
    }

    public static void a(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final Map<String, String> map, final com.applovin.impl.sdk.h hVar) {
        if (appLovinAd == null || appLovinAdRewardListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    appLovinAdRewardListener.userRewardVerified(h.b(appLovinAd), map);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad reward listener about successful reward validation request", th);
                }
            }
        });
    }

    public static void a(final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, final AppLovinAd appLovinAd, final double d, final boolean z, final com.applovin.impl.sdk.h hVar) {
        if (appLovinAd == null || appLovinAdVideoPlaybackListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.21
            @Override // java.lang.Runnable
            public void run() {
                try {
                    appLovinAdVideoPlaybackListener.videoPlaybackEnded(h.b(appLovinAd), d, z);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad playback ended", th);
                }
            }
        });
    }

    public static void a(final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, final AppLovinAd appLovinAd, final com.applovin.impl.sdk.h hVar) {
        if (appLovinAd == null || appLovinAdVideoPlaybackListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.20
            @Override // java.lang.Runnable
            public void run() {
                try {
                    appLovinAdVideoPlaybackListener.videoPlaybackBegan(h.b(appLovinAd));
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad playback began", th);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AppLovinAd b(AppLovinAd appLovinAd) {
        AppLovinAdBase appLovinAdBase = (AppLovinAdBase) appLovinAd;
        return appLovinAdBase.getDummyAd() != null ? appLovinAdBase.getDummyAd() : appLovinAd;
    }

    public static void b(final AppLovinAdViewEventListener appLovinAdViewEventListener, final AppLovinAd appLovinAd, final AppLovinAdView appLovinAdView, final com.applovin.impl.sdk.h hVar) {
        if (appLovinAd == null || appLovinAdViewEventListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.23
            @Override // java.lang.Runnable
            public void run() {
                try {
                    appLovinAdViewEventListener.adClosedFullscreen(h.b(appLovinAd), appLovinAdView);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about fullscreen closed event", th);
                }
            }
        });
    }

    public static void b(final MaxAdListener maxAdListener, final MaxAd maxAd, final com.applovin.impl.sdk.h hVar) {
        if (maxAd == null || maxAdListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    maxAdListener.onAdDisplayed(maxAd);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being displayed", th);
                }
            }
        });
    }

    public static void b(final AppLovinAdDisplayListener appLovinAdDisplayListener, final AppLovinAd appLovinAd, final com.applovin.impl.sdk.h hVar) {
        if (appLovinAd == null || appLovinAdDisplayListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.12
            @Override // java.lang.Runnable
            public void run() {
                try {
                    appLovinAdDisplayListener.adHidden(h.b(appLovinAd));
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being hidden", th);
                }
            }
        });
    }

    public static void b(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final Map<String, String> map, final com.applovin.impl.sdk.h hVar) {
        if (appLovinAd == null || appLovinAdRewardListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    appLovinAdRewardListener.userOverQuota(h.b(appLovinAd), map);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad reward listener about exceeding quota", th);
                }
            }
        });
    }

    public static void c(final AppLovinAdViewEventListener appLovinAdViewEventListener, final AppLovinAd appLovinAd, final AppLovinAdView appLovinAdView, final com.applovin.impl.sdk.h hVar) {
        if (appLovinAd == null || appLovinAdViewEventListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.24
            @Override // java.lang.Runnable
            public void run() {
                try {
                    appLovinAdViewEventListener.adLeftApplication(h.b(appLovinAd), appLovinAdView);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about application leave event", th);
                }
            }
        });
    }

    public static void c(final MaxAdListener maxAdListener, final MaxAd maxAd, final com.applovin.impl.sdk.h hVar) {
        if (maxAd == null || maxAdListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    maxAdListener.onAdHidden(maxAd);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being hidden", th);
                }
            }
        });
    }

    public static void c(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final Map<String, String> map, final com.applovin.impl.sdk.h hVar) {
        if (appLovinAd == null || appLovinAdRewardListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    appLovinAdRewardListener.userRewardRejected(h.b(appLovinAd), map);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad reward listener about reward validation request being rejected", th);
                }
            }
        });
    }

    public static void d(final MaxAdListener maxAdListener, final MaxAd maxAd, final com.applovin.impl.sdk.h hVar) {
        if (maxAd == null || maxAdListener == null) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.11
            @Override // java.lang.Runnable
            public void run() {
                try {
                    maxAdListener.onAdClicked(maxAd);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being clicked", th);
                }
            }
        });
    }

    public static void e(final MaxAdListener maxAdListener, final MaxAd maxAd, final com.applovin.impl.sdk.h hVar) {
        if (maxAd == null || !(maxAdListener instanceof MaxRewardedAdListener)) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.14
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ((MaxRewardedAdListener) maxAdListener).onRewardedVideoStarted(maxAd);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about rewarded video starting", th);
                }
            }
        });
    }

    public static void f(final MaxAdListener maxAdListener, final MaxAd maxAd, final com.applovin.impl.sdk.h hVar) {
        if (maxAd == null || !(maxAdListener instanceof MaxRewardedAdListener)) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.15
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ((MaxRewardedAdListener) maxAdListener).onRewardedVideoCompleted(maxAd);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about rewarded video completing", th);
                }
            }
        });
    }

    public static void g(final MaxAdListener maxAdListener, final MaxAd maxAd, final com.applovin.impl.sdk.h hVar) {
        if (maxAd == null || !(maxAdListener instanceof MaxAdViewAdListener)) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.17
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ((MaxAdViewAdListener) maxAdListener).onAdExpanded(maxAd);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being expanded", th);
                }
            }
        });
    }

    public static void h(final MaxAdListener maxAdListener, final MaxAd maxAd, final com.applovin.impl.sdk.h hVar) {
        if (maxAd == null || !(maxAdListener instanceof MaxAdViewAdListener)) {
            return;
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.utils.h.18
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ((MaxAdViewAdListener) maxAdListener).onAdCollapsed(maxAd);
                } catch (Throwable th) {
                    hVar.w().c("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being collapsed", th);
                }
            }
        });
    }
}
