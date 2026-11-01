package com.facebook.ads.internal.protocol;

import com.facebook.ads.AdError;
import com.google.android.gms.games.GamesStatusCodes;
import com.ironsource.mediationsdk.utils.IronSourceConstants;

/* JADX INFO: loaded from: assets/audience_network.dex */
public enum AdErrorType {
    UNKNOWN_ERROR(-1, "unknown error", false),
    NETWORK_ERROR(1000, "Network Error", true),
    NO_FILL(1001, "No Fill", true),
    LOAD_TOO_FREQUENTLY(1002, "Ad was re-loaded too frequently", true),
    DISABLED_APP(1005, "App is disabled from making ad requests", true),
    SERVER_ERROR(2000, "Server Error", true),
    INTERNAL_ERROR(2001, "Internal Error", true),
    CACHE_FAILURE_ERROR(2002, "Pre Caching failure", true),
    START_BEFORE_INIT(IronSourceConstants.IS_INSTANCE_OPENED, "initAd must be called before startAd", true),
    REMOTE_ADS_SERVICE_ERROR(AdError.REMOTE_ADS_SERVICE_ERROR, "Ads Service process error", true),
    INTERSTITIAL_AD_TIMEOUT(AdError.INTERSTITIAL_AD_TIMEOUT, "Timeout loading Interstitial Ad", true),
    BROKEN_MEDIA_ERROR(2100, "Failed to load Media for Native Ad", true),
    AD_REQUEST_FAILED(IronSourceConstants.RV_CALLBACK_AVAILABILITY_TRUE, "Facebook Ads SDK request for ads failed", false),
    AD_REQUEST_TIMEOUT(IronSourceConstants.RV_CALLBACK_AVAILABILITY_FALSE, "Facebook Ads SDK request for ads timed out", false),
    PARSER_FAILURE(IronSourceConstants.RV_INSTANCE_SHOW, "Failed to parse Facebook Ads SDK delivery response", false),
    UNKNOWN_RESPONSE(IronSourceConstants.RV_INSTANCE_SHOW_FAILED, "Unknown Facebook Ads SDK delivery response type", false),
    ERROR_MESSAGE(IronSourceConstants.RV_INSTANCE_CLOSED, "Facebook Ads SDK delivery response Error message", true),
    NO_AD_PLACEMENT(IronSourceConstants.RV_AUCTION_SUCCESS, "Facebook Ads SDK returned no ad placements", false),
    MEDIATION_ERROR(3001, "Mediation Error", true),
    BID_IMPRESSION_MISMATCH(GamesStatusCodes.STATUS_SNAPSHOT_CREATION_FAILED, "Bid payload does not match placement", true),
    BID_PAYLOAD_ERROR(GamesStatusCodes.STATUS_SNAPSHOT_CONTENTS_UNAVAILABLE, "Invalid bid payload", false),
    NO_ADAPTER_ON_LOAD(5001, "Adapter is null onLoad Ad", false),
    NO_ADAPTER_ON_START(5002, "Adapter is null onStart Ad", false),
    INTERSTITIAL_CONTROLLER_IS_NULL(5003, "Interstitial Controller is null show Ad", false),
    WEB_VIEW_FAILED_TO_LOAD(5004, "WebView failed to load", false),
    NO_MEDIAVIEW_IN_NATIVEAD(6001, "MediaView is missing in NativeAd", true),
    NO_ICONVIEW_IN_NATIVEBANNERAD(6002, "IconView is missing in NativeBannerAd", true),
    UNSUPPORTED_AD_ASSET_NATIVEAD(6003, "unsupported type of ad assets", true),
    AD_ALREADY_STARTED(7001, "Ad already started", true),
    LOAD_CALLED_WHILE_SHOWING_AD(7002, "Ad cannot be loaded while being displayed", true),
    CLEAR_TEXT_SUPPORT_NOT_ALLOWED(7003, "In order to use cache in Facebook Audience Network SDK you should whitelist 127.0.0.1 in your Network Security Configuration:\n<domain-config cleartextTrafficPermitted=\"true\">\n    <domain includeSubdomains=\"true\">127.0.0.1</domain>\n</domain-config>\nSee more: https://developers.facebook.com/docs/audience-network/android-network-security-config", true),
    INCORRECT_STATE_ERROR(7004, "You can't call %s for ad in state %s", true),
    MISSING_DEPENDENCIES_ERROR(7005, "Facebook Audience Network SDK doesn't have all required classes. Please, check LogCat output for tag %s. See more: https://developers.facebook.com/docs/audience-network/android/ ", true),
    API_NOT_SUPPORTED(AdError.API_NOT_SUPPORTED, "Facebook Audience Network SDK doesn't support Android OS versions below 4.2", true),
    NATIVE_AD_IS_NOT_LOADED(7007, "Ad is not loaded. Make sure that you call loadAd() before registerViewForInteraction()", true),
    IMAGE_CACHE_ERROR(GamesStatusCodes.STATUS_MILESTONE_CLAIM_FAILED, "Failed to cache image", false);

    private final String B;
    private final int C;
    private final boolean D;

    AdErrorType(int mErrorCode, String mDefaultErrorMessage, boolean mIsPublic) {
        this.C = mErrorCode;
        this.B = mDefaultErrorMessage;
        this.D = mIsPublic;
    }

    public static AdErrorType adErrorTypeFromCode(int mErrorCode) {
        return adErrorTypeFromCode(mErrorCode, UNKNOWN_ERROR);
    }

    public static AdErrorType adErrorTypeFromCode(int mErrorCode, AdErrorType defaultAdErrorType) {
        for (AdErrorType adErrorType : values()) {
            if (adErrorType.getErrorCode() == mErrorCode) {
                return adErrorType;
            }
        }
        return defaultAdErrorType;
    }

    public String getDefaultErrorMessage() {
        return this.B;
    }

    public int getErrorCode() {
        return this.C;
    }

    public boolean isPublicError() {
        return this.D;
    }
}
