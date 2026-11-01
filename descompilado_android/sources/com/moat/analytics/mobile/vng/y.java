package com.moat.analytics.mobile.vng;

import android.view.View;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class y extends c<Object> implements ReactiveVideoTracker {
    private Integer l;

    public y(String str) {
        super(str);
        p.a("[SUCCESS] ", a() + " created");
    }

    @Override // com.moat.analytics.mobile.vng.b
    String a() {
        return "ReactiveVideoTracker";
    }

    @Override // com.moat.analytics.mobile.vng.c
    protected JSONObject a(MoatAdEvent moatAdEvent) {
        if (moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE && !moatAdEvent.b.equals(MoatAdEvent.a) && !a(moatAdEvent.b, this.l)) {
            moatAdEvent.d = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.a(moatAdEvent);
    }

    @Override // com.moat.analytics.mobile.vng.c
    protected Map<String, Object> g() {
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

    @Override // com.moat.analytics.mobile.vng.ReactiveVideoTracker
    public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
        if (!this.e) {
            if (num.intValue() < 1000) {
                p.a(3, "ReactiveVideoTracker", this, String.format(Locale.ROOT, "Invalid duration = %d. Please make sure duration is in milliseconds.", num));
                return false;
            }
            this.l = num;
            return super.a(map, new Object(), view);
        }
        p.a(3, "ReactiveVideoTracker", this, "trackVideoAd already called");
        p.a("[ERROR] ", a() + " trackVideoAd can't be called twice");
        return false;
    }
}
