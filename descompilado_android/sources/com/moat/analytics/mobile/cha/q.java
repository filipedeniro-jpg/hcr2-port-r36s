package com.moat.analytics.mobile.cha;

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
final class q extends d implements NativeDisplayTracker {

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private final Map<String, String> f414;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private final Set<NativeDisplayTracker.MoatUserInteractionType> f415;

    @Override // com.moat.analytics.mobile.cha.d
    /* JADX INFO: renamed from: ˋ */
    final String mo303() {
        return "NativeDisplayTracker";
    }

    q(View view, Map<String, String> map) {
        super(view, true, false);
        this.f415 = new HashSet();
        a.m273(3, "NativeDisplayTracker", this, "Initializing.");
        this.f414 = map;
        if (view == null) {
            String str = "NativeDisplayTracker initialization not successful, Target view is null";
            a.m273(3, "NativeDisplayTracker", this, str);
            a.m270("[ERROR] ", str);
            this.f309 = new o("Target view is null");
            return;
        }
        if (map == null || map.isEmpty()) {
            String str2 = "NativeDisplayTracker initialization not successful, AdIds is null or empty";
            a.m273(3, "NativeDisplayTracker", this, str2);
            a.m270("[ERROR] ", str2);
            this.f309 = new o("AdIds is null or empty");
            return;
        }
        a aVar = ((f) f.getInstance()).f320;
        if (aVar == null) {
            String str3 = "NativeDisplayTracker initialization not successful, prepareNativeDisplayTracking was not called successfully";
            a.m273(3, "NativeDisplayTracker", this, str3);
            a.m270("[ERROR] ", str3);
            this.f309 = new o("prepareNativeDisplayTracking was not called successfully");
            return;
        }
        this.f306 = aVar.f275;
        try {
            super.m306(aVar.f273);
            if (this.f306 != null) {
                this.f306.m363(m398());
            }
            a.m270("[SUCCESS] ", "NativeDisplayTracker created for " + m299() + ", with adIds:" + map.toString());
        } catch (o e) {
            this.f309 = e;
        }
    }

    @Override // com.moat.analytics.mobile.cha.NativeDisplayTracker
    public final void reportUserInteractionEvent(NativeDisplayTracker.MoatUserInteractionType moatUserInteractionType) {
        try {
            a.m273(3, "NativeDisplayTracker", this, "reportUserInteractionEvent:" + moatUserInteractionType.name());
            if (this.f415.contains(moatUserInteractionType)) {
                return;
            }
            this.f415.add(moatUserInteractionType);
            JSONObject jSONObject = new JSONObject();
            jSONObject.accumulate("adKey", this.f305);
            jSONObject.accumulate("event", moatUserInteractionType.name().toLowerCase());
            if (this.f306 != null) {
                this.f306.m364(jSONObject.toString());
            }
        } catch (JSONException e) {
            a.m274("NativeDisplayTracker", this, "Got JSON exception");
            o.m389(e);
        } catch (Exception e2) {
            o.m389(e2);
        }
    }

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private String m398() {
        try {
            Map<String, String> map = this.f414;
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
            String string = new JSONObject(linkedHashMap).toString();
            a.m273(3, "NativeDisplayTracker", this, "Parsed ad ids = " + string);
            return "{\"adIds\":" + string + ", \"adKey\":\"" + this.f305 + "\", \"adSize\":" + m399() + "}";
        } catch (Exception e) {
            o.m389(e);
            return "";
        }
    }

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private String m399() {
        try {
            Rect rectM436 = u.m436(super.m300());
            int iWidth = rectM436.width();
            int iHeight = rectM436.height();
            HashMap map = new HashMap();
            map.put("width", Integer.toString(iWidth));
            map.put("height", Integer.toString(iHeight));
            return new JSONObject(map).toString();
        } catch (Exception e) {
            o.m389(e);
            return null;
        }
    }
}
