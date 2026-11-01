package com.amazon.device.ads;

import com.my.target.aa;
import com.my.target.ai;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import net.nend.android.NendAdNativeMediaView;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class AdLoader {
    public static final int AD_FAILED = -1;
    public static final int AD_LOAD_DEFERRED = 1;
    public static final int AD_READY_TO_LOAD = 0;
    public static final String DISABLED_APP_SERVER_MESSAGE = "DISABLED_APP";
    private static final String LOGTAG = "AdLoader";
    private final AdRequest adRequest;
    private final Assets assets;
    private MetricsCollector.CompositeMetricsCollector compositeMetricsCollector;
    private final DebugProperties debugProperties;
    private AdError error;
    private final MobileAdsInfoStore infoStore;
    private final MobileAdsLogger logger;
    private final Map<Integer, AdSlot> slots;
    private final SystemTime systemTime;
    private final ThreadUtils.ThreadRunner threadRunner;
    private int timeout;

    public AdLoader(AdRequest adRequest, Map<Integer, AdSlot> map) {
        this(adRequest, map, ThreadUtils.getThreadRunner(), new SystemTime(), Assets.getInstance(), MobileAdsInfoStore.getInstance(), new MobileAdsLoggerFactory(), DebugProperties.getInstance());
    }

    AdLoader(AdRequest adRequest, Map<Integer, AdSlot> map, ThreadUtils.ThreadRunner threadRunner, SystemTime systemTime, Assets assets, MobileAdsInfoStore mobileAdsInfoStore, MobileAdsLoggerFactory mobileAdsLoggerFactory, DebugProperties debugProperties) {
        this.timeout = 20000;
        this.error = null;
        this.compositeMetricsCollector = null;
        this.adRequest = adRequest;
        this.slots = map;
        this.threadRunner = threadRunner;
        this.systemTime = systemTime;
        this.assets = assets;
        this.infoStore = mobileAdsInfoStore;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.debugProperties = debugProperties;
    }

    public void setTimeout(int i) {
        this.timeout = i;
    }

    public void beginFetchAd() {
        getCompositeMetricsCollector().stopMetric(Metrics.MetricType.AD_LOAD_LATENCY_LOADAD_TO_FETCH_THREAD_REQUEST_START);
        getCompositeMetricsCollector().startMetric(Metrics.MetricType.AD_LOAD_LATENCY_FETCH_THREAD_SPIN_UP);
        startFetchAdThread();
    }

    protected void startFetchAdThread() {
        this.threadRunner.execute(new Runnable() { // from class: com.amazon.device.ads.AdLoader.1
            @Override // java.lang.Runnable
            public void run() {
                AdLoader.this.fetchAd();
                AdLoader.this.beginFinalizeFetchAd();
            }
        }, ThreadUtils.ExecutionStyle.SCHEDULE, ThreadUtils.ExecutionThread.BACKGROUND_THREAD);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void beginFinalizeFetchAd() {
        this.threadRunner.execute(new Runnable() { // from class: com.amazon.device.ads.AdLoader.2
            @Override // java.lang.Runnable
            public void run() {
                AdLoader.this.finalizeFetchAd();
            }
        }, ThreadUtils.ExecutionStyle.SCHEDULE, ThreadUtils.ExecutionThread.MAIN_THREAD);
    }

    protected void fetchAd() {
        getCompositeMetricsCollector().stopMetric(Metrics.MetricType.AD_LOAD_LATENCY_FETCH_THREAD_SPIN_UP);
        getCompositeMetricsCollector().startMetric(Metrics.MetricType.AD_LOAD_LATENCY_FETCH_THREAD_START_TO_AAX_GET_AD_START);
        if (!this.assets.ensureAssetsCreated()) {
            this.error = new AdError(AdError.ErrorCode.REQUEST_ERROR, "Unable to create the assets needed to display ads");
            this.logger.e("Unable to create the assets needed to display ads");
            setErrorForAllSlots(this.error);
            return;
        }
        try {
            WebRequest.WebResponse webResponseFetchResponseFromNetwork = fetchResponseFromNetwork();
            if (!webResponseFetchResponseFromNetwork.isHttpStatusCodeOK()) {
                String str = webResponseFetchResponseFromNetwork.getHttpStatusCode() + " - " + webResponseFetchResponseFromNetwork.getHttpStatus();
                this.error = new AdError(AdError.ErrorCode.NETWORK_ERROR, str);
                this.logger.e(str);
                setErrorForAllSlots(this.error);
                return;
            }
            JSONObject asJSON = webResponseFetchResponseFromNetwork.getResponseReader().readAsJSON();
            if (asJSON == null) {
                this.error = new AdError(AdError.ErrorCode.INTERNAL_ERROR, "Unable to parse response");
                this.logger.e("Unable to parse response");
                setErrorForAllSlots(this.error);
            } else {
                parseResponse(asJSON);
                getCompositeMetricsCollector().stopMetric(Metrics.MetricType.AD_LOAD_LATENCY_AAX_GET_AD_END_TO_FETCH_THREAD_END);
                getCompositeMetricsCollector().startMetric(Metrics.MetricType.AD_LOAD_LATENCY_FINALIZE_FETCH_SPIN_UP);
            }
        } catch (AdFetchException e) {
            this.error = e.getAdError();
            this.logger.e(e.getAdError().getMessage());
            setErrorForAllSlots(this.error);
        }
    }

    private WebRequest getAdRequest() throws AdFetchException {
        getCompositeMetricsCollector().startMetric(Metrics.MetricType.AD_LOAD_LATENCY_CREATE_AAX_GET_AD_URL);
        WebRequest webRequest = this.adRequest.getWebRequest();
        getCompositeMetricsCollector().stopMetric(Metrics.MetricType.AD_LOAD_LATENCY_CREATE_AAX_GET_AD_URL);
        return webRequest;
    }

    /* JADX WARN: Code duplicated, block: B:60:0x016c  */
    /* JADX WARN: Code duplicated, block: B:66:0x01b1  */
    private void parseResponse(JSONObject jSONObject) {
        AdError adError;
        String str;
        int i;
        int i2;
        boolean z;
        int i3;
        AdLoader adLoader = this;
        long jCurrentTimeMillis = adLoader.systemTime.currentTimeMillis();
        String str2 = null;
        String stringFromJSON = JSONUtils.getStringFromJSON(jSONObject, "status", null);
        HashSet<Integer> hashSet = new HashSet(adLoader.slots.keySet());
        AdError adError2 = getAdError(jSONObject);
        String stringFromJSON2 = JSONUtils.getStringFromJSON(jSONObject, "errorCode", "No Ad Received");
        String str3 = "instrPixelURL";
        adLoader.adRequest.setInstrumentationPixelURL(JSONUtils.getStringFromJSON(jSONObject, "instrPixelURL", null));
        if (stringFromJSON != null && stringFromJSON.equals("ok")) {
            JSONArray jSONArrayFromJSON = JSONUtils.getJSONArrayFromJSON(jSONObject, "ads");
            int i4 = 0;
            while (i4 < jSONArrayFromJSON.length()) {
                JSONObject jSONObjectFromJSONArray = JSONUtils.getJSONObjectFromJSONArray(jSONArrayFromJSON, i4);
                if (jSONObjectFromJSONArray == null) {
                    jSONArrayFromJSON = jSONArrayFromJSON;
                    adError = adError2;
                    str = stringFromJSON2;
                    str3 = str3;
                } else {
                    int integerFromJSON = JSONUtils.getIntegerFromJSON(jSONObjectFromJSONArray, "slotId", -1);
                    AdSlot adSlot = adLoader.slots.get(Integer.valueOf(integerFromJSON));
                    if (adSlot != null) {
                        hashSet.remove(Integer.valueOf(integerFromJSON));
                        String stringFromJSON3 = JSONUtils.getStringFromJSON(jSONObjectFromJSONArray, str3, adLoader.adRequest.getInstrumentationPixelURL());
                        AdData adData = new AdData();
                        adData.setInstrumentationPixelUrl(stringFromJSON3);
                        adData.setImpressionPixelUrl(JSONUtils.getStringFromJSON(jSONObjectFromJSONArray, "impPixelURL", str2));
                        if (adSlot.getRequestedAdSize().isAuto()) {
                            adSlot.getMetricsCollector().incrementMetric(Metrics.MetricType.AD_COUNTER_AUTO_AD_SIZE);
                        }
                        String stringFromJSON4 = JSONUtils.getStringFromJSON(jSONObjectFromJSONArray, ai.a.da, "");
                        JSONArray jSONArrayFromJSON2 = JSONUtils.getJSONArrayFromJSON(jSONObjectFromJSONArray, "creativeTypes");
                        HashSet hashSet2 = new HashSet();
                        if (jSONArrayFromJSON2 != null) {
                            int i5 = 0;
                            while (i5 < jSONArrayFromJSON2.length()) {
                                int integerFromJSONArray = JSONUtils.getIntegerFromJSONArray(jSONArrayFromJSON2, i5, 0);
                                AAXCreative creativeType = AAXCreative.getCreativeType(integerFromJSONArray);
                                if (creativeType != null) {
                                    hashSet2.add(creativeType);
                                } else {
                                    adLoader.logger.w("%d is not a recognized creative type.", Integer.valueOf(integerFromJSONArray));
                                }
                                i5++;
                                stringFromJSON2 = stringFromJSON2;
                                adError2 = adError2;
                            }
                            adError = adError2;
                            str = stringFromJSON2;
                        } else {
                            adError = adError2;
                            str = stringFromJSON2;
                        }
                        if (!AAXCreative.containsPrimaryCreativeType(hashSet2)) {
                            adSlot.setAdError(new AdError(AdError.ErrorCode.INTERNAL_ERROR, "No valid creative types found"));
                            adLoader.logger.e("No valid creative types found");
                        } else {
                            String stringFromJSON5 = JSONUtils.getStringFromJSON(jSONObjectFromJSONArray, "size", "");
                            if (stringFromJSON5 != null && ((stringFromJSON5.equals("9999x9999") || stringFromJSON5.equals(aa.e.bo)) && !hashSet2.contains(AAXCreative.INTERSTITIAL))) {
                                hashSet2.add(AAXCreative.INTERSTITIAL);
                            }
                            if (hashSet2.contains(AAXCreative.INTERSTITIAL)) {
                                i = 0;
                                i2 = 0;
                            } else {
                                String[] strArrSplit = stringFromJSON5 != null ? stringFromJSON5.split("x") : null;
                                if (strArrSplit == null || strArrSplit.length != 2) {
                                    z = true;
                                    i = 0;
                                } else {
                                    try {
                                        i3 = Integer.parseInt(strArrSplit[0]);
                                        try {
                                            i2 = Integer.parseInt(strArrSplit[1]);
                                            i = i3;
                                            z = false;
                                        } catch (NumberFormatException unused) {
                                            i = i3;
                                            z = true;
                                            i2 = 0;
                                        }
                                    } catch (NumberFormatException unused2) {
                                        i3 = 0;
                                    }
                                    if (z) {
                                        adSlot.setAdError(new AdError(AdError.ErrorCode.INTERNAL_ERROR, "Server returned an invalid ad size"));
                                        adLoader.logger.e("Server returned an invalid ad size");
                                    }
                                }
                                i2 = 0;
                                if (z) {
                                    adSlot.setAdError(new AdError(AdError.ErrorCode.INTERNAL_ERROR, "Server returned an invalid ad size"));
                                    adLoader.logger.e("Server returned an invalid ad size");
                                }
                            }
                            long longFromJSON = JSONUtils.getLongFromJSON(jSONObjectFromJSONArray, "cacheTTL", -1L);
                            if (longFromJSON > -1) {
                                adData.setExpirationTimeMillis(jCurrentTimeMillis + (longFromJSON * 1000));
                            }
                            AdProperties adProperties = new AdProperties(jSONArrayFromJSON2);
                            adData.setHeight(i2);
                            adData.setWidth(i);
                            adData.setCreative(stringFromJSON4);
                            adData.setCreativeTypes(hashSet2);
                            adData.setProperties(adProperties);
                            adData.setFetched(true);
                            adSlot.setAdData(adData);
                        }
                    } else {
                        jSONArrayFromJSON = jSONArrayFromJSON;
                        adError = adError2;
                        str = stringFromJSON2;
                        str3 = str3;
                    }
                }
                i4++;
                str2 = null;
                adLoader = this;
                jSONArrayFromJSON = jSONArrayFromJSON;
                str3 = str3;
                stringFromJSON2 = str;
                adError2 = adError;
            }
        }
        AdError adError3 = adError2;
        String str4 = stringFromJSON2;
        for (Integer num : hashSet) {
            this.slots.get(num).setAdError(adError3);
            AdData adData2 = new AdData();
            adData2.setInstrumentationPixelUrl(this.adRequest.getInstrumentationPixelURL());
            this.slots.get(num).setAdData(adData2);
            this.logger.w("%s; code: %s", adError3.getMessage(), str4);
        }
    }

    protected AdError getAdError(JSONObject jSONObject) {
        int iRetrieveNoRetryTtlSeconds = retrieveNoRetryTtlSeconds(jSONObject);
        this.infoStore.setNoRetryTtl(iRetrieveNoRetryTtlSeconds);
        String stringFromJSON = JSONUtils.getStringFromJSON(jSONObject, NendAdNativeMediaView.RESULT_ERROR_MESSAGE, "No Ad Received");
        this.infoStore.setIsAppDisabled(stringFromJSON.equalsIgnoreCase(DISABLED_APP_SERVER_MESSAGE));
        String str = "Server Message: " + stringFromJSON;
        if (iRetrieveNoRetryTtlSeconds > 0) {
            getCompositeMetricsCollector().publishMetricInMilliseconds(Metrics.MetricType.AD_NO_RETRY_TTL_RECEIVED, iRetrieveNoRetryTtlSeconds * 1000);
        }
        if (iRetrieveNoRetryTtlSeconds > 0 && !this.infoStore.getIsAppDisabled()) {
            return new AdError(AdError.ErrorCode.NO_FILL, str + ". Try again in " + iRetrieveNoRetryTtlSeconds + " seconds");
        }
        if (stringFromJSON.equals("no results")) {
            return new AdError(AdError.ErrorCode.NO_FILL, str);
        }
        return new AdError(AdError.ErrorCode.INTERNAL_ERROR, str);
    }

    private void setErrorForAllSlots(AdError adError) {
        Iterator<AdSlot> it = this.slots.values().iterator();
        while (it.hasNext()) {
            it.next().setAdError(adError);
        }
    }

    protected int retrieveNoRetryTtlSeconds(JSONObject jSONObject) {
        return this.debugProperties.getDebugPropertyAsInteger(DebugProperties.DEBUG_NORETRYTTL, Integer.valueOf(JSONUtils.getIntegerFromJSON(jSONObject, "noretryTTL", 0))).intValue();
    }

    protected void finalizeFetchAd() {
        Iterator<Map.Entry<Integer, AdSlot>> it = this.slots.entrySet().iterator();
        while (it.hasNext()) {
            AdSlot value = it.next().getValue();
            if (!value.canBeUsed()) {
                this.logger.w("Ad object was destroyed before ad fetching could be finalized. Ad fetching has been aborted.");
            } else {
                value.getMetricsCollector().stopMetric(Metrics.MetricType.AD_LOAD_LATENCY_FINALIZE_FETCH_SPIN_UP);
                if (!value.isFetched()) {
                    value.getMetricsCollector().startMetric(Metrics.MetricType.AD_LOAD_LATENCY_FINALIZE_FETCH_START_TO_FAILURE);
                    if (value.getAdError() != null) {
                        value.adFailed(value.getAdError());
                    } else {
                        value.adFailed(new AdError(AdError.ErrorCode.INTERNAL_ERROR, "Unknown error occurred."));
                    }
                } else {
                    value.getMetricsCollector().startMetric(Metrics.MetricType.AD_LOAD_LATENCY_FINALIZE_FETCH_START_TO_RENDER_START);
                    value.initializeAd();
                }
            }
        }
    }

    protected WebRequest.WebResponse fetchResponseFromNetwork() throws AdFetchException {
        AdError adError;
        WebRequest adRequest = getAdRequest();
        adRequest.setMetricsCollector(getCompositeMetricsCollector());
        adRequest.setServiceCallLatencyMetric(Metrics.MetricType.AAX_LATENCY_GET_AD);
        adRequest.setTimeout(this.timeout);
        adRequest.setDisconnectEnabled(false);
        getCompositeMetricsCollector().stopMetric(Metrics.MetricType.AD_LOAD_LATENCY_FETCH_THREAD_START_TO_AAX_GET_AD_START);
        getCompositeMetricsCollector().incrementMetric(Metrics.MetricType.TLS_ENABLED);
        try {
            WebRequest.WebResponse webResponseMakeCall = adRequest.makeCall();
            getCompositeMetricsCollector().startMetric(Metrics.MetricType.AD_LOAD_LATENCY_AAX_GET_AD_END_TO_FETCH_THREAD_END);
            return webResponseMakeCall;
        } catch (WebRequest.WebRequestException e) {
            if (e.getStatus() == WebRequest.WebRequestStatus.NETWORK_FAILURE) {
                adError = new AdError(AdError.ErrorCode.NETWORK_ERROR, "Could not contact Ad Server");
            } else if (e.getStatus() == WebRequest.WebRequestStatus.NETWORK_TIMEOUT) {
                adError = new AdError(AdError.ErrorCode.NETWORK_TIMEOUT, "Connection to Ad Server timed out");
            } else {
                adError = new AdError(AdError.ErrorCode.INTERNAL_ERROR, e.getMessage());
            }
            throw new AdFetchException(adError);
        }
    }

    private MetricsCollector getCompositeMetricsCollector() {
        if (this.compositeMetricsCollector == null) {
            ArrayList arrayList = new ArrayList();
            Iterator<Map.Entry<Integer, AdSlot>> it = this.slots.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getValue().getMetricsCollector());
            }
            this.compositeMetricsCollector = new MetricsCollector.CompositeMetricsCollector(arrayList);
        }
        return this.compositeMetricsCollector;
    }

    protected class AdFetchException extends Exception {
        private static final long serialVersionUID = 1;
        private final AdError adError;

        public AdFetchException(AdError adError) {
            this.adError = adError;
        }

        public AdFetchException(AdError adError, Throwable th) {
            super(th);
            this.adError = adError;
        }

        public AdError getAdError() {
            return this.adError;
        }
    }

    protected static class AdLoaderFactory {
        protected AdLoaderFactory() {
        }

        public AdLoader createAdLoader(AdRequest adRequest, Map<Integer, AdSlot> map) {
            return new AdLoader(adRequest, map);
        }
    }
}
