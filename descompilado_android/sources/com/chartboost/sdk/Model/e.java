package com.chartboost.sdk.Model;

import android.os.Build;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.i;
import com.chartboost.sdk.impl.o;
import com.chartboost.sdk.impl.s;
import com.facebook.internal.AnalyticsEvents;
import com.ironsource.sdk.constants.Constants;
import com.vungle.warren.network.VungleApiClient;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e {
    public final boolean A;
    public final int B;
    public final boolean C;
    public final int D;
    public final boolean E;
    public final String F;
    public final String G;
    public final String H;
    public final String I;
    public final boolean J;
    public final boolean K;
    public final boolean L;
    public final String a;
    public final boolean b;
    public final boolean c;
    public final List<String> d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final int i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final boolean m;
    public final boolean n;
    public final boolean o;
    public final boolean p;
    public final boolean q;
    public final boolean r;
    public final long s;
    public final int t;
    public final int u;
    public final int v;
    public final int w;
    public final List<String> x;
    public final boolean y;
    public final boolean z;

    public e(JSONObject jSONObject) {
        boolean zOptBoolean;
        JSONObject jSONObjectOptJSONObject;
        this.a = jSONObject.optString("configVariant");
        this.b = jSONObject.optBoolean("prefetchDisable");
        this.c = jSONObject.optBoolean("publisherDisable");
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("invalidateFolderList");
        if (jSONArrayOptJSONArray != null) {
            int length = jSONArrayOptJSONArray.length();
            for (int i = 0; i < length; i++) {
                String strOptString = jSONArrayOptJSONArray.optString(i);
                if (!strOptString.isEmpty()) {
                    arrayList.add(strOptString);
                }
            }
        }
        this.d = Collections.unmodifiableList(arrayList);
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
        jSONObjectOptJSONObject2 = jSONObjectOptJSONObject2 == null ? new JSONObject() : jSONObjectOptJSONObject2;
        this.e = jSONObjectOptJSONObject2.optBoolean("enabled", true);
        this.f = jSONObjectOptJSONObject2.optBoolean("inplayEnabled", true);
        this.g = jSONObjectOptJSONObject2.optBoolean("interstitialEnabled", true);
        this.h = jSONObjectOptJSONObject2.optBoolean("lockOrientation");
        this.i = jSONObjectOptJSONObject2.optInt("prefetchSession", 3);
        this.j = jSONObjectOptJSONObject2.optBoolean("rewardVideoEnabled", true);
        JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("trackingLevels");
        jSONObjectOptJSONObject3 = jSONObjectOptJSONObject3 == null ? new JSONObject() : jSONObjectOptJSONObject3;
        this.k = jSONObjectOptJSONObject3.optBoolean("critical", true);
        this.r = jSONObjectOptJSONObject3.optBoolean("includeStackTrace", true);
        this.l = jSONObjectOptJSONObject3.optBoolean("error");
        this.m = jSONObjectOptJSONObject3.optBoolean(Constants.RequestParameters.DEBUG);
        this.n = jSONObjectOptJSONObject3.optBoolean(SettingsJsonConstants.SESSION_KEY);
        this.o = jSONObjectOptJSONObject3.optBoolean("system");
        this.p = jSONObjectOptJSONObject3.optBoolean("timing");
        this.q = jSONObjectOptJSONObject3.optBoolean("user");
        this.s = jSONObject.optLong("getAdRetryBaseMs", com.chartboost.sdk.Libraries.b.b);
        this.t = jSONObject.optInt("getAdRetryMaxBackoffExponent", 5);
        JSONObject jSONObjectOptJSONObject4 = jSONObject.optJSONObject(Constants.ParametersKeys.WEB_VIEW);
        jSONObjectOptJSONObject4 = jSONObjectOptJSONObject4 == null ? new JSONObject() : jSONObjectOptJSONObject4;
        boolean z = !VungleApiClient.MANUFACTURER_AMAZON.equalsIgnoreCase(Build.MANUFACTURER) || Build.VERSION.SDK_INT >= 21;
        this.u = jSONObjectOptJSONObject4.optInt("cacheMaxBytes", 104857600);
        int iOptInt = jSONObjectOptJSONObject4.optInt("cacheMaxUnits", 10);
        this.v = iOptInt > 0 ? iOptInt : 10;
        this.w = (int) TimeUnit.SECONDS.toDays(jSONObjectOptJSONObject4.optInt("cacheTTLs", com.chartboost.sdk.Libraries.b.a));
        ArrayList arrayList2 = new ArrayList();
        JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject4.optJSONArray("directories");
        if (jSONArrayOptJSONArray2 != null) {
            int length2 = jSONArrayOptJSONArray2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                String strOptString2 = jSONArrayOptJSONArray2.optString(i2);
                if (!strOptString2.isEmpty()) {
                    arrayList2.add(strOptString2);
                }
            }
        }
        this.x = Collections.unmodifiableList(arrayList2);
        this.y = z && jSONObjectOptJSONObject4.optBoolean("enabled", a());
        this.z = jSONObjectOptJSONObject4.optBoolean("inplayEnabled", true);
        this.A = jSONObjectOptJSONObject4.optBoolean("interstitialEnabled", true);
        int iOptInt2 = jSONObjectOptJSONObject4.optInt("invalidatePendingImpression", 3);
        this.B = iOptInt2 <= 0 ? 3 : iOptInt2;
        this.C = jSONObjectOptJSONObject4.optBoolean("lockOrientation", true);
        this.D = jSONObjectOptJSONObject4.optInt("prefetchSession", 3);
        this.E = jSONObjectOptJSONObject4.optBoolean("rewardVideoEnabled", true);
        this.F = jSONObjectOptJSONObject4.optString("version", "v2");
        this.G = String.format("%s/%s%s", Constants.ParametersKeys.WEB_VIEW, this.F, "/interstitial/get");
        this.H = String.format("%s/%s/%s", Constants.ParametersKeys.WEB_VIEW, this.F, "prefetch");
        this.I = String.format("%s/%s%s", Constants.ParametersKeys.WEB_VIEW, this.F, "/reward/get");
        ArrayList arrayList3 = new ArrayList();
        boolean zOptBoolean2 = i.x != Chartboost.CBPIDataUseConsent.NO_BEHAVIORAL;
        boolean zOptBoolean3 = i.x != Chartboost.CBPIDataUseConsent.NO_BEHAVIORAL;
        JSONObject jSONObjectOptJSONObject5 = jSONObject.optJSONObject("certificationProviders");
        if (jSONObjectOptJSONObject5 == null || (jSONObjectOptJSONObject = jSONObjectOptJSONObject5.optJSONObject("moat")) == null) {
            zOptBoolean = false;
        } else {
            arrayList3.add("moat");
            zOptBoolean = jSONObjectOptJSONObject.optBoolean("loggingEnabled", false);
            zOptBoolean2 = jSONObjectOptJSONObject.optBoolean("locationEnabled", zOptBoolean2);
            zOptBoolean3 = jSONObjectOptJSONObject.optBoolean("idfaCollectionEnabled", zOptBoolean3);
        }
        this.J = zOptBoolean;
        this.K = zOptBoolean2;
        this.L = zOptBoolean3;
        o.a(arrayList3);
    }

    private static boolean a() {
        int[] iArr = {4, 4, 2};
        String strD = s.a().d();
        if (strD != null && strD.length() > 0) {
            String[] strArrSplit = strD.replaceAll("[^\\d.]", "").split("\\.");
            for (int i = 0; i < strArrSplit.length && i < iArr.length; i++) {
                try {
                    if (Integer.valueOf(strArrSplit[i]).intValue() > iArr[i]) {
                        return true;
                    }
                    if (Integer.valueOf(strArrSplit[i]).intValue() < iArr[i]) {
                        return false;
                    }
                } catch (NumberFormatException unused) {
                }
            }
        }
        return false;
    }
}
