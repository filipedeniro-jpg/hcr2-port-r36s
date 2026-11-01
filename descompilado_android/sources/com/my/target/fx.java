package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONObject;

/* JADX INFO: compiled from: ResearchStatsParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fx extends bh {
    public static fx a(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        return new fx(afVar, bVar, context);
    }

    private fx(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        super(afVar, bVar, context);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0053  */
    @Override // com.my.target.bh
    @Nullable
    public final aq a(@NonNull JSONObject jSONObject, float f) {
        byte b;
        String strOptString = jSONObject.optString("type");
        String strOptString2 = jSONObject.optString("url");
        if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString2)) {
            e(az.a.ej, "failed to parse stat: no type or url");
            return null;
        }
        int iHashCode = strOptString.hashCode();
        if (iHashCode != -1053159584) {
            if (iHashCode != 1669348544) {
                if (iHashCode == 1788134515 && strOptString.equals(aq.a.dH)) {
                    b = 2;
                } else {
                    b = -1;
                }
            } else if (strOptString.equals(aq.a.dI)) {
                b = 0;
            } else {
                b = -1;
            }
        } else if (strOptString.equals(aq.a.dJ)) {
            b = 1;
        } else {
            b = -1;
        }
        if (b == 0) {
            int iOptInt = jSONObject.optInt(be.a.fl, -1);
            if (iOptInt < 0 || iOptInt > 100) {
                e(az.a.ei, "failed to parse viewabilityStat: invalid viewable percent value");
                return null;
            }
            int iOptInt2 = jSONObject.optInt("duration", -1);
            if (iOptInt2 < 0) {
                return null;
            }
            int iOptInt3 = jSONObject.optInt("startTimer", 0);
            int iOptInt4 = jSONObject.optInt("endTimer", 0);
            if (iOptInt3 < 0) {
                e(az.a.ei, "failed to parse viewabilityStat: wrong start timer ".concat(String.valueOf(iOptInt3)));
                return null;
            }
            if (iOptInt4 < 0) {
                e(az.a.ei, "failed to parse viewabilityStat: wrong end timer ".concat(String.valueOf(iOptInt3)));
                return null;
            }
            if (iOptInt4 != 0 && iOptInt3 >= iOptInt4) {
                e(az.a.ei, "failed to parse viewabilityStat: start timer (" + iOptInt3 + ") cannot be less than end timer (" + iOptInt4 + ")");
                return null;
            }
            fw fwVarB = fw.b(strOptString2);
            fwVarB.f(iOptInt);
            fwVarB.setDuration(iOptInt2);
            fwVarB.d(iOptInt3);
            fwVarB.e(iOptInt4);
            return fwVarB;
        }
        if (b != 1) {
            if (b == 2) {
                ap apVarA = super.a(jSONObject, strOptString2, f);
                if (apVarA == null || apVarA.Y() < 0.0f) {
                    return null;
                }
                return apVarA;
            }
            return super.a(jSONObject, f);
        }
        if (!strOptString2.contains("[CONTENTPLAYHEAD]")) {
            e(az.a.ej, "failed to parse researchTimerStat: no [CONTENTPLAYHEAD] macros");
            return null;
        }
        int iOptInt5 = jSONObject.optInt("startTimer", 0);
        int iOptInt6 = jSONObject.optInt("endTimer", 0);
        if (iOptInt5 < 0) {
            e(az.a.ei, "failed to parse researchTimerStat: wrong start timer ".concat(String.valueOf(iOptInt5)));
            return null;
        }
        if (iOptInt6 < 0) {
            e(az.a.ei, "failed to parse researchTimerStat: wrong end timer ".concat(String.valueOf(iOptInt5)));
            return null;
        }
        if (iOptInt6 != 0 && iOptInt5 >= iOptInt6) {
            e(az.a.ei, "failed to parse researchTimerStat: start timer (" + iOptInt5 + ") cannot be less than end timer (" + iOptInt5 + ")");
            return null;
        }
        fv fvVarA = fv.a(strOptString2);
        fvVarA.f(jSONObject.optInt("rate", 1));
        fvVarA.d(iOptInt5);
        fvVarA.e(iOptInt6);
        return fvVarA;
    }
}
