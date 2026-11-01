package com.facebook.ads.redexgen.X;

import com.google.android.gms.games.GamesStatusCodes;
import java.io.Serializable;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1O, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C1O implements Serializable {
    private static final long serialVersionUID = -1165646029762217510L;
    private final int B;
    private final int C;
    private final int D;
    private final boolean E;

    private C1O(int countdownTimerMS, int pulseAnimationDurationMS, int defaultAdIndex, boolean shouldShowRating) {
        this.B = countdownTimerMS;
        this.D = pulseAnimationDurationMS;
        this.C = defaultAdIndex;
        this.E = shouldShowRating;
    }

    public final int A() {
        return this.B;
    }

    public final int B() {
        return this.C;
    }

    public static C1O B(JSONObject adConfigObject) {
        return new C1O(adConfigObject.optInt("countdown_time_ms", GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_CREATION_NOT_ALLOWED), adConfigObject.optInt("pulse_animation_duration_ms", 600), adConfigObject.optInt("default_ad_index"), adConfigObject.optBoolean("should_show_rating", false));
    }

    public final int C() {
        return this.D;
    }

    public final boolean D() {
        return this.E;
    }
}
