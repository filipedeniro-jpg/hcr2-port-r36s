package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.ironsource.sdk.constants.Constants;
import com.my.target.common.models.ImageData;
import org.json.JSONObject;

/* JADX INFO: compiled from: StandardAdBannerParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class di {

    @NonNull
    private final af S;

    @NonNull
    private final be T;

    @NonNull
    private final b adConfig;

    @NonNull
    private final Context context;

    @NonNull
    public static di a(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        return new di(afVar, bVar, context);
    }

    private di(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        this.S = afVar;
        this.adConfig = bVar;
        this.context = context;
        this.T = be.b(afVar, bVar, context);
    }

    /* JADX WARN: Code duplicated, block: B:50:0x0253  */
    public final boolean a(@NonNull JSONObject jSONObject, @NonNull com.my.target.core.models.banners.c cVar, @Nullable String str) {
        String strF;
        this.T.a(jSONObject, cVar);
        if (jSONObject.has("timeout")) {
            int iOptInt = jSONObject.optInt("timeout");
            if (iOptInt >= 5) {
                cVar.setTimeout(iOptInt);
            } else {
                az.z(az.a.ej).A("Wrong banner timeout: ".concat(String.valueOf(iOptInt))).g(this.adConfig.getSlotId()).C(cVar.getId()).B(this.S.getUrl()).e(this.context);
            }
        }
        String type = cVar.getType();
        cVar.getViewSettings().b(this.adConfig.getFormat());
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(Constants.ParametersKeys.VIEW);
        if (jSONObjectOptJSONObject != null) {
            com.my.target.core.models.banners.d viewSettings = cVar.getViewSettings();
            viewSettings.a(jSONObjectOptJSONObject.optString("type", viewSettings.l()));
            viewSettings.setBackgroundColor(bg.a(jSONObjectOptJSONObject, TtmlNode.ATTR_TTS_BACKGROUND_COLOR, viewSettings.getBackgroundColor()));
            viewSettings.a(bg.a(jSONObjectOptJSONObject, "backgroundTouchColor", viewSettings.v()));
            viewSettings.setTitleColor(bg.a(jSONObjectOptJSONObject, "titleColor", this.adConfig.getFormat().equals(b.a.b) ? ViewCompat.MEASURED_STATE_MASK : -16755546));
            viewSettings.b(bg.a(jSONObjectOptJSONObject, "ageRestrictionsBackgroundColor", viewSettings.w()));
            viewSettings.c(bg.a(jSONObjectOptJSONObject, "ageRestrictionsTextColor", viewSettings.x()));
            viewSettings.d(bg.a(jSONObjectOptJSONObject, "ageRestrictionsBorderColor", viewSettings.y()));
            viewSettings.e(bg.a(jSONObjectOptJSONObject, "descriptionColor", viewSettings.z()));
            viewSettings.f(bg.a(jSONObjectOptJSONObject, "domainColor", viewSettings.A()));
            viewSettings.g(bg.a(jSONObjectOptJSONObject, "votesColor", viewSettings.B()));
            viewSettings.h(bg.a(jSONObjectOptJSONObject, "disclaimerColor", viewSettings.C()));
            viewSettings.setCtaButtonColor(bg.a(jSONObjectOptJSONObject, "ctaButtonColor", viewSettings.getCtaButtonColor()));
            viewSettings.setCtaButtonTouchColor(bg.a(jSONObjectOptJSONObject, "ctaButtonTouchColor", viewSettings.getCtaButtonTouchColor()));
            viewSettings.setCtaButtonTextColor(bg.a(jSONObjectOptJSONObject, "ctaButtonTextColor", viewSettings.getCtaButtonTextColor()));
            viewSettings.e(jSONObjectOptJSONObject.optBoolean("titleBold", viewSettings.m()));
            viewSettings.f(jSONObjectOptJSONObject.optBoolean("descriptionBold", viewSettings.n()));
            viewSettings.g(jSONObjectOptJSONObject.optBoolean("domainBold", viewSettings.o()));
            viewSettings.h(jSONObjectOptJSONObject.optBoolean("votesBold", viewSettings.s()));
            viewSettings.j(jSONObjectOptJSONObject.optBoolean("disclaimerBold", viewSettings.t()));
            viewSettings.k(jSONObjectOptJSONObject.optBoolean("ctaButtonTextBold", viewSettings.u()));
        }
        byte b = -1;
        int iHashCode = type.hashCode();
        if (iHashCode != -1396342996) {
            if (iHashCode != -877706672) {
                if (iHashCode == 3213227 && type.equals(ai.a.da)) {
                    b = 1;
                }
            } else if (type.equals(ai.a.cV)) {
                b = 0;
            }
        } else if (type.equals(ai.a.cW)) {
            b = 2;
        }
        if (b == 0) {
            cVar.setIcon(cVar.getImage());
            cVar.setImage(null);
            String strOptString = jSONObject.optString("mainImageLink", "");
            int iOptInt2 = jSONObject.optInt("mainImageWidth", 0);
            int iOptInt3 = jSONObject.optInt("mainImageHeight", 0);
            if (!TextUtils.isEmpty(strOptString)) {
                cVar.setImage(ImageData.newImageData(strOptString, iOptInt2, iOptInt3));
            }
        } else if (b == 1) {
            String strOptString2 = jSONObject.optString("source", null);
            if (strOptString2 == null) {
                az.z(az.a.ej).A("Banner with type 'html' has no source field").C(cVar.getId()).B(this.S.getUrl()).g(this.adConfig.getSlotId()).e(this.context);
                return false;
            }
            String strDecode = cl.decode(strOptString2);
            if (TextUtils.isEmpty(str)) {
                strF = strDecode;
            } else {
                cVar.setMraidJs(str);
                strF = be.f(str, strDecode);
                if (strF != null) {
                    cVar.setMraidSource(strF);
                    cVar.setType("mraid");
                } else {
                    strF = strDecode;
                }
            }
            if (!this.T.a(strF, jSONObject)) {
                return false;
            }
        } else if (b == 2) {
            if (cVar.getImage() == null) {
                az.z(az.a.ej).A("Banner with type 'banner' has no image").C(cVar.getId()).B(this.S.getUrl()).g(this.adConfig.getSlotId()).e(this.context);
                return false;
            }
        } else {
            g.a("Unknown banner type: '" + type + "'");
            return false;
        }
        return true;
    }
}
