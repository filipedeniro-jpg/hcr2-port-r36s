package com.facebook.ads.redexgen.X;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class BO {
    public final long B;
    public final long C;
    public final long D;
    public final long E;
    public final long F;
    public final long G;
    public final long H;
    public final String I;

    private BO(String url, long handlerTimeMs, long loadStartMs, long responseEndMs, long domContentLoadedMs, long scrollReadyMs, long loadFinishMs, long sessionFinishMs) {
        this.I = url;
        this.C = handlerTimeMs;
        this.E = loadStartMs;
        this.F = responseEndMs;
        this.B = domContentLoadedMs;
        this.G = scrollReadyMs;
        this.D = loadFinishMs;
        this.H = sessionFinishMs;
    }

    public final Map<String, String> A() {
        HashMap map = new HashMap(7);
        map.put("initial_url", this.I);
        map.put("handler_time_ms", String.valueOf(this.C));
        map.put("load_start_ms", String.valueOf(this.E));
        map.put("response_end_ms", String.valueOf(this.F));
        map.put("dom_content_loaded_ms", String.valueOf(this.B));
        map.put("scroll_ready_ms", String.valueOf(this.G));
        map.put("load_finish_ms", String.valueOf(this.D));
        map.put("session_finish_ms", String.valueOf(this.H));
        return map;
    }
}
