package com.amazon.device.ads;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: compiled from: SISRequests.java */
/* JADX INFO: loaded from: classes.dex */
abstract class SISDeviceRequest extends SISRequest {
    private AdvertisingIdentifier advertisingIdentifier;
    private AdvertisingIdentifier.Info advertisingIdentifierInfo;

    private static String convertOptOutBooleanToStringInt(boolean z) {
        return z ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO;
    }

    @Override // com.amazon.device.ads.SISRequest
    public HashMap<String, String> getPostParameters() {
        return null;
    }

    SISDeviceRequest(MobileAdsLoggerFactory mobileAdsLoggerFactory, String str, Metrics.MetricType metricType, String str2, AdvertisingIdentifier advertisingIdentifier, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration) {
        super(mobileAdsLoggerFactory, str, metricType, str2, mobileAdsInfoStore, configuration);
        this.advertisingIdentifier = advertisingIdentifier;
        this.advertisingIdentifierInfo = this.advertisingIdentifier.getAdvertisingIdentifierInfo();
    }

    @Override // com.amazon.device.ads.SISRequest
    public WebRequest.QueryStringParameters getQueryParameters() {
        WebRequest.QueryStringParameters queryParameters = super.getQueryParameters();
        DeviceInfo deviceInfo = this.mobileAdsInfoStore.getDeviceInfo();
        queryParameters.putUnencoded("ua", deviceInfo.getUserAgentString());
        queryParameters.putUnencoded("dinfo", deviceInfo.getDInfoProperty().toString());
        if (this.advertisingIdentifierInfo.hasAdvertisingIdentifier()) {
            queryParameters.putUrlEncoded("idfa", this.advertisingIdentifierInfo.getAdvertisingIdentifier());
            queryParameters.putUrlEncoded("oo", convertOptOutBooleanToStringInt(this.advertisingIdentifierInfo.isLimitAdTrackingEnabled()));
        } else {
            queryParameters.putUrlEncoded("sha1_mac", deviceInfo.getMacSha1());
            queryParameters.putUrlEncoded("sha1_serial", deviceInfo.getSerialSha1());
            queryParameters.putUrlEncoded("sha1_udid", deviceInfo.getUdidSha1());
            queryParameters.putUrlEncodedIfTrue("badMac", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE, deviceInfo.isMacBad());
            queryParameters.putUrlEncodedIfTrue("badSerial", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE, deviceInfo.isSerialBad());
            queryParameters.putUrlEncodedIfTrue("badUdid", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE, deviceInfo.isUdidBad());
        }
        String andClearTransition = this.advertisingIdentifier.getAndClearTransition();
        queryParameters.putUrlEncodedIfTrue("aidts", andClearTransition, andClearTransition != null);
        return queryParameters;
    }

    protected AdvertisingIdentifier.Info getAdvertisingIdentifierInfo() {
        return this.advertisingIdentifierInfo;
    }

    @Override // com.amazon.device.ads.SISRequest
    public void onResponseReceived(JSONObject jSONObject) {
        String stringFromJSON = JSONUtils.getStringFromJSON(jSONObject, "adId", "");
        if (stringFromJSON.length() > 0) {
            this.mobileAdsInfoStore.getRegistrationInfo().putAdId(stringFromJSON, getAdvertisingIdentifierInfo());
        }
    }
}
