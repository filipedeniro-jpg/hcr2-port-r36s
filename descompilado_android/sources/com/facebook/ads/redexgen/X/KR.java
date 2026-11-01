package com.facebook.ads.redexgen.X;

import com.facebook.ads.internal.protocol.AdErrorType;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class KR extends Exception {
    private final AdErrorType B;
    private final String C;

    public KR(AdErrorType mAdErrorType, String mErrorMessage) {
        this(mAdErrorType, mErrorMessage, null);
    }

    public KR(AdErrorType mAdErrorType, String mErrorMessage, Throwable cause) {
        super(mErrorMessage, cause);
        this.B = mAdErrorType;
        this.C = mErrorMessage;
    }

    public final AdErrorType A() {
        return this.B;
    }

    public final String B() {
        return this.C;
    }
}
