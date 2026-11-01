package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.VideoData;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: InterstitialAdResponseParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dq extends d<com.my.target.core.models.sections.c> {
    /* JADX WARN: Code duplicated, block: B:70:0x0162  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.my.target.d
    @Nullable
    public final /* synthetic */ al a(@NonNull String str, @NonNull af afVar, @Nullable al alVar, @NonNull b bVar, @NonNull Context context) {
        JSONObject jSONObjectOptJSONObject;
        JSONObject jSONObjectOptJSONObject2;
        com.my.target.core.models.banners.g gVar;
        com.my.target.core.models.banners.i iVarNewBanner;
        com.my.target.core.models.banners.g gVarNewBanner;
        com.my.target.core.models.banners.e eVarFromCompanion;
        com.my.target.core.models.sections.c cVarL = (com.my.target.core.models.sections.c) alVar;
        boolean zA = false;
        if (d.isVast(str)) {
            bi biVarA = bi.a(bVar, afVar, context);
            biVarA.G(str);
            if (!biVarA.at().isEmpty()) {
                if (cVarL == null) {
                    cVarL = com.my.target.core.models.sections.c.l();
                }
                ak<VideoData> akVar = (ak) biVarA.at().get(0);
                com.my.target.core.models.banners.i iVarNewBanner2 = com.my.target.core.models.banners.i.newBanner();
                iVarNewBanner2.setCtaText(akVar.getCtaText());
                iVarNewBanner2.setVideoBanner(akVar);
                iVarNewBanner2.setStyle(1);
                iVarNewBanner2.setTrackingLink(akVar.getTrackingLink());
                Iterator<aq> it = akVar.getStatHolder().x(aq.a.dF).iterator();
                while (it.hasNext()) {
                    iVarNewBanner2.getStatHolder().b(it.next());
                }
                cVarL.d(biVarA.F());
                cVarL.a(iVarNewBanner2);
                for (aj ajVar : akVar.getCompanionBanners()) {
                    if (ajVar.getHtmlResource() != null) {
                        eVarFromCompanion = com.my.target.core.models.banners.g.fromCompanion(ajVar);
                    } else {
                        eVarFromCompanion = ajVar.getStaticResource() != null ? com.my.target.core.models.banners.h.fromCompanion(ajVar) : null;
                    }
                    if (eVarFromCompanion != null) {
                        iVarNewBanner2.setEndCard(eVarFromCompanion);
                        break;
                    }
                }
            }
            cVarL = cVarL;
            return cVarL;
        }
        JSONObject jSONObjectA = a(str, context);
        if (jSONObjectA != null && (jSONObjectOptJSONObject = jSONObjectA.optJSONObject(bVar.getFormat())) != null) {
            if (cVarL == null) {
                cVarL = com.my.target.core.models.sections.c.l();
            }
            JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("banners");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0 && (jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(0)) != null) {
                String strOptString = jSONObjectOptJSONObject2.optString("type", "");
                dv dvVarA = dv.a(afVar, bVar, context);
                byte b = -1;
                switch (strOptString.hashCode()) {
                    case -1396342996:
                        if (strOptString.equals(ai.a.cW)) {
                            b = 2;
                        }
                        break;
                    case -974458767:
                        if (strOptString.equals(ai.a.dd)) {
                            b = 0;
                        }
                        break;
                    case 3213227:
                        if (strOptString.equals(ai.a.da)) {
                            b = 4;
                        }
                        break;
                    case 106940687:
                        if (strOptString.equals("promo")) {
                            b = 3;
                        }
                        break;
                    case 110066619:
                        if (strOptString.equals("fullscreen")) {
                            b = 1;
                        }
                        break;
                }
                if (b != 0) {
                    if (b == 1 || b == 2) {
                        com.my.target.core.models.banners.h hVarNewBanner = com.my.target.core.models.banners.h.newBanner();
                        zA = dvVarA.a(jSONObjectOptJSONObject2, hVarNewBanner);
                        gVar = hVarNewBanner;
                    } else if (b == 3) {
                        iVarNewBanner = com.my.target.core.models.banners.i.newBanner();
                        zA = dvVarA.a(jSONObjectOptJSONObject2, iVarNewBanner, jSONObjectA.optString("mraid.js"));
                    } else if (b == 4) {
                        gVarNewBanner = com.my.target.core.models.banners.g.newBanner();
                        zA = dvVarA.a(jSONObjectOptJSONObject2, gVarNewBanner, jSONObjectA.optString("mraid.js"));
                    }
                    if (zA) {
                        gVar = iVarNewBanner;
                        gVar = gVarNewBanner;
                        cVarL.a(gVar);
                    }
                } else {
                    af afVarC = bd.a(afVar, bVar, context).c(jSONObjectOptJSONObject2);
                    if (afVarC != null) {
                        afVar.b(afVarC);
                    }
                }
                gVar = null;
                if (zA) {
                    gVar = iVarNewBanner;
                    gVar = gVarNewBanner;
                    cVarL.a(gVar);
                }
            }
        }
        gVar = iVarNewBanner;
        gVar = gVarNewBanner;
        cVarL = cVarL;
        return cVarL;
    }

    @NonNull
    public static d<com.my.target.core.models.sections.c> newParser() {
        return new dq();
    }

    private dq() {
    }
}
