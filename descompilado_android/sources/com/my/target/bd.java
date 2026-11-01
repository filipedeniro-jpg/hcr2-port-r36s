package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: AdditionalDataParser.java */
/* JADX INFO: loaded from: classes2.dex */
public class bd {
    public static final int ea = 5;

    @NonNull
    private final b adConfig;

    @NonNull
    private final bh eA;

    @NonNull
    private final af ey;

    @NonNull
    private final Context ez;

    /* JADX INFO: compiled from: AdditionalDataParser.java */
    public interface a {
        public static final String ID = "id";
        public static final String TYPE = "type";
        public static final String URL = "url";
        public static final String dc = "statistics";
        public static final String eB = "doAfter";
        public static final String eC = "doOnEmptyResponseFromId";
        public static final String eD = "isMidrollPoint";
        public static final String eE = "serviceStatistics";
        public static final String eF = "pointP";
        public static final String eG = "point";
        public static final String eH = "allowClose";
        public static final String eI = "allowCloseDelay";
        public static final String eJ = "allowSeek";
        public static final String eK = "allowSkip";
        public static final String eL = "allowTrackChange";
        public static final String eM = "hasPause";
    }

    public static bd a(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        return new bd(afVar, bVar, context);
    }

    private bd(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        this.ey = afVar;
        this.adConfig = bVar;
        this.ez = context;
        this.eA = bh.d(afVar, bVar, context);
    }

    @Nullable
    public af c(@NonNull JSONObject jSONObject) {
        int iG = this.ey.G();
        if (iG >= 5) {
            g.a("got additional data, but max redirects limit exceeded");
            return null;
        }
        int iOptInt = jSONObject.optInt("id", this.ey.getId());
        String strOptString = jSONObject.optString("url");
        if (TextUtils.isEmpty(strOptString)) {
            e(az.a.ej, "No url in additionalData Id = " + iOptInt);
            return null;
        }
        af afVarN = af.n(strOptString);
        afVarN.a(iG + 1);
        afVarN.setId(iOptInt);
        afVarN.c(jSONObject.optBoolean(a.eB, afVarN.z()));
        afVarN.b(jSONObject.optInt(a.eC, afVarN.A()));
        boolean zOptBoolean = jSONObject.optBoolean(a.eD, afVarN.B());
        afVarN.e(zOptBoolean);
        afVarN.setAllowCloseDelay((float) jSONObject.optDouble("allowCloseDelay", afVarN.getAllowCloseDelay()));
        if (jSONObject.has("allowClose")) {
            afVarN.a(Boolean.valueOf(jSONObject.optBoolean("allowClose")));
        }
        if (jSONObject.has("hasPause")) {
            afVarN.b(Boolean.valueOf(jSONObject.optBoolean("hasPause")));
        }
        if (jSONObject.has(a.eJ)) {
            afVarN.c(Boolean.valueOf(jSONObject.optBoolean(a.eJ)));
        }
        if (jSONObject.has(a.eK)) {
            afVarN.d(Boolean.valueOf(jSONObject.optBoolean(a.eK)));
        }
        if (jSONObject.has(a.eL)) {
            afVarN.e(Boolean.valueOf(jSONObject.optBoolean(a.eL)));
        }
        double dOptDouble = jSONObject.optDouble("point");
        if (Double.isNaN(dOptDouble)) {
            dOptDouble = -1.0d;
        } else if (dOptDouble < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            e(az.a.ei, "Wrong value " + dOptDouble + " for point in additionalData object");
        }
        double dOptDouble2 = jSONObject.optDouble("pointP");
        if (Double.isNaN(dOptDouble2)) {
            dOptDouble2 = -1.0d;
        } else if (dOptDouble2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            e(az.a.ei, "Wrong value " + dOptDouble2 + " for pointP in additionalData object");
        }
        if (zOptBoolean && dOptDouble < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && dOptDouble2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            dOptDouble2 = 50.0d;
            dOptDouble = -1.0d;
        }
        afVarN.setPoint((float) dOptDouble);
        afVarN.setPointP((float) dOptDouble2);
        afVarN.d(this.ey.F());
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(a.eE);
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null) {
                    String strOptString2 = jSONObjectOptJSONObject.optString("type", "");
                    String strOptString3 = jSONObjectOptJSONObject.optString("url", "");
                    if (!TextUtils.isEmpty(strOptString2) && !TextUtils.isEmpty(strOptString3)) {
                        afVarN.a(aq.b(strOptString2, strOptString3));
                    }
                }
            }
        }
        this.eA.a(afVarN.N(), jSONObject, String.valueOf(afVarN.getId()), -1.0f);
        return afVarN;
    }

    private void e(@NonNull String str, @NonNull String str2) {
        az.z(str).A(str2).g(this.adConfig.getSlotId()).B(this.ey.getUrl()).e(this.ez);
    }
}
