package com.vungle.warren.model;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.target.bj;
import com.vungle.warren.AdConfig;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.persistence.MemoryUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.nend.android.NendAdNativeMediaView;

/* JADX INFO: loaded from: classes2.dex */
public class Advertisement implements Memorable, Cloneable {
    public static final int DONE = 3;
    public static final int ERROR = 4;
    public static final String KEY_APP_ICON = "appicon";
    public static final String KEY_POSTROLL = "postroll";
    public static final String KEY_POWERED_BY_VUNGLE = "powervungle";
    public static final String KEY_TEMPLATE = "template";
    public static final String KEY_VIDEO = "video";
    public static final int LANDSCAPE = 1;
    public static final int NEW = 0;
    public static final int PORTRAIT = 0;
    public static final String POSTROLL_UNZIP = "postrollUnzip";
    public static final int READY = 1;
    public static final int ROTATE = 2;
    private static final String TAG = "Advertisement";
    public static final int TYPE_VUNGLE_LOCAL = 0;
    public static final int TYPE_VUNGLE_MRAID = 1;
    public static final int VIEWING = 2;
    private AdConfig adConfig;
    private final String adMarketId;
    private final String adToken;

    @AdType
    private final int adType;
    private final String appID;
    private final String bidToken;
    private final String campaign;
    private final ArrayList<Checkpoint> checkpoints;
    private final String[] clickUrls;
    private final String[] closeUrls;
    private final int countdown;
    private final int ctaClickArea;
    private final String ctaDestinationUrl;
    private final boolean ctaOverlayEnabled;
    private final boolean ctaShowOnClick;
    private final int ctaTimeEnabled;
    private final int ctaTimeShow;
    private final String ctaUrl;
    private final int delay;
    private final boolean enableMoat;
    private final long expireTime;
    private final String identifier;
    private final String md5;
    private final String moatExtraVast;
    private final String[] muteUrls;
    private final String[] postRollClickUrls;
    private final String[] postRollViewUrls;
    private final String postrollBundleUrl;
    private final boolean requiresNonMarketInstall;
    private final int retryCount;
    private final int showCloseDelay;
    private final int showCloseIncentivized;
    private int state;
    private final String templateId;
    private final Map<String, String> templateSettings;
    private final String templateType;
    private final String templateUrl;
    private final String[] unmuteUrls;
    private final int videoHeight;
    private final String videoIdentifier;
    private final String videoUrl;
    private final int videoWidth;

    /* JADX INFO: loaded from: classes.dex */
    public @interface AdType {
    }

    /* JADX INFO: loaded from: classes.dex */
    public @interface CacheKey {
    }

    /* JADX INFO: loaded from: classes.dex */
    public @interface Orientation {
    }

    /* JADX INFO: loaded from: classes.dex */
    public @interface State {
    }

