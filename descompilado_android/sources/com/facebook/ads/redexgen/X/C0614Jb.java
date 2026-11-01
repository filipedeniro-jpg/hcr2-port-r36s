package com.facebook.ads.redexgen.X;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Jb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0614Jb {
    private int B;
    private int C;
    private boolean D;
    private int E;
    private int F;
    private int G;

    public final Map<String, String> A() {
        HashMap map = new HashMap();
        map.put("is_timeout", Boolean.toString(this.D));
        map.put("ad_count", Integer.toString(this.E));
        map.put("default_ad_index", Integer.toString(this.B));
        map.put("selected_ad_index", Integer.toString(this.F));
        map.put("elapsed_time_from_timer_ms", Integer.toString(this.C));
        map.put("countdown_time_ms", Integer.toString(this.G));
        return map;
    }

    public final void B(int defaultAdIndex) {
        this.B = defaultAdIndex;
    }

    public final void C(int elapsedTimeFromTimeMS) {
        this.C = elapsedTimeFromTimeMS;
    }

    public final void D(int numberOfAds) {
        this.E = numberOfAds;
    }

    public final void E(int index) {
        this.F = index;
    }

    public final void F(boolean isTimout) {
        this.D = isTimout;
    }

    public final void G(int timerDuratonMS) {
        this.G = timerDuratonMS;
    }
}
