package net.nend.android.internal.utilities.video;

import com.ironsource.mediationsdk.logger.IronSourceError;

/* JADX INFO: loaded from: classes2.dex */
public enum NendVideoAdClientError {
    FAILED_INTERNAL(600, "Internal error at nendSDK Video process."),
    FAILED_AD_DOWNLOAD(601, "Failed to Ad download."),
    FAILED_AD_FALLBACK(IronSourceError.ERROR_BN_INIT_FAILED_AFTER_LOAD, "Failed to fallback fullboard ad."),
    NETWORK_IS_NOT_ACTIVE(IronSourceError.ERROR_BN_LOAD_WHILE_LONG_INITIATION, "Network is not active."),
    INVALID_AD_DATA(IronSourceError.ERROR_BN_LOAD_PLACEMENT_CAPPED, "ad data is not found or ad is expired.");

    private final int a;
    private final String b;

    NendVideoAdClientError(int i, String str) {
        this.a = i;
        this.b = str;
    }

    public String getMessage() {
        return this.b;
    }

    public int getCode() {
        return this.a;
    }
}
