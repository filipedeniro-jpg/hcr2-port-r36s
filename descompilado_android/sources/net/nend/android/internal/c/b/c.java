package net.nend.android.internal.c.b;

import android.content.Context;
import com.facebook.share.internal.MessengerShareContentUtility;
import net.nend.android.NendAdNative;
import net.nend.android.NendAdNativeMediaView;
import net.nend.android.internal.utilities.k;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NendAdNativeResponseParser.java */
/* JADX INFO: loaded from: classes2.dex */
final class c extends net.nend.android.internal.c.c<NendAdNative> {
    public c(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.nend.android.internal.c.c
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public NendAdNative a(int i) {
        if (k.ERR_EXCESSIVE_AD_CALLS.a() == i) {
            return new NendAdNative.a().a();
        }
        return null;
    }

    /* JADX INFO: renamed from: net.nend.android.internal.c.b.c$1, reason: invalid class name */
    /* JADX INFO: compiled from: NendAdNativeResponseParser.java */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[net.nend.android.internal.c.c.a.values().length];

        static {
            try {
                a[net.nend.android.internal.c.c.a.NATIVE_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[net.nend.android.internal.c.c.a.NATIVE_APP_TARGETING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // net.nend.android.internal.c.c
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public NendAdNative a(net.nend.android.internal.c.c.a aVar, JSONObject jSONObject) throws net.nend.android.internal.a.b {
        int i = AnonymousClass1.a[aVar.ordinal()];
        if (i == 1) {
            return a(jSONObject);
        }
        if (i == 2) {
            return b(jSONObject);
        }
        throw new net.nend.android.internal.a.b(k.ERR_INVALID_RESPONSE_TYPE);
    }

    private NendAdNative a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("default_ad");
        NendAdNative.a aVarJ = new NendAdNative.a().c(jSONObject2.getString(NendAdNativeMediaView.RESULT_CLICK_URL)).d(jSONObject2.getString("impression_count_url")).e(jSONObject2.getString("short_text")).f(jSONObject2.getString("long_text")).g(jSONObject2.getString("promotion_url")).h(jSONObject2.getString("promotion_name")).i(jSONObject2.getString("action_button_text")).j(jSONObject2.getString("campaign_id"));
        if (jSONObject2.has("ad_image")) {
            aVarJ.a(jSONObject2.getJSONObject("ad_image").getString(MessengerShareContentUtility.IMAGE_URL));
        }
        if (jSONObject2.has("logo_image")) {
            aVarJ.b(jSONObject2.getJSONObject("logo_image").getString(MessengerShareContentUtility.IMAGE_URL));
        }
        return aVarJ.a();
    }

    private NendAdNative b(JSONObject jSONObject) throws JSONException, net.nend.android.internal.a.b {
        JSONArray jSONArray = jSONObject.getJSONArray("targeting_ads");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            JSONArray jSONArray2 = jSONObject2.getJSONArray("conditions");
            int length2 = jSONArray2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                if (a(jSONArray2.getJSONArray(i2))) {
                    NendAdNative.a aVarJ = new NendAdNative.a().c(jSONObject2.getString(NendAdNativeMediaView.RESULT_CLICK_URL)).d(jSONObject2.getString("impression_count_url")).e(jSONObject2.getString("short_text")).f(jSONObject2.getString("long_text")).g(jSONObject2.getString("promotion_url")).h(jSONObject2.getString("promotion_name")).i(jSONObject2.getString("action_button_text")).j(jSONObject2.getString("campaign_id"));
                    if (jSONObject2.has("ad_image")) {
                        aVarJ.a(jSONObject2.getJSONObject("ad_image").getString(MessengerShareContentUtility.IMAGE_URL));
                    }
                    if (jSONObject2.has("logo_image")) {
                        aVarJ.b(jSONObject2.getJSONObject("logo_image").getString(MessengerShareContentUtility.IMAGE_URL));
                    }
                    return aVarJ.a();
                }
            }
        }
        if (jSONObject.isNull("default_ad")) {
            throw new net.nend.android.internal.a.b(k.ERR_OUT_OF_STOCK);
        }
        return a(jSONObject);
    }
}
