package com.applovin.impl.sdk.c;

import android.text.TextUtils;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
class b {
    static final b A;
    private static final Set<String> D = new HashSet(31);
    static final b a;
    static final b b;
    static final b c;
    static final b d;
    static final b e;
    static final b f;
    static final b g;
    static final b h;
    static final b i;
    static final b j;
    static final b k;
    static final b l;
    static final b m;
    static final b n;
    static final b o;
    static final b p;
    static final b q;
    static final b r;
    static final b s;
    static final b t;
    static final b u;
    static final b v;
    static final b w;
    static final b x;
    static final b y;
    static final b z;
    private final String B;
    private final String C;

    static {
        a("sasw", "AD_SHOWN_WITH_WEBKIT");
        a("sisw", "IS_STREAMING_WEBKIT");
        a("surw", "UNABLE_TO_RETRIEVE_WEBKIT_HTML_STRING");
        a("surp", "UNABLE_TO_PERSIST_WEBKIT_HTML_PLACEMENT_REPLACED_STRING");
        a("swhp", "SUCCESSFULLY_PERSISTED_WEBKIT_HTML_STRING");
        a("skr", "STOREKIT_REDIRECTED");
        a("sklf", "STOREKIT_LOAD_FAILURE");
        a("skps", "STOREKIT_PRELOAD_SKIPPED");
        a = a("sas", "AD_SOURCE");
        b = a("srt", "AD_RENDER_TIME");
        c = a("sft", "AD_FETCH_TIME");
        d = a("sfs", "AD_FETCH_SIZE");
        e = a("sadb", "AD_DOWNLOADED_BYTES");
        f = a("sacb", "AD_CACHED_BYTES");
        g = a("stdl", "TIME_TO_DISPLAY_FROM_LOAD");
        h = a("stdi", "TIME_TO_DISPLAY_FROM_INIT");
        i = a("snas", "AD_NUMBER_IN_SESSION");
        j = a("snat", "AD_NUMBER_TOTAL");
        k = a("stah", "TIME_AD_HIDDEN_FROM_SHOW");
        l = a("stas", "TIME_TO_SKIP_FROM_SHOW");
        m = a("stac", "TIME_TO_CLICK_FROM_SHOW");
        n = a("stbe", "TIME_TO_EXPAND_FROM_SHOW");
        o = a("stbc", "TIME_TO_CONTRACT_FROM_SHOW");
        p = a("saan", "AD_SHOWN_WITH_ACTIVE_NETWORK");
        q = a("suvs", "INTERSTITIAL_USED_VIDEO_STREAM");
        r = a("sugs", "AD_USED_GRAPHIC_STREAM");
        s = a("svpv", "INTERSTITIAL_VIDEO_PERCENT_VIEWED");
        t = a("stpd", "INTERSTITIAL_PAUSED_DURATION");
        u = a("sspe", "INTERSTITIAL_SHOW_POSTSTITIAL_CODE_EXECUTED");
        v = a("shsc", "HTML_RESOURCE_CACHE_SUCCESS_COUNT");
        w = a("shfc", "HTML_RESOURCE_CACHE_FAILURE_COUNT");
        x = a("svmi", "INTERSTITIAL_VIDEO_MUTED_INITIALLY");
        y = a("stvm", "TIME_TO_TOGGLE_VIDEO_MUTE");
        z = a("schc", "AD_CANCELLED_HTML_CACHING");
        A = a("smwm", "AD_SHOWN_IN_MULTIWINDOW_MODE");
    }

    private b(String str, String str2) {
        this.B = str;
        this.C = str2;
    }

    private static b a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("No key name specified");
        }
        if (D.contains(str)) {
            throw new IllegalArgumentException("Key has already been used: " + str);
        }
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("No debug name specified");
        }
        D.add(str);
        return new b(str, str2);
    }

    public String a() {
        return this.B;
    }

    public String b() {
        return this.C;
    }
}
