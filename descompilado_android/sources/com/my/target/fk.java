package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ironsource.sdk.precache.DownloadManager;
import com.my.target.common.models.VideoData;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: InstreamAdResponseParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fk extends d<com.my.target.core.models.sections.g> {
    @Override // com.my.target.d
    @Nullable
    public final /* synthetic */ al a(@NonNull String str, @NonNull af afVar, @Nullable al alVar, @NonNull b bVar, @NonNull Context context) {
        JSONObject jSONObjectOptJSONObject;
        com.my.target.core.models.sections.g gVarI = (com.my.target.core.models.sections.g) alVar;
        if (isVast(str)) {
            bi biVarA = bi.a(bVar, afVar, context);
            biVarA.G(str);
            String strH = afVar.H();
            if (strH == null) {
                strH = am.a.dm;
            }
            if (gVarI == null) {
                gVarI = com.my.target.core.models.sections.g.i();
            }
            am<VideoData> amVarA = gVarI.a(strH);
            if (amVarA != null) {
                if (biVarA.at().isEmpty()) {
                    af afVarAu = biVarA.au();
                    if (afVarAu != null) {
                        afVarAu.p(amVarA.getName());
                        int position = afVar.getPosition();
                        if (position >= 0) {
                            afVarAu.setPosition(position);
                        } else {
                            afVarAu.setPosition(amVarA.getBannersCount());
                        }
                        amVarA.c(afVarAu);
                    }
                } else {
                    amVarA.d(biVarA.F());
                    int position2 = afVar.getPosition();
                    for (ak<T> akVar : biVarA.at()) {
                        Boolean boolI = afVar.I();
                        if (boolI != null) {
                            akVar.setAllowClose(boolI.booleanValue());
                        }
                        float allowCloseDelay = afVar.getAllowCloseDelay();
                        if (allowCloseDelay > 0.0f) {
                            akVar.setAllowCloseDelay(allowCloseDelay);
                        }
                        Boolean boolJ = afVar.J();
                        if (boolJ != null) {
                            akVar.setAllowPause(boolJ.booleanValue());
                        }
                        akVar.setCloseActionText("Close");
                        akVar.setPoint(afVar.getPoint());
                        akVar.setPointP(afVar.getPointP());
                        if (position2 >= 0) {
                            amVarA.a(akVar, position2);
                            position2++;
                        } else {
                            amVarA.a(akVar);
                        }
                    }
                }
            }
            return gVarI;
        }
        JSONObject jSONObjectA = a(str, context);
        if (jSONObjectA != null && (jSONObjectOptJSONObject = jSONObjectA.optJSONObject(bVar.getFormat())) != null) {
            if (gVarI == null) {
                gVarI = com.my.target.core.models.sections.g.i();
            }
            fm.k();
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(DownloadManager.SETTINGS);
            if (jSONObjectOptJSONObject2 != null) {
                for (am<VideoData> amVar : gVarI.j()) {
                    JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject2.optJSONObject(amVar.getName());
                    if (jSONObjectOptJSONObject3 != null) {
                        amVar.d(jSONObjectOptJSONObject3.optInt("connectionTimeout", amVar.P()));
                        int iOptInt = jSONObjectOptJSONObject3.optInt("maxBannersShow", amVar.Q());
                        if (iOptInt == 0) {
                            iOptInt = -1;
                        }
                        amVar.e(iOptInt);
                    }
                }
            }
            bd bdVarA = bd.a(afVar, bVar, context);
            JSONObject jSONObjectOptJSONObject4 = jSONObjectOptJSONObject.optJSONObject("sections");
            if (jSONObjectOptJSONObject4 != null) {
                String strH2 = afVar.H();
                if (strH2 != null) {
                    am<VideoData> amVarA2 = gVarI.a(strH2);
                    if (amVarA2 != null) {
                        a(jSONObjectOptJSONObject4, bdVarA, amVarA2, bf.c(afVar, bVar, context), afVar);
                    }
                } else {
                    Iterator<am<VideoData>> it = gVarI.j().iterator();
                    while (it.hasNext()) {
                        a(jSONObjectOptJSONObject4, bdVarA, it.next(), bf.c(afVar, bVar, context), afVar);
                    }
                }
            }
        }
        return gVarI;
    }

    @NonNull
    public static d<com.my.target.core.models.sections.g> newParser() {
        return new fk();
    }

    private fk() {
    }

    private static void a(@NonNull JSONObject jSONObject, @NonNull bd bdVar, @NonNull am<VideoData> amVar, @NonNull bf bfVar, @NonNull af afVar) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(amVar.getName());
        if (jSONArrayOptJSONArray == null) {
            return;
        }
        int position = afVar.getPosition();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                if (!ai.a.dd.equals(jSONObjectOptJSONObject.optString("type"))) {
                    ak<VideoData> akVarNewVideoBanner = ak.newVideoBanner();
                    if (bfVar.a(jSONObjectOptJSONObject, akVarNewVideoBanner)) {
                        float point = afVar.getPoint();
                        if (point >= 0.0f) {
                            akVarNewVideoBanner.setPoint(point);
                        }
                        float pointP = afVar.getPointP();
                        if (pointP >= 0.0f) {
                            akVarNewVideoBanner.setPointP(pointP);
                        }
                        if (position >= 0) {
                            amVar.a(akVarNewVideoBanner, position);
                            position++;
                        } else {
                            amVar.a(akVarNewVideoBanner);
                        }
                    }
                } else {
                    af afVarC = bdVar.c(jSONObjectOptJSONObject);
                    if (afVarC != null) {
                        afVarC.p(amVar.getName());
                        if (afVarC.A() != -1) {
                            arrayList.add(afVarC);
                        } else {
                            arrayList2.add(afVarC);
                            if (!afVarC.B() && !afVarC.z()) {
                                afVar.b(afVarC);
                                int position2 = afVar.getPosition();
                                if (position2 >= 0) {
                                    afVarC.setPosition(position2);
                                } else {
                                    afVarC.setPosition(amVar.getBannersCount());
                                }
                            }
                            amVar.c(afVarC);
                        }
                    }
                }
            }
        }
        a((ArrayList<af>) arrayList2, (ArrayList<af>) arrayList);
    }

    private static void a(@NonNull ArrayList<af> arrayList, @NonNull ArrayList<af> arrayList2) {
        for (af afVar : arrayList2) {
            for (af afVar2 : arrayList) {
                if (afVar.A() == afVar2.getId()) {
                    afVar2.a(afVar);
                    break;
                }
            }
        }
    }
}
