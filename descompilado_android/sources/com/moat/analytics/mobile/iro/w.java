package com.moat.analytics.mobile.iro;

import android.view.View;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class w extends d implements ReactiveVideoTracker {

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    private Integer f685;

    @Override // com.moat.analytics.mobile.iro.c
    /* JADX INFO: renamed from: ˊ */
    final String mo469() {
        return "ReactiveVideoTracker";
    }

    public w(String str) {
        super(str);
        b.m461(3, "ReactiveVideoTracker", this, "Initializing.");
        b.m459("[SUCCESS] ", "ReactiveVideoTracker created");
    }

    @Override // com.moat.analytics.mobile.iro.d
    /* JADX INFO: renamed from: ˊॱ */
    final Map<String, Object> mo480() throws o {
        Integer numValueOf;
        HashMap map = new HashMap();
        View view = this.f526.get();
        int iValueOf = 0;
        if (view == null) {
            numValueOf = 0;
        } else {
            iValueOf = Integer.valueOf(view.getWidth());
            numValueOf = Integer.valueOf(view.getHeight());
        }
        map.put("duration", this.f685);
        map.put("width", iValueOf);
        map.put("height", numValueOf);
        return map;
    }

    @Override // com.moat.analytics.mobile.iro.ReactiveVideoTracker
    public final boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
        try {
            m470();
            m473();
            this.f685 = num;
            return super.mo482(map, view);
        } catch (Exception e) {
            m475("trackVideoAd", e);
            return false;
        }
    }

    @Override // com.moat.analytics.mobile.iro.d
    /* JADX INFO: renamed from: ˋ */
    final JSONObject mo481(MoatAdEvent moatAdEvent) {
        if (moatAdEvent.f490 == MoatAdEventType.AD_EVT_COMPLETE && !moatAdEvent.f488.equals(MoatAdEvent.f485) && !m478(moatAdEvent.f488, this.f685)) {
            moatAdEvent.f490 = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.mo481(moatAdEvent);
    }

    @Override // com.moat.analytics.mobile.iro.d, com.moat.analytics.mobile.iro.c
    /* JADX INFO: renamed from: ˏ */
    final void mo474(List<String> list) throws o {
        if (this.f685.intValue() < 1000) {
            throw new o(String.format(Locale.ROOT, "Invalid duration = %d. Please make sure duration is in milliseconds.", this.f685));
        }
        super.mo474(list);
    }
}
