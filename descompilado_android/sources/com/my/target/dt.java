package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.ironsource.sdk.precache.DownloadManager;
import com.my.target.common.models.ImageData;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: InterstitialSliderAdResponseParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dt extends d<com.my.target.core.models.sections.d> {
    @Override // com.my.target.d
    @Nullable
    public final /* synthetic */ al a(@NonNull String str, @NonNull af afVar, @Nullable al alVar, @NonNull b bVar, @NonNull Context context) {
        JSONObject jSONObjectOptJSONObject;
        com.my.target.core.models.sections.d dVarN = (com.my.target.core.models.sections.d) alVar;
        JSONObject jSONObjectA = a(str, context);
        if (jSONObjectA != null && (jSONObjectOptJSONObject = jSONObjectA.optJSONObject(bVar.getFormat())) != null) {
            if (dVarN == null) {
                dVarN = com.my.target.core.models.sections.d.n();
            }
            dy.t();
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(DownloadManager.SETTINGS);
            if (jSONObjectOptJSONObject2 != null) {
                String strOptString = jSONObjectOptJSONObject2.optString("close_icon_hd", "");
                if (!TextUtils.isEmpty(strOptString)) {
                    dVarN.setCloseIcon(ImageData.newImageData(strOptString));
                }
                dVarN.setBackgroundColor(bg.a(jSONObjectOptJSONObject2, TtmlNode.ATTR_TTS_BACKGROUND_COLOR, dVarN.getBackgroundColor()));
                dVarN.b(bg.a(jSONObjectOptJSONObject2, "markerColor", dVarN.s()));
                dVarN.a(bg.a(jSONObjectOptJSONObject2, "activeMarkerColor", dVarN.o()));
            }
            JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("banners");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                dx dxVarA = dx.a(dVarN, afVar, bVar, context);
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject3 = jSONArrayOptJSONArray.optJSONObject(i);
                    if (jSONObjectOptJSONObject3 != null) {
                        com.my.target.core.models.banners.h hVarNewBanner = com.my.target.core.models.banners.h.newBanner();
                        if (dxVarA.a(jSONObjectOptJSONObject3, hVarNewBanner)) {
                            dVarN.c(hVarNewBanner);
                        }
                    }
                }
                if (dVarN.getBannersCount() > 0) {
                    return dVarN;
                }
            }
        }
        return null;
    }

    @NonNull
    public static d<com.my.target.core.models.sections.d> newParser() {
        return new dt();
    }

    private dt() {
    }
}
