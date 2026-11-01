package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
public class AmazonViewableAdSDKBridgeFactory implements AdSDKBridgeFactory {
    @Override // com.amazon.device.ads.AdSDKBridgeFactory
    public AdSDKBridge createAdSDKBridge(AdControlAccessor adControlAccessor) {
        return new AmazonViewableAdSDKBridge(adControlAccessor, new JavascriptInteractor());
    }
}
