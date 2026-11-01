package com.ironsource.sdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.data.SSABCParameters;
import com.ironsource.sdk.data.SSAEnums;
import com.ironsource.sdk.data.SSAObj;
import com.ironsource.sdk.data.SSASession;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IronSourceSharedPrefHelper {
    private static final String APPLICATION_KEY = "application_key";
    private static final String APPLICATION_KEY_IS = "application_key_is";
    private static final String APPLICATION_KEY_OW = "application_key_ow";
    private static final String APPLICATION_KEY_RV = "application_key_rv";
    private static final String BACK_BUTTON_STATE = "back_button_state";
    private static final String IS_REPORTED = "is_reported";
    private static final String REGISTER_SESSIONS = "register_sessions";
    private static final String SEARCH_KEYS = "search_keys";
    private static final String SESSIONS = "sessions";
    private static final String SSA_RV_PARAMETER_CONNECTION_RETRIES = "ssa_rv_parameter_connection_retries";
    private static final String SSA_SDK_DOWNLOAD_URL = "ssa_sdk_download_url";
    private static final String SSA_SDK_LOAD_URL = "ssa_sdk_load_url";
    private static final String SUPERSONIC_SHARED_PREF = "supersonic_shared_preferen";
    private static final String UNIQUE_ID = "unique_id";
    private static final String UNIQUE_ID_IS = "unique_id_is";
    private static final String UNIQUE_ID_OW = "unique_id_ow";
    private static final String UNIQUE_ID_RV = "unique_id_rv";
    private static final String VERSION = "version";
    private static IronSourceSharedPrefHelper mInstance;
    private SharedPreferences mSharedPreferences;

    private IronSourceSharedPrefHelper(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(SUPERSONIC_SHARED_PREF, 0);
    }

    public static synchronized IronSourceSharedPrefHelper getSupersonicPrefHelper(Context context) {
        if (mInstance == null) {
            mInstance = new IronSourceSharedPrefHelper(context);
        }
        return mInstance;
    }

    public static synchronized IronSourceSharedPrefHelper getSupersonicPrefHelper() {
        return mInstance;
    }

    public String getConnectionRetries() {
        return this.mSharedPreferences.getString(SSA_RV_PARAMETER_CONNECTION_RETRIES, "3");
    }

    public void setSSABCParameters(SSABCParameters sSABCParameters) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putString(SSA_RV_PARAMETER_CONNECTION_RETRIES, sSABCParameters.getConnectionRetries());
        editorEdit.commit();
    }

    public void setBackButtonState(String str) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putString(BACK_BUTTON_STATE, str);
        editorEdit.commit();
    }

    public SSAEnums.BackButtonState getBackButtonState() {
        int i = Integer.parseInt(this.mSharedPreferences.getString(BACK_BUTTON_STATE, "2"));
        if (i == 0) {
            return SSAEnums.BackButtonState.None;
        }
        if (i == 1) {
            return SSAEnums.BackButtonState.Device;
        }
        if (i == 2) {
            return SSAEnums.BackButtonState.Controller;
        }
        return SSAEnums.BackButtonState.Controller;
    }

    public void setSearchKeys(String str) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putString(SEARCH_KEYS, str);
        editorEdit.commit();
    }

    public List<String> getSearchKeys() {
        String string = this.mSharedPreferences.getString(SEARCH_KEYS, null);
        ArrayList arrayList = new ArrayList();
        if (string != null) {
            SSAObj sSAObj = new SSAObj(string);
            if (sSAObj.containsKey(Constants.ParametersKeys.SEARCH_KEYS)) {
                try {
                    arrayList.addAll(sSAObj.toList((JSONArray) sSAObj.get(Constants.ParametersKeys.SEARCH_KEYS)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public JSONArray getSessions() {
        String string = this.mSharedPreferences.getString(SESSIONS, null);
        if (string == null) {
            return new JSONArray();
        }
        try {
            return new JSONArray(string);
        } catch (JSONException unused) {
            return new JSONArray();
        }
    }

    public void deleteSessions() {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putString(SESSIONS, null);
        editorEdit.commit();
    }

    public void addSession(SSASession sSASession) {
        if (getShouldRegisterSessions()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("sessionStartTime", sSASession.getSessionStartTime());
                jSONObject.put("sessionEndTime", sSASession.getSessionEndTime());
                jSONObject.put("sessionType", sSASession.getSessionType());
                jSONObject.put("connectivity", sSASession.getConnectivity());
            } catch (JSONException unused) {
            }
            JSONArray sessions = getSessions();
            if (sessions == null) {
                sessions = new JSONArray();
            }
            sessions.put(jSONObject);
            SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
            editorEdit.putString(SESSIONS, sessions.toString());
            editorEdit.commit();
        }
    }

    private boolean getShouldRegisterSessions() {
        return this.mSharedPreferences.getBoolean(REGISTER_SESSIONS, true);
    }

    public void setShouldRegisterSessions(boolean z) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putBoolean(REGISTER_SESSIONS, z);
        editorEdit.commit();
    }

    public boolean setUserData(String str, String str2) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putString(str, str2);
        return editorEdit.commit();
    }

    public String getUserData(String str) {
        String string = this.mSharedPreferences.getString(str, null);
        return string != null ? string : "{}";
    }

    /* JADX INFO: renamed from: com.ironsource.sdk.utils.IronSourceSharedPrefHelper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType = new int[SSAEnums.ProductType.values().length];

        static {
            try {
                $SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType[SSAEnums.ProductType.RewardedVideo.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType[SSAEnums.ProductType.OfferWall.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType[SSAEnums.ProductType.Interstitial.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public String getApplicationKey(SSAEnums.ProductType productType) {
        int i = AnonymousClass1.$SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType[productType.ordinal()];
        String string = null;
        if (i == 1) {
            string = this.mSharedPreferences.getString(APPLICATION_KEY_RV, null);
        } else if (i == 2) {
            string = this.mSharedPreferences.getString(APPLICATION_KEY_OW, null);
        } else if (i == 3) {
            string = this.mSharedPreferences.getString(APPLICATION_KEY_IS, null);
        }
        return string == null ? this.mSharedPreferences.getString(APPLICATION_KEY, "EMPTY_APPLICATION_KEY") : string;
    }

    public void setApplicationKey(String str) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putString(APPLICATION_KEY, str);
        editorEdit.commit();
    }

    public boolean setUniqueId(String str) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putString(UNIQUE_ID, str);
        return editorEdit.commit();
    }

    public String getCurrentSDKVersion() {
        return this.mSharedPreferences.getString("version", "UN_VERSIONED");
    }

    public void setCurrentSDKVersion(String str) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putString("version", str);
        editorEdit.commit();
    }

    public String getSDKDownloadUrl() {
        return this.mSharedPreferences.getString(SSA_SDK_DOWNLOAD_URL, null);
    }

    public String getCampaignLastUpdate(String str) {
        return this.mSharedPreferences.getString(str, null);
    }

    public void setCampaignLastUpdate(String str, String str2) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putString(str, str2);
        editorEdit.commit();
    }

    public String getUniqueId(String str) {
        String string = null;
        if (str.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
            string = this.mSharedPreferences.getString(UNIQUE_ID_RV, null);
        } else if (str.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
            string = this.mSharedPreferences.getString(UNIQUE_ID_OW, null);
        } else if (str.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
            string = this.mSharedPreferences.getString(UNIQUE_ID_IS, null);
        }
        return string == null ? this.mSharedPreferences.getString(UNIQUE_ID, "EMPTY_UNIQUE_ID") : string;
    }

    public String getUniqueId(SSAEnums.ProductType productType) {
        return getUniqueId(productType.toString());
    }

    public boolean setLatestCompeltionsTime(String str, String str2, String str3) {
        String string = this.mSharedPreferences.getString("ssaUserData", null);
        if (!TextUtils.isEmpty(string)) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                if (!jSONObject.isNull(str2)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(str2);
                    if (!jSONObject2.isNull(str3)) {
                        jSONObject2.getJSONObject(str3).put("timestamp", str);
                        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
                        editorEdit.putString("ssaUserData", jSONObject.toString());
                        return editorEdit.commit();
                    }
                }
            } catch (JSONException e) {
                new IronSourceAsyncHttpRequestTask().execute(Constants.NATIVE_EXCEPTION_BASE_URL + e.getStackTrace()[0].getMethodName());
            }
        }
        return false;
    }

    public void setReportAppStarted(boolean z) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putBoolean(IS_REPORTED, z);
        editorEdit.apply();
    }

    public boolean getReportAppStarted() {
        return this.mSharedPreferences.getBoolean(IS_REPORTED, false);
    }
}
