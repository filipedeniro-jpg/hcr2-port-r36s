package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.view.View;
import com.facebook.ads.NativeAdBase;

/* JADX INFO: loaded from: assets/audience_network.dex */
public enum MZ {
    INTERNAL_NO_TAG(0),
    INTERNAL_NO_CLICK(1),
    INTERNAL_API_TOO_LOW(2),
    INTERNAL_WRONG_TAG_CLASS(3),
    INTERNAL_NULL_VIEW(4),
    INTERNAL_AD_ICON(5, NativeAdBase.NativeComponentTag.AD_ICON),
    INTERNAL_AD_TITLE(6, NativeAdBase.NativeComponentTag.AD_TITLE),
    INTERNAL_AD_COVER_IMAGE(7, NativeAdBase.NativeComponentTag.AD_COVER_IMAGE),
    INTERNAL_AD_SUBTITLE(8, NativeAdBase.NativeComponentTag.AD_SUBTITLE),
    INTERNAL_AD_BODY(9, NativeAdBase.NativeComponentTag.AD_BODY),
    INTERNAL_AD_CALL_TO_ACTION(10, NativeAdBase.NativeComponentTag.AD_CALL_TO_ACTION),
    INTERNAL_AD_SOCIAL_CONTEXT(11, NativeAdBase.NativeComponentTag.AD_SOCIAL_CONTEXT),
    INTERNAL_AD_CHOICES_ICON(12, NativeAdBase.NativeComponentTag.AD_CHOICES_ICON),
    INTERNAL_AD_MEDIA(13, NativeAdBase.NativeComponentTag.AD_MEDIA),
    INTERNAL_AD_OPTIONS_VIEW(12, NativeAdBase.NativeComponentTag.AD_OPTIONS_VIEW);

    public static int E = -1593835521;
    private final int B;
    private final NativeAdBase.NativeComponentTag C;

    MZ(int code) {
        this.B = code;
        this.C = null;
    }

    MZ(int code, NativeAdBase.NativeComponentTag nativeComponentTag) {
        this.B = code;
        this.C = nativeComponentTag;
    }

    public final int A() {
        return this.B;
    }

    public static void B(View view, NativeAdBase.NativeComponentTag tag) {
        MZ mzD = D(tag);
        if (view != null && mzD != null && Build.VERSION.SDK_INT > 4) {
            view.setTag(E, tag);
        }
    }

    public static void C(View view, MZ internalTag) {
        if (view != null && internalTag != null && Build.VERSION.SDK_INT > 4) {
            view.setTag(E, internalTag);
        }
    }

    private static MZ D(NativeAdBase.NativeComponentTag tag) {
        for (MZ mz : values()) {
            if (mz.C == tag) {
                return mz;
            }
        }
        return null;
    }
}
