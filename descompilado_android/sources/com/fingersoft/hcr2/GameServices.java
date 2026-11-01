package com.fingersoft.hcr2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import org.cocos2dx.lib.Cocos2dxHelper;

/* JADX INFO: loaded from: classes.dex */
public class GameServices implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public static final String TAG = "hcr2 - GameServices";
    private static Activity mActivity;
    private static GoogleApiClient mGoogleApiClient;

    public String getPlayerName() {
        return "";
    }

    public GameServices(Activity activity) {
        mActivity = activity;
        mGoogleApiClient = new GoogleApiClient.Builder(mActivity).addApi(Games.API).addScope(Games.SCOPE_GAMES).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        if (!Cocos2dxHelper.getBoolForKey("gameServicesConnected", false) || Cocos2dxHelper.getBoolForKey("gameServicesManualDisconnect", false)) {
            return;
        }
        Log.d(TAG, "Game Services connected, auto-connect API");
        mGoogleApiClient.connect();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected() called. Sign in successful!");
        AppActivity.onGameServicesConnected();
        Cocos2dxHelper.setBoolForKey("gameServicesConnected", true);
        Cocos2dxHelper.setBoolForKey("gameServicesManualDisconnect", false);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended() called. Trying to reconnect.");
        mGoogleApiClient.connect();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed() called, result: " + connectionResult);
        AppActivity.onGameServicesFailed();
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(mActivity, 111);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void connect() {
        try {
            Log.d(TAG, "connect GameServices");
            if (mGoogleApiClient.isConnected()) {
                return;
            }
            mGoogleApiClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            Log.d(TAG, "disconnect GameServices");
            if (mGoogleApiClient.isConnected()) {
                mGoogleApiClient.disconnect();
                Cocos2dxHelper.setBoolForKey("gameServicesConnected", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return mGoogleApiClient.isConnected();
    }

    public static void showGoogleAchievements() {
        try {
            Log.d(TAG, "showGoogleAchievements");
            try {
                mActivity.startActivityForResult(Games.Achievements.getAchievementsIntent(mGoogleApiClient), 12345678);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void addAchievementSteps(int i, String str) {
        try {
            if (mGoogleApiClient.isConnected()) {
                Log.d(TAG, "addAchievementSteps " + i + " id " + str);
                try {
                    Games.Achievements.increment(mGoogleApiClient, str, i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void setAchievementSteps(int i, String str) {
        try {
            if (mGoogleApiClient.isConnected()) {
                Log.d(TAG, "setAchievementSteps " + i + " id " + str);
                try {
                    Games.Achievements.setSteps(mGoogleApiClient, str, i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void unlockAchievement(String str) {
        try {
            if (mGoogleApiClient.isConnected()) {
                Log.d(TAG, "unlockAchievement id " + str);
                try {
                    Games.Achievements.unlock(mGoogleApiClient, str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void resetAllAchievements() {
        try {
            Log.d(TAG, "resetAllAchievements");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onActivityResult(int i, int i2, Intent intent) {
        if (i == 12345678 && i2 == 10001) {
            try {
                Log.d(TAG, "GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED");
                Cocos2dxHelper.setBoolForKey("gameServicesManualDisconnect", true);
                AppActivity.onGameServicesManualDisconnect();
                FingerAccount.disconnectGoogle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
