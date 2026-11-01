package com.fingersoft.hcr2;

import com.fingersoft.ads_sdk.advertising.AdManager;
import com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialListener;
import com.fingersoft.hcr2.firebase.Firebase;
import com.fingersoft.hcr2.firebase.FirebaseAdListener;

/* JADX INFO: loaded from: classes.dex */
public class PlatformConfig {
    private static final String ADMOB_INT_BACKFILL_UNIT_ID = "ca-app-pub-4731967106298980/2930517354";
    private static final String ADMOB_INT_PHONE_UNIT_ID = "ca-app-pub-4731967106298980/1904186159";
    private static final String ADMOB_INT_TABLET_UNIT_ID = "ca-app-pub-4731967106298980/3380919355";
    private static final String ADMOB_VIDEO_ID = "ca-app-pub-4731967106298980/3688328159";
    public static final boolean ADS_ENABLED = true;
    public static final String APPSFLYER_DEV_KEY = "pTGntJjBNq2tfVJrTwi7FS";
    public static final String GOOGLE_PLAY_CLIENT_ID = "860483534254-8lq4nstdnhhktgdaujac1hdaqpoe0o63.apps.googleusercontent.com";
    public static final String HOCKEYAPP_APP_ID_RC = "11af47d463674192b85c5d6032a4f483";
    public static final String HOCKEYAPP_APP_ID_WEEKLY = "4c50f4a4f0484db08ae97bd26bba3abe";
    public static final boolean HOCKEYAPP_ENABLED = true;
    public static final String MARKET_VARIATION = "google";
    public static final String SUPERSONIC_VIDEO_ID = "58fe998d";

    public static void setupAds(AdManager adManager, Firebase firebase, InterstitialListener interstitialListener, float f, int i) {
    }

    public static void setupAds(Firebase firebase, FirebaseAdListener firebaseAdListener, float f) {
        firebase.initialiseAds(ADMOB_VIDEO_ID, f < 6.0f ? ADMOB_INT_PHONE_UNIT_ID : ADMOB_INT_TABLET_UNIT_ID, ADMOB_INT_BACKFILL_UNIT_ID, firebaseAdListener);
    }
}
