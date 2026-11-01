package com.adcolony.sdk;

import android.util.Log;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import com.facebook.AccessToken;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AdColonyAppOptions {
    public static final String ADMARVEL = "AdMarvel";
    public static final String ADMOB = "AdMob";
    public static final String ADOBEAIR = "Adobe AIR";
    public static final String AERSERVE = "AerServe";
    public static final int ALL = 2;
    public static final String APPODEAL = "Appodeal";
    public static final String COCOS2DX = "Cocos2d-x";
    public static final String CORONA = "Corona";
    public static final String FUSEPOWERED = "Fuse Powered";
    public static final String FYBER = "Fyber";
    public static final String IRONSOURCE = "ironSource";
    public static final int LANDSCAPE = 1;
    public static final String MOPUB = "MoPub";
    public static final int PORTRAIT = 0;

    @Deprecated
    public static final int SENSOR = 2;
    public static final String UNITY = "Unity";
    String[] b;
    AdColonyUserMetadata e;
    String a = "";
    JSONArray c = y.b();
    JSONObject d = y.a();

    public AdColonyAppOptions() {
        setOriginStore("google");
        if (a.b()) {
            l lVarA = a.a();
            if (lVarA.e()) {
                a(lVarA.d().a);
                a(lVarA.d().b);
            }
        }
    }

    public AdColonyAppOptions setGDPRRequired(boolean z) {
        setOption("gdpr_required", z);
        return this;
    }

    public boolean getGDPRRequired() {
        return y.d(this.d, "gdpr_required");
    }

    public AdColonyAppOptions setGDPRConsentString(@NonNull String str) {
        y.a(this.d, "consent_string", str);
        return this;
    }

    public String getGDPRConsentString() {
        return y.b(this.d, "consent_string");
    }

    public AdColonyAppOptions setAppVersion(@NonNull String str) {
        if (aw.d(str)) {
            setOption("app_version", str);
        }
        return this;
    }

    public String getAppVersion() {
        return y.b(this.d, "app_version");
    }

    public AdColonyAppOptions setUserID(@NonNull String str) {
        if (aw.d(str)) {
            setOption(AccessToken.USER_ID_KEY, str);
        }
        return this;
    }

    public String getUserID() {
        return y.b(this.d, AccessToken.USER_ID_KEY);
    }

    public AdColonyAppOptions setOption(@NonNull String str, boolean z) {
        if (aw.d(str)) {
            y.a(this.d, str, z);
        }
        return this;
    }

    public Object getOption(@NonNull String str) {
        return y.a(this.d, str);
    }

    public AdColonyAppOptions setOption(@NonNull String str, double d) {
        if (aw.d(str)) {
            y.a(this.d, str, d);
        }
        return this;
    }

    public AdColonyAppOptions setOption(@NonNull String str, @NonNull String str2) {
        if (str != null && aw.d(str) && aw.d(str2)) {
            y.a(this.d, str, str2);
        }
        return this;
    }

    public AdColonyAppOptions setOriginStore(@NonNull String str) {
        if (aw.d(str)) {
            setOption("origin_store", str);
        }
        return this;
    }

    public String getOriginStore() {
        return y.b(this.d, "origin_store");
    }

    public AdColonyAppOptions setRequestedAdOrientation(@IntRange(from = 0, to = 2) int i) {
        setOption("orientation", i);
        return this;
    }

    public int getRequestedAdOrientation() {
        return y.a(this.d, "orientation", -1);
    }

    public AdColonyAppOptions setAppOrientation(@IntRange(from = 0, to = 2) int i) {
        setOption("app_orientation", i);
        return this;
    }

    public int getAppOrientation() {
        return y.a(this.d, "app_orientation", -1);
    }

    public AdColonyAppOptions setUserMetadata(@NonNull AdColonyUserMetadata adColonyUserMetadata) {
        this.e = adColonyUserMetadata;
        y.a(this.d, "user_metadata", adColonyUserMetadata.c);
        return this;
    }

    public AdColonyAppOptions setTestModeEnabled(boolean z) {
        y.a(this.d, "test_mode", z);
        return this;
    }

    public boolean getTestModeEnabled() {
        return y.d(this.d, "test_mode");
    }

    public AdColonyAppOptions setMultiWindowEnabled(boolean z) {
        y.a(this.d, "multi_window_enabled", z);
        return this;
    }

    public boolean getMultiWindowEnabled() {
        return y.d(this.d, "multi_window_enabled");
    }

    public AdColonyUserMetadata getUserMetadata() {
        return this.e;
    }

    public AdColonyAppOptions setMediationNetwork(@NonNull String str, @NonNull String str2) {
        if (aw.d(str) && aw.d(str2)) {
            y.a(this.d, "mediation_network", str);
            y.a(this.d, "mediation_network_version", str2);
        }
        return this;
    }

    public JSONObject getMediationInfo() {
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "name", y.b(this.d, "mediation_network"));
        y.a(jSONObjectA, "version", y.b(this.d, "mediation_network_version"));
        return jSONObjectA;
    }

    public AdColonyAppOptions setPlugin(@NonNull String str, @NonNull String str2) {
        if (aw.d(str) && aw.d(str2)) {
            y.a(this.d, "plugin", str);
            y.a(this.d, "plugin_version", str2);
        }
        return this;
    }

    public JSONObject getPluginInfo() {
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "name", y.b(this.d, "plugin"));
        y.a(jSONObjectA, "version", y.b(this.d, "plugin_version"));
        return jSONObjectA;
    }

    public AdColonyAppOptions setKeepScreenOn(boolean z) {
        y.a(this.d, "keep_screen_on", z);
        return this;
    }

    public boolean getKeepScreenOn() {
        return y.d(this.d, "keep_screen_on");
    }

    public static AdColonyAppOptions getMoPubAppOptions(@NonNull String str) {
        AdColonyAppOptions mediationNetwork = new AdColonyAppOptions().setMediationNetwork(MOPUB, "1.0");
        if (str != null && !str.isEmpty()) {
            for (String str2 : str.split(",")) {
                String[] strArrSplit = str2.split(":");
                if (strArrSplit.length == 2) {
                    String str3 = strArrSplit[0];
                    byte b = -1;
                    int iHashCode = str3.hashCode();
                    if (iHashCode != 109770977) {
                        if (iHashCode == 351608024 && str3.equals("version")) {
                            b = 1;
                        }
                    } else if (str3.equals("store")) {
                        b = 0;
                    }
                    if (b == 0) {
                        mediationNetwork.setOriginStore(strArrSplit[1]);
                    } else if (b == 1) {
                        mediationNetwork.setAppVersion(strArrSplit[1]);
                    } else {
                        Log.e("AdColonyMoPub", "AdColony client options in wrong format - please check your MoPub dashboard");
                        return mediationNetwork;
                    }
                } else {
                    Log.e("AdColonyMoPub", "AdColony client options not recognized - please check your MoPub dashboard");
                    return null;
                }
            }
        }
        return mediationNetwork;
    }

    AdColonyAppOptions a(String str) {
        if (str == null) {
            return this;
        }
        this.a = str;
        y.a(this.d, "app_id", str);
        return this;
    }

    AdColonyAppOptions a(String... strArr) {
        if (strArr == null) {
            return this;
        }
        this.b = strArr;
        this.c = y.b();
        for (String str : strArr) {
            y.a(this.c, str);
        }
        return this;
    }

    String a() {
        return this.a;
    }

    String[] b() {
        return this.b;
    }

    JSONArray c() {
        return this.c;
    }

    JSONObject d() {
        return this.d;
    }

    void e() {
        if (y.i(this.d, "use_forced_controller")) {
            ADCVMModule.a = y.d(this.d, "use_forced_controller");
        }
        if (y.i(this.d, "use_staging_launch_server")) {
            l.e = y.d(this.d, "use_staging_launch_server") ? "https://adc3-launch-server-staging.herokuapp.com/v4/launch" : "https://adc3-launch.adcolony.com/v4/launch";
        }
    }
}
