package com.moat.analytics.mobile.inm;

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
    private final Map<String, String> g;
    private final Set<NativeDisplayTracker.MoatUserInteractionType> h;

    t(View view, Map<String, String> map) {
        super(view, true, false);
        this.h = new HashSet();
        p.a(3, "NativeDisplayTracker", this, "Initializing.");
        this.g = map;
        if (view == null) {
            p.a("[ERROR] ", 3, "NativeDisplayTracker", this, "NativeDisplayTracker initialization not successful, Target view is null");
            this.a = new m("Target view is null");
            return;
        }
        if (map == null || map.isEmpty()) {
            p.a("[ERROR] ", 3, "NativeDisplayTracker", this, "NativeDisplayTracker initialization not successful, AdIds is null or empty");
            this.a = new m("AdIds is null or empty");
            return;
        }
        g gVar = ((k) k.getInstance()).d;
        if (gVar == null) {
            p.a("[ERROR] ", 3, "NativeDisplayTracker", this, "NativeDisplayTracker initialization not successful, prepareNativeDisplayTracking was not called successfully");
            this.a = new m("prepareNativeDisplayTracking was not called successfully");
            return;
        }
        super.a(gVar.b);
        try {
            super.a(gVar.a);
            i();
            p.a("[SUCCESS] ", a() + " created for " + g() + ", with adIds:" + map.toString());
        } catch (m e) {
            this.a = e;
        }
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

    private void i() {
        if (this.c != null) {
            this.c.a(j());
        }
    }

    private String j() {
        try {
            String strA = a(this.g);
            p.a(3, "NativeDisplayTracker", this, "Parsed ad ids = " + strA);
            return "{\"adIds\":" + strA + ", \"adKey\":\"" + this.e + "\", \"adSize\":" + k() + "}";
        } catch (Exception e) {
            m.a(e);
            return "";
        }
    }

    private String k() {
        try {
            Rect rectA = z.a(super.f());
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

    @Override // com.moat.analytics.mobile.inm.b
    String a() {
        return "NativeDisplayTracker";
    }

    @Override // com.moat.analytics.mobile.inm.NativeDisplayTracker
    public void reportUserInteractionEvent(NativeDisplayTracker.MoatUserInteractionType moatUserInteractionType) {
        try {
            p.a(3, "NativeDisplayTracker", this, "reportUserInteractionEvent:" + moatUserInteractionType.name());
            if (this.h.contains(moatUserInteractionType)) {
                return;
            }
            this.h.add(moatUserInteractionType);
            JSONObject jSONObject = new JSONObject();
            jSONObject.accumulate("adKey", this.e);
            jSONObject.accumulate("event", moatUserInteractionType.name().toLowerCase());
            if (this.c != null) {
                this.c.b(jSONObject.toString());
            }
        } catch (JSONException e) {
            p.b(2, "NativeDisplayTracker", this, "Got JSON exception");
            m.a(e);
        } catch (Exception e2) {
            m.a(e2);
        }
    }
}
