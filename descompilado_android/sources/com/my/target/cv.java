package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.VideoData;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeAdBannerParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cv {

    @NonNull
    private final af S;

    @NonNull
    private final be T;

    @Nullable
    private String U;

    @NonNull
    private final b adConfig;

    @NonNull
    private final Context context;

    @NonNull
    public static cv a(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        return new cv(afVar, bVar, context);
    }

    private cv(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        this.S = afVar;
        this.adConfig = bVar;
        this.context = context;
        this.T = be.b(afVar, bVar, context);
    }

    public final void a(@NonNull JSONObject jSONObject, @NonNull com.my.target.core.models.banners.a aVar) {
        JSONObject jSONObjectOptJSONObject;
        this.T.a(jSONObject, aVar);
        this.U = aVar.getId();
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("viewability");
        if (jSONObjectOptJSONObject2 != null) {
            if (jSONObjectOptJSONObject2.has("percent")) {
                int iOptInt = jSONObjectOptJSONObject2.optInt("percent");
                if (iOptInt >= 5 && iOptInt <= 100) {
                    aVar.setViewabilitySquare(iOptInt / 100.0f);
                } else {
                    e(az.a.ei, "invalid viewability percent ".concat(String.valueOf(iOptInt)));
                }
            }
            if (jSONObjectOptJSONObject2.has("rate")) {
                double dOptDouble = jSONObjectOptJSONObject2.optDouble("rate");
                if (dOptDouble >= 0.5d) {
                    aVar.setViewabilityRate((float) dOptDouble);
                } else {
                    e(az.a.ei, "invalid viewability rate ".concat(String.valueOf(dOptDouble)));
                }
            }
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("cards");
        if (jSONArrayOptJSONArray != null) {
            int length = jSONArrayOptJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObjectOptJSONObject3 = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject3 != null) {
                    com.my.target.core.models.banners.b bVarNewCard = com.my.target.core.models.banners.b.newCard(aVar);
                    this.T.a(jSONObjectOptJSONObject3, bVarNewCard);
                    com.my.target.core.models.banners.b bVar = null;
                    if (TextUtils.isEmpty(bVarNewCard.getTrackingLink())) {
                        e(az.a.ej, "no tracking link in nativeAdCard");
                    } else if (bVarNewCard.getImage() == null) {
                        e(az.a.ej, "no image in nativeAdCard");
                    } else {
                        bVarNewCard.setId(jSONObjectOptJSONObject3.optString("cardID", bVarNewCard.getId()));
                        bVar = bVarNewCard;
                    }
                    if (bVar != null) {
                        aVar.addNativeAdCard(bVar);
                    }
                }
            }
        }
        if (aVar.getNativeAdCards().isEmpty() && (jSONObjectOptJSONObject = jSONObject.optJSONObject("video")) != null && cg.bl()) {
            ak<VideoData> akVarNewVideoBanner = ak.newVideoBanner();
            akVarNewVideoBanner.setId(aVar.getId());
            if (bf.c(this.S, this.adConfig, this.context).a(jSONObjectOptJSONObject, akVarNewVideoBanner)) {
                aVar.setVideoBanner(akVarNewVideoBanner);
            }
        }
    }

    private void e(String str, String str2) {
        az.z(str).A(str2).g(this.adConfig.getSlotId()).C(this.U).B(this.S.getUrl()).e(this.context);
    }
}
