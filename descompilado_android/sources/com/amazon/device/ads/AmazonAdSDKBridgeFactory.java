package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
class AmazonAdSDKBridgeFactory implements AdSDKBridgeFactory {
    AmazonAdSDKBridgeFactory() {
    }

    @Override // com.amazon.device.ads.AdSDKBridgeFactory
    public AdSDKBridge createAdSDKBridge(AdControlAccessor adControlAccessor) {
        return new AmazonAdSDKBridge(adControlAccessor, new JavascriptInteractor());
    }
}
