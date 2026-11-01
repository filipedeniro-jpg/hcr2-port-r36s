package com.applovin.impl.adview;

import android.annotation.TargetApi;
import android.webkit.WebSettings;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class t {
    private com.applovin.impl.sdk.h a;
    private JSONObject b;

    public t(JSONObject jSONObject, com.applovin.impl.sdk.h hVar) {
        this.a = hVar;
        this.b = jSONObject;
    }

    @TargetApi(21)
    Integer a() {
        int i;
        String strA = com.applovin.impl.sdk.utils.g.a(this.b, "mixed_content_mode", (String) null, this.a);
        if (com.applovin.impl.sdk.utils.k.b(strA)) {
            if ("always_allow".equalsIgnoreCase(strA)) {
                i = 0;
            } else if ("never_allow".equalsIgnoreCase(strA)) {
                i = 1;
            } else if ("compatibility_mode".equalsIgnoreCase(strA)) {
                i = 2;
            }
            return Integer.valueOf(i);
        }
        return null;
    }

    WebSettings.PluginState b() {
        String strA = com.applovin.impl.sdk.utils.g.a(this.b, "plugin_state", (String) null, this.a);
        if (com.applovin.impl.sdk.utils.k.b(strA)) {
            if ("on".equalsIgnoreCase(strA)) {
                return WebSettings.PluginState.ON;
            }
            if ("on_demand".equalsIgnoreCase(strA)) {
                return WebSettings.PluginState.ON_DEMAND;
            }
            if ("off".equalsIgnoreCase(strA)) {
                return WebSettings.PluginState.OFF;
            }
        }
        return null;
    }

    Boolean c() {
        return com.applovin.impl.sdk.utils.g.a(this.b, "allow_file_access", (Boolean) null, this.a);
    }

    Boolean d() {
        return com.applovin.impl.sdk.utils.g.a(this.b, "load_with_overview_mode", (Boolean) null, this.a);
    }

    Boolean e() {
        return com.applovin.impl.sdk.utils.g.a(this.b, "use_wide_view_port", (Boolean) null, this.a);
    }

    Boolean f() {
        return com.applovin.impl.sdk.utils.g.a(this.b, "allow_content_access", (Boolean) null, this.a);
    }

    Boolean g() {
        return com.applovin.impl.sdk.utils.g.a(this.b, "use_built_in_zoom_controls", (Boolean) null, this.a);
    }

    Boolean h() {
        return com.applovin.impl.sdk.utils.g.a(this.b, "display_zoom_controls", (Boolean) null, this.a);
    }

    Boolean i() {
        return com.applovin.impl.sdk.utils.g.a(this.b, "save_form_data", (Boolean) null, this.a);
    }

    Boolean j() {
        return com.applovin.impl.sdk.utils.g.a(this.b, "geolocation_enabled", (Boolean) null, this.a);
    }

    Boolean k() {
        return com.applovin.impl.sdk.utils.g.a(this.b, "need_initial_focus", (Boolean) null, this.a);
    }

    Boolean l() {
        return com.applovin.impl.sdk.utils.g.a(this.b, "allow_file_access_from_file_urls", (Boolean) null, this.a);
    }

    Boolean m() {
        return com.applovin.impl.sdk.utils.g.a(this.b, "allow_universal_access_from_file_urls", (Boolean) null, this.a);
    }

    Boolean n() {
        return com.applovin.impl.sdk.utils.g.a(this.b, "offscreen_pre_raster", (Boolean) null, this.a);
    }
}
