package com.fingersoft.account_sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.crashlytics.android.beta.Beta;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AccountManager {
    public static final int DATA_STORAGE_ITEMS = 4;
    public static final int ERROR_CODE_DEVICE_ACTIVATION_FAILED = 2;
    public static final int ERROR_CODE_EMAIL_REGISTER_FAILED = 1;
    public static final int ERROR_CODE_UNKNOWN = 3;
    private static final int MAXIMUM_DATA_STRING_SIZE = 64000;
    private static final int MAXIMUM_FETCH_SIZE = 16;
    private static final int MSG_TYPE_ACCEPT_FRIEND_REQUEST = 111;
    private static final int MSG_TYPE_ADD_FINGERSOFT_FRIEND = 105;
    private static final int MSG_TYPE_ADD_FRIEND_REQUEST = 110;
    private static final int MSG_TYPE_CREATE_FACEBOOK_ACCOUNT = 108;
    private static final int MSG_TYPE_DECLINE_FRIEND_REQUEST = 112;
    public static final int MSG_TYPE_FACEBOOK_ACCESS_TOKEN_CHANGED = 11;
    private static final int MSG_TYPE_GET_FRIEND_DATA = 106;
    private static final int MSG_TYPE_GET_FRIEND_REQUESTS = 113;
    private static final int MSG_TYPE_GET_PRIVATE_DATA = 104;
    private static final int MSG_TYPE_GET_PUBLIC_DATA = 103;
    private static final int MSG_TYPE_INITIALIZE_SDK = 1;
    private static final int MSG_TYPE_LOGIN_FACEBOOK = 2;
    public static final int MSG_TYPE_LOGIN_FACEBOOK_FAIL = 6;
    public static final int MSG_TYPE_LOGIN_FACEBOOK_SUCCESS = 4;
    private static final int MSG_TYPE_LOGIN_FINGERSOFT = 3;
    private static final int MSG_TYPE_LOGIN_FINGERSOFT_FAIL = 7;
    private static final int MSG_TYPE_LOGIN_FINGERSOFT_SUCCESS = 5;
    private static final int MSG_TYPE_LOGIN_GOOGLE = 12;
    public static final int MSG_TYPE_LOGIN_GOOGLE_FAILED = 14;
    public static final int MSG_TYPE_LOGIN_GOOGLE_SUCCESS = 13;
    private static final int MSG_TYPE_LOG_OFF = 10;
    public static final int MSG_TYPE_LOG_OFF_FACEBOOK = 15;
    private static final int MSG_TYPE_LOG_OFF_FINGERSOFT = 16;
    private static final int MSG_TYPE_LOG_OFF_GOOGLE = 17;
    private static final int MSG_TYPE_NETWORK_OFF = 9;
    private static final int MSG_TYPE_NETWORK_ON = 8;
    public static final int MSG_TYPE_REFRESH_FACEBOOK_FRIENDS = 109;
    private static final int MSG_TYPE_REFRESH_FACEBOOK_FRIENDS_REQUEST = 114;
    private static final int MSG_TYPE_REGISTER_EMAIL = 101;
    private static final int MSG_TYPE_REMOVE_FINGERSOFT_FRIEND = 107;
    public static final int MSG_TYPE_SET_FACEBOOK_FRIENDS = 115;
    private static final int MSG_TYPE_UPDATE_DATA = 100;
    private static final int MSG_TYPE_VALIDATE_DEVICE = 102;
    public static final int NOTIFICATION_DEVICE_ACTIVATION_SUCCESS = 101;
    public static final int NOTIFICATION_EMAIL_REGISTER_SUCCESS = 100;
    public static final int NOTIFICATION_LOGGED_IN = 103;
    private static final String TAG = "AccountManager";
    private static boolean mLoggingEnabled = true;
    private String mAccountID;
    private AccountListener mAccountListener;
    private AccountManagerThread mAccountManagerThread;
    private Activity mActivity;
    private String mApiKey;
    private FacebookAccount mFacebookAccount;
    private FingersoftAccount mFingersoftAccount;
    private GoogleAccount mGoogleAccount;
    private String mGoogleClientID;
    private Handler mHandler;
    private LoginData mLoginData;
    private String mServerAddress;
    private AccountManagerState mAccountManagerState = AccountManagerState.NOT_INITIALIZED;
    private DataStorageItem[] mDataStorage = new DataStorageItem[8];
    private AccountListener mInternalAccountListener = new AccountListener() { // from class: com.fingersoft.account_sdk.AccountManager.1
        @Override // com.fingersoft.account_sdk.AccountListener
        public void onDataRead(DataSlotType dataSlotType, int i, String str, String str2, int i2) {
        }

        @Override // com.fingersoft.account_sdk.AccountListener
        public void onDataWrite(boolean z) {
        }

        @Override // com.fingersoft.account_sdk.AccountListener
        public void onEvent(AccountEvent accountEvent) {
        }

        @Override // com.fingersoft.account_sdk.AccountListener
        public void onFriendDataRead(DataSlotType dataSlotType, int i, Friend[] friendArr) {
        }

        @Override // com.fingersoft.account_sdk.AccountListener
        public void onReadFriendRequests(String[] strArr) {
        }

        @Override // com.fingersoft.account_sdk.AccountListener
        public void onLoginSuccess(AccountID accountID, int i) {
            AccountManager.log(accountID + " Login success");
        }

        @Override // com.fingersoft.account_sdk.AccountListener
        public void onLoginCancelled(AccountID accountID) {
            AccountManager.log(accountID + " Login cancelled");
        }

        @Override // com.fingersoft.account_sdk.AccountListener
        public void onLoginError(AccountID accountID, int i) {
            AccountManager.log(accountID + " Login Error");
        }

        @Override // com.fingersoft.account_sdk.AccountListener
        public void onLogout(AccountID accountID) {
            AccountManager.log(accountID + " Logout");
        }
    };

    public enum AccountEvent {
        SEND_FRIEND_REQUEST_SUCCESS,
        SEND_FRIEND_REQUEST_FAIL,
        ACCEPT_FRIEND_REQUEST_SUCCESS,
        ACCEPT_FRIEND_REQUEST_FAIL,
        DECLINE_FRIEND_REQUEST_SUCCESS,
        DECLINE_FRIEND_REQUEST_FAIL,
        REMOVE_FINGERSOFT_FRIEND_SUCCESS,
        REMOVE_FINGERSOFT_FRIEND_FAIL,
        ADD_FINGERSOFT_FRIEND_SUCCESS,
        ADD_FINGERSOFT_FRIEND_FAIL,
        EMAIL_REGISTER_SUCCESS,
        EMAIL_REGISTER_FAIL,
        DEVICE_ACTIVATION_SUCCESS,
        DEVICE_ACTIVATION_FAIL,
        FACEBOOK_FRIENDS_REFRESH_SUCCESS,
        FACEBOOK_FRIENDS_REFRESH_FAIL,
        READ_PUBLIC_DATA_FAILED,
        READ_PRIVATE_DATA_FAILED
    }

    public enum AccountID {
        FACEBOOK,
        FINGERSOFT,
        GOOGLE
    }

    private enum AccountManagerState {
        NOT_INITIALIZED,
        LOGGED_OFF,
        LOGGING_IN,
        LOGIN_FAILED_FACEBOOK,
        LOGIN_FAILED_FINGERSOFT,
        LOGIN_FAILED_GOOGLE,
        LOGGED_IN
    }

    public enum DataSlotType {
        DATA_SLOT_TYPE_PUBLIC,
        DATA_SLOT_TYPE_PRIVATE
    }

    public AccountManager(Activity activity, String str, String str2, String str3, String str4, AccountListener accountListener) {
        int i = 0;
        while (true) {
            DataStorageItem[] dataStorageItemArr = this.mDataStorage;
            if (i < dataStorageItemArr.length) {
                dataStorageItemArr[i] = new DataStorageItem();
                i++;
            } else {
                this.mActivity = activity;
                this.mAccountListener = accountListener;
                this.mAccountID = str2;
                this.mApiKey = str;
                this.mGoogleClientID = str3;
                this.mServerAddress = str4;
                FriendManager.getInstance().initialize(this.mActivity);
                this.mAccountManagerThread = new AccountManagerThread(this);
                this.mAccountManagerThread.start();
                return;
            }
        }
    }

    private AccountManagerState getAccountManagerState() {
        return this.mAccountManagerState;
    }

    public void loginToFacebook() {
        this.mHandler.sendEmptyMessage(2);
    }

    public void loginToGoogle() {
        this.mHandler.sendEmptyMessage(12);
    }

    public void updatePublicData(int i, String str) {
        if (str == null) {
            log("updatePublicData :: data is null");
            return;
        }
        if (str.getBytes().length > 64000) {
            log("updatePublicData :: maximum data length exceeded");
            return;
        }
        if (i < 0 || i >= 3) {
            log("updatePublicData :: incorrect slot ID " + i);
            return;
        }
        this.mDataStorage[i].setData(str);
    }

    public void updatePrivateData(int i, String str) {
        if (str == null) {
            log("updatePrivateData :: data is null");
            return;
        }
        if (str.getBytes().length > 64000) {
            log("updatePrivateData :: maximum data length exceeded");
            return;
        }
        if (i < 0 || i >= 3) {
            log("updatePrivateData :: incorrect slot ID " + i);
            return;
        }
        this.mDataStorage[i + 4].setData(str);
    }

    public void deletePublicData(int i) {
        if (i < 0 || i >= 3) {
            log("incorrect slot ID " + i);
            return;
        }
        this.mDataStorage[i].setData("");
    }

    public void deletePrivateData(int i) {
        if (i < 0 || i >= 3) {
            log("incorrect slot ID " + i);
            return;
        }
        this.mDataStorage[i + 4].setData("");
    }

    public void commitData() {
        Message message = new Message();
        message.what = 100;
        message.obj = this.mDataStorage.clone();
        this.mHandler.sendMessage(message);
    }

    public void registerEmail(String str) {
        Message message = new Message();
        message.what = 101;
        message.obj = str;
        this.mHandler.sendMessage(message);
    }

    public void validateDevice(String str) {
        Message message = new Message();
        message.what = 102;
        message.obj = str;
        this.mHandler.sendMessage(message);
    }

    public void fetchPublicData(int i) {
        Message message = new Message();
        message.what = 103;
        message.obj = new Integer(i);
        this.mHandler.sendMessage(message);
    }

    public void fetchPrivateData(int i) {
        Message message = new Message();
        message.what = 104;
        message.obj = new Integer(i);
        this.mHandler.sendMessage(message);
    }

    public void logoutFingersoft() {
        Message message = new Message();
        message.what = 16;
        this.mHandler.sendMessage(message);
    }

    public void logoutFacebook() {
        Message message = new Message();
        message.what = 15;
        this.mHandler.sendMessage(message);
    }

    public void logoutGoogle() {
        Message message = new Message();
        message.what = 17;
        this.mHandler.sendMessage(message);
    }

    public void addFingersoftFriend(String str) {
        Message message = new Message();
        message.what = 105;
        message.obj = str;
        this.mHandler.sendMessage(message);
    }

    public void removeFingersoftFriend(String str) {
        Message message = new Message();
        message.what = 107;
        message.obj = str;
        this.mHandler.sendMessage(message);
    }

    public void getFriendData(int i) {
        Message message = new Message();
        message.what = 106;
        message.obj = new Integer(i);
        this.mHandler.sendMessage(message);
    }

    public void sendFriendRequest(String str) {
        Message message = new Message();
        message.what = 110;
        message.obj = str;
        this.mHandler.sendMessage(message);
    }

    public void acceptFriendRequest(String str) {
        Message message = new Message();
        message.what = 111;
        message.obj = str;
        this.mHandler.sendMessage(message);
    }

    public void declineFriendRequest(String str) {
        Message message = new Message();
        message.what = 112;
        message.obj = str;
        this.mHandler.sendMessage(message);
    }

    public void getFriendRequests() {
        Message message = new Message();
        message.what = 113;
        this.mHandler.sendMessage(message);
    }

    public void refreshFacebookFriends() {
        log("Sending FACEBOOK_FRIENDS_REQUEST");
        this.mHandler.sendEmptyMessage(114);
    }

    public boolean isFingersoftConnected() {
        LoginData loginData = this.mLoginData;
        if (loginData != null) {
            return loginData.getLoggedInFingersoft();
        }
        return false;
    }

    public boolean isFacebookConnected() {
        LoginData loginData = this.mLoginData;
        if (loginData != null) {
            return loginData.getLoggedInFacebook();
        }
        return false;
    }

    public boolean isGoogleConnected() {
        LoginData loginData = this.mLoginData;
        if (loginData != null) {
            return loginData.getLoggedInGoogle();
        }
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        FacebookAccount facebookAccount = this.mFacebookAccount;
        if (facebookAccount != null) {
            facebookAccount.onActivityResult(i, i2, intent);
        }
        GoogleAccount googleAccount = this.mGoogleAccount;
        if (googleAccount != null) {
            googleAccount.onActivityResult(i, i2, intent);
        }
    }

    public static void log(String str) {
        if (mLoggingEnabled) {
            Log.d(TAG, str);
        }
    }

    public String getFacebookId() {
        return this.mLoginData.getFacebookID();
    }

    public String getGoogleId() {
        return this.mLoginData.getGoogleIDToken();
    }

    public static void enableLogging() {
        mLoggingEnabled = true;
    }

    public static void disableLogging() {
        mLoggingEnabled = false;
    }

    public class AccountManagerThread extends Thread {
        private AccountManager mAccountManager;

        public AccountManagerThread(AccountManager accountManager) {
            this.mAccountManager = accountManager;
            getAccountManagerState();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            AccountManager accountManager = AccountManager.this;
            accountManager.mHandler = accountManager.new AccountMessageHandler(this);
            AccountManager.this.mHandler.sendEmptyMessage(1);
            Looper.loop();
        }

        public AccountManagerState getAccountManagerState() {
            return AccountManager.this.mAccountManagerState;
        }

        public void setAccountManagerState(AccountManagerState accountManagerState) {
            AccountManager.log("State change: " + AccountManager.this.mAccountManagerState + " -> " + accountManagerState);
            AccountManager.this.mAccountManagerState = accountManagerState;
        }

        public void doFacebookLogin() {
            if (AccountManager.this.mFacebookAccount != null) {
                AccountManager.this.mFacebookAccount.logIn();
            }
        }

        public void doGoogleLogin() {
            if (AccountManager.this.mGoogleAccount != null) {
                AccountManager.this.mGoogleAccount.logIn();
            }
        }
    }

    class AccountMessageHandler extends Handler {
        private AccountManagerThread mAccountManagerThread;

        public AccountMessageHandler(AccountManagerThread accountManagerThread) {
            this.mAccountManagerThread = accountManagerThread;
        }

        String parseReceivedPrivateData(JSONObject jSONObject, int i) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(AccountManager.this.mFingersoftAccount.getApplicationID());
                AccountManager.log("Data received " + jSONObject2.toString());
                if (jSONObject2.has("p_data_" + i)) {
                    String strDecodeString = FingersoftAccount.decodeString(jSONObject2.getString("p_data_" + i));
                    AccountManager.log("private data read " + strDecodeString);
                    return strDecodeString;
                }
            } catch (Exception unused) {
            }
            return null;
        }

        int parseReceivedStatusCode(JSONObject jSONObject) {
            try {
                AccountManager.log("Data received for status code" + jSONObject.toString());
                if (jSONObject.has("statuscode")) {
                    AccountManager.log("Statuscode read " + jSONObject.getInt("statuscode"));
                    return jSONObject.getInt("statuscode");
                }
            } catch (Exception unused) {
            }
            return 0;
        }

        String parseReceivedPublicData(JSONObject jSONObject, int i) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(AccountManager.this.mFingersoftAccount.getApplicationID());
                String strDecodeString = FingersoftAccount.decodeString(jSONObject2.getString("data_" + i));
                AccountManager.log("Data received " + jSONObject2.toString());
                AccountManager.log("private data read " + strDecodeString);
                return strDecodeString;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (this.mAccountManagerThread.getAccountManagerState()) {
                case NOT_INITIALIZED:
                    if (message.what == 1) {
                        this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGED_OFF);
                        AccountManager accountManager = AccountManager.this;
                        accountManager.mFacebookAccount = new FacebookAccount(accountManager.mActivity, AccountManager.this.mAccountListener, AccountManager.this.mHandler);
                        AccountManager accountManager2 = AccountManager.this;
                        accountManager2.mFingersoftAccount = new FingersoftAccount(accountManager2.mActivity, AccountManager.this.mApiKey, AccountManager.this.mAccountID, AccountManager.this.mServerAddress);
                        AccountManager accountManager3 = AccountManager.this;
                        accountManager3.mGoogleAccount = new GoogleAccount(accountManager3.mActivity, AccountManager.this.mHandler, AccountManager.this.mGoogleClientID);
                        AccountManager accountManager4 = AccountManager.this;
                        accountManager4.mLoginData = new LoginData(accountManager4.mActivity);
                        Log.d(Beta.TAG, "Initialize testing");
                        if (AccountManager.this.mLoginData.getLoggedInFingersoft()) {
                            if (AccountManager.this.mLoginData.getLoggedInFacebook()) {
                                AccountManager.this.mFacebookAccount.logIn();
                            }
                            if (AccountManager.this.mLoginData.getLoggedInGoogle()) {
                                AccountManager.this.mGoogleAccount.logIn();
                            }
                            this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGED_IN);
                            if (AccountManager.this.mAccountListener != null) {
                                AccountManager.this.mAccountListener.onLoginSuccess(AccountID.FINGERSOFT, 103);
                            }
                        } else if (!AccountManager.this.mLoginData.getLoggedInFacebook()) {
                            if (AccountManager.this.mLoginData.getLoggedInGoogle()) {
                                this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGING_IN);
                                AccountManager.this.mGoogleAccount.logIn();
                            } else {
                                this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGED_OFF);
                                AccountManager.log("stay logged off");
                            }
                        } else {
                            this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGING_IN);
                            AccountManager.this.mFacebookAccount.logIn();
                            if (AccountManager.this.mLoginData.getLoggedInGoogle()) {
                                AccountManager.this.mGoogleAccount.logIn();
                            }
                        }
                        break;
                    }
                    break;
                case LOGGED_OFF:
                    int i = message.what;
                    if (i == 2) {
                        this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGING_IN);
                        this.mAccountManagerThread.doFacebookLogin();
                        break;
                    } else if (i == 12) {
                        this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGING_IN);
                        this.mAccountManagerThread.doGoogleLogin();
                        break;
                    } else if (i == 15) {
                        AccountManagerState accountManagerStateLogOut = logOut(AccountID.FACEBOOK);
                        if (accountManagerStateLogOut != null) {
                            this.mAccountManagerThread.setAccountManagerState(accountManagerStateLogOut);
                            if (AccountManager.this.mAccountListener != null) {
                                AccountManager.this.mAccountListener.onLogout(AccountID.FACEBOOK);
                            }
                        }
                        break;
                    } else if (i == 17) {
                        AccountManagerState accountManagerStateLogOut2 = logOut(AccountID.GOOGLE);
                        if (accountManagerStateLogOut2 != null) {
                            this.mAccountManagerThread.setAccountManagerState(accountManagerStateLogOut2);
                            if (AccountManager.this.mAccountListener != null) {
                                AccountManager.this.mAccountListener.onLogout(AccountID.GOOGLE);
                            }
                        }
                        break;
                    } else if (i == 101) {
                        boolean zRegisterEmail = AccountManager.this.mFingersoftAccount.registerEmail((String) message.obj);
                        if (zRegisterEmail) {
                            if (AccountManager.this.mAccountListener != null) {
                                AccountManager.this.mAccountListener.onEvent(AccountEvent.EMAIL_REGISTER_SUCCESS);
                                AccountManager.this.mAccountListener.onLoginSuccess(AccountID.FINGERSOFT, 100);
                            }
                        } else if (AccountManager.this.mAccountListener != null) {
                            AccountManager.this.mAccountListener.onLoginError(AccountID.FINGERSOFT, 1);
                            AccountManager.this.mAccountListener.onEvent(AccountEvent.EMAIL_REGISTER_FAIL);
                            AccountManager.this.mLoginData.setLoggedInFingersoft(false);
                        }
                        AccountManager.log("Email register result: " + zRegisterEmail);
                        break;
                    } else if (i == 102) {
                        String str = (String) message.obj;
                        String email = AccountManager.this.mLoginData.getEmail();
                        if (email.contains("@")) {
                            JSONObject jSONObjectValidateDevice = AccountManager.this.mFingersoftAccount.validateDevice(email, str);
                            if (jSONObjectValidateDevice == null) {
                                AccountManager.this.mLoginData.setLoggedInFingersoft(false);
                                if (AccountManager.this.mAccountListener != null) {
                                    AccountManager.this.mAccountListener.onEvent(AccountEvent.DEVICE_ACTIVATION_FAIL);
                                    AccountManager.this.mAccountListener.onLoginError(AccountID.FINGERSOFT, 2);
                                }
                            } else {
                                AccountManager.log("received " + jSONObjectValidateDevice.toString());
                                AccountManager.this.saveLoginData(jSONObjectValidateDevice);
                                if (AccountManager.this.mAccountListener != null) {
                                    AccountManager.this.mAccountListener.onLoginSuccess(AccountID.FINGERSOFT, 101);
                                    AccountManager.this.mAccountListener.onEvent(AccountEvent.DEVICE_ACTIVATION_SUCCESS);
                                    AccountManager.this.mAccountListener.onLoginSuccess(AccountID.FINGERSOFT, 103);
                                }
                                this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGED_IN);
                                AccountManager.this.mLoginData.setLoggedInFingersoft(true);
                            }
                        } else {
                            AccountManager.log("Invalid email " + email);
                        }
                        break;
                    }
                    break;
                case LOGGING_IN:
                    int i2 = message.what;
                    if (i2 == 4) {
                        if (registerFacebook((LoginObject) message.obj)) {
                            this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGED_IN);
                            if (AccountManager.this.mAccountListener != null) {
                                AccountManager.this.mAccountListener.onLoginSuccess(AccountID.FACEBOOK, 103);
                            }
                        } else {
                            this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGED_OFF);
                            if (AccountManager.this.mAccountListener != null) {
                                AccountManager.this.mAccountListener.onLoginError(AccountID.FACEBOOK, 3);
                            }
                        }
                        break;
                    } else if (i2 == 5) {
                        AccountManager.this.mLoginData.setLoggedInFingersoft(true);
                        this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGED_IN);
                        break;
                    } else if (i2 == 6) {
                        this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGED_OFF);
                        if (AccountManager.this.mLoginData.getLoggedInFacebook()) {
                            AccountManager.this.mLoginData.setLoggedInFacebook(false);
                        }
                        if (AccountManager.this.mAccountListener != null) {
                            AccountManager.this.mAccountListener.onLoginError(AccountID.FACEBOOK, 3);
                        }
                        break;
                    } else if (i2 == 7) {
                        this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGED_OFF);
                        AccountManager.this.mLoginData.setLoggedInFingersoft(false);
                        break;
                    } else if (i2 == 13) {
                        if (registerGoogle((LoginObject) message.obj)) {
                            this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGED_IN);
                            if (AccountManager.this.mAccountListener != null) {
                                AccountManager.this.mAccountListener.onLoginSuccess(AccountID.GOOGLE, 103);
                            }
                        } else {
                            this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGED_OFF);
                            if (AccountManager.this.mAccountListener != null) {
                                AccountManager.this.mAccountListener.onLoginError(AccountID.GOOGLE, 3);
                            }
                        }
                        break;
                    } else if (i2 == 14) {
                        this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGED_OFF);
                        if (AccountManager.this.mAccountListener != null) {
                            AccountManager.this.mAccountListener.onLoginError(AccountID.GOOGLE, 3);
                        }
                        break;
                    }
                    break;
                case LOGIN_FAILED_FACEBOOK:
                    if (message.what == 8) {
                        this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGING_IN);
                        break;
                    }
                    break;
                case LOGIN_FAILED_FINGERSOFT:
                    if (message.what == 8) {
                        this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGING_IN);
                        break;
                    }
                    break;
                case LOGGED_IN:
                    int i3 = message.what;
                    if (i3 != 2) {
                        if (i3 != 4) {
                            if (i3 == 6) {
                                if (AccountManager.this.mAccountListener != null) {
                                    AccountManager.this.mAccountListener.onLoginError(AccountID.FACEBOOK, 3);
                                }
                                break;
                            } else {
                                switch (i3) {
                                    case 11:
                                        String str2 = (String) message.obj;
                                        if (str2 == null || str2.length() <= 0) {
                                            AccountManager.this.mLoginData.setFacebookToken("");
                                        } else {
                                            AccountManager.this.mLoginData.setFacebookToken(str2);
                                        }
                                        break;
                                    case 12:
                                        if (!AccountManager.this.mLoginData.getLoggedInGoogle()) {
                                            AccountManager.this.mGoogleAccount.logIn();
                                        }
                                        break;
                                    case 13:
                                        if (AccountManager.this.mLoginData.getLoggedInGoogle() ? true : connectAccounts((LoginObject) message.obj, AccountManager.this.mLoginData.getPrimaryAccount(), AccountID.GOOGLE)) {
                                            if (AccountManager.this.mAccountListener != null) {
                                                AccountManager.this.mAccountListener.onLoginSuccess(AccountID.GOOGLE, 103);
                                            }
                                        } else if (AccountManager.this.mAccountListener != null) {
                                            AccountManager.this.mAccountListener.onLoginError(AccountID.GOOGLE, 3);
                                        }
                                        break;
                                    case 14:
                                        if (AccountManager.this.mAccountListener != null) {
                                            AccountManager.this.mAccountListener.onLoginError(AccountID.GOOGLE, 3);
                                        }
                                        break;
                                    case 15:
                                        AccountManagerState accountManagerStateLogOut3 = logOut(AccountID.FACEBOOK);
                                        if (accountManagerStateLogOut3 != null) {
                                            this.mAccountManagerThread.setAccountManagerState(accountManagerStateLogOut3);
                                            if (AccountManager.this.mAccountListener != null) {
                                                AccountManager.this.mAccountListener.onLogout(AccountID.FACEBOOK);
                                            }
                                        }
                                        break;
                                    case 16:
                                        AccountManagerState accountManagerStateLogOut4 = logOut(AccountID.FINGERSOFT);
                                        if (accountManagerStateLogOut4 != null) {
                                            this.mAccountManagerThread.setAccountManagerState(accountManagerStateLogOut4);
                                            if (AccountManager.this.mAccountListener != null) {
                                                AccountManager.this.mAccountListener.onLogout(AccountID.FINGERSOFT);
                                            }
                                        }
                                        break;
                                    case 17:
                                        AccountManagerState accountManagerStateLogOut5 = logOut(AccountID.GOOGLE);
                                        if (accountManagerStateLogOut5 != null) {
                                            this.mAccountManagerThread.setAccountManagerState(accountManagerStateLogOut5);
                                            if (AccountManager.this.mAccountListener != null) {
                                                AccountManager.this.mAccountListener.onLogout(AccountID.GOOGLE);
                                            }
                                        }
                                        break;
                                    default:
                                        switch (i3) {
                                            case 100:
                                                updateData((DataStorageItem[]) message.obj);
                                                break;
                                            case 101:
                                                if (!AccountManager.this.mLoginData.getLoggedInFingersoft()) {
                                                    String str3 = (String) message.obj;
                                                    boolean zRegisterEmail2 = AccountManager.this.mFingersoftAccount.registerEmail(str3);
                                                    if (zRegisterEmail2) {
                                                        AccountManager.this.mLoginData.setEmail(str3);
                                                        if (AccountManager.this.mAccountListener != null) {
                                                            AccountManager.this.mAccountListener.onEvent(AccountEvent.EMAIL_REGISTER_SUCCESS);
                                                            AccountManager.this.mAccountListener.onLoginSuccess(AccountID.FINGERSOFT, 100);
                                                        }
                                                    } else if (AccountManager.this.mAccountListener != null) {
                                                        AccountManager.this.mAccountListener.onLoginError(AccountID.FINGERSOFT, 1);
                                                        AccountManager.this.mAccountListener.onEvent(AccountEvent.EMAIL_REGISTER_FAIL);
                                                        AccountManager.this.mLoginData.setLoggedInFingersoft(false);
                                                    }
                                                    AccountManager.log("Email register result: " + zRegisterEmail2);
                                                    break;
                                                }
                                                break;
                                            case 102:
                                                if (!AccountManager.this.mLoginData.getLoggedInFingersoft()) {
                                                    String str4 = (String) message.obj;
                                                    String email2 = AccountManager.this.mLoginData.getEmail();
                                                    if (email2.contains("@")) {
                                                        JSONObject jSONObjectValidateDevice2 = AccountManager.this.mFingersoftAccount.validateDevice(email2, str4);
                                                        if (jSONObjectValidateDevice2 == null) {
                                                            AccountManager.this.mLoginData.setLoggedInFingersoft(false);
                                                            if (AccountManager.this.mAccountListener != null) {
                                                                AccountManager.this.mAccountListener.onEvent(AccountEvent.DEVICE_ACTIVATION_FAIL);
                                                                AccountManager.this.mAccountListener.onLoginError(AccountID.FINGERSOFT, 2);
                                                            }
                                                        } else {
                                                            AccountManager.log("received " + jSONObjectValidateDevice2.toString());
                                                            AccountManager.this.saveLoginData(jSONObjectValidateDevice2);
                                                            if (connectAccounts(new LoginObject(AccountManager.this.mLoginData.getGlobalGuid(), null), AccountID.FINGERSOFT, AccountManager.this.mLoginData.getPrimaryAccount())) {
                                                                if (AccountManager.this.mAccountListener != null) {
                                                                    AccountManager.this.mAccountListener.onLoginSuccess(AccountID.FINGERSOFT, 101);
                                                                    AccountManager.this.mAccountListener.onEvent(AccountEvent.DEVICE_ACTIVATION_SUCCESS);
                                                                    AccountManager.this.mAccountListener.onLoginSuccess(AccountID.FINGERSOFT, 103);
                                                                }
                                                                this.mAccountManagerThread.setAccountManagerState(AccountManagerState.LOGGED_IN);
                                                                AccountManager.this.mLoginData.setLoggedInFingersoft(true);
                                                            } else {
                                                                AccountManager.this.mLoginData.setLoggedInFingersoft(false);
                                                                if (AccountManager.this.mAccountListener != null) {
                                                                    AccountManager.this.mAccountListener.onEvent(AccountEvent.DEVICE_ACTIVATION_FAIL);
                                                                    AccountManager.this.mAccountListener.onLoginError(AccountID.FINGERSOFT, 2);
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        AccountManager.log("Invalid email " + email2);
                                                    }
                                                    break;
                                                }
                                                break;
                                            case 103:
                                                getPublicData(((Integer) message.obj).intValue());
                                                break;
                                            case 104:
                                                getPrivateData(((Integer) message.obj).intValue());
                                                break;
                                            case 105:
                                                addFingersoftFriend((String) message.obj);
                                                break;
                                            case 106:
                                                getFriendData(((Integer) message.obj).intValue());
                                                break;
                                            case 107:
                                                removeFingersoftFriend((String) message.obj);
                                                break;
                                            default:
                                                switch (i3) {
                                                    case 109:
                                                        Utils.setValue(AccountManager.this.mActivity, "facebookFriendsDirtyFlag", "1");
                                                        AccountManager.log("Sending FACEBOOK_FRIENDS_REQUEST: refreshing friends from server");
                                                        boolean zRefreshFacebookFriends = AccountManager.this.mFingersoftAccount.refreshFacebookFriends(AccountManager.this.mLoginData.getBid(), AccountManager.this.mLoginData.getFacebookToken(), AccountManager.this.mLoginData.getGlobalGuid());
                                                        AccountManager.log("refresh fb friends returns " + zRefreshFacebookFriends);
                                                        if (zRefreshFacebookFriends) {
                                                            if (AccountManager.this.mAccountListener != null) {
                                                                AccountManager.this.mAccountListener.onEvent(AccountEvent.FACEBOOK_FRIENDS_REFRESH_SUCCESS);
                                                            }
                                                        } else if (AccountManager.this.mAccountListener != null) {
                                                            AccountManager.this.mAccountListener.onEvent(AccountEvent.FACEBOOK_FRIENDS_REFRESH_FAIL);
                                                        }
                                                        break;
                                                    case 110:
                                                        addFriendRequest((String) message.obj);
                                                        break;
                                                    case 111:
                                                        acceptFriendRequest((String) message.obj);
                                                        break;
                                                    case 112:
                                                        declineFriendRequest((String) message.obj);
                                                        break;
                                                    case 113:
                                                        getFriendRequests();
                                                        break;
                                                    case 114:
                                                        AccountManager.log("Received FACEBOOK_FRIENDS_REQUEST");
                                                        if (!AccountManager.this.mLoginData.getLoggedInFacebook() || AccountManager.this.mFacebookAccount == null) {
                                                            AccountManager.log("ERROR: FACEBOOK_FRIENDS_REQUEST not logged in");
                                                            if (AccountManager.this.mAccountListener != null) {
                                                                AccountManager.this.mAccountListener.onEvent(AccountEvent.FACEBOOK_FRIENDS_REFRESH_FAIL);
                                                            }
                                                        } else {
                                                            AccountManager.log("FACEBOOK_FRIENDS_REQUEST: refreshing friends from facebook");
                                                            AccountManager.this.mFacebookAccount.refreshFriends();
                                                        }
                                                        break;
                                                    case 115:
                                                        AccountManager.log("STORING FACEBOOK FRIENDS");
                                                        FriendManager.getInstance().storeFriendJson((JSONObject) message.obj);
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                            }
                        } else {
                            LoginObject loginObject = (LoginObject) message.obj;
                            StringBuilder sb = new StringBuilder();
                            sb.append("facebook login success, registering ");
                            sb.append(AccountManager.this.mLoginData.getLoggedInFacebook() ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
                            AccountManager.log(sb.toString());
                            if (AccountManager.this.mLoginData.getLoggedInFacebook() ? true : connectAccounts(loginObject, AccountManager.this.mLoginData.getPrimaryAccount(), AccountID.FACEBOOK)) {
                                if (AccountManager.this.mAccountListener != null) {
                                    AccountManager.this.mAccountListener.onLoginSuccess(AccountID.FACEBOOK, 103);
                                }
                            } else if (AccountManager.this.mAccountListener != null) {
                                AccountManager.this.mAccountListener.onLoginError(AccountID.FACEBOOK, 3);
                            }
                            break;
                        }
                    } else {
                        if (!AccountManager.this.mLoginData.getLoggedInFacebook()) {
                            this.mAccountManagerThread.doFacebookLogin();
                        }
                        break;
                    }
                    break;
            }
        }

        private boolean registerFacebook(LoginObject loginObject) {
            String email = loginObject.getEmail();
            if (email != null && email.length() > 0) {
                AccountManager.this.mLoginData.setEmail(email);
            }
            String token = loginObject.getToken();
            if (token == null) {
                AccountManager.this.mLoginData.setLoggedInFacebook(false);
                return false;
            }
            if (!AccountManager.this.mLoginData.getFacebookID().equals(loginObject.getTokenID())) {
                AccountManager.log("asdf FB id changed, update guid");
                AccountManager.this.mLoginData.setGlobalGuid(UniqueID.updateId(AccountManager.this.mActivity));
            } else {
                AccountManager.log("asdf FB id the same, shold keep current guid");
            }
            String facebookToken = AccountManager.this.mLoginData.getFacebookToken();
            AccountManager.log("old facebook token (" + facebookToken + ")");
            AccountManager.this.mLoginData.setFacebookToken(token);
            AccountManager.this.mLoginData.setFacebookID(loginObject.getTokenID());
            if (!AccountManager.this.mLoginData.getLoggedInFacebook()) {
                JSONObject jSONObjectRegisterFacebook = AccountManager.this.mFingersoftAccount.registerFacebook(token);
                if (jSONObjectRegisterFacebook == null) {
                    AccountManager.this.mLoginData.setLoggedInFacebook(false);
                    return false;
                }
                AccountManager.log("Register facebook result " + jSONObjectRegisterFacebook.toString());
                AccountManager.this.saveLoginData(jSONObjectRegisterFacebook);
                AccountManager.this.mLoginData.setLoggedInFacebook(true);
                if (facebookToken == null || facebookToken.length() == 0) {
                }
                return true;
            }
            AccountManager.this.mLoginData.setLoggedInFacebook(true);
            return true;
        }

        private boolean registerGoogle(LoginObject loginObject) {
            String token = loginObject.getToken();
            if (token == null) {
                AccountManager.this.mLoginData.setLoggedInGoogle(false);
                return false;
            }
            if (!AccountManager.this.mLoginData.getGoogleServerAuthCode().equals(loginObject.getTokenID())) {
                AccountManager.log("asdf Google id changed, update guid");
                AccountManager.this.mLoginData.setGlobalGuid(UniqueID.updateId(AccountManager.this.mActivity));
            } else {
                AccountManager.log("asdf Google id the same, shold keep current guid");
            }
            AccountManager.this.mLoginData.getGoogleIDToken();
            AccountManager.this.mLoginData.setGoogleIDToken(token);
            AccountManager.this.mLoginData.setGoogleServerAuthCode(loginObject.getTokenID());
            AccountManager.log("Login data - google server auth code " + loginObject.getTokenID());
            if (!AccountManager.this.mLoginData.getLoggedInGoogle()) {
                JSONObject jSONObjectRegisterGoogle = AccountManager.this.mFingersoftAccount.registerGoogle(token, AccountManager.this.mGoogleClientID);
                if (jSONObjectRegisterGoogle == null) {
                    AccountManager.this.mLoginData.setLoggedInGoogle(false);
                    return false;
                }
                AccountManager.log("Register Google result " + jSONObjectRegisterGoogle.toString());
                AccountManager.this.saveLoginData(jSONObjectRegisterGoogle);
                AccountManager.this.mLoginData.setLoggedInGoogle(true);
                return true;
            }
            AccountManager.this.mLoginData.setLoggedInGoogle(true);
            return true;
        }

        private boolean connectAccounts(LoginObject loginObject, AccountID accountID, AccountID accountID2) {
            String token;
            String token2 = loginObject.getToken();
            if (accountID == AccountID.FACEBOOK) {
                token = AccountManager.this.mLoginData.getFacebookID();
            } else if (accountID == AccountID.GOOGLE) {
                token = AccountManager.this.mLoginData.getGoogleServerAuthCode();
            } else {
                token = accountID == AccountID.FINGERSOFT ? loginObject.getToken() : null;
            }
            String str = token;
            AccountManager.log("CA Source ID " + accountID.toString());
            AccountManager.log("CA loginToken " + str.toString());
            if (str != null) {
                JSONObject jSONObjectConnectAccounts = AccountManager.this.mFingersoftAccount.connectAccounts(accountID, accountID2, AccountManager.this.mLoginData.getGlobalGuid(), str, AccountManager.this.mLoginData.getEmail(), AccountManager.this.mGoogleClientID, token2);
                if (jSONObjectConnectAccounts != null) {
                    AccountManager.this.saveLoginData(jSONObjectConnectAccounts);
                    if (accountID2 == AccountID.FACEBOOK) {
                        AccountManager.this.mLoginData.setLoggedInFacebook(true);
                        AccountManager.this.mLoginData.setFacebookToken(loginObject.getToken());
                    } else if (accountID2 == AccountID.GOOGLE) {
                        AccountManager.this.mLoginData.setLoggedInGoogle(true);
                        AccountManager.this.mLoginData.setGoogleIDToken(loginObject.getToken());
                    } else if (accountID2 == AccountID.FINGERSOFT) {
                        AccountManager.this.mLoginData.setLoggedInFingersoft(true);
                    }
                    return true;
                }
                logOut(accountID2);
                if (AccountManager.this.mAccountListener != null) {
                    AccountManager.this.mAccountListener.onLoginError(accountID, 3);
                }
                return false;
            }
            if (accountID2 == AccountID.FACEBOOK) {
                AccountManager.this.mLoginData.setLoggedInFacebook(false);
            } else if (accountID2 == AccountID.GOOGLE) {
                AccountManager.this.mLoginData.setLoggedInGoogle(false);
            } else if (accountID2 == AccountID.FINGERSOFT) {
                AccountManager.this.mLoginData.setLoggedInFingersoft(false);
            }
            return false;
        }

        private AccountManagerState logOut(AccountID accountID) {
            AccountManager.log("Logout facebook 0 " + accountID);
            if (accountID == AccountID.FINGERSOFT && AccountManager.this.mLoginData.getLoggedInFingersoft()) {
                AccountManager.this.clearLoginData(AccountID.FINGERSOFT);
                AccountManager.this.mLoginData.setLoggedInFingersoft(false);
                if (AccountManager.this.mLoginData.getPrimaryAccount() == null) {
                    return AccountManagerState.LOGGED_OFF;
                }
                return AccountManagerState.LOGGED_IN;
            }
            if (accountID == AccountID.FACEBOOK && AccountManager.this.mLoginData.getLoggedInFacebook()) {
                AccountManager.log("Logout facebook 1");
                AccountManager.this.mFacebookAccount.logOut();
                AccountManager.this.mLoginData.setLoggedInFacebook(false);
                AccountManager.this.clearLoginData(AccountID.FACEBOOK);
                if (AccountManager.this.mLoginData.getPrimaryAccount() == null) {
                    return AccountManagerState.LOGGED_OFF;
                }
                return AccountManagerState.LOGGED_IN;
            }
            if (accountID != AccountID.GOOGLE || !AccountManager.this.mLoginData.getLoggedInGoogle()) {
                return null;
            }
            AccountManager.this.clearLoginData(AccountID.GOOGLE);
            AccountManager.this.mGoogleAccount.logOut();
            AccountManager.this.mLoginData.setLoggedInGoogle(false);
            if (AccountManager.this.mLoginData.getPrimaryAccount() == null) {
                return AccountManagerState.LOGGED_OFF;
            }
            return AccountManagerState.LOGGED_IN;
        }

        private void updateData(DataStorageItem[] dataStorageItemArr) {
            boolean zUpdateData = AccountManager.this.mFingersoftAccount.updateData(AccountID.FINGERSOFT, dataStorageItemArr, AccountManager.this.mLoginData.getToken(), AccountManager.this.mLoginData.getBid(), AccountManager.this.mLoginData.getGlobalGuid());
            if (AccountManager.this.mAccountListener != null) {
                AccountManager.this.mAccountListener.onDataWrite(zUpdateData);
            }
        }

        private void getPrivateData(int i) {
            String token = AccountManager.this.mLoginData.getToken();
            String bid = AccountManager.this.mLoginData.getBid();
            String globalGuid = AccountManager.this.mLoginData.getGlobalGuid();
            JSONObject jSONObjectFetchPrivateData = AccountManager.this.mFingersoftAccount.fetchPrivateData(i, bid, token, globalGuid);
            if (jSONObjectFetchPrivateData == null) {
                AccountManager.log("Result null");
                if (AccountManager.this.mAccountListener != null) {
                    AccountManager.this.mAccountListener.onEvent(AccountEvent.READ_PRIVATE_DATA_FAILED);
                    return;
                }
                return;
            }
            AccountManager.log("Result : " + jSONObjectFetchPrivateData.toString());
            String receivedPrivateData = parseReceivedPrivateData(jSONObjectFetchPrivateData, i);
            int receivedStatusCode = parseReceivedStatusCode(jSONObjectFetchPrivateData);
            if (AccountManager.this.mAccountListener != null) {
                AccountManager.log("Result to be sent: " + receivedPrivateData + " status code: " + receivedStatusCode);
                AccountManager.this.mAccountListener.onDataRead(DataSlotType.DATA_SLOT_TYPE_PRIVATE, i, globalGuid, receivedPrivateData, receivedStatusCode);
            }
        }

        private void getPublicData(int i) {
            JSONObject jSONObjectFetchPublicData = AccountManager.this.mFingersoftAccount.fetchPublicData(i, AccountManager.this.mLoginData.getBid(), AccountManager.this.mLoginData.getToken(), AccountManager.this.mLoginData.getGlobalGuid());
            if (jSONObjectFetchPublicData == null) {
                AccountManager.log("Result null");
                if (AccountManager.this.mAccountListener != null) {
                    AccountManager.this.mAccountListener.onEvent(AccountEvent.READ_PUBLIC_DATA_FAILED);
                    return;
                }
                return;
            }
            AccountManager.log("Result : " + jSONObjectFetchPublicData.toString());
        }

        private void addFingersoftFriend(String str) {
            String token = AccountManager.this.mLoginData.getToken();
            if (AccountManager.this.mFingersoftAccount.addFriend(AccountManager.this.mLoginData.getBid(), token, AccountManager.this.mLoginData.getGlobalGuid(), str)) {
                if (AccountManager.this.mAccountListener != null) {
                    AccountManager.this.mAccountListener.onEvent(AccountEvent.ADD_FINGERSOFT_FRIEND_SUCCESS);
                }
                AccountManager.log("Friend added");
            } else {
                if (AccountManager.this.mAccountListener != null) {
                    AccountManager.this.mAccountListener.onEvent(AccountEvent.ADD_FINGERSOFT_FRIEND_FAIL);
                }
                AccountManager.log("Friend add faileD");
            }
        }

        private void removeFingersoftFriend(String str) {
            AccountManager.this.mLoginData.getToken();
            AccountManager.this.mLoginData.getBid();
            AccountManager.this.mLoginData.getGlobalGuid();
        }

        private void getFriendData(int i) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String token = AccountManager.this.mLoginData.getToken();
            String bid = AccountManager.this.mLoginData.getBid();
            String globalGuid = AccountManager.this.mLoginData.getGlobalGuid();
            ArrayList arrayList = new ArrayList();
            Integer.parseInt(Utils.getValueString(AccountManager.this.mActivity, "facebookFriendsDirtyFlag", "1"));
            JSONObject facebookFriends = AccountManager.this.mFingersoftAccount.getFacebookFriends(bid, token, globalGuid);
            if (facebookFriends != null) {
                try {
                    FriendManager.getInstance().setGuidFbIdList(facebookFriends.getJSONObject(AccountManager.this.mFingersoftAccount.getApplicationID()).getJSONArray("friendsList"));
                    Utils.setValue(AccountManager.this.mActivity, "facebookFriendsDirtyFlag", AppEventsConstants.EVENT_PARAM_VALUE_NO);
                } catch (Exception unused) {
                    if (AccountManager.this.mAccountListener != null) {
                        AccountManager.this.mAccountListener.onFriendDataRead(DataSlotType.DATA_SLOT_TYPE_PUBLIC, i, null);
                        return;
                    }
                    return;
                }
            }
            JSONObject friends = AccountManager.this.mFingersoftAccount.getFriends(bid, token, globalGuid);
            AccountManager.log("get friend result " + friends);
            if (friends != null) {
                try {
                    JSONObject jSONObject = friends.getJSONObject(AccountManager.this.mFingersoftAccount.getApplicationID());
                    JSONArray jSONArray = jSONObject.getJSONArray("friendsList");
                    AccountManager.log("Jsonres: " + jSONObject);
                    AccountManager.log("Array : " + jSONArray);
                    if (jSONArray != null && jSONArray.length() != 0) {
                        String[] strArr = new String[jSONArray.length()];
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            strArr[i2] = jSONArray.getString(i2);
                            AccountManager.log("Guid added " + strArr[i2]);
                        }
                        String[] bidListByGuids = AccountManager.this.getBidListByGuids(strArr);
                        AccountManager.log("Calling fetch, array len " + jSONArray.length());
                        String[] strArr2 = new String[bidListByGuids.length];
                        int i3 = 0;
                        boolean z = false;
                        while (true) {
                            int i4 = i3 + 1;
                            boolean z2 = i4 * 16 >= bidListByGuids.length ? true : z;
                            int i5 = i3 * 16;
                            int length = 16;
                            if (bidListByGuids.length - i5 < 16) {
                                length = (bidListByGuids.length - i5) % 16;
                            }
                            AccountManager.log("Friend fetch size " + length + " step " + i3);
                            String[] strArr3 = new String[length];
                            String[] strArr4 = new String[length];
                            for (int i6 = 0; i6 < length; i6++) {
                                int i7 = i6 + i5;
                                strArr3[i6] = strArr[i7];
                                strArr4[i6] = bidListByGuids[i7];
                            }
                            String[] strArr5 = strArr2;
                            String[] strArr6 = bidListByGuids;
                            String[] strArr7 = strArr;
                            JSONObject jSONObjectFetchPublicDataByList = AccountManager.this.mFingersoftAccount.fetchPublicDataByList(i, bid, token, globalGuid, strArr3, strArr4);
                            if (jSONObjectFetchPublicDataByList == null) {
                                AccountManager.this.mAccountListener.onEvent(AccountEvent.READ_PUBLIC_DATA_FAILED);
                                return;
                            }
                            JSONArray jSONArray2 = jSONObjectFetchPublicDataByList.getJSONObject(AccountManager.this.mFingersoftAccount.getApplicationID()).getJSONArray("data_" + i);
                            AccountManager.log("Friend data result " + jSONObjectFetchPublicDataByList);
                            AccountManager.log("Friend queries done in [" + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms]");
                            if (jSONArray2 != null && jSONArray2.length() != 0) {
                                for (int i8 = 0; i8 < jSONArray2.length(); i8++) {
                                    try {
                                        JSONObject jSONObject2 = (JSONObject) jSONArray2.get(i8);
                                        int i9 = i8 + i5;
                                        strArr5[i9] = FingersoftAccount.decodeString(jSONObject2.getString(strArr3[i8]));
                                        Friend friendByGUID = FriendManager.getInstance().getFriendByGUID(strArr3[i8]);
                                        if (friendByGUID != null) {
                                            friendByGUID.setGuid(strArr3[i8]);
                                            friendByGUID.setPlayerData(strArr5[i9]);
                                            arrayList.add(friendByGUID);
                                        } else {
                                            Friend friend = new Friend(null, null, null, null, strArr5[i9]);
                                            friend.setGuid(strArr3[i8]);
                                            arrayList.add(friend);
                                        }
                                        AccountManager.this.saveBidByGuid(strArr3[i8], jSONObject2.getString("bid"));
                                    } catch (Exception unused2) {
                                        AccountManager.log("ERROR: Couldn't receive data for friend number " + i8 + " (" + strArr3[i8] + ")");
                                    }
                                }
                                if (z2) {
                                    if (AccountManager.this.mAccountListener != null) {
                                        AccountManager.log("calling onFriendDataRead()");
                                        AccountManager.this.mAccountListener.onFriendDataRead(DataSlotType.DATA_SLOT_TYPE_PUBLIC, i, (Friend[]) arrayList.toArray(new Friend[arrayList.size()]));
                                        return;
                                    } else {
                                        AccountManager.log("ERROR: accountListener null");
                                        return;
                                    }
                                }
                                i3 = i4;
                                z = z2;
                                strArr2 = strArr5;
                                bidListByGuids = strArr6;
                                strArr = strArr7;
                            }
                            AccountManager.log("No friends");
                            if (AccountManager.this.mAccountListener != null) {
                                AccountManager.log("No friends, calling onFriendDataRead() with null parameters");
                                AccountManager.this.mAccountListener.onFriendDataRead(DataSlotType.DATA_SLOT_TYPE_PUBLIC, i, null);
                                return;
                            }
                            return;
                        }
                    }
                    if (AccountManager.this.mAccountListener != null) {
                        AccountManager.this.mAccountListener.onFriendDataRead(DataSlotType.DATA_SLOT_TYPE_PUBLIC, i, null);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    AccountManager.log("JSON EXception " + e);
                    if (AccountManager.this.mAccountListener != null) {
                        AccountManager.this.mAccountListener.onEvent(AccountEvent.READ_PUBLIC_DATA_FAILED);
                        return;
                    }
                    return;
                }
            }
            if (AccountManager.this.mAccountListener != null) {
                AccountManager.this.mAccountListener.onEvent(AccountEvent.READ_PUBLIC_DATA_FAILED);
            }
        }

        private void addFriendRequest(String str) {
            String token = AccountManager.this.mLoginData.getToken();
            if (AccountManager.this.mFingersoftAccount.addFriendRequest(AccountManager.this.mLoginData.getBid(), token, AccountManager.this.mLoginData.getGlobalGuid(), str)) {
                if (AccountManager.this.mAccountListener != null) {
                    AccountManager.this.mAccountListener.onEvent(AccountEvent.SEND_FRIEND_REQUEST_SUCCESS);
                }
                AccountManager.log("Friend request sent");
            } else if (AccountManager.this.mAccountListener != null) {
                AccountManager.this.mAccountListener.onEvent(AccountEvent.SEND_FRIEND_REQUEST_FAIL);
            }
        }

        private void acceptFriendRequest(String str) {
            String token = AccountManager.this.mLoginData.getToken();
            if (AccountManager.this.mFingersoftAccount.acceptFriendRequest(AccountManager.this.mLoginData.getBid(), token, AccountManager.this.mLoginData.getGlobalGuid(), str)) {
                AccountManager.log("Friend request accepted");
                if (AccountManager.this.mAccountListener != null) {
                    AccountManager.this.mAccountListener.onEvent(AccountEvent.ACCEPT_FRIEND_REQUEST_SUCCESS);
                    return;
                }
                return;
            }
            AccountManager.log("Friend request accept failed");
            if (AccountManager.this.mAccountListener != null) {
                AccountManager.this.mAccountListener.onEvent(AccountEvent.ACCEPT_FRIEND_REQUEST_FAIL);
            }
        }

        private void declineFriendRequest(String str) {
            String token = AccountManager.this.mLoginData.getToken();
            if (AccountManager.this.mFingersoftAccount.declineFriendRequest(AccountManager.this.mLoginData.getBid(), token, AccountManager.this.mLoginData.getGlobalGuid(), str)) {
                if (AccountManager.this.mAccountListener != null) {
                    AccountManager.this.mAccountListener.onEvent(AccountEvent.DECLINE_FRIEND_REQUEST_SUCCESS);
                }
                AccountManager.log("Friend request declined");
            } else {
                AccountManager.log("Friend request decline failed");
                if (AccountManager.this.mAccountListener != null) {
                    AccountManager.this.mAccountListener.onEvent(AccountEvent.ACCEPT_FRIEND_REQUEST_FAIL);
                }
            }
        }

        private void getFriendRequests() {
            JSONObject friendRequests = AccountManager.this.mFingersoftAccount.getFriendRequests(AccountManager.this.mLoginData.getBid(), AccountManager.this.mLoginData.getToken(), AccountManager.this.mLoginData.getGlobalGuid());
            if (friendRequests == null) {
                AccountManager.this.mAccountListener.onReadFriendRequests(null);
                AccountManager.log("Get friend requests failed");
                return;
            }
            try {
                AccountManager.log("Get friend requests result " + friendRequests.toString());
                JSONArray jSONArray = friendRequests.getJSONArray("friend_requests");
                if (jSONArray == null) {
                    AccountManager.this.mAccountListener.onReadFriendRequests(null);
                    return;
                }
                if (jSONArray.length() == 0) {
                    AccountManager.this.mAccountListener.onReadFriendRequests(null);
                    return;
                }
                String[] strArr = new String[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    strArr[i] = jSONArray.getString(i);
                }
                AccountManager.this.mAccountListener.onReadFriendRequests(strArr);
            } catch (Exception unused) {
                AccountManager.this.mAccountListener.onReadFriendRequests(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveLoginData(JSONObject jSONObject) {
        try {
            if (jSONObject == null) {
                log("ERROR: saveLoginData :: object null, cannot save");
                return;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject(this.mFingersoftAccount.getApplicationID());
            if (jSONObject2 == null) {
                log("ERROR: saveLoginData :: data null, cannot save");
                return;
            }
            this.mLoginData.setBid(jSONObject2.getString("bid"));
            this.mLoginData.setToken(jSONObject2.getString("token"));
            this.mLoginData.setGlobalGuid(jSONObject2.getString("global_guid"));
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLoginData(AccountID accountID) {
        log("Clearing login data " + accountID);
        if (accountID == AccountID.FINGERSOFT) {
            this.mLoginData.clearLoginData(accountID);
        } else if (accountID == AccountID.FACEBOOK) {
            this.mLoginData.clearLoginData(accountID);
        } else if (accountID == AccountID.GOOGLE) {
            this.mLoginData.clearLoginData(accountID);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveBidByGuid(String str, String str2) {
        Utils.setValue(this.mActivity, str, str2);
    }

    private String loadBidByGuid(String str) {
        return Utils.getValueString(this.mActivity, str, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String[] getBidListByGuids(String[] strArr) {
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr2[i] = Utils.getValueString(this.mActivity, strArr[i], "");
        }
        return strArr2;
    }
}
