package com.unity3d.services.ads;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.IUnityServicesListener;
import com.unity3d.services.UnityServices;
import com.unity3d.services.ads.adunit.AdUnitOpen;
import com.unity3d.services.ads.placement.Placement;
import com.unity3d.services.ads.properties.AdsProperties;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.properties.ClientProperties;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class UnityAdsImplementation {
    public static void initialize(Activity activity, String str, IUnityAdsListener iUnityAdsListener) {
        initialize(activity, str, iUnityAdsListener, false);
    }

    public static void initialize(Activity activity, String str, final IUnityAdsListener iUnityAdsListener, boolean z) {
        DeviceLog.entered();
        AdsProperties.setListener(iUnityAdsListener);
        UnityServices.initialize(activity, str, new IUnityServicesListener() { // from class: com.unity3d.services.ads.UnityAdsImplementation.1
            @Override // com.unity3d.services.IUnityServicesListener
            public void onUnityServicesError(UnityServices.UnityServicesError unityServicesError, String str2) {
                if (unityServicesError == UnityServices.UnityServicesError.INIT_SANITY_CHECK_FAIL) {
                    iUnityAdsListener.onUnityAdsError(UnityAds.UnityAdsError.INIT_SANITY_CHECK_FAIL, str2);
                } else if (unityServicesError == UnityServices.UnityServicesError.INVALID_ARGUMENT) {
                    iUnityAdsListener.onUnityAdsError(UnityAds.UnityAdsError.INVALID_ARGUMENT, str2);
                }
            }
        }, z);
    }

    public static boolean isInitialized() {
        return UnityServices.isInitialized();
    }

    public static void setListener(IUnityAdsListener iUnityAdsListener) {
        AdsProperties.setListener(iUnityAdsListener);
    }

    public static IUnityAdsListener getListener() {
        return AdsProperties.getListener();
    }

    public static boolean isSupported() {
        return UnityServices.isSupported();
    }

    public static String getVersion() {
        return UnityServices.getVersion();
    }

    public static boolean isReady() {
        return isSupported() && isInitialized() && Placement.isReady();
    }

    public static boolean isReady(String str) {
        return isSupported() && isInitialized() && str != null && Placement.isReady(str);
    }

    public static UnityAds.PlacementState getPlacementState() {
        if (isSupported() && isInitialized()) {
            return Placement.getPlacementState();
        }
        return UnityAds.PlacementState.NOT_AVAILABLE;
    }

    public static UnityAds.PlacementState getPlacementState(String str) {
        if (isSupported() && isInitialized() && str != null) {
            return Placement.getPlacementState(str);
        }
        return UnityAds.PlacementState.NOT_AVAILABLE;
    }

    public static void show(Activity activity) {
        if (Placement.getDefaultPlacement() != null) {
            show(activity, Placement.getDefaultPlacement());
        } else {
            handleShowError("", UnityAds.UnityAdsError.NOT_INITIALIZED, "Unity Ads default placement is not initialized");
        }
    }

    public static void show(final Activity activity, final String str) {
        if (activity == null) {
            handleShowError(str, UnityAds.UnityAdsError.INVALID_ARGUMENT, "Activity must not be null");
            return;
        }
        if (isReady(str)) {
            DeviceLog.info("Unity Ads opening new ad unit for placement " + str);
            ClientProperties.setActivity(activity);
            new Thread(new Runnable() { // from class: com.unity3d.services.ads.UnityAdsImplementation.2
                @Override // java.lang.Runnable
                public void run() {
                    Display defaultDisplay = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("requestedOrientation", activity.getRequestedOrientation());
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("rotation", defaultDisplay.getRotation());
                        if (Build.VERSION.SDK_INT >= 13) {
                            Point point = new Point();
                            defaultDisplay.getSize(point);
                            jSONObject2.put("width", point.x);
                            jSONObject2.put("height", point.y);
                        } else {
                            jSONObject2.put("width", defaultDisplay.getWidth());
                            jSONObject2.put("height", defaultDisplay.getHeight());
                        }
                        jSONObject.put("display", jSONObject2);
                    } catch (JSONException e) {
                        DeviceLog.exception("JSON error while constructing show options", e);
                    }
                    try {
                        if (AdUnitOpen.open(str, jSONObject)) {
                            return;
                        }
                        UnityAdsImplementation.handleShowError(str, UnityAds.UnityAdsError.INTERNAL_ERROR, "Webapp timeout, shutting down Unity Ads");
                    } catch (NoSuchMethodException e2) {
                        DeviceLog.exception("Could not get callback method", e2);
                        UnityAdsImplementation.handleShowError(str, UnityAds.UnityAdsError.SHOW_ERROR, "Could not get com.unity3d.ads.properties.showCallback method");
                    }
                }
            }).start();
            return;
        }
        if (!isSupported()) {
            handleShowError(str, UnityAds.UnityAdsError.NOT_INITIALIZED, "Unity Ads is not supported for this device");
            return;
        }
        if (!isInitialized()) {
            handleShowError(str, UnityAds.UnityAdsError.NOT_INITIALIZED, "Unity Ads is not initialized");
            return;
        }
        handleShowError(str, UnityAds.UnityAdsError.SHOW_ERROR, "Placement \"" + str + "\" is not ready");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleShowError(final String str, final UnityAds.UnityAdsError unityAdsError, String str2) {
        final String str3 = "Unity Ads show failed: " + str2;
        DeviceLog.error(str3);
        final IUnityAdsListener listener = AdsProperties.getListener();
        if (listener != null) {
            Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.ads.UnityAdsImplementation.3
                @Override // java.lang.Runnable
                public void run() {
                    listener.onUnityAdsError(unityAdsError, str3);
                    String str4 = str;
                    if (str4 != null) {
                        listener.onUnityAdsFinish(str4, UnityAds.FinishState.ERROR);
                    } else {
                        listener.onUnityAdsFinish("", UnityAds.FinishState.ERROR);
                    }
                }
            });
        }
    }

    public static void setDebugMode(boolean z) {
        UnityServices.setDebugMode(z);
    }

    public static boolean getDebugMode() {
        return UnityServices.getDebugMode();
    }
}
