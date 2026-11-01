package com.adcolony.sdk;

import android.app.Activity;
import com.facebook.appevents.AppEventsConstants;
import com.integralads.avid.library.adcolony.session.AvidAdSessionManager;
import com.integralads.avid.library.adcolony.session.AvidDisplayAdSession;
import com.integralads.avid.library.adcolony.session.AvidManagedVideoAdSession;
import com.integralads.avid.library.adcolony.session.AvidVideoAdSession;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener;
import com.my.target.bj;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class g {
    private AvidDisplayAdSession a;
    private AvidVideoAdSession b;
    private AvidManagedVideoAdSession c;
    private AdColonyCustomMessageListener d;
    private JSONArray e;
    private int f;
    private String g = "";
    private int h;
    private boolean i;
    private boolean j;
    private boolean k;

    g(JSONObject jSONObject) {
        this.f = -1;
        this.f = a(jSONObject);
        this.e = y.g(jSONObject, "js_resources");
    }

    int a(JSONObject jSONObject) {
        int i = this.f;
        if (i != -1) {
            return i;
        }
        this.h = y.c(jSONObject, "ad_unit_type");
        String strB = y.b(jSONObject, AppEventsConstants.EVENT_PARAM_AD_TYPE);
        int i2 = this.h;
        if (i2 == 0) {
            return 2;
        }
        if (i2 == 1) {
            return strB.equals("video") ? 2 : 1;
        }
        return strB.equals("video") ? 0 : 1;
    }

    void a(c cVar) {
        int i;
        if (!this.k && (i = this.f) >= 0) {
            if (i == 2) {
                c();
            }
            b(cVar);
            int i2 = this.f;
            if (i2 == 0) {
                this.b.getAvidDeferredAdSessionListener().recordReadyEvent();
            } else if (i2 == 1) {
                this.a.getAvidDeferredAdSessionListener().recordReadyEvent();
            } else if (i2 == 2) {
                this.c.getAvidDeferredAdSessionListener().recordReadyEvent();
            }
            this.k = true;
            a("record_ready");
        }
    }

    void a() {
        int iD = d();
        if (iD == 0) {
            this.b.endSession();
            this.b = null;
            a("end_session");
        } else {
            if (iD != 1) {
                return;
            }
            this.a.endSession();
            this.a = null;
            a("end_session");
        }
    }

    void b() {
        final Activity activityC;
        if (this.f < 0 || (activityC = a.c()) == null) {
            return;
        }
        aw.a(new Runnable() { // from class: com.adcolony.sdk.g.1
            @Override // java.lang.Runnable
            public void run() {
                l lVarA = a.a();
                int iD = g.this.d();
                if (iD == 0) {
                    g.this.b = AvidAdSessionManager.startAvidVideoAdSession(activityC, lVarA.D());
                    g gVar = g.this;
                    gVar.g = gVar.b.getAvidAdSessionId();
                    g.this.a("start_session");
                    return;
                }
                if (iD == 1) {
                    g.this.a = AvidAdSessionManager.startAvidDisplayAdSession(activityC, lVarA.D());
                    g gVar2 = g.this;
                    gVar2.g = gVar2.a.getAvidAdSessionId();
                    g.this.a("start_session");
                    return;
                }
                if (iD != 2) {
                    return;
                }
                g.this.c = AvidAdSessionManager.startAvidManagedVideoAdSession(activityC, lVarA.D());
                g gVar3 = g.this;
                gVar3.g = gVar3.c.getAvidAdSessionId();
                g.this.a("start_session");
                for (int i = 0; i < g.this.e.length(); i++) {
                    g.this.c.injectJavaScriptResource(y.c(g.this.e, i));
                    g.this.a("inject_javascript");
                }
            }
        });
    }

    void a(String str) {
        JSONObject jSONObjectA = y.a();
        JSONObject jSONObjectA2 = y.a();
        y.b(jSONObjectA2, "session_type", this.f);
        y.a(jSONObjectA2, "session_id", this.g);
        y.a(jSONObjectA2, "event", str);
        y.a(jSONObjectA, "type", "ias_hook");
        y.a(jSONObjectA, "message", jSONObjectA2.toString());
        new af("CustomMessage.controller_send", 0, jSONObjectA).b();
    }

    void b(c cVar) {
        Activity activityC = a.c();
        if (activityC == null) {
            return;
        }
        a("register_ad_view");
        ay value = a.a().x().get(Integer.valueOf(cVar.c()));
        if (value == null && !cVar.h().isEmpty()) {
            value = cVar.h().entrySet().iterator().next().getValue();
        }
        AvidVideoAdSession avidVideoAdSession = this.b;
        if (avidVideoAdSession != null && value != null) {
            avidVideoAdSession.registerAdView(value, activityC);
            return;
        }
        AvidDisplayAdSession avidDisplayAdSession = this.a;
        if (avidDisplayAdSession != null && value != null) {
            avidDisplayAdSession.registerAdView(value, activityC);
            if (this.h != 1 || cVar == null) {
                return;
            }
            JSONObject jSONObjectA = y.a();
            y.a(jSONObjectA, "id", this.a.getAvidAdSessionId());
            new af("AdSession.send_avid_id", cVar.c(), jSONObjectA).b();
            return;
        }
        AvidManagedVideoAdSession avidManagedVideoAdSession = this.c;
        if (avidManagedVideoAdSession != null) {
            avidManagedVideoAdSession.registerAdView(cVar, activityC);
            cVar.a(this.c);
            a("register_obstructions");
        }
    }

    void c() {
        this.d = new AdColonyCustomMessageListener() { // from class: com.adcolony.sdk.g.2
            @Override // com.adcolony.sdk.AdColonyCustomMessageListener
            public void onAdColonyCustomMessage(AdColonyCustomMessage adColonyCustomMessage) {
                JSONObject jSONObjectA = y.a(adColonyCustomMessage.getMessage());
                String strB = y.b(jSONObjectA, "event_type");
                boolean zD = y.d(jSONObjectA, "replay");
                boolean zEquals = y.b(jSONObjectA, "skip_type").equals("dec");
                if (strB.equals("skip") && zEquals) {
                    return;
                }
                if (zD && (strB.equals("start") || strB.equals("first_quartile") || strB.equals(bj.gr) || strB.equals("third_quartile") || strB.equals(bj.gt))) {
                    return;
                }
                g.this.b(strB);
            }
        };
        AdColony.addCustomMessageListener(this.d, "ias_ad_event");
    }

    void b(String str) {
        AvidManagedVideoAdSession avidManagedVideoAdSession;
        if (!a.d() || (avidManagedVideoAdSession = this.c) == null) {
            return;
        }
        AvidVideoPlaybackListener avidVideoPlaybackListener = avidManagedVideoAdSession.getAvidVideoPlaybackListener();
        try {
            switch (str) {
                case "start":
                    avidVideoPlaybackListener.recordAdLoadedEvent();
                    avidVideoPlaybackListener.recordAdStartedEvent();
                    avidVideoPlaybackListener.recordAdPlayingEvent();
                    avidVideoPlaybackListener.recordAdImpressionEvent();
                    avidVideoPlaybackListener.recordAdVideoStartEvent();
                    a(str);
                    break;
                case "first_quartile":
                    avidVideoPlaybackListener.recordAdVideoFirstQuartileEvent();
                    a(str);
                    break;
                case "midpoint":
                    avidVideoPlaybackListener.recordAdVideoMidpointEvent();
                    a(str);
                    break;
                case "third_quartile":
                    avidVideoPlaybackListener.recordAdVideoThirdQuartileEvent();
                    a(str);
                    break;
                case "complete":
                    avidVideoPlaybackListener.recordAdCompleteEvent();
                    a(str);
                    break;
                case "continue":
                    avidVideoPlaybackListener.recordAdUserCloseEvent();
                    avidVideoPlaybackListener.recordAdStoppedEvent();
                    AdColony.removeCustomMessageListener("ias_ad_event");
                    this.c.endSession();
                    a("end_session");
                    this.c = null;
                    a(str);
                    break;
                case "in_video_engagement":
                case "html5_interaction":
                    avidVideoPlaybackListener.recordAdClickThruEvent();
                    a(str);
                    if (this.j && !this.i) {
                        avidVideoPlaybackListener.recordAdPausedEvent();
                        a("pause");
                        this.i = true;
                        this.j = false;
                        break;
                    }
                    break;
                case "skip":
                case "cancel":
                    avidVideoPlaybackListener.recordAdSkippedEvent();
                    avidVideoPlaybackListener.recordAdStoppedEvent();
                    AdColony.removeCustomMessageListener("ias_ad_event");
                    this.c.endSession();
                    a("end_session");
                    this.c = null;
                    a(str);
                    break;
                case "sound_mute":
                    avidVideoPlaybackListener.recordAdVolumeChangeEvent(0);
                    a(str);
                    break;
                case "sound_unmute":
                    avidVideoPlaybackListener.recordAdVolumeChangeEvent(100);
                    a(str);
                    break;
                case "pause":
                    if (!this.i && !this.j) {
                        avidVideoPlaybackListener.recordAdPausedEvent();
                        a("pause");
                        this.i = true;
                        this.j = false;
                        break;
                    }
                    break;
                case "resume":
                    if (this.i) {
                        avidVideoPlaybackListener.recordAdPlayingEvent();
                        a("resume");
                        this.i = false;
                        break;
                    }
                    break;
            }
        } catch (IllegalStateException unused) {
            new aa.a().a("Recording IAS event for ").a(str).a(" caused IllegalStateException.").a(aa.f);
        }
    }

    int d() {
        return this.f;
    }

    AvidManagedVideoAdSession e() {
        return this.c;
    }

    void f() {
        this.j = true;
    }
}
