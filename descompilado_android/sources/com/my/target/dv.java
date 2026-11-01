package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.ImageData;
import com.my.target.common.models.VideoData;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: InterstitialAdBannerParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dv {

    @NonNull
    private final b adConfig;

    @NonNull
    private final Context context;

    @NonNull
    private final af v;

    @NonNull
    private final be w;

    @NonNull
    public static dv a(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        return new dv(afVar, bVar, context);
    }

    private dv(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        this.v = afVar;
        this.adConfig = bVar;
        this.context = context;
        this.w = be.b(afVar, bVar, context);
    }

    public final boolean a(@NonNull JSONObject jSONObject, @NonNull com.my.target.core.models.banners.h hVar) {
        a(jSONObject, (com.my.target.core.models.banners.e) hVar);
        return dw.b(this.v, this.adConfig, this.context).b(jSONObject, hVar);
    }

    public final boolean a(@NonNull JSONObject jSONObject, @NonNull com.my.target.core.models.banners.i iVar, @Nullable String str) {
        JSONObject jSONObjectOptJSONObject;
        a(jSONObject, iVar);
        iVar.setFooterColor(bg.a(jSONObject, "footerColor", iVar.getFooterColor()));
        iVar.setCtaButtonColor(bg.a(jSONObject, "ctaButtonColor", iVar.getCtaButtonColor()));
        iVar.setCtaButtonTouchColor(bg.a(jSONObject, "ctaButtonTouchColor", iVar.getCtaButtonTouchColor()));
        iVar.setCtaButtonTextColor(bg.a(jSONObject, "ctaButtonTextColor", iVar.getCtaButtonTextColor()));
        iVar.setStyle(jSONObject.optInt("style", iVar.getStyle()));
        iVar.setCloseOnClick(jSONObject.optBoolean("closeOnClick", iVar.isCloseOnClick()));
        String strOptString = jSONObject.optString("play_icon_hd");
        if (!TextUtils.isEmpty(strOptString)) {
            iVar.setPlayIcon(ImageData.newImageData(strOptString));
        }
        String strOptString2 = jSONObject.optString("store_icon_hd");
        if (!TextUtils.isEmpty(strOptString2)) {
            iVar.setStoreIcon(ImageData.newImageData(strOptString2));
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("cards");
        if (jSONArrayOptJSONArray != null) {
            int length = jSONArrayOptJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject2 != null) {
                    com.my.target.core.models.banners.f fVarNewCard = com.my.target.core.models.banners.f.newCard(iVar);
                    fVarNewCard.setClickArea(iVar.getClickArea());
                    this.w.a(jSONObjectOptJSONObject2, fVarNewCard);
                    com.my.target.core.models.banners.f fVar = null;
                    if (TextUtils.isEmpty(fVarNewCard.getTrackingLink())) {
                        a(az.a.ej, "no tracking link in interstitialAdCard", iVar.getId());
                    } else if (fVarNewCard.getImage() == null) {
                        a(az.a.ej, "no image in interstitialAdCard", iVar.getId());
                    } else {
                        fVarNewCard.setId(jSONObjectOptJSONObject2.optString("cardID", fVarNewCard.getId()));
                        fVar = fVarNewCard;
                    }
                    if (fVar != null) {
                        iVar.addInterstitialAdCard(fVar);
                    }
                }
            }
        }
        if (!iVar.getInterstitialAdCards().isEmpty() || (jSONObjectOptJSONObject = jSONObject.optJSONObject("video")) == null) {
            return true;
        }
        ak<VideoData> akVarNewVideoBanner = ak.newVideoBanner();
        akVarNewVideoBanner.setId(iVar.getId());
        if (bf.c(this.v, this.adConfig, this.context).a(jSONObjectOptJSONObject, akVarNewVideoBanner)) {
            iVar.setVideoBanner(akVarNewVideoBanner);
            if (akVarNewVideoBanner.isAutoPlay()) {
                iVar.setAllowClose(akVarNewVideoBanner.isAllowClose());
                iVar.setAllowCloseDelay(akVarNewVideoBanner.getAllowCloseDelay());
            }
        }
        JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("endcard");
        if (jSONObjectOptJSONObject3 == null) {
            return true;
        }
        com.my.target.core.models.banners.g gVarNewBanner = com.my.target.core.models.banners.g.newBanner();
        if (!a(jSONObjectOptJSONObject3, gVarNewBanner, str)) {
            return true;
        }
        iVar.setEndCard(gVarNewBanner);
        return true;
    }

    public final boolean a(@NonNull JSONObject jSONObject, @NonNull com.my.target.core.models.banners.g gVar, @Nullable String str) {
        String strF;
        String strOptString = jSONObject.optString("source", null);
        if (strOptString == null) {
            a(az.a.ej, "Banner with type 'html' has no source field", gVar.getId());
            return false;
        }
        String strDecode = cl.decode(strOptString);
        a(jSONObject, gVar);
        if (TextUtils.isEmpty(str) || (strF = be.f(str, strDecode)) == null) {
            strF = strDecode;
        } else {
            gVar.setType("mraid");
        }
        gVar.setSource(strF);
        return this.w.a(strF, jSONObject);
    }

    private void a(@NonNull JSONObject jSONObject, @NonNull com.my.target.core.models.banners.e eVar) {
        this.w.a(jSONObject, eVar);
        eVar.setAllowCloseDelay((float) jSONObject.optDouble("allowCloseDelay", eVar.getAllowCloseDelay()));
        String strOptString = jSONObject.optString("close_icon_hd");
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        eVar.setCloseIcon(ImageData.newImageData(strOptString));
    }

    private void a(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        az.z(str).A(str2).g(this.adConfig.getSlotId()).C(str3).B(this.v.getUrl()).e(this.context);
    }
}
