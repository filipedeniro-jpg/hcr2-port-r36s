package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.protocol.AdErrorType;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class KQ {
    private final AdErrorType B;
    private final String C;

    public KQ(int errorCode, String errorMessage) {
        this(AdErrorType.adErrorTypeFromCode(errorCode), errorMessage);
    }

    public KQ(AdErrorType adErrorType, @Nullable String errorMessage) {
        errorMessage = TextUtils.isEmpty(errorMessage) ? adErrorType.getDefaultErrorMessage() : errorMessage;
        this.B = adErrorType;
        this.C = errorMessage;
    }

    public final AdErrorType A() {
        return this.B;
    }

    public static KQ B(AdErrorType mAdErrorType) {
        return new KQ(mAdErrorType, (String) null);
    }

    public final String B() {
        return this.C;
    }

    public static KQ C(AdErrorType mAdErrorType, @Nullable String mErrorMessage) {
        return new KQ(mAdErrorType, mErrorMessage);
    }

    public static KQ D(KR adException) {
        return new KQ(adException.A(), adException.B());
    }

    public static AdError E(KQ wrapper) {
        if (wrapper.A().isPublicError()) {
            return new AdError(wrapper.A().getErrorCode(), wrapper.B());
        }
        return new AdError(AdErrorType.UNKNOWN_ERROR.getErrorCode(), AdErrorType.UNKNOWN_ERROR.getDefaultErrorMessage());
    }
}
