package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.android.gms.drive.DriveFile;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
class k implements AppLovinInterstitialAdDialog {
    private static volatile boolean n;
    protected final com.applovin.impl.sdk.h a;
    private final String e;
    private final WeakReference<Context> f;
    private volatile AppLovinAdLoadListener g;
    private volatile AppLovinAdDisplayListener h;
    private volatile AppLovinAdVideoPlaybackListener i;
    private volatile AppLovinAdClickListener j;
    private volatile com.applovin.impl.sdk.ad.g k;
    private volatile com.applovin.impl.sdk.ad.g.b l;
    private volatile h m;
    private static final Map<String, k> d = Collections.synchronizedMap(new HashMap());
    public static volatile boolean b = false;
    public static volatile boolean c = false;

    k(AppLovinSdk appLovinSdk, Context context) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.a = com.applovin.impl.sdk.utils.n.a(appLovinSdk);
        this.e = UUID.randomUUID().toString();
        this.f = new WeakReference<>(context);
        b = true;
        c = false;
    }

    public static k a(String str) {
        return d.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i) {
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.adview.k.4
            @Override // java.lang.Runnable
            public void run() {
                if (k.this.g != null) {
                    k.this.g.failedToReceiveAd(i);
                }
            }
        });
    }

    private void a(com.applovin.impl.sdk.ad.g gVar, final Context context) {
        com.applovin.impl.sdk.n nVarW;
        String str;
        d.put(this.e, this);
        this.k = gVar;
        this.l = this.k != null ? this.k.m() : com.applovin.impl.sdk.ad.g.b.DEFAULT;
        if (!com.applovin.impl.sdk.utils.n.a(gVar, context, this.a)) {
            this.a.G().a(com.applovin.impl.sdk.c.g.o);
            if (this.k instanceof com.applovin.impl.a.a) {
                com.applovin.impl.a.k kVarC = ((com.applovin.impl.a.a) this.k).c();
                if (kVarC == null) {
                    nVarW = this.a.w();
                    str = "Cached video removed from local filesystem for VAST ad and source uri not found. Failing ad show.";
                    nVarW.d("InterstitialAdDialogWrapper", str);
                    a(gVar);
                    return;
                }
                this.a.w().d("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for VAST ad. Setting videoUri to source: " + kVarC.a());
                kVarC.a(kVarC.a());
            } else if (this.k instanceof com.applovin.impl.sdk.ad.a) {
                com.applovin.impl.sdk.ad.a aVar = (com.applovin.impl.sdk.ad.a) this.k;
                if (!aVar.aj() || !aVar.d()) {
                    nVarW = this.a.w();
                    str = "Cached video removed from local filesystem for ad server ad: " + aVar.getAdIdNumber() + " and could not restore video stream url. Failing ad show.";
                    nVarW.d("InterstitialAdDialogWrapper", str);
                    a(gVar);
                    return;
                }
                this.a.w().d("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for ad server ad. Restored video uri to video stream url: " + aVar.e());
            }
        }
        final Class cls = (gVar.n() && com.applovin.impl.sdk.utils.e.a((Class<?>) AppLovinOrientationAwareInterstitialActivity.class, context)) ? AppLovinOrientationAwareInterstitialActivity.class : AppLovinInterstitialActivity.class;
        if (!com.applovin.impl.sdk.utils.e.a((Class<?>) cls, context)) {
            this.a.w().e("AppLovinInterstitialAdDialog", "Unable to show ad. Please make sure you have AppLovinInterstitialActivity declared in your Android Manifest: <activity android:name=\"com.applovin.adview.AppLovinInterstitialActivity\" android:configChanges=\"orientation|screenSize\"/>");
            a(gVar);
            return;
        }
        long jMax = Math.max(0L, ((Long) this.a.a(com.applovin.impl.sdk.b.b.da)).longValue());
        this.a.w().a("InterstitialAdDialogWrapper", "Presenting ad with delay of " + jMax);
        new Handler(context.getMainLooper()).postDelayed(new Runnable() { // from class: com.applovin.impl.adview.k.2
            @Override // java.lang.Runnable
            public void run() {
                k.this.a((Class<?>) cls, context);
            }
        }, jMax);
    }

    private void a(AppLovinAd appLovinAd) {
        if (this.h != null) {
            this.h.adHidden(appLovinAd);
        }
        n = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Class<?> cls, Context context) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(j.KEY_WRAPPER_ID, this.e);
        j.lastKnownWrapper = this;
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        if (context instanceof Activity) {
            try {
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(0, 0);
            } catch (Throwable th) {
                this.a.w().b("InterstitialAdDialogWrapper", "Unable to remove pending transition animations", th);
            }
        } else {
            intent.setFlags(DriveFile.MODE_READ_ONLY);
            context.startActivity(intent);
        }
        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        a(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final AppLovinAd appLovinAd) {
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.adview.k.3
            @Override // java.lang.Runnable
            public void run() {
                if (k.this.g != null) {
                    k.this.g.adReceived(appLovinAd);
                }
            }
        });
    }

    private Context h() {
        WeakReference<Context> weakReference = this.f;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public com.applovin.impl.sdk.h a() {
        return this.a;
    }

    public void a(h hVar) {
        this.m = hVar;
    }

    protected void a(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a.p().loadNextAd(AppLovinAdSize.INTERSTITIAL, appLovinAdLoadListener);
    }

    public void a(boolean z) {
        n = z;
    }

    public AppLovinAd b() {
        return this.k;
    }

    public AppLovinAdVideoPlaybackListener c() {
        return this.i;
    }

    public AppLovinAdDisplayListener d() {
        return this.h;
    }

    @Override // com.applovin.adview.AppLovinInterstitialAdDialog
    public void dismiss() {
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.adview.k.5
            @Override // java.lang.Runnable
            public void run() {
                if (((Boolean) k.this.a.a(com.applovin.impl.sdk.b.b.ff)).booleanValue() && k.this.m == null) {
                    return;
                }
                k.this.m.dismiss();
            }
        });
    }

    public AppLovinAdClickListener e() {
        return this.j;
    }

    public com.applovin.impl.sdk.ad.g.b f() {
        return this.l;
    }

    public void g() {
        b = false;
        c = true;
        d.remove(this.e);
        if (this.k == null || !this.k.Q()) {
            return;
        }
        this.m = null;
    }

    @Override // com.applovin.adview.AppLovinInterstitialAdDialog
    public boolean isAdReadyToDisplay() {
        return this.a.p().hasPreloadedAd(AppLovinAdSize.INTERSTITIAL);
    }

    @Override // com.applovin.adview.AppLovinInterstitialAdDialog
    public boolean isShowing() {
        return n;
    }

    @Override // com.applovin.adview.AppLovinInterstitialAdDialog
    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        this.j = appLovinAdClickListener;
    }

    @Override // com.applovin.adview.AppLovinInterstitialAdDialog
    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.h = appLovinAdDisplayListener;
    }

    @Override // com.applovin.adview.AppLovinInterstitialAdDialog
    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.g = appLovinAdLoadListener;
    }

    @Override // com.applovin.adview.AppLovinInterstitialAdDialog
    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        this.i = appLovinAdVideoPlaybackListener;
    }

    @Override // com.applovin.adview.AppLovinInterstitialAdDialog
    public void show() {
        show(null);
    }

    @Override // com.applovin.adview.AppLovinInterstitialAdDialog
    public void show(final String str) {
        a(new AppLovinAdLoadListener() { // from class: com.applovin.impl.adview.k.1
            @Override // com.applovin.sdk.AppLovinAdLoadListener
            public void adReceived(AppLovinAd appLovinAd) {
                k.this.b(appLovinAd);
                k.this.showAndRender(appLovinAd, str);
            }

            @Override // com.applovin.sdk.AppLovinAdLoadListener
            public void failedToReceiveAd(int i) {
                k.this.a(i);
            }
        });
    }

    @Override // com.applovin.adview.AppLovinInterstitialAdDialog
    public void showAndRender(AppLovinAd appLovinAd) {
        showAndRender(appLovinAd, null);
    }

    @Override // com.applovin.adview.AppLovinInterstitialAdDialog
    public void showAndRender(AppLovinAd appLovinAd, String str) {
        com.applovin.impl.sdk.n nVarW;
        String str2;
        if (isShowing() && !((Boolean) this.a.a(com.applovin.impl.sdk.b.b.fe)).booleanValue()) {
            this.a.w().e("AppLovinInterstitialAdDialog", "Attempted to show an interstitial while one is already displayed; ignoring.");
            return;
        }
        if (!com.applovin.impl.sdk.utils.n.a(appLovinAd, this.a)) {
            a(appLovinAd);
            return;
        }
        Context contextH = h();
        if (contextH != null) {
            AppLovinAd appLovinAdB = com.applovin.impl.sdk.utils.n.b(appLovinAd, this.a);
            if (appLovinAdB != null) {
                if (appLovinAdB instanceof com.applovin.impl.sdk.ad.g) {
                    a((com.applovin.impl.sdk.ad.g) appLovinAdB, contextH);
                    return;
                }
                this.a.w().d("InterstitialAdDialogWrapper", "Failed to show interstitial: unknown ad type provided: '" + appLovinAdB + "'");
                a(appLovinAdB);
                return;
            }
            nVarW = this.a.w();
            str2 = "Failed to show ad: " + appLovinAd;
        } else {
            nVarW = this.a.w();
            str2 = "Failed to show interstitial: stale activity reference provided";
        }
        nVarW.d("InterstitialAdDialogWrapper", str2);
        a(appLovinAd);
    }

    public String toString() {
        return "AppLovinInterstitialAdDialog{}";
    }
}
