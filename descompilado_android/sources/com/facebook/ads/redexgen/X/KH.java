package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.api.NativeAdRatingApi;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class KH implements NativeAdRatingApi {
    private final double B;
    private final double C;

    private KH(double mValue, double mScale) {
        this.C = mValue;
        this.B = mScale;
    }

    @Nullable
    public static KH B(JSONObject dataObject) {
        if (dataObject == null) {
            return null;
        }
        double dOptDouble = dataObject.optDouble("value", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        double dOptDouble2 = dataObject.optDouble("scale", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        if (dOptDouble == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || dOptDouble2 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return null;
        }
        return new KH(dOptDouble, dOptDouble2);
    }

    @Override // com.facebook.ads.internal.api.NativeAdRatingApi
    public final double getScale() {
        return this.B;
    }

    @Override // com.facebook.ads.internal.api.NativeAdRatingApi
    public final double getValue() {
        return this.C;
    }
}
