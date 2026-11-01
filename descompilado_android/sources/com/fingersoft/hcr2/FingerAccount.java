package com.fingersoft.hcr2;

import android.app.Activity;
import com.fingersoft.account_sdk.AccountListener;
import com.fingersoft.account_sdk.AccountManager;
import com.fingersoft.account_sdk.Friend;
import org.cocos2dx.lib.Cocos2dxHelper;

/* JADX INFO: loaded from: classes.dex */
public class FingerAccount {
    private static final int SLOT_TYPE_PRIVATE = 0;
    private static final int SLOT_TYPE_PUBLIC = 1;
    private static final String TAG = "hcr2 - FSAccount";
    private static final String accountId = "hcr2";
    private static final String apiKey = "8fd861e9-7bd3-42a6-9ea3-5801c975f464";
    private static AccountManager mAccountManager = null;
    private static String serverAddress = "";

    public static AccountManager getAccountManager() {
        return mAccountManager;
    }

    public static void init(Activity activity, String str, String str2) {
        Log.d(TAG, "Connecting to " + str2);
        serverAddress = str2;
        mAccountManager = new AccountManager(activity, apiKey, "hcr2", str, str2, new AccountListener() { // from class: com.fingersoft.hcr2.FingerAccount.1
            @Override // com.fingersoft.account_sdk.AccountListener
            public void onLoginSuccess(AccountManager.AccountID accountID, int i) {
                Log.d(FingerAccount.TAG, "onLoginSuccess");
                if (accountID == AccountManager.AccountID.FINGERSOFT) {
                    if (i == 103) {
                        AppActivity.onLoginSuccess(0);
                    }
                } else if (accountID == AccountManager.AccountID.FACEBOOK) {
                    AppActivity.onLoginSuccess(1);
                } else if (accountID == AccountManager.AccountID.GOOGLE) {
                    AppActivity.onLoginSuccess(2);
                } else {
                    AppActivity.onLoginSuccess(4);
                }
                if (FingerAccount.mAccountManager == null || !FingerAccount.mAccountManager.isGoogleConnected() || Cocos2dxHelper.getBoolForKey("gameServicesManualDisconnect", false)) {
                    return;
                }
                AppActivity.connectGameServices();
            }

            @Override // com.fingersoft.account_sdk.AccountListener
            public void onLoginCancelled(AccountManager.AccountID accountID) {
                Log.d(FingerAccount.TAG, "onLoginCancelled");
                AppActivity.onLoginCancel();
            }

            @Override // com.fingersoft.account_sdk.AccountListener
            public void onLoginError(AccountManager.AccountID accountID, int i) {
                Log.d(FingerAccount.TAG, "onLoginError");
                AppActivity.onLoginError();
            }

            @Override // com.fingersoft.account_sdk.AccountListener
            public void onLogout(AccountManager.AccountID accountID) {
                Log.d(FingerAccount.TAG, "onLogout");
                AppActivity.onLogout();
            }

            @Override // com.fingersoft.account_sdk.AccountListener
            public void onDataRead(AccountManager.DataSlotType dataSlotType, int i, String str3, String str4, int i2) {
                boolean z = i2 == 200;
                Log.d(FingerAccount.TAG, "onDataRead");
                Log.d(FingerAccount.TAG, " isSuccess: " + z);
                Log.d(FingerAccount.TAG, " dataSlotType: " + dataSlotType);
                Log.d(FingerAccount.TAG, " slotID: " + i);
                Log.d(FingerAccount.TAG, " playerID: " + str3);
                Log.d(FingerAccount.TAG, " data: " + str4);
                if (dataSlotType == AccountManager.DataSlotType.DATA_SLOT_TYPE_PRIVATE) {
                    AppActivity.onDataRead(z, 0, i, str3, str4, i2);
                } else if (dataSlotType == AccountManager.DataSlotType.DATA_SLOT_TYPE_PUBLIC) {
                    AppActivity.onDataRead(z, 1, i, str3, str4, i2);
                }
            }

            @Override // com.fingersoft.account_sdk.AccountListener
            public void onFriendDataRead(AccountManager.DataSlotType dataSlotType, int i, Friend[] friendArr) {
                Log.d(FingerAccount.TAG, "onFriendDataRead");
                if (friendArr == null || friendArr.length <= 0) {
                    return;
                }
                Log.d(FingerAccount.TAG, "Friends:");
                for (Friend friend : friendArr) {
                    Log.d(FingerAccount.TAG, "Facebook profile pic url: " + friend.getProfilePictureLink());
                    StringBuilder sb = new StringBuilder();
                    sb.append("Player ID: ");
                    sb.append(friend.getPlayerData());
                    sb.append(friend.isFacebookFriend() ? " (FB friend)" : "");
                    Log.d(FingerAccount.TAG, sb.toString());
                    Log.d(FingerAccount.TAG, "  ACS GUID: : " + friend.getGuid());
                    if (friend.getName() != null) {
                        Log.d(FingerAccount.TAG, " Facebook Name: " + friend.getName());
                    }
                    if (friend.getEmail() != null) {
                        Log.d(FingerAccount.TAG, "  Email: " + friend.getEmail());
                    }
                    if (friend.getFacebookID() != null) {
                        Log.d(FingerAccount.TAG, "  FB id: " + friend.getFacebookID());
                    }
                }
                AppActivity.onFriendDataRead(i, friendArr);
            }

            @Override // com.fingersoft.account_sdk.AccountListener
            public void onDataWrite(boolean z) {
                Log.d(FingerAccount.TAG, "onDataWrite " + z);
                AppActivity.onDataWrite(z);
            }

            @Override // com.fingersoft.account_sdk.AccountListener
            public void onEvent(AccountManager.AccountEvent accountEvent) {
                Log.d(FingerAccount.TAG, "onEvent: " + accountEvent.name());
                if (accountEvent == AccountManager.AccountEvent.FACEBOOK_FRIENDS_REFRESH_SUCCESS) {
                    FingerAccount.fetchFriendsPublicData(0);
                    return;
                }
                if (accountEvent == AccountManager.AccountEvent.FACEBOOK_FRIENDS_REFRESH_FAIL) {
                    Log.d(FingerAccount.TAG, "TODO: FACEBOOK_FRIENDS_REFRESH_FAIL");
                    return;
                }
                if (accountEvent == AccountManager.AccountEvent.READ_PUBLIC_DATA_FAILED) {
                    Log.d(FingerAccount.TAG, "READ_PUBLIC_DATA_FAILED");
                    AppActivity.onDataRead(false, 1, -1, "", "", 0);
                } else if (accountEvent == AccountManager.AccountEvent.READ_PRIVATE_DATA_FAILED) {
                    Log.d(FingerAccount.TAG, "READ_PRIVATE_DATA_FAILED");
                    AppActivity.onDataRead(false, 0, -1, "", "", 0);
                }
            }

            @Override // com.fingersoft.account_sdk.AccountListener
            public void onReadFriendRequests(String[] strArr) {
                for (String str3 : strArr) {
                    Log.d(FingerAccount.TAG, "Friend request received: " + str3);
                }
            }
        });
        if (Config.ENABLE_ANDROID_LOG) {
            AccountManager accountManager = mAccountManager;
            AccountManager.enableLogging();
        } else {
            AccountManager accountManager2 = mAccountManager;
            AccountManager.disableLogging();
        }
    }

