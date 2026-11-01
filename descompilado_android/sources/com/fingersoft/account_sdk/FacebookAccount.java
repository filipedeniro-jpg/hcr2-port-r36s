package com.fingersoft.account_sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class FacebookAccount implements IAccount {
    private AccessToken mAccessToken;
    private AccessTokenTracker mAccessTokenTracker;
    private AccountManager.AccountID mAccountID = AccountManager.AccountID.FACEBOOK;
    private AccountListener mAccountListener;
    private Handler mAccountMessageHandler;
    private Activity mActivity;
    private CallbackManager mCallbackManager;
    private String mEmail;
    private ProfileTracker mProfileTracker;
    private String tmpNewIDToken;
    private String tmpOldIDToken;

    public FacebookAccount(Activity activity, AccountListener accountListener, Handler handler) {
        this.mActivity = activity;
        this.mAccountListener = accountListener;
        AccountManager.log("at Facebook Account Create");
        if (!FacebookSdk.isInitialized()) {
            AccountManager.log("Initializing FB sdk");
            FacebookSdk.sdkInitialize(this.mActivity);
        }
        AccountManager.log("SIGNATURE: " + FacebookSdk.getApplicationSignature(activity));
        this.mAccountMessageHandler = handler;
        this.mCallbackManager = CallbackManager.Factory.create();
        this.mAccessToken = AccessToken.getCurrentAccessToken();
        this.mProfileTracker = new ProfileTracker() { // from class: com.fingersoft.account_sdk.FacebookAccount.1
            @Override // com.facebook.ProfileTracker
            protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                AccountManager.log("profile changed?");
            }
        };
        this.mAccessTokenTracker = new AccessTokenTracker() { // from class: com.fingersoft.account_sdk.FacebookAccount.2
            @Override // com.facebook.AccessTokenTracker
            protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken2) {
                String userId = accessToken != null ? accessToken.getUserId() : null;
                String userId2 = accessToken2 != null ? accessToken2.getUserId() : null;
                if (FacebookAccount.this.mAccessToken != null) {
                    AccountManager.log("fbaccout, user id's - mAccessToken.ID: " + FacebookAccount.this.mAccessToken.getUserId());
                }
                AccountManager.log("fbaccout, user id's - oldID:" + userId + ", current ID: " + userId2);
                if (accessToken != null && accessToken.equals(accessToken2)) {
                    AccountManager.log("fbaccount, access token ");
                }
                FacebookAccount.this.mAccessToken = accessToken2;
                Message message = new Message();
                message.what = 11;
                if (FacebookAccount.this.mAccessToken != null) {
                    message.obj = FacebookAccount.this.mAccessToken.getToken();
                } else {
                    message.obj = null;
                }
                FacebookAccount.this.mAccountMessageHandler.sendMessage(message);
            }
        };
        LoginManager.getInstance().registerCallback(this.mCallbackManager, new FacebookCallback<LoginResult>() { // from class: com.fingersoft.account_sdk.FacebookAccount.3
            @Override // com.facebook.FacebookCallback
            public void onSuccess(LoginResult loginResult) {
                AccountManager.log("Facebook login success " + loginResult);
                FacebookAccount.this.mAccessToken = loginResult.getAccessToken();
                FacebookAccount.this.requestData(true);
            }

            @Override // com.facebook.FacebookCallback
            public void onCancel() {
                AccountManager.log("Facebook login cancel");
                if (AccessToken.getCurrentAccessToken() != null) {
                    LoginManager.getInstance().logOut();
                }
                FacebookAccount.this.mAccountMessageHandler.sendEmptyMessage(6);
            }

            @Override // com.facebook.FacebookCallback
            public void onError(FacebookException facebookException) {
                AccountManager.log("Facebook login error #1 " + facebookException);
                if (AccessToken.getCurrentAccessToken() != null) {
                    LoginManager.getInstance().logOut();
                }
                FacebookAccount.this.mAccountMessageHandler.sendEmptyMessage(6);
            }
        });
        new Handler().postDelayed(new Runnable() { // from class: com.fingersoft.account_sdk.FacebookAccount.4
            @Override // java.lang.Runnable
            public void run() {
                FacebookAccount.this.mAccessToken = AccessToken.getCurrentAccessToken();
                if (FacebookAccount.this.mAccessToken == null || FacebookAccount.this.mAccessToken.isExpired()) {
                    return;
                }
                AccountManager.log("fbaccount, accesstoken delayed " + FacebookAccount.this.mAccessToken.toString());
            }
        }, 500L);
    }

    @Override // com.fingersoft.account_sdk.IAccount
    public void logIn() {
        LoginData loginData = new LoginData(this.mActivity);
        AccountManager.log("Is there a token? " + AccessToken.getCurrentAccessToken());
        AccessToken accessToken = this.mAccessToken;
        if (accessToken == null) {
            AccountManager.log("logging in");
            LoginManager.getInstance().logInWithReadPermissions(this.mActivity, Arrays.asList("public_profile", "user_friends"));
            return;
        }
        if (accessToken.isExpired()) {
            AccountManager.log("Access token expired login again");
            if (AccessToken.getCurrentAccessToken() != null) {
                AccountManager.log("Old login data found, loggin out.");
                LoginManager.getInstance().logOut();
            }
            AccountManager.log("logging in");
            LoginManager.getInstance().logInWithReadPermissions(this.mActivity, Arrays.asList("public_profile", "user_friends"));
            return;
        }
        AccountManager.log("Token exists, don't do real login --");
        LoginObject loginObject = new LoginObject(this.mAccessToken.getToken(), this.mAccessToken.getUserId());
        AccountManager.log("fbaccount -- loginData ID: " + loginData.getFacebookID() + " loginObject ID: " + AccessToken.getCurrentAccessToken().getUserId());
        if (!loginData.getFacebookID().equals(loginObject.getTokenID())) {
            AccountManager.log("fbaccount FB id changed, should log out old account");
        } else {
            AccountManager.log("fbaccount FB id the same, shold keep current account");
        }
        Message message = new Message();
        message.what = 4;
        message.obj = loginObject;
        this.mAccountMessageHandler.sendMessage(message);
    }

    public void logOut() {
        LoginManager.getInstance().logOut();
    }

    public void requestData(final boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("limit", "5000");
        bundle.putString(GraphRequest.FIELDS_PARAM, "id,first_name,link,picture.type(square)");
        AccountManager.log("Facebook Graph API version: " + ServerProtocol.getDefaultAPIVersion());
        GraphRequest graphRequestNewGraphPathRequest = GraphRequest.newGraphPathRequest(AccessToken.getCurrentAccessToken(), "/me/friends", new GraphRequest.Callback() { // from class: com.fingersoft.account_sdk.FacebookAccount.5
            @Override // com.facebook.GraphRequest.Callback
            public void onCompleted(GraphResponse graphResponse) {
                JSONObject jSONObject = graphResponse.getJSONObject();
                AccountManager.log("ERRORS: " + graphResponse.getError());
                AccountManager.log("request " + graphResponse.getRequest().toString());
                AccountManager.log("response: " + graphResponse.getRawResponse());
                if (jSONObject != null) {
                    try {
                        AccountManager.log("FACEBOOK FRIENDS, got " + jSONObject.toString());
                        FacebookAccount.this.mEmail = null;
                        JSONArray jSONArray = jSONObject.getJSONArray("data");
                        int length = jSONArray != null ? jSONArray.length() : 0;
                        boolean z2 = false;
                        for (int i = 0; i < length; i++) {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            String string = jSONObject2.getString("first_name");
                            String string2 = jSONObject2.getString("id");
                            AccountManager.log(i + ". facebook pic url " + jSONObject2.getJSONObject("picture").getJSONObject("data").getString("url"));
                            String strSha1Hash = Utils.sha1Hash("fbfriend_" + string + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + string2);
                            if (!z) {
                                if (Utils.getValueString(FacebookAccount.this.mActivity, strSha1Hash, AppEventsConstants.EVENT_PARAM_VALUE_NO).equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                                    Utils.setValue(FacebookAccount.this.mActivity, strSha1Hash, "1");
                                    AccountManager.log("new Friend " + (i + 1) + ": " + string + " ID: " + string2);
                                    z2 = true;
                                } else {
                                    AccountManager.log("old Friend " + (i + 1) + ": " + string + " ID: " + string2);
                                }
                            }
                        }
                        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
                        if (currentAccessToken == null) {
                            return;
                        }
                        String token = currentAccessToken.getToken();
                        if (z) {
                            AccountManager.log("Facebook Access token " + token);
                            LoginObject loginObject = new LoginObject(token, currentAccessToken.getUserId());
                            Message message = new Message();
                            message.what = 4;
                            message.obj = loginObject;
                            FacebookAccount.this.mAccountMessageHandler.sendMessage(message);
                        } else if (z2) {
                            AccountManager.log("Sending FACEBOOK_FRIENDS_REQUEST: need to update friends from server");
                            FacebookAccount.this.mAccountMessageHandler.sendEmptyMessage(109);
                        } else {
                            AccountManager.log("FACEBOOK_FRIENDS_REQUEST: no new friends, send success");
                            if (FacebookAccount.this.mAccountListener != null) {
                                FacebookAccount.this.mAccountListener.onEvent(AccountManager.AccountEvent.FACEBOOK_FRIENDS_REFRESH_SUCCESS);
                            }
                        }
                        Message message2 = new Message();
                        message2.what = 115;
                        message2.obj = jSONObject;
                        FacebookAccount.this.mAccountMessageHandler.sendMessage(message2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        graphRequestNewGraphPathRequest.setParameters(bundle);
        graphRequestNewGraphPathRequest.executeAsync();
    }

    public void refreshFriends() {
        requestData(false);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        CallbackManager callbackManager = this.mCallbackManager;
        if (callbackManager != null) {
            callbackManager.onActivityResult(i, i2, intent);
        }
    }
}
