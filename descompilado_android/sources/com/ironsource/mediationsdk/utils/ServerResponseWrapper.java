package com.ironsource.mediationsdk.utils;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.ads.mediation.unity.BuildConfig;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.eventsmodule.DataBaseEventsStorage;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import com.ironsource.mediationsdk.logger.ConsoleLogger;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.logger.ServerLogger;
import com.ironsource.mediationsdk.model.ApplicationConfigurations;
import com.ironsource.mediationsdk.model.ApplicationEvents;
import com.ironsource.mediationsdk.model.ApplicationLogger;
import com.ironsource.mediationsdk.model.BannerConfigurations;
import com.ironsource.mediationsdk.model.BannerPlacement;
import com.ironsource.mediationsdk.model.Configurations;
import com.ironsource.mediationsdk.model.InterstitialConfigurations;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import com.ironsource.mediationsdk.model.OfferwallConfigurations;
import com.ironsource.mediationsdk.model.OfferwallPlacement;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.model.PlacementAvailabilitySettings;
import com.ironsource.mediationsdk.model.PlacementCappingType;
import com.ironsource.mediationsdk.model.ProviderOrder;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.model.ProviderSettingsHolder;
import com.ironsource.mediationsdk.model.RewardedVideoConfigurations;
import com.ironsource.mediationsdk.model.ServerSegmetData;
import com.ironsource.mediationsdk.sdk.GeneralProperties;
import com.ironsource.sdk.constants.Constants;
import com.my.target.aa;
import com.my.target.ai;
import com.my.target.bj;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ServerResponseWrapper {
    public static final String APP_KEY_FIELD = "appKey";
    public static final String RESPONSE_FIELD = "response";
    public static final String USER_ID_FIELD = "userId";
    private String mAppKey;
    private Configurations mConfigurations;
    private Context mContext;
    private ProviderOrder mProviderOrder;
    private ProviderSettingsHolder mProviderSettingsHolder;
    private JSONObject mResponse;
    private String mUserId;
    private final String ERROR_KEY = "error";
    private final int DEFAULT_LOG_LEVEL = 3;
    private final int DEFAULT_ADAPTERS_SMARTLOAD_AMOUNT = 2;
    private final int DEFAULT_ADAPTERS_SMARTLOAD_TIMEOUT = 60;
    private final int DEFAULT_BANNER_SMARTLOAD_TIMEOUT = 10000;
    private final int DEFAULT_MAX_EVENTS_PER_BATCH = 5000;
    private final int DEFAULT_MANUAL_LOAD_INTERVAL_FIELD = 300;
    private final int DEFAULT_IS_DELAY_LOAD_FAILURE_TIMEOUT = 3;
    private final int DEFAULT_BN_DELAY_LOAD_FAILURE_TIMEOUT = 3;
    private final String PROVIDER_ORDER_FIELD = "providerOrder";
    private final String PROVIDER_SETTINGS_FIELD = "providerSettings";
    private final String CONFIGURATIONS_FIELD = "configurations";
    private final String GENERIC_PARAMS_FIELD = "genericParams";
    private final String AD_UNITS_FIELD = "adUnits";
    private final String PROVIDER_LOAD_NAME_FIELD = "providerLoadName";
    private final String APPLICATION_FIELD = "application";
    private final String RV_FIELD = "rewardedVideo";
    private final String IS_FIELD = aa.e.bo;
    private final String OW_FIELD = "offerwall";
    private final String BN_FIELD = ai.a.cW;
    private final String INTEGRATION_FIELD = "integration";
    private final String LOGGERS_FIELD = "loggers";
    private final String SEGMENT_FIELD = "segment";
    private final String EVENTS_FIELD = DataBaseEventsStorage.EventEntry.TABLE_NAME;
    private final String MAX_NUM_OF_ADAPTERS_TO_LOAD_ON_START_FIELD = "maxNumOfAdaptersToLoadOnStart";
    private final String ADAPTER_TIMEOUT_IN_SECS_FIELD = "adapterTimeOutInSeconds";
    private final String ADAPTER_TIMEOUT_IN_MILLIS_FIELD = "atim";
    private final String DEFAULT_BANNER_LOAD_REFRESH_INTERVAL = "bannerInterval";
    private final String MANUAL_LOAD_INTERVAL_FIELD = "loadRVInterval";
    private final String SERVER_FIELD = ServerLogger.NAME;
    private final String PUBLISHER_FIELD = "publisher";
    private final String CONSOLE_FIELD = ConsoleLogger.NAME;
    private final String SEND_ULTRA_EVENTS_FIELD = "sendUltraEvents";
    private final String SEND_EVENTS_TOGGLE_FIELD = "sendEventsToggle";
    private final String SERVER_EVENTS_URL_FIELD = "serverEventsURL";
    private final String SERVER_EVENTS_TYPE = "serverEventsType";
    private final String BACKUP_THRESHOLD_FIELD = "backupThreshold";
    private final String MAX_NUM_OF_EVENTS_FIELD = "maxNumberOfEvents";
    private final String MAX_EVENTS_PER_BATCH = "maxEventsPerBatch";
    private final String OPT_OUT_EVENTS_FIELD = "optOut";
    private final String ALLOW_LOCATION = "allowLocation";
    private final String PLACEMENTS_FIELD = "placements";
    private final String PLACEMENT_ID_FIELD = Constants.PLACEMENT_ID;
    private final String PLACEMENT_NAME_FIELD = "placementName";
    private final String PLACEMENT_SETTINGS_DELIVERY_FIELD = bj.gV;
    private final String PLACEMENT_SETTINGS_IS_DEFAULT_FIELD = "isDefault";
    private final String PLACEMENT_SETTINGS_CAPPING_FIELD = "capping";
    private final String PLACEMENT_SETTINGS_PACING_FIELD = "pacing";
    private final String PLACEMENT_SETTINGS_ENABLED_FIELD = "enabled";
    private final String PLACEMENT_SETTINGS_CAPPING_VALUE_FIELD = "maxImpressions";
    private final String PLACEMENT_SETTINGS_PACING_VALUE_FIELD = "numOfSeconds";
    private final String PLACEMENT_SETTINGS_CAPPING_UNIT_FIELD = "unit";
    private final String VIRTUAL_ITEM_NAME_FIELD = "virtualItemName";
    private final String VIRTUAL_ITEM_COUNT_FIELD = "virtualItemCount";
    private final String BACKFILL_FIELD = "backFill";
    private final String PREMIUM_FIELD = "premium";
    private final String UUID_ENABLED_FIELD = DeviceStatus.UUID_ENABLED;
    private final String AB_TESTING = "abt";
    private final String DELAY_LOAD_FAILURE = "delayLoadFailure";
    private final String AD_SOURCE_NAME_FIELD = "adSourceName";
    private final String SUB_PROVIDER_ID_FIELD = "spId";
    private final String IS_MULTIPLE_INSTANCES_FIELD = "mpis";
    private final String AUCTION_FIELD = "auction";
    private final String AUCTION_DATA_FIELD = "auctionData";
    private final String AUCTION_URL_FIELD = "auctioneerURL";
    private final String AUCTION_PROGRAMMATIC_FIELD = IronSourceConstants.EVENTS_PROGRAMMATIC;
    private final String MIN_TIME_BEFORE_FIRST_AUCTION_FIELD = "minTimeBeforeFirstAuction";
    private final String TIME_TO_WAIT_BEFORE_AUCTION_FIELD = "timeToWaitBeforeAuction";
    private final String AUCTION_RETRY_INTERVAL_FIELD = "auctionRetryInterval";
    private final String IS_AUCTION_ON_SHOW_START_FIELD = "isAuctionOnShowStart";

    public ServerResponseWrapper(Context context, String str, String str2, String str3) {
        this.mContext = context;
        try {
            if (TextUtils.isEmpty(str3)) {
                this.mResponse = new JSONObject();
            } else {
                this.mResponse = new JSONObject(str3);
            }
            parseProviderSettings();
            parseConfigurations();
            parseProviderOrder();
            this.mAppKey = TextUtils.isEmpty(str) ? "" : str;
            this.mUserId = TextUtils.isEmpty(str2) ? "" : str2;
        } catch (JSONException e) {
            e.printStackTrace();
            defaultInit();
        }
    }

    public ServerResponseWrapper(ServerResponseWrapper serverResponseWrapper) {
        try {
            this.mContext = serverResponseWrapper.getContext();
            this.mResponse = new JSONObject(serverResponseWrapper.mResponse.toString());
            this.mAppKey = serverResponseWrapper.mAppKey;
            this.mUserId = serverResponseWrapper.mUserId;
            this.mProviderOrder = serverResponseWrapper.getProviderOrder();
            this.mProviderSettingsHolder = serverResponseWrapper.getProviderSettingsHolder();
            this.mConfigurations = serverResponseWrapper.getConfigurations();
        } catch (Exception unused) {
            defaultInit();
        }
    }

    private void defaultInit() {
        this.mResponse = new JSONObject();
        this.mAppKey = "";
        this.mUserId = "";
        this.mProviderOrder = new ProviderOrder();
        this.mProviderSettingsHolder = ProviderSettingsHolder.getProviderSettingsHolder();
        this.mConfigurations = new Configurations();
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(APP_KEY_FIELD, this.mAppKey);
            jSONObject.put(USER_ID_FIELD, this.mUserId);
            jSONObject.put(RESPONSE_FIELD, this.mResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public boolean isValidResponse() {
        return ((((this.mResponse != null) && !this.mResponse.has("error")) && this.mProviderOrder != null) && this.mProviderSettingsHolder != null) && this.mConfigurations != null;
    }

    public List<IronSource.AD_UNIT> getInitiatedAdUnits() {
        ProviderOrder providerOrder;
        ProviderOrder providerOrder2;
        if (this.mResponse == null || this.mConfigurations == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (this.mConfigurations.getRewardedVideoConfigurations() != null && (providerOrder2 = this.mProviderOrder) != null && providerOrder2.getRewardedVideoProviderOrder().size() > 0) {
            arrayList.add(IronSource.AD_UNIT.REWARDED_VIDEO);
        }
        if (this.mConfigurations.getInterstitialConfigurations() != null && (providerOrder = this.mProviderOrder) != null && providerOrder.getInterstitialProviderOrder().size() > 0) {
            arrayList.add(IronSource.AD_UNIT.INTERSTITIAL);
        }
        if (this.mConfigurations.getOfferwallConfigurations() != null) {
            arrayList.add(IronSource.AD_UNIT.OFFERWALL);
        }
        if (this.mConfigurations.getBannerConfigurations() != null) {
            arrayList.add(IronSource.AD_UNIT.BANNER);
        }
        return arrayList;
    }

    private void parseProviderOrder() {
        try {
            JSONObject section = getSection(this.mResponse, "providerOrder");
            JSONArray jSONArrayOptJSONArray = section.optJSONArray("rewardedVideo");
            JSONArray jSONArrayOptJSONArray2 = section.optJSONArray(aa.e.bo);
            JSONArray jSONArrayOptJSONArray3 = section.optJSONArray(ai.a.cW);
            this.mProviderOrder = new ProviderOrder();
            if (jSONArrayOptJSONArray != null && getConfigurations() != null && getConfigurations().getRewardedVideoConfigurations() != null) {
                String backFillProviderName = getConfigurations().getRewardedVideoConfigurations().getBackFillProviderName();
                String premiumProviderName = getConfigurations().getRewardedVideoConfigurations().getPremiumProviderName();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    String strOptString = jSONArrayOptJSONArray.optString(i);
                    if (strOptString.equals(backFillProviderName)) {
                        this.mProviderOrder.setRVBackFillProvider(backFillProviderName);
                    } else {
                        if (strOptString.equals(premiumProviderName)) {
                            this.mProviderOrder.setRVPremiumProvider(premiumProviderName);
                        }
                        this.mProviderOrder.addRewardedVideoProvider(strOptString);
                        ProviderSettings providerSettings = ProviderSettingsHolder.getProviderSettingsHolder().getProviderSettings(strOptString);
                        if (providerSettings != null) {
                            providerSettings.setRewardedVideoPriority(i);
                        }
                    }
                }
            }
            if (jSONArrayOptJSONArray2 != null && getConfigurations() != null && getConfigurations().getInterstitialConfigurations() != null) {
                String backFillProviderName2 = getConfigurations().getInterstitialConfigurations().getBackFillProviderName();
                String premiumProviderName2 = getConfigurations().getInterstitialConfigurations().getPremiumProviderName();
                for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                    String strOptString2 = jSONArrayOptJSONArray2.optString(i2);
                    if (strOptString2.equals(backFillProviderName2)) {
                        this.mProviderOrder.setISBackFillProvider(backFillProviderName2);
                    } else {
                        if (strOptString2.equals(premiumProviderName2)) {
                            this.mProviderOrder.setISPremiumProvider(premiumProviderName2);
                        }
                        this.mProviderOrder.addInterstitialProvider(strOptString2);
                        ProviderSettings providerSettings2 = ProviderSettingsHolder.getProviderSettingsHolder().getProviderSettings(strOptString2);
                        if (providerSettings2 != null) {
                            providerSettings2.setInterstitialPriority(i2);
                        }
                    }
                }
            }
            if (jSONArrayOptJSONArray3 != null) {
                for (int i3 = 0; i3 < jSONArrayOptJSONArray3.length(); i3++) {
                    String strOptString3 = jSONArrayOptJSONArray3.optString(i3);
                    this.mProviderOrder.addBannerProvider(strOptString3);
                    ProviderSettings providerSettings3 = ProviderSettingsHolder.getProviderSettingsHolder().getProviderSettings(strOptString3);
                    if (providerSettings3 != null) {
                        providerSettings3.setBannerPriority(i3);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseProviderSettings() {
        try {
            this.mProviderSettingsHolder = ProviderSettingsHolder.getProviderSettingsHolder();
            JSONObject section = getSection(this.mResponse, "providerSettings");
            Iterator<String> itKeys = section.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                JSONObject jSONObjectOptJSONObject = section.optJSONObject(next);
                if (jSONObjectOptJSONObject != null) {
                    boolean zOptBoolean = jSONObjectOptJSONObject.optBoolean("mpis", false);
                    String strOptString = jSONObjectOptJSONObject.optString("spId", AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    String strOptString2 = jSONObjectOptJSONObject.optString("adSourceName", null);
                    String strOptString3 = jSONObjectOptJSONObject.optString("providerLoadName", next);
                    JSONObject section2 = getSection(jSONObjectOptJSONObject, "adUnits");
                    JSONObject section3 = getSection(jSONObjectOptJSONObject, "application");
                    JSONObject section4 = getSection(section2, "rewardedVideo");
                    JSONObject section5 = getSection(section2, aa.e.bo);
                    JSONObject section6 = getSection(section2, ai.a.cW);
                    JSONObject jSONObjectMergeJsons = IronSourceUtils.mergeJsons(section4, section3);
                    JSONObject jSONObjectMergeJsons2 = IronSourceUtils.mergeJsons(section5, section3);
                    JSONObject jSONObjectMergeJsons3 = IronSourceUtils.mergeJsons(section6, section3);
                    if (this.mProviderSettingsHolder.containsProviderSettings(next)) {
                        ProviderSettings providerSettings = this.mProviderSettingsHolder.getProviderSettings(next);
                        JSONObject rewardedVideoSettings = providerSettings.getRewardedVideoSettings();
                        JSONObject interstitialSettings = providerSettings.getInterstitialSettings();
                        JSONObject bannerSettings = providerSettings.getBannerSettings();
                        providerSettings.setRewardedVideoSettings(IronSourceUtils.mergeJsons(rewardedVideoSettings, jSONObjectMergeJsons));
                        providerSettings.setInterstitialSettings(IronSourceUtils.mergeJsons(interstitialSettings, jSONObjectMergeJsons2));
                        providerSettings.setBannerSettings(IronSourceUtils.mergeJsons(bannerSettings, jSONObjectMergeJsons3));
                        providerSettings.setIsMultipleInstances(zOptBoolean);
                        providerSettings.setSubProviderId(strOptString);
                        providerSettings.setAdSourceNameForEvents(strOptString2);
                    } else if (shouldMergeWithDebugSettings(strOptString3)) {
                        ProviderSettings providerSettings2 = this.mProviderSettingsHolder.getProviderSettings("Mediation");
                        JSONObject rewardedVideoSettings2 = providerSettings2.getRewardedVideoSettings();
                        JSONObject interstitialSettings2 = providerSettings2.getInterstitialSettings();
                        JSONObject bannerSettings2 = providerSettings2.getBannerSettings();
                        ProviderSettings providerSettings3 = new ProviderSettings(next, strOptString3, section3, IronSourceUtils.mergeJsons(new JSONObject(rewardedVideoSettings2.toString()), jSONObjectMergeJsons), IronSourceUtils.mergeJsons(new JSONObject(interstitialSettings2.toString()), jSONObjectMergeJsons2), IronSourceUtils.mergeJsons(new JSONObject(bannerSettings2.toString()), jSONObjectMergeJsons3));
                        providerSettings3.setIsMultipleInstances(zOptBoolean);
                        providerSettings3.setSubProviderId(strOptString);
                        providerSettings3.setAdSourceNameForEvents(strOptString2);
                        this.mProviderSettingsHolder.addProviderSettings(providerSettings3);
                    } else {
                        ProviderSettings providerSettings4 = new ProviderSettings(next, strOptString3, section3, jSONObjectMergeJsons, jSONObjectMergeJsons2, jSONObjectMergeJsons3);
                        providerSettings4.setIsMultipleInstances(zOptBoolean);
                        providerSettings4.setSubProviderId(strOptString);
                        providerSettings4.setAdSourceNameForEvents(strOptString2);
                        this.mProviderSettingsHolder.addProviderSettings(providerSettings4);
                    }
                }
            }
            this.mProviderSettingsHolder.fillSubProvidersDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean shouldMergeWithDebugSettings(String str) {
        String lowerCase = str.toLowerCase();
        return this.mProviderSettingsHolder.containsProviderSettings("Mediation") && (IronSourceConstants.SUPERSONIC_CONFIG_NAME.toLowerCase().equals(lowerCase) || IronSourceConstants.IRONSOURCE_CONFIG_NAME.toLowerCase().equals(lowerCase) || IronSourceConstants.RIS_CONFIG_NAME.toLowerCase().equals(lowerCase));
    }

    private void parseConfigurations() {
        String str;
        RewardedVideoConfigurations rewardedVideoConfigurations;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        InterstitialConfigurations interstitialConfigurations;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        BannerConfigurations bannerConfigurations;
        String str13;
        OfferwallConfigurations offerwallConfigurations;
        JSONObject section;
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        AuctionSettings auctionSettings;
        int[] iArr4;
        AuctionSettings auctionSettings2;
        try {
            JSONObject section2 = getSection(this.mResponse, "configurations");
            JSONObject section3 = getSection(section2, "adUnits");
            JSONObject section4 = getSection(section2, "application");
            JSONObject section5 = getSection(section3, "rewardedVideo");
            JSONObject section6 = getSection(section3, aa.e.bo);
            JSONObject section7 = getSection(section3, "offerwall");
            JSONObject section8 = getSection(section3, ai.a.cW);
            JSONObject section9 = getSection(section4, DataBaseEventsStorage.EventEntry.TABLE_NAME);
            JSONObject section10 = getSection(section4, "loggers");
            JSONObject section11 = getSection(section4, "segment");
            JSONObject section12 = getSection(section4, "auction");
            if (section4 != null) {
                IronSourceUtils.saveBooleanToSharedPrefs(this.mContext, DeviceStatus.UUID_ENABLED, section4.optBoolean(DeviceStatus.UUID_ENABLED, true));
            }
            if (section9 != null) {
                String strOptString = section9.optString("abt");
                if (!TextUtils.isEmpty(strOptString)) {
                    InterstitialEventsManager.getInstance().setABT(strOptString);
                    RewardedVideoEventsManager.getInstance().setABT(strOptString);
                }
            }
            String str14 = "maxEventsPerBatch";
            String str15 = "maxNumberOfEvents";
            String str16 = "backupThreshold";
            String str17 = "serverEventsURL";
            String str18 = "sendEventsToggle";
            if (section5 != null) {
                JSONArray jSONArrayOptJSONArray = section5.optJSONArray("placements");
                JSONObject section13 = getSection(section5, DataBaseEventsStorage.EventEntry.TABLE_NAME);
                int intConfigValue = getIntConfigValue(section5, section4, "maxNumOfAdaptersToLoadOnStart", 2);
                int intConfigValue2 = getIntConfigValue(section5, section4, "adapterTimeOutInSeconds", 60);
                int intConfigValue3 = getIntConfigValue(section5, section4, "loadRVInterval", 300);
                JSONObject jSONObjectMergeJsons = IronSourceUtils.mergeJsons(section13, section9);
                boolean zOptBoolean = jSONObjectMergeJsons.optBoolean("sendUltraEvents", false);
                boolean zOptBoolean2 = jSONObjectMergeJsons.optBoolean("sendEventsToggle", false);
                String strOptString2 = jSONObjectMergeJsons.optString("serverEventsURL", "");
                String strOptString3 = jSONObjectMergeJsons.optString("serverEventsType", "");
                int iOptInt = jSONObjectMergeJsons.optInt("backupThreshold", -1);
                int iOptInt2 = jSONObjectMergeJsons.optInt("maxNumberOfEvents", -1);
                int iOptInt3 = jSONObjectMergeJsons.optInt("maxEventsPerBatch", 5000);
                JSONArray jSONArrayOptJSONArray2 = jSONObjectMergeJsons.optJSONArray("optOut");
                if (jSONArrayOptJSONArray2 != null) {
                    int[] iArr5 = new int[jSONArrayOptJSONArray2.length()];
                    for (int i = 0; i < jSONArrayOptJSONArray2.length(); i++) {
                        iArr5[i] = jSONArrayOptJSONArray2.optInt(i);
                    }
                    iArr4 = iArr5;
                } else {
                    iArr4 = null;
                }
                ApplicationEvents applicationEvents = new ApplicationEvents(zOptBoolean, zOptBoolean2, strOptString2, strOptString3, iOptInt, iOptInt2, iOptInt3, iArr4);
                if (section12 != null) {
                    JSONObject section14 = getSection(section12, "rewardedVideo");
                    str = "serverEventsType";
                    auctionSettings2 = new AuctionSettings(section12.optString("auctionData", ""), section12.optString("auctioneerURL", ""), section14.optBoolean(IronSourceConstants.EVENTS_PROGRAMMATIC, false), section14.optInt("minTimeBeforeFirstAuction", 2000), section14.optInt("auctionRetryInterval", BuildConfig.VERSION_CODE), section14.optInt("timeToWaitBeforeAuction", 5000), section14.optBoolean("isAuctionOnShowStart", true));
                } else {
                    str = "serverEventsType";
                    auctionSettings2 = new AuctionSettings();
                }
                RewardedVideoConfigurations rewardedVideoConfigurations2 = new RewardedVideoConfigurations(intConfigValue, intConfigValue2, intConfigValue3, applicationEvents, auctionSettings2);
                if (jSONArrayOptJSONArray != null) {
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                        Placement singleRVPlacement = parseSingleRVPlacement(jSONArrayOptJSONArray.optJSONObject(i2));
                        if (singleRVPlacement != null) {
                            rewardedVideoConfigurations2.addRewardedVideoPlacement(singleRVPlacement);
                        }
                    }
                }
                String strOptString4 = section5.optString("backFill");
                if (!TextUtils.isEmpty(strOptString4)) {
                    rewardedVideoConfigurations2.setBackFillProviderName(strOptString4);
                }
                String strOptString5 = section5.optString("premium");
                if (!TextUtils.isEmpty(strOptString5)) {
                    rewardedVideoConfigurations2.setPremiumProviderName(strOptString5);
                }
                rewardedVideoConfigurations = rewardedVideoConfigurations2;
            } else {
                section12 = section12;
                str16 = "backupThreshold";
                str = "serverEventsType";
                str18 = "sendEventsToggle";
                str14 = "maxEventsPerBatch";
                str15 = "maxNumberOfEvents";
                rewardedVideoConfigurations = null;
            }
            if (section6 != 0) {
                str3 = "placements";
                JSONArray jSONArrayOptJSONArray3 = section6.optJSONArray(str3);
                str4 = DataBaseEventsStorage.EventEntry.TABLE_NAME;
                JSONObject section15 = getSection(section6, str4);
                int intConfigValue4 = getIntConfigValue(section6, section4, "maxNumOfAdaptersToLoadOnStart", 2);
                int intConfigValue5 = getIntConfigValue(section6, section4, "adapterTimeOutInSeconds", 60);
                int intConfigValue6 = getIntConfigValue(section6, section4, "delayLoadFailure", 3);
                JSONObject jSONObjectMergeJsons2 = IronSourceUtils.mergeJsons(section15, section9);
                str7 = str18;
                boolean zOptBoolean3 = jSONObjectMergeJsons2.optBoolean(str7, false);
                String strOptString6 = jSONObjectMergeJsons2.optString("serverEventsURL", "");
                str2 = str;
                String strOptString7 = jSONObjectMergeJsons2.optString(str2, "");
                str6 = str16;
                int iOptInt4 = jSONObjectMergeJsons2.optInt(str6, -1);
                str5 = str15;
                int iOptInt5 = jSONObjectMergeJsons2.optInt(str5, -1);
                str14 = str14;
                int iOptInt6 = jSONObjectMergeJsons2.optInt(str14, 5000);
                JSONArray jSONArrayOptJSONArray4 = jSONObjectMergeJsons2.optJSONArray("optOut");
                if (jSONArrayOptJSONArray4 != null) {
                    int[] iArr6 = new int[jSONArrayOptJSONArray4.length()];
                    for (int i3 = 0; i3 < jSONArrayOptJSONArray4.length(); i3++) {
                        iArr6[i3] = jSONArrayOptJSONArray4.optInt(i3);
                    }
                    iArr3 = iArr6;
                } else {
                    iArr3 = null;
                }
                ApplicationEvents applicationEvents2 = new ApplicationEvents(false, zOptBoolean3, strOptString6, strOptString7, iOptInt4, iOptInt5, iOptInt6, iArr3);
                if (section12 != null) {
                    JSONObject jSONObject = section12;
                    JSONObject section16 = getSection(jSONObject, aa.e.bo);
                    auctionSettings = new AuctionSettings(jSONObject.optString("auctionData", ""), jSONObject.optString("auctioneerURL", ""), section16.optBoolean(IronSourceConstants.EVENTS_PROGRAMMATIC, false), section16.optInt("minTimeBeforeFirstAuction", 2000), 0L, 0L, true);
                } else {
                    auctionSettings = new AuctionSettings();
                }
                InterstitialConfigurations interstitialConfigurations2 = new InterstitialConfigurations(intConfigValue4, intConfigValue5, applicationEvents2, auctionSettings, intConfigValue6);
                if (jSONArrayOptJSONArray3 != null) {
                    for (int i4 = 0; i4 < jSONArrayOptJSONArray3.length(); i4++) {
                        InterstitialPlacement singleISPlacement = parseSingleISPlacement(jSONArrayOptJSONArray3.optJSONObject(i4));
                        if (singleISPlacement != null) {
                            interstitialConfigurations2.addInterstitialPlacement(singleISPlacement);
                        }
                    }
                }
                String strOptString8 = section6.optString("backFill");
                if (!TextUtils.isEmpty(strOptString8)) {
                    interstitialConfigurations2.setBackFillProviderName(strOptString8);
                }
                String strOptString9 = section6.optString("premium");
                if (!TextUtils.isEmpty(strOptString9)) {
                    interstitialConfigurations2.setPremiumProviderName(strOptString9);
                }
                interstitialConfigurations = interstitialConfigurations2;
            } else {
                str2 = str;
                str3 = r12;
                str4 = r0;
                str5 = str15;
                str6 = str16;
                str7 = str18;
                str17 = "serverEventsURL";
                interstitialConfigurations = null;
            }
            if (section8 != null) {
                JSONArray jSONArrayOptJSONArray5 = section8.optJSONArray(str3);
                JSONObject section17 = getSection(section8, str4);
                int intConfigValue7 = getIntConfigValue(section8, section4, "maxNumOfAdaptersToLoadOnStart", 1);
                String str19 = str14;
                String str20 = str6;
                str10 = str17;
                JSONArray jSONArray = jSONArrayOptJSONArray5;
                str8 = str20;
                String str21 = str2;
                String str22 = str5;
                str11 = str21;
                long longConfigValue = getLongConfigValue(section8, section4, "atim", 10000L);
                int intConfigValue8 = getIntConfigValue(section8, section4, "delayLoadFailure", 3);
                int intConfigValue9 = getIntConfigValue(section8, section4, "bannerInterval", 60);
                JSONObject jSONObjectMergeJsons3 = IronSourceUtils.mergeJsons(section17, section9);
                boolean zOptBoolean4 = jSONObjectMergeJsons3.optBoolean(str7, false);
                String strOptString10 = jSONObjectMergeJsons3.optString(str10, "");
                String strOptString11 = jSONObjectMergeJsons3.optString(str11, "");
                int iOptInt7 = jSONObjectMergeJsons3.optInt(str8, -1);
                str9 = str22;
                int iOptInt8 = jSONObjectMergeJsons3.optInt(str9, -1);
                str12 = str19;
                int iOptInt9 = jSONObjectMergeJsons3.optInt(str12, 5000);
                JSONArray jSONArrayOptJSONArray6 = jSONObjectMergeJsons3.optJSONArray("optOut");
                if (jSONArrayOptJSONArray6 != null) {
                    int[] iArr7 = new int[jSONArrayOptJSONArray6.length()];
                    for (int i5 = 0; i5 < jSONArrayOptJSONArray6.length(); i5++) {
                        iArr7[i5] = jSONArrayOptJSONArray6.optInt(i5);
                    }
                    iArr2 = iArr7;
                } else {
                    iArr2 = null;
                }
                BannerConfigurations bannerConfigurations2 = new BannerConfigurations(intConfigValue7, longConfigValue, new ApplicationEvents(false, zOptBoolean4, strOptString10, strOptString11, iOptInt7, iOptInt8, iOptInt9, iArr2), intConfigValue9, intConfigValue8);
                if (jSONArray != null) {
                    int i6 = 0;
                    while (i6 < jSONArray.length()) {
                        JSONArray jSONArray2 = jSONArray;
                        BannerPlacement singleBNPlacement = parseSingleBNPlacement(jSONArray2.optJSONObject(i6));
                        if (singleBNPlacement != null) {
                            bannerConfigurations2.addBannerPlacement(singleBNPlacement);
                        }
                        i6++;
                        jSONArray = jSONArray2;
                    }
                }
                bannerConfigurations = bannerConfigurations2;
            } else {
                str8 = str6;
                str9 = str5;
                str10 = str17;
                str11 = str2;
                str12 = str14;
                bannerConfigurations = null;
            }
            if (section7 != null) {
                str13 = str4;
                JSONObject jSONObjectMergeJsons4 = IronSourceUtils.mergeJsons(getSection(section7, str13), section9);
                boolean zOptBoolean5 = jSONObjectMergeJsons4.optBoolean(str7, false);
                String strOptString12 = jSONObjectMergeJsons4.optString(str10, "");
                String strOptString13 = jSONObjectMergeJsons4.optString(str11, "");
                int iOptInt10 = jSONObjectMergeJsons4.optInt(str8, -1);
                int iOptInt11 = jSONObjectMergeJsons4.optInt(str9, -1);
                int iOptInt12 = jSONObjectMergeJsons4.optInt(str12, 5000);
                JSONArray jSONArrayOptJSONArray7 = jSONObjectMergeJsons4.optJSONArray("optOut");
                if (jSONArrayOptJSONArray7 != null) {
                    int[] iArr8 = new int[jSONArrayOptJSONArray7.length()];
                    for (int i7 = 0; i7 < jSONArrayOptJSONArray7.length(); i7++) {
                        iArr8[i7] = jSONArrayOptJSONArray7.optInt(i7);
                    }
                    iArr = iArr8;
                } else {
                    iArr = null;
                }
                OfferwallConfigurations offerwallConfigurations2 = new OfferwallConfigurations(new ApplicationEvents(false, zOptBoolean5, strOptString12, strOptString13, iOptInt10, iOptInt11, iOptInt12, iArr));
                JSONArray jSONArrayOptJSONArray8 = section7.optJSONArray(str3);
                if (jSONArrayOptJSONArray8 != null) {
                    for (int i8 = 0; i8 < jSONArrayOptJSONArray8.length(); i8++) {
                        OfferwallPlacement singleOWPlacement = parseSingleOWPlacement(jSONArrayOptJSONArray8.optJSONObject(i8));
                        if (singleOWPlacement != null) {
                            offerwallConfigurations2.addOfferwallPlacement(singleOWPlacement);
                        }
                    }
                }
                offerwallConfigurations = offerwallConfigurations2;
            } else {
                str13 = str4;
                offerwallConfigurations = null;
            }
            ApplicationConfigurations applicationConfigurations = new ApplicationConfigurations(new ApplicationLogger(section10.optInt(ServerLogger.NAME, 3), section10.optInt("publisher", 3), section10.optInt(ConsoleLogger.NAME, 3)), section11 != null ? new ServerSegmetData(section11.optString("name", ""), section11.optString("id", "-1"), section11.optJSONObject("custom")) : null, section4.optBoolean("integration", false));
            IronSourceUtils.saveBooleanToSharedPrefs(this.mContext, GeneralProperties.ALLOW_LOCATION_SHARED_PREFS_KEY, section4.optBoolean("allowLocation", false));
            this.mConfigurations = new Configurations(rewardedVideoConfigurations, interstitialConfigurations, offerwallConfigurations, bannerConfigurations, applicationConfigurations);
            JSONObject section18 = getSection(section9, "genericParams");
            if (section18 != null && (section = getSection(section18, str13)) != null) {
                section18.remove(str13);
                Map<String, String> jsonToStringMap = IronSourceUtils.parseJsonToStringMap(section);
                RewardedVideoEventsManager.getInstance().setEventGenericParams(jsonToStringMap);
                InterstitialEventsManager.getInstance().setEventGenericParams(jsonToStringMap);
            }
            if (section18 != null) {
                Map<String, String> jsonToStringMap2 = IronSourceUtils.parseJsonToStringMap(section18);
                RewardedVideoEventsManager.getInstance().setBatchParams(jsonToStringMap2);
                InterstitialEventsManager.getInstance().setBatchParams(jsonToStringMap2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getIntConfigValue(JSONObject jSONObject, JSONObject jSONObject2, String str, int i) {
        int iOptInt;
        if (jSONObject.has(str)) {
            iOptInt = jSONObject.optInt(str, 0);
        } else {
            iOptInt = jSONObject2.has(str) ? jSONObject2.optInt(str, 0) : 0;
        }
        return iOptInt == 0 ? i : iOptInt;
    }

    private long getLongConfigValue(JSONObject jSONObject, JSONObject jSONObject2, String str, long j) {
        long jOptLong;
        if (jSONObject.has(str)) {
            jOptLong = jSONObject.optLong(str, 0L);
        } else {
            jOptLong = jSONObject2.has(str) ? jSONObject2.optLong(str, 0L) : 0L;
        }
        return jOptLong == 0 ? j : jOptLong;
    }

    private Placement parseSingleRVPlacement(JSONObject jSONObject) {
        if (jSONObject != null) {
            int iOptInt = jSONObject.optInt(Constants.PLACEMENT_ID, -1);
            String strOptString = jSONObject.optString("placementName", "");
            boolean zOptBoolean = jSONObject.optBoolean("isDefault", false);
            String strOptString2 = jSONObject.optString("virtualItemName", "");
            int iOptInt2 = jSONObject.optInt("virtualItemCount", -1);
            PlacementAvailabilitySettings placementAvailabilitySettings = getPlacementAvailabilitySettings(jSONObject);
            if (iOptInt >= 0 && !TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2) && iOptInt2 > 0) {
                Placement placement = new Placement(iOptInt, strOptString, zOptBoolean, strOptString2, iOptInt2, placementAvailabilitySettings);
                if (placementAvailabilitySettings == null) {
                    return placement;
                }
                CappingManager.addCappingInfo(this.mContext, placement);
                return placement;
            }
        }
        return null;
    }

    private InterstitialPlacement parseSingleISPlacement(JSONObject jSONObject) {
        if (jSONObject != null) {
            int iOptInt = jSONObject.optInt(Constants.PLACEMENT_ID, -1);
            String strOptString = jSONObject.optString("placementName", "");
            boolean zOptBoolean = jSONObject.optBoolean("isDefault", false);
            PlacementAvailabilitySettings placementAvailabilitySettings = getPlacementAvailabilitySettings(jSONObject);
            if (iOptInt >= 0 && !TextUtils.isEmpty(strOptString)) {
                InterstitialPlacement interstitialPlacement = new InterstitialPlacement(iOptInt, strOptString, zOptBoolean, placementAvailabilitySettings);
                if (placementAvailabilitySettings == null) {
                    return interstitialPlacement;
                }
                CappingManager.addCappingInfo(this.mContext, interstitialPlacement);
                return interstitialPlacement;
            }
        }
        return null;
    }

    private OfferwallPlacement parseSingleOWPlacement(JSONObject jSONObject) {
        if (jSONObject != null) {
            int iOptInt = jSONObject.optInt(Constants.PLACEMENT_ID, -1);
            String strOptString = jSONObject.optString("placementName", "");
            boolean zOptBoolean = jSONObject.optBoolean("isDefault", false);
            if (iOptInt >= 0 && !TextUtils.isEmpty(strOptString)) {
                return new OfferwallPlacement(iOptInt, strOptString, zOptBoolean);
            }
        }
        return null;
    }

    private BannerPlacement parseSingleBNPlacement(JSONObject jSONObject) {
        if (jSONObject != null) {
            int iOptInt = jSONObject.optInt(Constants.PLACEMENT_ID, -1);
            String strOptString = jSONObject.optString("placementName", "");
            boolean zOptBoolean = jSONObject.optBoolean("isDefault", false);
            PlacementAvailabilitySettings placementAvailabilitySettings = getPlacementAvailabilitySettings(jSONObject);
            if (iOptInt >= 0 && !TextUtils.isEmpty(strOptString)) {
                BannerPlacement bannerPlacement = new BannerPlacement(iOptInt, strOptString, zOptBoolean, placementAvailabilitySettings);
                if (placementAvailabilitySettings == null) {
                    return bannerPlacement;
                }
                CappingManager.addCappingInfo(this.mContext, bannerPlacement);
                return bannerPlacement;
            }
        }
        return null;
    }

    private PlacementAvailabilitySettings getPlacementAvailabilitySettings(JSONObject jSONObject) {
        PlacementCappingType placementCappingType = null;
        if (jSONObject == null) {
            return null;
        }
        PlacementAvailabilitySettings.PlacementAvailabilitySettingsBuilder placementAvailabilitySettingsBuilder = new PlacementAvailabilitySettings.PlacementAvailabilitySettingsBuilder();
        placementAvailabilitySettingsBuilder.delivery(jSONObject.optBoolean(bj.gV, true));
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("capping");
        if (jSONObjectOptJSONObject != null) {
            String strOptString = jSONObjectOptJSONObject.optString("unit");
            if (!TextUtils.isEmpty(strOptString)) {
                if (PlacementCappingType.PER_DAY.toString().equals(strOptString)) {
                    placementCappingType = PlacementCappingType.PER_DAY;
                } else if (PlacementCappingType.PER_HOUR.toString().equals(strOptString)) {
                    placementCappingType = PlacementCappingType.PER_HOUR;
                }
            }
            int iOptInt = jSONObjectOptJSONObject.optInt("maxImpressions", 0);
            placementAvailabilitySettingsBuilder.capping(jSONObjectOptJSONObject.optBoolean("enabled", false) && iOptInt > 0, placementCappingType, iOptInt);
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("pacing");
        if (jSONObjectOptJSONObject2 != null) {
            int iOptInt2 = jSONObjectOptJSONObject2.optInt("numOfSeconds", 0);
            placementAvailabilitySettingsBuilder.pacing(jSONObjectOptJSONObject2.optBoolean("enabled", false) && iOptInt2 > 0, iOptInt2);
        }
        return placementAvailabilitySettingsBuilder.build();
    }

    private JSONObject getSection(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject(str);
        }
        return null;
    }

    public String getRVBackFillProvider() {
        try {
            return this.mProviderOrder.getRVBackFillProvider();
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.INTERNAL, "getRVBackFillProvider", e);
            return null;
        }
    }

    public String getRVPremiumProvider() {
        try {
            return this.mProviderOrder.getRVPremiumProvider();
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.INTERNAL, "getRVPremiumProvider", e);
            return null;
        }
    }

    public ProviderSettingsHolder getProviderSettingsHolder() {
        return this.mProviderSettingsHolder;
    }

    public ProviderOrder getProviderOrder() {
        return this.mProviderOrder;
    }

    public Configurations getConfigurations() {
        return this.mConfigurations;
    }

    private Context getContext() {
        return this.mContext;
    }
}
