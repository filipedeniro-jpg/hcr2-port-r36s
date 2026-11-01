package net.nend.android.internal.c.c;

import android.content.Context;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.ArrayList;
import net.nend.android.NendAdNativeMediaView;
import net.nend.android.internal.utilities.k;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NendAdIconResponseParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c extends net.nend.android.internal.c.c<b> {
    private final int b;

    public c(Context context, int i) {
        super(context);
        this.b = i;
    }

    /* JADX INFO: renamed from: net.nend.android.internal.c.c.c$1, reason: invalid class name */
    /* JADX INFO: compiled from: NendAdIconResponseParser.java */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[net.nend.android.internal.c.c.a.values().length];

        static {
            try {
                a[net.nend.android.internal.c.c.a.ICON_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[net.nend.android.internal.c.c.a.ICON_APP_TARGETING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // net.nend.android.internal.c.c
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public b a(net.nend.android.internal.c.c.a aVar, JSONObject jSONObject) throws net.nend.android.internal.a.b {
        int i = AnonymousClass1.a[aVar.ordinal()];
        if (i == 1) {
            return a(jSONObject);
        }
        if (i == 2) {
            return b(jSONObject);
        }
        throw new net.nend.android.internal.a.b(k.ERR_INVALID_RESPONSE_TYPE);
    }

    private b a(JSONObject jSONObject) throws JSONException, net.nend.android.internal.a.b {
        ArrayList<net.nend.android.internal.a> arrayList = new ArrayList<>();
        b.a aVar = new b.a();
        JSONArray jSONArray = jSONObject.getJSONArray("default_ads");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            net.nend.android.internal.c.a.b.a aVarF = new net.nend.android.internal.c.a.b.a().a(net.nend.android.internal.a.EnumC0106a.ADVIEW).e(jSONObject2.getString("icon_id")).a(jSONObject2.getString(MessengerShareContentUtility.IMAGE_URL)).b(jSONObject2.getString(NendAdNativeMediaView.RESULT_CLICK_URL)).f(jSONObject2.getString("animation_gif_flg"));
            if (!jSONObject2.isNull("icon_text")) {
                aVarF.d(jSONObject2.getString("icon_text"));
            }
            arrayList.add(aVarF.a());
            if (arrayList.size() >= this.b) {
                break;
            }
        }
        if (arrayList.size() == 0) {
            throw new net.nend.android.internal.a.b(k.ERR_OUT_OF_STOCK);
        }
        aVar.a(arrayList);
        if (!jSONObject.isNull("status_code")) {
            aVar.b(jSONObject.getInt("status_code"));
        }
        if (!jSONObject.isNull("message")) {
            aVar.a(jSONObject.getString("message"));
        }
        if (!jSONObject.isNull("reload")) {
            aVar.a(jSONObject.getInt("reload"));
        }
        if (!jSONObject.isNull("impression_count_url")) {
            aVar.b(jSONObject.getString("impression_count_url"));
        }
        aVar.a(net.nend.android.internal.a.EnumC0106a.ADVIEW);
        return aVar.a();
    }

    private b b(JSONObject jSONObject) throws JSONException, net.nend.android.internal.a.b {
        JSONArray jSONArray;
        ArrayList<net.nend.android.internal.a> arrayList = new ArrayList<>();
        b.a aVar = new b.a();
        JSONArray jSONArray2 = jSONObject.getJSONArray("targeting_ads");
        int length = jSONArray2.length();
        int i = 0;
        while (i < length) {
            JSONObject jSONObject2 = jSONArray2.getJSONObject(i);
            JSONArray jSONArray3 = jSONObject2.getJSONArray("conditions");
            int length2 = jSONArray3.length();
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    jSONArray = jSONArray2;
                    break;
                }
                jSONArray = jSONArray2;
                if (a(jSONArray3.getJSONArray(i2))) {
                    net.nend.android.internal.c.a.b.a aVarF = new net.nend.android.internal.c.a.b.a().a(net.nend.android.internal.a.EnumC0106a.ADVIEW).e(jSONObject2.getString("icon_id")).a(jSONObject2.getString(MessengerShareContentUtility.IMAGE_URL)).b(jSONObject2.getString(NendAdNativeMediaView.RESULT_CLICK_URL)).f(jSONObject2.getString("animation_gif_flg"));
                    if (!jSONObject2.isNull("icon_text")) {
                        aVarF.d(jSONObject2.getString("icon_text"));
                    }
                    arrayList.add(aVarF.a());
                    break;
                }
                i2++;
                jSONArray2 = jSONArray;
            }
            if (arrayList.size() >= this.b) {
                break;
            }
            i++;
            jSONArray2 = jSONArray;
        }
        if (arrayList.size() < this.b && !jSONObject.isNull("default_ads")) {
            JSONArray jSONArray4 = jSONObject.getJSONArray("default_ads");
            for (int i3 = 0; i3 < jSONArray4.length(); i3++) {
                JSONObject jSONObject3 = jSONArray4.getJSONObject(i3);
                net.nend.android.internal.c.a.b.a aVarF2 = new net.nend.android.internal.c.a.b.a().a(net.nend.android.internal.a.EnumC0106a.ADVIEW).e(jSONObject3.getString("icon_id")).a(jSONObject3.getString(MessengerShareContentUtility.IMAGE_URL)).b(jSONObject3.getString(NendAdNativeMediaView.RESULT_CLICK_URL)).f(jSONObject3.getString("animation_gif_flg"));
                if (!jSONObject3.isNull("icon_text")) {
                    aVarF2.d(jSONObject3.getString("icon_text"));
                }
                arrayList.add(aVarF2.a());
                if (arrayList.size() >= this.b) {
                    break;
                }
            }
        }
        if (arrayList.size() == 0) {
            throw new net.nend.android.internal.a.b(k.ERR_OUT_OF_STOCK);
        }
        aVar.a(arrayList);
        if (!jSONObject.isNull("status_code")) {
            aVar.b(jSONObject.getInt("status_code"));
        }
        if (!jSONObject.isNull("message")) {
            aVar.a(jSONObject.getString("message"));
        }
        if (!jSONObject.isNull("reload")) {
            aVar.a(jSONObject.getInt("reload"));
        }
        if (!jSONObject.isNull("impression_count_url")) {
            aVar.b(jSONObject.getString("impression_count_url"));
        }
        aVar.a(net.nend.android.internal.a.EnumC0106a.ADVIEW);
        return aVar.a();
    }
}
