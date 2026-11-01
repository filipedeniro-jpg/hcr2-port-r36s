package com.fingersoft.account_sdk;

import android.app.Activity;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class FriendManager {
    private static FriendManager mInstance;
    private Activity mActivity;
    private Friend[] mFriends;

    public void initialize(Activity activity) {
        this.mActivity = activity;
    }

    public static FriendManager getInstance() {
        if (mInstance == null) {
            mInstance = new FriendManager();
        }
        return mInstance;
    }

    public void storeFriendJson(JSONObject jSONObject) {
        Utils.setValue(this.mActivity, "friend_data", jSONObject.toString());
        updateFriendInfo();
    }

    public void setGuidFbIdList(JSONArray jSONArray) {
        Utils.setValue(this.mActivity, "friend_guid_fbid_list", jSONArray.toString());
    }

    public Friend getFriendByGUID(String str) {
        if (this.mFriends == null) {
            AccountManager.log("No guid fbidlist avail, refresh");
            updateFriendInfo();
        }
        try {
            String valueString = Utils.getValueString(this.mActivity, "friend_guid_fbid_list", "");
            AccountManager.log("guidString: " + valueString);
            if (valueString.length() == 0) {
                AccountManager.log("Guidstring len 0");
                return null;
            }
            JSONArray jSONArray = new JSONArray(valueString);
            int length = jSONArray.length();
            if (length == 0) {
                AccountManager.log("No friends");
                return null;
            }
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("global_guid");
                AccountManager.log("comparing " + string + " :: " + str);
                if (string.equals(str)) {
                    String string2 = jSONObject.getString("fb_id");
                    AccountManager.log("guid match, searching fbid " + string2);
                    for (Friend friend : this.mFriends) {
                        AccountManager.log("comparing " + friend.getFacebookID() + " " + string2);
                        if (friend.getFacebookID().equals(string2)) {
                            friend.setGuid(str);
                            AccountManager.log("friend found");
                            return friend;
                        }
                    }
                }
            }
            return null;
        } catch (Exception e) {
            AccountManager.log("Exception at getFriendByGUID :: " + e);
            return null;
        }
    }

    private void updateFriendInfo() {
        String valueString = Utils.getValueString(this.mActivity, "friend_data", "");
        if (valueString.length() == 0) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONObject(valueString).getJSONArray("data");
            int length = jSONArray != null ? jSONArray.length() : 0;
            if (length == 0) {
                return;
            }
            this.mFriends = new Friend[length];
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                this.mFriends[i] = new Friend(jSONObject.getString("id"), jSONObject.getString("first_name"), null, jSONObject.getJSONObject("picture").getJSONObject("data").getString("url"), null);
            }
        } catch (Exception e) {
            AccountManager.log("updateFriendInfo :: Exception " + e);
        }
    }

    public Friend[] getFriends() {
        updateFriendInfo();
        return this.mFriends;
    }
}
