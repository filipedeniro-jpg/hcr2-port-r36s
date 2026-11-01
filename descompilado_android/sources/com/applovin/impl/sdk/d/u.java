package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.NativeAdImpl;
import com.applovin.mediation.AppLovinNativeAdapter;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.facebook.share.internal.ShareConstants;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.nend.android.NendAdNativeMediaView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class u extends a {
    private final AppLovinNativeAdLoadListener a;
    private final JSONObject c;

    u(JSONObject jSONObject, com.applovin.impl.sdk.h hVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskRenderNativeAd", hVar);
        this.a = appLovinNativeAdLoadListener;
        this.c = jSONObject;
    }

    private String a(String str, Map<String, String> map, String str2) {
        String str3 = map.get(str);
        if (str3 != null) {
            return str3.replace("{CLCODE}", str2);
        }
        return null;
    }

    private String a(Map<String, String> map, String str, String str2) {
        String str3 = map.get(NendAdNativeMediaView.RESULT_CLICK_URL);
        if (str2 == null) {
            str2 = "";
        }
        return str3.replace("{CLCODE}", str).replace("{EVENT_ID}", str2);
    }

    private void a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject;
        JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("native_ads");
        JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject("native_settings");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
            c("No ads were returned from the server");
            this.a.onNativeAdsFailedToLoad(204);
            return;
        }
        List listB = com.applovin.impl.sdk.utils.g.b(jSONArrayOptJSONArray);
        ArrayList arrayList = new ArrayList(listB.size());
        Map<String, String> mapA = jSONObjectOptJSONObject != null ? com.applovin.impl.sdk.utils.g.a(jSONObjectOptJSONObject) : new HashMap<>(0);
        Iterator it = listB.iterator();
        while (it.hasNext()) {
            Map map = (Map) it.next();
            String str = (String) map.get("clcode");
            String strA = com.applovin.impl.sdk.utils.g.a(jSONObject2, "zone_id", (String) null, this.b);
            String str2 = (String) map.get("event_id");
            com.applovin.impl.sdk.ad.d dVarB = com.applovin.impl.sdk.ad.d.b(strA, this.b);
            String strA2 = a("simp_url", mapA, str);
            String strA3 = a(mapA, str, str2);
            List<com.applovin.impl.sdk.c.a> listA = com.applovin.impl.sdk.utils.n.a("simp_urls", jSONObjectOptJSONObject, str, strA2, this.b);
            Iterator it2 = it;
            JSONObject jSONObject3 = jSONObjectOptJSONObject;
            List<com.applovin.impl.sdk.c.a> listA2 = com.applovin.impl.sdk.utils.n.a("click_tracking_urls", jSONObjectOptJSONObject, str, str2, com.applovin.impl.sdk.utils.g.a(jSONObjectOptJSONObject, "should_post_click_url", (Boolean) true, this.b).booleanValue() ? strA3 : null, this.b);
            if (listA.size() == 0) {
                throw new IllegalArgumentException("No impression URL available");
            }
            if (listA2.size() == 0) {
                throw new IllegalArgumentException("No click tracking URL available");
            }
            String str3 = (String) map.get("resource_cache_prefix");
            NativeAdImpl nativeAdImplA = new NativeAdImpl.a().a(dVarB).e(strA).f((String) map.get("title")).g((String) map.get("description")).h((String) map.get(ShareConstants.FEED_CAPTION_PARAM)).q((String) map.get(InMobiNetworkValues.CTA)).a((String) map.get("icon_url")).b((String) map.get(MessengerShareContentUtility.IMAGE_URL)).d((String) map.get("video_url")).c((String) map.get("star_rating_url")).i((String) map.get("icon_url")).j((String) map.get(MessengerShareContentUtility.IMAGE_URL)).k((String) map.get("video_url")).a(Float.parseFloat((String) map.get("star_rating"))).p(str).l(strA3).m(strA2).n(a("video_start_url", mapA, str)).o(a("video_end_url", mapA, str)).a(listA).b(listA2).a(Long.parseLong((String) map.get(AppLovinNativeAdapter.KEY_EXTRA_AD_ID))).c(str3 != null ? com.applovin.impl.sdk.utils.d.a(str3) : this.b.b(com.applovin.impl.sdk.b.b.bG)).a(this.b).a();
            arrayList.add(nativeAdImplA);
            a("Prepared native ad: " + nativeAdImplA.getAdId());
            jSONObject2 = jSONObject;
            jSONObjectOptJSONObject = jSONObject3;
            it = it2;
        }
        AppLovinNativeAdLoadListener appLovinNativeAdLoadListener = this.a;
        if (appLovinNativeAdLoadListener != null) {
            appLovinNativeAdLoadListener.onNativeAdsLoaded(arrayList);
        }
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.u;
    }

    void a(int i) {
        try {
            if (this.a != null) {
                this.a.onNativeAdsFailedToLoad(i);
            }
        } catch (Exception e) {
            a("Unable to notify listener about failure.", e);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.c != null && this.c.length() != 0) {
                a(this.c);
                return;
            }
            a(AppLovinErrorCodes.UNABLE_TO_PREPARE_NATIVE_AD);
        } catch (Exception e) {
            a("Unable to render native ad.", e);
            a(AppLovinErrorCodes.UNABLE_TO_PREPARE_NATIVE_AD);
            this.b.H().a(a());
        }
    }
}
