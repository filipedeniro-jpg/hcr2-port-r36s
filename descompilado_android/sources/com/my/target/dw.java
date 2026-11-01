package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.ImageData;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: InterstitialAdImageBannerParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dw {

    @NonNull
    private final b adConfig;

    @NonNull
    private final Context context;

    @NonNull
    private final af v;

    @NonNull
    public static dw b(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        return new dw(afVar, bVar, context);
    }

    private dw(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        this.v = afVar;
        this.adConfig = bVar;
        this.context = context;
    }

    public final boolean b(@NonNull JSONObject jSONObject, @NonNull com.my.target.core.models.banners.h hVar) {
        ImageData imageDataA;
        ImageData imageDataA2;
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("portrait");
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("landscape");
        if ((jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) && (jSONArrayOptJSONArray2 == null || jSONArrayOptJSONArray2.length() <= 0)) {
            a("No images in InterstitialAdImageBanner", az.a.ej, hVar.getId());
            return false;
        }
        if (jSONArrayOptJSONArray != null) {
            int length = jSONArrayOptJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null && (imageDataA2 = a(jSONObjectOptJSONObject, hVar.getId())) != null) {
                    hVar.addPortraitImage(imageDataA2);
                }
            }
        }
        if (jSONArrayOptJSONArray2 != null) {
            int length2 = jSONArrayOptJSONArray2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray2.optJSONObject(i2);
                if (jSONObjectOptJSONObject2 != null && (imageDataA = a(jSONObjectOptJSONObject2, hVar.getId())) != null) {
                    hVar.addLandscapeImage(imageDataA);
                }
            }
        }
        return (hVar.getLandscapeImages().isEmpty() && hVar.getPortraitImages().isEmpty()) ? false : true;
    }

    @Nullable
    private ImageData a(@NonNull JSONObject jSONObject, @NonNull String str) {
        String strOptString = jSONObject.optString(be.a.fh);
        if (TextUtils.isEmpty(strOptString)) {
            a("InterstitialAdImageBanner no imageLink for image", az.a.ej, str);
            return null;
        }
        int iOptInt = jSONObject.optInt("width");
        int iOptInt2 = jSONObject.optInt("height");
        if (iOptInt <= 0 || iOptInt2 <= 0) {
            a("InterstitialAdImageBanner  image has wrong dimensions, w = " + iOptInt + ", h = " + iOptInt2, az.a.ej, str);
            return null;
        }
        return ImageData.newImageData(strOptString, iOptInt, iOptInt2);
    }

    private void a(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        az.z(str2).A(str).C(str3).g(this.adConfig.getSlotId()).B(this.v.getUrl()).e(this.context);
    }
}
