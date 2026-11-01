package com.applovin.impl.sdk;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.text.TextUtils;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.impl.sdk.ad.NativeAdImpl;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdUpdateListener;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AppLovinAdServiceImpl implements AppLovinAdService {
    public static String URI_LOAD_URL = "/adservice/load_url";
    public static String URI_NO_OP = "/adservice/no_op";
    public static String URI_TRACK_CLICK_IMMEDIATELY = "/adservice/track_click_now";
    private final h a;
    private final n b;
    private final Handler c = new Handler(Looper.getMainLooper());
    private final Object e = new Object();
    private final Map<com.applovin.impl.sdk.ad.d, b> d = new HashMap(5);

    private class a implements AppLovinAdLoadListener {
        private final b b;

        private a(b bVar) {
            this.b = bVar;
        }

        @Override // com.applovin.sdk.AppLovinAdLoadListener
        public void adReceived(AppLovinAd appLovinAd) {
            HashSet hashSet;
            com.applovin.impl.sdk.ad.d adZone = ((AppLovinAdBase) appLovinAd).getAdZone();
            if (!(appLovinAd instanceof com.applovin.impl.sdk.ad.h) && adZone.i()) {
                AppLovinAdServiceImpl.this.a.O().adReceived(appLovinAd);
                appLovinAd = new com.applovin.impl.sdk.ad.h(adZone, AppLovinAdServiceImpl.this.a);
            }
            synchronized (this.b.a) {
                hashSet = new HashSet(this.b.c);
                this.b.c.clear();
                this.b.b = false;
            }
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                AppLovinAdServiceImpl.this.a(appLovinAd, (AppLovinAdLoadListener) it.next());
            }
        }

        @Override // com.applovin.sdk.AppLovinAdLoadListener
        public void failedToReceiveAd(int i) {
            HashSet hashSet;
            synchronized (this.b.a) {
                hashSet = new HashSet(this.b.c);
                this.b.c.clear();
                this.b.b = false;
            }
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                AppLovinAdServiceImpl.this.a(i, (AppLovinAdLoadListener) it.next());
            }
        }
    }

    private static class b {
        final Object a;
        boolean b;
        final Collection<AppLovinAdLoadListener> c;

        private b() {
            this.a = new Object();
            this.c = new HashSet();
        }

        public String toString() {
            return "AdLoadState{, isWaitingForAd=" + this.b + ", pendingAdListeners=" + this.c + '}';
        }
    }

    AppLovinAdServiceImpl(h hVar) {
        this.a = hVar;
        this.b = hVar.w();
        this.d.put(com.applovin.impl.sdk.ad.d.c(hVar), new b());
        this.d.put(com.applovin.impl.sdk.ad.d.d(hVar), new b());
        this.d.put(com.applovin.impl.sdk.ad.d.e(hVar), new b());
        this.d.put(com.applovin.impl.sdk.ad.d.f(hVar), new b());
        this.d.put(com.applovin.impl.sdk.ad.d.g(hVar), new b());
    }

    private b a(com.applovin.impl.sdk.ad.d dVar) {
        b bVar;
        synchronized (this.e) {
            bVar = this.d.get(dVar);
            if (bVar == null) {
                bVar = new b();
                this.d.put(dVar, bVar);
            }
        }
        return bVar;
    }

    private String a(String str, int i, String str2, boolean z) {
        try {
            if (!com.applovin.impl.sdk.utils.k.b(str)) {
                return null;
            }
            if (i < 0 || i > 100) {
                i = 0;
            }
            return Uri.parse(str).buildUpon().appendQueryParameter(NativeAdImpl.QUERY_PARAM_VIDEO_PERCENT_VIEWED, Integer.toString(i)).appendQueryParameter("vid_ts", str2).appendQueryParameter("uvs", Boolean.toString(z)).build().toString();
        } catch (Throwable th) {
            this.b.b("AppLovinAdService", "Unknown error parsing the video end url: " + str, th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i, final AppLovinAdLoadListener appLovinAdLoadListener) {
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.fh)).booleanValue()) {
            this.c.post(new Runnable() { // from class: com.applovin.impl.sdk.AppLovinAdServiceImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        appLovinAdLoadListener.failedToReceiveAd(i);
                    } catch (Throwable th) {
                        AppLovinAdServiceImpl.this.b.c("AppLovinAdService", "Unable to notify listener about ad load failure", th);
                    }
                }
            });
            return;
        }
        try {
            appLovinAdLoadListener.failedToReceiveAd(i);
        } catch (Throwable th) {
            this.b.c("AppLovinAdService", "Unable to notify listener about ad load failure", th);
        }
    }

    private void a(Uri uri, com.applovin.impl.sdk.ad.g gVar, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl) {
        if (appLovinAdView == null) {
            this.b.d("AppLovinAdService", "Unable to launch click - adView has been prematurely destroyed");
            return;
        }
        if (com.applovin.impl.sdk.utils.n.a(appLovinAdView.getContext(), uri, this.a)) {
            com.applovin.impl.sdk.utils.h.c(adViewControllerImpl.getAdViewEventListener(), gVar, appLovinAdView, this.a);
        }
        adViewControllerImpl.dismissInterstitialIfRequired();
    }

    private void a(com.applovin.impl.sdk.ad.d dVar, a aVar) {
        AppLovinAd appLovinAd = (AppLovinAd) this.a.O().e(dVar);
        if (appLovinAd != null) {
            this.b.a("AppLovinAdService", "Using pre-loaded ad: " + appLovinAd + " for " + dVar);
            aVar.adReceived(appLovinAd);
        } else {
            a(new com.applovin.impl.sdk.d.m(dVar, aVar, this.a), aVar);
        }
        if (dVar.i() && appLovinAd == null) {
            return;
        }
        if (!dVar.j() && (appLovinAd == null || dVar.g() <= 0)) {
            return;
        }
        this.a.O().i(dVar);
    }

    private void a(com.applovin.impl.sdk.ad.d dVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        n nVar;
        String str;
        String str2;
        int i;
        if (dVar == null) {
            throw new IllegalArgumentException("No zone specified");
        }
        if (appLovinAdLoadListener == null) {
            throw new IllegalArgumentException("No callback specified");
        }
        if (!com.applovin.impl.sdk.utils.f.a(this.a.z(), this.a) && !((Boolean) this.a.a(com.applovin.impl.sdk.b.b.dI)).booleanValue()) {
            this.b.e("AppLovinAdService", "Failing ad load due to no internet connection.");
            i = AppLovinErrorCodes.NO_NETWORK;
        } else {
            if (!((Boolean) this.a.a(com.applovin.impl.sdk.b.b.dV)).booleanValue() || dVar.j() || !this.a.R().a() || this.a.R().a(dVar)) {
                this.a.w().a("AppLovinAdService", "Loading next ad of zone {" + dVar + "}...");
                b bVarA = a(dVar);
                synchronized (bVarA.a) {
                    bVarA.c.add(appLovinAdLoadListener);
                    if (bVarA.b) {
                        nVar = this.b;
                        str = "AppLovinAdService";
                        str2 = "Already waiting on an ad load...";
                    } else {
                        this.b.a("AppLovinAdService", "Loading next ad...");
                        bVarA.b = true;
                        a aVar = new a(bVarA);
                        if (!dVar.h()) {
                            this.b.a("AppLovinAdService", "Task merge not necessary.");
                        } else if (this.a.O().a(dVar, aVar)) {
                            nVar = this.b;
                            str = "AppLovinAdService";
                            str2 = "Attaching load listener to initial preload task...";
                        } else {
                            this.b.a("AppLovinAdService", "Skipped attach of initial preload callback.");
                        }
                        a(dVar, aVar);
                    }
                    nVar.a(str, str2);
                }
                return;
            }
            this.b.e("AppLovinAdService", "Failed to load ad for zone (" + dVar.a() + "). Please check that the zone has been added to your AppLovin account and given at least 30 minutes to fully propagate.");
            i = -7;
        }
        a(i, appLovinAdLoadListener);
    }

    private void a(com.applovin.impl.sdk.c.a aVar) {
        if (!com.applovin.impl.sdk.utils.k.b(aVar.a())) {
            this.b.c("AppLovinAdService", "Requested a postback dispatch for a null URL; nothing to do...");
        } else {
            this.a.I().a(com.applovin.impl.sdk.network.e.j().a(com.applovin.impl.sdk.utils.n.b(aVar.a())).b(com.applovin.impl.sdk.utils.k.b(aVar.b()) ? com.applovin.impl.sdk.utils.n.b(aVar.b()) : null).a(false).a());
        }
    }

    private void a(com.applovin.impl.sdk.d.a aVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        if (!com.applovin.impl.sdk.utils.f.a(this.a.z(), this.a) && !((Boolean) this.a.a(com.applovin.impl.sdk.b.b.dI)).booleanValue()) {
            this.b.e("AppLovinAdService", "Failing ad load due to no internet connection.");
            a(AppLovinErrorCodes.NO_NETWORK, appLovinAdLoadListener);
            return;
        }
        this.a.a();
        this.b.b("AppLovinAdService", "Loading ad using '" + aVar.d() + "'...");
        this.a.F().a(aVar, com.applovin.impl.sdk.d.q.a.MAIN);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final AppLovinAd appLovinAd, final AppLovinAdLoadListener appLovinAdLoadListener) {
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.fh)).booleanValue()) {
            this.c.post(new Runnable() { // from class: com.applovin.impl.sdk.AppLovinAdServiceImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        appLovinAdLoadListener.adReceived(appLovinAd);
                    } catch (Throwable th) {
                        AppLovinAdServiceImpl.this.b.c("AppLovinAdService", "Unable to notify listener about a newly loaded ad", th);
                    }
                }
            });
            return;
        }
        try {
            appLovinAdLoadListener.adReceived(appLovinAd);
        } catch (Throwable th) {
            this.b.c("AppLovinAdService", "Unable to notify listener about a newly loaded ad", th);
        }
    }

    private void a(List<com.applovin.impl.sdk.c.a> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<com.applovin.impl.sdk.c.a> it = list.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener) {
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize) {
    }

    public AppLovinAd dequeueAd(com.applovin.impl.sdk.ad.d dVar) {
        AppLovinAd appLovinAd = (AppLovinAd) this.a.O().d(dVar);
        this.b.a("AppLovinAdService", "Dequeued ad: " + appLovinAd + " for zone: " + dVar + "...");
        return appLovinAd;
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public String getBidToken() {
        com.applovin.impl.sdk.ad.f fVarA;
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                fVarA = this.a.J().a(((Integer) this.a.a(com.applovin.impl.sdk.b.b.aK)).intValue());
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            } catch (Throwable th) {
                this.b.b("AppLovinAdService", "Encountered error while generating bid token", th);
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                fVarA = null;
            }
            if (fVarA == null) {
                return "";
            }
            if (TextUtils.isEmpty(fVarA.a())) {
                this.b.d("AppLovinAdService", "Failed to generate bid token");
            } else {
                this.b.a("AppLovinAdService", "Generated bid token: " + fVarA);
            }
            if (!fVarA.b()) {
                this.b.e("AppLovinAdService", "Bid token generated too early in session - please initialize the SDK first. Not doing so can negatively impact your eCPMs!");
            }
            return fVarA.a();
        } catch (Throwable th2) {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            throw th2;
        }
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public boolean hasPreloadedAd(AppLovinAdSize appLovinAdSize) {
        return this.a.O().g(com.applovin.impl.sdk.ad.d.a(appLovinAdSize, AppLovinAdType.REGULAR, this.a));
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public boolean hasPreloadedAdForZoneId(String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.e("AppLovinAdService", "Unable to check if ad is preloaded - invalid zone id");
            return false;
        }
        return this.a.O().g(com.applovin.impl.sdk.ad.d.a(str, this.a));
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public void loadNextAd(AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener) {
        a(com.applovin.impl.sdk.ad.d.a(appLovinAdSize, AppLovinAdType.REGULAR, this.a), appLovinAdLoadListener);
    }

    public void loadNextAd(String str, AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener) {
        this.b.a("AppLovinAdService", "Loading next ad of zone {" + str + "} with size " + appLovinAdSize);
        a(com.applovin.impl.sdk.ad.d.a(appLovinAdSize, AppLovinAdType.REGULAR, str, this.a), appLovinAdLoadListener);
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public void loadNextAdForAdToken(String str, AppLovinAdLoadListener appLovinAdLoadListener) {
        com.applovin.impl.sdk.d.a rVar;
        String strTrim = str != null ? str.trim() : null;
        if (TextUtils.isEmpty(strTrim)) {
            this.b.e("AppLovinAdService", "Invalid ad token specified");
            a(-8, appLovinAdLoadListener);
            return;
        }
        com.applovin.impl.sdk.ad.c cVar = new com.applovin.impl.sdk.ad.c(strTrim, this.a);
        if (cVar.b() != com.applovin.impl.sdk.ad.c.a.REGULAR) {
            if (cVar.b() == com.applovin.impl.sdk.ad.c.a.AD_RESPONSE_JSON) {
                JSONObject jSONObjectD = cVar.d();
                if (jSONObjectD != null) {
                    com.applovin.impl.sdk.utils.f.f(jSONObjectD, this.a);
                    com.applovin.impl.sdk.utils.f.d(jSONObjectD, this.a);
                    com.applovin.impl.sdk.utils.f.c(jSONObjectD, this.a);
                    if (com.applovin.impl.sdk.utils.g.a(jSONObjectD, "ads", new JSONArray(), this.a).length() <= 0) {
                        this.b.d("AppLovinAdService", "No ad returned from the server for token: " + cVar);
                        appLovinAdLoadListener.failedToReceiveAd(204);
                        return;
                    }
                    this.b.a("AppLovinAdService", "Rendering ad for token: " + cVar);
                    rVar = new com.applovin.impl.sdk.d.r(jSONObjectD, com.applovin.impl.sdk.utils.n.a(jSONObjectD, this.a), com.applovin.impl.sdk.ad.b.DECODED_AD_TOKEN_JSON, appLovinAdLoadListener, this.a);
                } else {
                    this.b.d("AppLovinAdService", "Unable to retrieve ad response JSON from token: " + cVar);
                }
            } else {
                this.b.e("AppLovinAdService", "Invalid ad token specified: " + cVar);
            }
            appLovinAdLoadListener.failedToReceiveAd(-8);
            return;
        }
        this.b.a("AppLovinAdService", "Loading next ad for token: " + cVar);
        rVar = new com.applovin.impl.sdk.d.o(cVar, appLovinAdLoadListener, this.a);
        a(rVar, appLovinAdLoadListener);
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public void loadNextAdForZoneId(String str, AppLovinAdLoadListener appLovinAdLoadListener) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("No zone id specified");
        }
        this.b.a("AppLovinAdService", "Loading next ad of zone {" + str + "}");
        a(com.applovin.impl.sdk.ad.d.a(str, this.a), appLovinAdLoadListener);
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public void loadNextAdForZoneIds(List<String> list, AppLovinAdLoadListener appLovinAdLoadListener) {
        List<String> listA = com.applovin.impl.sdk.utils.d.a(list);
        if (listA == null || listA.isEmpty()) {
            this.b.e("AppLovinAdService", "No zones were provided");
            a(-7, appLovinAdLoadListener);
            return;
        }
        this.b.a("AppLovinAdService", "Loading next ad for zones: " + listA);
        a(new com.applovin.impl.sdk.d.l(listA, appLovinAdLoadListener, this.a), appLovinAdLoadListener);
    }

    public void loadNextIncentivizedAd(String str, AppLovinAdLoadListener appLovinAdLoadListener) {
        this.b.a("AppLovinAdService", "Loading next incentivized ad of zone {" + str + "}");
        a(com.applovin.impl.sdk.ad.d.c(str, this.a), appLovinAdLoadListener);
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public void preloadAd(AppLovinAdSize appLovinAdSize) {
        this.a.a();
        this.a.O().i(com.applovin.impl.sdk.ad.d.a(appLovinAdSize, AppLovinAdType.REGULAR, this.a));
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public void preloadAdForZoneId(String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.e("AppLovinAdService", "Unable to preload ad for invalid zone identifier");
            return;
        }
        com.applovin.impl.sdk.ad.d dVarA = com.applovin.impl.sdk.ad.d.a(str, this.a);
        this.a.O().h(dVarA);
        this.a.O().i(dVarA);
    }

    public void preloadAds(com.applovin.impl.sdk.ad.d dVar) {
        this.a.O().h(dVar);
        int iG = dVar.g();
        if (iG == 0 && this.a.O().b(dVar)) {
            iG = 1;
        }
        this.a.O().b(dVar, iG);
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public void removeAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize) {
    }

    public String toString() {
        return "AppLovinAdService{adLoadStates=" + this.d + '}';
    }

    public void trackAndLaunchClick(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl, Uri uri) {
        if (appLovinAd == null) {
            this.b.d("AppLovinAdService", "Unable to track ad view click. No ad specified");
            return;
        }
        this.b.a("AppLovinAdService", "Tracking click on an ad...");
        com.applovin.impl.sdk.ad.g gVar = (com.applovin.impl.sdk.ad.g) appLovinAd;
        a(gVar.al());
        a(uri, gVar, appLovinAdView, adViewControllerImpl);
    }

    public void trackAndLaunchVideoClick(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, Uri uri) {
        if (appLovinAd == null) {
            this.b.d("AppLovinAdService", "Unable to track video click. No ad specified");
            return;
        }
        this.b.a("AppLovinAdService", "Tracking VIDEO click on an ad...");
        a(((com.applovin.impl.sdk.ad.g) appLovinAd).am());
        com.applovin.impl.sdk.utils.n.a(appLovinAdView.getContext(), uri, this.a);
    }

    public void trackImpression(com.applovin.impl.sdk.ad.g gVar) {
        if (gVar == null) {
            this.b.d("AppLovinAdService", "Unable to track impression click. No ad specified");
        } else {
            this.b.a("AppLovinAdService", "Tracking impression on ad...");
            a(gVar.an());
        }
    }

    public void trackVideoEnd(com.applovin.impl.sdk.ad.g gVar, int i, boolean z) {
        if (gVar == null) {
            this.b.d("AppLovinAdService", "Unable to track video end. No ad specified");
            return;
        }
        this.b.a("AppLovinAdService", "Tracking video end on ad...");
        List<com.applovin.impl.sdk.c.a> listAk = gVar.ak();
        if (listAk == null || listAk.isEmpty()) {
            this.b.c("AppLovinAdService", "Unable to submit persistent postback for AD #" + gVar.getAdIdNumber() + ". Missing video end tracking URL.");
            return;
        }
        String string = Long.toString(System.currentTimeMillis());
        for (com.applovin.impl.sdk.c.a aVar : listAk) {
            if (com.applovin.impl.sdk.utils.k.b(aVar.a())) {
                String strA = a(aVar.a(), i, string, z);
                String strA2 = a(aVar.b(), i, string, z);
                if (strA != null) {
                    a(new com.applovin.impl.sdk.c.a(strA, strA2));
                } else {
                    this.b.d("AppLovinAdService", "Failed to parse url: " + aVar.a());
                }
            } else {
                this.b.c("AppLovinAdService", "Requested a postback dispatch for an empty video end URL; nothing to do...");
            }
        }
    }
}
