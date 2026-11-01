package com.amazon.device.ads;

import com.vungle.warren.model.Cookie;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: SISRequests.java */
/* JADX INFO: loaded from: classes.dex */
abstract class SISRequest {
    private final Metrics.MetricType callMetricType;
    private final Configuration configuration;
    private final String logTag;
    protected final MobileAdsLogger logger;
    protected MobileAdsInfoStore mobileAdsInfoStore;
    private final String path;

    /* JADX INFO: compiled from: SISRequests.java */
    enum SISDeviceRequestType {
        GENERATE_DID,
        UPDATE_DEVICE_INFO
    }

    abstract HashMap<String, String> getPostParameters();

    abstract void onResponseReceived(JSONObject jSONObject);

    SISRequest(MobileAdsLoggerFactory mobileAdsLoggerFactory, String str, Metrics.MetricType metricType, String str2, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration) {
        this.logTag = str;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(this.logTag);
        this.callMetricType = metricType;
        this.path = str2;
        this.mobileAdsInfoStore = mobileAdsInfoStore;
        this.configuration = configuration;
    }

    MobileAdsLogger getLogger() {
        return this.logger;
    }

    String getLogTag() {
        return this.logTag;
    }

    Metrics.MetricType getCallMetricType() {
        return this.callMetricType;
    }

    String getPath() {
        return this.path;
    }

    WebRequest.QueryStringParameters getQueryParameters() {
        WebRequest.QueryStringParameters queryStringParameters = new WebRequest.QueryStringParameters();
        queryStringParameters.putUrlEncoded("dt", this.mobileAdsInfoStore.getDeviceInfo().getDeviceType());
        queryStringParameters.putUrlEncoded("app", this.mobileAdsInfoStore.getRegistrationInfo().getAppName());
        queryStringParameters.putUrlEncoded(Cookie.APP_ID, this.mobileAdsInfoStore.getRegistrationInfo().getAppKey());
        queryStringParameters.putUrlEncoded("sdkVer", Version.getSDKVersion());
        queryStringParameters.putUrlEncoded("aud", this.configuration.getString(Configuration.ConfigOption.SIS_DOMAIN));
        queryStringParameters.putUnencoded("pkg", this.mobileAdsInfoStore.getAppInfo().getPackageInfoJSONString());
        return queryStringParameters;
    }

    /* JADX INFO: compiled from: SISRequests.java */
    static class SISRequestFactory {
        SISRequestFactory() {
        }

        public SISDeviceRequest createDeviceRequest(SISDeviceRequestType sISDeviceRequestType, AdvertisingIdentifier advertisingIdentifier) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$ads$SISRequest$SISDeviceRequestType[sISDeviceRequestType.ordinal()];
            if (i == 1) {
                return new SISGenerateDIDRequest(advertisingIdentifier);
            }
            if (i == 2) {
                return new SISUpdateDeviceInfoRequest(advertisingIdentifier);
            }
            throw new IllegalArgumentException("SISRequestType " + sISDeviceRequestType + " is not a SISDeviceRequest");
        }

        public SISRegisterEventRequest createRegisterEventRequest(AdvertisingIdentifier.Info info, JSONArray jSONArray) {
            return new SISRegisterEventRequest(info, jSONArray);
        }
    }

    /* JADX INFO: renamed from: com.amazon.device.ads.SISRequest$1, reason: invalid class name */
    /* JADX INFO: compiled from: SISRequests.java */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$SISRequest$SISDeviceRequestType = new int[SISDeviceRequestType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$ads$SISRequest$SISDeviceRequestType[SISDeviceRequestType.GENERATE_DID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SISRequest$SISDeviceRequestType[SISDeviceRequestType.UPDATE_DEVICE_INFO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
