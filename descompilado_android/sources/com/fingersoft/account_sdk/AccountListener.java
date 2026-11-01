package com.fingersoft.account_sdk;

/* JADX INFO: loaded from: classes.dex */
public interface AccountListener {
    void onDataRead(AccountManager.DataSlotType dataSlotType, int i, String str, String str2, int i2);

    void onDataWrite(boolean z);

    void onEvent(AccountManager.AccountEvent accountEvent);

    void onFriendDataRead(AccountManager.DataSlotType dataSlotType, int i, Friend[] friendArr);

    void onLoginCancelled(AccountManager.AccountID accountID);

    void onLoginError(AccountManager.AccountID accountID, int i);

    void onLoginSuccess(AccountManager.AccountID accountID, int i);

    void onLogout(AccountManager.AccountID accountID);

    void onReadFriendRequests(String[] strArr);
}
