package net.nend.android.internal.c.d;

import android.content.Context;
import com.applovin.mediation.AppLovinNativeAdapter;
import net.nend.android.NendAdInterstitial;
import net.nend.android.internal.utilities.k;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NendAdInterstitialResponseParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c extends net.nend.android.internal.c.c<b> {
    public c(Context context) {
        super(context);
    }

    /* JADX INFO: renamed from: net.nend.android.internal.c.d.c$1, reason: invalid class name */
    /* JADX INFO: compiled from: NendAdInterstitialResponseParser.java */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[net.nend.android.internal.c.c.a.values().length];

        static {
            try {
                a[net.nend.android.internal.c.c.a.INTERSTITIAL_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[net.nend.android.internal.c.c.a.INTERSTITIAL_APP_TARGETING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[net.nend.android.internal.c.c.a.INTERSTITIAL_APP_TARGETING_ICON.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[net.nend.android.internal.c.c.a.INTERSTITIAL_APP_TARGETING_RECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // net.nend.android.internal.c.c
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public b a(net.nend.android.internal.c.c.a aVar, JSONObject jSONObject) {
        int i = AnonymousClass1.a[aVar.ordinal()];
        if (i == 1) {
            return a(jSONObject);
        }
        if (i == 2 || i == 3 || i == 4) {
            return b(jSONObject);
        }
        b.a aVar2 = new b.a();
        aVar2.a(net.nend.android.internal.a.EnumC0106a.ADVIEW);
        aVar2.a(NendAdInterstitial.NendAdInterstitialStatusCode.INVALID_RESPONSE_TYPE);
        return aVar2.a();
    }

    private b a(JSONObject jSONObject) throws JSONException {
        b.a aVar = new b.a();
        aVar.a(jSONObject.getJSONObject("ad").getJSONObject("default_ad").getString(AppLovinNativeAdapter.KEY_EXTRA_AD_ID));
        if (!jSONObject.isNull("status_code")) {
            aVar.a(jSONObject.getInt("status_code"));
        }
        if (!jSONObject.isNull("message")) {
            aVar.b(jSONObject.getString("message"));
        }
        if (!jSONObject.isNull("impression_count_url")) {
            aVar.c(jSONObject.getString("impression_count_url"));
        }
        if (!jSONObject.isNull("request_url")) {
            aVar.d(jSONObject.getString("request_url"));
        }
        if (!jSONObject.isNull("size")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("size");
            if (!jSONObject2.isNull("portrait") && !jSONObject2.isNull("landscape")) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("portrait");
                JSONObject jSONObject4 = jSONObject2.getJSONObject("landscape");
                if (jSONObject3 != null && !jSONObject3.isNull("width") && !jSONObject3.isNull("height")) {
                    aVar.e(jSONObject3.getInt("height"));
                    aVar.d(jSONObject3.getInt("width"));
                }
                if (jSONObject4 != null && !jSONObject4.isNull("width") && !jSONObject4.isNull("height")) {
                    aVar.g(jSONObject4.getInt("height"));
                    aVar.f(jSONObject4.getInt("width"));
                }
            }
        }
        if (!jSONObject.isNull("frequency")) {
            aVar.b(jSONObject.getInt("frequency"));
        }
        if (!jSONObject.isNull("ad_close_area")) {
            aVar.c(jSONObject.getInt("ad_close_area"));
        }
        aVar.a(net.nend.android.internal.a.EnumC0106a.ADVIEW);
        return aVar.a();
    }

    private b b(JSONObject jSONObject) throws JSONException, net.nend.android.internal.a.b {
        b.a aVar = new b.a();
        JSONObject jSONObject2 = jSONObject.getJSONObject("ad");
        JSONArray jSONArray = jSONObject2.getJSONArray("targeting_ads");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject3 = jSONArray.getJSONObject(i);
            JSONArray jSONArray2 = jSONObject3.getJSONArray("conditions");
            int length2 = jSONArray2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                if (a(jSONArray2.getJSONArray(i2))) {
                    aVar.a(jSONObject3.getString(AppLovinNativeAdapter.KEY_EXTRA_AD_ID));
                    if (!jSONObject.isNull("status_code")) {
                        aVar.a(jSONObject.getInt("status_code"));
                    }
                    if (!jSONObject.isNull("message")) {
                        aVar.b(jSONObject.getString("message"));
                    }
                    if (!jSONObject.isNull("impression_count_url")) {
                        aVar.c(jSONObject.getString("impression_count_url"));
                    }
                    if (!jSONObject.isNull("request_url")) {
                        aVar.d(jSONObject.getString("request_url"));
                    }
                    if (!jSONObject.isNull("size")) {
                        JSONObject jSONObject4 = jSONObject.getJSONObject("size");
                        if (!jSONObject4.isNull("portrait") && !jSONObject4.isNull("landscape")) {
                            JSONObject jSONObject5 = jSONObject4.getJSONObject("portrait");
                            JSONObject jSONObject6 = jSONObject4.getJSONObject("landscape");
                            if (jSONObject5 != null && !jSONObject5.isNull("width") && !jSONObject5.isNull("height")) {
                                aVar.e(jSONObject5.getInt("height"));
                                aVar.d(jSONObject5.getInt("width"));
                            }
                            if (jSONObject6 != null && !jSONObject6.isNull("width") && !jSONObject6.isNull("height")) {
                                aVar.g(jSONObject6.getInt("height"));
                                aVar.f(jSONObject6.getInt("width"));
                            }
                        }
                    }
                    if (!jSONObject.isNull("frequency")) {
                        aVar.b(jSONObject.getInt("frequency"));
                    }
                    if (!jSONObject.isNull("ad_close_area")) {
                        aVar.c(jSONObject.getInt("ad_close_area"));
                    }
                    aVar.a(net.nend.android.internal.a.EnumC0106a.ADVIEW);
                    return aVar.a();
                }
            }
        }
        if (jSONObject2.isNull("default_ad")) {
            throw new net.nend.android.internal.a.b(k.ERR_OUT_OF_STOCK);
        }
        return a(jSONObject);
    }
}
