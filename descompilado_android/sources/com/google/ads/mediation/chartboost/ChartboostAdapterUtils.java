package com.google.ads.mediation.chartboost;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Model.CBError;

/* JADX INFO: loaded from: classes.dex */
class ChartboostAdapterUtils {
    static final String KEY_AD_LOCATION = "adLocation";
    static final String KEY_APP_ID = "appId";
    static final String KEY_APP_SIGNATURE = "appSignature";

    ChartboostAdapterUtils() {
    }

    static ChartboostParams createChartboostParams(Bundle bundle, Bundle bundle2) {
        ChartboostParams chartboostParams = new ChartboostParams();
        String string = bundle.getString("appId");
        String string2 = bundle.getString(KEY_APP_SIGNATURE);
        if (string != null && string2 != null) {
            chartboostParams.setAppId(string.trim());
            chartboostParams.setAppSignature(string2.trim());
        }
        String string3 = bundle.getString(KEY_AD_LOCATION);
        if (!isValidParam(string3)) {
            Log.w(ChartboostMediationAdapter.TAG, String.format("Chartboost ad location is empty, defaulting to %s. Please set the Ad Location parameter in the AdMob UI.", CBLocation.LOCATION_DEFAULT));
            string3 = CBLocation.LOCATION_DEFAULT;
        }
        chartboostParams.setLocation(string3.trim());
        if (bundle2 != null && bundle2.containsKey("framework") && bundle2.containsKey("framework_version")) {
            chartboostParams.setFramework((Chartboost.CBFramework) bundle2.getSerializable("framework"));
            chartboostParams.setFrameworkVersion(bundle2.getString("framework_version"));
        }
        return chartboostParams;
    }

    static boolean isValidChartboostParams(ChartboostParams chartboostParams) {
        String str;
        String appId = chartboostParams.getAppId();
        String appSignature = chartboostParams.getAppSignature();
        if (isValidParam(appId) && isValidParam(appSignature)) {
            return true;
        }
        if (isValidParam(appId)) {
            str = "App Signature";
        } else {
            str = !isValidParam(appSignature) ? "App ID and App Signature" : "App ID";
        }
        Log.w(ChartboostMediationAdapter.TAG, str + " cannot be empty.");
        return false;
    }

    static boolean isValidParam(String str) {
        return (str == null || str.trim().length() == 0) ? false : true;
    }

    static boolean isValidContext(Context context) {
        if (context == null) {
            Log.w(ChartboostAdapter.TAG, "Context cannot be null");
            return false;
        }
        if (context instanceof Activity) {
            return true;
        }
        Log.w(ChartboostAdapter.TAG, "Context is not an Activity. Chartboost requires an Activity context to load ads.");
        return false;
    }

    static int getAdRequestErrorType(CBError.CBImpressionError cBImpressionError) {
        switch (cBImpressionError) {
            case INTERNAL:
            case INVALID_RESPONSE:
            case NO_HOST_ACTIVITY:
            case USER_CANCELLATION:
            case WRONG_ORIENTATION:
            case ERROR_PLAYING_VIDEO:
            case ERROR_CREATING_VIEW:
            case SESSION_NOT_STARTED:
            case ERROR_DISPLAYING_VIEW:
            case ERROR_LOADING_WEB_VIEW:
            case INCOMPATIBLE_API_VERSION:
            case ASSET_PREFETCH_IN_PROGRESS:
            case IMPRESSION_ALREADY_VISIBLE:
            case ACTIVITY_MISSING_IN_MANIFEST:
            case WEB_VIEW_CLIENT_RECEIVED_ERROR:
                return 0;
            case NETWORK_FAILURE:
            case END_POINT_DISABLED:
            case INTERNET_UNAVAILABLE:
            case TOO_MANY_CONNECTIONS:
            case ASSETS_DOWNLOAD_FAILURE:
            case WEB_VIEW_PAGE_LOAD_TIMEOUT:
                return 2;
            case INVALID_LOCATION:
            case VIDEO_ID_MISSING:
            case HARDWARE_ACCELERATION_DISABLED:
            case FIRST_SESSION_INTERSTITIALS_DISABLED:
                return 1;
            default:
                return 3;
        }
    }
}
