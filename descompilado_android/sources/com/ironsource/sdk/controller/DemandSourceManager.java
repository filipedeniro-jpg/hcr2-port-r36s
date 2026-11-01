package com.ironsource.sdk.controller;

import android.text.TextUtils;
import com.ironsource.sdk.IronSourceAdInstance;
import com.ironsource.sdk.data.DemandSource;
import com.ironsource.sdk.data.SSAEnums;
import com.ironsource.sdk.listeners.OnAdProductListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class DemandSourceManager {
    private Map<String, DemandSource> mRewardedVideoDemandSourceMap = new LinkedHashMap();
    private Map<String, DemandSource> mInterstitialDemandSourceMap = new LinkedHashMap();
    private Map<String, DemandSource> mBannerDemandSourceMap = new LinkedHashMap();

    private Map<String, DemandSource> getMapByProductType(SSAEnums.ProductType productType) {
        if (productType.name().equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.name())) {
            return this.mRewardedVideoDemandSourceMap;
        }
        if (productType.name().equalsIgnoreCase(SSAEnums.ProductType.Interstitial.name())) {
            return this.mInterstitialDemandSourceMap;
        }
        if (productType.name().equalsIgnoreCase(SSAEnums.ProductType.Banner.name())) {
            return this.mBannerDemandSourceMap;
        }
        return null;
    }

    public Collection<DemandSource> getDemandSources(SSAEnums.ProductType productType) {
        Map<String, DemandSource> mapByProductType = getMapByProductType(productType);
        if (mapByProductType != null) {
            return mapByProductType.values();
        }
        return new ArrayList();
    }

    public DemandSource getDemandSourceById(SSAEnums.ProductType productType, String str) {
        Map<String, DemandSource> mapByProductType;
        if (TextUtils.isEmpty(str) || (mapByProductType = getMapByProductType(productType)) == null) {
            return null;
        }
        return mapByProductType.get(str);
    }

    private void addDemandSourceToDemandSources(SSAEnums.ProductType productType, String str, DemandSource demandSource) {
        Map<String, DemandSource> mapByProductType;
        if (TextUtils.isEmpty(str) || demandSource == null || (mapByProductType = getMapByProductType(productType)) == null) {
            return;
        }
        mapByProductType.put(str, demandSource);
    }

    public DemandSource createDemandSource(SSAEnums.ProductType productType, IronSourceAdInstance ironSourceAdInstance) {
        String id = ironSourceAdInstance.getId();
        DemandSource demandSource = new DemandSource(id, ironSourceAdInstance.getName(), ironSourceAdInstance.convertToMap(), ironSourceAdInstance.getAdListener());
        addDemandSourceToDemandSources(productType, id, demandSource);
        return demandSource;
    }

    public DemandSource createDemandSource(SSAEnums.ProductType productType, String str, Map<String, String> map, OnAdProductListener onAdProductListener) {
        DemandSource demandSource = new DemandSource(str, str, map, onAdProductListener);
        addDemandSourceToDemandSources(productType, str, demandSource);
        return demandSource;
    }
}
