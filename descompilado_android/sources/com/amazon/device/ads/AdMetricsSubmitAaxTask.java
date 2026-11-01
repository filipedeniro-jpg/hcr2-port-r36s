package com.amazon.device.ads;

import android.os.AsyncTask;

/* JADX INFO: compiled from: AdMetricsTasks.java */
/* JADX INFO: loaded from: classes.dex */
class AdMetricsSubmitAaxTask extends AsyncTask<WebRequest, Void, Void> {
    private static final String LOGTAG = "AdMetricsSubmitAaxTask";
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    AdMetricsSubmitAaxTask() {
    }

    @Override // android.os.AsyncTask
    public Void doInBackground(WebRequest... webRequestArr) {
        for (WebRequest webRequest : webRequestArr) {
            try {
                webRequest.makeCall();
            } catch (WebRequest.WebRequestException e) {
                int i = AnonymousClass1.$SwitchMap$com$amazon$device$ads$WebRequest$WebRequestStatus[e.getStatus().ordinal()];
                if (i == 1) {
                    this.logger.e("Unable to submit metrics for ad due to an Invalid Client Protocol, msg: %s", e.getMessage());
                } else if (i == 2) {
                    this.logger.e("Unable to submit metrics for ad due to Network Failure, msg: %s", e.getMessage());
                } else {
                    if (i == 3) {
                        this.logger.e("Unable to submit metrics for ad due to a Malformed Pixel URL, msg: %s", e.getMessage());
                    } else if (i != 4) {
                    }
                    this.logger.e("Unable to submit metrics for ad because of unsupported character encoding, msg: %s", e.getMessage());
                }
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: com.amazon.device.ads.AdMetricsSubmitAaxTask$1, reason: invalid class name */
    /* JADX INFO: compiled from: AdMetricsTasks.java */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$WebRequest$WebRequestStatus = new int[WebRequest.WebRequestStatus.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$ads$WebRequest$WebRequestStatus[WebRequest.WebRequestStatus.INVALID_CLIENT_PROTOCOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$WebRequest$WebRequestStatus[WebRequest.WebRequestStatus.NETWORK_FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$WebRequest$WebRequestStatus[WebRequest.WebRequestStatus.MALFORMED_URL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$WebRequest$WebRequestStatus[WebRequest.WebRequestStatus.UNSUPPORTED_ENCODING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
