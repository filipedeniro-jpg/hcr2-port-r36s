package com.applovin.impl.sdk;

import android.content.Intent;
import android.text.TextUtils;
import com.applovin.sdk.AppLovinEventParameters;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinEventTypes;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinWebViewActivity;
import com.facebook.appevents.AppEventsConstants;
import com.fingersoft.billing.util.IabHelper;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class EventServiceImpl implements AppLovinEventService {
    private final Map<String, Object> a;
    private final h b;

    public EventServiceImpl(h hVar) {
        this.b = hVar;
        if (((Boolean) hVar.a(com.applovin.impl.sdk.b.b.aQ)).booleanValue()) {
            this.a = com.applovin.impl.sdk.utils.g.a((String) this.b.b(com.applovin.impl.sdk.b.d.l, "{}"), new HashMap(), this.b);
        } else {
            this.a = new HashMap();
            hVar.a(com.applovin.impl.sdk.b.d.l, "{}");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a() {
        return ((String) this.b.a(com.applovin.impl.sdk.b.b.aI)) + "4.0/pix";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public HashMap<String, String> a(j jVar, i.a aVar) {
        i iVarJ = this.b.J();
        i.d dVarA = iVarJ.a();
        i.b bVarC = iVarJ.c();
        boolean zContains = this.b.b(com.applovin.impl.sdk.b.b.aN).contains(jVar.a());
        HashMap<String, String> map = new HashMap<>();
        map.put("event", zContains ? com.applovin.impl.sdk.utils.k.e(jVar.a()) : "postinstall");
        map.put("ts", Long.toString(jVar.c()));
        map.put("platform", com.applovin.impl.sdk.utils.k.e(dVarA.c));
        map.put("model", com.applovin.impl.sdk.utils.k.e(dVarA.a));
        map.put(InMobiNetworkValues.PACKAGE_NAME, com.applovin.impl.sdk.utils.k.e(bVarC.c));
        map.put("installer_name", com.applovin.impl.sdk.utils.k.e(bVarC.d));
        map.put("ia", Long.toString(bVarC.f));
        map.put("api_did", this.b.a(com.applovin.impl.sdk.b.b.U));
        map.put("brand", com.applovin.impl.sdk.utils.k.e(dVarA.d));
        map.put("brand_name", com.applovin.impl.sdk.utils.k.e(dVarA.e));
        map.put("hardware", com.applovin.impl.sdk.utils.k.e(dVarA.f));
        map.put("revision", com.applovin.impl.sdk.utils.k.e(dVarA.g));
        map.put("sdk_version", AppLovinSdk.VERSION);
        map.put("os", com.applovin.impl.sdk.utils.k.e(dVarA.b));
        map.put("orientation_lock", dVarA.l);
        map.put("app_version", com.applovin.impl.sdk.utils.k.e(bVarC.b));
        map.put("country_code", com.applovin.impl.sdk.utils.k.e(dVarA.i));
        map.put("carrier", com.applovin.impl.sdk.utils.k.e(dVarA.j));
        map.put("tz_offset", String.valueOf(dVarA.o));
        map.put("adr", dVarA.q ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        map.put("volume", String.valueOf(dVarA.s));
        map.put("sim", dVarA.u ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        map.put("gy", String.valueOf(dVarA.v));
        map.put("tv", String.valueOf(dVarA.w));
        map.put("tg", bVarC.e);
        map.put("fs", String.valueOf(dVarA.y));
        map.put("fm", String.valueOf(dVarA.z.b));
        map.put("tm", String.valueOf(dVarA.z.a));
        map.put("lmt", String.valueOf(dVarA.z.c));
        map.put("lm", String.valueOf(dVarA.z.d));
        if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eV)).booleanValue()) {
            map.put(AppLovinWebViewActivity.INTENT_EXTRA_KEY_SDK_KEY, this.b.u());
        }
        a(aVar, map);
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ee)).booleanValue()) {
            com.applovin.impl.sdk.utils.n.a("cuid", this.b.l(), map);
        }
        Boolean bool = dVarA.A;
        if (bool != null) {
            map.put("huc", bool.toString());
        }
        Boolean bool2 = dVarA.B;
        if (bool2 != null) {
            map.put("aru", bool2.toString());
        }
        i.c cVar = dVarA.r;
        if (cVar != null) {
            map.put("act", String.valueOf(cVar.a));
            map.put("acm", String.valueOf(cVar.b));
        }
        String str = dVarA.t;
        if (com.applovin.impl.sdk.utils.k.b(str)) {
            map.put("ua", com.applovin.impl.sdk.utils.k.e(str));
        }
        String str2 = dVarA.x;
        if (!TextUtils.isEmpty(str2)) {
            map.put("so", com.applovin.impl.sdk.utils.k.e(str2));
        }
        if (!zContains) {
            map.put("sub_event", com.applovin.impl.sdk.utils.k.e(jVar.a()));
        }
        map.put("sc", com.applovin.impl.sdk.utils.k.e((String) this.b.a(com.applovin.impl.sdk.b.b.X)));
        map.put("sc2", com.applovin.impl.sdk.utils.k.e((String) this.b.a(com.applovin.impl.sdk.b.b.Y)));
        map.put("server_installed_at", com.applovin.impl.sdk.utils.k.e((String) this.b.a(com.applovin.impl.sdk.b.b.Z)));
        com.applovin.impl.sdk.utils.n.a("persisted_data", com.applovin.impl.sdk.utils.k.e((String) this.b.a(com.applovin.impl.sdk.b.d.t)), map);
        return map;
    }

    private void a(com.applovin.impl.sdk.d.i.a aVar) {
        this.b.F().a(new com.applovin.impl.sdk.d.i(this.b, aVar), com.applovin.impl.sdk.d.q.a.BACKGROUND);
    }

    private void a(i.a aVar, Map<String, String> map) {
        String str = aVar.b;
        if (com.applovin.impl.sdk.utils.k.b(str)) {
            map.put("idfa", str);
        }
        map.put("dnt", Boolean.toString(aVar.a));
    }

    private void a(final String str, final Map<String, String> map, final boolean z) {
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.aO)).booleanValue()) {
            this.b.w().a("AppLovinEventService", "Tracking event: \"" + str + "\" with parameters: " + map);
            a(new com.applovin.impl.sdk.d.i.a() { // from class: com.applovin.impl.sdk.EventServiceImpl.1
                @Override // com.applovin.impl.sdk.d.i.a
                public void a(i.a aVar) {
                    j jVar = new j(str, map, EventServiceImpl.this.a);
                    try {
                        if (z) {
                            EventServiceImpl.this.b.I().a(com.applovin.impl.sdk.network.e.j().a(EventServiceImpl.this.a()).b(EventServiceImpl.this.b()).a(EventServiceImpl.this.a(jVar, aVar)).b(jVar.b()).a(((Boolean) EventServiceImpl.this.b.a(com.applovin.impl.sdk.b.b.eV)).booleanValue()).a());
                        } else {
                            EventServiceImpl.this.b.M().dispatchPostbackRequest(com.applovin.impl.sdk.network.f.b(EventServiceImpl.this.b).a(EventServiceImpl.this.a()).c(EventServiceImpl.this.b()).b((Map<String, String>) EventServiceImpl.this.a(jVar, aVar)).a(com.applovin.impl.sdk.utils.g.a((Map<String, ?>) jVar.b())).a(((Boolean) EventServiceImpl.this.b.a(com.applovin.impl.sdk.b.b.eV)).booleanValue()).a(), null);
                        }
                    } catch (Throwable th) {
                        EventServiceImpl.this.b.w().b("AppLovinEventService", "Unable to track event: " + jVar, th);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b() {
        return ((String) this.b.a(com.applovin.impl.sdk.b.b.aJ)) + "4.0/pix";
    }

    private void c() {
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.aQ)).booleanValue()) {
            this.b.a(com.applovin.impl.sdk.b.d.l, com.applovin.impl.sdk.utils.g.a(this.a, "{}", this.b));
        }
    }

    void a(String str, boolean z) {
        a(str, new HashMap(), z);
    }

    @Override // com.applovin.sdk.AppLovinEventService
    public Map<String, Object> getSuperProperties() {
        return new HashMap(this.a);
    }

    @Override // com.applovin.sdk.AppLovinEventService
    public void setSuperProperty(Object obj, String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.w().e("AppLovinEventService", "Super property key cannot be null or empty");
            return;
        }
        if (obj == null) {
            this.a.remove(str);
            c();
            return;
        }
        List<String> listB = this.b.b(com.applovin.impl.sdk.b.b.aP);
        if (com.applovin.impl.sdk.utils.n.a(obj, listB, this.b)) {
            this.a.put(str, com.applovin.impl.sdk.utils.n.a(obj, this.b));
            c();
            return;
        }
        this.b.w().e("AppLovinEventService", "Failed to set super property '" + obj + "' for key '" + str + "' - valid super property types include: " + listB);
    }

    public String toString() {
        return "EventService{}";
    }

    @Override // com.applovin.sdk.AppLovinEventService
    public void trackCheckout(String str, Map<String, String> map) {
        HashMap map2 = map != null ? new HashMap(map) : new HashMap(1);
        map2.put("transaction_id", str);
        trackEvent(AppLovinEventTypes.USER_COMPLETED_CHECKOUT, map2);
    }

    @Override // com.applovin.sdk.AppLovinEventService
    public void trackEvent(String str) {
        trackEvent(str, new HashMap());
    }

    @Override // com.applovin.sdk.AppLovinEventService
    public void trackEvent(String str, Map<String, String> map) {
        a(str, map, true);
    }

    @Override // com.applovin.sdk.AppLovinEventService
    public void trackInAppPurchase(Intent intent, Map<String, String> map) {
        HashMap map2 = map != null ? new HashMap(map) : new HashMap();
        try {
            map2.put(AppLovinEventParameters.IN_APP_PURCHASE_DATA, intent.getStringExtra(IabHelper.RESPONSE_INAPP_PURCHASE_DATA));
            map2.put(AppLovinEventParameters.IN_APP_DATA_SIGNATURE, intent.getStringExtra(IabHelper.RESPONSE_INAPP_SIGNATURE));
        } catch (Throwable th) {
            this.b.w().c("AppLovinEventService", "Unable to track in app purchase - invalid purchase intent", th);
        }
        trackEvent("iap", map2);
    }
}
