package com.applovin.impl.adview;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class p {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final boolean e;
    private final int f;
    private final int g;
    private final int h;
    private final float i;
    private final float j;

    public p(JSONObject jSONObject, com.applovin.impl.sdk.h hVar) {
        hVar.w().b("VideoButtonProperties", "Updating video button properties with JSON = " + com.applovin.impl.sdk.utils.g.a(jSONObject, hVar));
        this.a = com.applovin.impl.sdk.utils.g.b(jSONObject, "width", 64, hVar);
        this.b = com.applovin.impl.sdk.utils.g.b(jSONObject, "height", 7, hVar);
        this.c = com.applovin.impl.sdk.utils.g.b(jSONObject, "margin", 20, hVar);
        this.d = com.applovin.impl.sdk.utils.g.b(jSONObject, "gravity", 85, hVar);
        this.e = com.applovin.impl.sdk.utils.g.a(jSONObject, "tap_to_fade", (Boolean) false, hVar).booleanValue();
        this.f = com.applovin.impl.sdk.utils.g.b(jSONObject, "tap_to_fade_duration_milliseconds", 500, hVar);
        this.g = com.applovin.impl.sdk.utils.g.b(jSONObject, "fade_in_duration_milliseconds", 500, hVar);
        this.h = com.applovin.impl.sdk.utils.g.b(jSONObject, "fade_out_duration_milliseconds", 500, hVar);
        this.i = com.applovin.impl.sdk.utils.g.a(jSONObject, "fade_in_delay_seconds", 1.0f, hVar);
        this.j = com.applovin.impl.sdk.utils.g.a(jSONObject, "fade_out_delay_seconds", 6.0f, hVar);
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        p pVar = (p) obj;
        return this.a == pVar.a && this.b == pVar.b && this.c == pVar.c && this.d == pVar.d && this.e == pVar.e && this.f == pVar.f && this.g == pVar.g && this.h == pVar.h && Float.compare(pVar.i, this.i) == 0 && Float.compare(pVar.j, this.j) == 0;
    }

    public long f() {
        return this.f;
    }

    public long g() {
        return this.g;
    }

    public long h() {
        return this.h;
    }

    public int hashCode() {
        int i = ((((((((((((((this.a * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + (this.e ? 1 : 0)) * 31) + this.f) * 31) + this.g) * 31) + this.h) * 31;
        float f = this.i;
        int iFloatToIntBits = (i + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31;
        float f2 = this.j;
        return iFloatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0);
    }

    public float i() {
        return this.i;
    }

    public float j() {
        return this.j;
    }

    public String toString() {
        return "VideoButtonProperties{widthPercentOfScreen=" + this.a + ", heightPercentOfScreen=" + this.b + ", margin=" + this.c + ", gravity=" + this.d + ", tapToFade=" + this.e + ", tapToFadeDurationMillis=" + this.f + ", fadeInDurationMillis=" + this.g + ", fadeOutDurationMillis=" + this.h + ", fadeInDelay=" + this.i + ", fadeOutDelay=" + this.j + '}';
    }
}
