package com.moat.analytics.mobile.vng;

import android.graphics.Rect;
import android.view.View;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class t extends b implements NativeDisplayTracker {
    private final Map<String, String> f;
    private final Set<NativeDisplayTracker.MoatUserInteractionType> g;

    t(View view, Map<String, String> map) {
        super(view, true, false);
        this.g = new HashSet();
        p.a(3, "NativeDisplayTracker", this, "Initializing.");
        this.f = map;
        g gVar = ((k) k.getInstance()).d;
        if (gVar != null) {
            super.a(gVar.b);
            super.a(gVar.a);
        }
        g();
        p.a("[SUCCESS] ", a() + " created for " + e() + ", with adIds:" + map.toString());
    }

    private static String a(Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < 8; i++) {
            String str = "moatClientLevel" + i;
            if (map.containsKey(str)) {
                linkedHashMap.put(str, map.get(str));
            }
        }
        for (int i2 = 0; i2 < 8; i2++) {
            String str2 = "moatClientSlicer" + i2;
            if (map.containsKey(str2)) {
                linkedHashMap.put(str2, map.get(str2));
            }
        }
        for (String str3 : map.keySet()) {
            if (!linkedHashMap.containsKey(str3)) {
                linkedHashMap.put(str3, map.get(str3));
            }
        }
        return new JSONObject(linkedHashMap).toString();
    }

    private void g() {
        if (this.a != null) {
            this.a.a(h());
        }
    }

    private String h() {
        try {
            String strA = a(this.f);
            p.a(3, "NativeDisplayTracker", this, "Parsed ad ids = " + strA);
            return "{\"adIds\":" + strA + ", \"adKey\":\"" + this.b + "\", \"adSize\":" + i() + "}";
        } catch (Exception e) {
            m.a(e);
            return "";
        }
    }

    private String i() {
        try {
            Rect rectA = z.a(super.d());
            int iWidth = rectA.width();
            int iHeight = rectA.height();
            HashMap map = new HashMap();
            map.put("width", Integer.toString(iWidth));
            map.put("height", Integer.toString(iHeight));
            return new JSONObject(map).toString();
        } catch (Exception e) {
            m.a(e);
            return null;
        }
    }

    @Override // com.moat.analytics.mobile.vng.b
    String a() {
        return "NativeDisplayTracker";
    }

    @Override // com.moat.analytics.mobile.vng.NativeDisplayTracker
    public void reportUserInteractionEvent(NativeDisplayTracker.MoatUserInteractionType moatUserInteractionType) {
        try {
            p.a(3, "NativeDisplayTracker", this, "reportUserInteractionEvent:" + moatUserInteractionType.name());
            if (this.g.contains(moatUserInteractionType)) {
                return;
            }
            this.g.add(moatUserInteractionType);
            JSONObject jSONObject = new JSONObject();
            jSONObject.accumulate("adKey", this.b);
            jSONObject.accumulate("event", moatUserInteractionType.name().toLowerCase());
            if (this.a != null) {
                this.a.b(jSONObject.toString());
            }
        } catch (JSONException e) {
            e = e;
            p.b(2, "NativeDisplayTracker", this, "Got JSON exception");
            m.a(e);
        } catch (Exception e2) {
            e = e2;
            m.a(e);
        }
    }
}
