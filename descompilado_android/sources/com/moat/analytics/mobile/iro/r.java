package com.moat.analytics.mobile.iro;

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
final class r extends c implements NativeDisplayTracker {

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private final Set<NativeDisplayTracker.MoatUserInteractionType> f642;

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private final Map<String, String> f643;

    @Override // com.moat.analytics.mobile.iro.c
    /* JADX INFO: renamed from: ˊ */
    final String mo469() {
        return "NativeDisplayTracker";
    }

    r(View view, Map<String, String> map) {
        super(view, true, false);
        this.f642 = new HashSet();
        b.m461(3, "NativeDisplayTracker", this, "Initializing.");
        this.f643 = map;
        if (view == null) {
            String str = "NativeDisplayTracker initialization not successful, Target view is null";
            b.m461(3, "NativeDisplayTracker", this, str);
            b.m459("[ERROR] ", str);
            this.f518 = new o("Target view is null");
            return;
        }
        if (map == null || map.isEmpty()) {
            String str2 = "NativeDisplayTracker initialization not successful, AdIds is null or empty";
            b.m461(3, "NativeDisplayTracker", this, str2);
            b.m459("[ERROR] ", str2);
            this.f518 = new o("AdIds is null or empty");
            return;
        }
        b bVar = ((j) j.getInstance()).f596;
        if (bVar == null) {
            String str3 = "NativeDisplayTracker initialization not successful, prepareNativeDisplayTracking was not called successfully";
            b.m461(3, "NativeDisplayTracker", this, str3);
            b.m459("[ERROR] ", str3);
            this.f518 = new o("prepareNativeDisplayTracking was not called successfully");
            return;
        }
        this.f520 = bVar.f503;
        try {
            super.m471(bVar.f505);
            if (this.f520 != null) {
                this.f520.m509(m587());
            }
            b.m459("[SUCCESS] ", "NativeDisplayTracker created for " + m468() + ", with adIds:" + map.toString());
        } catch (o e) {
            this.f518 = e;
        }
    }

    @Override // com.moat.analytics.mobile.iro.NativeDisplayTracker
    public final void reportUserInteractionEvent(NativeDisplayTracker.MoatUserInteractionType moatUserInteractionType) {
        try {
            b.m461(3, "NativeDisplayTracker", this, "reportUserInteractionEvent:" + moatUserInteractionType.name());
            if (this.f642.contains(moatUserInteractionType)) {
                return;
            }
            this.f642.add(moatUserInteractionType);
            JSONObject jSONObject = new JSONObject();
            jSONObject.accumulate("adKey", this.f519);
            jSONObject.accumulate("event", moatUserInteractionType.name().toLowerCase());
            if (this.f520 != null) {
                this.f520.m511(jSONObject.toString());
            }
        } catch (JSONException e) {
            b.m462("NativeDisplayTracker", this, "Got JSON exception");
            o.m569(e);
        } catch (Exception e2) {
            o.m569(e2);
        }
    }

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private String m587() {
        try {
            Map<String, String> map = this.f643;
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
            b.m461(3, "NativeDisplayTracker", this, "Parsed ad ids = " + string);
            return "{\"adIds\":" + string + ", \"adKey\":\"" + this.f519 + "\", \"adSize\":" + m586() + "}";
        } catch (Exception e) {
            o.m569(e);
            return "";
        }
    }

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private String m586() {
        try {
            Rect rectM617 = y.m617(super.m466());
            int iWidth = rectM617.width();
            int iHeight = rectM617.height();
            HashMap map = new HashMap();
            map.put("width", Integer.toString(iWidth));
            map.put("height", Integer.toString(iHeight));
            return new JSONObject(map).toString();
        } catch (Exception e) {
            o.m569(e);
            return null;
        }
    }
}
