package com.facebook.ads.redexgen.X;

import com.facebook.ads.AdSize;
import com.facebook.ads.internal.api.AdSizeApi;
import com.tonyodev.fetch.FetchService;
import java.io.Serializable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public enum KV implements Serializable, AdSizeApi {
    BANNER_320_50(FetchService.ACTION_LOGGING, 50),
    INTERSTITIAL(0, 0),
    BANNER_HEIGHT_50(-1, 50),
    BANNER_HEIGHT_90(-1, 90),
    RECTANGLE_HEIGHT_250(-1, 250);

    public static final long serialVersionUID = 42;
    private final int B;
    private final int C;

    KV(int mWidth, int mHeight) {
        this.C = mWidth;
        this.B = mHeight;
    }

    public final AdSize A() {
        return AdSize.fromWidthAndHeight(this.C, this.B);
    }

    public static KV B(AdSize adSize) {
        return D(adSize.getWidth(), adSize.getHeight());
    }

    public static AdSizeApi C(int sizeType) {
        switch (sizeType) {
            case 4:
                return BANNER_320_50;
            case 5:
                return BANNER_HEIGHT_50;
            case 6:
                return BANNER_HEIGHT_90;
            case 7:
                return RECTANGLE_HEIGHT_250;
            case 100:
                return INTERSTITIAL;
            default:
                throw new IllegalArgumentException("Unknown AdSize type.");
        }
    }

    private static KV D(int mWidth, int mHeight) {
        if (INTERSTITIAL.B == mHeight && INTERSTITIAL.C == mWidth) {
            return INTERSTITIAL;
        }
        if (BANNER_320_50.B == mHeight && BANNER_320_50.C == mWidth) {
            return BANNER_320_50;
        }
        if (BANNER_HEIGHT_50.B == mHeight && BANNER_HEIGHT_50.C == mWidth) {
            return BANNER_HEIGHT_50;
        }
        if (BANNER_HEIGHT_90.B == mHeight && BANNER_HEIGHT_90.C == mWidth) {
            return BANNER_HEIGHT_90;
        }
        if (RECTANGLE_HEIGHT_250.B == mHeight && RECTANGLE_HEIGHT_250.C == mWidth) {
            return RECTANGLE_HEIGHT_250;
        }
        return null;
    }

    @Override // com.facebook.ads.internal.api.AdSizeApi
    public final int getHeight() {
        return this.B;
    }

    @Override // com.facebook.ads.internal.api.AdSizeApi
    public final int getWidth() {
        return this.C;
    }
}
