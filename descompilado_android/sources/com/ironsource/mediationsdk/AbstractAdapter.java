package com.ironsource.mediationsdk;

import android.app.Activity;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.logger.LogListener;
import com.ironsource.mediationsdk.logger.LoggingApi;
import com.ironsource.mediationsdk.sdk.BannerAdapterApi;
import com.ironsource.mediationsdk.sdk.BannerSmashListener;
import com.ironsource.mediationsdk.sdk.BaseApi;
import com.ironsource.mediationsdk.sdk.InterstitialAdapterApi;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialApi;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractAdapter implements BaseApi, InterstitialAdapterApi, RewardedVideoAdapterApi, BannerAdapterApi, LoggingApi, RewardedInterstitialApi {
    protected BannerSmashListener mActiveBannerSmash;
    protected InterstitialSmashListener mActiveInterstitialSmash;
    protected RewardedVideoSmashListener mActiveRewardedVideoSmash;
    private String mPluginFrameworkVersion;
    private String mPluginType;
    private String mProviderName;
    protected RewardedInterstitialListener mRewardedInterstitial;
    private IronSourceLoggerManager mLoggerManager = IronSourceLoggerManager.getLogger();
    protected CopyOnWriteArrayList<RewardedVideoSmashListener> mAllRewardedVideoSmashes = new CopyOnWriteArrayList<>();
    protected CopyOnWriteArrayList<InterstitialSmashListener> mAllInterstitialSmashes = new CopyOnWriteArrayList<>();
    protected CopyOnWriteArrayList<BannerSmashListener> mAllBannerSmashes = new CopyOnWriteArrayList<>();
    protected ConcurrentHashMap<String, RewardedVideoSmashListener> mRewardedVideoPlacementToListenerMap = new ConcurrentHashMap<>();
    protected ConcurrentHashMap<String, InterstitialSmashListener> mInterstitialPlacementToListenerMap = new ConcurrentHashMap<>();
    protected ConcurrentHashMap<String, BannerSmashListener> mBannerPlacementToListenerMap = new ConcurrentHashMap<>();

    protected void addBannerListener(BannerSmashListener bannerSmashListener) {
    }

    public void destroyBanner(JSONObject jSONObject) {
    }

    public abstract String getCoreSDKVersion();

    public Map<String, Object> getIsBiddingData(JSONObject jSONObject) {
        return null;
    }

    public Map<String, Object> getRvBiddingData(JSONObject jSONObject) {
        return null;
    }

    public abstract String getVersion();

    public void initBanners(Activity activity, String str, String str2, JSONObject jSONObject, BannerSmashListener bannerSmashListener) {
    }

    public void initInterstitialForBidding(Activity activity, String str, String str2, JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener) {
    }

    public void initRvForBidding(Activity activity, String str, String str2, JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener) {
    }

    public void initRvForDemandOnly(Activity activity, String str, String str2, JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener) {
    }

    public void loadBanner(IronSourceBannerLayout ironSourceBannerLayout, JSONObject jSONObject, BannerSmashListener bannerSmashListener) {
    }

    public void loadInterstitial(JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener, String str) {
    }

    public void loadVideo(JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener, String str) {
    }

    public void loadVideoForDemandOnly(JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener) {
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void onPause(Activity activity) {
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void onResume(Activity activity) {
    }

    public void preInitInterstitial(Activity activity, String str, String str2, JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener) {
    }

    public void reloadBanner(JSONObject jSONObject) {
    }

    protected void removeBannerListener(BannerSmashListener bannerSmashListener) {
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void setAge(int i) {
    }

    protected void setConsent(boolean z) {
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void setGender(String str) {
    }

    @Override // com.ironsource.mediationsdk.logger.LoggingApi
    public void setLogListener(LogListener logListener) {
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void setMediationSegment(String str) {
    }

    protected void setMediationState(AbstractSmash.MEDIATION_STATE mediation_state, String str) {
    }

    public AbstractAdapter(String str) {
        this.mProviderName = str;
    }

    public String getProviderName() {
        return this.mProviderName;
    }

    protected String getDynamicUserId() {
        return IronSourceObject.getInstance().getDynamicUserId();
    }

    void setPluginData(String str, String str2) {
        this.mPluginType = str;
        this.mPluginFrameworkVersion = str2;
    }

    public String getPluginType() {
        return this.mPluginType;
    }

    public String getPluginFrameworkVersion() {
        return this.mPluginFrameworkVersion;
    }

    protected void log(IronSourceLogger.IronSourceTag ironSourceTag, String str, int i) {
        this.mLoggerManager.onLog(ironSourceTag, str, i);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedInterstitialApi
    public void setRewardedInterstitialListener(RewardedInterstitialListener rewardedInterstitialListener) {
        this.mRewardedInterstitial = rewardedInterstitialListener;
    }

    protected boolean isAdaptersDebugEnabled() {
        return this.mLoggerManager.isDebugEnabled();
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi
    public void addRewardedVideoListener(RewardedVideoSmashListener rewardedVideoSmashListener) {
        this.mAllRewardedVideoSmashes.add(rewardedVideoSmashListener);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi
    public void removeRewardedVideoListener(RewardedVideoSmashListener rewardedVideoSmashListener) {
        this.mAllRewardedVideoSmashes.remove(rewardedVideoSmashListener);
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialAdapterApi
    public void addInterstitialListener(InterstitialSmashListener interstitialSmashListener) {
        this.mAllInterstitialSmashes.add(interstitialSmashListener);
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialAdapterApi
    public void removeInterstitialListener(InterstitialSmashListener interstitialSmashListener) {
        this.mAllInterstitialSmashes.remove(interstitialSmashListener);
    }
}
