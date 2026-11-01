package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.games.GamesStatusCodes;

/* JADX INFO: loaded from: classes.dex */
public class as {
    public static boolean a(Context context) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int iIsGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(context);
        if (iIsGooglePlayServicesAvailable == 0) {
            return true;
        }
        if (!googleApiAvailability.isUserResolvableError(iIsGooglePlayServicesAvailable) || !(context instanceof Activity)) {
            return false;
        }
        googleApiAvailability.getErrorDialog((Activity) context, iIsGooglePlayServicesAvailable, GamesStatusCodes.STATUS_VIDEO_NOT_ACTIVE).show();
        return true;
    }
}
