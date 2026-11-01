package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: StatsParser.java */
/* JADX INFO: loaded from: classes2.dex */
public class bh {

    @NonNull
    private final b adConfig;

    @Nullable
    private String ax;

    @NonNull
    private final af eO;

    @NonNull
    private final Context ez;

    @NonNull
    public static bh d(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        return new bh(afVar, bVar, context);
    }

    protected bh(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        this.eO = afVar;
        this.adConfig = bVar;
        this.ez = context;
    }

    public void a(@NonNull ar arVar, @NonNull JSONObject jSONObject, @NonNull String str, float f) {
        int length;
        aq aqVarA;
        arVar.a(this.eO.N(), f);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("statistics");
        if (jSONArrayOptJSONArray != null && (length = jSONArrayOptJSONArray.length()) > 0) {
            this.ax = str;
            for (int i = 0; i < length; i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null && (aqVarA = a(jSONObjectOptJSONObject, f)) != null) {
                    arVar.b(aqVarA);
                }
            }
        }
    }

    @Nullable
    protected aq a(@NonNull JSONObject jSONObject, float f) {
        String strOptString = jSONObject.optString("type");
        String strOptString2 = jSONObject.optString("url");
        if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString2)) {
            e(az.a.ej, "failed to parse stat: no type or url");
            return null;
        }
        byte b = -1;
        int iHashCode = strOptString.hashCode();
        if (iHashCode != 1669348544) {
            if (iHashCode == 1788134515 && strOptString.equals(aq.a.dH)) {
                b = 0;
            }
        } else if (strOptString.equals(aq.a.dI)) {
            b = 1;
        }
        if (b == 0) {
            return a(jSONObject, strOptString2, f);
        }
        if (b == 1) {
            return b(jSONObject, strOptString2, f);
        }
        return aq.b(strOptString, strOptString2);
    }

    @Nullable
    ap a(@NonNull JSONObject jSONObject, @NonNull String str, float f) {
        ap apVarW = ap.w(str);
        if (jSONObject.has(be.a.fk)) {
            float fOptDouble = (float) jSONObject.optDouble(be.a.fk, apVarW.Z());
            if (fOptDouble >= 0.0f && fOptDouble <= 100.0f) {
                if (f > 0.0f) {
                    apVarW.c((fOptDouble * f) / 100.0f);
                } else {
                    apVarW.d(fOptDouble);
                }
                return apVarW;
            }
        }
        if (!jSONObject.has("value")) {
            return null;
        }
        float fOptDouble2 = (float) jSONObject.optDouble("value", apVarW.Y());
        if (fOptDouble2 < 0.0f) {
            return null;
        }
        apVarW.c(fOptDouble2);
        return apVarW;
    }

    @Nullable
    private aq b(@NonNull JSONObject jSONObject, @NonNull String str, float f) {
        int iOptInt = jSONObject.optInt(be.a.fl, -1);
        if (iOptInt < 0 || iOptInt > 100) {
            e(az.a.ei, "failed to parse viewabilityStat: invalid viewable percent value");
            return null;
        }
        if (jSONObject.has(be.a.fm)) {
            ao aoVarV = ao.v(str);
            aoVarV.f(iOptInt);
            aoVarV.f(jSONObject.optBoolean(be.a.fm, false));
            if (jSONObject.has(be.a.fk)) {
                float fOptDouble = (float) jSONObject.optDouble(be.a.fk, aoVarV.Z());
                if (fOptDouble >= 0.0f && fOptDouble <= 100.0f) {
                    if (f > 0.0f) {
                        aoVarV.c((fOptDouble * f) / 100.0f);
                    } else {
                        aoVarV.d(fOptDouble);
                    }
                    return aoVarV;
                }
            }
            if (jSONObject.has("value")) {
                float fOptDouble2 = (float) jSONObject.optDouble("value", aoVarV.Y());
                if (fOptDouble2 >= 0.0f) {
                    aoVarV.c(fOptDouble2);
                    return aoVarV;
                }
            }
        } else if (jSONObject.has("duration")) {
            an anVarU = an.u(str);
            anVarU.f(iOptInt);
            float fOptDouble3 = (float) jSONObject.optDouble("duration", anVarU.getDuration());
            if (fOptDouble3 >= 0.0f) {
                anVarU.setDuration(fOptDouble3);
                return anVarU;
            }
        } else {
            e(az.a.ei, "failed to parse viewabilityStat: no ovv or duration");
        }
        return null;
    }

    protected void e(@NonNull String str, @NonNull String str2) {
        az.z(str).A(str2).g(this.adConfig.getSlotId()).C(this.ax).B(this.eO.getUrl()).e(this.ez);
    }
}
