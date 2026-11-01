package com.fingersoft.liveops_sdk;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.widget.RelativeLayout;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.share.Constants;
import com.fingersoft.liveops_sdk.crosspromotions.CrossPromotionListener;
import com.fingersoft.liveops_sdk.crosspromotions.CrossPromotionManager;
import com.fingersoft.liveops_sdk.crosspromotions.HtmlCrossPromotionDialog;
import com.fingersoft.liveops_sdk.eula.EULAListener;
import com.fingersoft.liveops_sdk.eula.EULAWindow;
import com.fingersoft.liveops_sdk.news.GameNews;
import com.fingersoft.liveops_sdk.news.GameNewsListener;
import com.fingersoft.liveops_sdk.settings.ApplicationSettings;
import com.fingersoft.liveops_sdk.settings.MarketVariation;
import com.fingersoft.liveops_sdk.settings.SettingsListener;
import com.fingersoft.liveops_sdk.utils.ApiHelper;
import com.fingersoft.liveops_sdk.utils.AppRatingDialog;
import com.fingersoft.liveops_sdk.utils.CommonUtils;
import com.fingersoft.liveops_sdk.utils.DeviceCapabilities;
import com.fingersoft.liveops_sdk.utils.EmptyJsonResultContainer;
import com.fingersoft.liveops_sdk.utils.IResultContainer;
import com.fingersoft.liveops_sdk.utils.ISdkInitListener;
import com.fingersoft.liveops_sdk.utils.JsonResponseHandler;
import com.fingersoft.liveops_sdk.utils.Log;
import com.fingersoft.liveops_sdk.utils.NetworkListener;
import com.fingersoft.liveops_sdk.utils.NetworkMonitor;
import com.fingersoft.liveops_sdk.utils.SettingCollection;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import io.fabric.sdk.android.services.network.UrlUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class LiveopsManager implements NetworkListener {
    private static final String AESIv = "FsD1S9pA0Fz1ilcn";
    private static final String AESKey = "WwctgcnYnQ1iMv91vos6CLD1XdTAzsyC";
    private static final String PREFS_NAME = "ADMANAGER_SETTINGS";
    public static String TAG = "LiveOps SDK";
    private static int mMarketVariation;
    private static int mStartupCount;
    Activity mActivity;
    private RelativeLayout mAdTargetLayout;
    private String mAppVersionId;
    private CrossPromotionManager mCrossPromotionManager;
    private int mCurrentVersionCode;
    private NetworkListener mNetworkListener;
    private ISdkInitListener mSdkInitializedListener;
    private String mBaseAddress = "http://ads3.fingersoft.net:3000/";
    ConnectivityManager mConnectivityManager = null;
    private HtmlCrossPromotionDialog mAppReleaseCheck = null;
    private SettingsListener mSettingsListnener = null;
    boolean mHasNetworkConnection = true;
    private boolean mVIPStatus = true;
    private String mLocale = null;
    private String mNetworkMcc = "";
    private boolean mGetRequest = false;
    private String mSimMcc = "";
    private AtomicReference<String> mAdditionalRequestParams = new AtomicReference<>("");
    private int mStartupCountWithCurrentVersion = 0;
    private IResultContainer mContainer = null;
    private AtomicReference<HashMap<String, String>> mAdditionalReqParams = new AtomicReference<>(new HashMap());
    private boolean mIsStarted = false;
    private AppRatingDialog mAppRatingDialog = null;
    private AtomicReference<String> mAdvertisingId = new AtomicReference<>(null);
    private AtomicReference<AdvertisingIdClient.Info> mAdInfo = new AtomicReference<>(null);
    private int mShowEulaCallCount = 0;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canShowEula() {
        return true;
    }

    public void onPause() {
    }

    public LiveopsManager(Activity activity, RelativeLayout relativeLayout, ISdkInitListener iSdkInitListener, CrossPromotionListener crossPromotionListener) {
        this.mActivity = null;
        this.mCurrentVersionCode = 0;
        this.mAppVersionId = "NO_ID";
        this.mSdkInitializedListener = null;
        try {
            PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            this.mAppVersionId = packageInfo.versionCode + Constants.URL_PATH_DELIMITER + packageInfo.versionName;
            this.mCurrentVersionCode = packageInfo.versionCode;
            CommonUtils.log("Current mCurrentVersionCode " + this.mAppVersionId);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        this.mActivity = activity;
        this.mAdTargetLayout = relativeLayout;
        this.mSdkInitializedListener = iSdkInitListener;
        this.mCrossPromotionManager = new CrossPromotionManager(this, crossPromotionListener);
    }

    public LiveopsManager withBaseServerAddress(String str) {
        if (!str.endsWith(Constants.URL_PATH_DELIMITER)) {
            str = str + Constants.URL_PATH_DELIMITER;
        }
        this.mBaseAddress = str;
        CommonUtils.log("Setting base server address to: " + this.mBaseAddress);
        return this;
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public String getBaseAddress() {
        return this.mBaseAddress;
    }

    public boolean getVIPStatus() {
        return this.mVIPStatus;
    }

    public String getPackageName() {
        return this.mActivity.getPackageName();
    }

    public String getPlatformName() {
        return MarketVariation.derivePlatformName(mMarketVariation);
    }

    public String getNetworkMcc() {
        return this.mNetworkMcc;
    }

    public IResultContainer getContainer() {
        return this.mContainer;
    }

    public int getStartCountWithVersion() {
        return this.mStartupCountWithCurrentVersion;
    }

    public int getCurrentVersionCode() {
        return this.mCurrentVersionCode;
    }

    public void setVIPStatus(boolean z) {
        this.mVIPStatus = z;
    }

    public LiveopsManager withGameNewsListener(GameNewsListener gameNewsListener) {
        Log.d("LIVEOPS", "Gamenewslistener");
        GameNews.getInstance(this.mActivity).setListener(gameNewsListener);
        return this;
    }

    public LiveopsManager withLogging(Boolean bool) {
        Log.LOG = bool.booleanValue();
        return this;
    }

    public static String getPrefValueString(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(PREFS_NAME, 0).getString(str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }

    public static void setPrefValueString(Context context, String str, String str2) {
        try {
            SharedPreferences.Editor editorEdit = context.getApplicationContext().getSharedPreferences(PREFS_NAME, 0).edit();
            editorEdit.putString(str, str2);
            editorEdit.commit();
        } catch (Exception unused) {
        }
    }

    public static int getPrefValueInt(Context context, String str, int i) {
        try {
            return context.getSharedPreferences(PREFS_NAME, 0).getInt(str, i);
        } catch (Exception unused) {
            return i;
        }
    }

    public static void setPrefValueInt(Context context, String str, int i) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFS_NAME, 0).edit();
            editorEdit.putInt(str, i);
            editorEdit.commit();
        } catch (Exception unused) {
        }
    }

    public LiveopsManager withSettingsListener(SettingsListener settingsListener) {
        this.mSettingsListnener = settingsListener;
        return this;
    }

    public LiveopsManager withNetworkListener(NetworkListener networkListener) {
        this.mNetworkListener = networkListener;
        return this;
    }

    public void manage() {
        this.mLocale = this.mActivity.getResources().getConfiguration().locale.getCountry().toUpperCase(Locale.US);
        initialise(this.mActivity);
        this.mIsStarted = true;
    }

    private void initialise(Activity activity) {
        CommonUtils.log("Initialising LiveopsManager with baseAddress: " + this.mBaseAddress);
        this.mActivity = activity;
        NetworkMonitor.isConnected = this.mHasNetworkConnection;
        NetworkMonitor.listener = this;
        getPriorities();
        getCountryCodes();
        increaseStartupCounter();
        new Thread(new AdvertisingWorker(this)).start();
    }

    @Override // com.fingersoft.liveops_sdk.utils.NetworkListener
    public void onConnectivityChanged(boolean z) {
        if (!z) {
            CommonUtils.log("Network turned off");
        } else {
            CommonUtils.log("Network turned on");
        }
        NetworkListener networkListener = this.mNetworkListener;
        if (networkListener != null) {
            networkListener.onConnectivityChanged(z);
        }
    }

    private void getCountryCodes() {
        String networkOperator;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.mActivity.getSystemService("phone");
            if (telephonyManager != null) {
                if (telephonyManager.getPhoneType() != 2 && (networkOperator = telephonyManager.getNetworkOperator()) != null && networkOperator.length() >= 3) {
                    this.mNetworkMcc = networkOperator.substring(0, 3);
                    CommonUtils.log("Network MCC determined to be: " + this.mNetworkMcc);
                    this.mAdditionalRequestParams.set(this.mAdditionalRequestParams.get() + "&networkMcc=" + this.mNetworkMcc);
                }
                String simOperator = telephonyManager.getSimOperator();
                if (simOperator == null || simOperator.length() < 3) {
                    return;
                }
                this.mSimMcc = simOperator.substring(0, 3);
                CommonUtils.log("SIM MCC determined to be: " + this.mSimMcc);
                this.mAdditionalRequestParams.set(this.mAdditionalRequestParams.get() + "&simMcc=" + this.mSimMcc);
            }
        } catch (Exception e) {
            CommonUtils.log("Could not determine country network codes: " + e.getMessage());
        }
    }

    private void increaseStartupCounter() {
        CommonUtils.log("Incrementing startup counter");
        mStartupCount = getPrefValueInt(this.mActivity, "startup_counter", 0);
        setPrefValueInt(this.mActivity, "startup_counter", mStartupCount + 1);
        int prefValueInt = getPrefValueInt(this.mActivity, "last_versioncode", -1);
        int i = this.mCurrentVersionCode;
        if (prefValueInt != i) {
            setPrefValueInt(this.mActivity, "last_versioncode", i);
            setPrefValueInt(this.mActivity, "startup_counter_currentversion", 1);
            this.mStartupCountWithCurrentVersion = 0;
            CommonUtils.log("startupcounter is 0 versioncode " + this.mCurrentVersionCode);
            return;
        }
        this.mStartupCountWithCurrentVersion = getPrefValueInt(this.mActivity, "startup_counter_currentversion", 0);
        setPrefValueInt(this.mActivity, "startup_counter_currentversion", this.mStartupCountWithCurrentVersion + 1);
        CommonUtils.log("current version startup is " + (this.mStartupCountWithCurrentVersion + 1));
    }

    public boolean isPackageInstalled(String str) {
        try {
            this.mActivity.getPackageManager().getPackageInfo(str, 128);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public void onCrossPromotionEvent(CrossPromotionManager.CrossPromotionEvent crossPromotionEvent) {
        if (this.mCrossPromotionManager != null) {
            Log.d(TAG, "Cross promotion event in liveopsmanager");
            this.mCrossPromotionManager.onEvent(crossPromotionEvent);
        }
    }

    protected void runCrossPromotion() {
        if (checkNetworkConnection()) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.fingersoft.liveops_sdk.LiveopsManager.1
                @Override // java.lang.Runnable
                public void run() {
                    LiveopsManager.this.mCrossPromotionManager.loadCrossPromotion();
                }
            });
        }
    }

    private void cancelCrossPromotion() {
        HtmlCrossPromotionDialog htmlCrossPromotionDialog = this.mAppReleaseCheck;
        if (htmlCrossPromotionDialog != null) {
            htmlCrossPromotionDialog.dismissPromotion();
        }
    }

    public boolean hasPromotions() {
        try {
            if (this.mContainer == null) {
                return false;
            }
            return this.mContainer.getPromotions().hasPromotions();
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean checkNetworkConnection() {
        if (this.mConnectivityManager == null) {
            Activity activity = this.mActivity;
            if (activity == null) {
                return true;
            }
            this.mConnectivityManager = (ConnectivityManager) activity.getSystemService("connectivity");
        }
        ConnectivityManager connectivityManager = this.mConnectivityManager;
        if (connectivityManager == null) {
            return true;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        this.mHasNetworkConnection = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        return this.mHasNetworkConnection;
    }

    public void showEulaWindow(final EULAListener eULAListener) {
        boolean z;
        int i;
        CommonUtils.log("At show eula");
        try {
            if (EULAWindow.isEulaAcceptedAlready(this.mActivity)) {
                CommonUtils.log("eula already accepted");
                eULAListener.onEULAAccepted();
                return;
            }
            if (this.mContainer != null && this.mContainer.getSettings() != null) {
                List<SettingCollection.Setting> settings = this.mContainer.getSettings().getSettings();
                CommonUtils.log("eula Settings size " + settings.size());
                Iterator<SettingCollection.Setting> it = settings.iterator();
                while (true) {
                    z = false;
                    if (!it.hasNext()) {
                        i = 0;
                        break;
                    }
                    SettingCollection.Setting next = it.next();
                    CommonUtils.log("eula settings: " + next.getName());
                    if (next.getName().equalsIgnoreCase("eula_showafter")) {
                        i = Integer.parseInt(next.getValue());
                        CommonUtils.log("eula show after found " + i);
                        break;
                    }
                }
                for (SettingCollection.Setting setting : settings) {
                    if (setting.getName().equalsIgnoreCase("eula_enabled")) {
                        CommonUtils.log("eula enabled received " + setting.getValue());
                        if (Integer.parseInt(setting.getValue()) == 1) {
                            z = true;
                            break;
                        }
                    }
                }
                CommonUtils.log("Showing with eula parameters showAfter: " + i + " mStartupCount: " + mStartupCount + " showEulaCallCount: " + this.mShowEulaCallCount);
                if (z) {
                    if (i == 0 || i <= mStartupCount + this.mShowEulaCallCount) {
                        CommonUtils.log("Showing eula");
                        this.mActivity.runOnUiThread(new Runnable() { // from class: com.fingersoft.liveops_sdk.LiveopsManager.3
                            @Override // java.lang.Runnable
                            public void run() {
                                if (LiveopsManager.this.canShowEula()) {
                                    new EULAWindow(LiveopsManager.this.mActivity, eULAListener).show();
                                } else {
                                    CommonUtils.log("Canceling eula");
                                }
                            }
                        });
                    }
                    this.mShowEulaCallCount++;
                    return;
                }
                return;
            }
            CommonUtils.log("Startup not received, show eula if startupcount + eula call count >= 3");
            if (mStartupCount + this.mShowEulaCallCount >= 3) {
                this.mActivity.runOnUiThread(new Runnable() { // from class: com.fingersoft.liveops_sdk.LiveopsManager.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (LiveopsManager.this.canShowEula()) {
                            new EULAWindow(LiveopsManager.this.mActivity, eULAListener).show();
                        } else {
                            CommonUtils.log("Canceling eula");
                        }
                    }
                });
            }
            this.mShowEulaCallCount++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onNewsRead() {
        GameNews.getInstance(this.mActivity).onNewsRead();
    }

    public void onNewsPopupShown() {
        GameNews.getInstance(this.mActivity).onNewsPopupShown();
    }

    public long getNewsShownLast() {
        return GameNews.getInstance(this.mActivity).getNewsReadTimestamp();
    }

    public void onResume(Activity activity) {
        CommonUtils.log("LiveopsManager: resume");
        this.mActivity = activity;
        increaseStartupCounter();
        runCrossPromotion();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.fingersoft.liveops_sdk.LiveopsManager$4] */
    public void recordCrosspromotionImpression(final String str) {
        new Thread() { // from class: com.fingersoft.liveops_sdk.LiveopsManager.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                CommonUtils.log("Starting POST request");
                try {
                    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(LiveopsManager.this.mBaseAddress + "api/incr_promo_impressions");
                    httpPost.addHeader("content-type", "application/json");
                    CommonUtils.log("POST: " + LiveopsManager.this.mBaseAddress + "api/startup_request");
                    JSONObject jSONObject = new JSONObject();
                    JSONObject jSONObject2 = new JSONObject();
                    Log.e("", "package_name " + LiveopsManager.this.getPackageName());
                    jSONObject2.put("jsonver", 1);
                    jSONObject2.put(InMobiNetworkValues.PACKAGE_NAME, LiveopsManager.this.getPackageName());
                    jSONObject2.put("platform", LiveopsManager.this.getPlatformName());
                    jSONObject2.put("locale", Locale.getDefault().getCountry());
                    jSONObject2.put(com.ironsource.sdk.constants.Constants.RequestParameters.NETWORK_MCC, LiveopsManager.this.getNetworkMcc());
                    jSONObject2.put("version", Integer.valueOf(LiveopsManager.this.getCurrentVersionCode()));
                    jSONObject2.put("guid", str);
                    jSONObject2.put(AppsFlyerLib.ATTRIBUTION_ID_COLUMN_NAME, "");
                    jSONObject.put("data", jSONObject2);
                    httpPost.setEntity(new StringEntity(jSONObject.toJSONString()));
                    CommonUtils.log("POST: " + jSONObject.toJSONString());
                    StatusLine statusLine = defaultHttpClient.execute(httpPost).getStatusLine();
                    if (statusLine == null || statusLine.getStatusCode() == 200) {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public boolean recordCrosspromotionClick(String str) {
        CommonUtils.log("Starting POST request");
        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(this.mBaseAddress + "api/incr_promo_clicks");
            httpPost.addHeader("content-type", "application/json");
            CommonUtils.log("POST: " + this.mBaseAddress + "api/startup_request");
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            Log.e("", "package_name " + getPackageName());
            jSONObject2.put("jsonver", 1);
            jSONObject2.put(InMobiNetworkValues.PACKAGE_NAME, getPackageName());
            jSONObject2.put("platform", getPlatformName());
            jSONObject2.put("locale", Locale.getDefault().getCountry());
            jSONObject2.put(com.ironsource.sdk.constants.Constants.RequestParameters.NETWORK_MCC, getNetworkMcc());
            jSONObject2.put("guid", str);
            jSONObject2.put(AppsFlyerLib.ATTRIBUTION_ID_COLUMN_NAME, "");
            jSONObject.put("data", jSONObject2);
            httpPost.setEntity(new StringEntity(jSONObject.toJSONString()));
            CommonUtils.log("POST: " + jSONObject.toJSONString());
            StatusLine statusLine = defaultHttpClient.execute(httpPost).getStatusLine();
            return statusLine != null && statusLine.getStatusCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void saveSettingsFromContainer() {
        try {
            if (this.mContainer != null) {
                List<SettingCollection.Setting> settings = this.mContainer.getSettings().getSettings();
                if (settings.size() <= 0) {
                    return;
                }
                CommonUtils.log("Clearing settings");
                ApplicationSettings.clearSettings(this.mActivity.getApplicationContext());
                for (SettingCollection.Setting setting : settings) {
                    Log.d("LiveOps SDK", "setting setting" + setting.toString());
                    ApplicationSettings.setValue(this.mActivity.getApplicationContext(), setting.getName(), setting.getValue());
                }
            }
        } catch (Exception e) {
            CommonUtils.log("Could not save settings: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String encryptDecrypt(String str) {
        char[] cArr = {'o', 'J', 'D', 'K', 'G', 'p', 'R', 'L', 'G', 'r', 's', 'B', 'Y', 'F', '2', 'a', 'W', 'K', 'v', 'g', 'x', 'R', '4', 'F', 'p', '2', 'v', 'M', 'X', 'v', 'j', 'g', 'l', 'C', 'Y', 't', '4', '4', 'e', 'r', '9', 'E', 'J', 'Q', 'I', 'k', 'Y', 'v', 'P', '9', 'w', 'a', 'A', '4', 'B', 'H', 't', 's', 'K', 'X', 'I', 'o', 'v', 'T', 'L', 'D', 'd', 'B', 'a', 's', 'b', 'T', 'P', 'N', 'q', 'b', 'N', '6', 'x', 'q', 'd', 'R', 'I', 'j', '1', 's', 'Q', 'H', 'P', 'k', 'I', 'F', 'u', 'J', '9', 'Q', '3', 'l', 'q', '2', 'e', 'u', 'C', 'y', 'Y', 'g', 's', 'y', 'Q', 'r', 'n', '5', '2', 'h', '5', 'B', 'f', 'p', 's', '1', 'r', '5', 'J', '8', '3', '8', 'z', 'p', 'u', 's', 'F', 'o', '0', 'B', 'a', '6', 'I', 'B', 'Z', '7', 'a', 'E', 'q', 'r', 'z', 'S', 'A', 'D', 'u', 'V', 'r', 'F', '6', 'G', 'g', 'd', 'O', 'A', 'X', 'c', 'h', 's', 'w', 'h', 'l', 'l', 'l', 'e', 'W', 'W', 'N', 'l', 'x', 'm', 'Y', 'W', 'C', 'S', 'T', 'c', 'd', 'o', '9', 'z', 'e', 'P', 'W', 'R', 'v', 'x', 'f', 'L', 'J', 'G', 'T', 'v', 'V', '0', 'v', 'n', 'E', 'a', 'm', 'u', '6', 'f', 'r', 'C', 'v', 'Z', 'F', 'm', '6', 'U', 'R', 'z', 'D', 'O', '0', '2', 'h', 'H', 'I', 'w', '5', 'm', 'x', 'G', '9', 'V', 'q', '1', 'H', 'Y', 'p', 'h', '4', 'u', 'S', 'T', 'i', 'X', 'A', 'Q', 'a', '8', 'j', 'q', 'H', '5', 'e', '4', 'z', 'C', 'C', 'c'};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append((char) (str.charAt(i) ^ cArr[i % cArr.length]));
        }
        return sb.toString();
    }

    public static String decryptSetting(String str) {
        try {
            byte[] bytes = AESKey.getBytes(UrlUtils.UTF8);
            byte[] bytes2 = AESIv.getBytes(UrlUtils.UTF8);
            byte[] bArrDecodeBase64 = Base64.decodeBase64(str.getBytes());
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(2, secretKeySpec, new IvParameterSpec(bytes2));
            return new String(cipher.doFinal(bArrDecodeBase64), "UTF-8");
        } catch (Exception e) {
            CommonUtils.log("Could not decrypt settings: " + e.getMessage());
            return "";
        }
    }

    public static String encryptSetting(String str) {
        try {
            byte[] bytes = AESKey.getBytes(UrlUtils.UTF8);
            byte[] bytes2 = AESIv.getBytes(UrlUtils.UTF8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(1, secretKeySpec, new IvParameterSpec(bytes2));
            return new String(Base64.encodeBase64(cipher.doFinal(str.getBytes())), "UTF-8");
        } catch (Exception e) {
            CommonUtils.log("Could not encrypt settings: " + e.getMessage());
            return "";
        }
    }

    private void getPriorities() {
        String country = this.mActivity.getResources().getConfiguration().locale.getCountry();
        String packageName = this.mActivity.getPackageName();
        String strDerivePlatformName = MarketVariation.derivePlatformName(mMarketVariation);
        if (this.mGetRequest) {
            new JsonFromServerGetTask(this).execute(packageName, country, String.valueOf(this.mCurrentVersionCode), strDerivePlatformName);
        } else {
            new JsonFromServerPostTask(this).execute(packageName, country, String.valueOf(this.mCurrentVersionCode), strDerivePlatformName, this.mNetworkMcc);
        }
        runCrossPromotion();
    }

    public LiveopsManager withMarketVariation(int i) {
        mMarketVariation = i;
        return this;
    }

    public LiveopsManager withRequestParameters(String str) {
        this.mAdditionalRequestParams.set(str);
        return this;
    }

    public LiveopsManager withRequestParameters(HashMap<String, String> map) {
        this.mAdditionalReqParams.set(map);
        return this;
    }

    public void stop() {
        onPause();
    }

    public boolean isStarted() {
        return this.mIsStarted;
    }

    public void start() {
        if (this.mIsStarted) {
            return;
        }
        this.mIsStarted = true;
    }

    public void onDestroy() {
        stop();
        this.mActivity = null;
    }

    public void askRating() {
        if (mMarketVariation == 8) {
            return;
        }
        int prefValueInt = getPrefValueInt(this.mActivity.getApplicationContext(), "RATE_COUNTER", 0) + 1;
        setPrefValueInt(this.mActivity.getApplicationContext(), "RATE_COUNTER", prefValueInt);
        int prefValueInt2 = getPrefValueInt(this.mActivity.getApplicationContext(), "APP_RATED", 0);
        if (prefValueInt % 8 == 0 && prefValueInt2 == 0) {
            this.mAppRatingDialog.showDialog();
        }
    }

    public boolean hasPromotionBeenShown() {
        CrossPromotionManager crossPromotionManager = this.mCrossPromotionManager;
        if (crossPromotionManager != null) {
            return crossPromotionManager.hasPromotionBeenShown();
        }
        return false;
    }

    public boolean hasActivePromotion() {
        CrossPromotionManager crossPromotionManager = this.mCrossPromotionManager;
        if (crossPromotionManager != null) {
            return crossPromotionManager.hasActivePromotion();
        }
        return false;
    }

    public String getAdvertisingId() {
        if (this.mAdvertisingId.get() != null) {
            return this.mAdvertisingId.get();
        }
        return this.mAdInfo.get() != null ? this.mAdInfo.get().getId() : "";
    }

    public void setAdvertisingId(String str) {
        this.mAdvertisingId.set(str);
    }

    public void setAdInfo(AdvertisingIdClient.Info info) {
        this.mAdInfo.set(info);
    }

    public class AdvertisingWorker implements Runnable {
        LiveopsManager mLiveopsManager;

        public AdvertisingWorker(LiveopsManager liveopsManager) {
            this.mLiveopsManager = null;
            this.mLiveopsManager = liveopsManager;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(LiveopsManager.this.getActivity());
                this.mLiveopsManager.setAdInfo(advertisingIdInfo);
                this.mLiveopsManager.setAdvertisingId(advertisingIdInfo.getId());
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesRepairableException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            } catch (IllegalStateException e4) {
                e4.printStackTrace();
            }
        }
    }

    public HashMap<String, String> getAdditionalReqParams() {
        return this.mAdditionalReqParams.get();
    }

    public void setContainer(IResultContainer iResultContainer) {
        this.mContainer = iResultContainer;
    }

    public SettingsListener getSettingsListener() {
        return this.mSettingsListnener;
    }

    public String getAdditionalRequestParams() {
        return this.mAdditionalRequestParams.get();
    }

    public int getStartupCountWithCurrentVersion() {
        return this.mStartupCountWithCurrentVersion;
    }

    private class JsonFromServerPostTask extends AsyncTask<String, Void, String> {
        private LiveopsManager mLiveopsManager;
        private IResultContainer mContainer = null;
        private long mServerTime = 0;

        public JsonFromServerPostTask(LiveopsManager liveopsManager) {
            this.mLiveopsManager = null;
            this.mLiveopsManager = liveopsManager;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(String... strArr) {
            CommonUtils.log("Starting POST request");
            String str = strArr[0];
            String str2 = strArr[1];
            String str3 = strArr[2];
            String str4 = strArr[3];
            String str5 = strArr[4];
            try {
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(this.mLiveopsManager.getBaseAddress() + "api/v2/startup_request");
                httpPost.addHeader("content-type", "application/json");
                CommonUtils.log("POST: " + this.mLiveopsManager.mBaseAddress + "api/v2/startup_request");
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                Map<String, String> deviceCapabilitiesAsMap = DeviceCapabilities.getDeviceCapabilitiesAsMap();
                Log.e("", "packageNAME " + str);
                jSONObject2.put("jsonver", 3);
                jSONObject2.put(InMobiNetworkValues.PACKAGE_NAME, str);
                jSONObject2.put("locale", str2);
                jSONObject2.put(com.ironsource.sdk.constants.Constants.RequestParameters.NETWORK_MCC, str5);
                jSONObject2.put("version", str3);
                jSONObject2.put("platform", str4);
                jSONObject2.put("startCount", Integer.valueOf(LiveopsManager.mStartupCount));
                jSONObject2.put("currentversionstartcount", Integer.valueOf(this.mLiveopsManager.getStartupCountWithCurrentVersion()));
                jSONObject2.put("news_shown_last", Long.valueOf(GameNews.getInstance(LiveopsManager.this.mActivity).getNewsReadTimestamp()));
                jSONObject2.put("popup_shown_last", Long.valueOf(GameNews.getInstance(LiveopsManager.this.mActivity).getNewsPopupShownTimestamp()));
                new JSONObject();
                if (this.mLiveopsManager.getAdditionalReqParams() != null) {
                    Iterator<Map.Entry<String, String>> it = this.mLiveopsManager.getAdditionalReqParams().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, String> next = it.next();
                        jSONObject2.put(next.getKey(), next.getValue());
                        it.remove();
                    }
                }
                if (deviceCapabilitiesAsMap != null) {
                    Iterator<Map.Entry<String, String>> it2 = deviceCapabilitiesAsMap.entrySet().iterator();
                    while (it2.hasNext()) {
                        Map.Entry<String, String> next2 = it2.next();
                        jSONObject2.put(next2.getKey(), next2.getValue());
                        it2.remove();
                    }
                }
                jSONObject.put("data", jSONObject2);
                jSONObject.put("osversion", String.valueOf(Build.VERSION.SDK_INT));
                httpPost.setEntity(new StringEntity(jSONObject.toJSONString()));
                CommonUtils.log("POST: " + jSONObject.toJSONString());
                HttpResponse httpResponseExecute = defaultHttpClient.execute(httpPost);
                CommonUtils.log("Response code is " + httpResponseExecute.getStatusLine().getStatusCode());
                if (httpResponseExecute.getStatusLine().getStatusCode() != 200) {
                    this.mContainer = new EmptyJsonResultContainer();
                } else {
                    String line = new BufferedReader(new InputStreamReader(httpResponseExecute.getEntity().getContent(), "UTF-8")).readLine();
                    try {
                        try {
                            CommonUtils.log("* trying to parse server response as JSON");
                            new org.json.JSONObject(line);
                            this.mContainer = new JsonResponseHandler(line).getResultContainer();
                            CommonUtils.log("* server response parsed successfully");
                        } catch (Exception unused) {
                            CommonUtils.log("** not JSON, trying to decode base64");
                            byte[] bArrDecode = android.util.Base64.decode(line, 0);
                            CommonUtils.log("** decrypting settings data");
                            this.mContainer = new JsonResponseHandler(LiveopsManager.encryptDecrypt(new String(bArrDecode, "UTF-8"))).getResultContainer();
                            CommonUtils.log("** settings data decrypted successfully");
                        }
                    } catch (Exception e) {
                        CommonUtils.log("Error decoding encrypted settings: " + e.getMessage());
                    }
                    ApplicationSettings.getValueInt(LiveopsManager.this.mActivity, "minimum_required_version", 0);
                    ApplicationSettings.getValueInt(LiveopsManager.this.mActivity, "latest_version", 0);
                    try {
                        Integer.parseInt(str3);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    this.mServerTime = this.mContainer.getServerTime();
                    GameNews.getInstance(LiveopsManager.this.mActivity).setNewsData(this.mContainer.getNewsCollection());
                }
            } catch (Exception e3) {
                CommonUtils.log("error: " + e3.getMessage());
            }
            return "";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            this.mLiveopsManager.setContainer(this.mContainer);
            this.mLiveopsManager.saveSettingsFromContainer();
            if (this.mLiveopsManager.getSettingsListener() != null) {
                this.mLiveopsManager.getSettingsListener().onSettingsRefreshed();
                this.mLiveopsManager.getSettingsListener().onServerTimeChanged(this.mServerTime);
            }
        }
    }

    private class JsonFromServerGetTask extends AsyncTask<String, Void, String> {
        private IResultContainer mContainer = null;
        private LiveopsManager mLiveopsManager;

        public JsonFromServerGetTask(LiveopsManager liveopsManager) {
            this.mLiveopsManager = null;
            this.mLiveopsManager = liveopsManager;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(String... strArr) {
            String bannerPriorityList = "";
            try {
                HttpResponse httpResponseExecute = new DefaultHttpClient().execute(new HttpGet(new URI(ApiHelper.getStartUpRequestUrl(this.mLiveopsManager.getBaseAddress(), strArr[0], strArr[1], strArr[2], strArr[3], LiveopsManager.mStartupCount, this.mLiveopsManager.getStartupCountWithCurrentVersion(), this.mLiveopsManager.getAdditionalRequestParams()))));
                CommonUtils.log("Response code is " + httpResponseExecute.getStatusLine().getStatusCode());
                if (httpResponseExecute.getStatusLine().getStatusCode() != 200) {
                    this.mContainer = new EmptyJsonResultContainer();
                } else {
                    this.mContainer = new JsonResponseHandler(new BufferedReader(new InputStreamReader(httpResponseExecute.getEntity().getContent(), "UTF-8")).readLine()).getResultContainer();
                    bannerPriorityList = this.mContainer.getApplicationInfo().getBannerPriorityList();
                }
            } catch (Exception e) {
                CommonUtils.log("error: " + e.getMessage());
            }
            return bannerPriorityList;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            this.mLiveopsManager.setContainer(this.mContainer);
            this.mLiveopsManager.saveSettingsFromContainer();
        }
    }
}
