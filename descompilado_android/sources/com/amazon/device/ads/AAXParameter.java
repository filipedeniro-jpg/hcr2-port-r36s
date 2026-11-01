package com.amazon.device.ads;

import android.content.Context;
import android.location.Location;
import com.appsflyer.share.Constants;
import com.facebook.share.internal.ShareConstants;
import com.my.target.az;
import com.vungle.warren.model.Cookie;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
abstract class AAXParameter<T> {
    private static final String LOGTAG = "AAXParameter";
    private final String debugName;
    private final String name;
    static final AAXParameter<String> APP_KEY = new AppKeyParameter();
    static final AAXParameter<String> CHANNEL = new StringParameter(Constants.URL_CAMPAIGN, DebugProperties.DEBUG_CHANNEL);
    static final PublisherKeywordsParameter PUBLISHER_KEYWORDS = new PublisherKeywordsParameter();
    static final AAXParameter<JSONArray> PUBLISHER_ASINS = new JSONArrayParameter("pa", DebugProperties.DEBUG_PA);
    static final AAXParameter<String> USER_AGENT = new UserAgentParameter();
    static final AAXParameter<String> SDK_VERSION = new SDKVersionParameter();
    static final AAXParameter<String> GEOLOCATION = new GeoLocationParameter();
    static final AAXParameter<JSONObject> DEVICE_INFO = new DeviceInfoParameter();
    static final AAXParameter<JSONObject> PACKAGE_INFO = new PackageInfoParameter();
    static final AAXParameter<Boolean> TEST = new TestParameter();
    static final AAXParameter<JSONArray> SLOTS = new JSONArrayParameter("slots", DebugProperties.DEBUG_SLOTS);
    static final AAXParameter<Boolean> OPT_OUT = new OptOutParameter();
    static final AAXParameter<String> SIZE = new SizeParameter();
    static final AAXParameter<String> PAGE_TYPE = new StringParameter("pt", DebugProperties.DEBUG_PT);
    static final AAXParameter<String> SLOT = new SlotParameter();
    static final AAXParameter<String> SLOT_POSITION = new StringParameter("sp", DebugProperties.DEBUG_SP);
    static final AAXParameter<String> MAX_SIZE = new MaxSizeParameter();
    static final AAXParameter<Integer> SLOT_ID = new SlotIdParameter();
    static final AAXParameter<Long> FLOOR_PRICE = new FloorPriceParameter();
    static final AAXParameter<JSONArray> SUPPORTED_MEDIA_TYPES = new SupportedMediaTypesParameter();
    static final AAXParameter<JSONObject> VIDEO_OPTIONS = new VideoOptionsParameter();

    protected T applyPostParameterProcessing(T t, ParameterData parameterData) {
        return t;
    }

    protected T getDerivedValue(ParameterData parameterData) {
        return null;
    }

    protected abstract T getFromDebugProperties();

    protected abstract T parseFromString(String str);

    AAXParameter(String str, String str2) {
        this.name = str;
        this.debugName = str2;
    }

    String getName() {
        return this.name;
    }

    protected String getDebugName() {
        return this.debugName;
    }

    protected boolean hasDebugPropertiesValue() {
        return DebugProperties.getInstance().containsDebugProperty(this.debugName);
    }

    T getValueDoNotRemove(ParameterData parameterData) {
        return getValueHelper(parameterData, false);
    }

