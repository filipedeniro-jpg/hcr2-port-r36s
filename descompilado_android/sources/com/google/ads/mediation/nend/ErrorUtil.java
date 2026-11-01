package com.google.ads.mediation.nend;

/* JADX INFO: loaded from: classes.dex */
class ErrorUtil {
    private static final int NEND_SDK_NETWORK_ERROR_CODE = 603;

    static int convertErrorCodeFromNendVideoToAdMob(int i) {
        if (i == 204) {
            return 3;
        }
        if (i != 400) {
            return i != 603 ? 0 : 2;
        }
        return 1;
    }

    ErrorUtil() {
    }
}
