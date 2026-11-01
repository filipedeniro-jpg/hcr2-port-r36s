package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.my.target.common.models.ImageData;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeAppwallAdBannerParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fa {

    @NonNull
    private final be m;

    @NonNull
    private final com.my.target.core.models.sections.e section;

    public static fa a(@NonNull com.my.target.core.models.sections.e eVar, @NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        return new fa(eVar, afVar, bVar, context);
    }

    private fa(@NonNull com.my.target.core.models.sections.e eVar, @NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        this.section = eVar;
        this.m = be.b(afVar, bVar, context);
    }

    public final void a(@NonNull JSONObject jSONObject, @NonNull com.my.target.core.models.banners.j jVar) {
        this.m.a(jSONObject, jVar);
        jVar.setHasNotification(jSONObject.optBoolean("hasNotification", jVar.isHasNotification()));
        jVar.setBanner(jSONObject.optBoolean(IronSourceConstants.BANNER_AD_UNIT, jVar.isBanner()));
        jVar.setRequireCategoryHighlight(jSONObject.optBoolean("RequireCategoryHighlight", jVar.isRequireCategoryHighlight()));
        jVar.setItemHighlight(jSONObject.optBoolean("ItemHighlight", jVar.isItemHighlight()));
        jVar.setMain(jSONObject.optBoolean("Main", jVar.isMain()));
        jVar.setRequireWifi(jSONObject.optBoolean("RequireWifi", jVar.isRequireWifi()));
        jVar.setSubItem(jSONObject.optBoolean("subitem", jVar.isSubItem()));
        jVar.setBubbleId(jSONObject.optString("bubble_id", jVar.getBubbleId()));
        jVar.setLabelType(jSONObject.optString("labelType", jVar.getLabelType()));
        jVar.setStatus(jSONObject.optString("status", jVar.getStatus()));
        jVar.setPaidType(jSONObject.optString("paidType", jVar.getPaidType()));
        jVar.setMrgsId(jSONObject.optInt("mrgs_id"));
        jVar.setCoins(jSONObject.optInt("coins"));
        jVar.setCoinsIconBgColor(bg.a(jSONObject, "coins_icon_bgcolor", jVar.getCoinsIconBgColor()));
        jVar.setCoinsIconTextColor(bg.a(jSONObject, "coins_icon_textcolor", jVar.getCoinsIconTextColor()));
        String strOptString = jSONObject.optString("icon_hd");
        if (!TextUtils.isEmpty(strOptString)) {
            jVar.setIcon(ImageData.newImageData(strOptString));
        }
        String strOptString2 = jSONObject.optString("coins_icon_hd");
        if (!TextUtils.isEmpty(strOptString2)) {
            jVar.setCoinsIcon(ImageData.newImageData(strOptString2));
        }
        String strOptString3 = jSONObject.optString("cross_notif_icon_hd");
        if (!TextUtils.isEmpty(strOptString3)) {
            jVar.setCrossNotifIcon(ImageData.newImageData(strOptString3));
        }
        String strH = this.section.h();
        if (!TextUtils.isEmpty(strH)) {
            jVar.setBubbleIcon(ImageData.newImageData(strH));
        }
        String strJ = this.section.j();
        if (!TextUtils.isEmpty(strJ)) {
            jVar.setGotoAppIcon(ImageData.newImageData(strJ));
        }
        String strI = this.section.i();
        if (!TextUtils.isEmpty(strI)) {
            jVar.setLabelIcon(ImageData.newImageData(strI));
        }
        String status = jVar.getStatus();
        if (status != null) {
            String strG = this.section.g(status);
            if (!TextUtils.isEmpty(strG)) {
                jVar.setStatusIcon(ImageData.newImageData(strG));
            }
        }
        String strK = this.section.k();
        if (!jVar.isItemHighlight() || TextUtils.isEmpty(strK)) {
            return;
        }
        jVar.setItemHighlightIcon(ImageData.newImageData(strK));
    }
}
