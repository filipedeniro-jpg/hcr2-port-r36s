package com.amazon.device.ads;

/* JADX INFO: compiled from: AAXParameterGroupParameter.java */
/* JADX INFO: loaded from: classes.dex */
class AdvertisingIdentifierAAXParameter extends AAXParameterGroupParameter {
    AdvertisingIdentifierAAXParameter() {
        this(DebugProperties.getInstance(), new MobileAdsLoggerFactory());
    }

    AdvertisingIdentifierAAXParameter(DebugProperties debugProperties, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        super(debugProperties, "idfa", DebugProperties.DEBUG_IDFA, mobileAdsLoggerFactory);
    }

    @Override // com.amazon.device.ads.AAXParameterGroupParameter
    protected String getDerivedValue(AAXParameter.ParameterData parameterData) {
        return parameterData.getAdRequest().getAdvertisingIdentifierInfo().getAdvertisingIdentifier();
    }
}
