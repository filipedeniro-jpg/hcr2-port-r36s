package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
class MraidAdSDKBridgeFactory implements AdSDKBridgeFactory {
    MraidAdSDKBridgeFactory() {
    }

    @Override // com.amazon.device.ads.AdSDKBridgeFactory
    public AdSDKBridge createAdSDKBridge(AdControlAccessor adControlAccessor) {
        return new MRAIDAdSDKBridge(adControlAccessor, new JavascriptInteractor());
    }
}
