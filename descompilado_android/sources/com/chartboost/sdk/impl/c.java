package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError;
import com.facebook.internal.AnalyticsEvents;
import com.ironsource.sdk.constants.Constants;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public final int a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final boolean g;
    public final boolean h;

    private c(int i, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = z;
        this.h = z2;
    }

    public static c a() {
        return new c(0, com.my.target.aa.e.bo, com.my.target.aa.e.bo, "/interstitial/get", "webview/%s/interstitial/get", "/interstitial/show", false, false);
    }

    public static c b() {
        return new c(1, Constants.CONVERT_REWARDED, "rewarded-video", "/reward/get", "webview/%s/reward/get", "/reward/show", true, false);
    }

    public static c c() {
        return new c(2, "inplay", null, "/inplay/get", "no webview endpoint", "/inplay/show", false, true);
    }

    public String a(int i) {
        Object[] objArr = new Object[2];
        objArr[0] = this.c;
        objArr[1] = i == 1 ? "web" : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
        return String.format("%s-%s", objArr);
    }

    public void a(String str) {
        if (com.chartboost.sdk.i.c != null) {
            int i = this.a;
            if (i == 0) {
                com.chartboost.sdk.i.c.didClickInterstitial(str);
            } else {
                if (i != 1) {
                    return;
                }
                com.chartboost.sdk.i.c.didClickRewardedVideo(str);
            }
        }
    }

    public void b(String str) {
        if (com.chartboost.sdk.i.c != null) {
            int i = this.a;
            if (i == 0) {
                com.chartboost.sdk.i.c.didCloseInterstitial(str);
            } else {
                if (i != 1) {
                    return;
                }
                com.chartboost.sdk.i.c.didCloseRewardedVideo(str);
            }
        }
    }

    public void c(String str) {
        if (com.chartboost.sdk.i.c != null) {
            int i = this.a;
            if (i == 0) {
                com.chartboost.sdk.i.c.didDismissInterstitial(str);
            } else {
                if (i != 1) {
                    return;
                }
                com.chartboost.sdk.i.c.didDismissRewardedVideo(str);
            }
        }
    }

    public void d(String str) {
        if (com.chartboost.sdk.i.c != null) {
            int i = this.a;
            if (i == 0) {
                com.chartboost.sdk.i.c.didCacheInterstitial(str);
            } else if (i == 1) {
                com.chartboost.sdk.i.c.didCacheRewardedVideo(str);
            } else {
                if (i != 2) {
                    return;
                }
                com.chartboost.sdk.i.c.didCacheInPlay(str);
            }
        }
    }

    public void a(String str, CBError.CBImpressionError cBImpressionError) {
        if (com.chartboost.sdk.i.c != null) {
            int i = this.a;
            if (i == 0) {
                com.chartboost.sdk.i.c.didFailToLoadInterstitial(str, cBImpressionError);
            } else if (i == 1) {
                com.chartboost.sdk.i.c.didFailToLoadRewardedVideo(str, cBImpressionError);
            } else {
                if (i != 2) {
                    return;
                }
                com.chartboost.sdk.i.c.didFailToLoadInPlay(str, cBImpressionError);
            }
        }
    }

    public void e(String str) {
        if (com.chartboost.sdk.i.c != null) {
            int i = this.a;
            if (i == 0) {
                com.chartboost.sdk.i.c.didDisplayInterstitial(str);
            } else {
                if (i != 1) {
                    return;
                }
                com.chartboost.sdk.i.c.didDisplayRewardedVideo(str);
            }
        }
    }

    public boolean f(String str) {
        if (com.chartboost.sdk.i.c == null) {
            return true;
        }
        int i = this.a;
        if (i == 0) {
            return com.chartboost.sdk.i.c.shouldDisplayInterstitial(str);
        }
        if (i != 1) {
            return true;
        }
        return com.chartboost.sdk.i.c.shouldDisplayRewardedVideo(str);
    }

    public boolean g(String str) {
        if (com.chartboost.sdk.i.c == null || this.a != 0) {
            return true;
        }
        return com.chartboost.sdk.i.c.shouldRequestInterstitial(str);
    }

    public class a implements Runnable {
        private final int b;
        private final String c;
        private final CBError.CBImpressionError d;

        public a(int i, String str, CBError.CBImpressionError cBImpressionError) {
            this.b = i;
            this.c = str;
            this.d = cBImpressionError;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.b;
            if (i == 0) {
                c.this.d(this.c);
                return;
            }
            if (i == 1) {
                c.this.a(this.c);
                return;
            }
            if (i == 2) {
                c.this.b(this.c);
                return;
            }
            if (i == 3) {
                c.this.c(this.c);
            } else if (i == 4) {
                c.this.a(this.c, this.d);
            } else {
                if (i != 5) {
                    return;
                }
                c.this.e(this.c);
            }
        }
    }
}
