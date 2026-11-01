package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.api.NativeAdImageApi;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class KG implements NativeAdImageApi {
    private final int B;
    private final String C;
    private final int D;

    private KG(String mUrl, int mWidth, int mHeight) {
        this.C = mUrl;
        this.D = mWidth;
        this.B = mHeight;
    }

    @Nullable
    public static KG B(JSONObject dataObject) {
        String strOptString;
        if (dataObject == null || (strOptString = dataObject.optString("url")) == null) {
            return null;
        }
        return new KG(strOptString, dataObject.optInt("width", 0), dataObject.optInt("height", 0));
    }

    @Override // com.facebook.ads.internal.api.NativeAdImageApi
    public final int getHeight() {
        return this.B;
    }

    @Override // com.facebook.ads.internal.api.NativeAdImageApi
    public final String getUrl() {
        return this.C;
    }

    @Override // com.facebook.ads.internal.api.NativeAdImageApi
    public final int getWidth() {
        return this.D;
    }
}
