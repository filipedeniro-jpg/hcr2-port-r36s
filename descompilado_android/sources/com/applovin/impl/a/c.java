package com.applovin.impl.a;

import com.applovin.impl.sdk.utils.n;
import com.applovin.impl.sdk.utils.o;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private static final List<String> c = Arrays.asList(MimeTypes.VIDEO_MP4, MimeTypes.VIDEO_WEBM, MimeTypes.VIDEO_H263, "video/x-matroska");
    private final com.applovin.impl.sdk.h b;
    private final JSONObject d;
    private final JSONObject e;
    private final com.applovin.impl.sdk.ad.b f;
    protected List<o> a = new ArrayList();
    private final long g = System.currentTimeMillis();

    public c(JSONObject jSONObject, JSONObject jSONObject2, com.applovin.impl.sdk.ad.b bVar, com.applovin.impl.sdk.h hVar) {
        this.b = hVar;
        this.d = jSONObject;
        this.e = jSONObject2;
        this.f = bVar;
    }

    public int a() {
        return this.a.size();
    }

    public List<o> b() {
        return this.a;
    }

    public JSONObject c() {
        return this.d;
    }

    public JSONObject d() {
        return this.e;
    }

    public com.applovin.impl.sdk.ad.b e() {
        return this.f;
    }

    public long f() {
        return this.g;
    }

    public com.applovin.impl.sdk.ad.d g() {
        String strA = com.applovin.impl.sdk.utils.g.a(this.e, "zone_id", (String) null, this.b);
        return com.applovin.impl.sdk.ad.d.a(AppLovinAdSize.fromString(com.applovin.impl.sdk.utils.g.a(this.e, "ad_size", (String) null, this.b)), AppLovinAdType.fromString(com.applovin.impl.sdk.utils.g.a(this.e, AppEventsConstants.EVENT_PARAM_AD_TYPE, (String) null, this.b)), strA, this.b);
    }

    public List<String> h() {
        List<String> listA = com.applovin.impl.sdk.utils.d.a(com.applovin.impl.sdk.utils.g.a(this.d, "vast_preferred_video_types", (String) null, (com.applovin.impl.sdk.h) null));
        return !listA.isEmpty() ? listA : c;
    }

    public int i() {
        return n.a(this.d);
    }
}
