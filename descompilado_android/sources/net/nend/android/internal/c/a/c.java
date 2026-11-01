package net.nend.android.internal.c.a;

import android.content.Context;
import com.facebook.share.internal.MessengerShareContentUtility;
import net.nend.android.NendAdNativeMediaView;
import net.nend.android.internal.utilities.k;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NendAdResponseParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c extends net.nend.android.internal.c.c<net.nend.android.internal.a> {
    public c(Context context) {
        super(context);
    }

    /* JADX INFO: renamed from: net.nend.android.internal.c.a.c$1, reason: invalid class name */
    /* JADX INFO: compiled from: NendAdResponseParser.java */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[net.nend.android.internal.c.c.a.values().length];

        static {
            try {
                a[net.nend.android.internal.c.c.a.BANNER_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[net.nend.android.internal.c.c.a.BANNER_WEB_VIEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[net.nend.android.internal.c.c.a.BANNER_APP_TARGETING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[net.nend.android.internal.c.c.a.BANNER_DYNAMIC_RETARGETING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // net.nend.android.internal.c.c
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public net.nend.android.internal.a a(net.nend.android.internal.c.c.a aVar, JSONObject jSONObject) throws net.nend.android.internal.a.b {
        int i = AnonymousClass1.a[aVar.ordinal()];
        if (i == 1) {
            return a(jSONObject);
        }
        if (i == 2) {
            return b(jSONObject);
        }
        if (i == 3) {
            return c(jSONObject);
        }
        if (i == 4) {
            return d(jSONObject);
        }
        throw new net.nend.android.internal.a.b(k.ERR_INVALID_RESPONSE_TYPE);
    }

    private net.nend.android.internal.a a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("default_ad");
        b.a aVarC = new b.a().a(net.nend.android.internal.a.EnumC0106a.ADVIEW).a(jSONObject2.getString(MessengerShareContentUtility.IMAGE_URL)).b(jSONObject2.getString(NendAdNativeMediaView.RESULT_CLICK_URL)).b(jSONObject.getInt("height")).c(jSONObject.getInt("width"));
        if (!jSONObject2.isNull("animation_gif_flg")) {
            aVarC.f(jSONObject2.getString("animation_gif_flg"));
        }
        if (!jSONObject.isNull("reload")) {
            aVarC.a(jSONObject.getInt("reload"));
        }
        return aVarC.a();
    }

    private net.nend.android.internal.a b(JSONObject jSONObject) {
        return new b.a().a(net.nend.android.internal.a.EnumC0106a.WEBVIEW).c(jSONObject.getString("web_view_url")).b(jSONObject.getInt("height")).c(jSONObject.getInt("width")).a();
    }

    private net.nend.android.internal.a c(JSONObject jSONObject) throws JSONException, net.nend.android.internal.a.b {
        JSONArray jSONArray = jSONObject.getJSONArray("targeting_ads");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            JSONArray jSONArray2 = jSONObject2.getJSONArray("conditions");
            int length2 = jSONArray2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                if (a(jSONArray2.getJSONArray(i2))) {
                    b.a aVarF = new b.a().a(net.nend.android.internal.a.EnumC0106a.ADVIEW).a(jSONObject2.getString(MessengerShareContentUtility.IMAGE_URL)).b(jSONObject2.getString(NendAdNativeMediaView.RESULT_CLICK_URL)).b(jSONObject.getInt("height")).c(jSONObject.getInt("width")).f(jSONObject2.getString("animation_gif_flg"));
                    if (!jSONObject.isNull("reload")) {
                        aVarF.a(jSONObject.getInt("reload"));
                    }
                    return aVarF.a();
                }
            }
        }
        if (jSONObject.isNull("default_ad")) {
            throw new net.nend.android.internal.a.b(k.ERR_OUT_OF_STOCK);
        }
        return a(jSONObject);
    }

    private net.nend.android.internal.a d(JSONObject jSONObject) {
        b.a aVarC = new b.a().a(net.nend.android.internal.a.EnumC0106a.DYNAMICRETARGETING).c(jSONObject.getString("web_view_url")).b(jSONObject.getInt("height")).c(jSONObject.getInt("width"));
        if (!jSONObject.isNull("reload")) {
            aVarC.a(jSONObject.getInt("reload"));
        }
        return aVarC.a();
    }
}
