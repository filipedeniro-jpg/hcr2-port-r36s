package com.ironsource.sdk.utils;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import com.applovin.sdk.AppLovinEventParameters;
import com.facebook.appevents.AppEventsConstants;
import com.fingersoft.billing.util.IabHelper;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.data.SSAEnums;
import com.ironsource.sdk.data.SSAObj;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SDKUtils {
    private static final String TAG = "SDKUtils";
    private static String mAdvertisingId = null;
    private static String mControllerConfig = null;
    private static String mControllerUrl = null;
    private static int mDebugMode = 0;
    private static Map<String, String> mInitSDKParams = null;
    private static boolean mIsLimitedTrackingEnabled = true;
    private static String mUserGroup = "";
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static String getSDKVersion() {
        return Constants.SDK_VERSION;
    }

    public static String translateOrientation(int i) {
        if (i != 1) {
            return i != 2 ? "none" : "landscape";
        }
        return "portrait";
    }

    public static String translateRequestedOrientation(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 11) {
                    if (i != 12) {
                        switch (i) {
                            case 6:
                            case 8:
                                break;
                            case 7:
                            case 9:
                                break;
                            default:
                                return "none";
                        }
                    }
                }
            }
            return "portrait";
        }
        return "landscape";
    }

    public static String getFileName(String str) {
        String[] strArrSplit = str.split(File.separator);
        try {
            return URLEncoder.encode(strArrSplit[strArrSplit.length - 1].split("\\?")[0], "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int pxToDp(long j) {
        return (int) ((j / Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static int dpToPx(long j) {
        return (int) ((j * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static int convertPxToDp(int i) {
        return (int) TypedValue.applyDimension(1, i, Resources.getSystem().getDisplayMetrics());
    }

    public static int convertDpToPx(int i) {
        return (int) TypedValue.applyDimension(0, i, Resources.getSystem().getDisplayMetrics());
    }

    public static JSONObject getOrientation(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("orientation", translateOrientation(DeviceStatus.getDeviceOrientation(context)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static String createErrorMessage(String str, String str2) {
        return String.format("%s Failure occurred during initiation at: %s", str, str2);
    }

    public static Long getCurrentTimeMillis() {
        return Long.valueOf(System.currentTimeMillis());
    }

    public static boolean isApplicationVisible(Context context) {
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equalsIgnoreCase(packageName) && runningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }

    public static void showNoInternetDialog(Context context) {
        new AlertDialog.Builder(context).setMessage("No Internet Connection").setPositiveButton("Ok", new DialogInterface.OnClickListener() { // from class: com.ironsource.sdk.utils.SDKUtils.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    public static byte[] encrypt(String str) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(CommonUtils.SHA1_INSTANCE);
            try {
                messageDigest.reset();
                messageDigest.update(str.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e = e;
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e2) {
                e = e2;
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e3) {
            e = e3;
            messageDigest = null;
        } catch (NoSuchAlgorithmException e4) {
            e = e4;
            messageDigest = null;
        }
        if (messageDigest != null) {
            return messageDigest.digest();
        }
        return null;
    }

    public static String convertStreamToString(InputStream inputStream, boolean z, String str, String str2) throws Throwable {
        Throwable th;
        BufferedReader bufferedReader;
        InputStream gZIPInputStream = z ? new GZIPInputStream(inputStream) : inputStream;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(str, str2)));
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(gZIPInputStream, "UTF-8"));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                    sb.append("\n");
                }
                bufferedWriter.write(sb.toString());
                String string = sb.toString();
                bufferedReader.close();
                gZIPInputStream.close();
                if (z) {
                    inputStream.close();
                }
                bufferedWriter.close();
                return string;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                gZIPInputStream.close();
                if (z) {
                    inputStream.close();
                }
                bufferedWriter.close();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
    }

    public static String encodeString(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public static String decodeString(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Logger.d(TAG, "Failed decoding string " + e.getMessage());
            return "";
        }
    }

    public static String getMD5(String str) {
        try {
            String string = new BigInteger(1, MessageDigest.getInstance("MD5").digest(str.getBytes())).toString(16);
            while (string.length() < 32) {
                string = AppEventsConstants.EVENT_PARAM_VALUE_NO + string;
            }
            return string;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadGoogleAdvertiserInfo(Context context) {
        try {
            String[] advertisingIdInfo = DeviceStatus.getAdvertisingIdInfo(context);
            mAdvertisingId = advertisingIdInfo[0];
            mIsLimitedTrackingEnabled = Boolean.valueOf(advertisingIdInfo[1]).booleanValue();
        } catch (Exception e) {
            if (e.getMessage() != null) {
                Logger.i(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            }
            if (e.getCause() != null) {
                Logger.i(TAG, e.getClass().getSimpleName() + ": " + e.getCause());
            }
        }
    }

    public static String getAdvertiserId() {
        return mAdvertisingId;
    }

    public static boolean isLimitAdTrackingEnabled() {
        return mIsLimitedTrackingEnabled;
    }

    public static Object getIInAppBillingServiceClass(IBinder iBinder) {
        String str;
        StringBuilder sb;
        try {
            return Class.forName("com.android.vending.billing.IInAppBillingService$Stub").getMethod("asInterface", IBinder.class).invoke(null, iBinder);
        } catch (ClassNotFoundException e) {
            e = e;
            if (e.getMessage() != null) {
                Logger.i(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            }
            if (e.getCause() != null) {
                str = TAG;
                sb = new StringBuilder();
                sb.append(e.getClass().getSimpleName());
                sb.append(": ");
                sb.append(e.getCause());
                Logger.i(str, sb.toString());
            }
            return null;
        } catch (IllegalAccessException e2) {
            e = e2;
            if (e.getMessage() != null) {
                Logger.i(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            }
            if (e.getCause() != null) {
                str = TAG;
                sb = new StringBuilder();
                sb.append(e.getClass().getSimpleName());
                sb.append(": ");
                sb.append(e.getCause());
                Logger.i(str, sb.toString());
            }
            return null;
        } catch (IllegalArgumentException e3) {
            e = e3;
            if (e.getMessage() != null) {
                Logger.i(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            }
            if (e.getCause() != null) {
                str = TAG;
                sb = new StringBuilder();
                sb.append(e.getClass().getSimpleName());
                sb.append(": ");
                sb.append(e.getCause());
                Logger.i(str, sb.toString());
            }
            return null;
        } catch (NoSuchMethodException e4) {
            e = e4;
            if (e.getMessage() != null) {
                Logger.i(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            }
            if (e.getCause() != null) {
                str = TAG;
                sb = new StringBuilder();
                sb.append(e.getClass().getSimpleName());
                sb.append(": ");
                sb.append(e.getCause());
                Logger.i(str, sb.toString());
            }
            return null;
        } catch (InvocationTargetException e5) {
            e = e5;
            if (e.getMessage() != null) {
                Logger.i(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            }
            if (e.getCause() != null) {
                str = TAG;
                sb = new StringBuilder();
                sb.append(e.getClass().getSimpleName());
                sb.append(": ");
                sb.append(e.getCause());
                Logger.i(str, sb.toString());
            }
            return null;
        }
    }

    public static String queryingPurchasedItems(Object obj, String str) {
        String str2;
        StringBuilder sb;
        JSONArray jSONArray = new JSONArray();
        try {
            Object objInvoke = obj.getClass().getMethod("getPurchases", Integer.TYPE, String.class, String.class, String.class).invoke(obj, 3, str, IabHelper.ITEM_TYPE_INAPP, null);
            Method method = objInvoke.getClass().getMethod("getInt", String.class);
            Method method2 = objInvoke.getClass().getMethod("getStringArrayList", String.class);
            Method method3 = objInvoke.getClass().getMethod("getString", String.class);
            if (((Integer) method.invoke(objInvoke, IabHelper.RESPONSE_CODE)).intValue() == 0) {
                ArrayList arrayList = (ArrayList) method2.invoke(objInvoke, IabHelper.RESPONSE_INAPP_ITEM_LIST);
                ArrayList arrayList2 = (ArrayList) method2.invoke(objInvoke, IabHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST);
                ArrayList arrayList3 = (ArrayList) method2.invoke(objInvoke, IabHelper.RESPONSE_INAPP_SIGNATURE_LIST);
                for (int i = 0; i < arrayList2.size(); i++) {
                    String str3 = (String) arrayList2.get(i);
                    String str4 = (String) arrayList3.get(i);
                    String str5 = (String) arrayList.get(i);
                    Logger.i(TAG, str3);
                    Logger.i(TAG, str4);
                    Logger.i(TAG, str5);
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("purchaseData", str3);
                        jSONObject.put(InAppPurchaseMetaData.KEY_SIGNATURE, str3);
                        jSONObject.put(AppLovinEventParameters.PRODUCT_IDENTIFIER, str3);
                        jSONArray.put(jSONObject);
                    } catch (JSONException unused) {
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e = e;
            if (e.getMessage() != null) {
                Logger.i(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            }
            if (e.getCause() != null) {
                str2 = TAG;
                sb = new StringBuilder();
                sb.append(e.getClass().getSimpleName());
                sb.append(": ");
                sb.append(e.getCause());
                Logger.i(str2, sb.toString());
            }
        } catch (IllegalArgumentException e2) {
            e = e2;
            if (e.getMessage() != null) {
                Logger.i(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            }
            if (e.getCause() != null) {
                str2 = TAG;
                sb = new StringBuilder();
                sb.append(e.getClass().getSimpleName());
                sb.append(": ");
                sb.append(e.getCause());
                Logger.i(str2, sb.toString());
            }
        } catch (NoSuchMethodException e3) {
            e = e3;
            if (e.getMessage() != null) {
                Logger.i(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            }
            if (e.getCause() != null) {
                str2 = TAG;
                sb = new StringBuilder();
                sb.append(e.getClass().getSimpleName());
                sb.append(": ");
                sb.append(e.getCause());
                Logger.i(str2, sb.toString());
            }
        } catch (InvocationTargetException e4) {
            e = e4;
            if (e.getMessage() != null) {
                Logger.i(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            }
            if (e.getCause() != null) {
                str2 = TAG;
                sb = new StringBuilder();
                sb.append(e.getClass().getSimpleName());
                sb.append(": ");
                sb.append(e.getCause());
                Logger.i(str2, sb.toString());
            }
        }
        return jSONArray.toString();
    }

    public static String getControllerUrl() {
        return !TextUtils.isEmpty(mControllerUrl) ? mControllerUrl : "";
    }

    public static void setControllerUrl(String str) {
        mControllerUrl = str;
    }

    public static String getControllerConfig() {
        return mControllerConfig;
    }

    public static void setControllerConfig(String str) {
        mControllerConfig = str;
    }

    public static Map<String, String> getInitSDKParams() {
        return mInitSDKParams;
    }

    public static void setInitSDKParams(Map<String, String> map) {
        mInitSDKParams = map;
    }

    public static int getDebugMode() {
        return mDebugMode;
    }

    public static void setDebugMode(int i) {
        mDebugMode = i;
    }

    public static String getValueFromJsonObject(String str, String str2) {
        try {
            return new JSONObject(str).getString(str2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isExternalStorageAvailable() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }

    public static int getActivityUIFlags(boolean z) {
        int i = Build.VERSION.SDK_INT >= 14 ? 2 : 0;
        if (Build.VERSION.SDK_INT >= 16) {
            i |= 1796;
        }
        return (Build.VERSION.SDK_INT < 19 || !z) ? i : i | 4096;
    }

    private static int generateViewIdForOldOS() {
        int i;
        int i2;
        do {
            i = sNextGeneratedId.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!sNextGeneratedId.compareAndSet(i, i2));
        return i;
    }

    public static int generateViewId() {
        if (Build.VERSION.SDK_INT < 17) {
            return generateViewIdForOldOS();
        }
        return View.generateViewId();
    }

    public static JSONObject getControllerConfigAsJSONObject() {
        try {
            return new JSONObject(getControllerConfig());
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static SSAEnums.ProductType getProductType(String str) {
        if (str.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
            return SSAEnums.ProductType.RewardedVideo;
        }
        if (str.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
            return SSAEnums.ProductType.Interstitial;
        }
        if (str.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
            return SSAEnums.ProductType.OfferWall;
        }
        return null;
    }

    public static void setTesterParameters(String str) {
        mUserGroup = str;
    }

    public static String getTesterParameters() {
        return mUserGroup;
    }

    public static JSONObject mergeJSONObjects(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        JSONObject jSONObject3 = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (jSONObject != null) {
            jSONObject3 = new JSONObject(jSONObject.toString());
        }
        if (jSONObject2 != null) {
            jSONArray = jSONObject2.names();
        }
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getString(i);
                jSONObject3.putOpt(string, jSONObject2.opt(string));
            }
        }
        return jSONObject3;
    }

    public static String flatMapToJsonAsString(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> next = it.next();
                try {
                    jSONObject.putOpt(next.getKey(), encodeString(next.getValue()));
                } catch (JSONException e) {
                    Logger.i(TAG, "flatMapToJsonAsStringfailed " + e.toString());
                }
                it.remove();
            }
        }
        return jSONObject.toString();
    }

    public static Map<String, String> mergeHashMaps(Map<String, String>[] mapArr) {
        HashMap map = new HashMap();
        if (mapArr == null) {
            return map;
        }
        for (Map<String, String> map2 : mapArr) {
            if (map2 != null) {
                map.putAll(map2);
            }
        }
        return map;
    }

    public static String fetchDemandSourceId(SSAObj sSAObj) {
        return fetchDemandSourceId(sSAObj.getJsonObject());
    }

    public static String fetchDemandSourceId(JSONObject jSONObject) {
        String strOptString = jSONObject.optString("demandSourceId");
        return !TextUtils.isEmpty(strOptString) ? strOptString : jSONObject.optString("demandSourceName");
    }

    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static String requireNonEmptyOrNull(String str, String str2) {
        if (str != null) {
            return str;
        }
        throw new NullPointerException(str2);
    }
}
