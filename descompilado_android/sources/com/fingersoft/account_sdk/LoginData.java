package com.fingersoft.account_sdk;

import android.app.Activity;
import com.facebook.appevents.AppEventsConstants;

/* JADX INFO: loaded from: classes.dex */
public class LoginData {
    private static final int ACCOUNT_FACEBOOK = 2;
    private static final int ACCOUNT_FINGERSOFT = 1;
    private static final int ACCOUNT_GOOGLE = 3;
    private static final int ACCOUNT_NOT_CONNECTED = 0;
    private Activity mActivity;
    private String mBid;
    private String mEmail;
    private String mFacebookID;
    private String mFacebookToken;
    private String mGlobalGuid;
    private String mGoogleIDToken;
    private String mGoogleServerAuthCode;
    private boolean mLoggedInFacebook;
    private boolean mLoggedInFingersoft;
    private boolean mLoggedInGoogle;
    private AccountManager.AccountID mPrimaryAccount;
    private String mToken;

    public LoginData(Activity activity) {
        this.mActivity = activity;
        readLoginData();
    }

    public void readLoginData() {
        AccountManager.log("activity " + this.mActivity);
        this.mEmail = "";
        this.mToken = Utils.getValueString(this.mActivity, "token", "");
        this.mBid = Utils.getValueString(this.mActivity, "bid", "");
        this.mGlobalGuid = Utils.getValueString(this.mActivity, "global_guid", "");
        this.mFacebookToken = Utils.getValueString(this.mActivity, "fbtoken", "");
        this.mFacebookID = Utils.getValueString(this.mActivity, "fbidtoken", "");
        this.mGoogleIDToken = Utils.getValueString(this.mActivity, "googleidtoken", "");
        this.mGoogleServerAuthCode = Utils.getValueString(this.mActivity, "googleserverauthcode", "");
        this.mLoggedInFacebook = Utils.getValueInt(this.mActivity, "logged_in_facebook", 0) != 0;
        this.mLoggedInFingersoft = Utils.getValueInt(this.mActivity, "logged_in_fingersoft", 0) != 0;
        this.mLoggedInGoogle = Utils.getValueInt(this.mActivity, "logged_in_google", 0) != 0;
        this.mPrimaryAccount = getAccountIDByInt(Utils.getValueInt(this.mActivity, "primary_account", 0));
        AccountManager.log("Login data read " + this.mEmail + " \n" + this.mToken + " \n" + this.mBid + " \n" + this.mGlobalGuid + " \n" + this.mFacebookToken + " \n" + this.mGoogleIDToken + " \n" + this.mGoogleServerAuthCode);
    }

    private AccountManager.AccountID getAccountIDByInt(int i) {
        if (i == 0) {
            return null;
        }
        if (i == 1) {
            return AccountManager.AccountID.FINGERSOFT;
        }
        if (i == 2) {
            return AccountManager.AccountID.FACEBOOK;
        }
        if (i != 3) {
            return null;
        }
        return AccountManager.AccountID.GOOGLE;
    }

