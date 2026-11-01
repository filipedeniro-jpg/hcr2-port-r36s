package com.ironsource.mediationsdk.model;

import android.text.TextUtils;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class ProviderSettingsHolder {
    private static ProviderSettingsHolder mInstance;
    private ArrayList<ProviderSettings> mProviderSettingsArrayList = new ArrayList<>();

    public static synchronized ProviderSettingsHolder getProviderSettingsHolder() {
        if (mInstance == null) {
            mInstance = new ProviderSettingsHolder();
        }
        return mInstance;
    }

    private ProviderSettingsHolder() {
    }

    public void addProviderSettings(ProviderSettings providerSettings) {
        if (providerSettings != null) {
            this.mProviderSettingsArrayList.add(providerSettings);
        }
    }

    public ProviderSettings getProviderSettings(String str) {
        for (ProviderSettings providerSettings : this.mProviderSettingsArrayList) {
            if (providerSettings.getProviderName().equals(str)) {
                return providerSettings;
            }
        }
        ProviderSettings providerSettings2 = new ProviderSettings(str);
        addProviderSettings(providerSettings2);
        return providerSettings2;
    }

    public HashSet<String> getProviderSettingsByReflectionName(String str, String str2) {
        HashSet<String> hashSet = new HashSet<>();
        try {
            for (ProviderSettings providerSettings : this.mProviderSettingsArrayList) {
                if (providerSettings.getProviderTypeForReflection().equals(str)) {
                    if (providerSettings.getRewardedVideoSettings() != null && providerSettings.getRewardedVideoSettings().length() > 0 && !TextUtils.isEmpty(providerSettings.getRewardedVideoSettings().optString(str2))) {
                        hashSet.add(providerSettings.getRewardedVideoSettings().optString(str2));
                    }
                    if (providerSettings.getInterstitialSettings() != null && providerSettings.getInterstitialSettings().length() > 0 && !TextUtils.isEmpty(providerSettings.getInterstitialSettings().optString(str2))) {
                        hashSet.add(providerSettings.getInterstitialSettings().optString(str2));
                    }
                    if (providerSettings.getBannerSettings() != null && providerSettings.getBannerSettings().length() > 0 && !TextUtils.isEmpty(providerSettings.getBannerSettings().optString(str2))) {
                        hashSet.add(providerSettings.getBannerSettings().optString(str2));
                    }
                }
            }
        } catch (Exception unused) {
        }
        return hashSet;
    }

    public boolean containsProviderSettings(String str) {
        Iterator<ProviderSettings> it = this.mProviderSettingsArrayList.iterator();
        while (it.hasNext()) {
            if (it.next().getProviderName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<ProviderSettings> getProviderSettingsArrayList() {
        return this.mProviderSettingsArrayList;
    }

    public void fillSubProvidersDetails() {
        for (ProviderSettings providerSettings : this.mProviderSettingsArrayList) {
            if (providerSettings.isMultipleInstances() && !TextUtils.isEmpty(providerSettings.getProviderTypeForReflection())) {
                ProviderSettings providerSettings2 = getProviderSettings(providerSettings.getProviderTypeForReflection());
                providerSettings.setInterstitialSettings(IronSourceUtils.mergeJsons(providerSettings.getInterstitialSettings(), providerSettings2.getInterstitialSettings()));
                providerSettings.setRewardedVideoSettings(IronSourceUtils.mergeJsons(providerSettings.getRewardedVideoSettings(), providerSettings2.getRewardedVideoSettings()));
                providerSettings.setBannerSettings(IronSourceUtils.mergeJsons(providerSettings.getBannerSettings(), providerSettings2.getBannerSettings()));
            }
        }
    }
}
