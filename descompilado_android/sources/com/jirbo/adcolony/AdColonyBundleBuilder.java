package com.jirbo.adcolony;

import android.os.Bundle;
import com.facebook.AccessToken;

/* JADX INFO: loaded from: classes2.dex */
public class AdColonyBundleBuilder {
    private static String _gdprConsentString;
    private static boolean _gdprRequired;
    private static boolean _showPostAdPopup;
    private static boolean _showPreAdPopup;
    private static String _userId;
    private static String _zoneId;

    public static void setZoneId(String str) {
        _zoneId = str;
    }

    public static void setUserId(String str) {
        _userId = str;
    }

    public static void setShowPrePopup(boolean z) {
        _showPreAdPopup = z;
    }

    public static void setShowPostPopup(boolean z) {
        _showPostAdPopup = z;
    }

    public static void setGdprRequired(boolean z) {
        _gdprRequired = z;
    }

    public static void setGdprConsentString(String str) {
        _gdprConsentString = str;
    }

    public static Bundle build() {
        Bundle bundle = new Bundle();
        bundle.putString("zone_id", _zoneId);
        bundle.putString(AccessToken.USER_ID_KEY, _userId);
        bundle.putBoolean("show_pre_popup", _showPreAdPopup);
        bundle.putBoolean("show_post_popup", _showPostAdPopup);
        bundle.putBoolean("gdpr_required", _gdprRequired);
        bundle.putString("gdpr_consent_string", _gdprConsentString);
        return bundle;
    }
}
