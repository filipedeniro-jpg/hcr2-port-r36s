package com.applovin.impl.sdk.ad;

import android.R;
import android.graphics.Color;
import android.net.Uri;
import androidx.core.view.ViewCompat;
import com.applovin.impl.adview.p;
import com.applovin.impl.adview.t;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.impl.sdk.utils.k;
import com.applovin.impl.sdk.utils.n;
import com.applovin.sdk.AppLovinAdType;
import com.appsflyer.share.Constants;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class g extends AppLovinAdBase {
    private final AtomicBoolean a;
    private List<com.applovin.impl.sdk.c.a> b;
    private List<com.applovin.impl.sdk.c.a> c;
    private List<com.applovin.impl.sdk.c.a> d;
    private List<com.applovin.impl.sdk.c.a> e;

    public enum a {
        UNSPECIFIED,
        DISMISS,
        DO_NOT_DISMISS
    }

    public enum b {
        DEFAULT,
        ACTIVITY_PORTRAIT,
        ACTIVITY_LANDSCAPE
    }

    public g(JSONObject jSONObject, JSONObject jSONObject2, com.applovin.impl.sdk.ad.b bVar, com.applovin.impl.sdk.h hVar) {
        super(jSONObject, jSONObject2, bVar, hVar);
        this.a = new AtomicBoolean();
    }

    private float a(AppLovinAdType appLovinAdType, float f, boolean z) {
        if (appLovinAdType.equals(AppLovinAdType.INCENTIVIZED)) {
            return 0.5f;
        }
        return (appLovinAdType.equals(AppLovinAdType.REGULAR) && z && f == -1.0f) ? 0.5f : 0.0f;
    }

    private String a() {
        String stringFromAdObject = getStringFromAdObject("video_end_url", null);
        if (stringFromAdObject != null) {
            return stringFromAdObject.replace("{CLCODE}", getClCode());
        }
        return null;
    }

    private com.applovin.impl.adview.f.a b(boolean z) {
        return z ? com.applovin.impl.adview.f.a.WhiteXOnTransparentGrey : com.applovin.impl.adview.f.a.WhiteXOnOpaqueBlack;
    }

    private String c() {
        String stringFromAdObject = getStringFromAdObject("click_tracking_url", null);
        if (stringFromAdObject != null) {
            return stringFromAdObject.replace("{CLCODE}", getClCode());
        }
        return null;
    }

    public boolean A() {
        return getBooleanFromAdObject("hide_close_on_exit", false);
    }

    public boolean B() {
        return getBooleanFromAdObject("lock_current_orientation", false);
    }

    public int C() {
        return getIntFromAdObject("countdown_length", 0);
    }

    public int D() {
        int color = Color.parseColor("#C8FFFFFF");
        String stringFromAdObject = getStringFromAdObject("countdown_color", null);
        if (!k.b(stringFromAdObject)) {
            return color;
        }
        try {
            return Color.parseColor(stringFromAdObject);
        } catch (Throwable th) {
            this.sdk.w().b("DirectAd", "Unable to parse countdown color", th);
            return color;
        }
    }

    public int E() {
        String stringFromAdObject = getStringFromAdObject("video_background_color", null);
        if (k.b(stringFromAdObject)) {
            try {
                return Color.parseColor(stringFromAdObject);
            } catch (Throwable unused) {
            }
        }
        return ViewCompat.MEASURED_STATE_MASK;
    }

    public int F() {
        int i = hasVideoUrl() ? ViewCompat.MEASURED_STATE_MASK : -1157627904;
        String stringFromAdObject = getStringFromAdObject("graphic_background_color", null);
        if (!k.b(stringFromAdObject)) {
            return i;
        }
        try {
            return Color.parseColor(stringFromAdObject);
        } catch (Throwable unused) {
            return i;
        }
    }

    public a G() {
        String stringFromAdObject = getStringFromAdObject("poststitial_dismiss_type", null);
        if (k.b(stringFromAdObject)) {
            if ("dismiss".equalsIgnoreCase(stringFromAdObject)) {
                return a.DISMISS;
            }
            if ("no_dismiss".equalsIgnoreCase(stringFromAdObject)) {
                return a.DO_NOT_DISMISS;
            }
        }
        return a.UNSPECIFIED;
    }

    public List<String> H() {
        String stringFromAdObject = getStringFromAdObject("resource_cache_prefix", null);
        return stringFromAdObject != null ? com.applovin.impl.sdk.utils.d.a(stringFromAdObject) : this.sdk.b(com.applovin.impl.sdk.b.b.bG);
    }

    public String I() {
        return getStringFromAdObject("cache_prefix", null);
    }

    public boolean J() {
        return getBooleanFromAdObject("progress_bar_enabled", false);
    }

    public int K() {
        String stringFromAdObject = getStringFromAdObject("progress_bar_color", "#C8FFFFFF");
        if (k.b(stringFromAdObject)) {
            try {
                return Color.parseColor(stringFromAdObject);
            } catch (Throwable unused) {
            }
        }
        return 0;
    }

    public boolean L() {
        return getBooleanFromAdObject("vs_buffer_indicator_enabled", false);
    }

    public boolean M() {
        return getBooleanFromAdObject("vs_buffer_indicator_initial_load_enabled", false);
    }

    public int N() {
        return getIntFromAdObject("vs_buffer_indicator_style", R.attr.progressBarStyleLarge);
    }

    public int O() {
        String stringFromAdObject = getStringFromAdObject("vs_buffer_indicator_color", null);
        if (k.b(stringFromAdObject)) {
            try {
                return Color.parseColor(stringFromAdObject);
            } catch (Throwable unused) {
            }
        }
        return -1;
    }

    public int P() {
        int color = Color.parseColor("#66000000");
        String stringFromAdObject = getStringFromAdObject("vs_buffer_indicator_bg_color", null);
        if (!k.b(stringFromAdObject)) {
            return color;
        }
        try {
            return Color.parseColor(stringFromAdObject);
        } catch (Throwable unused) {
            return color;
        }
    }

    public boolean Q() {
        return getBooleanFromAdObject("clear_dismissible", false);
    }

    public int R() {
        int iA;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fj)).booleanValue()) {
            return n.a(this.adObject);
        }
        synchronized (this.adObjectLock) {
            iA = n.a(this.adObject);
        }
        return iA;
    }

    public int S() {
        return getIntFromAdObject("poststitial_shown_forward_delay_millis", -1);
    }

    public boolean T() {
        return getBooleanFromAdObject("should_apply_mute_setting_to_poststitial", false);
    }

    public boolean U() {
        return getBooleanFromAdObject("should_forward_close_button_tapped_to_poststitial", false);
    }

    public boolean V() {
        return getBooleanFromAdObject("forward_lifecycle_events_to_webview", false);
    }

    public int W() {
        return getIntFromAdObject("close_button_size", ((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.cL)).intValue());
    }

    public int X() {
        return getIntFromAdObject("close_button_top_margin", ((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.cM)).intValue());
    }

    public int Y() {
        return getIntFromAdObject("close_button_horizontal_margin", ((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.cK)).intValue());
    }

    public boolean Z() {
        return getBooleanFromAdObject("lhs_close_button", (Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.cJ));
    }

    protected com.applovin.impl.adview.f.a a(int i) {
        if (i == 1) {
            return com.applovin.impl.adview.f.a.WhiteXOnTransparentGrey;
        }
        return i == 2 ? com.applovin.impl.adview.f.a.Invisible : com.applovin.impl.adview.f.a.WhiteXOnOpaqueBlack;
    }

    public void a(boolean z) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put("html_resources_cached", z);
            }
        } catch (Throwable unused) {
        }
    }

    public boolean aa() {
        return getBooleanFromAdObject("lhs_skip_button", (Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.dc));
    }

    public boolean ab() {
        return getBooleanFromAdObject("stop_video_player_after_poststitial_render", false);
    }

    public boolean ac() {
        return getBooleanFromAdObject("unhide_adview_on_render", false);
    }

    public long ad() {
        long longFromAdObject = getLongFromAdObject("report_reward_duration", -1L);
        if (longFromAdObject >= 0) {
            return TimeUnit.SECONDS.toMillis(longFromAdObject);
        }
        return -1L;
    }

    public int ae() {
        return getIntFromAdObject("report_reward_percent", -1);
    }

    public boolean af() {
        return getBooleanFromAdObject("report_reward_percent_include_close_delay", true);
    }

    public AtomicBoolean ag() {
        return this.a;
    }

    public boolean ah() {
        return getBooleanFromAdObject("show_skip_button_on_click", false);
    }

    public boolean ai() {
        return getBooleanFromAdObject("restore_original_orientation", false);
    }

    public boolean aj() {
        return getBooleanFromAdObject("use_stream_url_on_cache_drop", false);
    }

    public List<com.applovin.impl.sdk.c.a> ak() {
        List<com.applovin.impl.sdk.c.a> list = this.b;
        if (list != null) {
            return list;
        }
        if (((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fj)).booleanValue()) {
            synchronized (this.adObjectLock) {
                this.b = n.a("video_end_urls", this.adObject, getClCode(), a(), this.sdk);
            }
        } else {
            this.b = n.a("video_end_urls", this.adObject, getClCode(), a(), this.sdk);
        }
        return this.b;
    }

    public List<com.applovin.impl.sdk.c.a> al() {
        List<com.applovin.impl.sdk.c.a> list = this.c;
        if (list != null) {
            return list;
        }
        if (((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fj)).booleanValue()) {
            synchronized (this.adObjectLock) {
                this.c = n.a("click_tracking_urls", this.adObject, getClCode(), c(), this.sdk);
            }
        } else {
            this.c = n.a("click_tracking_urls", this.adObject, getClCode(), c(), this.sdk);
        }
        return this.c;
    }

    public List<com.applovin.impl.sdk.c.a> am() {
        List<com.applovin.impl.sdk.c.a> list = this.d;
        if (list != null) {
            return list;
        }
        if (((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fj)).booleanValue()) {
            synchronized (this.adObjectLock) {
                this.d = n.a("video_click_tracking_urls", this.adObject, getClCode(), null, this.sdk);
            }
        } else {
            this.d = n.a("video_click_tracking_urls", this.adObject, getClCode(), null, this.sdk);
        }
        if (this.d.isEmpty()) {
            this.d = al();
        }
        return this.d;
    }

    public List<com.applovin.impl.sdk.c.a> an() {
        List<com.applovin.impl.sdk.c.a> list = this.e;
        if (list != null) {
            return list;
        }
        if (((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fj)).booleanValue()) {
            synchronized (this.adObjectLock) {
                this.e = n.a("imp_urls", this.adObject, getClCode(), null, this.sdk);
            }
        } else {
            this.e = n.a("imp_urls", this.adObject, getClCode(), null, this.sdk);
        }
        return this.e;
    }

    public boolean ao() {
        return getBooleanFromAdObject("render_poststitial_on_attach", false);
    }

    public boolean ap() {
        return getBooleanFromAdObject("render_poststitial_on_set_content_view", false);
    }

    public boolean aq() {
        return getBooleanFromAdObject("playback_requires_user_action", true);
    }

    public boolean ar() {
        return getBooleanFromAdObject("sanitize_webview", false);
    }

    public String as() {
        String stringFromAdObject = getStringFromAdObject("base_url", Constants.URL_PATH_DELIMITER);
        if ("null".equalsIgnoreCase(stringFromAdObject)) {
            return null;
        }
        return stringFromAdObject;
    }

    public boolean at() {
        return getBooleanFromAdObject("web_contents_debugging_enabled", false);
    }

    public t au() {
        JSONObject jsonObjectFromAdObject = getJsonObjectFromAdObject("web_view_settings", null);
        if (jsonObjectFromAdObject != null) {
            return new t(jsonObjectFromAdObject, this.sdk);
        }
        return null;
    }

    public List<String> av() {
        return com.applovin.impl.sdk.utils.d.a(getStringFromAdObject("wls", ""));
    }

    public List<String> aw() {
        return com.applovin.impl.sdk.utils.d.a(getStringFromAdObject("wlh", null));
    }

    public boolean ax() {
        return getBooleanFromAdObject("tvv", false);
    }

    public Uri ay() {
        String stringFromAdObject = getStringFromAdObject("mute_image", null);
        if (!k.b(stringFromAdObject)) {
            return null;
        }
        try {
            return Uri.parse(stringFromAdObject);
        } catch (Throwable unused) {
            return null;
        }
    }

    public Uri az() {
        String stringFromAdObject = getStringFromAdObject("unmute_image", "");
        if (k.b(stringFromAdObject)) {
            try {
                return Uri.parse(stringFromAdObject);
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    public void b(Uri uri) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put("mute_image", uri);
            }
        } catch (Throwable unused) {
        }
    }

    public boolean b() {
        this.sdk.w().d("DirectAd", "Attempting to invoke isVideoStream() from base ad class");
        return false;
    }

    public void c(Uri uri) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put("unmute_image", uri);
            }
        } catch (Throwable unused) {
        }
    }

    public Uri e() {
        this.sdk.w().d("DirectAd", "Attempting to invoke getVideoUri() from base ad class");
        return null;
    }

    public Uri g() {
        this.sdk.w().d("DirectAd", "Attempting to invoke getClickDestinationUri() from base ad class");
        return null;
    }

    public Uri h() {
        this.sdk.w().d("DirectAd", "Attempting to invoke getVideoClickDestinationUri() from base ad class");
        return null;
    }

    public b m() {
        String upperCase = getStringFromAdObject("ad_target", b.DEFAULT.toString()).toUpperCase(Locale.ENGLISH);
        if ("ACTIVITY_PORTRAIT".equalsIgnoreCase(upperCase)) {
            return b.ACTIVITY_PORTRAIT;
        }
        return "ACTIVITY_LANDSCAPE".equalsIgnoreCase(upperCase) ? b.ACTIVITY_LANDSCAPE : b.DEFAULT;
    }

    public boolean n() {
        return getBooleanFromAdObject("usia", false);
    }

    public float o() {
        return getFloatFromAdObject("close_delay", 0.0f);
    }

    public float p() {
        return getFloatFromAdObject("close_delay_graphic", a(getType(), o(), hasVideoUrl()));
    }

    public com.applovin.impl.adview.f.a q() {
        int intFromAdObject = getIntFromAdObject("close_style", -1);
        return intFromAdObject == -1 ? b(hasVideoUrl()) : a(intFromAdObject);
    }

    public com.applovin.impl.adview.f.a r() {
        int intFromAdObject = getIntFromAdObject("skip_style", -1);
        return intFromAdObject == -1 ? q() : a(intFromAdObject);
    }

    public boolean s() {
        return getBooleanFromAdObject("dismiss_on_skip", false);
    }

    public boolean t() {
        return getBooleanFromAdObject("html_resources_cached", false);
    }

    public String u() {
        JSONObject jsonObjectFromAdObject = getJsonObjectFromAdObject("video_button_properties", null);
        return jsonObjectFromAdObject != null ? com.applovin.impl.sdk.utils.g.a(jsonObjectFromAdObject, "video_button_html", "", this.sdk) : "";
    }

    public p v() {
        return new p(getJsonObjectFromAdObject("video_button_properties", null), this.sdk);
    }

    public boolean w() {
        return getBooleanFromAdObject("video_clickable", false);
    }

    public boolean x() {
        return getBooleanFromAdObject("accelerate_hardware", false);
    }

    public boolean y() {
        return getBooleanFromAdObject("keep_screen_on", false);
    }

    public boolean z() {
        return getBooleanFromAdObject("hide_close_on_exit_graphic", false);
    }
}