    T getValue(ParameterData parameterData) {
        return getValueHelper(parameterData, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private T getValueHelper(ParameterData parameterData, boolean z) {
        T fromDebugProperties = hasDebugPropertiesValue() ? getFromDebugProperties() : null;
        if (parameterData.advancedOptions != null) {
            String str = z ? (String) parameterData.advancedOptions.remove(this.name) : (String) parameterData.advancedOptions.get(this.name);
            if (fromDebugProperties == null && !StringUtils.isNullOrEmpty(str)) {
                fromDebugProperties = parseFromString(str);
            }
        }
        if (fromDebugProperties == null) {
            fromDebugProperties = getDerivedValue(parameterData);
        }
        T tApplyPostParameterProcessing = applyPostParameterProcessing(fromDebugProperties, parameterData);
        if ((tApplyPostParameterProcessing instanceof String) && StringUtils.isNullOrWhiteSpace((String) tApplyPostParameterProcessing)) {
            return null;
        }
        return tApplyPostParameterProcessing;
    }

    static class ParameterData {
        private AdRequest adRequest;
        private AdTargetingOptions adTargetingOptions;
        private Map<String, String> advancedOptions;
        private AdRequest.LOISlot loiSlot;
        private Map<String, String> temporaryOptions = new HashMap();

        ParameterData setAdRequest(AdRequest adRequest) {
            this.adRequest = adRequest;
            return this;
        }

        ParameterData setAdvancedOptions(Map<String, String> map) {
            this.advancedOptions = map;
            return this;
        }

        Map<String, String> getInternalAdvancedOptions() {
            return this.advancedOptions;
        }

        ParameterData setLOISlot(AdRequest.LOISlot lOISlot) {
            this.loiSlot = lOISlot;
            return this;
        }

        AdRequest getAdRequest() {
            return this.adRequest;
        }

        ParameterData setAdTargetingOptions(AdTargetingOptions adTargetingOptions) {
            this.adTargetingOptions = adTargetingOptions;
            return this;
        }
    }

    static class StringParameter extends AAXParameter<String> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public String parseFromString(String str) {
            return str;
        }

        StringParameter(String str, String str2) {
            super(str, str2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public String getFromDebugProperties() {
            return DebugProperties.getInstance().getDebugPropertyAsString(getDebugName(), null);
        }
    }

    static class BooleanParameter extends AAXParameter<Boolean> {
        BooleanParameter(String str, String str2) {
            super(str, str2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.device.ads.AAXParameter
        public Boolean parseFromString(String str) {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.device.ads.AAXParameter
        public Boolean getFromDebugProperties() {
            return DebugProperties.getInstance().getDebugPropertyAsBoolean(getDebugName(), null);
        }
    }

    static class IntegerParameter extends AAXParameter<Integer> {
        IntegerParameter(String str, String str2) {
            super(str, str2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.device.ads.AAXParameter
        public Integer parseFromString(String str) {
            return Integer.valueOf(Integer.parseInt(str));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.device.ads.AAXParameter
        public Integer getFromDebugProperties() {
            return DebugProperties.getInstance().getDebugPropertyAsInteger(getDebugName(), null);
        }
    }

    static class LongParameter extends AAXParameter<Long> {
        LongParameter(String str, String str2) {
            super(str, str2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.device.ads.AAXParameter
        public Long parseFromString(String str) {
            return Long.valueOf(Long.parseLong(str));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.device.ads.AAXParameter
        public Long getFromDebugProperties() {
            return DebugProperties.getInstance().getDebugPropertyAsLong(getDebugName(), null);
        }
    }

    static class JSONArrayParameter extends AAXParameter<JSONArray> {
        private final MobileAdsLogger logger;

        JSONArrayParameter(String str, String str2) {
            super(str, str2);
            this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(AAXParameter.LOGTAG);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public JSONArray parseFromString(String str) {
            try {
                return new JSONArray(str);
            } catch (JSONException unused) {
                this.logger.e("Unable to parse the following value into a JSONArray: %s", getName());
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public JSONArray getFromDebugProperties() {
            return parseFromString(DebugProperties.getInstance().getDebugPropertyAsString(getDebugName(), null));
        }
    }

    static class JSONObjectParameter extends AAXParameter<JSONObject> {
        private final MobileAdsLogger logger;

        JSONObjectParameter(String str, String str2) {
            super(str, str2);
            this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(AAXParameter.LOGTAG);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public JSONObject parseFromString(String str) {
            try {
                return new JSONObject(str);
            } catch (JSONException unused) {
                this.logger.e("Unable to parse the following value into a JSONObject: %s", getName());
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public JSONObject getFromDebugProperties() {
            return parseFromString(DebugProperties.getInstance().getDebugPropertyAsString(getDebugName(), null));
        }
    }

    static class AppKeyParameter extends StringParameter {
        AppKeyParameter() {
            super(Cookie.APP_ID, DebugProperties.DEBUG_APPID);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public String getDerivedValue(ParameterData parameterData) {
            return MobileAdsInfoStore.getInstance().getRegistrationInfo().getAppKey();
        }
    }

    static class PublisherKeywordsParameter extends JSONArrayParameter {
        PublisherKeywordsParameter() {
            super("pk", DebugProperties.DEBUG_PK);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public JSONArray applyPostParameterProcessing(JSONArray jSONArray, ParameterData parameterData) {
            HashSet<String> internalPublisherKeywords;
            if (jSONArray == null) {
                jSONArray = new JSONArray();
            }
            if (parameterData.adTargetingOptions != null && (internalPublisherKeywords = parameterData.adTargetingOptions.getInternalPublisherKeywords()) != null) {
                Iterator<String> it = internalPublisherKeywords.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
            }
            return jSONArray;
        }
    }

    static class UserAgentParameter extends StringParameter {
        UserAgentParameter() {
            super("ua", DebugProperties.DEBUG_UA);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public String getDerivedValue(ParameterData parameterData) {
            return MobileAdsInfoStore.getInstance().getDeviceInfo().getUserAgentString();
        }
    }

    static class SDKVersionParameter extends StringParameter {
        SDKVersionParameter() {
            super("adsdk", DebugProperties.DEBUG_VER);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public String getDerivedValue(ParameterData parameterData) {
            return Version.getSDKVersion();
        }
    }

    static class GeoLocationParameter extends StringParameter {
        private final Configuration configuration;
        private final Context context;

        GeoLocationParameter() {
            this(Configuration.getInstance(), MobileAdsInfoStore.getInstance().getApplicationContext());
        }

        GeoLocationParameter(Configuration configuration, Context context) {
            super("geoloc", DebugProperties.DEBUG_GEOLOC);
            this.configuration = configuration;
            this.context = context;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public String getDerivedValue(ParameterData parameterData) {
            Location location;
            if (!this.configuration.getBoolean(Configuration.ConfigOption.SEND_GEO) || !parameterData.getAdRequest().getAdTargetingOptions().isGeoLocationEnabled() || (location = new AdLocation(this.context).getLocation()) == null) {
                return null;
            }
            return location.getLatitude() + "," + location.getLongitude();
        }
    }

    static class DeviceInfoParameter extends JSONObjectParameter {
        DeviceInfoParameter() {
            super("dinfo", DebugProperties.DEBUG_DINFO);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public JSONObject getDerivedValue(ParameterData parameterData) {
            return MobileAdsInfoStore.getInstance().getDeviceInfo().toJsonObject(parameterData.adRequest.getOrientation());
        }
    }

    static class PackageInfoParameter extends JSONObjectParameter {
        PackageInfoParameter() {
            super("pkg", DebugProperties.DEBUG_PKG);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public JSONObject getDerivedValue(ParameterData parameterData) {
            return MobileAdsInfoStore.getInstance().getAppInfo().getPackageInfoJSON();
        }
    }

    static class TestParameter extends BooleanParameter {
        TestParameter() {
            super("isTest", DebugProperties.DEBUG_TEST);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.device.ads.AAXParameter
        public Boolean getDerivedValue(ParameterData parameterData) {
            return Settings.getInstance().getBoolean("testingEnabled", (Boolean) null);
        }
    }

    static class OptOutParameter extends BooleanParameter {
        OptOutParameter() {
            super("oo", DebugProperties.DEBUG_OPT_OUT);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.device.ads.AAXParameter
        public Boolean getDerivedValue(ParameterData parameterData) {
            if (parameterData.adRequest.getAdvertisingIdentifierInfo().hasAdvertisingIdentifier()) {
                return Boolean.valueOf(parameterData.adRequest.getAdvertisingIdentifierInfo().isLimitAdTrackingEnabled());
            }
            return null;
        }
    }

    static class SizeParameter extends StringParameter {
        SizeParameter() {
            super("sz", DebugProperties.DEBUG_SIZE);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public String getDerivedValue(ParameterData parameterData) {
            return parameterData.loiSlot.getAdSlot().getRequestedAdSize().toString();
        }
    }

    static class SlotParameter extends StringParameter {
        SlotParameter() {
            super(az.b.ep, DebugProperties.DEBUG_SLOT);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public String getDerivedValue(ParameterData parameterData) {
            return parameterData.adRequest.getOrientation();
        }
    }

    static class MaxSizeParameter extends StringParameter {
        MaxSizeParameter() {
            super("mxsz", DebugProperties.DEBUG_MXSZ);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public String getDerivedValue(ParameterData parameterData) {
            return parameterData.loiSlot.getAdSlot().getMaxSize();
        }
    }

    static class SlotIdParameter extends IntegerParameter {
        SlotIdParameter() {
            super("slotId", DebugProperties.DEBUG_SLOT_ID);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.device.ads.AAXParameter
        public Integer getDerivedValue(ParameterData parameterData) {
            return Integer.valueOf(parameterData.loiSlot.getAdSlot().getSlotNumber());
        }
    }

    static class FloorPriceParameter extends LongParameter {
        FloorPriceParameter() {
            super("ec", DebugProperties.DEBUG_ECPM);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.device.ads.AAXParameter
        public Long getDerivedValue(ParameterData parameterData) {
            if (parameterData.loiSlot.getAdTargetingOptions().hasFloorPrice()) {
                return Long.valueOf(parameterData.loiSlot.getAdTargetingOptions().getFloorPrice());
            }
            return null;
        }
    }

    static class SupportedMediaTypesParameter extends JSONArrayParameter {
        public SupportedMediaTypesParameter() {
            super("supportedMediaTypes", DebugProperties.DEBUG_SUPPORTED_MEDIA_TYPES);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public JSONArray getDerivedValue(ParameterData parameterData) {
            JSONArray jSONArray = new JSONArray();
            addDisplay(parameterData, jSONArray);
            addVideo(parameterData, jSONArray);
            return jSONArray;
        }

        private void addDisplay(ParameterData parameterData, JSONArray jSONArray) {
            boolean zIsDisplayAdsEnabled = parameterData.loiSlot.getAdTargetingOptions().isDisplayAdsEnabled();
            if (parameterData.advancedOptions.containsKey("enableDisplayAds")) {
                zIsDisplayAdsEnabled = Boolean.parseBoolean((String) parameterData.advancedOptions.remove("enableDisplayAds"));
            }
            if (zIsDisplayAdsEnabled) {
                jSONArray.put("DISPLAY");
            }
        }

        private void addVideo(ParameterData parameterData, JSONArray jSONArray) {
            if (new VideoAdsEnabledChecker(parameterData).isVideoAdsEnabled()) {
                jSONArray.put(ShareConstants.VIDEO_URL);
            }
        }
    }

    static class VideoOptionsParameter extends JSONObjectParameter {
        private static final int MAXIMUM_DURATION_DEFAULT = 30000;
        private static final int MINIMUM_DURATION_DEFAULT = 0;

        public VideoOptionsParameter() {
            super("video", DebugProperties.DEBUG_VIDEO_OPTIONS);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.device.ads.AAXParameter
        public JSONObject getDerivedValue(ParameterData parameterData) {
            if (!new VideoAdsEnabledChecker(parameterData).isVideoAdsEnabled()) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            JSONUtils.put(jSONObject, "minAdDuration", parameterData.advancedOptions.containsKey("minVideoAdDuration") ? new Parsers.IntegerParser().setDefaultValue(0).setParseErrorLogTag(AAXParameter.LOGTAG).setParseErrorLogMessage("The minVideoAdDuration advanced option could not be parsed properly.").parse((String) parameterData.advancedOptions.remove("minVideoAdDuration")) : 0);
            JSONUtils.put(jSONObject, "maxAdDuration", parameterData.advancedOptions.containsKey("maxVideoAdDuration") ? new Parsers.IntegerParser().setDefaultValue(30000).setParseErrorLogTag(AAXParameter.LOGTAG).setParseErrorLogMessage("The maxVideoAdDuration advanced option could not be parsed properly.").parse((String) parameterData.advancedOptions.remove("maxVideoAdDuration")) : 30000);
            return jSONObject;
        }
    }

    private static class VideoAdsEnabledChecker {
        private final ParameterData parameterData;

        public VideoAdsEnabledChecker(ParameterData parameterData) {
            this.parameterData = parameterData;
        }

        public boolean isVideoAdsEnabled() {
            if (!this.parameterData.loiSlot.getAdTargetingOptions().isVideoEnabledSettable()) {
                return false;
            }
            if (!this.parameterData.advancedOptions.containsKey("enableVideoAds")) {
                return this.parameterData.temporaryOptions.containsKey("enableVideoAds") ? Boolean.parseBoolean((String) this.parameterData.temporaryOptions.get("enableVideoAds")) : this.parameterData.loiSlot.getAdTargetingOptions().isVideoAdsEnabled();
            }
            String str = (String) this.parameterData.advancedOptions.remove("enableVideoAds");
            this.parameterData.temporaryOptions.put("enableVideoAds", str);
            return Boolean.parseBoolean(str);
        }
    }
}
