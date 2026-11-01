package com.adcolony.sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.applovin.mediation.AppLovinNativeAdapter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class d {
    private HashMap<String, c> a;
    private ConcurrentHashMap<String, AdColonyInterstitial> b;
    private HashMap<String, ba> c;
    private HashMap<String, AdColonyNativeAdViewListener> d;
    private HashMap<String, az> e;
    private HashMap<String, f> f;

    void a(String str, ba baVar, AdColonyAdSize adColonyAdSize) {
    }

    d() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(final af afVar) {
        final JSONObject jSONObjectC = afVar.c();
        final String strB = y.b(jSONObjectC, "id");
        final ba baVarRemove = this.c.remove(strB);
        final AdColonyNativeAdViewListener adColonyNativeAdViewListenerRemove = this.d.remove(strB);
        if (baVarRemove == null && adColonyNativeAdViewListenerRemove == null) {
            a(afVar.d(), strB);
            return false;
        }
        final Activity activityC = a.c();
        if (activityC == null) {
            return false;
        }
        aw.a(new Runnable() { // from class: com.adcolony.sdk.d.1
            @Override // java.lang.Runnable
            public void run() {
                az adColonyNativeAdView;
                ba baVar = baVarRemove;
                if (baVar != null) {
                    adColonyNativeAdView = new az(activityC, afVar, baVar);
                    d.this.e.put(strB, adColonyNativeAdView);
                } else {
                    adColonyNativeAdView = new AdColonyNativeAdView(activityC, afVar, adColonyNativeAdViewListenerRemove);
                    d.this.e.put(strB, adColonyNativeAdView);
                }
                adColonyNativeAdView.setAdvertiserName(y.b(jSONObjectC, "name"));
                adColonyNativeAdView.setTitle(y.b(jSONObjectC, "title"));
                adColonyNativeAdView.setDescription(y.b(jSONObjectC, "description"));
                adColonyNativeAdView.setImageFilepath(y.b(jSONObjectC, "thumb_filepath"));
                adColonyNativeAdView.b();
                ba baVar2 = baVarRemove;
                if (baVar2 != null) {
                    baVar2.a(adColonyNativeAdView);
                } else {
                    adColonyNativeAdViewListenerRemove.onRequestFilled((AdColonyNativeAdView) adColonyNativeAdView);
                }
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(af afVar) {
        String strB = y.b(afVar.c(), "id");
        final ba baVarRemove = this.c.remove(strB);
        final AdColonyNativeAdViewListener adColonyNativeAdViewListenerRemove = this.d.remove(strB);
        if (baVarRemove == null && adColonyNativeAdViewListenerRemove == null) {
            a(afVar.d(), strB);
            return false;
        }
        aw.a(new Runnable() { // from class: com.adcolony.sdk.d.12
            @Override // java.lang.Runnable
            public void run() {
                boolean z = baVarRemove == null;
                String str = z ? adColonyNativeAdViewListenerRemove.a : baVarRemove.a;
                AdColonyZone adColonyZone = a.a().f().get(str);
                if (adColonyZone == null) {
                    adColonyZone = new AdColonyZone(str);
                    adColonyZone.b(6);
                }
                if (z) {
                    adColonyNativeAdViewListenerRemove.onRequestNotFilled(adColonyZone);
                } else {
                    baVarRemove.a(adColonyZone);
                }
            }
        });
        return true;
    }

    void a() {
        this.a = new HashMap<>();
        this.b = new ConcurrentHashMap<>();
        this.c = new HashMap<>();
        this.d = new HashMap<>();
        this.e = new HashMap<>();
        this.f = new HashMap<>();
        a.a("AdContainer.create", new ah() { // from class: com.adcolony.sdk.d.23
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                d.this.j(afVar);
            }
        });
        a.a("AdContainer.destroy", new ah() { // from class: com.adcolony.sdk.d.28
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                d.this.k(afVar);
            }
        });
        a.a("AdContainer.move_view_to_index", new ah() { // from class: com.adcolony.sdk.d.29
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                d.this.l(afVar);
            }
        });
        a.a("AdContainer.move_view_to_front", new ah() { // from class: com.adcolony.sdk.d.30
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                d.this.m(afVar);
            }
        });
        a.a("AdSession.finish_fullscreen_ad", new ah() { // from class: com.adcolony.sdk.d.31
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                d.this.i(afVar);
            }
        });
        a.a("AdSession.start_fullscreen_ad", new ah() { // from class: com.adcolony.sdk.d.32
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                d.this.h(afVar);
            }
        });
        a.a("AdSession.native_ad_view_available", new ah() { // from class: com.adcolony.sdk.d.33
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                d.this.d(afVar);
            }
        });
        a.a("AdSession.native_ad_view_unavailable", new ah() { // from class: com.adcolony.sdk.d.2
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                d.this.e(afVar);
            }
        });
        a.a("AdSession.expiring", new ah() { // from class: com.adcolony.sdk.d.3
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                d.this.a(afVar);
            }
        });
        a.a("AdSession.audio_stopped", new ah() { // from class: com.adcolony.sdk.d.4
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                aw.a(new Runnable() { // from class: com.adcolony.sdk.d.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) d.this.b.get(y.b(afVar.c(), "id"));
                        if (adColonyInterstitial == null || adColonyInterstitial.getListener() == null) {
                            return;
                        }
                        adColonyInterstitial.getListener().onAudioStopped(adColonyInterstitial);
                    }
                });
            }
        });
        a.a("AdSession.audio_started", new ah() { // from class: com.adcolony.sdk.d.5
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                aw.a(new Runnable() { // from class: com.adcolony.sdk.d.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) d.this.b.get(y.b(afVar.c(), "id"));
                        if (adColonyInterstitial == null || adColonyInterstitial.getListener() == null) {
                            return;
                        }
                        adColonyInterstitial.getListener().onAudioStarted(adColonyInterstitial);
                    }
                });
            }
        });
        a.a("AudioPlayer.create", new ah() { // from class: com.adcolony.sdk.d.6
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                d.this.n(afVar);
            }
        });
        a.a("AudioPlayer.destroy", new ah() { // from class: com.adcolony.sdk.d.7
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (d.this.c(afVar)) {
                    d.this.o(afVar);
                }
            }
        });
        a.a("AudioPlayer.play", new ah() { // from class: com.adcolony.sdk.d.8
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (d.this.c(afVar)) {
                    d.this.p(afVar);
                }
            }
        });
        a.a("AudioPlayer.pause", new ah() { // from class: com.adcolony.sdk.d.9
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (d.this.c(afVar)) {
                    d.this.q(afVar);
                }
            }
        });
        a.a("AudioPlayer.stop", new ah() { // from class: com.adcolony.sdk.d.10
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (d.this.c(afVar)) {
                    d.this.r(afVar);
                }
            }
        });
        a.a("AdSession.interstitial_available", new ah() { // from class: com.adcolony.sdk.d.11
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                d.this.g(afVar);
            }
        });
        a.a("AdSession.interstitial_unavailable", new ah() { // from class: com.adcolony.sdk.d.13
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                d.this.b(afVar);
            }
        });
        a.a("AdSession.has_audio", new ah() { // from class: com.adcolony.sdk.d.14
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                d.this.f(afVar);
            }
        });
        a.a("WebView.prepare", new ah() { // from class: com.adcolony.sdk.d.15
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                JSONObject jSONObjectA = y.a();
                y.a(jSONObjectA, "success", true);
                afVar.a(jSONObjectA).b();
            }
        });
        a.a("AdSession.iap_event", new ah() { // from class: com.adcolony.sdk.d.16
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                JSONObject jSONObjectC = afVar.c();
                if (y.c(jSONObjectC, "type") != 2) {
                    return;
                }
                az azVar = (az) d.this.e.get(y.b(jSONObjectC, "id"));
                JSONObject jSONObjectF = y.f(jSONObjectC, "v4iap");
                JSONArray jSONArrayG = y.g(jSONObjectF, "product_ids");
                if (azVar == null || jSONObjectF == null || jSONArrayG.length() <= 0) {
                    return;
                }
                ((AdColonyNativeAdViewListener) azVar.getListener()).onIAPEvent((AdColonyNativeAdView) azVar, y.c(jSONArrayG, 0), y.c(jSONObjectF, "engagement_type"));
            }
        });
        a.a("AdSession.native_ad_view_finished", new ah() { // from class: com.adcolony.sdk.d.17
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                aw.a(new Runnable() { // from class: com.adcolony.sdk.d.17.1
                    @Override // java.lang.Runnable
                    public void run() {
                        az azVar = (az) d.this.e.get(y.b(afVar.c(), "id"));
                        if (azVar == null || azVar.getListener() == null || !(azVar instanceof AdColonyNativeAdView)) {
                            return;
                        }
                        ((AdColonyNativeAdViewListener) azVar.getListener()).onNativeVideoFinished((AdColonyNativeAdView) azVar);
                    }
                });
            }
        });
        a.a("AdSession.native_ad_view_started", new ah() { // from class: com.adcolony.sdk.d.18
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                aw.a(new Runnable() { // from class: com.adcolony.sdk.d.18.1
                    @Override // java.lang.Runnable
                    public void run() {
                        az azVar = (az) d.this.e.get(y.b(afVar.c(), "id"));
                        if (azVar == null || azVar.getListener() == null || !(azVar instanceof AdColonyNativeAdView)) {
                            return;
                        }
                        ((AdColonyNativeAdViewListener) azVar.getListener()).onNativeVideoStarted((AdColonyNativeAdView) azVar);
                    }
                });
            }
        });
        a.a("AdSession.destroy_native_ad_view", new ah() { // from class: com.adcolony.sdk.d.19
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                aw.a(new Runnable() { // from class: com.adcolony.sdk.d.19.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JSONObject jSONObjectC = afVar.c();
                        az azVar = (az) d.this.e.get(y.b(jSONObjectC, "id"));
                        if (azVar != null) {
                            azVar.a();
                            afVar.a(jSONObjectC).b();
                        }
                    }
                });
            }
        });
        a.a("AdSession.expanded", new ah() { // from class: com.adcolony.sdk.d.20
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                aw.a(new Runnable() { // from class: com.adcolony.sdk.d.20.1
                    @Override // java.lang.Runnable
                    public void run() {
                        af afVar2 = afVar;
                        afVar2.a(afVar2.c()).b();
                    }
                });
            }
        });
        a.a("AdSession.native_ad_muted", new ah() { // from class: com.adcolony.sdk.d.21
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                aw.a(new Runnable() { // from class: com.adcolony.sdk.d.21.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JSONObject jSONObjectC = afVar.c();
                        az azVar = (az) d.this.e.get(y.b(jSONObjectC, "id"));
                        boolean zD = y.d(jSONObjectC, "muted");
                        e listener = azVar != null ? azVar.getListener() : null;
                        if (!(azVar instanceof AdColonyNativeAdView) || listener == null) {
                            return;
                        }
                        if (zD) {
                            ((AdColonyNativeAdViewListener) listener).onAudioStopped((AdColonyNativeAdView) azVar);
                        } else {
                            ((AdColonyNativeAdViewListener) listener).onAudioStarted((AdColonyNativeAdView) azVar);
                        }
                    }
                });
            }
        });
    }

    boolean a(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        String strB = y.b(jSONObjectC, "id");
        if (y.c(jSONObjectC, "type") != 0) {
            return true;
        }
        final AdColonyInterstitial adColonyInterstitialRemove = this.b.remove(strB);
        final AdColonyInterstitialListener listener = adColonyInterstitialRemove == null ? null : adColonyInterstitialRemove.getListener();
        if (listener == null) {
            a(afVar.d(), strB);
            return false;
        }
        if (!a.d()) {
            return false;
        }
        aw.a(new Runnable() { // from class: com.adcolony.sdk.d.22
            @Override // java.lang.Runnable
            public void run() {
                adColonyInterstitialRemove.a(true);
                listener.onExpiring(adColonyInterstitialRemove);
                o oVarR = a.a().r();
                if (oVarR.b() != null) {
                    oVarR.b().dismiss();
                    oVarR.a((AlertDialog) null);
                }
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f(af afVar) {
        String strB = y.b(afVar.c(), "id");
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "id", strB);
        Activity activityC = a.c();
        if (activityC == null) {
            y.a(jSONObjectA, "has_audio", false);
            afVar.a(jSONObjectA).b();
            return false;
        }
        boolean zA = aw.a(aw.a((Context) activityC));
        double dB = aw.b(aw.a((Context) activityC));
        y.a(jSONObjectA, "has_audio", zA);
        y.a(jSONObjectA, "volume", dB);
        afVar.a(jSONObjectA).b();
        return zA;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g(af afVar) {
        String strB = y.b(afVar.c(), "id");
        final AdColonyInterstitial adColonyInterstitial = this.b.get(strB);
        final AdColonyInterstitialListener listener = adColonyInterstitial == null ? null : adColonyInterstitial.getListener();
        if (listener == null) {
            a(afVar.d(), strB);
            return false;
        }
        if (!a.d()) {
            return false;
        }
        adColonyInterstitial.a(y.f(afVar.c(), "ias"));
        adColonyInterstitial.a(y.b(afVar.c(), AppLovinNativeAdapter.KEY_EXTRA_AD_ID));
        adColonyInterstitial.b(y.b(afVar.c(), "creative_id"));
        if (adColonyInterstitial.g()) {
            adColonyInterstitial.h().b();
        }
        aw.a(new Runnable() { // from class: com.adcolony.sdk.d.24
            @Override // java.lang.Runnable
            public void run() {
                listener.onRequestFilled(adColonyInterstitial);
            }
        });
        return true;
    }

    boolean b(af afVar) {
        String strB = y.b(afVar.c(), "id");
        final AdColonyInterstitial adColonyInterstitialRemove = this.b.remove(strB);
        final AdColonyInterstitialListener listener = adColonyInterstitialRemove == null ? null : adColonyInterstitialRemove.getListener();
        if (listener == null) {
            a(afVar.d(), strB);
            return false;
        }
        if (!a.d()) {
            return false;
        }
        aw.a(new Runnable() { // from class: com.adcolony.sdk.d.25
            @Override // java.lang.Runnable
            public void run() {
                AdColonyZone adColonyZone = a.a().f().get(adColonyInterstitialRemove.getZoneID());
                if (adColonyZone == null) {
                    adColonyZone = new AdColonyZone(adColonyInterstitialRemove.getZoneID());
                    adColonyZone.b(6);
                }
                listener.onRequestNotFilled(adColonyZone);
            }
        });
        return true;
    }

    boolean c(af afVar) {
        String strB = y.b(afVar.c(), "ad_session_id");
        c cVar = this.a.get(strB);
        f fVar = this.f.get(strB);
        if (cVar != null && fVar != null) {
            return true;
        }
        new aa.a().a("Invalid AudioPlayer message!").a(aa.g);
        return false;
    }

    void a(String str, AdColonyNativeAdViewListener adColonyNativeAdViewListener, AdColonyAdSize adColonyAdSize, AdColonyAdOptions adColonyAdOptions) {
        float fO = a.a().n().o();
        String strE = aw.e();
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "zone_id", str);
        y.b(jSONObjectA, "type", 2);
        y.b(jSONObjectA, "width", (int) (adColonyAdSize.a * fO));
        y.b(jSONObjectA, "height", (int) (adColonyAdSize.b * fO));
        y.a(jSONObjectA, "id", strE);
        adColonyNativeAdViewListener.a = str;
        if (adColonyAdOptions != null && adColonyAdOptions.d != null) {
            y.a(jSONObjectA, "options", adColonyAdOptions.d);
        }
        this.d.put(strE, adColonyNativeAdViewListener);
        new af("AdSession.on_request", 1, jSONObjectA).b();
    }

    void a(String str, AdColonyInterstitialListener adColonyInterstitialListener, AdColonyAdOptions adColonyAdOptions) {
        String strE = aw.e();
        l lVarA = a.a();
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "zone_id", str);
        y.a(jSONObjectA, "fullscreen", true);
        y.b(jSONObjectA, "width", lVarA.c.p());
        y.b(jSONObjectA, "height", lVarA.c.q());
        y.b(jSONObjectA, "type", 0);
        y.a(jSONObjectA, "id", strE);
        new aa.a().a("AdSession request with id = ").a(strE).a(aa.b);
        AdColonyInterstitial adColonyInterstitial = new AdColonyInterstitial(strE, adColonyInterstitialListener, str);
        this.b.put(strE, adColonyInterstitial);
        if (adColonyAdOptions != null && adColonyAdOptions.d != null) {
            adColonyInterstitial.a(adColonyAdOptions);
            y.a(jSONObjectA, "options", adColonyAdOptions.d);
        }
        new aa.a().a("Requesting AdColony interstitial advertisement.").a(aa.a);
        new af("AdSession.on_request", 1, jSONObjectA).b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h(af afVar) {
        Activity activityC = a.c();
        if (activityC == null) {
            return false;
        }
        JSONObject jSONObjectC = afVar.c();
        l lVarA = a.a();
        String strB = y.b(jSONObjectC, "id");
        AdColonyInterstitial adColonyInterstitial = this.b.get(strB);
        az azVar = this.e.get(strB);
        int iA = y.a(jSONObjectC, "orientation", -1);
        boolean z = azVar != null;
        if (adColonyInterstitial == null && !z) {
            a(afVar.d(), strB);
            return false;
        }
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "id", strB);
        if (adColonyInterstitial != null) {
            adColonyInterstitial.a(y.c(jSONObjectA, "module_id"));
            adColonyInterstitial.b(iA);
            adColonyInterstitial.a();
        } else if (z) {
            azVar.b = iA;
            lVarA.a(azVar.getExpandedContainer());
            lVarA.a(azVar);
            activityC.startActivity(new Intent(activityC, (Class<?>) AdColonyAdViewActivity.class));
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        int iC = y.c(jSONObjectC, "status");
        if (iC == 5 || iC == 1 || iC == 0 || iC == 6) {
            return false;
        }
        String strB = y.b(jSONObjectC, "id");
        final AdColonyInterstitial adColonyInterstitialRemove = this.b.remove(strB);
        final AdColonyInterstitialListener listener = adColonyInterstitialRemove == null ? null : adColonyInterstitialRemove.getListener();
        if (listener == null) {
            a(afVar.d(), strB);
            return false;
        }
        aw.a(new Runnable() { // from class: com.adcolony.sdk.d.26
            @Override // java.lang.Runnable
            public void run() {
                a.a().c(false);
                listener.onClosed(adColonyInterstitialRemove);
            }
        });
        adColonyInterstitialRemove.a((c) null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j(af afVar) {
        Activity activityC = a.c();
        if (activityC == null) {
            return false;
        }
        JSONObject jSONObjectC = afVar.c();
        String strB = y.b(jSONObjectC, "ad_session_id");
        c cVar = new c(activityC, strB);
        cVar.b(afVar);
        if (this.a.containsKey(strB)) {
            az azVar = this.e.get(strB);
            if (azVar == null) {
                return false;
            }
            azVar.setExpandedContainer(cVar);
            return true;
        }
        new aa.a().a("Inserting container into hash map tied to ad session id: ").a(strB).a(aa.b);
        this.a.put(strB, cVar);
        if (y.c(jSONObjectC, "width") == 0) {
            if (this.b.get(strB) == null) {
                a(afVar.d(), strB);
                return false;
            }
            this.b.get(strB).a(cVar);
        } else {
            cVar.a(false);
        }
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "success", true);
        afVar.a(jSONObjectA).b();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k(af afVar) {
        String strB = y.b(afVar.c(), "ad_session_id");
        c cVar = this.a.get(strB);
        if (cVar == null) {
            a(afVar.d(), strB);
            return false;
        }
        a(cVar);
        return true;
    }

    void a(final c cVar) {
        aw.a(new Runnable() { // from class: com.adcolony.sdk.d.27
            @Override // java.lang.Runnable
            public void run() {
                for (int i = 0; i < cVar.n().size(); i++) {
                    a.b(cVar.o().get(i), cVar.n().get(i));
                }
                cVar.o().clear();
                cVar.n().clear();
                cVar.removeAllViews();
                c cVar2 = cVar;
                cVar2.d = null;
                cVar2.c = null;
                new aa.a().a("Destroying container tied to ad_session_id = ").a(cVar.b()).a(aa.d);
                Iterator<u> it = cVar.g().values().iterator();
                while (it.hasNext()) {
                    it.next().b();
                }
                for (ay ayVar : cVar.h().values()) {
                    if (!ayVar.g()) {
                        a.a().a(ayVar.a());
                        ayVar.loadUrl("about:blank");
                        ayVar.clearCache(true);
                        ayVar.removeAllViews();
                        ayVar.a(true);
                    }
                }
                new aa.a().a("Stopping and releasing all media players associated with ").a("VideoViews tied to ad_session_id = ").a(cVar.b()).a(aa.d);
                for (ax axVar : cVar.e().values()) {
                    axVar.d();
                    axVar.g();
                }
                cVar.e().clear();
                cVar.f().clear();
                cVar.h().clear();
                cVar.g().clear();
                cVar.k().clear();
                cVar.m().clear();
                cVar.j().clear();
                cVar.l().clear();
                cVar.a = true;
            }
        });
        az azVar = this.e.get(cVar.b());
        if (azVar == null || azVar.c()) {
            new aa.a().a("Removing ad 4").a(aa.b);
            this.a.remove(cVar.b());
            cVar.c = null;
        }
    }

    void a(String str, String str2) {
        new aa.a().a("Message '").a(str).a("' sent with invalid id: ").a(str2).a(aa.g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        String strD = afVar.d();
        String strB = y.b(jSONObjectC, "ad_session_id");
        int iC = y.c(jSONObjectC, "view_id");
        c cVar = this.a.get(strB);
        View view = cVar.m().get(Integer.valueOf(iC));
        if (cVar == null) {
            a(strD, strB);
            return false;
        }
        if (view == null) {
            a(strD, "" + iC);
            return false;
        }
        view.bringToFront();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m(af afVar) {
        az azVar;
        JSONObject jSONObjectC = afVar.c();
        String strD = afVar.d();
        String strB = y.b(jSONObjectC, "ad_session_id");
        int iC = y.c(jSONObjectC, "view_id");
        c expandedContainer = this.a.get(strB);
        if (expandedContainer == null) {
            a(strD, strB);
            return false;
        }
        if (expandedContainer.d() == 0 && y.c(jSONObjectC, "id") == 1 && (azVar = this.e.get(strB)) != null && azVar.getExpandedContainer() != null) {
            expandedContainer = azVar.getExpandedContainer();
        }
        View view = expandedContainer.m().get(Integer.valueOf(iC));
        if (view == null) {
            a(strD, "" + iC);
            return false;
        }
        expandedContainer.removeView(view);
        expandedContainer.addView(view, view.getLayoutParams());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n(af afVar) {
        String strB = y.b(afVar.c(), "ad_session_id");
        c cVar = this.a.get(strB);
        if (cVar == null) {
            a(afVar.d(), strB);
            return false;
        }
        f fVar = this.f.get(strB);
        if (fVar == null) {
            fVar = new f(strB, cVar.c());
            this.f.put(strB, fVar);
        }
        fVar.a(afVar);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean o(af afVar) {
        String strB = y.b(afVar.c(), "ad_session_id");
        f fVar = this.f.get(strB);
        if (fVar == null) {
            a(afVar.d(), strB);
            return false;
        }
        fVar.d(afVar);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean p(af afVar) {
        String strB = y.b(afVar.c(), "ad_session_id");
        f fVar = this.f.get(strB);
        if (fVar == null) {
            a(afVar.d(), strB);
            return false;
        }
        fVar.c(afVar);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean q(af afVar) {
        String strB = y.b(afVar.c(), "ad_session_id");
        f fVar = this.f.get(strB);
        if (fVar == null) {
            a(afVar.d(), strB);
            return false;
        }
        fVar.b(afVar);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean r(af afVar) {
        String strB = y.b(afVar.c(), "ad_session_id");
        f fVar = this.f.get(strB);
        if (fVar == null) {
            a(afVar.d(), strB);
            return false;
        }
        fVar.e(afVar);
        return true;
    }

    HashMap<String, c> b() {
        return this.a;
    }

    ConcurrentHashMap<String, AdColonyInterstitial> c() {
        return this.b;
    }

    HashMap<String, ba> d() {
        return this.c;
    }

    HashMap<String, AdColonyNativeAdViewListener> e() {
        return this.d;
    }

    HashMap<String, az> f() {
        return this.e;
    }

    HashMap<String, f> g() {
        return this.f;
    }
}
