package com.ironsource.sdk.service;

import android.content.Context;
import android.text.TextUtils;
import com.ironsource.environment.ApplicationContext;
import com.ironsource.environment.ConnectivityService;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.utils.DeviceProperties;
import com.ironsource.sdk.utils.IronSourceStorageUtils;
import com.ironsource.sdk.utils.Logger;
import com.ironsource.sdk.utils.SDKUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceData {
    private static final String TAG = "DeviceData";

    public static JSONObject fetchAdvertiserIdData(Context context) {
        SDKUtils.loadGoogleAdvertiserInfo(context);
        String advertiserId = SDKUtils.getAdvertiserId();
        Boolean boolValueOf = Boolean.valueOf(SDKUtils.isLimitAdTrackingEnabled());
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(advertiserId)) {
            try {
                Logger.i(TAG, "add AID and LAT");
                jSONObject.put(Constants.RequestParameters.isLAT, boolValueOf);
                jSONObject.put(Constants.RequestParameters.DEVICE_IDS + Constants.RequestParameters.LEFT_BRACKETS + Constants.RequestParameters.AID + Constants.RequestParameters.RIGHT_BRACKETS, SDKUtils.encodeString(advertiserId));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jSONObject;
    }

    public static JSONObject fetchMutableData(Context context) {
        JSONObject jSONObject = new JSONObject();
        updateWithDisplaySize(jSONObject);
        updateWithConnectionType(context, jSONObject);
        updateWithFreeDiskSize(context, jSONObject);
        updateWithBatteryLevel(context, jSONObject);
        updateWithDeviceVolume(context, jSONObject);
        return jSONObject;
    }

    private static void updateObjectWithKeyValue(JSONObject jSONObject, String str, String str2) {
        try {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            jSONObject.put(str, SDKUtils.encodeString(str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateWithDisplaySize(JSONObject jSONObject) {
        try {
            updateObjectWithKeyValue(jSONObject, Constants.RequestParameters.DISPLAY_SIZE_WIDTH, String.valueOf(DeviceStatus.getDisplayWidth()));
            updateObjectWithKeyValue(jSONObject, Constants.RequestParameters.DISPLAY_SIZE_HEIGHT, String.valueOf(DeviceStatus.getDisplayHeight()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateWithConnectionType(Context context, JSONObject jSONObject) {
        try {
            String connectionType = ConnectivityService.getConnectionType(context);
            if (TextUtils.isEmpty(connectionType)) {
                return;
            }
            jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.CONNECTION_TYPE), SDKUtils.encodeString(connectionType));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateWithBatteryLevel(Context context, JSONObject jSONObject) {
        try {
            jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.BATTERY_LEVEL), DeviceStatus.getBatteryLevel(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateWithDeviceVolume(Context context, JSONObject jSONObject) {
        try {
            jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_VOLUME), DeviceProperties.getInstance(context).getDeviceVolume(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateWithFreeDiskSize(Context context, JSONObject jSONObject) {
        try {
            jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DISK_FREE_SIZE), SDKUtils.encodeString(String.valueOf(DeviceStatus.getAvailableMemorySizeInMegaBytes(IronSourceStorageUtils.getDiskCacheDirPath(context)))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject fetchPermanentData(Context context) {
        DeviceProperties deviceProperties = DeviceProperties.getInstance(context);
        JSONObject jSONObject = new JSONObject();
        try {
            String deviceOem = deviceProperties.getDeviceOem();
            if (deviceOem != null) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_OEM), SDKUtils.encodeString(deviceOem));
            }
            String deviceModel = deviceProperties.getDeviceModel();
            if (deviceModel != null) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_MODEL), SDKUtils.encodeString(deviceModel));
            }
            String deviceOsType = deviceProperties.getDeviceOsType();
            if (deviceOsType != null) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_OS), SDKUtils.encodeString(deviceOsType));
            }
            String deviceOsVersion = deviceProperties.getDeviceOsVersion();
            if (deviceOsVersion != null) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_OS_VERSION), deviceOsVersion.replaceAll("[^0-9/.]", ""));
            }
            String deviceOsVersion2 = deviceProperties.getDeviceOsVersion();
            if (deviceOsVersion2 != null) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_OS_VERSION_FULL), SDKUtils.encodeString(deviceOsVersion2));
            }
            jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_API_LEVEL), String.valueOf(deviceProperties.getDeviceApiLevel()));
            String supersonicSdkVersion = DeviceProperties.getSupersonicSdkVersion();
            if (supersonicSdkVersion != null) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.SDK_VERSION), SDKUtils.encodeString(supersonicSdkVersion));
            }
            if (deviceProperties.getDeviceCarrier() != null && deviceProperties.getDeviceCarrier().length() > 0) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.MOBILE_CARRIER), SDKUtils.encodeString(deviceProperties.getDeviceCarrier()));
            }
            String language = context.getResources().getConfiguration().locale.getLanguage();
            if (!TextUtils.isEmpty(language)) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_LANGUAGE), SDKUtils.encodeString(language.toUpperCase()));
            }
            String packageName = ApplicationContext.getPackageName(context);
            if (!TextUtils.isEmpty(packageName)) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.PACKAGE_NAME), SDKUtils.encodeString(packageName));
            }
            String strValueOf = String.valueOf(DeviceStatus.getDeviceDensity());
            if (!TextUtils.isEmpty(strValueOf)) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_SCREEN_SCALE), SDKUtils.encodeString(strValueOf));
            }
            String strValueOf2 = String.valueOf(DeviceStatus.isRootedDevice());
            if (!TextUtils.isEmpty(strValueOf2)) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.IS_ROOT_DEVICE), SDKUtils.encodeString(strValueOf2));
            }
            jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.NETWORK_MCC), ConnectivityService.getNetworkMCC(context));
            jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.NETWORK_MNC), ConnectivityService.getNetworkMNC(context));
            jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.PHONE_TYPE), ConnectivityService.getPhoneType(context));
            jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.SIM_OPERATOR), SDKUtils.encodeString(ConnectivityService.getSimOperator(context)));
            jSONObject.put(SDKUtils.encodeString("lastUpdateTime"), ApplicationContext.getLastUpdateTime(context));
            jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.FIRST_INSTALL_TIME), ApplicationContext.getFirstInstallTime(context));
            jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.APPLICATION_VERSION_NAME), SDKUtils.encodeString(ApplicationContext.getApplicationVersionName(context)));
            String installerPackageName = ApplicationContext.getInstallerPackageName(context);
            if (!TextUtils.isEmpty(installerPackageName)) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.INSTALLER_PACKAGE_NAME), SDKUtils.encodeString(installerPackageName));
            }
            jSONObject.put(Constants.RequestParameters.LOCAL_TIME, SDKUtils.encodeString(String.valueOf(DeviceStatus.getDeviceLocalTime())));
            jSONObject.put(Constants.RequestParameters.TIMEZONE_OFFSET, SDKUtils.encodeString(String.valueOf(DeviceStatus.getDeviceTimeZoneOffsetInMinutes())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
