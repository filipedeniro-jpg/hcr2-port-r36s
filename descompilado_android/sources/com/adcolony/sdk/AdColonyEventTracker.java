package com.adcolony.sdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import bolts.MeasurementEvent;
import com.applovin.sdk.AppLovinEventParameters;
import com.applovin.sdk.AppLovinEventTypes;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.unity3d.services.purchasing.core.TransactionDetailsUtilities;
import io.fabric.sdk.android.services.settings.AppSettingsData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AdColonyEventTracker {
    public static final String CUSTOM_EVENT_1 = "ADCT_CUSTOM_EVENT_1";
    public static final String CUSTOM_EVENT_2 = "ADCT_CUSTOM_EVENT_2";
    public static final String CUSTOM_EVENT_3 = "ADCT_CUSTOM_EVENT_3";
    public static final String CUSTOM_EVENT_4 = "ADCT_CUSTOM_EVENT_4";
    public static final String CUSTOM_EVENT_5 = "ADCT_CUSTOM_EVENT_5";
    public static final String LOGIN_DEFAULT = "ADCT_DEFAULT_LOGIN";
    public static final String LOGIN_FACEBOOK = "ADCT_FACEBOOK_LOGIN";
    public static final String LOGIN_GOOGLE = "ADCT_GOOGLE_LOGIN";
    public static final String LOGIN_LINKEDIN = "ADCT_LINKEDIN_LOGIN";
    public static final String LOGIN_OPENID = "ADCT_OPENID_LOGIN";
    public static final String LOGIN_TWITTER = "ADCT_TWITTER_LOGIN";
    public static final String REGISTRATION_CUSTOM = "ADCT_CUSTOM_REGISTRATION";
    public static final String REGISTRATION_DEFAULT = "ADCT_DEFAULT_REGISTRATION";
    public static final String REGISTRATION_FACEBOOK = "ADCT_FACEBOOK_REGISTRATION";
    public static final String REGISTRATION_GOOGLE = "ADCT_GOOGLE_REGISTRATION";
    public static final String REGISTRATION_LINKEDIN = "ADCT_LINKEDIN_REGISTRATION";
    public static final String REGISTRATION_OPENID = "ADCT_OPENID_REGISTRATION";
    public static final String REGISTRATION_TWITTER = "ADCT_TWITTER_REGISTRATION";
    public static final String SOCIAL_SHARING_CUSTOM = "ADCT_CUSTOM_SHARING";
    public static final String SOCIAL_SHARING_FACEBOOK = "ADCT_FACEBOOK_SHARING";
    public static final String SOCIAL_SHARING_FLICKR = "ADCT_FLICKR_SHARING";
    public static final String SOCIAL_SHARING_FOURSQUARE = "ADCT_FOURSQUARE_SHARING";
    public static final String SOCIAL_SHARING_GOOGLE = "ADCT_GOOGLE_SHARING";
    public static final String SOCIAL_SHARING_INSTAGRAM = "ADCT_INSTAGRAM_SHARING";
    public static final String SOCIAL_SHARING_LINKEDIN = "ADCT_LINKEDIN_SHARING";
    public static final String SOCIAL_SHARING_PINTEREST = "ADCT_PINTEREST_SHARING";
    public static final String SOCIAL_SHARING_SNAPCHAT = "ADCT_SNAPCHAT_SHARING";
    public static final String SOCIAL_SHARING_TUMBLR = "ADCT_TUMBLR_SHARING";
    public static final String SOCIAL_SHARING_TWITTER = "ADCT_TWITTER_SHARING";
    public static final String SOCIAL_SHARING_VIMEO = "ADCT_VIMEO_SHARING";
    public static final String SOCIAL_SHARING_VINE = "ADCT_VINE_SHARING";
    public static final String SOCIAL_SHARING_YOUTUBE = "ADCT_YOUTUBE_SHARING";
    private static final List<JSONObject> a = Collections.synchronizedList(new ArrayList());
    private static final int b = 200;

    static void a(JSONObject jSONObject) {
        synchronized (a) {
            if (200 > a.size()) {
                a.add(jSONObject);
            }
        }
    }

    static void a() {
        if (l.C().equals("")) {
            return;
        }
        synchronized (a) {
            Iterator<JSONObject> it = a.iterator();
            while (it.hasNext()) {
                ADCNative.EventTracker__logEvent(it.next());
            }
            a.clear();
        }
    }

    static boolean b() {
        boolean z;
        synchronized (a) {
            z = a.size() != 0;
        }
        return z;
    }

    public static void logTransaction(@Nullable String str, @Nullable Integer num, @Nullable Double d, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        if (a(str5, "logTransaction")) {
            return;
        }
        if (str2 != null && str2.length() != 3) {
            new aa.a().a("Event logCreditsSpentWithName currency code is specified, but a three-letter ISO 4217 code, (e.g.: 'USD'). Event will not be sent.").a(aa.g);
            return;
        }
        HashMap map = new HashMap();
        map.put(FirebaseAnalytics.Param.ITEM_ID, str);
        map.put(FirebaseAnalytics.Param.QUANTITY, String.valueOf(num));
        map.put("price", String.valueOf(d));
        map.put("currency_code", str2);
        map.put(TransactionDetailsUtilities.RECEIPT, str3);
        map.put("store", str4);
        map.put("description", str5);
        logEvent("transaction", map);
    }

    public static void logCreditsSpent(@Nullable String str, @Nullable Integer num, @Nullable Double d, @Nullable String str2) {
        if (str2 != null && str2.length() != 3) {
            new aa.a().a("Event logCreditsSpentWithName currency code is specified, but a three-letter ISO 4217 code, (e.g.: 'USD'). Event will not be sent.").a(aa.g);
            return;
        }
        HashMap map = new HashMap();
        map.put("name", str);
        map.put(FirebaseAnalytics.Param.QUANTITY, String.valueOf(num));
        map.put("value", String.valueOf(d));
        map.put("currency_code", str2);
        logEvent("credits_spent", map);
    }

    public static void logPaymentInfoAdded() {
        logEvent("payment_info_added");
    }

    public static void logAchievementUnlocked(@Nullable String str) {
        if (a(str, "logAchievementUnlocked")) {
            return;
        }
        HashMap map = new HashMap();
        map.put("description", str);
        logEvent("achievement_unlocked", map);
    }

    public static void logLevelAchieved(@Nullable Integer num) {
        HashMap map = new HashMap();
        map.put("level_achieved", String.valueOf(num));
        logEvent("level_achieved", map);
    }

    public static void logAppRated() {
        logEvent("app_rated");
    }

    public static void logActivated() {
        logEvent(AppSettingsData.STATUS_ACTIVATED);
    }

    public static void logTutorialCompleted() {
        logEvent("tutorial_completed");
    }

    public static void logSocialSharingEvent(@Nullable String str, @Nullable String str2) {
        if (a(str2, "logSocialSharingEvent")) {
            return;
        }
        HashMap map = new HashMap();
        map.put("network", str);
        map.put("description", str2);
        logEvent("social_sharing_event", map);
    }

    public static void logRegistrationCompleted(@Nullable String str, @Nullable String str2) {
        if (a(str2, "logRegistrationCompleted")) {
            return;
        }
        HashMap map = new HashMap();
        map.put("method", str);
        map.put("description", str2);
        logEvent("registration_completed", map);
    }

    public static void logCustomEvent(@Nullable String str, @Nullable String str2) {
        if (a(str2, "logCustomEvent")) {
            return;
        }
        HashMap map = new HashMap();
        map.put("event", str);
        map.put("description", str2);
        logEvent("custom_event", map);
    }

    public static void logAddToCart(@Nullable String str) {
        HashMap map = new HashMap();
        map.put(FirebaseAnalytics.Param.ITEM_ID, str);
        logEvent(FirebaseAnalytics.Event.ADD_TO_CART, map);
    }

    public static void logAddToWishlist(@Nullable String str) {
        HashMap map = new HashMap();
        map.put(FirebaseAnalytics.Param.ITEM_ID, str);
        logEvent(FirebaseAnalytics.Event.ADD_TO_WISHLIST, map);
    }

    public static void logCheckoutInitiated() {
        logEvent("checkout_initiated");
    }

    public static void logContentView(@Nullable String str, @Nullable String str2) {
        HashMap map = new HashMap();
        map.put(AppLovinEventParameters.CONTENT_IDENTIFIER, str);
        map.put(FirebaseAnalytics.Param.CONTENT_TYPE, str2);
        logEvent("content_view", map);
    }

    public static void logInvite() {
        logEvent(AppLovinEventTypes.USER_SENT_INVITATION);
    }

    public static void logLogin(@Nullable String str) {
        HashMap map = new HashMap();
        map.put("method", str);
        logEvent("login", map);
    }

    public static void logReservation() {
        logEvent(AppLovinEventTypes.USER_CREATED_RESERVATION);
    }

    public static void logSearch(@Nullable String str) {
        if (str != null && str.length() > 512) {
            new aa.a().a("logSearch searchString cannot exceed 512 characters. Event will ").a("not be sent.").a(aa.g);
            return;
        }
        HashMap map = new HashMap();
        map.put("search_string", str);
        logEvent("search", map);
    }

    public static void logEvent(@Nullable String str) {
        logEvent(str, null);
    }

    public static void logEvent(@NonNull String str, @Nullable HashMap<String, String> map) {
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY, str);
        JSONObject jSONObjectA2 = y.a();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue() != null && !entry.getValue().equals("null")) {
                    y.a(jSONObjectA2, entry.getKey(), entry.getValue());
                }
            }
        }
        b(jSONObjectA2);
        y.a(jSONObjectA, MessengerShareContentUtility.ATTACHMENT_PAYLOAD, jSONObjectA2);
        ADCNative.EventTracker__logEvent(jSONObjectA);
    }

    private static void b(JSONObject jSONObject) {
        y.a(jSONObject, com.my.target.i.K, TimeZone.getDefault().getID());
        y.a(jSONObject, "action_time", String.valueOf(Math.round(System.currentTimeMillis() / 1000)));
    }

    private static boolean a(String str, String str2) {
        if (str == null || str.length() <= 512) {
            return false;
        }
        new aa.a().a("Description of event ").a(str2).a(" must be less").a(" than 512 characters").a(aa.g);
        return true;
    }
}
