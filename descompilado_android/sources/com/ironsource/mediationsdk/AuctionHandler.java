package com.ironsource.mediationsdk;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import com.ironsource.environment.ApplicationContext;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.mediationsdk.utils.IronSourceAES;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import com.ironsource.sdk.constants.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AuctionHandler {
    private static final int SERVER_REQUEST_TIMEOUT = 15000;
    private String mAdUnit;
    private String mBlob;
    private Context mContext;
    private String mSessionId = IronSourceObject.getInstance().getSessionId();
    private String mUrl;

    public AuctionHandler(Context context, String str, String str2, String str3) {
        this.mContext = context;
        this.mAdUnit = str;
        this.mBlob = str2;
        this.mUrl = str3;
    }

    public void executeAuction(Map<String, Object> map, List<String> list, int i, AuctionHandlerCallback auctionHandlerCallback) {
        try {
            boolean z = IronSourceUtils.getSerr() == 1;
            new AuctionHttpRequestTask(auctionHandlerCallback).execute(this.mContext, this.mUrl, generateRequest(this.mContext, map, list, i, z), Boolean.valueOf(z));
        } catch (Exception e) {
            auctionHandlerCallback.callback(false, null, null, 1000, e.getMessage(), 0L);
        }
    }

    public void reportImpression(AuctionResponseItem auctionResponseItem) {
        Iterator<String> it = auctionResponseItem.getWinUrls().iterator();
        while (it.hasNext()) {
            new ImpressionHttpTask().execute(it.next());
        }
    }

    private JSONObject generateRequest(Context context, Map<String, Object> map, List<String> list, int i, boolean z) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(IronSourceConstants.EVENTS_INSTANCE_TYPE, 2);
            jSONObject2.put("biddingAdditionalData", new JSONObject((Map) map.get(str)));
            jSONObject.put(str, jSONObject2);
        }
        for (String str2 : list) {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(IronSourceConstants.EVENTS_INSTANCE_TYPE, 1);
            jSONObject.put(str2, jSONObject3);
        }
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put(Constants.RequestParameters.APPLICATION_USER_ID, IronSourceObject.getInstance().getIronSourceUserId());
        String gender = IronSourceObject.getInstance().getGender();
        if (TextUtils.isEmpty(gender)) {
            gender = "unknown";
        }
        jSONObject4.put("applicationUserGender", gender);
        int age = IronSourceObject.getInstance().getAge();
        if (age == null) {
            age = -1;
        }
        jSONObject4.put("applicationUserAge", age);
        Boolean consent = IronSourceObject.getInstance().getConsent();
        if (consent != null) {
            jSONObject4.put(Constants.RequestParameters.CONSENT, consent.booleanValue() ? 1 : 0);
        }
        jSONObject4.put(Constants.RequestParameters.MOBILE_CARRIER, getMobileCarrier(context));
        jSONObject4.put(Constants.RequestParameters.CONNECTION_TYPE, IronSourceUtils.getConnectionType(context));
        jSONObject4.put("deviceOS", "android");
        jSONObject4.put("deviceWidth", context.getResources().getConfiguration().screenWidthDp);
        jSONObject4.put("deviceHeight", context.getResources().getConfiguration().screenHeightDp);
        jSONObject4.put(Constants.RequestParameters.DEVICE_OS_VERSION, Build.VERSION.SDK_INT + "(" + Build.VERSION.RELEASE + ")");
        jSONObject4.put(Constants.RequestParameters.DEVICE_MODEL, Build.MODEL);
        jSONObject4.put("deviceMake", Build.MANUFACTURER);
        jSONObject4.put(Constants.RequestParameters.PACKAGE_NAME, context.getPackageName());
        jSONObject4.put(Constants.RequestParameters.APPLICATION_VERSION_NAME, ApplicationContext.getPublisherApplicationVersion(context, context.getPackageName()));
        jSONObject4.put("clientTimestamp", new Date().getTime());
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("adUnit", this.mAdUnit);
        jSONObject5.put("auctionData", this.mBlob);
        jSONObject5.put(Constants.RequestParameters.APPLICATION_KEY, IronSourceObject.getInstance().getIronSourceAppKey());
        jSONObject5.put(Constants.RequestParameters.SDK_VERSION, IronSourceUtils.getSDKVersion());
        jSONObject5.put("clientParams", jSONObject4);
        jSONObject5.put(Constants.RequestParameters.SESSION_DEPTH, i);
        jSONObject5.put("sessionId", this.mSessionId);
        jSONObject5.put("doNotEncryptResponse", z ? "false" : ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        jSONObject5.put("instances", jSONObject);
        return jSONObject5;
    }

    private String getMobileCarrier(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? telephonyManager.getNetworkOperatorName() : "";
        } catch (Exception unused) {
            return "";
        }
    }

    static class AuctionHttpRequestTask extends AsyncTask<Object, Void, Boolean> {
        private String mAuctionId;
        private AuctionHandlerCallback mCallback;
        private int mErrorCode;
        private String mErrorMessage;
        private long mRequestStartTime = new Date().getTime();
        private List<AuctionResponseItem> mWaterfall;

        AuctionHttpRequestTask(AuctionHandlerCallback auctionHandlerCallback) {
            this.mCallback = auctionHandlerCallback;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Object... objArr) {
            boolean zBooleanValue;
            try {
                try {
                    Context context = (Context) objArr[0];
                    URL url = new URL((String) objArr[1]);
                    JSONObject jSONObject = (JSONObject) objArr[2];
                    Boolean bool = (Boolean) objArr[3];
                    JSONObject jSONObject2 = (JSONObject) jSONObject.get("clientParams");
                    String orGenerateOnceUniqueIdentifier = DeviceStatus.getOrGenerateOnceUniqueIdentifier(context);
                    String str = IronSourceConstants.TYPE_UUID;
                    try {
                        String[] advertisingIdInfo = DeviceStatus.getAdvertisingIdInfo(context);
                        if (advertisingIdInfo.length == 2) {
                            zBooleanValue = Boolean.valueOf(advertisingIdInfo[1]).booleanValue();
                            try {
                                if (!TextUtils.isEmpty(advertisingIdInfo[0])) {
                                    orGenerateOnceUniqueIdentifier = advertisingIdInfo[0];
                                    str = IronSourceConstants.TYPE_GAID;
                                }
                            } catch (Exception unused) {
                            }
                        } else {
                            zBooleanValue = false;
                        }
                    } catch (Exception unused2) {
                    }
                    jSONObject2.put("advId", orGenerateOnceUniqueIdentifier);
                    jSONObject2.put("advIdType", str);
                    jSONObject2.put(Constants.RequestParameters.isLAT, zBooleanValue ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
                    httpURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");
                    httpURLConnection.setConnectTimeout(15000);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    bufferedWriter.write("{\"request\" : \"" + IronSourceAES.encode(IronSourceUtils.KEY, jSONObject.toString()) + "\"}");
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            sb.append(line);
                        }
                        String string = sb.toString();
                        if (TextUtils.isEmpty(string)) {
                            this.mErrorCode = 1002;
                            this.mErrorMessage = "empty response";
                            return false;
                        }
                        try {
                            JSONObject jSONObject3 = new JSONObject(string);
                            if (bool.booleanValue()) {
                                jSONObject3 = new JSONObject(IronSourceAES.decode(IronSourceUtils.KEY, jSONObject3.getString(ServerResponseWrapper.RESPONSE_FIELD)));
                            }
                            this.mAuctionId = jSONObject3.getString(IronSourceConstants.EVENTS_AUCTION_ID);
                            this.mWaterfall = new ArrayList();
                            JSONArray jSONArray = jSONObject3.getJSONArray("waterfall");
                            for (int i = 0; i < jSONArray.length(); i++) {
                                AuctionResponseItem auctionResponseItem = new AuctionResponseItem(jSONArray.getJSONObject(i));
                                if (!auctionResponseItem.isValid()) {
                                    this.mErrorCode = 1002;
                                    this.mErrorMessage = "waterfall " + i;
                                    return false;
                                }
                                this.mWaterfall.add(auctionResponseItem);
                            }
                            if (this.mWaterfall.size() == 0) {
                                this.mErrorCode = 1004;
                                return false;
                            }
                            httpURLConnection.disconnect();
                            return true;
                        } catch (Exception unused3) {
                            this.mErrorCode = 1002;
                            return false;
                        }
                    }
                    this.mErrorCode = 1001;
                    this.mErrorMessage = responseCode + "";
                    httpURLConnection.disconnect();
                    return false;
                } catch (Exception e) {
                    this.mErrorCode = 1000;
                    this.mErrorMessage = e.getMessage();
                    return false;
                }
            } catch (SocketTimeoutException unused4) {
                this.mErrorCode = 1006;
                this.mErrorMessage = "Connection timed out";
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            long time = new Date().getTime() - this.mRequestStartTime;
            if (bool.booleanValue()) {
                this.mCallback.callback(true, this.mWaterfall, this.mAuctionId, 0, null, time);
            } else {
                this.mCallback.callback(false, null, null, this.mErrorCode, this.mErrorMessage, time);
            }
        }
    }

    static class ImpressionHttpTask extends AsyncTask<String, Void, Boolean> {
        ImpressionHttpTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(String... strArr) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_GET);
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                httpURLConnection.disconnect();
                return Boolean.valueOf(responseCode == 200);
            } catch (Exception unused) {
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
        }
    }
}