    /* JADX INFO: renamed from: com.fingersoft.account_sdk.LoginData$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fingersoft$account_sdk$AccountManager$AccountID = new int[AccountManager.AccountID.values().length];

        static {
            try {
                $SwitchMap$com$fingersoft$account_sdk$AccountManager$AccountID[AccountManager.AccountID.FINGERSOFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fingersoft$account_sdk$AccountManager$AccountID[AccountManager.AccountID.FACEBOOK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fingersoft$account_sdk$AccountManager$AccountID[AccountManager.AccountID.GOOGLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private int getAccountIntByID(AccountManager.AccountID accountID) {
        if (accountID == null) {
            return 0;
        }
        int i = AnonymousClass1.$SwitchMap$com$fingersoft$account_sdk$AccountManager$AccountID[accountID.ordinal()];
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                i2 = 3;
                if (i != 3) {
                    return 0;
                }
            }
        }
        return i2;
    }

    public AccountManager.AccountID getPrimaryAccount() {
        return this.mPrimaryAccount;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getToken() {
        return this.mToken;
    }

    public String getBid() {
        return this.mBid;
    }

    public String getGlobalGuid() {
        return this.mGlobalGuid;
    }

    public String getFacebookToken() {
        return this.mFacebookToken;
    }

    public String getGoogleIDToken() {
        return this.mGoogleIDToken;
    }

    public String getGoogleServerAuthCode() {
        return this.mGoogleServerAuthCode;
    }

    public boolean getLoggedInFingersoft() {
        return this.mLoggedInFingersoft;
    }

    public boolean getLoggedInFacebook() {
        return this.mLoggedInFacebook;
    }

    public boolean getLoggedInGoogle() {
        return this.mLoggedInGoogle;
    }

    public void setLoggedInFacebook(boolean z) {
        if (z && !getLoggedInFingersoft() && !getLoggedInGoogle()) {
            setPrimaryAccount(AccountManager.AccountID.FACEBOOK);
        } else if (getLoggedInGoogle()) {
            setPrimaryAccount(AccountManager.AccountID.GOOGLE);
        } else if (getLoggedInFingersoft()) {
            setPrimaryAccount(AccountManager.AccountID.FINGERSOFT);
        } else {
            setPrimaryAccount(null);
        }
        Utils.setValue(this.mActivity, "logged_in_facebook", z ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        this.mLoggedInFacebook = z;
    }

    public void setLoggedInFingersoft(boolean z) {
        if (z && !getLoggedInFacebook() && !getLoggedInGoogle()) {
            setPrimaryAccount(AccountManager.AccountID.FINGERSOFT);
        } else if (getLoggedInFacebook()) {
            setPrimaryAccount(AccountManager.AccountID.FACEBOOK);
        } else if (getLoggedInGoogle()) {
            setPrimaryAccount(AccountManager.AccountID.GOOGLE);
        } else {
            setPrimaryAccount(null);
        }
        Utils.setValue(this.mActivity, "logged_in_fingersoft", z ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        this.mLoggedInFingersoft = z;
    }

    public void setLoggedInGoogle(boolean z) {
        if (z && this.mPrimaryAccount == null) {
            setPrimaryAccount(AccountManager.AccountID.GOOGLE);
        } else if (z && !getLoggedInFingersoft() && !getLoggedInFacebook()) {
            setPrimaryAccount(AccountManager.AccountID.GOOGLE);
        } else if (getLoggedInFacebook()) {
            setPrimaryAccount(AccountManager.AccountID.FACEBOOK);
        } else if (getLoggedInFingersoft()) {
            setPrimaryAccount(AccountManager.AccountID.FINGERSOFT);
        } else {
            setPrimaryAccount(null);
        }
        Utils.setValue(this.mActivity, "logged_in_google", z ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        this.mLoggedInGoogle = z;
    }

    private void setPrimaryAccount(AccountManager.AccountID accountID) {
        AccountManager.log("Primary account changed to " + accountID);
        this.mPrimaryAccount = accountID;
        Utils.setValue(this.mActivity, "primary_account", "" + getAccountIntByID(accountID));
    }

    public void setEmail(String str) {
        Utils.setValue(this.mActivity, "email", "");
        this.mEmail = "";
    }

    public void setToken(String str) {
        Utils.setValue(this.mActivity, "token", str);
        this.mToken = str;
    }

    public void setBid(String str) {
        Utils.setValue(this.mActivity, "bid", str);
        this.mBid = str;
    }

    public void setGlobalGuid(String str) {
        Utils.setValue(this.mActivity, "global_guid", str);
        this.mGlobalGuid = str;
    }

    public void setFacebookToken(String str) {
        Utils.setValue(this.mActivity, "fbtoken", str);
        this.mFacebookToken = str;
    }

    public void setGoogleIDToken(String str) {
        Utils.setValue(this.mActivity, "googleidtoken", str);
        this.mGoogleIDToken = str;
    }

    public void setGoogleServerAuthCode(String str) {
        Utils.setValue(this.mActivity, "googleserverauthcode", str);
        this.mGoogleServerAuthCode = str;
    }

    public void setFacebookID(String str) {
        Utils.setValue(this.mActivity, "fbidtoken", str);
        this.mFacebookID = str;
    }

    public String getFacebookID() {
        return this.mFacebookID;
    }

    public void clearLoginData(AccountManager.AccountID accountID) {
        if (accountID == AccountManager.AccountID.FINGERSOFT) {
            setEmail("");
            setLoggedInFingersoft(false);
        } else if (accountID == AccountManager.AccountID.FACEBOOK) {
            Utils.setValue(this.mActivity, "fbtoken", "");
            this.mFacebookToken = "";
            setLoggedInFacebook(false);
        } else if (accountID == AccountManager.AccountID.GOOGLE) {
            this.mGoogleIDToken = "";
            Utils.setValue(this.mActivity, "googleidtoken", "");
            setLoggedInGoogle(false);
        }
    }
}
