package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.mediation.AppLovinNativeAdapter;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.facebook.appevents.AppEventsConstants;
import java.util.Arrays;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class AppLovinAdBase implements com.applovin.impl.sdk.ad.j, AppLovinAd {
    private final int a;
    protected final JSONObject adObject;
    protected final Object adObjectLock;
    private com.applovin.impl.sdk.ad.d b;
    private final long c;
    private com.applovin.impl.sdk.ad.h d;
    protected final JSONObject fullResponse;
    protected final Object fullResponseLock;
    protected final h sdk;
    protected final com.applovin.impl.sdk.ad.b source;

    protected AppLovinAdBase(JSONObject jSONObject, JSONObject jSONObject2, com.applovin.impl.sdk.ad.b bVar, h hVar) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("No ad object specified");
        }
        if (jSONObject2 == null) {
            throw new IllegalArgumentException("No response specified");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.adObject = jSONObject;
        this.fullResponse = jSONObject2;
        this.source = bVar;
        this.sdk = hVar;
        this.adObjectLock = new Object();
        this.fullResponseLock = new Object();
        this.c = System.currentTimeMillis();
        char[] charArray = jSONObject.toString().toCharArray();
        Arrays.sort(charArray);
        this.a = new String(charArray).hashCode();
    }

    public boolean equals(Object obj) {
        AppLovinAd appLovinAdB;
        if ((obj instanceof com.applovin.impl.sdk.ad.h) && (appLovinAdB = ((com.applovin.impl.sdk.ad.h) obj).b()) != null) {
            obj = appLovinAdB;
        }
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppLovinAdBase appLovinAdBase = (AppLovinAdBase) obj;
        com.applovin.impl.sdk.ad.d dVar = this.b;
        if (dVar == null ? appLovinAdBase.b == null : dVar.equals(appLovinAdBase.b)) {
            return this.source == appLovinAdBase.source && this.a == appLovinAdBase.a;
        }
        return false;
    }

    @Override // com.applovin.sdk.AppLovinAd
    public long getAdIdNumber() {
        return getLongFromAdObject(AppLovinNativeAdapter.KEY_EXTRA_AD_ID, -1L);
    }

    @Override // com.applovin.sdk.AppLovinAd
    public String getAdValue(String str) {
        JSONObject jsonObjectFromAdObject;
        if (TextUtils.isEmpty(str) || (jsonObjectFromAdObject = getJsonObjectFromAdObject("ad_values", null)) == null || jsonObjectFromAdObject.length() <= 0) {
            return null;
        }
        return com.applovin.impl.sdk.utils.g.a(jsonObjectFromAdObject, str, (String) null, this.sdk);
    }

    public com.applovin.impl.sdk.ad.d getAdZone() {
        com.applovin.impl.sdk.ad.d dVar = this.b;
        if (dVar != null) {
            return dVar;
        }
        this.b = com.applovin.impl.sdk.ad.d.a(getSize(), getType(), getStringFromFullResponse("zone_id", null), this.sdk);
        return this.b;
    }

    protected boolean getBooleanFromAdObject(String str, Boolean bool) {
        boolean zBooleanValue;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fj)).booleanValue()) {
            return com.applovin.impl.sdk.utils.g.a(this.adObject, str, bool, this.sdk).booleanValue();
        }
        synchronized (this.adObjectLock) {
            zBooleanValue = com.applovin.impl.sdk.utils.g.a(this.adObject, str, bool, this.sdk).booleanValue();
        }
        return zBooleanValue;
    }

    protected boolean getBooleanFromFullResponse(String str, boolean z) {
        boolean zBooleanValue;
        synchronized (this.fullResponseLock) {
            zBooleanValue = com.applovin.impl.sdk.utils.g.a(this.fullResponse, str, Boolean.valueOf(z), this.sdk).booleanValue();
        }
        return zBooleanValue;
    }

    public String getClCode() {
        String stringFromAdObject = getStringFromAdObject("clcode", "");
        return com.applovin.impl.sdk.utils.k.b(stringFromAdObject) ? stringFromAdObject : getStringFromFullResponse("clcode", "");
    }

    public long getCreatedAtMillis() {
        return this.c;
    }

    public com.applovin.impl.sdk.ad.h getDummyAd() {
        return this.d;
    }

    public long getFetchLatencyMillis() {
        return getLongFromFullResponse("ad_fetch_latency_millis", -1L);
    }

    public long getFetchResponseSize() {
        return getLongFromFullResponse("ad_fetch_response_size", -1L);
    }

    protected float getFloatFromAdObject(String str, float f) {
        float fA;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fj)).booleanValue()) {
            return com.applovin.impl.sdk.utils.g.a(this.adObject, str, f, this.sdk);
        }
        synchronized (this.adObjectLock) {
            fA = com.applovin.impl.sdk.utils.g.a(this.adObject, str, f, this.sdk);
        }
        return fA;
    }

    protected float getFloatFromFullResponse(String str, float f) {
        float fA;
        synchronized (this.fullResponseLock) {
            fA = com.applovin.impl.sdk.utils.g.a(this.fullResponse, str, f, this.sdk);
        }
        return fA;
    }

    protected int getIntFromAdObject(String str, int i) {
        int iB;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fj)).booleanValue()) {
            return com.applovin.impl.sdk.utils.g.b(this.adObject, str, i, this.sdk);
        }
        synchronized (this.adObjectLock) {
            iB = com.applovin.impl.sdk.utils.g.b(this.adObject, str, i, this.sdk);
        }
        return iB;
    }

    protected int getIntFromFullResponse(String str, int i) {
        int iB;
        synchronized (this.fullResponseLock) {
            iB = com.applovin.impl.sdk.utils.g.b(this.fullResponse, str, i, this.sdk);
        }
        return iB;
    }

    protected JSONObject getJsonObjectFromAdObject(String str, JSONObject jSONObject) {
        JSONObject jSONObjectA;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fj)).booleanValue()) {
            return com.applovin.impl.sdk.utils.g.a(this.adObject, str, jSONObject, this.sdk);
        }
        synchronized (this.adObjectLock) {
            jSONObjectA = com.applovin.impl.sdk.utils.g.a(this.adObject, str, jSONObject, this.sdk);
        }
        return jSONObjectA;
    }

    protected JSONObject getJsonObjectFromFullResponse(String str, JSONObject jSONObject) {
        JSONObject jSONObjectA;
        synchronized (this.fullResponseLock) {
            jSONObjectA = com.applovin.impl.sdk.utils.g.a(this.fullResponse, str, jSONObject, this.sdk);
        }
        return jSONObjectA;
    }

    protected long getLongFromAdObject(String str, long j) {
        long jA;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fj)).booleanValue()) {
            return com.applovin.impl.sdk.utils.g.a(this.adObject, str, j, this.sdk);
        }
        synchronized (this.adObjectLock) {
            jA = com.applovin.impl.sdk.utils.g.a(this.adObject, str, j, this.sdk);
        }
        return jA;
    }

    protected long getLongFromFullResponse(String str, long j) {
        long jA;
        synchronized (this.fullResponseLock) {
            jA = com.applovin.impl.sdk.utils.g.a(this.fullResponse, str, j, this.sdk);
        }
        return jA;
    }

    public String getPrimaryKey() {
        return getStringFromAdObject("pk", "NA");
    }

    public h getSdk() {
        return this.sdk;
    }

    public String getSecondaryKey1() {
        return getStringFromAdObject("sk1", null);
    }

    public String getSecondaryKey2() {
        return getStringFromAdObject("sk2", null);
    }

    @Override // com.applovin.sdk.AppLovinAd
    public AppLovinAdSize getSize() {
        return AppLovinAdSize.fromString(getStringFromFullResponse("ad_size", null));
    }

    public com.applovin.impl.sdk.ad.b getSource() {
        return this.source;
    }

    protected String getStringFromAdObject(String str, String str2) {
        String strA;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fj)).booleanValue()) {
            return com.applovin.impl.sdk.utils.g.a(this.adObject, str, str2, this.sdk);
        }
        synchronized (this.adObjectLock) {
            strA = com.applovin.impl.sdk.utils.g.a(this.adObject, str, str2, this.sdk);
        }
        return strA;
    }

    protected String getStringFromFullResponse(String str, String str2) {
        String strA;
        synchronized (this.fullResponseLock) {
            strA = com.applovin.impl.sdk.utils.g.a(this.fullResponse, str, str2, this.sdk);
        }
        return strA;
    }

    @Override // com.applovin.sdk.AppLovinAd
    public AppLovinAdType getType() {
        return AppLovinAdType.fromString(getStringFromFullResponse(AppEventsConstants.EVENT_PARAM_AD_TYPE, null));
    }

    @Override // com.applovin.sdk.AppLovinAd
    public String getZoneId() {
        if (getAdZone().j()) {
            return null;
        }
        return getStringFromFullResponse("zone_id", null);
    }

    public boolean hasShown() {
        return getBooleanFromAdObject("shown", false);
    }

    public boolean hasVideoUrl() {
        this.sdk.w().d("AppLovinAdBase", "Attempting to invoke hasVideoUrl() from base ad class");
        return false;
    }

    public int hashCode() {
        return this.a;
    }

    @Override // com.applovin.sdk.AppLovinAd
    public boolean isVideoAd() {
        return this.adObject.has("is_video_ad") ? getBooleanFromAdObject("is_video_ad", false) : hasVideoUrl();
    }

    public void setDummyAd(com.applovin.impl.sdk.ad.h hVar) {
        this.d = hVar;
    }

    public void setHasShown(boolean z) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put("shown", z);
            }
        } catch (Throwable unused) {
        }
    }

    public boolean shouldCancelHtmlCachingIfShown() {
        return getBooleanFromAdObject("chcis", false);
    }

    public String toString() {
        return "AppLovinAd{adIdNumber" + getAdIdNumber() + ", source=" + getSource() + ", zoneId='" + getZoneId() + "'}";
    }
}
