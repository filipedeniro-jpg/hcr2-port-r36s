package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ironsource.sdk.precache.DownloadManager;
import com.my.target.common.models.AudioData;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: InstreamAudioAdResponseParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ff extends d<com.my.target.core.models.sections.f> {
    @Override // com.my.target.d
    @Nullable
    public final /* synthetic */ al a(@NonNull String str, @NonNull af afVar, @Nullable al alVar, @NonNull b bVar, @NonNull Context context) {
        JSONObject jSONObjectOptJSONObject;
        com.my.target.core.models.sections.f fVarH = (com.my.target.core.models.sections.f) alVar;
        if (isVast(str)) {
            bi biVarA = bi.a(bVar, afVar, context);
            biVarA.G(str);
            String strH = afVar.H();
            if (strH == null) {
                strH = am.a.dm;
            }
            if (fVarH == null) {
                fVarH = com.my.target.core.models.sections.f.h();
            }
            am<AudioData> amVarA = fVarH.a(strH);
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
                        Boolean boolK = afVar.K();
                        if (boolK != null) {
                            akVar.setAllowSeek(boolK.booleanValue());
                        }
                        Boolean boolL = afVar.L();
                        if (boolL != null) {
                            akVar.setAllowSkip(boolL.booleanValue());
                        }
                        Boolean boolM = afVar.M();
                        if (boolM != null) {
                            akVar.setAllowTrackChange(boolM.booleanValue());
                        }
                        akVar.setCloseActionText("Close");
                        float point = afVar.getPoint();
                        if (point >= 0.0f) {
                            akVar.setPoint(point);
                        }
                        float pointP = afVar.getPointP();
                        if (pointP >= 0.0f) {
                            akVar.setPointP(pointP);
                        }
                        if (position2 >= 0) {
                            amVarA.a(akVar, position2);
                            position2++;
                        } else {
                            amVarA.a(akVar);
                        }
                    }
                }
            }
            return fVarH;
        }
        JSONObject jSONObjectA = a(str, context);
        if (jSONObjectA != null && (jSONObjectOptJSONObject = jSONObjectA.optJSONObject(bVar.getFormat())) != null) {
            if (fVarH == null) {
                fVarH = com.my.target.core.models.sections.f.h();
            }
            fi.j();
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(DownloadManager.SETTINGS);
            if (jSONObjectOptJSONObject2 != null) {
                for (am<AudioData> amVar : fVarH.i()) {
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
                    am<AudioData> amVarA2 = fVarH.a(strH2);
                    if (amVarA2 != null) {
                        a(jSONObjectOptJSONObject4, bdVarA, amVarA2, fh.a(afVar, bVar, context), afVar);
                    }
                } else {
                    Iterator<am<AudioData>> it = fVarH.i().iterator();
                    while (it.hasNext()) {
                        a(jSONObjectOptJSONObject4, bdVarA, it.next(), fh.a(afVar, bVar, context), afVar);
                    }
                }
            }
        }
        return fVarH;
    }

    @NonNull
    public static d<com.my.target.core.models.sections.f> newParser() {
        return new ff();
    }

    private static void a(@NonNull JSONObject jSONObject, @NonNull bd bdVar, @NonNull am<AudioData> amVar, @NonNull fh fhVar, @NonNull af afVar) {
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
                    ak<AudioData> akVarNewAudioBanner = ak.newAudioBanner();
                    if (fhVar.a(jSONObjectOptJSONObject, akVarNewAudioBanner)) {
                        if (afVar.B()) {
                            akVarNewAudioBanner.setPoint(afVar.getPoint());
                            akVarNewAudioBanner.setPointP(afVar.getPointP());
                        }
                        if (position >= 0) {
                            amVar.a(akVarNewAudioBanner, position);
                            position++;
                        } else {
                            amVar.a(akVarNewAudioBanner);
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
