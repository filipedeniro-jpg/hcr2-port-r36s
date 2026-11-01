package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1W, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C1W extends C1M {
    private C1O B;
    private String C;
    private List<C01601n> D;
    private String E;
    private String F;
    private C1X G;

    private C1W(C1X translations, Map<String, String> map, C1O adConfig, List<C01601n> list) {
        String str;
        String str2;
        String str3;
        this.G = translations;
        this.B = adConfig;
        this.D = list;
        if (map.containsKey("background_color")) {
            str = map.get("background_color");
        } else {
            str = "#FF23272F";
        }
        this.C = str;
        if (map.containsKey("timer_text_color")) {
            str2 = map.get("timer_text_color");
        } else {
            str2 = "#FFFFFF";
        }
        this.E = str2;
        if (map.containsKey("title_text_color")) {
            str3 = map.get("title_text_color");
        } else {
            str3 = "#FFFFFF";
        }
        this.F = str3;
    }

    @Override // com.facebook.ads.redexgen.X.C1M
    @Nullable
    public final String A() {
        if (this.D.isEmpty()) {
            return null;
        }
        return this.D.get(0).A();
    }

    @Override // com.facebook.ads.redexgen.X.C1M
    public final int B() {
        return 2;
    }

    public static C1W C(JSONObject dataObject) {
        C1X c1xB = C1X.B(dataObject.optJSONObject("translations"));
        JSONObject jSONObjectOptJSONObject = dataObject.optJSONObject(TtmlNode.TAG_LAYOUT);
        HashMap map = new HashMap();
        if (jSONObjectOptJSONObject != null) {
            D(jSONObjectOptJSONObject, map, "background_color");
            D(jSONObjectOptJSONObject, map, "timer_text_color");
            D(jSONObjectOptJSONObject, map, "title_text_color");
        }
        JSONObject layoutObject = dataObject.optJSONObject("ad_config");
        C1O c1oB = C1O.B(layoutObject);
        JSONArray jSONArrayOptJSONArray = dataObject.optJSONArray("choosable_ads");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                C01601n c01601nD = C01601n.D(jSONArrayOptJSONArray.optJSONObject(i), dataObject.optInt("an_logo_type", 0));
                c01601nD.T(true);
                arrayList.add(c01601nD);
            }
        }
        return new C1W(c1xB, map, c1oB, arrayList);
    }

    @Override // com.facebook.ads.redexgen.X.C1M
    public final int D() {
        int duration = 0;
        Iterator<C01601n> it = this.D.iterator();
        while (it.hasNext()) {
            duration = Math.max(duration, it.next().K().G());
        }
        return duration;
    }

    private static void D(JSONObject layoutObject, HashMap<String, String> map, String colorKey) {
        String colorValue = layoutObject.optString(colorKey);
        if (!TextUtils.isEmpty(colorValue)) {
            map.put(colorKey, colorValue);
        }
    }

    @Override // com.facebook.ads.redexgen.X.C1M
    public final void G(String extraHints) {
        super.G(extraHints);
        Iterator<C01601n> it = this.D.iterator();
        while (it.hasNext()) {
            it.next().G(extraHints);
        }
    }

    public final C1O J() {
        return this.B;
    }

    public final List<C01601n> K() {
        return this.D;
    }

    public final String L() {
        return this.C;
    }

    public final String M() {
        return this.E;
    }

    public final String N() {
        return this.F;
    }

    public final C1X O() {
        return this.G;
    }
}
