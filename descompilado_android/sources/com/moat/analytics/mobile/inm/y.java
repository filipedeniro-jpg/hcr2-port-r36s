package com.moat.analytics.mobile.inm;

import android.view.View;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class y extends c implements ReactiveVideoTracker {
    private Integer l;

    public y(String str) {
        super(str);
        p.a(3, "ReactiveVideoTracker", this, "Initializing.");
        p.a("[SUCCESS] ", a() + " created");
    }

    @Override // com.moat.analytics.mobile.inm.b
    String a() {
        return "ReactiveVideoTracker";
    }

    @Override // com.moat.analytics.mobile.inm.c
    JSONObject a(MoatAdEvent moatAdEvent) {
        if (moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE && !moatAdEvent.b.equals(MoatAdEvent.a) && !a(moatAdEvent.b, this.l)) {
            moatAdEvent.d = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.a(moatAdEvent);
    }

    @Override // com.moat.analytics.mobile.inm.c, com.moat.analytics.mobile.inm.b
    void a(List<String> list) throws m {
        if (this.l.intValue() < 1000) {
            throw new m(String.format(Locale.ROOT, "Invalid duration = %d. Please make sure duration is in milliseconds.", this.l));
        }
        super.a(list);
    }

    @Override // com.moat.analytics.mobile.inm.c
    Map<String, Object> i() {
        Integer numValueOf;
        HashMap map = new HashMap();
        View view = this.k.get();
        int iValueOf = 0;
        if (view != null) {
            iValueOf = Integer.valueOf(view.getWidth());
            numValueOf = Integer.valueOf(view.getHeight());
        } else {
            numValueOf = 0;
        }
        map.put("duration", this.l);
        map.put("width", iValueOf);
        map.put("height", numValueOf);
        return map;
    }

    @Override // com.moat.analytics.mobile.inm.ReactiveVideoTracker
    public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
        try {
            c();
            d();
            this.l = num;
            return super.a(map, view);
        } catch (Exception e) {
            a("trackVideoAd", e);
            return false;
        }
    }
}