    public Advertisement(byte[] bArr) {
        this.state = 0;
        if (bArr.length == 0) {
            throw new IllegalArgumentException("Empty array cannot be used to construct Advertisement");
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        this.adConfig = new AdConfig();
        this.adType = byteBufferWrap.getInt();
        this.expireTime = byteBufferWrap.getLong();
        this.delay = byteBufferWrap.getInt();
        this.showCloseDelay = byteBufferWrap.getInt();
        this.showCloseIncentivized = byteBufferWrap.getInt();
        this.countdown = byteBufferWrap.getInt();
        this.videoWidth = byteBufferWrap.getInt();
        this.videoHeight = byteBufferWrap.getInt();
        this.ctaOverlayEnabled = byteBufferWrap.get() == 1;
        this.ctaShowOnClick = byteBufferWrap.get() == 1;
        this.ctaTimeEnabled = byteBufferWrap.getInt();
        this.ctaTimeShow = byteBufferWrap.getInt();
        this.ctaClickArea = byteBufferWrap.getInt();
        this.retryCount = byteBufferWrap.getInt();
        this.enableMoat = byteBufferWrap.get() == 1;
        this.requiresNonMarketInstall = byteBufferWrap.get() == 1;
        int i = byteBufferWrap.getInt();
        int i2 = byteBufferWrap.getInt();
        int i3 = byteBufferWrap.getInt();
        this.adConfig.setFlexViewCloseTime(i2);
        this.adConfig.setOrdinal(i3);
        this.adConfig.setAutoRotate((i & 16) == 16);
        this.adConfig.setImmersiveMode((i & 4) == 4);
        this.adConfig.setMuted((i & 1) == 1);
        HashMap map = new HashMap();
        map.put(AdConfig.FLAG_DIRECT_DOWNLOAD, Boolean.valueOf((i & 32) == 32));
        this.adConfig.setExtraSettings(map);
        this.adConfig.setTransitionAnimationEnabled((i & 8) == 8);
        this.identifier = MemoryUtils.extractString(byteBufferWrap);
        this.appID = MemoryUtils.extractString(byteBufferWrap);
        this.campaign = MemoryUtils.extractString(byteBufferWrap);
        this.videoUrl = MemoryUtils.extractString(byteBufferWrap);
        this.md5 = MemoryUtils.extractString(byteBufferWrap);
        this.postrollBundleUrl = MemoryUtils.extractString(byteBufferWrap);
        this.ctaDestinationUrl = MemoryUtils.extractString(byteBufferWrap);
        this.ctaUrl = MemoryUtils.extractString(byteBufferWrap);
        this.adToken = MemoryUtils.extractString(byteBufferWrap);
        this.videoIdentifier = MemoryUtils.extractString(byteBufferWrap);
        this.muteUrls = MemoryUtils.extractStringArray(byteBufferWrap);
        this.unmuteUrls = MemoryUtils.extractStringArray(byteBufferWrap);
        this.closeUrls = MemoryUtils.extractStringArray(byteBufferWrap);
        this.postRollClickUrls = MemoryUtils.extractStringArray(byteBufferWrap);
        this.postRollViewUrls = MemoryUtils.extractStringArray(byteBufferWrap);
        this.clickUrls = MemoryUtils.extractStringArray(byteBufferWrap);
        this.templateUrl = MemoryUtils.extractString(byteBufferWrap);
        this.templateId = MemoryUtils.extractString(byteBufferWrap);
        this.templateType = MemoryUtils.extractString(byteBufferWrap);
        this.moatExtraVast = MemoryUtils.extractString(byteBufferWrap);
        this.adMarketId = MemoryUtils.extractString(byteBufferWrap);
        this.bidToken = MemoryUtils.extractString(byteBufferWrap);
        int i4 = byteBufferWrap.getInt();
        this.checkpoints = new ArrayList<>(i4);
        for (int i5 = 0; i5 < i4; i5++) {
            try {
                this.checkpoints.add((Checkpoint) MemoryUtils.extractMemorable(byteBufferWrap, Checkpoint.class));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e2) {
                throw new RuntimeException(e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException(e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException(e4);
            }
        }
        this.templateSettings = MemoryUtils.extractStringMap(byteBufferWrap);
        this.state = byteBufferWrap.getInt();
    }

    public boolean isCtaOverlayEnabled() {
        return this.ctaOverlayEnabled;
    }

    public boolean isCtaShowOnClick() {
        return this.ctaShowOnClick;
    }

    public int getCtaTimeEnabled() {
        return this.ctaTimeEnabled * 1000;
    }

    public int getCtaTimeShow() {
        return this.ctaTimeShow * 1000;
    }

    public int getCtaClickArea() {
        int i = this.ctaClickArea;
        if (i >= 1) {
            return i;
        }
        return 1;
    }

    public boolean isRequiresNonMarketInstall() {
        return this.requiresNonMarketInstall;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x004b  */
    public Advertisement(@NonNull JsonObject jsonObject) throws IllegalArgumentException {
        byte b;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        this.state = 0;
        if (!JsonUtil.hasNonNull(jsonObject, "ad_markup")) {
            throw new IllegalArgumentException("JSON does not contain ad markup!");
        }
        JsonObject asJsonObject = jsonObject.getAsJsonObject("ad_markup");
        if (asJsonObject.has("adType")) {
            String asString = asJsonObject.get("adType").getAsString();
            int iHashCode = asString.hashCode();
            if (iHashCode != -1852456483) {
                if (iHashCode == -1851445271 && asString.equals("vungle_mraid")) {
                    b = 1;
                } else {
                    b = -1;
                }
            } else if (asString.equals("vungle_local")) {
                b = 0;
            } else {
                b = -1;
            }
            if (b == 0) {
                this.adType = 0;
                this.postrollBundleUrl = JsonUtil.hasNonNull(asJsonObject, "postBundle") ? asJsonObject.get("postBundle").getAsString() : "";
                this.templateSettings = new HashMap();
                this.templateUrl = "";
                this.templateId = "";
                this.templateType = "";
            } else if (b == 1) {
                this.adType = 1;
                this.postrollBundleUrl = "";
                if (JsonUtil.hasNonNull(asJsonObject, "templateSettings")) {
                    this.templateSettings = new HashMap();
                    JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("templateSettings");
                    if (JsonUtil.hasNonNull(asJsonObject2, "normal_replacements")) {
                        for (Map.Entry<String, JsonElement> entry : asJsonObject2.getAsJsonObject("normal_replacements").entrySet()) {
                            this.templateSettings.put(entry.getKey(), entry.getValue().getAsString());
                        }
                    }
                    if (JsonUtil.hasNonNull(asJsonObject2, "cacheable_replacements")) {
                        for (Map.Entry<String, JsonElement> entry2 : asJsonObject2.getAsJsonObject("cacheable_replacements").entrySet()) {
                            if (JsonUtil.hasNonNull(entry2.getValue(), "url")) {
                                this.templateSettings.put(entry2.getKey(), entry2.getValue().getAsJsonObject().get("url").getAsString());
                            }
                        }
                    }
                    if (JsonUtil.hasNonNull(asJsonObject, "templateId")) {
                        this.templateId = asJsonObject.get("templateId").getAsString();
                        if (JsonUtil.hasNonNull(asJsonObject, MessengerShareContentUtility.TEMPLATE_TYPE)) {
                            this.templateType = asJsonObject.get(MessengerShareContentUtility.TEMPLATE_TYPE).getAsString();
                            if (JsonUtil.hasNonNull(asJsonObject, "templateURL")) {
                                this.templateUrl = asJsonObject.get("templateURL").getAsString();
                            } else {
                                throw new IllegalArgumentException("Template URL missing!");
                            }
                        } else {
                            throw new IllegalArgumentException("Template Type missing!");
                        }
                    } else {
                        throw new IllegalArgumentException("Missing templateID!");
                    }
                } else {
                    throw new IllegalArgumentException("Missing template adConfig!");
                }
            } else {
                throw new IllegalArgumentException("Unknown Ad Type " + asString + "! Please add this ad type");
            }
            if (JsonUtil.hasNonNull(asJsonObject, "id")) {
                this.identifier = asJsonObject.get("id").getAsString();
                if (JsonUtil.hasNonNull(asJsonObject, FirebaseAnalytics.Param.CAMPAIGN)) {
                    this.campaign = asJsonObject.get(FirebaseAnalytics.Param.CAMPAIGN).getAsString();
                    if (JsonUtil.hasNonNull(asJsonObject, "app_id")) {
                        this.appID = asJsonObject.get("app_id").getAsString();
                        if (JsonUtil.hasNonNull(asJsonObject, "expiry") && !asJsonObject.get("expiry").isJsonNull()) {
                            long asLong = asJsonObject.get("expiry").getAsLong();
                            if (asLong > 0) {
                                this.expireTime = asLong;
                            } else {
                                this.expireTime = System.currentTimeMillis() / 1000;
                            }
                        } else {
                            this.expireTime = System.currentTimeMillis() / 1000;
                        }
                        if (JsonUtil.hasNonNull(asJsonObject, "tpat")) {
                            JsonObject asJsonObject3 = asJsonObject.getAsJsonObject("tpat");
                            this.checkpoints = new ArrayList<>(5);
                            int i = this.adType;
                            if (i != 0) {
                                if (i != 1) {
                                    throw new IllegalArgumentException("Unknown Ad Type!");
                                }
                                for (int i2 = 0; i2 < 5; i2++) {
                                    int i3 = i2 * 25;
                                    String str6 = String.format(Locale.ENGLISH, "checkpoint.%d", Integer.valueOf(i3));
                                    this.checkpoints.add(i2, asJsonObject3.has(str6) ? new Checkpoint(asJsonObject3.getAsJsonArray(str6), (byte) i3) : null);
                                }
                            } else if (asJsonObject3.has("play_percentage")) {
                                JsonArray asJsonArray = asJsonObject3.getAsJsonArray("play_percentage");
                                for (int i4 = 0; i4 < asJsonArray.size(); i4++) {
                                    this.checkpoints.add(new Checkpoint(asJsonArray.get(i4).getAsJsonObject()));
                                }
                                Collections.sort(this.checkpoints);
                            }
                            if (asJsonObject3.has("clickUrl")) {
                                JsonArray asJsonArray2 = asJsonObject3.getAsJsonArray("clickUrl");
                                this.clickUrls = new String[asJsonArray2.size()];
                                Iterator<JsonElement> it = asJsonArray2.iterator();
                                int i5 = 0;
                                while (it.hasNext()) {
                                    this.clickUrls[i5] = it.next().getAsString();
                                    i5++;
                                }
                            } else {
                                this.clickUrls = new String[0];
                            }
                            if (asJsonObject3.has("moat")) {
                                JsonObject asJsonObject4 = asJsonObject3.getAsJsonObject("moat");
                                this.enableMoat = asJsonObject4.get("is_enabled").getAsBoolean();
                                this.moatExtraVast = asJsonObject4.get("extra_vast").getAsString();
                            } else {
                                this.enableMoat = false;
                                this.moatExtraVast = "";
                            }
                            int i6 = this.adType;
                            if (i6 == 0) {
                                str = bj.gv;
                                str2 = bj.gw;
                                str3 = "video_close";
                                str4 = "postroll_click";
                                str5 = "postroll_view";
                            } else {
                                if (i6 != 1) {
                                    throw new IllegalArgumentException("Unknown AdType!");
                                }
                                str = "video.mute";
                                str2 = "video.unmute";
                                str3 = "video.close";
                                str4 = "postroll.click";
                                str5 = "postroll.view";
                            }
                            if (JsonUtil.hasNonNull(asJsonObject3, str)) {
                                JsonArray asJsonArray3 = asJsonObject3.getAsJsonArray(str);
                                this.muteUrls = new String[asJsonArray3.size()];
                                for (int i7 = 0; i7 < asJsonArray3.size(); i7++) {
                                    if (asJsonArray3.get(i7) == null || "null".equalsIgnoreCase(asJsonArray3.get(i7).toString())) {
                                        this.muteUrls[i7] = "";
                                    } else {
                                        this.muteUrls[i7] = asJsonArray3.get(i7).getAsString();
                                    }
                                }
                            } else {
                                this.muteUrls = new String[0];
                            }
                            if (JsonUtil.hasNonNull(asJsonObject3, str2)) {
                                JsonArray asJsonArray4 = asJsonObject3.getAsJsonArray(str2);
                                this.unmuteUrls = new String[asJsonArray4.size()];
                                for (int i8 = 0; i8 < asJsonArray4.size(); i8++) {
                                    if (asJsonArray4.get(i8) == null || "null".equalsIgnoreCase(asJsonArray4.get(i8).toString())) {
                                        this.unmuteUrls[i8] = "";
                                    } else {
                                        this.unmuteUrls[i8] = asJsonArray4.get(i8).getAsString();
                                    }
                                }
                            } else {
                                this.unmuteUrls = new String[0];
                            }
                            if (JsonUtil.hasNonNull(asJsonObject3, str3)) {
                                JsonArray asJsonArray5 = asJsonObject3.getAsJsonArray(str3);
                                this.closeUrls = new String[asJsonArray5.size()];
                                for (int i9 = 0; i9 < asJsonArray5.size(); i9++) {
                                    if (asJsonArray5.get(i9) == null || "null".equalsIgnoreCase(asJsonArray5.get(i9).toString())) {
                                        this.closeUrls[i9] = "";
                                    } else {
                                        this.closeUrls[i9] = asJsonArray5.get(i9).getAsString();
                                    }
                                }
                            } else {
                                this.closeUrls = new String[0];
                            }
                            if (JsonUtil.hasNonNull(asJsonObject3, str4)) {
                                JsonArray asJsonArray6 = asJsonObject3.getAsJsonArray(str4);
                                this.postRollClickUrls = new String[asJsonArray6.size()];
                                for (int i10 = 0; i10 < asJsonArray6.size(); i10++) {
                                    if (asJsonArray6.get(i10) == null || "null".equalsIgnoreCase(asJsonArray6.get(i10).toString())) {
                                        this.postRollClickUrls[i10] = "";
                                    } else {
                                        this.postRollClickUrls[i10] = asJsonArray6.get(i10).getAsString();
                                    }
                                }
                            } else {
                                this.postRollClickUrls = new String[0];
                            }
                            if (JsonUtil.hasNonNull(asJsonObject3, str5)) {
                                JsonArray asJsonArray7 = asJsonObject3.getAsJsonArray(str5);
                                this.postRollViewUrls = new String[asJsonArray7.size()];
                                for (int i11 = 0; i11 < asJsonArray7.size(); i11++) {
                                    if (asJsonArray7.get(i11) == null || "null".equalsIgnoreCase(asJsonArray7.get(i11).toString())) {
                                        this.postRollViewUrls[i11] = "";
                                    } else {
                                        this.postRollViewUrls[i11] = asJsonArray7.get(i11).getAsString();
                                    }
                                }
                            } else {
                                this.postRollViewUrls = new String[0];
                            }
                        } else {
                            this.checkpoints = new ArrayList<>();
                            this.muteUrls = new String[0];
                            this.closeUrls = new String[0];
                            this.unmuteUrls = new String[0];
                            this.postRollViewUrls = new String[0];
                            this.postRollClickUrls = new String[0];
                            this.clickUrls = new String[0];
                            this.enableMoat = false;
                            this.moatExtraVast = "";
                        }
                        if (asJsonObject.has("delay")) {
                            this.delay = asJsonObject.get("delay").getAsInt();
                        } else {
                            this.delay = 0;
                        }
                        if (asJsonObject.has("showClose")) {
                            this.showCloseDelay = asJsonObject.get("showClose").getAsInt();
                        } else {
                            this.showCloseDelay = 0;
                        }
                        if (asJsonObject.has("showCloseIncentivized")) {
                            this.showCloseIncentivized = asJsonObject.get("showCloseIncentivized").getAsInt();
                        } else {
                            this.showCloseIncentivized = 0;
                        }
                        if (asJsonObject.has("countdown")) {
                            this.countdown = asJsonObject.get("countdown").getAsInt();
                        } else {
                            this.countdown = 0;
                        }
                        if (JsonUtil.hasNonNull(asJsonObject, "url")) {
                            this.videoUrl = asJsonObject.get("url").getAsString();
                            if (asJsonObject.has("videoWidth")) {
                                this.videoWidth = asJsonObject.get("videoWidth").getAsInt();
                                if (asJsonObject.has("videoHeight")) {
                                    this.videoHeight = asJsonObject.get("videoHeight").getAsInt();
                                    if (asJsonObject.has("md5")) {
                                        this.md5 = asJsonObject.get("md5").getAsString();
                                    } else {
                                        this.md5 = "";
                                    }
                                    if (asJsonObject.has("cta_overlay")) {
                                        JsonObject asJsonObject5 = asJsonObject.getAsJsonObject("cta_overlay");
                                        if (asJsonObject5.has("enabled")) {
                                            this.ctaOverlayEnabled = asJsonObject5.get("enabled").getAsBoolean();
                                        } else {
                                            this.ctaOverlayEnabled = false;
                                        }
                                        if (asJsonObject5.has("show_onclick")) {
                                            this.ctaShowOnClick = asJsonObject5.get("show_onclick").getAsBoolean();
                                        } else {
                                            this.ctaShowOnClick = false;
                                        }
                                        if (asJsonObject5.has("time_enabled")) {
                                            this.ctaTimeEnabled = asJsonObject5.get("time_enabled").getAsInt();
                                        } else {
                                            this.ctaTimeEnabled = -1;
                                        }
                                        if (asJsonObject5.has("click_area")) {
                                            this.ctaClickArea = asJsonObject5.get("click_area").getAsInt();
                                        } else {
                                            this.ctaClickArea = -1;
                                        }
                                        if (asJsonObject5.has("time_show")) {
                                            this.ctaTimeShow = asJsonObject5.get("time_show").getAsInt();
                                        } else {
                                            this.ctaTimeShow = -1;
                                        }
                                    } else {
                                        this.ctaOverlayEnabled = false;
                                        this.ctaClickArea = -1;
                                        this.ctaTimeEnabled = -1;
                                        this.ctaTimeShow = -1;
                                        this.ctaShowOnClick = false;
                                    }
                                    this.ctaDestinationUrl = JsonUtil.hasNonNull(asJsonObject, "callToActionDest") ? asJsonObject.get("callToActionDest").getAsString() : null;
                                    this.ctaUrl = JsonUtil.hasNonNull(asJsonObject, "callToActionUrl") ? asJsonObject.get("callToActionUrl").getAsString() : null;
                                    if (asJsonObject.has("retryCount")) {
                                        this.retryCount = asJsonObject.get("retryCount").getAsInt();
                                    } else {
                                        this.retryCount = 1;
                                    }
                                    if (asJsonObject.has("ad_token")) {
                                        this.adToken = asJsonObject.get("ad_token").getAsString();
                                        if (asJsonObject.has("video_object_id")) {
                                            this.videoIdentifier = asJsonObject.get("video_object_id").getAsString();
                                        } else {
                                            this.videoIdentifier = "";
                                        }
                                        if (asJsonObject.has("requires_sideloading")) {
                                            this.requiresNonMarketInstall = asJsonObject.get("requires_sideloading").getAsBoolean();
                                        } else {
                                            this.requiresNonMarketInstall = false;
                                        }
                                        if (asJsonObject.has("ad_market_id")) {
                                            this.adMarketId = asJsonObject.get("ad_market_id").getAsString();
                                        } else {
                                            this.adMarketId = "";
                                        }
                                        if (asJsonObject.has("bid_token")) {
                                            this.bidToken = asJsonObject.get("bid_token").getAsString();
                                        } else {
                                            this.bidToken = "";
                                        }
                                        this.adConfig = new AdConfig();
                                        return;
                                    }
                                    throw new IllegalArgumentException("AdToken missing!");
                                }
                                throw new IllegalArgumentException("Missing video height!");
                            }
                            throw new IllegalArgumentException("Missing video width!");
                        }
                        throw new IllegalArgumentException("Missing video URL!");
                    }
                    throw new IllegalArgumentException("Missing app Id, cannot process advertisement!");
                }
                throw new IllegalArgumentException("Missing campaign information, cannot process advertisement!");
            }
            throw new IllegalArgumentException("Missing identifier, cannot process advertisement!");
        }
        throw new IllegalArgumentException("Advertisement did not contain an adType!");
    }

    @AdType
    public int getAdType() {
        return this.adType;
    }

    public List<Checkpoint> getCheckpoints() {
        return this.checkpoints;
    }

    public void configure(AdConfig adConfig) {
        if (adConfig == null) {
            this.adConfig = new AdConfig();
        } else {
            this.adConfig = adConfig;
        }
    }

    public AdConfig getAdConfig() {
        return this.adConfig;
    }

    @Orientation
    public int getOrientation() {
        return this.videoWidth > this.videoHeight ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Advertisement)) {
            return false;
        }
        Advertisement advertisement = (Advertisement) obj;
        if (getId() == null || advertisement.getId() == null || !advertisement.getId().equals(getId()) || advertisement.adType != this.adType || advertisement.expireTime != this.expireTime || advertisement.delay != this.delay || advertisement.showCloseDelay != this.showCloseDelay || advertisement.showCloseIncentivized != this.showCloseIncentivized || advertisement.countdown != this.countdown || advertisement.videoWidth != this.videoWidth || advertisement.videoHeight != this.videoHeight || advertisement.ctaOverlayEnabled != this.ctaOverlayEnabled || advertisement.ctaShowOnClick != this.ctaShowOnClick || advertisement.ctaTimeEnabled != this.ctaTimeEnabled || advertisement.ctaTimeShow != this.ctaTimeShow || advertisement.ctaClickArea != this.ctaClickArea || advertisement.retryCount != this.retryCount || advertisement.enableMoat != this.enableMoat || advertisement.requiresNonMarketInstall != this.requiresNonMarketInstall || !advertisement.identifier.equals(this.identifier) || !advertisement.campaign.equals(this.campaign) || !advertisement.videoUrl.equals(this.videoUrl) || !advertisement.md5.equals(this.md5) || !advertisement.postrollBundleUrl.equals(this.postrollBundleUrl) || !advertisement.ctaDestinationUrl.equals(this.ctaDestinationUrl) || !advertisement.ctaUrl.equals(this.ctaUrl) || !advertisement.adToken.equals(this.adToken) || !advertisement.videoIdentifier.equals(this.videoIdentifier) || !advertisement.moatExtraVast.equals(this.moatExtraVast) || advertisement.state != this.state || advertisement.muteUrls.length != this.muteUrls.length) {
            return false;
        }
        int i = 0;
        while (true) {
            String[] strArr = this.muteUrls;
            if (i < strArr.length) {
                if (!advertisement.muteUrls[i].equals(strArr[i])) {
                    return false;
                }
                i++;
            } else {
                if (advertisement.unmuteUrls.length != this.unmuteUrls.length) {
                    return false;
                }
                int i2 = 0;
                while (true) {
                    String[] strArr2 = this.unmuteUrls;
                    if (i2 < strArr2.length) {
                        if (!advertisement.unmuteUrls[i2].equals(strArr2[i2])) {
                            return false;
                        }
                        i2++;
                    } else {
                        if (advertisement.closeUrls.length != this.closeUrls.length) {
                            return false;
                        }
                        int i3 = 0;
                        while (true) {
                            String[] strArr3 = this.closeUrls;
                            if (i3 < strArr3.length) {
                                if (!advertisement.closeUrls[i3].equals(strArr3[i3])) {
                                    return false;
                                }
                                i3++;
                            } else {
                                if (advertisement.postRollClickUrls.length != this.postRollClickUrls.length) {
                                    return false;
                                }
                                int i4 = 0;
                                while (true) {
                                    String[] strArr4 = this.postRollClickUrls;
                                    if (i4 < strArr4.length) {
                                        if (!advertisement.postRollClickUrls[i4].equals(strArr4[i4])) {
                                            return false;
                                        }
                                        i4++;
                                    } else {
                                        if (advertisement.postRollViewUrls.length != this.postRollViewUrls.length) {
                                            return false;
                                        }
                                        int i5 = 0;
                                        while (true) {
                                            String[] strArr5 = this.postRollViewUrls;
                                            if (i5 < strArr5.length) {
                                                if (!advertisement.postRollViewUrls[i5].equals(strArr5[i5])) {
                                                    return false;
                                                }
                                                i5++;
                                            } else {
                                                if (advertisement.checkpoints.size() != this.checkpoints.size()) {
                                                    return false;
                                                }
                                                for (int i6 = 0; i6 < this.checkpoints.size(); i6++) {
                                                    if (!advertisement.checkpoints.get(i6).equals(this.checkpoints.get(i6))) {
                                                        return false;
                                                    }
                                                }
                                                return advertisement.adMarketId.equals(this.adMarketId) && advertisement.bidToken.equals(this.bidToken);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public int hashCode() {
        return super.hashCode();
    }

    @Override // com.vungle.warren.persistence.Memorable
    public byte[] toByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.adType));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.expireTime));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.delay));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.showCloseDelay));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.showCloseIncentivized));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.countdown));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.videoWidth));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.videoHeight));
            int i = 1;
            byteArrayOutputStream.write(this.ctaOverlayEnabled ? 1 : 0);
            byteArrayOutputStream.write(this.ctaShowOnClick ? 1 : 0);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.ctaTimeEnabled));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.ctaTimeShow));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.ctaClickArea));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.retryCount));
            byteArrayOutputStream.write(this.enableMoat ? 1 : 0);
            if (!this.requiresNonMarketInstall) {
                i = 0;
            }
            byteArrayOutputStream.write(i);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.adConfig.getSettings()));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.adConfig.getFlexViewCloseTime()));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.adConfig.getOrdinal()));
            MemoryUtils.writeString(this.identifier, byteArrayOutputStream);
            MemoryUtils.writeString(this.appID, byteArrayOutputStream);
            MemoryUtils.writeString(this.campaign, byteArrayOutputStream);
            MemoryUtils.writeString(this.videoUrl, byteArrayOutputStream);
            MemoryUtils.writeString(this.md5, byteArrayOutputStream);
            MemoryUtils.writeString(this.postrollBundleUrl, byteArrayOutputStream);
            MemoryUtils.writeString(this.ctaDestinationUrl, byteArrayOutputStream);
            MemoryUtils.writeString(this.ctaUrl, byteArrayOutputStream);
            MemoryUtils.writeString(this.adToken, byteArrayOutputStream);
            MemoryUtils.writeString(this.videoIdentifier, byteArrayOutputStream);
            MemoryUtils.writeStringArray(this.muteUrls, byteArrayOutputStream);
            MemoryUtils.writeStringArray(this.unmuteUrls, byteArrayOutputStream);
            MemoryUtils.writeStringArray(this.closeUrls, byteArrayOutputStream);
            MemoryUtils.writeStringArray(this.postRollClickUrls, byteArrayOutputStream);
            MemoryUtils.writeStringArray(this.postRollViewUrls, byteArrayOutputStream);
            MemoryUtils.writeStringArray(this.clickUrls, byteArrayOutputStream);
            MemoryUtils.writeString(this.templateUrl, byteArrayOutputStream);
            MemoryUtils.writeString(this.templateId, byteArrayOutputStream);
            MemoryUtils.writeString(this.templateType, byteArrayOutputStream);
            MemoryUtils.writeString(this.moatExtraVast, byteArrayOutputStream);
            MemoryUtils.writeString(this.adMarketId, byteArrayOutputStream);
            MemoryUtils.writeString(this.bidToken, byteArrayOutputStream);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.checkpoints.size()));
            Iterator<Checkpoint> it = this.checkpoints.iterator();
            while (it.hasNext()) {
                MemoryUtils.writeMemorable(it.next(), byteArrayOutputStream);
            }
            MemoryUtils.writeStringMap(this.templateSettings, byteArrayOutputStream);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.state));
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            Log.e("Advertisement.java", "Failed to write " + this + " to a byte array");
            return new byte[0];
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public String[] getTpatUrls(@NonNull String str) {
        int i = this.adType;
        byte b = -1;
        if (i == 0) {
            switch (str.hashCode()) {
                case -1964722632:
                    if (str.equals(NendAdNativeMediaView.RESULT_CLICK_URL)) {
                        b = 5;
                    }
                    break;
                case -840405966:
                    if (str.equals(bj.gw)) {
                        b = 3;
                    }
                    break;
                case 3363353:
                    if (str.equals(bj.gv)) {
                        b = 2;
                    }
                    break;
                case 109635558:
                    if (str.equals("postroll_click")) {
                        b = 1;
                    }
                    break;
                case 1370606900:
                    if (str.equals("video_close")) {
                        b = 4;
                    }
                    break;
                case 1666667655:
                    if (str.equals("postroll_view")) {
                        b = 0;
                    }
                    break;
            }
            if (b == 0) {
                return this.postRollViewUrls;
            }
            if (b == 1) {
                return this.postRollClickUrls;
            }
            if (b == 2) {
                return this.muteUrls;
            }
            if (b == 3) {
                return this.unmuteUrls;
            }
            if (b == 4) {
                return this.closeUrls;
            }
            if (b == 5) {
                return this.clickUrls;
            }
            throw new IllegalArgumentException("Unknown TPAT Event " + str);
        }
        if (i == 1) {
            if (str.startsWith("checkpoint")) {
                String[] strArr = new String[0];
                Checkpoint checkpoint = this.checkpoints.get(Integer.parseInt(str.split("\\.")[1]) / 25);
                return checkpoint != null ? checkpoint.getUrls() : strArr;
            }
            switch (str.hashCode()) {
                case -1663300692:
                    if (str.equals("video.mute")) {
                        b = 4;
                    }
                    break;
                case -1293192841:
                    if (str.equals("postroll.click")) {
                        b = 2;
                    }
                    break;
                case -481751803:
                    if (str.equals("video.unmute")) {
                        b = 5;
                    }
                    break;
                case -32221499:
                    if (str.equals("video.close")) {
                        b = 0;
                    }
                    break;
                case 906443463:
                    if (str.equals("clickUrl")) {
                        b = 3;
                    }
                    break;
                case 1621415126:
                    if (str.equals("postroll.view")) {
                        b = 1;
                    }
                    break;
            }
            if (b == 0) {
                return this.closeUrls;
            }
            if (b == 1) {
                return this.postRollViewUrls;
            }
            if (b == 2) {
                return this.postRollClickUrls;
            }
            if (b == 3) {
                return this.clickUrls;
            }
            if (b == 4) {
                return this.muteUrls;
            }
            if (b == 5) {
                return this.unmuteUrls;
            }
            throw new IllegalArgumentException("Unknown TPAT Event " + str);
        }
        throw new IllegalStateException("Unknown Advertisement Type!");
    }

    @Override // com.vungle.warren.persistence.Memorable
    @NonNull
    public String getId() {
        String str = this.identifier;
        return str == null ? "" : str;
    }

    public String getAdToken() {
        return this.adToken;
    }

    public String getAppID() {
        return this.appID;
    }

    String getUrl() {
        return this.videoUrl;
    }

    public String getCampaign() {
        return this.campaign;
    }

    String getTemplateId() {
        return this.templateId;
    }

    public String getTemplateType() {
        return this.templateType;
    }

    public int getShowCloseDelay(boolean z) {
        int i;
        if (z) {
            i = this.showCloseIncentivized;
        } else {
            i = this.showCloseDelay;
        }
        return i * 1000;
    }

    public boolean getMoatEnabled() {
        return this.enableMoat;
    }

    public String getMoatVastExtra() {
        return this.moatExtraVast;
    }

    public long getExpireTime() {
        return this.expireTime * 1000;
    }

    public JsonObject getMRAIDArgs() {
        if (this.templateSettings == null) {
            throw new IllegalArgumentException("Advertisment does not have MRAID Arguments!");
        }
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<String, String> entry : this.templateSettings.entrySet()) {
            jsonObject.addProperty(entry.getKey(), entry.getValue());
        }
        return jsonObject;
    }

    @Nullable
    public String getCTAURL(boolean z) {
        int i = this.adType;
        if (i == 0) {
            return z ? this.ctaUrl : this.ctaDestinationUrl;
        }
        if (i == 1) {
            return this.ctaUrl;
        }
        throw new IllegalArgumentException("Unknown AdType " + this.adType);
    }

    public boolean hasPostroll() {
        return !TextUtils.isEmpty(this.postrollBundleUrl);
    }

    public Map<String, String> getDownloadableUrls() {
        HashMap map = new HashMap();
        int i = this.adType;
        if (i == 0) {
            map.put("video", this.videoUrl);
            if (!TextUtils.isEmpty(this.postrollBundleUrl)) {
                map.put("postroll", this.postrollBundleUrl);
            }
        } else if (i == 1) {
            map.put("template", this.templateUrl);
            if (this.templateSettings.containsKey("MAIN_VIDEO")) {
                map.put("video", this.templateSettings.get("MAIN_VIDEO"));
            }
            if (this.templateSettings.containsKey("APP_ICON")) {
                map.put(KEY_APP_ICON, this.templateSettings.get("APP_ICON"));
            }
            if (this.templateSettings.containsKey("POWERED_BY_VUNGLE")) {
                map.put(KEY_POWERED_BY_VUNGLE, this.templateSettings.get("POWERED_BY_VUNGLE"));
            }
        } else {
            throw new IllegalStateException("Advertisement created without adType!");
        }
        return map;
    }

    public void setMraidAssetDir(File file) {
        File file2 = new File(file, "video");
        if (file2.exists()) {
            this.templateSettings.put("MAIN_VIDEO", "file://" + file2.getPath());
        }
        File file3 = new File(file, KEY_APP_ICON);
        if (file3.exists()) {
            this.templateSettings.put("APP_ICON", "file://" + file3.getPath());
        }
        File file4 = new File(file, KEY_POWERED_BY_VUNGLE);
        if (file4.exists()) {
            this.templateSettings.put("POWERED_BY_VUNGLE", "file://" + file4.getPath());
        }
    }

    public void setState(@State int i) {
        this.state = i;
    }

    @State
    public int getState() {
        return this.state;
    }

    public String getAdMarketId() {
        return this.adMarketId;
    }

    public String getBidToken() {
        return this.bidToken;
    }

    public static class Checkpoint implements Comparable<Checkpoint>, Memorable {
        private final byte percentage;
        private final String[] urls;

        @Override // com.vungle.warren.persistence.Memorable
        @NonNull
        public String getId() {
            return "checkpoint";
        }

        public Checkpoint(byte[] bArr) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            this.percentage = byteBufferWrap.get();
            this.urls = MemoryUtils.extractStringArray(byteBufferWrap);
        }

        public Checkpoint(JsonObject jsonObject) throws IllegalArgumentException {
            if (jsonObject.has("checkpoint")) {
                this.percentage = (byte) (jsonObject.get("checkpoint").getAsFloat() * 100.0f);
                if (JsonUtil.hasNonNull(jsonObject, "urls")) {
                    JsonArray asJsonArray = jsonObject.getAsJsonArray("urls");
                    this.urls = new String[asJsonArray.size()];
                    for (int i = 0; i < asJsonArray.size(); i++) {
                        if (asJsonArray.get(i) == null || "null".equalsIgnoreCase(asJsonArray.get(i).toString())) {
                            this.urls[i] = "";
                        } else {
                            this.urls[i] = asJsonArray.get(i).getAsString();
                        }
                    }
                    return;
                }
                throw new IllegalArgumentException("Checkpoint missing reporting URL!");
            }
            throw new IllegalArgumentException("Checkpoint missing percentage!");
        }

        public Checkpoint(JsonArray jsonArray, byte b) {
            if (jsonArray.size() == 0) {
                throw new IllegalArgumentException("Empty URLS!");
            }
            this.urls = new String[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                this.urls[i] = jsonArray.get(i).getAsString();
            }
            this.percentage = b;
        }

        public String[] getUrls() {
            return this.urls;
        }

        public byte getPercentage() {
            return this.percentage;
        }

        @Override // java.lang.Comparable
        public int compareTo(@NonNull Checkpoint checkpoint) {
            return Float.compare(this.percentage, checkpoint.percentage);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Checkpoint)) {
                return false;
            }
            Checkpoint checkpoint = (Checkpoint) obj;
            if (checkpoint.percentage != this.percentage || checkpoint.urls.length != this.urls.length) {
                return false;
            }
            int i = 0;
            while (true) {
                String[] strArr = this.urls;
                if (i >= strArr.length) {
                    return true;
                }
                if (!checkpoint.urls[i].equals(strArr[i])) {
                    return false;
                }
                i++;
            }
        }

        public int hashCode() {
            return super.hashCode();
        }

        @Override // com.vungle.warren.persistence.Memorable
        public byte[] toByteArray() {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(this.percentage);
                MemoryUtils.writeStringArray(this.urls, byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            } catch (IOException unused) {
                Log.e("Advertisement.java", "Failed to write " + this + " to a byte array");
                return new byte[0];
            }
        }
    }

    public Advertisement copy() {
        try {
            return (Advertisement) clone();
        } catch (CloneNotSupportedException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return null;
        }
    }

    public String toString() {
        return "Advertisement{adType=" + this.adType + ", identifier='" + this.identifier + "', appID='" + this.appID + "', expireTime=" + this.expireTime + ", checkpoints=" + this.checkpoints + ", muteUrls=" + Arrays.toString(this.muteUrls) + ", unmuteUrls=" + Arrays.toString(this.unmuteUrls) + ", closeUrls=" + Arrays.toString(this.closeUrls) + ", postRollClickUrls=" + Arrays.toString(this.postRollClickUrls) + ", postRollViewUrls=" + Arrays.toString(this.postRollViewUrls) + ", clickUrls=" + Arrays.toString(this.clickUrls) + ", delay=" + this.delay + ", campaign='" + this.campaign + "', showCloseDelay=" + this.showCloseDelay + ", showCloseIncentivized=" + this.showCloseIncentivized + ", countdown=" + this.countdown + ", videoUrl='" + this.videoUrl + "', videoWidth=" + this.videoWidth + ", videoHeight=" + this.videoHeight + ", md5='" + this.md5 + "', postrollBundleUrl='" + this.postrollBundleUrl + "', ctaOverlayEnabled=" + this.ctaOverlayEnabled + ", ctaShowOnClick=" + this.ctaShowOnClick + ", ctaTimeEnabled=" + this.ctaTimeEnabled + ", ctaTimeShow=" + this.ctaTimeShow + ", ctaClickArea=" + this.ctaClickArea + ", ctaDestinationUrl='" + this.ctaDestinationUrl + "', ctaUrl='" + this.ctaUrl + "', adConfig=" + this.adConfig + ", retryCount=" + this.retryCount + ", adToken='" + this.adToken + "', videoIdentifier='" + this.videoIdentifier + "', templateUrl='" + this.templateUrl + "', templateSettings=" + this.templateSettings + ", templateId='" + this.templateId + "', templateType='" + this.templateType + "', enableMoat=" + this.enableMoat + ", moatExtraVast='" + this.moatExtraVast + "', requiresNonMarketInstall=" + this.requiresNonMarketInstall + ", adMarketId='" + this.adMarketId + "', bidToken='" + this.bidToken + "', state=" + this.state + '}';
    }
}
