package com.amazon.device.ads;

/* JADX INFO: compiled from: AAXParameterGroupParameter.java */
/* JADX INFO: loaded from: classes.dex */
class SISDeviceIdentifierAAXParameter extends AAXParameterGroupParameter {
    SISDeviceIdentifierAAXParameter() {
        this(DebugProperties.getInstance(), new MobileAdsLoggerFactory());
    }

    SISDeviceIdentifierAAXParameter(DebugProperties debugProperties, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        super(debugProperties, "ad-id", DebugProperties.DEBUG_ADID, mobileAdsLoggerFactory);
    }

    @Override // com.amazon.device.ads.AAXParameterGroupParameter
    protected String getDerivedValue(AAXParameter.ParameterData parameterData) {
        return parameterData.getAdRequest().getAdvertisingIdentifierInfo().getSISDeviceIdentifier();
    }
}
