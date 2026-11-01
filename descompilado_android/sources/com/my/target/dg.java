package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ironsource.sdk.precache.DownloadManager;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: StandardAdResponseParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dg extends d<com.my.target.core.models.sections.b> {
    @Override // com.my.target.d
    @Nullable
    public final /* synthetic */ al a(@NonNull String str, @NonNull af afVar, @Nullable al alVar, @NonNull b bVar, @NonNull Context context) {
        JSONObject jSONObjectOptJSONObject;
        JSONArray jSONArrayOptJSONArray;
        JSONObject jSONObjectOptJSONObject2;
        com.my.target.core.models.sections.b bVarD = (com.my.target.core.models.sections.b) alVar;
        JSONObject jSONObjectA = a(str, context);
        if (jSONObjectA != null && (jSONObjectOptJSONObject = jSONObjectA.optJSONObject(bVar.getFormat())) != null && (jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("banners")) != null && jSONArrayOptJSONArray.length() > 0) {
            if (bVarD == null) {
                bVarD = com.my.target.core.models.sections.b.D();
            }
            dj.H();
            JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject.optJSONObject(DownloadManager.SETTINGS);
            if (jSONObjectOptJSONObject3 != null) {
                bVarD.n(jSONObjectOptJSONObject3.optBoolean("hasAdditionalAds", bVarD.E()));
            }
            di diVarA = di.a(afVar, bVar, context);
            if (jSONArrayOptJSONArray.length() > 0 && (jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(0)) != null) {
                com.my.target.core.models.banners.c cVarNewBanner = com.my.target.core.models.banners.c.newBanner();
                boolean zA = diVarA.a(jSONObjectOptJSONObject2, cVarNewBanner, jSONObjectA.optString("mraid.js"));
                if (ai.a.da.equals(cVarNewBanner.getViewSettings().l())) {
                    if (jSONObjectA.has("html_wrapper")) {
                        bVarD.c(jSONObjectA.optString("html_wrapper"));
                        jSONObjectA.remove("html_wrapper");
                        bVarD.setRawData(jSONObjectA);
                    } else {
                        az.z(az.a.ej).A("Section has no HTML_WRAPPER field, required for viewType = html").g(bVar.getSlotId()).B(afVar.getUrl()).e(context);
                        zA = false;
                    }
                }
                if (zA) {
                    bVarD.a(cVarNewBanner);
                    return bVarD;
                }
            }
        }
        return null;
    }

    @NonNull
    public static d<com.my.target.core.models.sections.b> newParser() {
        return new dg();
    }

    private dg() {
    }
}
