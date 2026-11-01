package com.fingersoft.liveops_sdk.utils;

import com.vungle.warren.DirectDownloadAdapter;

/* JADX INFO: loaded from: classes.dex */
public class ApiHelper {
    private static String adIncrementUrl = "api/increment_ad_requests?guid=GUID&locale=LOCALE&platform=PLATFORM&ADDITIONS";
    private static String advertImageUrl = "api/get_advert_image?guid=GUID&advert_image=ADVERT_IMAGE&locale=LOCALE&platform=PLATFORM&ADDITIONS";
    private static String getCampaignUrl = "api/campaigns?package_name=PACKAGE_NAME&locale=LOCALE&request=REQUEST&platform=PLATFORM&version=VERSION&startCount=START_COUNT&currentversionstartcount=CVSC&DEV_CAPABILITIES&ADDITIONS";
    private static String promoIncrementUrl = "api/increment_promotion_clicks?guid=GUID&locale=LOCALE&platform=PLATFORM&ADDITIONS";
    private static String promotionImageUrl = "api/get_promotion_image?guid=GUID&promotion_image=PROMOTION_IMAGE&locale=LOCALE&platform=PLATFORM&ADDITIONS";
    private static String promotionImageUrlBase64 = "api/get_promotion_image?guid=GUID&promotion_image=PROMOTION_IMAGE&has_image=1";
    private static String startupRequestUrl = "api/startup_request?package_name=PACKAGE_NAME&locale=LOCALE&version=VERSION&platform=PLATFORM&startCount=START_COUNT&currentversionstartcount=CVSC&DEV_CAPABILITIES&ADDITIONS";

    public static String getAdvertImageUrl(String str, String str2, String str3, String str4, String str5, String str6) {
        return str + advertImageUrl.replace("GUID", str2).replace("ADVERT_IMAGE", str3).replace("LOCALE", str4).replace("PLATFORM", str5).replace("ADDITIONS", str6);
    }

    public static String getBase64PromotionImageUrl(String str, String str2, String str3) {
        return str + promotionImageUrlBase64.replace("GUID", str2).replace("PROMOTION_IMAGE", str3);
    }

    public static String getPromotionsImageUrl(String str, String str2, String str3, String str4, String str5, String str6) {
        return str + promotionImageUrl.replace("LOCALE", str4).replace("PLATFORM", str5).replace("ADDITIONS", str6).replace("GUID", str2).replace("PROMOTION_IMAGE", str3);
    }

    public static String getAdIncrementUrl(String str, String str2, String str3, String str4, String str5) {
        return str + adIncrementUrl.replace("LOCALE", str3).replace("PLATFORM", str4).replace("ADDITIONS", str5).replace("GUID", str2);
    }

    public static String getPromoIncrementUrl(String str, String str2, String str3, String str4, String str5) {
        return str + promoIncrementUrl.replace("LOCALE", str3).replace("PLATFORM", str4).replace("ADDITIONS", str5).replace("GUID", str2);
    }

    public static String getCampaignUrl(String str, String str2, String str3, String str4, int i, String str5, int i2, int i3, String str6) {
        return str + getCampaignUrl.replace(DirectDownloadAdapter.PACKAGE_NAME, str2).replace("LOCALE", str3).replace("PLATFORM", str4).replace("REQUEST", Integer.toString(i)).replace("VERSION", str5).replace("ADDITIONS", str6).replace("START_COUNT", Integer.toString(i2)).replace("CVSC", Integer.toString(i3)).replace("DEV_CAPABILITIES", DeviceCapabilities.getDeviceCapabilitiesAsRequestParams());
    }

    public static String getStartUpRequestUrl(String str, String str2, String str3, String str4, String str5, int i, int i2, String str6) {
        return str + startupRequestUrl.replace(DirectDownloadAdapter.PACKAGE_NAME, str2).replace("LOCALE", str3).replace("VERSION", str4).replace("PLATFORM", str5).replace("START_COUNT", Integer.toString(i)).replace("CVSC", Integer.toString(i2)).replace("ADDITIONS", str6).replace("DEV_CAPABILITIES", DeviceCapabilities.getDeviceCapabilitiesAsRequestParams());
    }
}
