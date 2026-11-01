package com.moat.analytics.mobile.cha;

import android.view.View;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class w extends b implements ReactiveVideoTracker {

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    private Integer f482;

    @Override // com.moat.analytics.mobile.cha.d
    /* JADX INFO: renamed from: ˋ */
    final String mo303() {
        return "ReactiveVideoTracker";
    }

    public w(String str) {
        super(str);
        a.m273(3, "ReactiveVideoTracker", this, "Initializing.");
        a.m270("[SUCCESS] ", "ReactiveVideoTracker created");
    }

    @Override // com.moat.analytics.mobile.cha.b
    /* JADX INFO: renamed from: ᐝ */
    final Map<String, Object> mo290() throws o {
        Integer numValueOf;
        HashMap map = new HashMap();
        View view = this.f284.get();
        int iValueOf = 0;
        if (view == null) {
            numValueOf = 0;
        } else {
            iValueOf = Integer.valueOf(view.getWidth());
            numValueOf = Integer.valueOf(view.getHeight());
        }
        map.put("duration", this.f482);
        map.put("width", iValueOf);
        map.put("height", numValueOf);
        return map;
    }

    @Override // com.moat.analytics.mobile.cha.ReactiveVideoTracker
    public final boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
        try {
            m304();
            m305();
            this.f482 = num;
            return super.mo288(map, view);
        } catch (Exception e) {
            m307("trackVideoAd", e);
            return false;
        }
    }

    @Override // com.moat.analytics.mobile.cha.b
    /* JADX INFO: renamed from: ˎ */
    final JSONObject mo285(MoatAdEvent moatAdEvent) {
        if (moatAdEvent.f264 == MoatAdEventType.AD_EVT_COMPLETE && !moatAdEvent.f263.equals(MoatAdEvent.f259) && !m280(moatAdEvent.f263, this.f482)) {
            moatAdEvent.f264 = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.mo285(moatAdEvent);
    }

    @Override // com.moat.analytics.mobile.cha.b, com.moat.analytics.mobile.cha.d
    /* JADX INFO: renamed from: ˋ */
    final void mo283(List<String> list) throws o {
        if (this.f482.intValue() < 1000) {
            throw new o(String.format(Locale.ROOT, "Invalid duration = %d. Please make sure duration is in milliseconds.", this.f482));
        }
        super.mo283(list);
    }
}
