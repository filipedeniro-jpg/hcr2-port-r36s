package com.amazon.device.ads;

import java.io.InputStream;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class ResponseReader {
    private static final String LOGTAG = "ResponseReader";
    private final InputStream stream;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    private boolean enableLog = false;

    ResponseReader(InputStream inputStream) {
        this.stream = inputStream;
    }

    public InputStream getInputStream() {
        return this.stream;
    }

    public void enableLog(boolean z) {
        this.enableLog = z;
    }

    public void setExternalLogTag(String str) {
        if (str == null) {
            this.logger.withLogTag(LOGTAG);
            return;
        }
        this.logger.withLogTag(LOGTAG + " " + str);
    }

    public String readAsString() {
        String stringFromInputStream = StringUtils.readStringFromInputStream(this.stream);
        if (this.enableLog) {
            this.logger.d("Response Body: %s", stringFromInputStream);
        }
        return stringFromInputStream;
    }

    public JSONObject readAsJSON() {
        return JSONUtils.getJSONObjectFromString(readAsString());
    }
}
