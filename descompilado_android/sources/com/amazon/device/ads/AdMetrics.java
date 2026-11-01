package com.amazon.device.ads;

import com.appsflyer.share.Constants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class AdMetrics {
    public static final String LOGTAG = "AdMetrics";
    private MetricsCollector globalMetrics;
    private final MobileAdsLogger logger;
    private final MobileAdsInfoStore mobileAdsInfoStore;
    private final Metrics.MetricsSubmitter submitter;
    private final WebRequest.WebRequestFactory webRequestFactory;

    public AdMetrics(Metrics.MetricsSubmitter metricsSubmitter) {
        this(metricsSubmitter, MobileAdsInfoStore.getInstance());
    }

    AdMetrics(Metrics.MetricsSubmitter metricsSubmitter, MobileAdsInfoStore mobileAdsInfoStore) {
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        this.webRequestFactory = new WebRequest.WebRequestFactory();
        this.submitter = metricsSubmitter;
        this.mobileAdsInfoStore = mobileAdsInfoStore;
    }

    private String getAaxUrlAndResetAdMetrics() {
        String str = this.submitter.getInstrumentationPixelUrl() + WebUtils.getURLEncodedString(getAaxJson());
        this.submitter.resetMetricsCollector();
        return str;
    }

    public WebRequest getAaxWebRequestAndResetAdMetrics() {
        WebRequest webRequestCreateWebRequest = this.webRequestFactory.createWebRequest();
        webRequestCreateWebRequest.setUrlString(getAaxUrlAndResetAdMetrics());
        return webRequestCreateWebRequest;
    }

    public boolean canSubmit() {
        String instrumentationPixelUrl = this.submitter.getInstrumentationPixelUrl();
        if (instrumentationPixelUrl == null || instrumentationPixelUrl.equals("")) {
            return false;
        }
        if (this.mobileAdsInfoStore.getRegistrationInfo().getAppKey() != null) {
            return true;
        }
        this.logger.d("Not submitting metrics because the AppKey is not set.");
        return false;
    }

    public void addGlobalMetrics(MetricsCollector metricsCollector) {
        this.globalMetrics = metricsCollector;
    }

    protected String getAaxJson() {
        JSONObject jSONObject = new JSONObject();
        JSONUtils.put(jSONObject, Constants.URL_CAMPAIGN, "msdk");
        JSONUtils.put(jSONObject, "v", Version.getRawSDKVersion());
        addMetricsToJSON(jSONObject, this.submitter.getMetricsCollector());
        addMetricsToJSON(jSONObject, this.globalMetrics);
        String string = jSONObject.toString();
        return string.substring(1, string.length() - 1);
    }

    protected static void addMetricsToJSON(JSONObject jSONObject, MetricsCollector metricsCollector) {
        if (metricsCollector == null) {
            return;
        }
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        String adTypeMetricTag = metricsCollector.getAdTypeMetricTag();
        if (adTypeMetricTag != null) {
            adTypeMetricTag = adTypeMetricTag + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR;
        }
        for (MetricsCollector.MetricHit metricHit : (MetricsCollector.MetricHit[]) metricsCollector.getMetricHits().toArray(new MetricsCollector.MetricHit[metricsCollector.getMetricHits().size()])) {
            String aaxName = metricHit.metric.getAaxName();
            if (adTypeMetricTag != null && metricHit.metric.isAdTypeSpecific()) {
                aaxName = adTypeMetricTag + aaxName;
            }
            if (metricHit instanceof MetricsCollector.MetricHitStartTime) {
                map.put(metricHit.metric, Long.valueOf(((MetricsCollector.MetricHitStartTime) metricHit).startTime));
            } else if (metricHit instanceof MetricsCollector.MetricHitStopTime) {
                MetricsCollector.MetricHitStopTime metricHitStopTime = (MetricsCollector.MetricHitStopTime) metricHit;
                Long l = (Long) map.remove(metricHit.metric);
                if (l != null) {
                    JSONUtils.put(jSONObject, aaxName, (JSONUtils.getLongFromJSON(jSONObject, aaxName, 0L) + metricHitStopTime.stopTime) - l.longValue());
                }
            } else if (metricHit instanceof MetricsCollector.MetricHitTotalTime) {
                JSONUtils.put(jSONObject, aaxName, ((MetricsCollector.MetricHitTotalTime) metricHit).totalTime);
            } else if (metricHit instanceof MetricsCollector.MetricHitIncrement) {
                MetricsCollector.MetricHitIncrement metricHitIncrement = (MetricsCollector.MetricHitIncrement) metricHit;
                Integer num = (Integer) map2.get(metricHit.metric);
                map2.put(metricHit.metric, Integer.valueOf(num == null ? metricHitIncrement.increment : metricHitIncrement.increment + num.intValue()));
            } else if (metricHit instanceof MetricsCollector.MetricHitString) {
                JSONUtils.put(jSONObject, aaxName, ((MetricsCollector.MetricHitString) metricHit).text);
            }
        }
        for (Map.Entry entry : map2.entrySet()) {
            String aaxName2 = ((Metrics.MetricType) entry.getKey()).getAaxName();
            if (adTypeMetricTag != null && ((Metrics.MetricType) entry.getKey()).isAdTypeSpecific()) {
                aaxName2 = adTypeMetricTag + aaxName2;
            }
            JSONUtils.put(jSONObject, aaxName2, ((Integer) entry.getValue()).intValue());
        }
    }
}
