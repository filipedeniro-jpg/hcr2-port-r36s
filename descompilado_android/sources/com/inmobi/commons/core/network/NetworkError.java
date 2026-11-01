package com.inmobi.commons.core.network;

import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.utils.IronSourceConstants;

/* JADX INFO: loaded from: classes2.dex */
public final class NetworkError {
    public ErrorCode a;
    public String b;

    public enum ErrorCode {
        NETWORK_UNAVAILABLE_ERROR(0),
        UNKNOWN_ERROR(-1),
        NETWORK_IO_ERROR(-2),
        OUT_OF_MEMORY_ERROR(-3),
        INVALID_ENCRYPTED_RESPONSE_RECEIVED(-4),
        RESPONSE_EXCEEDS_SPECIFIED_SIZE_LIMIT(-5),
        GZIP_DECOMPRESSION_FAILED(-6),
        BAD_REQUEST(-7),
        GDPR_COMPLIANCE_ENFORCED(-8),
        HTTP_NO_CONTENT(204),
        HTTP_NOT_MODIFIED(304),
        HTTP_SEE_OTHER(303),
        HTTP_SERVER_NOT_FOUND(404),
        HTTP_MOVED_TEMP(IronSourceConstants.OFFERWALL_AVAILABLE),
        HTTP_INTERNAL_SERVER_ERROR(500),
        HTTP_NOT_IMPLEMENTED(IronSourceError.ERROR_CODE_NO_CONFIGURATION_AVAILABLE),
        HTTP_BAD_GATEWAY(IronSourceError.ERROR_CODE_USING_CACHED_CONFIGURATION),
        HTTP_SERVER_NOT_AVAILABLE(503),
        HTTP_GATEWAY_TIMEOUT(504),
        HTTP_VERSION_NOT_SUPPORTED(IronSourceError.ERROR_CODE_KEY_NOT_SET);

        private int a;

        ErrorCode(int i) {
            this.a = i;
        }

        public final int getValue() {
            return this.a;
        }

        public static ErrorCode fromValue(int i) {
            if (400 <= i && 500 > i) {
                return BAD_REQUEST;
            }
            for (ErrorCode errorCode : values()) {
                if (errorCode.a == i) {
                    return errorCode;
                }
            }
            return null;
        }
    }

    public NetworkError(ErrorCode errorCode, String str) {
        this.a = errorCode;
        this.b = str;
    }
}
