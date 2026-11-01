package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAppOptions;
import com.adcolony.sdk.AdColonyUserMetadata;
import com.facebook.AccessToken;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
class AdColonyManager {
    private static AdColonyManager _instance;
    private boolean _isConfigured = false;
    boolean rewardedAdsConfigured = false;
    private Context _context = null;
    private ArrayList<String> _configuredListOfZones = new ArrayList<>();

    private AdColonyManager() {
    }

    static AdColonyManager getInstance() {
        if (_instance == null) {
            _instance = new AdColonyManager();
        }
        return _instance;
    }

    void onDestroy() {
        this._context = null;
    }

    boolean configureAdColony(Context context, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        String string = bundle.getString("app_id");
        ArrayList<String> zoneList = parseZoneList(bundle);
        if (context != null) {
            this._context = context;
        }
        Context context2 = this._context;
        boolean z = false;
        if (context2 != null && !(context2 instanceof Activity)) {
            Log.w("AdColonyAdapter", "Context must be of type Activity.");
            return false;
        }
        if (string == null) {
            Log.w("AdColonyAdapter", "A valid appId wasn't provided.");
            return false;
        }
        if (zoneList == null || zoneList.isEmpty()) {
            Log.w("AdColonyAdapter", "No zones provided to request ad.");
            return false;
        }
        for (String str : zoneList) {
            if (!this._configuredListOfZones.contains(str)) {
                this._configuredListOfZones.add(str);
                z = true;
            }
        }
        AdColonyAppOptions adColonyAppOptionsBuildAppOptions = buildAppOptions(mediationAdRequest, bundle2);
        if (!this._isConfigured || z) {
            ArrayList<String> arrayList = this._configuredListOfZones;
            String[] strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            if (adColonyAppOptionsBuildAppOptions == null) {
                adColonyAppOptionsBuildAppOptions = new AdColonyAppOptions();
            }
            adColonyAppOptionsBuildAppOptions.setMediationNetwork(AdColonyAppOptions.ADMOB, BuildConfig.VERSION_NAME);
            this._isConfigured = AdColony.configure((Activity) this._context, adColonyAppOptionsBuildAppOptions, string, strArr);
        } else if (adColonyAppOptionsBuildAppOptions != null) {
            AdColony.setAppOptions(adColonyAppOptionsBuildAppOptions);
        }
        return this._isConfigured;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x005e  */
    /* JADX WARN: Code duplicated, block: B:27:0x0068  */
    /* JADX WARN: Code duplicated, block: B:29:0x0077  */
    private AdColonyAppOptions buildAppOptions(MediationAdRequest mediationAdRequest, Bundle bundle) {
        Location location;
        Date birthday;
        long jCurrentTimeMillis;
        AdColonyAppOptions adColonyAppOptions = new AdColonyAppOptions();
        boolean z = false;
        if (bundle != null) {
            String string = bundle.getString(AccessToken.USER_ID_KEY);
            String string2 = bundle.getString("gdpr_consent_string");
            if (string != null) {
                adColonyAppOptions.setUserID(string);
                z = true;
            }
            if (string2 != null) {
                adColonyAppOptions.setGDPRConsentString(string2);
                z = true;
            }
            if (bundle.containsKey("gdpr_required")) {
                adColonyAppOptions.setGDPRRequired(bundle.getBoolean("gdpr_required"));
                z = true;
            }
        }
        if (mediationAdRequest != null) {
            if (mediationAdRequest.isTesting()) {
                adColonyAppOptions.setTestModeEnabled(true);
                z = true;
            }
            AdColonyUserMetadata adColonyUserMetadata = new AdColonyUserMetadata();
            int gender = mediationAdRequest.getGender();
            if (gender == 2) {
                adColonyUserMetadata.setUserGender("female");
            } else {
                if (gender == 1) {
                    adColonyUserMetadata.setUserGender("male");
                }
                location = mediationAdRequest.getLocation();
                if (location != null) {
                    adColonyUserMetadata.setUserLocation(location);
                    z = true;
                }
                birthday = mediationAdRequest.getBirthday();
                if (birthday != null) {
                    jCurrentTimeMillis = System.currentTimeMillis() - birthday.getTime();
                    if (jCurrentTimeMillis > 0) {
                        adColonyUserMetadata.setUserAge((int) ((jCurrentTimeMillis / 86400000) / 365));
                        z = true;
                    }
                }
                adColonyAppOptions.setUserMetadata(adColonyUserMetadata);
            }
            z = true;
            location = mediationAdRequest.getLocation();
            if (location != null) {
                adColonyUserMetadata.setUserLocation(location);
                z = true;
            }
            birthday = mediationAdRequest.getBirthday();
            if (birthday != null) {
                jCurrentTimeMillis = System.currentTimeMillis() - birthday.getTime();
                if (jCurrentTimeMillis > 0) {
                    adColonyUserMetadata.setUserAge((int) ((jCurrentTimeMillis / 86400000) / 365));
                    z = true;
                }
            }
            adColonyAppOptions.setUserMetadata(adColonyUserMetadata);
        }
        if (z) {
            return adColonyAppOptions;
        }
        return null;
    }

    ArrayList<String> parseZoneList(Bundle bundle) {
        String string;
        if (bundle == null || (string = bundle.getString("zone_ids")) == null) {
            return null;
        }
        return new ArrayList<>(Arrays.asList(string.split(";")));
    }

    String getZoneFromRequest(ArrayList<String> arrayList, Bundle bundle) {
        String str = (arrayList == null || arrayList.isEmpty()) ? null : arrayList.get(0);
        return (bundle == null || bundle.getString("zone_id") == null) ? str : bundle.getString("zone_id");
    }
}
