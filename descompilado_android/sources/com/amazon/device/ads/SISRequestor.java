package com.amazon.device.ads;

import androidx.core.app.NotificationCompat;
import com.appsflyer.share.Constants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class SISRequestor {
    protected static final String API_LEVEL_ENDPOINT = "/api3";
    private final Configuration configuration;
    private final SISRequestorCallback sisRequestorCallback;
    private final SISRequest[] sisRequests;
    private final WebRequest.WebRequestFactory webRequestFactory;

    public SISRequestor(SISRequestorCallback sISRequestorCallback, SISRequest... sISRequestArr) {
        this(new WebRequest.WebRequestFactory(), sISRequestorCallback, Configuration.getInstance(), sISRequestArr);
    }

    SISRequestor(WebRequest.WebRequestFactory webRequestFactory, SISRequestorCallback sISRequestorCallback, Configuration configuration, SISRequest... sISRequestArr) {
        this.webRequestFactory = webRequestFactory;
        this.sisRequestorCallback = sISRequestorCallback;
        this.configuration = configuration;
        this.sisRequests = sISRequestArr;
    }

    public void startCallSIS() {
        for (SISRequest sISRequest : this.sisRequests) {
            callSIS(sISRequest);
        }
        SISRequestorCallback sisRequestorCallback = getSisRequestorCallback();
        if (sisRequestorCallback != null) {
            sisRequestorCallback.onSISCallComplete();
        }
    }

    private void callSIS(SISRequest sISRequest) {
        try {
            JSONObject asJSON = getWebRequest(sISRequest).makeCall().getResponseReader().readAsJSON();
            if (asJSON == null) {
                return;
            }
            int integerFromJSON = JSONUtils.getIntegerFromJSON(asJSON, "rcode", 0);
            String stringFromJSON = JSONUtils.getStringFromJSON(asJSON, NotificationCompat.CATEGORY_MESSAGE, "");
            if (integerFromJSON == 1) {
                sISRequest.getLogger().i("Result - code: %d, msg: %s", Integer.valueOf(integerFromJSON), stringFromJSON);
                sISRequest.onResponseReceived(asJSON);
            } else {
                sISRequest.getLogger().w("Result - code: %d, msg: %s", Integer.valueOf(integerFromJSON), stringFromJSON);
            }
        } catch (WebRequest.WebRequestException unused) {
        }
    }

    private WebRequest getWebRequest(SISRequest sISRequest) {
        WebRequest webRequestCreateWebRequest = this.webRequestFactory.createWebRequest();
        webRequestCreateWebRequest.setExternalLogTag(sISRequest.getLogTag());
        webRequestCreateWebRequest.setHttpMethod(WebRequest.HttpMethod.POST);
        webRequestCreateWebRequest.setHost(getHostname());
        webRequestCreateWebRequest.setPath(getEndpoint(sISRequest));
        webRequestCreateWebRequest.enableLog(true);
        HashMap<String, String> postParameters = sISRequest.getPostParameters();
        if (postParameters != null) {
            for (Map.Entry<String, String> entry : postParameters.entrySet()) {
                webRequestCreateWebRequest.putPostParameter(entry.getKey(), entry.getValue());
            }
        }
        webRequestCreateWebRequest.setQueryStringParameters(sISRequest.getQueryParameters());
        webRequestCreateWebRequest.setMetricsCollector(Metrics.getInstance().getMetricsCollector());
        webRequestCreateWebRequest.setServiceCallLatencyMetric(sISRequest.getCallMetricType());
        return webRequestCreateWebRequest;
    }

    private String getHostname() {
        int iIndexOf;
        String string = this.configuration.getString(Configuration.ConfigOption.SIS_URL);
        return (string == null || (iIndexOf = string.indexOf(Constants.URL_PATH_DELIMITER)) <= -1) ? string : string.substring(0, iIndexOf);
    }

    private String getEndpoint(SISRequest sISRequest) {
        String string = this.configuration.getString(Configuration.ConfigOption.SIS_URL);
        if (string != null) {
            int iIndexOf = string.indexOf(Constants.URL_PATH_DELIMITER);
            string = iIndexOf > -1 ? string.substring(iIndexOf) : "";
        }
        return string + API_LEVEL_ENDPOINT + sISRequest.getPath();
    }

    private SISRequestorCallback getSisRequestorCallback() {
        return this.sisRequestorCallback;
    }

    static class SISRequestorFactory {
        SISRequestorFactory() {
        }

        public SISRequestor createSISRequestor(SISRequest... sISRequestArr) {
            return createSISRequestor(null, sISRequestArr);
        }

        public SISRequestor createSISRequestor(SISRequestorCallback sISRequestorCallback, SISRequest... sISRequestArr) {
            return new SISRequestor(sISRequestorCallback, sISRequestArr);
        }
    }
}
