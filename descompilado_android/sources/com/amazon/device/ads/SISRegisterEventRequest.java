package com.amazon.device.ads;

import com.ironsource.eventsmodule.DataBaseEventsStorage;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: SISRequests.java */
/* JADX INFO: loaded from: classes.dex */
class SISRegisterEventRequest extends SISRequest {
    private static final Metrics.MetricType CALL_METRIC_TYPE = Metrics.MetricType.SIS_LATENCY_REGISTER_EVENT;
    private static final String LOGTAG = "SISRegisterEventRequest";
    private static final String PATH = "/register_event";
    private final AdvertisingIdentifier.Info advertisingIdentifierInfo;
    private final AppEventRegistrationHandler appEventRegistrationHandler;
    private final JSONArray appEvents;

    public SISRegisterEventRequest(AdvertisingIdentifier.Info info, JSONArray jSONArray) {
        this(info, jSONArray, AppEventRegistrationHandler.getInstance(), new MobileAdsLoggerFactory(), MobileAdsInfoStore.getInstance(), Configuration.getInstance());
    }

    SISRegisterEventRequest(AdvertisingIdentifier.Info info, JSONArray jSONArray, AppEventRegistrationHandler appEventRegistrationHandler, MobileAdsLoggerFactory mobileAdsLoggerFactory, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration) {
        super(mobileAdsLoggerFactory, LOGTAG, CALL_METRIC_TYPE, PATH, mobileAdsInfoStore, configuration);
        this.advertisingIdentifierInfo = info;
        this.appEvents = jSONArray;
        this.appEventRegistrationHandler = appEventRegistrationHandler;
    }

    @Override // com.amazon.device.ads.SISRequest
    public WebRequest.QueryStringParameters getQueryParameters() {
        WebRequest.QueryStringParameters queryParameters = super.getQueryParameters();
        queryParameters.putUrlEncoded("adId", this.advertisingIdentifierInfo.getSISDeviceIdentifier());
        return queryParameters;
    }

    @Override // com.amazon.device.ads.SISRequest
    public HashMap<String, String> getPostParameters() {
        HashMap<String, String> map = new HashMap<>();
        map.put(DataBaseEventsStorage.EventEntry.TABLE_NAME, this.appEvents.toString());
        return map;
    }

    @Override // com.amazon.device.ads.SISRequest
    public void onResponseReceived(JSONObject jSONObject) {
        int integerFromJSON = JSONUtils.getIntegerFromJSON(jSONObject, "rcode", 0);
        if (integerFromJSON == 1) {
            this.logger.d("Application events registered successfully.");
            this.appEventRegistrationHandler.onAppEventsRegistered();
            return;
        }
        this.logger.d("Application events not registered. rcode:" + integerFromJSON);
    }
}
