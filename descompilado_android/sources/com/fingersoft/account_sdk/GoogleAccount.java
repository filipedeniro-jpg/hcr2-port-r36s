package com.fingersoft.account_sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes.dex */
public class GoogleAccount implements IAccount {
    public static final int RC_SIGN_IN = 9001;
    private Activity mActivity;
    private android.accounts.AccountManager mGoogleAccountManager;
    private GoogleApiClient mGoogleApiClient;
    private Handler mMessageHandler;

    public GoogleAccount(Activity activity, Handler handler, String str) {
        this.mActivity = activity;
        this.mMessageHandler = handler;
        this.mGoogleApiClient = new GoogleApiClient.Builder(this.mActivity).addApi(Auth.GOOGLE_SIGN_IN_API, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestIdToken(str).requestServerAuthCode(str).build()).build();
    }

    @Override // com.fingersoft.account_sdk.IAccount
    public void logIn() {
        this.mActivity.startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(this.mGoogleApiClient), 9001);
    }

    public void logOut() {
        this.mGoogleApiClient.disconnect();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 9001) {
            handleSignInResult(Auth.GoogleSignInApi.getSignInResultFromIntent(intent));
        }
    }

    private void handleSignInResult(GoogleSignInResult googleSignInResult) {
        if (googleSignInResult.isSuccess()) {
            GoogleSignInAccount signInAccount = googleSignInResult.getSignInAccount();
            try {
                String idToken = signInAccount.getIdToken();
                signInAccount.getEmail();
                String id = signInAccount.getId();
                AdUtils.log("GOOGLE: idToken " + idToken);
                AdUtils.log("GOOGLE: authCode " + signInAccount.getServerAuthCode());
                AdUtils.log("GOOGLE: email " + signInAccount.getEmail());
                LoginObject loginObject = new LoginObject(idToken, id);
                AdUtils.log("GOOGLE Login success");
                Message message = new Message();
                message.what = 13;
                message.obj = loginObject;
                this.mMessageHandler.sendMessage(message);
                return;
            } catch (Exception e) {
                AdUtils.log("GOOGLE Login failed " + e);
                this.mMessageHandler.sendEmptyMessage(14);
                return;
            }
        }
        AdUtils.log("GOOGLE Login failed " + googleSignInResult.getStatus());
        this.mMessageHandler.sendEmptyMessage(14);
    }
}
