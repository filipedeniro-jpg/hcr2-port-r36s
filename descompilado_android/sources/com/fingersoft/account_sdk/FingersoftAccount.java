package com.fingersoft.account_sdk;

import android.app.Activity;
import android.content.Context;
import com.facebook.internal.ServerProtocol;
import com.fingersoft.ads_sdk.advertising.providers.AdProviders;
import com.fingersoft.hcr2.Config;
import com.ironsource.sdk.constants.LocationConst;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class FingersoftAccount {
    private static final int PROTOCOL_VERSION = 3;
    private Activity mActivity;
    private String mApiKey;
    private String mApplicationID;
    private Context mContext;
    private String mDeviceGUID;
    private String mServerBaseAddress;

    private int getProtocolVersion() {
        return 3;
    }

    public void login() {
    }

    public void logout() {
    }

    public FingersoftAccount(Activity activity, String str, String str2, String str3) {
        this.mServerBaseAddress = Config.ACCOUNT_SERVER_ADDRESS_STAGING;
        this.mApiKey = "8fd861e9-7bd3-42a6-9ea3-5801c975f464";
        this.mActivity = activity;
        this.mContext = activity;
        this.mApplicationID = str2;
        this.mApiKey = str;
        this.mServerBaseAddress = str3;
    }

    public String getApplicationID() {
        return this.mApplicationID;
    }

    public boolean updateData(AccountManager.AccountID accountID, DataStorageItem[] dataStorageItemArr, String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("version", getProtocolVersion());
            boolean z = false;
            for (int i = 0; i < dataStorageItemArr.length; i++) {
                if (i < 4) {
                    if (dataStorageItemArr[i].getIsUpdated()) {
                        jSONObject2.put("data_" + i, encodeString(dataStorageItemArr[i].getData()));
                        z = true;
                    }
                } else if (dataStorageItemArr[i].getIsUpdated()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("p_data_");
                    sb.append(i - 4);
                    jSONObject2.put(sb.toString(), encodeString(dataStorageItemArr[i].getData()));
                    z = true;
                }
            }
            if (!z) {
                return true;
            }
            fillAuthorizationData(accountID, jSONObject, jSONObject2, str, str2, str3);
            jSONObject.put(getApplicationID(), jSONObject2);
            AccountManager.log("app id " + getApplicationID());
            AccountManager.log("json send" + jSONObject.toString());
            try {
                HttpResponse httpResponseHttpPost = httpPost("api/update", jSONObject);
                if (httpResponseHttpPost == null || httpResponseHttpPost.getStatusLine().getStatusCode() != 200) {
                    return false;
                }
                new BufferedReader(new InputStreamReader(httpResponseHttpPost.getEntity().getContent(), "UTF-8"));
                return true;
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
        }
        AdUtils.log("error: " + e.getMessage());
        return false;
    }

    public static String decodeString(String str) {
        try {
            return URLDecoder.decode(str, "utf8");
        } catch (Exception unused) {
            return null;
        }
    }

    public static String encodeString(String str) {
        try {
            return URLEncoder.encode(str, "utf8");
        } catch (Exception unused) {
            return null;
        }
    }

    private void fillAuthorizationData(AccountManager.AccountID accountID, JSONObject jSONObject, JSONObject jSONObject2, String str, String str2, String str3) {
        fillAuthorizationData(accountID, jSONObject, jSONObject2, str, str2, str3, true);
    }

    private void fillAuthorizationData(AccountManager.AccountID accountID, JSONObject jSONObject, JSONObject jSONObject2, String str, String str2, String str3, boolean z) {
        if (accountID == AccountManager.AccountID.FINGERSOFT) {
            JSONObject jSONObject3 = new JSONObject();
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                jSONObject3.put("keyhash", getKeyHash(jCurrentTimeMillis));
                jSONObject3.put(LocationConst.TIME, "" + jCurrentTimeMillis);
                jSONObject3.put("device_guid", getDeviceGUID());
                jSONObject3.put(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, "token");
                jSONObject3.put("access_token", str);
                jSONObject3.put("global_guid", str3);
                if (str2 != null) {
                    jSONObject3.put("bid", str2);
                }
                jSONObject3.put("advid", "");
                jSONObject3.put("protocol_ver", getProtocolVersion());
                if (z) {
                    jSONObject3.put("hmac", getHMac(jCurrentTimeMillis, jSONObject2.toString()));
                }
                try {
                    jSONObject.put("auth", jSONObject3);
                    AccountManager.log("data: " + jSONObject.toString());
                } catch (Exception e) {
                    e = e;
                    AccountManager.log("RegisterEmail :: JSON ERROR " + e);
                }
            } catch (Exception e2) {
                e = e2;
            }
        } else if (accountID == AccountManager.AccountID.FACEBOOK) {
            JSONObject jSONObject4 = new JSONObject();
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            try {
                jSONObject4.put("keyhash", getKeyHash(jCurrentTimeMillis2));
                jSONObject4.put(LocationConst.TIME, "" + jCurrentTimeMillis2);
                jSONObject4.put("device_guid", getDeviceGUID());
                jSONObject4.put(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, "fbtoken");
                jSONObject4.put("fbtoken", str);
                jSONObject4.put("global_guid", str3);
                if (str2 != null) {
                    jSONObject4.put("bid", str2);
                }
                jSONObject4.put("advid", "");
                jSONObject4.put("protocol_ver", getProtocolVersion());
                if (z) {
                    jSONObject4.put("hmac", getHMac(jCurrentTimeMillis2, jSONObject2.toString()));
                }
                jSONObject.put("auth", jSONObject4);
            } catch (Exception e3) {
                AccountManager.log("RegisterEmail :: JSON ERROR " + e3);
            }
        }
    }

    private String getApiKey() {
        return this.mApiKey;
    }

    public boolean registerEmail(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            jSONObject2.put("protocol_ver", getProtocolVersion());
            jSONObject2.put("keyhash", getKeyHash(jCurrentTimeMillis));
            jSONObject2.put(LocationConst.TIME, "" + jCurrentTimeMillis);
            jSONObject2.put(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, "emailreg");
            jSONObject2.put("device_guid", getDeviceGUID());
            jSONObject2.put("email", str);
            jSONObject.put("auth", jSONObject2);
        } catch (Exception e) {
            AccountManager.log("RegisterEmail :: JSON ERROR " + e);
        }
        HttpResponse httpResponseHttpPost = httpPost("api/register_email", jSONObject);
        return httpResponseHttpPost != null && httpResponseHttpPost.getStatusLine().getStatusCode() == 200;
    }

    public JSONObject registerFacebook(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            jSONObject2.put("protocol_ver", getProtocolVersion());
            jSONObject2.put("keyhash", getKeyHash(jCurrentTimeMillis));
            jSONObject2.put(LocationConst.TIME, "" + jCurrentTimeMillis);
            jSONObject2.put(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, "fbtoken");
            jSONObject2.put("fbtoken", str);
            jSONObject2.put("device_guid", getDeviceGUID());
            jSONObject2.put("advid", "");
            jSONObject.put("auth", jSONObject2);
        } catch (Exception e) {
            AccountManager.log("RegisterEmail :: JSON ERROR " + e);
        }
        HttpResponse httpResponseHttpPost = httpPost("api/register_fb", jSONObject);
        if (httpResponseHttpPost == null || httpResponseHttpPost.getStatusLine().getStatusCode() != 200) {
            return null;
        }
        try {
            return new JSONObject(new BufferedReader(new InputStreamReader(httpResponseHttpPost.getEntity().getContent(), "UTF-8")).readLine());
        } catch (Exception e2) {
            AccountManager.log("validateEmail :: Failed to handle json " + e2);
            return null;
        }
    }

    public JSONObject registerGoogle(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            jSONObject2.put("protocol_ver", getProtocolVersion());
            jSONObject2.put("keyhash", getKeyHash(jCurrentTimeMillis));
            jSONObject2.put(LocationConst.TIME, "" + jCurrentTimeMillis);
            jSONObject2.put(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, "googletokenid");
            jSONObject2.put("id_token", str);
            jSONObject2.put("client_id", str2);
            jSONObject2.put("device_guid", getDeviceGUID());
            jSONObject2.put("advid", "");
            jSONObject.put("auth", jSONObject2);
        } catch (Exception e) {
            AccountManager.log("RegisterGoogle :: JSON ERROR " + e);
        }
        HttpResponse httpResponseHttpPost = httpPost("api/register_google", jSONObject);
        if (httpResponseHttpPost == null || httpResponseHttpPost.getStatusLine().getStatusCode() != 200) {
            return null;
        }
        try {
            return new JSONObject(new BufferedReader(new InputStreamReader(httpResponseHttpPost.getEntity().getContent(), "UTF-8")).readLine());
        } catch (Exception e2) {
            AccountManager.log("validateEmail :: Failed to handle json " + e2);
            return null;
        }
    }

    public void setAPIKey(String str) {
        this.mApiKey = str;
    }

    private String getKeyHash(long j) {
        return Utils.sha256Hash(getApiKey() + j);
    }

    private String getHMac(long j, String str) {
        AccountManager.log("Hashing: " + getApiKey() + j + str);
        return Utils.sha256Hash(getApiKey() + j + str);
    }

    private String getDeviceGUID() {
        if (this.mDeviceGUID == null) {
            this.mDeviceGUID = UniqueID.id(this.mActivity);
        }
        return this.mDeviceGUID;
    }

    public JSONObject validateDevice(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            jSONObject2.put("keyhash", getKeyHash(jCurrentTimeMillis));
            jSONObject2.put(LocationConst.TIME, "" + jCurrentTimeMillis);
            jSONObject2.put("device_guid", getDeviceGUID());
            jSONObject2.put(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, "actcode");
            jSONObject2.put("act_code", str2);
            jSONObject2.put("email", str);
            jSONObject2.put("protocol_ver", getProtocolVersion());
            jSONObject.put("auth", jSONObject2);
        } catch (Exception e) {
            AccountManager.log("validateDevice :: JSON ERROR " + e);
        }
        AccountManager.log("Validate device JSON: " + jSONObject);
        HttpResponse httpResponseHttpPost = httpPost("api/validate_device", jSONObject);
        if (httpResponseHttpPost == null || httpResponseHttpPost.getStatusLine().getStatusCode() != 200) {
            return null;
        }
        try {
            return new JSONObject(new BufferedReader(new InputStreamReader(httpResponseHttpPost.getEntity().getContent(), "UTF-8")).readLine());
        } catch (Exception e2) {
            AccountManager.log("validateEmail :: Failed to handle json " + e2);
            return null;
        }
    }

    private HttpResponse httpPost(String str, JSONObject jSONObject) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(this.mServerBaseAddress + "" + str);
            httpPost.addHeader("content-type", "application/json");
            AccountManager.log("Request: " + jSONObject.toString());
            AccountManager.log("POST: " + this.mServerBaseAddress + "" + str);
            httpPost.setEntity(new StringEntity(jSONObject.toString()));
            HttpResponse httpResponseExecute = defaultHttpClient.execute(httpPost);
            AccountManager.log("Response code is " + httpResponseExecute.getStatusLine().getStatusCode());
            if (httpResponseExecute.getStatusLine().getStatusCode() != 200) {
                AccountManager.log("Query (" + str + ") done in [" + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms]");
                return null;
            }
            AccountManager.log("Query (" + str + ") done in [" + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms]");
            return httpResponseExecute;
        } catch (Exception e) {
            AccountManager.log("Post exception: " + e);
            return null;
        }
    }

    public JSONObject fetchPublicData(int i, String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONObject2.put("bid", str);
            jSONObject2.put("id", str3);
            jSONArray.put(0, jSONObject2);
            jSONObject3.put("data_" + i, jSONArray);
            jSONObject.put(getApplicationID(), jSONObject3);
            fillAuthorizationData(AccountManager.AccountID.FINGERSOFT, jSONObject, jSONObject3, str2, str, str3);
            HttpResponse httpResponseHttpPost = httpPost("api/fetch", jSONObject);
            if (httpResponseHttpPost == null) {
                return null;
            }
            AdUtils.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            if (httpResponseHttpPost.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            return new JSONObject(new BufferedReader(new InputStreamReader(httpResponseHttpPost.getEntity().getContent(), "UTF-8")).readLine());
        } catch (Exception e) {
            AdUtils.log("error: " + e.getMessage());
            return null;
        }
    }

    public JSONObject fetchPublicDataByList(int i, String str, String str2, String str3, String[] strArr, String[] strArr2) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < strArr.length; i2++) {
                JSONObject jSONObject3 = new JSONObject();
                if (strArr2 == null || strArr2[i2].length() == 0) {
                    jSONObject3.put("bid", "");
                } else {
                    jSONObject3.put("bid", strArr2[i2]);
                }
                jSONObject3.put("id", strArr[i2]);
                jSONArray.put(i2, jSONObject3);
            }
            jSONObject2.put("data_" + i, jSONArray);
            jSONObject.put(getApplicationID(), jSONObject2);
            fillAuthorizationData(AccountManager.AccountID.FINGERSOFT, jSONObject, jSONObject2, str2, str, str3);
            HttpResponse httpResponseHttpPost = httpPost("api/fetch", jSONObject);
            if (httpResponseHttpPost == null) {
                return null;
            }
            AdUtils.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            if (httpResponseHttpPost.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            return new JSONObject(new BufferedReader(new InputStreamReader(httpResponseHttpPost.getEntity().getContent(), "UTF-8")).readLine());
        } catch (Exception e) {
            AdUtils.log("error: " + e.getMessage());
            return null;
        }
    }

    public JSONObject fetchPrivateData(int i, String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            new JSONObject();
            jSONArray.put(0, i);
            jSONObject2.put("p_data", jSONArray);
            fillAuthorizationData(AccountManager.AccountID.FINGERSOFT, jSONObject, jSONObject2, str2, str, str3);
            jSONObject.put(getApplicationID(), jSONObject2);
            AccountManager.log("POST JSON: " + jSONObject.toString());
            HttpResponse httpResponseHttpPost = httpPost("api/fetch", jSONObject);
            if (httpResponseHttpPost == null) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("statuscode", 0);
                return jSONObject3;
            }
            AdUtils.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            if (httpResponseHttpPost.getStatusLine().getStatusCode() != 200) {
                int statusCode = httpResponseHttpPost.getStatusLine().getStatusCode();
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("statuscode", statusCode);
                return jSONObject4;
            }
            int statusCode2 = httpResponseHttpPost.getStatusLine().getStatusCode();
            JSONObject jSONObject5 = new JSONObject(new BufferedReader(new InputStreamReader(httpResponseHttpPost.getEntity().getContent(), "UTF-8")).readLine());
            jSONObject5.put("statuscode", statusCode2);
            return jSONObject5;
        } catch (Exception e) {
            AdUtils.log("error: " + e.getMessage());
            return null;
        }
    }

    public JSONObject getInviteLink(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("version", getProtocolVersion());
            jSONObject.put(getApplicationID(), jSONObject2);
            fillAuthorizationData(AccountManager.AccountID.FINGERSOFT, jSONObject, jSONObject2, str2, str, str3);
            HttpResponse httpResponseHttpPost = httpPost("api/get_inv_lnk", jSONObject);
            if (httpResponseHttpPost == null) {
                return null;
            }
            AdUtils.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            if (httpResponseHttpPost.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            return new JSONObject(new BufferedReader(new InputStreamReader(httpResponseHttpPost.getEntity().getContent(), "UTF-8")).readLine());
        } catch (Exception e) {
            AdUtils.log("error: " + e.getMessage());
            return null;
        }
    }

    public JSONObject useInviteLink(String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("version", getProtocolVersion());
            jSONObject2.put("invite_code", str4);
            jSONObject.put(getApplicationID(), jSONObject2);
            fillAuthorizationData(AccountManager.AccountID.FINGERSOFT, jSONObject, jSONObject2, str2, str, str3);
            HttpResponse httpResponseHttpPost = httpPost("api/use_inv_lnk", jSONObject);
            if (httpResponseHttpPost == null) {
                return null;
            }
            AdUtils.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            if (httpResponseHttpPost.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            return new JSONObject(new BufferedReader(new InputStreamReader(httpResponseHttpPost.getEntity().getContent(), "UTF-8")).readLine());
        } catch (Exception e) {
            AdUtils.log("error: " + e.getMessage());
            return null;
        }
    }

    public JSONObject getTimeFriendsUpdated(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("version", getProtocolVersion());
            jSONObject.put(getApplicationID(), jSONObject2);
            fillAuthorizationData(AccountManager.AccountID.FINGERSOFT, jSONObject, jSONObject2, str2, str, str3);
            HttpResponse httpResponseHttpPost = httpPost("api/friends_updated", jSONObject);
            if (httpResponseHttpPost == null) {
                return null;
            }
            AdUtils.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            if (httpResponseHttpPost.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            return new JSONObject(new BufferedReader(new InputStreamReader(httpResponseHttpPost.getEntity().getContent(), "UTF-8")).readLine());
        } catch (Exception e) {
            AdUtils.log("error: " + e.getMessage());
            return null;
        }
    }

    public boolean refreshFacebookFriends(String str, String str2, String str3) {
        AccountManager.log("refreshFacebookFriends: 1");
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("version", getProtocolVersion());
            AccountManager.log("refreshFacebookFriends: " + str);
            fillAuthorizationData(AccountManager.AccountID.FACEBOOK, jSONObject, jSONObject2, str2, str, str3, false);
            AccountManager.log("refreshFacebookFriends: " + jSONObject.toString());
            HttpResponse httpResponseHttpPost = httpPost("api/refresh_fb_friends", jSONObject);
            if (httpResponseHttpPost == null) {
                return false;
            }
            AdUtils.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            return httpResponseHttpPost.getStatusLine().getStatusCode() == 200;
        } catch (Exception e) {
            AdUtils.log("error: " + e.getMessage());
            return true;
        }
    }

    public JSONObject getFacebookFriends(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("version", getProtocolVersion());
            fillAuthorizationData(AccountManager.AccountID.FINGERSOFT, jSONObject, jSONObject2, str2, str, str3, false);
            HttpResponse httpResponseHttpPost = httpPost("api/get_fb_friends", jSONObject);
            if (httpResponseHttpPost == null) {
                return null;
            }
            AdUtils.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            if (httpResponseHttpPost.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            return new JSONObject(new BufferedReader(new InputStreamReader(httpResponseHttpPost.getEntity().getContent(), "UTF-8")).readLine());
        } catch (Exception e) {
            AdUtils.log("error: " + e.getMessage());
            return null;
        }
    }

    public JSONObject getFriends(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("version", getProtocolVersion());
            fillAuthorizationData(AccountManager.AccountID.FINGERSOFT, jSONObject, jSONObject2, str2, str, str3, false);
            HttpResponse httpResponseHttpPost = httpPost("api/get_friends", jSONObject);
            if (httpResponseHttpPost == null) {
                return null;
            }
            AdUtils.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            if (httpResponseHttpPost.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            return new JSONObject(new BufferedReader(new InputStreamReader(httpResponseHttpPost.getEntity().getContent(), "UTF-8")).readLine());
        } catch (Exception e) {
            AdUtils.log("error: " + e.getMessage());
            return null;
        }
    }

    public boolean addFriend(String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("version", getProtocolVersion());
            jSONObject2.put("friend_global_guid", str4);
            jSONObject.put(getApplicationID(), jSONObject2);
            fillAuthorizationData(AccountManager.AccountID.FINGERSOFT, jSONObject, jSONObject2, str2, str, str3);
            HttpResponse httpResponseHttpPost = httpPost("api/add_friend", jSONObject);
            if (httpResponseHttpPost == null) {
                return false;
            }
            AdUtils.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            return httpResponseHttpPost.getStatusLine().getStatusCode() == 200;
        } catch (Exception e) {
            AdUtils.log("error: " + e.getMessage());
            return false;
        }
    }

    public boolean addFriendRequest(String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("version", getProtocolVersion());
            jSONObject2.put("friend_global_guid", str4);
            jSONObject.put(getApplicationID(), jSONObject2);
            fillAuthorizationData(AccountManager.AccountID.FINGERSOFT, jSONObject, jSONObject2, str2, str, str3);
            HttpResponse httpResponseHttpPost = httpPost("api/add_friend_request", jSONObject);
            if (httpResponseHttpPost == null) {
                return false;
            }
            AdUtils.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            return httpResponseHttpPost.getStatusLine().getStatusCode() == 200;
        } catch (Exception e) {
            AdUtils.log("error: " + e.getMessage());
            return false;
        }
    }

    public boolean acceptFriendRequest(String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("version", getProtocolVersion());
            jSONObject2.put("friend_global_guid", str4);
            jSONObject.put(getApplicationID(), jSONObject2);
            fillAuthorizationData(AccountManager.AccountID.FINGERSOFT, jSONObject, jSONObject2, str2, str, str3);
            HttpResponse httpResponseHttpPost = httpPost("api/accept_friend_request", jSONObject);
            if (httpResponseHttpPost == null) {
                return false;
            }
            AdUtils.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            return httpResponseHttpPost.getStatusLine().getStatusCode() == 200;
        } catch (Exception e) {
            AdUtils.log("error: " + e.getMessage());
            return false;
        }
    }

    public boolean declineFriendRequest(String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("version", getProtocolVersion());
            jSONObject2.put("friend_global_guid", str4);
            jSONObject.put(getApplicationID(), jSONObject2);
            fillAuthorizationData(AccountManager.AccountID.FINGERSOFT, jSONObject, jSONObject2, str2, str, str3);
            HttpResponse httpResponseHttpPost = httpPost("api/decline_friend_request", jSONObject);
            if (httpResponseHttpPost == null) {
                return false;
            }
            AdUtils.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            return httpResponseHttpPost.getStatusLine().getStatusCode() == 200;
        } catch (Exception e) {
            AdUtils.log("error: " + e.getMessage());
            return false;
        }
    }

    public JSONObject getFriendRequests(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("version", getProtocolVersion());
            jSONObject.put(getApplicationID(), jSONObject2);
            fillAuthorizationData(AccountManager.AccountID.FINGERSOFT, jSONObject, jSONObject2, str2, str, str3);
            HttpResponse httpResponseHttpPost = httpPost("api/get_friend_requests", jSONObject);
            if (httpResponseHttpPost == null) {
                return null;
            }
            AdUtils.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            if (httpResponseHttpPost.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            return new JSONObject(new BufferedReader(new InputStreamReader(httpResponseHttpPost.getEntity().getContent(), "UTF-8")).readLine());
        } catch (Exception e) {
            AdUtils.log("error: " + e.getMessage());
            return null;
        }
    }

    public JSONObject connectAccounts(AccountManager.AccountID accountID, AccountManager.AccountID accountID2, String str, String str2, String str3, String str4, String str5) {
        String str6;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        AccountManager.log("ACCESS TOKEN " + str5);
        if (accountID == AccountManager.AccountID.FINGERSOFT) {
            try {
                jSONObject2.put("platform", "email");
                jSONObject2.put("id", str3);
            } catch (Exception e) {
                AccountManager.log("connectAccounts : " + e);
                return null;
            }
        } else if (accountID == AccountManager.AccountID.FACEBOOK) {
            try {
                jSONObject2.put("platform", AdProviders.FACEBOOK);
                jSONObject2.put("id", str2);
            } catch (Exception e2) {
                AccountManager.log("connectAccounts : " + e2);
                return null;
            }
        } else if (accountID == AccountManager.AccountID.GOOGLE) {
            try {
                jSONObject2.put("platform", "google");
                jSONObject2.put("id", str2);
            } catch (Exception e3) {
                AccountManager.log("connectAccounts : " + e3);
                return null;
            }
        }
        JSONObject jSONObject3 = new JSONObject();
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            jSONObject3.put("protocol_ver", getProtocolVersion());
            jSONObject3.put("keyhash", getKeyHash(jCurrentTimeMillis));
            jSONObject3.put(LocationConst.TIME, "" + jCurrentTimeMillis);
            jSONObject3.put("device_guid", getDeviceGUID());
            jSONObject3.put("global_guid", str);
            if (accountID2 == AccountManager.AccountID.FINGERSOFT) {
                jSONObject3.put(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, "token");
                jSONObject3.put("email", str3);
                jSONObject3.put("access_token", str5);
                str6 = "api/connect_email";
            } else if (accountID2 == AccountManager.AccountID.FACEBOOK) {
                jSONObject3.put(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, "fbtoken");
                jSONObject3.put("fbtoken", str5);
                str6 = "api/connect_fb";
            } else {
                if (accountID2 != AccountManager.AccountID.GOOGLE) {
                    return null;
                }
                jSONObject3.put(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, "googletokenid");
                jSONObject3.put("client_id", str4);
                jSONObject3.put("id_token", str5);
                str6 = "api/connect_google";
            }
            jSONObject.put("auth", jSONObject3);
            jSONObject.put("source", jSONObject2);
            AccountManager.log("Connect accounts " + jSONObject);
            HttpResponse httpResponseHttpPost = httpPost(str6, jSONObject);
            if (httpResponseHttpPost == null) {
                return null;
            }
            AccountManager.log("Response code is " + httpResponseHttpPost.getStatusLine().getStatusCode());
            if (httpResponseHttpPost.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            return new JSONObject(new BufferedReader(new InputStreamReader(httpResponseHttpPost.getEntity().getContent(), "UTF-8")).readLine());
        } catch (Exception e4) {
            AccountManager.log("connectAccounts : " + e4);
            return null;
        }
    }
}
