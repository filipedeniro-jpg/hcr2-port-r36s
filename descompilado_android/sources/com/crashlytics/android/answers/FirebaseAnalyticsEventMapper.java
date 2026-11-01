package com.crashlytics.android.answers;

import android.os.Bundle;
import com.applovin.sdk.AppLovinEventTypes;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseAnalyticsEventMapper {
    private static final Set<String> EVENT_NAMES = new HashSet(Arrays.asList("app_clear_data", "app_exception", "app_remove", "app_upgrade", "app_install", "app_update", "firebase_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement", "ad_exposure", "adunit_exposure", "ad_query", "ad_activeview", "ad_impression", "ad_click", "screen_view", "firebase_extra_parameter"));
    private static final String FIREBASE_LEVEL_NAME = "level_name";
    private static final String FIREBASE_METHOD = "method";
    private static final String FIREBASE_RATING = "rating";
    private static final String FIREBASE_SUCCESS = "success";

    public FirebaseAnalyticsEvent mapEvent(SessionEvent sessionEvent) {
        Bundle bundle;
        String strMapCustomEventName;
        boolean z = SessionEvent.Type.CUSTOM.equals(sessionEvent.type) && sessionEvent.customType != null;
        boolean z2 = SessionEvent.Type.PREDEFINED.equals(sessionEvent.type) && sessionEvent.predefinedType != null;
        if (!z && !z2) {
            return null;
        }
        if (z2) {
            bundle = mapPredefinedEvent(sessionEvent);
        } else {
            bundle = new Bundle();
            if (sessionEvent.customAttributes != null) {
                mapCustomEventAttributes(bundle, sessionEvent.customAttributes);
            }
        }
        if (z2) {
            String str = (String) sessionEvent.predefinedAttributes.get("success");
            strMapCustomEventName = mapPredefinedEventName(sessionEvent.predefinedType, (str == null || Boolean.parseBoolean(str)) ? false : true);
        } else {
            strMapCustomEventName = mapCustomEventName(sessionEvent.customType);
        }
        Fabric.getLogger().d(Answers.TAG, "Logging event into firebase...");
        return new FirebaseAnalyticsEvent(strMapCustomEventName, bundle);
    }

    private String mapCustomEventName(String str) {
        if (str == null || str.length() == 0) {
            return "fabric_unnamed_event";
        }
        if (EVENT_NAMES.contains(str)) {
            return "fabric_" + str;
        }
        String strReplaceAll = str.replaceAll("[^\\p{Alnum}_]+", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
        if (strReplaceAll.startsWith("ga_") || strReplaceAll.startsWith("google_") || strReplaceAll.startsWith("firebase_") || !Character.isLetter(strReplaceAll.charAt(0))) {
            strReplaceAll = "fabric_" + strReplaceAll;
        }
        return strReplaceAll.length() > 40 ? strReplaceAll.substring(0, 40) : strReplaceAll;
    }

    private String mapAttribute(String str) {
        if (str == null || str.length() == 0) {
            return "fabric_unnamed_parameter";
        }
        String strReplaceAll = str.replaceAll("[^\\p{Alnum}_]+", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
        if (strReplaceAll.startsWith("ga_") || strReplaceAll.startsWith("google_") || strReplaceAll.startsWith("firebase_") || !Character.isLetter(strReplaceAll.charAt(0))) {
            strReplaceAll = "fabric_" + strReplaceAll;
        }
        return strReplaceAll.length() > 40 ? strReplaceAll.substring(0, 40) : strReplaceAll;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:20:0x0038  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c9  */
    private String mapPredefinedEventName(String str, boolean z) {
        byte b;
        if (z) {
            int iHashCode = str.hashCode();
            if (iHashCode != -902468296) {
                if (iHashCode != 103149417) {
                    if (iHashCode == 1743324417 && str.equals("purchase")) {
                        b = 0;
                    } else {
                        b = -1;
                    }
                } else if (str.equals("login")) {
                    b = 2;
                } else {
                    b = -1;
                }
            } else if (str.equals("signUp")) {
                b = 1;
            } else {
                b = -1;
            }
            if (b == 0) {
                return "failed_ecommerce_purchase";
            }
            if (b == 1) {
                return "failed_sign_up";
            }
            if (b == 2) {
                return "failed_login";
            }
        }
        switch (str) {
            case "purchase":
                return FirebaseAnalytics.Event.ECOMMERCE_PURCHASE;
            case "addToCart":
                return FirebaseAnalytics.Event.ADD_TO_CART;
            case "startCheckout":
                return FirebaseAnalytics.Event.BEGIN_CHECKOUT;
            case "contentView":
                return FirebaseAnalytics.Event.SELECT_CONTENT;
            case "search":
                return "search";
            case "share":
                return "share";
            case "rating":
                return "rate_content";
            case "signUp":
                return FirebaseAnalytics.Event.SIGN_UP;
            case "login":
                return "login";
            case "invite":
                return AppLovinEventTypes.USER_SENT_INVITATION;
            case "levelStart":
                return FirebaseAnalytics.Event.LEVEL_START;
            case "levelEnd":
                return FirebaseAnalytics.Event.LEVEL_END;
            default:
                return mapCustomEventName(str);
        }
    }

    private Bundle mapPredefinedEvent(SessionEvent sessionEvent) {
        Bundle bundle = new Bundle();
        if ("purchase".equals(sessionEvent.predefinedType)) {
            putString(bundle, FirebaseAnalytics.Param.ITEM_ID, (String) sessionEvent.predefinedAttributes.get("itemId"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_NAME, (String) sessionEvent.predefinedAttributes.get("itemName"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_CATEGORY, (String) sessionEvent.predefinedAttributes.get("itemType"));
            putDouble(bundle, "value", mapPriceValue(sessionEvent.predefinedAttributes.get("itemPrice")));
            putString(bundle, "currency", (String) sessionEvent.predefinedAttributes.get("currency"));
        } else if ("addToCart".equals(sessionEvent.predefinedType)) {
            putString(bundle, FirebaseAnalytics.Param.ITEM_ID, (String) sessionEvent.predefinedAttributes.get("itemId"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_NAME, (String) sessionEvent.predefinedAttributes.get("itemName"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_CATEGORY, (String) sessionEvent.predefinedAttributes.get("itemType"));
            putDouble(bundle, "price", mapPriceValue(sessionEvent.predefinedAttributes.get("itemPrice")));
            putDouble(bundle, "value", mapPriceValue(sessionEvent.predefinedAttributes.get("itemPrice")));
            putString(bundle, "currency", (String) sessionEvent.predefinedAttributes.get("currency"));
            bundle.putLong(FirebaseAnalytics.Param.QUANTITY, 1L);
        } else if ("startCheckout".equals(sessionEvent.predefinedType)) {
            putLong(bundle, FirebaseAnalytics.Param.QUANTITY, Long.valueOf(((Integer) sessionEvent.predefinedAttributes.get("itemCount")).intValue()));
            putDouble(bundle, "value", mapPriceValue(sessionEvent.predefinedAttributes.get("totalPrice")));
            putString(bundle, "currency", (String) sessionEvent.predefinedAttributes.get("currency"));
        } else if ("contentView".equals(sessionEvent.predefinedType)) {
            putString(bundle, FirebaseAnalytics.Param.CONTENT_TYPE, (String) sessionEvent.predefinedAttributes.get("contentType"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_ID, (String) sessionEvent.predefinedAttributes.get("contentId"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_NAME, (String) sessionEvent.predefinedAttributes.get("contentName"));
        } else if ("search".equals(sessionEvent.predefinedType)) {
            putString(bundle, FirebaseAnalytics.Param.SEARCH_TERM, (String) sessionEvent.predefinedAttributes.get("query"));
        } else if ("share".equals(sessionEvent.predefinedType)) {
            putString(bundle, "method", (String) sessionEvent.predefinedAttributes.get("method"));
            putString(bundle, FirebaseAnalytics.Param.CONTENT_TYPE, (String) sessionEvent.predefinedAttributes.get("contentType"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_ID, (String) sessionEvent.predefinedAttributes.get("contentId"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_NAME, (String) sessionEvent.predefinedAttributes.get("contentName"));
        } else if ("rating".equals(sessionEvent.predefinedType)) {
            putString(bundle, "rating", String.valueOf(sessionEvent.predefinedAttributes.get("rating")));
            putString(bundle, FirebaseAnalytics.Param.CONTENT_TYPE, (String) sessionEvent.predefinedAttributes.get("contentType"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_ID, (String) sessionEvent.predefinedAttributes.get("contentId"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_NAME, (String) sessionEvent.predefinedAttributes.get("contentName"));
        } else if ("signUp".equals(sessionEvent.predefinedType) || "login".equals(sessionEvent.predefinedType) || AppLovinEventTypes.USER_SENT_INVITATION.equals(sessionEvent.predefinedType)) {
            putString(bundle, "method", (String) sessionEvent.predefinedAttributes.get("method"));
        } else if ("levelStart".equals(sessionEvent.predefinedType)) {
            putString(bundle, "level_name", (String) sessionEvent.predefinedAttributes.get("levelName"));
        } else if ("levelEnd".equals(sessionEvent.predefinedType)) {
            putDouble(bundle, FirebaseAnalytics.Param.SCORE, mapDouble(sessionEvent.predefinedAttributes.get(FirebaseAnalytics.Param.SCORE)));
            putString(bundle, "level_name", (String) sessionEvent.predefinedAttributes.get("levelName"));
            putInt(bundle, "success", mapBooleanValue((String) sessionEvent.predefinedAttributes.get("success")));
        }
        mapCustomEventAttributes(bundle, sessionEvent.customAttributes);
        return bundle;
    }

    private void putLong(Bundle bundle, String str, Long l) {
        if (l == null) {
            return;
        }
        bundle.putLong(str, l.longValue());
    }

    private void putInt(Bundle bundle, String str, Integer num) {
        if (num == null) {
            return;
        }
        bundle.putInt(str, num.intValue());
    }

    private void putString(Bundle bundle, String str, String str2) {
        if (str2 == null) {
            return;
        }
        bundle.putString(str, str2);
    }

    private void putDouble(Bundle bundle, String str, Double d) {
        Double dMapDouble = mapDouble(d);
        if (dMapDouble == null) {
            return;
        }
        bundle.putDouble(str, dMapDouble.doubleValue());
    }

    private Double mapDouble(Object obj) {
        String strValueOf = String.valueOf(obj);
        if (strValueOf == null) {
            return null;
        }
        return Double.valueOf(strValueOf);
    }

    private Integer mapBooleanValue(String str) {
        if (str == null) {
            return null;
        }
        return Integer.valueOf(str.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE) ? 1 : 0);
    }

    private Double mapPriceValue(Object obj) {
        Long l = (Long) obj;
        if (l == null) {
            return null;
        }
        return Double.valueOf(new BigDecimal(l.longValue()).divide(AddToCartEvent.MICRO_CONSTANT).doubleValue());
    }

    private void mapCustomEventAttributes(Bundle bundle, Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            String strMapAttribute = mapAttribute(entry.getKey());
            if (value instanceof String) {
                bundle.putString(strMapAttribute, entry.getValue().toString());
            } else if (value instanceof Double) {
                bundle.putDouble(strMapAttribute, ((Double) entry.getValue()).doubleValue());
            } else if (value instanceof Long) {
                bundle.putLong(strMapAttribute, ((Long) entry.getValue()).longValue());
            } else if (value instanceof Integer) {
                bundle.putInt(strMapAttribute, ((Integer) entry.getValue()).intValue());
            }
        }
    }
}
