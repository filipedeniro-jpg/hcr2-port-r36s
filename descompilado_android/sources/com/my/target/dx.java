package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.my.target.common.models.ImageData;
import org.json.JSONObject;

/* JADX INFO: compiled from: InterstitialSliderAdBannerParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dx {

    @NonNull
    private final com.my.target.core.models.sections.d n;

    @NonNull
    private final be w;

    @NonNull
    private final dw x;

    @NonNull
    public static dx a(@NonNull com.my.target.core.models.sections.d dVar, @NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        return new dx(dVar, afVar, bVar, context);
    }

    private dx(@NonNull com.my.target.core.models.sections.d dVar, @NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        this.n = dVar;
        this.w = be.b(afVar, bVar, context);
        this.x = dw.b(afVar, bVar, context);
    }

    public final boolean a(@NonNull JSONObject jSONObject, @NonNull com.my.target.core.models.banners.h hVar) {
        this.w.a(jSONObject, hVar);
        hVar.setAllowClose(true);
        String strOptString = jSONObject.optString("close_icon_hd");
        if (!TextUtils.isEmpty(strOptString)) {
            hVar.setCloseIcon(ImageData.newImageData(strOptString));
        } else {
            hVar.setCloseIcon(this.n.getCloseIcon());
        }
        return this.x.b(jSONObject, hVar);
    }
}
