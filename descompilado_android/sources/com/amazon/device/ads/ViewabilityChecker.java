package com.amazon.device.ads;

import android.graphics.Rect;
import android.view.View;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class ViewabilityChecker {
    static final String HEIGHT_AD = "height";
    static final String INSTRUMENTATION_URL = "instrumentationPixelUrl";
    static final String IS_AD_ONSCREEN = "isAdOnScreen";
    private static final String LOGTAG = "ViewabilityChecker";
    static final String VIEWABLE_PERCENTAGE = "viewablePercentage";
    static final String WIDTH_AD = "width";
    static final String X_POSITION_AD = "x";
    static final String Y_POSITION_AD = "y";
    private final AdController adController;
    private float adTotalArea;
    private View adView;
    private final MobileAdsLogger logger;
    private ViewabilityOverlapCalculator viewabilityOverlapCalculator;

    public ViewabilityChecker(AdController adController) {
        this(adController, new MobileAdsLoggerFactory(), new ViewabilityOverlapCalculator(adController));
    }

    ViewabilityChecker(AdController adController, MobileAdsLoggerFactory mobileAdsLoggerFactory, ViewabilityOverlapCalculator viewabilityOverlapCalculator) {
        this.adController = adController;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        if (this.adController == null) {
            throw new IllegalArgumentException("AdController is null");
        }
        this.viewabilityOverlapCalculator = viewabilityOverlapCalculator;
    }

    public ViewabilityInfo getViewabilityInfo() {
        float fCalculateViewablePercentage;
        Rect rect = new Rect();
        this.adView = this.adController.getAdContainer().getCurrentAdView();
        View view = this.adView;
        if (view == null) {
            this.adTotalArea = 0.0f;
        } else {
            this.adTotalArea = view.getWidth() * this.adView.getHeight();
        }
        if (this.adTotalArea == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.logger.w("AdView width and height not set");
            return null;
        }
        boolean globalVisibleRect = this.adView.getGlobalVisibleRect(rect);
        boolean zIsShown = this.adView.isShown();
        boolean zHasWindowFocus = hasWindowFocus();
        boolean zIsAdTransparent = AndroidTargetUtils.isAdTransparent(this.adController.getAdContainer());
        if (zIsAdTransparent) {
            this.logger.forceLog(MobileAdsLogger.Level.WARN, "This ad view is transparent therefore it will not be considered viewable. Please ensure the ad view is completely opaque.", new Object[0]);
        }
        this.logger.d("IsAdVisible: %s, IsAdShown: %s, windowHasFocus: %s, IsAdTransparent: %s", Boolean.valueOf(globalVisibleRect), Boolean.valueOf(zIsShown), Boolean.valueOf(zHasWindowFocus), Boolean.valueOf(zIsAdTransparent));
        boolean z = globalVisibleRect && zIsShown && zHasWindowFocus && !zIsAdTransparent;
        if (!z) {
            fCalculateViewablePercentage = 0.0f;
        } else if (this.adController.isModal()) {
            fCalculateViewablePercentage = 100.0f;
        } else {
            long jCurrentTimeMillis = System.currentTimeMillis();
            fCalculateViewablePercentage = this.viewabilityOverlapCalculator.calculateViewablePercentage(this.adView, rect);
            this.logger.d("Total computation time: %d", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        }
        if (fCalculateViewablePercentage == 0.0f) {
            z = false;
        }
        return new ViewabilityInfo(z, getJSONObject(fCalculateViewablePercentage, z, this.adView));
    }

    private JSONObject getJSONObject(float f, boolean z, View view) {
        JSONObject jSONObject = new JSONObject();
        int[] iArr = new int[2];
        try {
            jSONObject.put(VIEWABLE_PERCENTAGE, f);
            jSONObject.put("width", view.getWidth());
            jSONObject.put("height", view.getHeight());
            if (z) {
                this.adView.getLocationOnScreen(iArr);
            }
            jSONObject.put("x", iArr[0]);
            jSONObject.put("y", iArr[1]);
            return jSONObject;
        } catch (JSONException e) {
            this.logger.w("JSON Error occured %s", e.getMessage());
            return null;
        }
    }

    private boolean hasWindowFocus() {
        View rootView = this.adController.getRootView();
        if (rootView == null) {
            return false;
        }
        return rootView.hasWindowFocus();
    }
}
