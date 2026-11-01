package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeAdResponseParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cs extends d<com.my.target.core.models.sections.a> {
    @Override // com.my.target.d
    @Nullable
    public final /* synthetic */ al a(@NonNull String str, @NonNull af afVar, @Nullable al alVar, @NonNull b bVar, @NonNull Context context) {
        JSONObject jSONObjectOptJSONObject;
        JSONArray jSONArrayOptJSONArray;
        com.my.target.core.models.sections.a aVarH = (com.my.target.core.models.sections.a) alVar;
        JSONObject jSONObjectA = a(str, context);
        if (jSONObjectA != null && (jSONObjectOptJSONObject = jSONObjectA.optJSONObject(bVar.getFormat())) != null && (jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("banners")) != null && jSONArrayOptJSONArray.length() > 0) {
            if (aVarH == null) {
                aVarH = com.my.target.core.models.sections.a.H();
            }
            cv cvVarA = cv.a(afVar, bVar, context);
            int bannersCount = bVar.getBannersCount();
            if (bannersCount > 0) {
                int length = jSONArrayOptJSONArray.length();
                if (bannersCount > length) {
                    bannersCount = length;
                }
            } else {
                bannersCount = 1;
            }
            for (int i = 0; i < bannersCount; i++) {
                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject2 != null) {
                    com.my.target.core.models.banners.a aVarNewBanner = com.my.target.core.models.banners.a.newBanner();
                    cvVarA.a(jSONObjectOptJSONObject2, aVarNewBanner);
                    aVarH.a(aVarNewBanner);
                }
            }
            if (aVarH.getBannersCount() > 0) {
                return aVarH;
            }
        }
        return null;
    }

    @NonNull
    public static d<com.my.target.core.models.sections.a> newParser() {
        return new cs();
    }

    private cs() {
    }
}
