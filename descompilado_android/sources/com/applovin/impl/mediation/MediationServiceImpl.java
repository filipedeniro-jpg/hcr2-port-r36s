package com.applovin.impl.mediation;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.n;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxErrorCodes;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.adapter.listeners.MaxSignalCollectionListener;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public class MediationServiceImpl {
    private final com.applovin.impl.sdk.h a;
    private final n b;
    private final g d;
    private final AtomicBoolean c = new AtomicBoolean(false);
    private final LinkedHashSet<String> e = new LinkedHashSet<>();
    private final Object f = new Object();

    private class a implements d, MaxAdViewAdListener, MaxRewardedAdListener {
        private final com.applovin.impl.mediation.b.a b;
        private final MaxAdListener c;

        private a(com.applovin.impl.mediation.b.a aVar, MaxAdListener maxAdListener) {
            this.b = aVar;
            this.c = maxAdListener;
        }

        @Override // com.applovin.impl.mediation.d
        public void a(MaxAd maxAd, int i, String str) {
            MediationServiceImpl.this.b(this.b, i, str, this.c);
        }

        @Override // com.applovin.impl.mediation.d
        public void a(String str, int i, String str2) {
            MediationServiceImpl.this.a(this.b, i, str2, this.c);
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdClicked(MaxAd maxAd) {
            MediationServiceImpl.this.d(this.b);
            com.applovin.impl.sdk.utils.h.d(this.c, maxAd, MediationServiceImpl.this.a);
        }

        @Override // com.applovin.mediation.MaxAdViewAdListener
        public void onAdCollapsed(MaxAd maxAd) {
            com.applovin.impl.sdk.utils.h.h(this.c, maxAd, MediationServiceImpl.this.a);
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdDisplayFailed(MaxAd maxAd, int i) {
            MediationServiceImpl.this.b(this.b, i, "", this.c);
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdDisplayed(MaxAd maxAd) {
            MediationServiceImpl.this.b.a("MediationService", "Scheduling impression for ad via callback...");
            MediationServiceImpl.this.maybeScheduleCallbackAdImpressionPostback(this.b);
            com.applovin.impl.sdk.utils.h.b(this.c, maxAd, MediationServiceImpl.this.a);
            if (maxAd.getFormat() == MaxAdFormat.INTERSTITIAL || maxAd.getFormat() == MaxAdFormat.REWARDED) {
                MediationServiceImpl.this.a.U().c();
            }
        }

        @Override // com.applovin.mediation.MaxAdViewAdListener
        public void onAdExpanded(MaxAd maxAd) {
            com.applovin.impl.sdk.utils.h.g(this.c, maxAd, MediationServiceImpl.this.a);
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdHidden(MaxAd maxAd) {
            com.applovin.impl.sdk.utils.h.c(this.c, maxAd, MediationServiceImpl.this.a);
            if (maxAd.getFormat() == MaxAdFormat.INTERSTITIAL || maxAd.getFormat() == MaxAdFormat.REWARDED) {
                MediationServiceImpl.this.a.U().d();
            }
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdLoadFailed(String str, int i) {
            MediationServiceImpl.this.a(this.b, i, "", this.c);
        }

        @Override // com.applovin.mediation.MaxAdListener
        public void onAdLoaded(MaxAd maxAd) {
            MediationServiceImpl.this.c(this.b);
            com.applovin.impl.sdk.utils.h.a(this.c, maxAd, MediationServiceImpl.this.a);
        }

        @Override // com.applovin.mediation.MaxRewardedAdListener
        public void onRewardedVideoCompleted(MaxAd maxAd) {
            com.applovin.impl.sdk.utils.h.f(this.c, maxAd, MediationServiceImpl.this.a);
        }

        @Override // com.applovin.mediation.MaxRewardedAdListener
        public void onRewardedVideoStarted(MaxAd maxAd) {
            com.applovin.impl.sdk.utils.h.e(this.c, maxAd, MediationServiceImpl.this.a);
        }

        @Override // com.applovin.mediation.MaxRewardedAdListener
        public void onUserRewarded(MaxAd maxAd, MaxReward maxReward) {
            com.applovin.impl.sdk.utils.h.a(this.c, maxAd, maxReward, MediationServiceImpl.this.a);
        }
    }

    public MediationServiceImpl(com.applovin.impl.sdk.h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = hVar;
        this.b = hVar.w();
        this.d = new g(hVar);
    }

    private MaxAdapterParametersImpl.a a(Context context) {
        return new MaxAdapterParametersImpl.a().b(AppLovinPrivacySettings.hasUserConsent(context)).a(AppLovinPrivacySettings.isAgeRestrictedUser(context));
    }

    private void a(int i, String str, com.applovin.impl.mediation.b.a aVar) {
        long jE = aVar.e();
        this.b.a("MediationService", "Firing ad load failure postback with load time: " + jE);
        HashMap map = new HashMap(1);
        map.put("{LOAD_TIME_MS}", String.valueOf(jE));
        a("mlerr", map, i, str, aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.applovin.impl.mediation.b.a aVar, int i, String str, MaxAdListener maxAdListener) {
        a(i, str, aVar);
        destroyAd(aVar);
        com.applovin.impl.sdk.utils.h.a(maxAdListener, aVar.getAdUnitId(), i, this.a);
    }

    private void a(String str, int i, com.applovin.impl.mediation.b.e eVar) {
        a(str, Collections.EMPTY_MAP, i, (String) null, eVar);
    }

    private void a(String str, int i, String str2, com.applovin.impl.mediation.b.e eVar) {
        a(str, Collections.EMPTY_MAP, i, str2, eVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, com.applovin.impl.mediation.b.g gVar) {
        a("serr", Collections.EMPTY_MAP, 0, str, gVar);
    }

    private void a(String str, Map<String, String> map, int i, String str2, com.applovin.impl.mediation.b.e eVar) {
        HashMap map2 = new HashMap(map);
        map2.put("{PLACEMENT}", eVar.E() != null ? eVar.E() : "");
        this.a.F().a(new com.applovin.impl.mediation.c.d(str, map2, i, str2, eVar, this.a), q.a.MEDIATION_POSTBACKS);
    }

    private boolean a(com.applovin.impl.mediation.b.e eVar) {
        boolean zContains;
        synchronized (this.f) {
            zContains = this.e.contains(eVar.u());
        }
        return zContains;
    }

    private void b(int i, String str, com.applovin.impl.mediation.b.a aVar) {
        a("mierr", i, str, aVar);
    }

    private void b(com.applovin.impl.mediation.b.a aVar) {
        this.b.a("MediationService", "Firing ad preload postback for " + aVar.v());
        a("mpreload", 0, aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(com.applovin.impl.mediation.b.a aVar, int i, String str, MaxAdListener maxAdListener) {
        b(i, str, aVar);
        if (aVar.g().compareAndSet(false, true)) {
            com.applovin.impl.sdk.utils.h.a(maxAdListener, aVar, i, this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(com.applovin.impl.mediation.b.a aVar) {
        long jE = aVar.e();
        this.b.a("MediationService", "Firing ad load success postback with load time: " + jE);
        String str = aVar.a() ? "boad" : "load";
        HashMap map = new HashMap(1);
        map.put("{LOAD_TIME_MS}", String.valueOf(jE));
        a(str, map, 0, (String) null, aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(com.applovin.impl.mediation.b.a aVar) {
        a("mclick", 0, aVar);
    }

    void a(com.applovin.impl.mediation.b.a aVar) {
        this.b.a("MediationService", "Firing backup ad used to display for " + aVar.v());
        a("bimp", 0, aVar);
    }

    void a(String str) {
        synchronized (this.f) {
            this.e.add(str);
        }
    }

    public void collectSignal(final com.applovin.impl.mediation.b.g gVar, Activity activity, final com.applovin.impl.mediation.b.f.a aVar) {
        String str;
        n nVar;
        StringBuilder sb;
        String str2;
        if (gVar == null) {
            throw new IllegalArgumentException("No spec specified");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        if (aVar == null) {
            throw new IllegalArgumentException("No callback specified");
        }
        final h hVarA = this.d.a(gVar);
        if (hVarA != null) {
            MaxAdapterParametersImpl maxAdapterParametersImplA = a(activity.getApplicationContext()).a(gVar, activity.getApplicationContext()).a();
            hVarA.a(maxAdapterParametersImplA, activity);
            MaxSignalCollectionListener maxSignalCollectionListener = new MaxSignalCollectionListener() { // from class: com.applovin.impl.mediation.MediationServiceImpl.3
                @Override // com.applovin.mediation.adapter.listeners.MaxSignalCollectionListener
                public void onSignalCollected(String str3) {
                    aVar.a(com.applovin.impl.mediation.b.f.a(gVar, hVarA, str3));
                }

                @Override // com.applovin.mediation.adapter.listeners.MaxSignalCollectionListener
                public void onSignalCollectionFailed(String str3) {
                    MediationServiceImpl.this.a(str3, gVar);
                    aVar.a(com.applovin.impl.mediation.b.f.b(gVar, hVarA, str3));
                }
            };
            if (!gVar.b()) {
                nVar = this.b;
                sb = new StringBuilder();
                str2 = "Collecting signal for adapter: ";
            } else if (a(gVar)) {
                nVar = this.b;
                sb = new StringBuilder();
                str2 = "Collecting signal for now-initialized adapter: ";
            } else {
                this.b.d("MediationService", "Skip collecting signal for not-initialized adapter: " + hVarA.b());
                str = "Adapter not initialized yet";
            }
            sb.append(str2);
            sb.append(hVarA.b());
            nVar.a("MediationService", sb.toString());
            hVarA.a(maxAdapterParametersImplA, gVar, activity, maxSignalCollectionListener);
            return;
        }
        str = "Could not load adapter";
        aVar.a(com.applovin.impl.mediation.b.f.a(gVar, str));
    }

    public void destroyAd(MaxAd maxAd) {
        if (maxAd == null) {
            return;
        }
        this.b.b("MediationService", "Destroying " + maxAd);
        ArrayList<com.applovin.impl.mediation.b.a> arrayList = new ArrayList();
        if (maxAd instanceof e) {
            arrayList.addAll(((e) maxAd).b());
        } else if (maxAd instanceof com.applovin.impl.mediation.b.a) {
            arrayList.add((com.applovin.impl.mediation.b.a) maxAd);
        }
        for (com.applovin.impl.mediation.b.a aVar : arrayList) {
            h hVarB = aVar.b();
            if (hVarB != null) {
                hVarB.g();
                aVar.h();
            }
        }
    }

    public Collection<String> getFailedAdapterClassnames() {
        return this.d.b();
    }

    public LinkedHashSet<String> getInitializedAdapterNames() {
        return this.e;
    }

    public Collection<String> getLoadedAdapterClassnames() {
        return this.d.a();
    }

    public void initializeAdapter(com.applovin.impl.mediation.b.e eVar, Activity activity) {
        if (eVar == null) {
            throw new IllegalArgumentException("No spec specified");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        h hVarA = this.d.a(eVar);
        if (hVarA != null) {
            this.b.b("MediationService", "Initializing adapter " + eVar);
            hVarA.a(a(activity.getApplicationContext()).a(eVar, activity.getApplicationContext()).a(), activity);
        }
    }

    public void loadAd(final String str, MaxAdFormat maxAdFormat, f fVar, Activity activity, MaxAdListener maxAdListener) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("No ad unit ID specified");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        if (maxAdListener == null) {
            throw new IllegalArgumentException("No listener specified");
        }
        this.a.a();
        if (fVar == null) {
            fVar = new f.a().a();
        }
        final com.applovin.impl.mediation.c.c cVar = new com.applovin.impl.mediation.c.c(str, maxAdFormat, fVar, activity, this.a, maxAdListener);
        this.b.b("MediationService", "Scheduling signal collection before fetching next ad for ad unit '" + str + "'");
        final q.a aVarA = com.applovin.impl.mediation.d.c.a(maxAdFormat, this.a);
        this.a.F().a(new com.applovin.impl.mediation.c.b(activity, this.a, new com.applovin.impl.mediation.c.b.a() { // from class: com.applovin.impl.mediation.MediationServiceImpl.1
            @Override // com.applovin.impl.mediation.c.b.a
            public void a(JSONArray jSONArray) {
                cVar.a(jSONArray);
                MediationServiceImpl.this.b.b("MediationService", "Scheduling fetching next ad after signal collection for ad unit '" + str + "'");
                if (((Boolean) MediationServiceImpl.this.a.a(com.applovin.impl.sdk.b.a.c)).booleanValue()) {
                    MediationServiceImpl.this.a.F().a(cVar);
                } else {
                    MediationServiceImpl.this.a.F().a(cVar, aVarA);
                }
            }
        }), aVarA);
    }

    public void loadThirdPartyMediatedAd(String str, com.applovin.impl.mediation.b.a aVar, Activity activity, MaxAdListener maxAdListener) {
        if (aVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        }
        this.b.a("MediationService", "Loading " + aVar + "...");
        b(aVar);
        h hVarA = this.d.a(aVar);
        if (hVarA != null) {
            MaxAdapterParametersImpl maxAdapterParametersImplA = a(activity.getApplicationContext()).a(aVar, activity.getApplicationContext()).a();
            hVarA.a(maxAdapterParametersImplA, activity);
            com.applovin.impl.mediation.b.a aVarA = aVar.a(hVarA);
            hVarA.a(str, aVarA);
            aVarA.f();
            hVarA.a(str, maxAdapterParametersImplA, aVarA, activity, new a(aVarA, maxAdListener));
            return;
        }
        this.b.c("MediationService", "Failed to load " + aVar + ": adapter not loaded");
        a(aVar, MaxErrorCodes.MEDIATION_ADAPTER_LOAD_FAILED, "", maxAdListener);
    }

    public void maybeInitialize(Activity activity) {
        if (this.c.compareAndSet(false, true)) {
            this.a.F().a(new com.applovin.impl.mediation.c.a(activity, this.a), q.a.MEDIATION_MAIN);
        }
    }

    public void maybeScheduleBackupAdPromotedToPrimaryPostback(com.applovin.impl.mediation.b.a aVar) {
        long jE = aVar.e();
        this.b.a("MediationService", "Firing ad load success postback with load time: " + jE);
        HashMap map = new HashMap(1);
        map.put("{LOAD_TIME_MS}", String.valueOf(jE));
        a("load", map, 0, (String) null, aVar);
    }

    public void maybeScheduleCallbackAdImpressionPostback(com.applovin.impl.mediation.b.a aVar) {
        a("mcimp", 0, aVar);
    }

    public void maybeScheduleRawAdImpressionPostback(com.applovin.impl.mediation.b.a aVar) {
        a("mimp", 0, aVar);
    }

    public void maybeScheduleViewabilityAdImpressionPostback(com.applovin.impl.mediation.b.b bVar, long j) {
        HashMap map = new HashMap(1);
        map.put("{VIEWABILITY_FLAGS}", String.valueOf(j));
        map.put("{USED_VIEWABILITY_TIMER}", String.valueOf(bVar.q()));
        a("mvimp", map, 0, (String) null, bVar);
    }

    public void showFullscreenAd(MaxAd maxAd, String str, final Activity activity) {
        if (maxAd == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        if (maxAd instanceof e) {
            maxAd = ((e) maxAd).a(activity);
        }
        if (!(maxAd instanceof com.applovin.impl.mediation.b.c)) {
            this.b.e("MediationService", "Unable to show ad for '" + maxAd.getAdUnitId() + "': only REWARDED or INTERSTITIAL ads are eligible for showFullscreenAd(). " + maxAd.getFormat() + " ad was provided.");
            throw new IllegalArgumentException("Provided ad is not a MediatedFullscreenAd");
        }
        this.a.U().a(true);
        final com.applovin.impl.mediation.b.c cVar = (com.applovin.impl.mediation.b.c) maxAd;
        final h hVarB = cVar.b();
        if (hVarB != null) {
            cVar.d(str);
            long jC = cVar.C();
            this.b.b("MediationService", "Showing ad " + maxAd.getAdUnitId() + " with delay of " + jC + "ms...");
            AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() { // from class: com.applovin.impl.mediation.MediationServiceImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    hVarB.a(cVar, activity);
                    MediationServiceImpl.this.a.U().a(false);
                    MediationServiceImpl.this.b.a("MediationService", "Scheduling impression for ad manually...");
                    MediationServiceImpl.this.maybeScheduleRawAdImpressionPostback(cVar);
                }
            }, jC);
            return;
        }
        this.a.U().a(false);
        this.b.c("MediationService", "Failed to show " + maxAd + ": adapter not found");
        this.b.e("MediationService", "There may be an integration problem with the adapter for ad unit id '" + cVar.getAdUnitId() + "'. Please check if you have a supported version of that SDK integrated into your project.");
        throw new IllegalStateException("Could not find adapter for provided ad");
    }
}
