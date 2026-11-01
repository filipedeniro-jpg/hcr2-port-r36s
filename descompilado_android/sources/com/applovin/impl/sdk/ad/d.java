package com.applovin.impl.sdk.ad;

import android.text.TextUtils;
import com.applovin.impl.sdk.n;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.facebook.appevents.AppEventsConstants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    private static final Map<String, d> a = new HashMap();
    private static final Object b = new Object();
    private com.applovin.impl.sdk.h c;
    private n d;
    private JSONObject e;
    private final String f;
    private String g;
    private AppLovinAdSize h;
    private AppLovinAdType i;

    private d(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, String str, com.applovin.impl.sdk.h hVar) {
        if (TextUtils.isEmpty(str) && (appLovinAdType == null || appLovinAdSize == null)) {
            throw new IllegalArgumentException("No zone identifier or type or size specified");
        }
        this.c = hVar;
        this.d = hVar != null ? hVar.w() : null;
        this.h = appLovinAdSize;
        this.i = appLovinAdType;
        if (!TextUtils.isEmpty(str)) {
            this.f = str.toLowerCase(Locale.ENGLISH);
            this.g = str.toLowerCase(Locale.ENGLISH);
            return;
        }
        this.f = (appLovinAdSize.getLabel() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + appLovinAdType.getLabel()).toLowerCase(Locale.ENGLISH);
    }

    public static d a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, com.applovin.impl.sdk.h hVar) {
        return a(appLovinAdSize, appLovinAdType, null, hVar);
    }

    public static d a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, String str, com.applovin.impl.sdk.h hVar) {
        d dVar = new d(appLovinAdSize, appLovinAdType, str, hVar);
        synchronized (b) {
            String str2 = dVar.f;
            if (a.containsKey(str2)) {
                dVar = a.get(str2);
            } else {
                a.put(str2, dVar);
            }
        }
        return dVar;
    }

    public static d a(String str, com.applovin.impl.sdk.h hVar) {
        return a(null, null, str, hVar);
    }

    public static d a(String str, JSONObject jSONObject, com.applovin.impl.sdk.h hVar) {
        d dVarA = a(str, hVar);
        dVarA.e = jSONObject;
        return dVarA;
    }

    private <ST> com.applovin.impl.sdk.b.b<ST> a(String str, com.applovin.impl.sdk.b.b<ST> bVar) {
        return this.c.a(str + this.f, bVar);
    }

    private boolean a(com.applovin.impl.sdk.b.b<String> bVar, AppLovinAdSize appLovinAdSize) {
        return ((String) this.c.a(bVar)).toUpperCase(Locale.ENGLISH).contains(appLovinAdSize.getLabel());
    }

    public static d b(String str, com.applovin.impl.sdk.h hVar) {
        return a(AppLovinAdSize.NATIVE, AppLovinAdType.NATIVE, str, hVar);
    }

    public static Collection<d> b(com.applovin.impl.sdk.h hVar) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(8);
        Collections.addAll(linkedHashSet, c(hVar), d(hVar), e(hVar), f(hVar), g(hVar), h(hVar));
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public static d c(com.applovin.impl.sdk.h hVar) {
        return a(AppLovinAdSize.BANNER, AppLovinAdType.REGULAR, hVar);
    }

    public static d c(String str, com.applovin.impl.sdk.h hVar) {
        return a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, str, hVar);
    }

    public static d d(com.applovin.impl.sdk.h hVar) {
        return a(AppLovinAdSize.MREC, AppLovinAdType.REGULAR, hVar);
    }

    public static d e(com.applovin.impl.sdk.h hVar) {
        return a(AppLovinAdSize.LEADER, AppLovinAdType.REGULAR, hVar);
    }

    public static d f(com.applovin.impl.sdk.h hVar) {
        return a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.REGULAR, hVar);
    }

    public static d g(com.applovin.impl.sdk.h hVar) {
        return a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, hVar);
    }

    public static d h(com.applovin.impl.sdk.h hVar) {
        return a(AppLovinAdSize.NATIVE, AppLovinAdType.NATIVE, hVar);
    }

    private boolean k() {
        try {
            if (TextUtils.isEmpty(this.g)) {
                return AppLovinAdType.INCENTIVIZED.equals(c()) ? ((Boolean) this.c.a(com.applovin.impl.sdk.b.b.aW)).booleanValue() : a(com.applovin.impl.sdk.b.b.aV, b());
            }
            return true;
        } catch (Throwable th) {
            this.d.b("AdZone", "Unable to safely test preload merge capability", th);
            return false;
        }
    }

    public String a() {
        return this.f;
    }

    void a(com.applovin.impl.sdk.h hVar) {
        this.c = hVar;
        this.d = hVar.w();
    }

    public AppLovinAdSize b() {
        if (this.h == null && com.applovin.impl.sdk.utils.g.a(this.e, "ad_size")) {
            this.h = AppLovinAdSize.fromString(com.applovin.impl.sdk.utils.g.a(this.e, "ad_size", (String) null, this.c));
        }
        return this.h;
    }

    public AppLovinAdType c() {
        if (this.i == null && com.applovin.impl.sdk.utils.g.a(this.e, AppEventsConstants.EVENT_PARAM_AD_TYPE)) {
            this.i = new AppLovinAdType(com.applovin.impl.sdk.utils.g.a(this.e, AppEventsConstants.EVENT_PARAM_AD_TYPE, (String) null, this.c));
        }
        return this.i;
    }

    public boolean d() {
        return AppLovinAdSize.NATIVE.equals(b()) && AppLovinAdType.NATIVE.equals(c());
    }

    public int e() {
        if (com.applovin.impl.sdk.utils.g.a(this.e, "capacity")) {
            return com.applovin.impl.sdk.utils.g.b(this.e, "capacity", 0, this.c);
        }
        if (TextUtils.isEmpty(this.g)) {
            return ((Integer) this.c.a(a("preload_capacity_", com.applovin.impl.sdk.b.b.aZ))).intValue();
        }
        return d() ? ((Integer) this.c.a(com.applovin.impl.sdk.b.b.bl)).intValue() : ((Integer) this.c.a(com.applovin.impl.sdk.b.b.bk)).intValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f.equalsIgnoreCase(((d) obj).f);
    }

    public int f() {
        if (com.applovin.impl.sdk.utils.g.a(this.e, "extended_capacity")) {
            return com.applovin.impl.sdk.utils.g.b(this.e, "extended_capacity", 0, this.c);
        }
        if (TextUtils.isEmpty(this.g)) {
            return ((Integer) this.c.a(a("extended_preload_capacity_", com.applovin.impl.sdk.b.b.bf))).intValue();
        }
        if (d()) {
            return 0;
        }
        return ((Integer) this.c.a(com.applovin.impl.sdk.b.b.bm)).intValue();
    }

    public int g() {
        return com.applovin.impl.sdk.utils.g.b(this.e, "preload_count", 0, this.c);
    }

    public boolean h() {
        if (!((Boolean) this.c.a(com.applovin.impl.sdk.b.b.aU)).booleanValue() || !k()) {
            return false;
        }
        if (TextUtils.isEmpty(this.g)) {
            com.applovin.impl.sdk.b.b bVarA = a("preload_merge_init_tasks_", (com.applovin.impl.sdk.b.b) null);
            return bVarA != null && ((Boolean) this.c.a(bVarA)).booleanValue() && e() > 0;
        }
        if (this.e != null && g() == 0) {
            return false;
        }
        String upperCase = ((String) this.c.a(com.applovin.impl.sdk.b.b.aV)).toUpperCase(Locale.ENGLISH);
        if (upperCase.contains(AppLovinAdSize.INTERSTITIAL.getLabel()) || upperCase.contains(AppLovinAdSize.BANNER.getLabel()) || upperCase.contains(AppLovinAdSize.MREC.getLabel()) || upperCase.contains(AppLovinAdSize.LEADER.getLabel())) {
            return ((Boolean) this.c.a(com.applovin.impl.sdk.b.b.bt)).booleanValue();
        }
        return this.c.R().a(this) && g() > 0 && ((Boolean) this.c.a(com.applovin.impl.sdk.b.b.dT)).booleanValue();
    }

    public int hashCode() {
        return this.f.hashCode();
    }

    public boolean i() {
        if (com.applovin.impl.sdk.utils.g.a(this.e, "wrapped_ads_enabled")) {
            return com.applovin.impl.sdk.utils.g.a(this.e, "wrapped_ads_enabled", (Boolean) false, this.c).booleanValue();
        }
        return b() != null ? this.c.b(com.applovin.impl.sdk.b.b.ch).contains(b().getLabel()) : ((Boolean) this.c.a(com.applovin.impl.sdk.b.b.cg)).booleanValue();
    }

    public boolean j() {
        return b(this.c).contains(this);
    }

    public String toString() {
        return "AdZone{identifier=" + this.f + ", zoneObject=" + this.e + '}';
    }
}