    public static String getServerAddress() {
        return serverAddress;
    }

    public static boolean isConnected() {
        return isGoogleConnected() || isFacebookConnected() || isEmailConnected();
    }

    public static boolean isGoogleConnected() {
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            return accountManager.isGoogleConnected();
        }
        return false;
    }

    public static boolean isGoogleGameServicesConnected() {
        return AppActivity.isGameServicesConnected();
    }

    public static boolean isFacebookConnected() {
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            return accountManager.isFacebookConnected();
        }
        return false;
    }

    public static boolean isEmailConnected() {
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            return accountManager.isFingersoftConnected();
        }
        return false;
    }

    public static void connectWithFacebook() {
        if (mAccountManager != null) {
            Log.d(TAG, "connectWithFacebook");
            mAccountManager.loginToFacebook();
        }
    }

    public static void refreshFacebookFriends() {
        if (mAccountManager != null) {
            Log.d(TAG, "refreshFacebookFriends");
            mAccountManager.refreshFacebookFriends();
        }
    }

    public static void connectWithEmail(String str) {
        Log.d(TAG, "connectWithEmail");
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            accountManager.registerEmail(str);
        }
    }

    public static void connectWithGoogle() {
        Log.d(TAG, "connectWithGoogle");
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            accountManager.loginToGoogle();
        }
    }

    public static void disconnectFingerAccount() {
        Log.d(TAG, "disconnectFingerAccount");
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            accountManager.logoutGoogle();
            mAccountManager.logoutFacebook();
            mAccountManager.logoutFingersoft();
        }
    }

    public static void disconnectGoogle() {
        Log.d(TAG, "disconnectGoogle");
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            accountManager.logoutGoogle();
            AppActivity.disconnectGameServices();
        }
    }

    public static void disconnectFacebook() {
        Log.d(TAG, "disconnectFacebook");
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            accountManager.logoutFacebook();
        }
    }

    public static void disconnectEmail() {
        Log.d(TAG, "disconnectEmail");
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            accountManager.logoutFingersoft();
        }
    }

    public static void validateDeviceWithCode(String str) {
        Log.d(TAG, "Validating device");
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            accountManager.validateDevice(str);
        }
    }

    public static void updatePrivateData(int i, String str) {
        Log.d(TAG, "updatePrivateData");
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            accountManager.updatePrivateData(i, str);
        }
    }

    public static void updatePublicData(int i, String str) {
        Log.d(TAG, "updatePublicData");
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            accountManager.updatePublicData(i, str);
        }
    }

    public static void commitData() {
        Log.d(TAG, "commitData");
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            accountManager.commitData();
        }
    }

    public static void fetchPrivateData(int i) {
        Log.d(TAG, "fetchPrivateData");
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            accountManager.fetchPrivateData(i);
        }
    }

    public static void fetchPublicData(int i) {
        Log.d(TAG, "fetchPublicData");
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            accountManager.fetchPublicData(i);
        }
    }

    public static void fetchFriendsPublicData(int i) {
        Log.d(TAG, "fetchFriendsPublicData");
        AccountManager accountManager = mAccountManager;
        if (accountManager != null) {
            accountManager.getFriendData(i);
        }
    }
}
