package com.vungle.mediation;

import com.vungle.warren.Vungle;

/* JADX INFO: loaded from: classes2.dex */
public class VungleConsent {
    private static Vungle.Consent sCurrentVungleConsent = null;
    private static String sCurrentVungleConsentMessageVersion = "";

    public static void updateConsentStatus(Vungle.Consent consent, String str) {
        Vungle.Consent consent2;
        String str2;
        sCurrentVungleConsent = consent;
        sCurrentVungleConsentMessageVersion = str;
        if (!Vungle.isInitialized() || (consent2 = sCurrentVungleConsent) == null || (str2 = sCurrentVungleConsentMessageVersion) == null) {
            return;
        }
        Vungle.updateConsentStatus(consent2, str2);
    }

    public static Vungle.Consent getCurrentVungleConsent() {
        return sCurrentVungleConsent;
    }

    public static String getCurrentVungleConsentMessageVersion() {
        return sCurrentVungleConsentMessageVersion;
    }
}
