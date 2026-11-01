package com.chartboost.sdk.Tracking;

import android.text.TextUtils;
import android.util.Base64;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.h;
import com.chartboost.sdk.i;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.aj;
import com.chartboost.sdk.impl.ap;
import com.unity3d.services.purchasing.core.TransactionDetailsUtilities;
import java.util.EnumMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CBAnalytics {

    public enum CBIAPPurchaseInfo {
        PRODUCT_ID,
        PRODUCT_TITLE,
        PRODUCT_DESCRIPTION,
        PRODUCT_PRICE,
        PRODUCT_CURRENCY_CODE,
        GOOGLE_PURCHASE_DATA,
        GOOGLE_PURCHASE_SIGNATURE,
        AMAZON_PURCHASE_TOKEN,
        AMAZON_USER_ID
    }

    public enum CBIAPType {
        GOOGLE_PLAY,
        AMAZON
    }

    private CBAnalytics() {
    }

    public enum CBLevelType {
        HIGHEST_LEVEL_REACHED(1),
        CURRENT_AREA(2),
        CHARACTER_LEVEL(3),
        OTHER_SEQUENTIAL(4),
        OTHER_NONSEQUENTIAL(5);

        private final int a;

        CBLevelType(int i) {
            this.a = i;
        }

        public int getLevelType() {
            return this.a;
        }
    }

    public synchronized void trackInAppPurchaseEvent(EnumMap<CBIAPPurchaseInfo, String> enumMap, CBIAPType cBIAPType) {
        if (enumMap != null && cBIAPType != null) {
            if (!TextUtils.isEmpty(enumMap.get(CBIAPPurchaseInfo.PRODUCT_ID)) && !TextUtils.isEmpty(enumMap.get(CBIAPPurchaseInfo.PRODUCT_TITLE)) && !TextUtils.isEmpty(enumMap.get(CBIAPPurchaseInfo.PRODUCT_DESCRIPTION)) && !TextUtils.isEmpty(enumMap.get(CBIAPPurchaseInfo.PRODUCT_PRICE)) && !TextUtils.isEmpty(enumMap.get(CBIAPPurchaseInfo.PRODUCT_CURRENCY_CODE))) {
                a(enumMap.get(CBIAPPurchaseInfo.PRODUCT_ID), enumMap.get(CBIAPPurchaseInfo.PRODUCT_TITLE), enumMap.get(CBIAPPurchaseInfo.PRODUCT_DESCRIPTION), enumMap.get(CBIAPPurchaseInfo.PRODUCT_PRICE), enumMap.get(CBIAPPurchaseInfo.PRODUCT_CURRENCY_CODE), enumMap.get(CBIAPPurchaseInfo.GOOGLE_PURCHASE_DATA), enumMap.get(CBIAPPurchaseInfo.GOOGLE_PURCHASE_SIGNATURE), enumMap.get(CBIAPPurchaseInfo.AMAZON_USER_ID), enumMap.get(CBIAPPurchaseInfo.AMAZON_PURCHASE_TOKEN), cBIAPType);
                return;
            }
        }
        CBLogging.b("CBPostInstallTracker", "Null object is passed. Please pass a valid value object");
    }

    private static synchronized void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, CBIAPType cBIAPType) {
        if (i.m == null) {
            CBLogging.b("CBPostInstallTracker", "You need call Chartboost.init() before calling any public API's");
            return;
        }
        h hVarA = h.a();
        if (hVarA == null) {
            CBLogging.b("CBPostInstallTracker", "You need call Chartboost.startWithAppId() before tracking in-app purchases");
            return;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4) || TextUtils.isEmpty(str5)) {
            CBLogging.b("CBPostInstallTracker", "Null object is passed. Please pass a valid value object");
            return;
        }
        try {
            Matcher matcher = Pattern.compile("(\\d+\\.\\d+)|(\\d+)").matcher(str4);
            matcher.find();
            String strGroup = matcher.group();
            if (TextUtils.isEmpty(strGroup)) {
                CBLogging.b("CBPostInstallTracker", "Invalid price object");
                return;
            }
            float f = Float.parseFloat(strGroup);
            JSONObject jSONObjectA = null;
            if (cBIAPType == CBIAPType.GOOGLE_PLAY) {
                if (!TextUtils.isEmpty(str6) && !TextUtils.isEmpty(str7)) {
                    jSONObjectA = e.a(e.a("purchaseData", str6), e.a("purchaseSignature", str7), e.a("type", Integer.valueOf(CBIAPType.GOOGLE_PLAY.ordinal())));
                }
                CBLogging.b("CBPostInstallTracker", "Null object is passed for for purchase data or purchase signature");
                return;
            }
            if (cBIAPType == CBIAPType.AMAZON) {
                if (!TextUtils.isEmpty(str8) && !TextUtils.isEmpty(str9)) {
                    jSONObjectA = e.a(e.a("userID", str8), e.a("purchaseToken", str9), e.a("type", Integer.valueOf(CBIAPType.AMAZON.ordinal())));
                }
                CBLogging.b("CBPostInstallTracker", "Null object is passed for for amazon user id or amazon purchase token");
                return;
            }
            if (jSONObjectA == null) {
                CBLogging.b("CBPostInstallTracker", "Error while parsing the receipt to a JSON Object, ");
            } else {
                a(hVarA.h, hVarA.j, hVarA.o, e.a(e.a("localized-title", str2), e.a("localized-description", str3), e.a("price", Float.valueOf(f)), e.a("currency", str5), e.a("productID", str), e.a(TransactionDetailsUtilities.RECEIPT, Base64.encodeToString(jSONObjectA.toString().getBytes(), 2))), "iap", cBIAPType);
            }
        } catch (IllegalStateException unused) {
            CBLogging.b("CBPostInstallTracker", "Invalid price object");
        }
    }

    public static synchronized void trackInAppGooglePlayPurchaseEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        a(str5, str, str2, str3, str4, str6, str7, null, null, CBIAPType.GOOGLE_PLAY);
    }

    public static synchronized void trackInAppAmazonStorePurchaseEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        a(str5, str, str2, str3, str4, null, null, str6, str7, CBIAPType.AMAZON);
    }

    public static synchronized void trackLevelInfo(String str, CBLevelType cBLevelType, int i, String str2) {
        trackLevelInfo(str, cBLevelType, i, 0, str2);
    }

    public static synchronized void trackLevelInfo(String str, CBLevelType cBLevelType, int i, int i2, String str2) {
        h hVarA = h.a();
        if (hVarA == null) {
            CBLogging.b("CBPostInstallTracker", "trackLevelInfo: SDK is not initialized");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            CBLogging.b("CBPostInstallTracker", "Invalid value: event label cannot be empty or null");
            return;
        }
        if (cBLevelType == null) {
            CBLogging.b("CBPostInstallTracker", "Invalid value: level type cannot be empty or null, please choose from one of the CBLevelType enum values");
            return;
        }
        if (i < 0 || i2 < 0) {
            CBLogging.b("CBPostInstallTracker", "Invalid value: Level number should be > 0");
            return;
        }
        if (str2.isEmpty()) {
            CBLogging.b("CBPostInstallTracker", "Invalid value: description cannot be empty or null");
            return;
        }
        JSONObject jSONObjectA = e.a(e.a("event_label", str), e.a("event_field", Integer.valueOf(cBLevelType.getLevelType())), e.a("main_level", Integer.valueOf(i)), e.a("sub_level", Integer.valueOf(i2)), e.a("description", str2), e.a("timestamp", Long.valueOf(System.currentTimeMillis())), e.a("data_type", "level_info"));
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObjectA);
        a(hVarA.h, hVarA.j, hVarA.o, jSONArray, "tracking");
    }

    private static synchronized void a(ah ahVar, ap apVar, a aVar, JSONObject jSONObject, String str, CBIAPType cBIAPType) {
        aj ajVar = new aj(String.format(Locale.US, "%s%s", "/post-install-event/", str), apVar, aVar, 2, null);
        ajVar.a(str, jSONObject);
        ajVar.l = true;
        ajVar.b(str);
        ahVar.a(ajVar);
    }

    private static synchronized void a(ah ahVar, ap apVar, a aVar, JSONArray jSONArray, String str) {
        aj ajVar = new aj("/post-install-event/".concat("tracking"), apVar, aVar, 2, null);
        ajVar.a("track_info", jSONArray);
        ajVar.l = true;
        ajVar.b(str);
        ahVar.a(ajVar);
    }
}
